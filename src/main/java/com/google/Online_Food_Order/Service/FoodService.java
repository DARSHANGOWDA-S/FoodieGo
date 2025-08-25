package com.google.Online_Food_Order.Service;

import org.springframework.data.domain.Page;

import com.google.Online_Food_Order.Entity.Food;

public interface FoodService {

	Food createFood(Food food);
	
	Food getFoodById(Integer id);
	Page<Food> getAllFood(int pageNo,int pageSize);
	
	Food updateFood(Food uptFood,Integer id);
	
	void deleteFoodById(Integer id);
	
}
