package com.google.Online_Food_Order.Service.Implementation;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.Online_Food_Order.Entity.User;
import com.google.Online_Food_Order.Exception.NoFoodFoundException;
import com.google.Online_Food_Order.Repository.UserRepository;
import com.google.Online_Food_Order.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public User createUser(User user) {

		return userRepository.save(user);
	}

	@Override
	public User getUserById(int id) {
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			return user.get();
		}

		else {
			throw new NoFoodFoundException("No User Found with Id :" + id);
		}
	}

	
	@CacheEvict(value = "user_cache",key = "#id")
	@Override
	public void deleteUser(int id) {
		User user = getUserById(id);

		userRepository.delete(user);

	}

	@Cacheable(value = "user_cache")
	@Override
	public List<User> getAllUser() {
		List<User> all = userRepository.findAll();

		return all;
	}

	@CachePut(value = "user_cache", key = "#id")
	@Override
	public User updateUser(User updUser, int id) {

		User existeduser = getUserById(id);
		existeduser.setName(updUser.getName());
		existeduser.setEmail(updUser.getEmail());
		existeduser.setPassword(updUser.getPassword());
		existeduser.setAddress(updUser.getAddress());
		existeduser.setContactNum(updUser.getContactNum());
		existeduser.setGender(updUser.getGender());

		return userRepository.save(existeduser);
	}

	
	//To evict cache for every 2 min
	
	@CacheEvict(value = " user_cache",allEntries = true)
	@Scheduled(fixedRate = 120000)               //evict cache every 2 minutes
	public void evictAllCache() {
		System.out.println("Evicting all entries from 'user_cache' cache");
	}
	
	
		
	
	
	@Override
	public String uploadImage(MultipartFile file, Integer id) throws IOException {

		byte[] image = file.getBytes();
		User user = getUserById(id);
		user.setIamge(image);
		userRepository.save(user);

		return "Image Uploaded";
	}

	@Override
	public byte[] getImage(Integer id) {
		User user = getUserById(id);
		byte[] image = user.getIamge();
		if (image == null || image.length == 0) {
			throw new NoSuchElementException("User with ID: " + id + " Does Not have any Image");
		}
		return image;
	}

	@Override
	public void deleteImage(Integer id) {
		User user = getUserById(id);

		if (user.getIamge() == null || user.getIamge().length == 0) {
			throw new NoSuchElementException("User with ID: " + id + " Does Not have any Image");
		}

		user.setIamge(null);
		userRepository.save(user);

	}

	@Override
	public String updateImage(MultipartFile updfile, Integer id) throws IOException {
		User exist = getUserById(id);

		exist.setIamge(updfile.getBytes());

		userRepository.save(exist);
		return "Image Updated";
	}

//	@Override
//	public User assignOrder(int uid, Set<Integer> oid) {
//		User user = getUserById(uid);
//		List<Order> list=new ArrayList<>();
//		
//		for(Integer id:oid) {
//			Order orderById = orderService.getOrderById(id);
//			orderById.setUser(user);   // IMP Other wise Null
//
//			list.add(orderById);
//		}
//		user.setOrders(list);
//		
//		return userRepository.save(user);
//	}

}
