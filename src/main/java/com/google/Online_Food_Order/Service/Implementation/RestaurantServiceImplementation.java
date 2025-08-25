package com.google.Online_Food_Order.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.Online_Food_Order.Entity.Food;
import com.google.Online_Food_Order.Entity.Order;
import com.google.Online_Food_Order.Entity.Restaurant;
import com.google.Online_Food_Order.Exception.NoRestarantException;
import com.google.Online_Food_Order.Repository.RestaurantRepository;
import com.google.Online_Food_Order.Service.RestaurantService;

@Service
public class RestaurantServiceImplementation implements  RestaurantService {

	
	@Autowired
	private RestaurantRepository restaurantrepository;
	
	
	@Autowired
	private FoodServiceImpl foodserviceImpl;
	
	
	@Override
	public Restaurant creatRestaurant(Restaurant restaurant) {
		
		return restaurantrepository.save(restaurant);
	}


	@Override
	public Restaurant getById(Integer id) {
		
	 Optional<Restaurant> opt=restaurantrepository.findById(id);
	
	 if(opt.isPresent()) {
		 return opt.get();
	 }
	 else {
		 
		 throw new NoRestarantException("No Restaurant Found with Id: " + id);
	 }
	 
	
	}


	@Override
	public Page getAllRestaurant(int pageNum, int pageSize, String sortBy) {
		Sort sort=Sort.by(sortBy).descending();
	    Pageable pageable=PageRequest.of(pageNum, pageSize, sort);
		Page page=restaurantrepository.findAll(pageable);
		return page;
	}


	@Override
	public Restaurant updateRestaurant(Integer id, Restaurant updrestaurant) {
	
		Restaurant existing=getById(id);
		
		existing.setAddress(updrestaurant.getAddress());
		existing.setContactNumber(updrestaurant.getContactNumber());
		existing.setEmail(updrestaurant.getEmail());
		existing.setName(updrestaurant.getName());
		
		return restaurantrepository.save(existing);
		
	}


	@Override
	public void delteRestaurant(Integer id) {
		Restaurant rs=getById(id);
		restaurantrepository.delete(rs);
		
	}


	@Override
	public Restaurant assignFood(Integer restaurantId, Set<Integer> foodId) {
		Restaurant resto = getById(restaurantId);
		
		List<Food> foodlist=new ArrayList<>();
		
		for(Integer id:foodId) {
			Food food = foodserviceImpl.getFoodById(id);        //getting food by using getFoodById(id) from foodserviceImpl
			foodlist.add(food);
		}
		resto.setFood(foodlist);
		
		
		
		return restaurantrepository.save(resto);
	}

	
	
	
	

	@Override
	public List<Food> findFoodByRestaurantId(int id) {
		List<Food> foods = restaurantrepository.findFoodByRestaurantId(id);
		if(foods==null || foods.size()==0) {
			throw new NoRestarantException("No Restaurant Found with Id: " + id+" Or food Not Found" );
		}
		else {
			return foods;
		}
		
	
	}
	@Override
	public List<Order> getAllOrderByRestoId(int id) {
		 List<Order> orders = restaurantrepository.getAllOrderByRestoId(id);
		if(orders==null || orders.size()==0) {
			throw new NoRestarantException("No Restaurant Found with Id: " + id+" Or Orders Not Found" );
		}
		else {
			return orders;
		}
		
	
	}
	
	
	
	
	




}
