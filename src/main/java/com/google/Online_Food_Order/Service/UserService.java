package com.google.Online_Food_Order.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.google.Online_Food_Order.Entity.User;

public interface UserService {

	User createUser(User user);

	User getUserById(int id);

	List<User> getAllUser();

	User updateUser(User updUser, int id);

	void deleteUser(int id);
	
	
	public String uploadImage(MultipartFile file,Integer id) throws IOException;
	
	public byte[] getImage(Integer id);
	
	public void deleteImage(Integer id);

	public String updateImage(MultipartFile updfile,Integer id) throws IOException;
	
	
	
//	User assignOrder(int uid,Set<Integer> oid);
}
