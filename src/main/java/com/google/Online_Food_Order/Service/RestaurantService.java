package com.google.Online_Food_Order.Service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.google.Online_Food_Order.Entity.Food;
import com.google.Online_Food_Order.Entity.Order;
import com.google.Online_Food_Order.Entity.Restaurant;

public interface RestaurantService {
	
	
	Restaurant creatRestaurant(Restaurant restaurant);
	
	Restaurant getById(Integer id);
	
	
	Page  getAllRestaurant(int pageNum, int pageSize,String sortBy );
	
	Restaurant updateRestaurant(Integer id,Restaurant updrestaurant);
	
	void delteRestaurant(Integer id); 
	
	Restaurant assignFood(Integer restaurantId,Set<Integer> foodId);
	
	List<Food> findFoodByRestaurantId(int id);

	List<Order> getAllOrderByRestoId(int id);
}
