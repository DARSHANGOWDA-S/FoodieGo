package com.google.Online_Food_Order.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.Online_Food_Order.Entity.Food;

public interface FoodRepository  extends JpaRepository<Food, Integer>{

}
