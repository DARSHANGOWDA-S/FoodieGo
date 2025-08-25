package com.google.Online_Food_Order.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.Online_Food_Order.Entity.Food;
import com.google.Online_Food_Order.Service.FoodService;
import com.google.Online_Food_Order.dto.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/food/api")
public class FoodController {
	

 @Autowired
 FoodService foodservice;
 
 
 
 @PostMapping("/save")
 public ResponseEntity<ResponseStructure<Food>> createFood(@Valid @RequestBody Food food){
	 
	 Food saved=foodservice.createFood(food);
	 
	 ResponseStructure<Food> rs=new ResponseStructure<>();
	 
	 rs.setData(saved);
	 rs.setMessage("Food Added SuccessFully");
	 rs.setStatusCode(HttpStatus.CREATED.value());

	 return new ResponseEntity<>(rs,HttpStatus.CREATED);
 }
 
 @GetMapping("/get/{id}")
 public ResponseEntity<ResponseStructure<Food>> getFoodById(@PathVariable int id){
	 
	 Food savedfood = foodservice.getFoodById(id);
	 
	 ResponseStructure<Food> rs=new ResponseStructure<>();
	 rs.setData(savedfood);
	 rs.setMessage("The food With "+id+" Found ");
	 rs.setStatusCode(HttpStatus.OK.value());
	 
	 return new ResponseEntity<>(rs,HttpStatus.OK);
 }
 
 @GetMapping("get")
 public ResponseEntity<Food> getAllFood( @RequestParam(defaultValue = "0",required = false) int pageNo,@RequestParam(defaultValue = "10",required = false)  int pageSize ){
	 Page<Food> allFood = foodservice.getAllFood(pageNo, pageSize);
	 ResponseStructure<Page> rs=new ResponseStructure<>();
	 rs.setData(allFood);
	 rs.setMessage("All food ");
	 rs.setStatusCode(HttpStatus.OK.value());
	 return new ResponseEntity(rs, HttpStatus.OK);
 }
	
 
 @PutMapping("/update/{id}")
 public ResponseEntity<ResponseStructure<Food>> updateFood(@Valid @RequestBody Food food ,@PathVariable int id){
	 Food updateFood = foodservice.updateFood(food, id);
	 ResponseStructure<Food> rs=new ResponseStructure<>();
	 rs.setData(updateFood);
	 rs.setMessage("Updated Success Fully ");
	 rs.setStatusCode(HttpStatus.OK.value());
	 
	 return new ResponseEntity<>(rs,HttpStatus.OK);
	 
 }
 
 
 @DeleteMapping("/delete/{id}")
 public ResponseEntity<Food> deleteFoodById(@PathVariable Integer id){
	 foodservice.deleteFoodById(id);
	
	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
 }
 
 
 
 
	
}
