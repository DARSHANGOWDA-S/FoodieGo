package com.google.Online_Food_Order.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.google.Online_Food_Order.Entity.Food;
import com.google.Online_Food_Order.Entity.Order;
import com.google.Online_Food_Order.Entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	
	
	@Query("SELECT r.food FROM Restaurant r WHERE r.id=:restoId")
	List<Food> findFoodByRestaurantId(@Param(value = "restoId")int  id);
	
	@Query("SELECT r.orders FROM Restaurant r WHERE r.id=:restoId")
	List<Order> getAllOrderByRestoId(@Param(value = "restoId")int  id);

}
