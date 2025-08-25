package com.google.Online_Food_Order.Contoller;

import java.util.List;
import java.util.Set;

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
import com.google.Online_Food_Order.Entity.Order;
import com.google.Online_Food_Order.Entity.Restaurant;
import com.google.Online_Food_Order.Service.RestaurantService;
import com.google.Online_Food_Order.dto.ResponseStructure;

@RestController
@RequestMapping("/restaurant/api")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantservice;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Restaurant>> creatRestaurant(@RequestBody Restaurant restaurant) {

		Restaurant response = restaurantservice.creatRestaurant(restaurant);
		ResponseStructure rs = new ResponseStructure<>();
		rs.setData(response);
		rs.setMessage("Restaurant Object Saved Successfully");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<>(rs, HttpStatus.CREATED);

	}

	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> getById(@PathVariable Integer id) {

		Restaurant response = restaurantservice.getById(id);

		ResponseStructure<Restaurant> rs = new ResponseStructure<>();

		rs.setData(response);
		rs.setMessage("Requested Object Found..");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(rs, HttpStatus.OK);

	}
	@GetMapping("/get")
	public ResponseEntity<ResponseStructure<Page>> getAllRestaurant(@RequestParam(defaultValue = "0",required = false) int  pageNum,
			@RequestParam(defaultValue = "5",required = false)  int pageSize,
			@RequestParam(defaultValue ="createdAt" ,required = false)  String sortBy) {
		Page ls = restaurantservice.getAllRestaurant(pageNum,pageSize,sortBy);

	    ResponseStructure<Page> rs = new ResponseStructure<>();
	    rs.setData(ls);
	    rs.setMessage("Found all");
	    rs.setStatusCode(HttpStatus.OK.value());

//	    return new ResponseEntity<>(rs, HttpStatus.OK);
//	    Or
	    return  ResponseEntity.ok(rs);	
	}

	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Restaurant>> updatedRestaurant(@PathVariable Integer id,@RequestBody Restaurant updrestaurant){
	Restaurant upd=restaurantservice.updateRestaurant(id, updrestaurant);
	ResponseStructure<Restaurant> rs=new ResponseStructure<>();
	rs.setData(upd);
	rs.setMessage("Successfully Updated");
	rs.setStatusCode(HttpStatus.OK.value());
	
	return new ResponseEntity<>(rs,HttpStatus.OK);
		
	}
	
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<Restaurant> delteRestaurant(@PathVariable Integer id){
		
	 restaurantservice.delteRestaurant(id);
	 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 
	}
	

	@PostMapping("/{restaurantId}/assignFood")
	public ResponseEntity<ResponseStructure<Restaurant>> assignFood(@PathVariable Integer restaurantId, @RequestBody Set<Integer> food){
		Restaurant restaurant = restaurantservice.assignFood(restaurantId, food);
		ResponseStructure<Restaurant> rs = new ResponseStructure<>();
		rs.setData(restaurant);
		rs.setMessage("Assigned");
		rs.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(rs);
	}
	
	@GetMapping("/findFoodByRestaurantId/{id}")
	public ResponseEntity<ResponseStructure<List<Food>>> findFoodByRestaurantId(@PathVariable int id){
		List<Food> foodByRestaurantId = restaurantservice.findFoodByRestaurantId(id);
		ResponseStructure<List<Food>> rs = new ResponseStructure<>();
		rs.setData(foodByRestaurantId);
		rs.setMessage("Fetched The Foods");
		rs.setStatusCode(HttpStatus.OK.value());
		
		return  ResponseEntity.ok(rs);	
	}
	
	@GetMapping("/getAllOrderByRestoId/{restoid}")
	public ResponseEntity<ResponseStructure<List<Order>>> getAllOrderByRestoId(@PathVariable int restoid) {

		List<Order> allOrderByRestoId = restaurantservice.getAllOrderByRestoId(restoid);

		ResponseStructure<List<Order>> rs = new ResponseStructure<>();
		rs.setData(allOrderByRestoId);
		rs.setMessage("All Orders are fetched with id :" + restoid);
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(rs, HttpStatus.OK);

	}
	

}
