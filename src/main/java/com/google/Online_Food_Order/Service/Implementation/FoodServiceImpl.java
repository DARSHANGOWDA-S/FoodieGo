package com.google.Online_Food_Order.Service.Implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.google.Online_Food_Order.Entity.Food;
import com.google.Online_Food_Order.Entity.Restaurant;
import com.google.Online_Food_Order.Exception.NoFoodFoundException;
import com.google.Online_Food_Order.Repository.FoodRepository;
import com.google.Online_Food_Order.Repository.RestaurantRepository;
import com.google.Online_Food_Order.Service.FoodService;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	FoodRepository foodrepository;

	@Autowired
	RestaurantRepository restaurantrepository;

	@Override
	public Food createFood(Food food) {

		Food savedfood = foodrepository.save(food);

		return savedfood;
	}

	@Override
	public Food getFoodById(Integer id) {

		Optional<Food> opt = foodrepository.findById(id);
		if (opt.isPresent()) {
			Food food = opt.get();
			return food;
		} else {
			throw new NoFoodFoundException("No Food Found with Id: " + id);
		}

	}

	@Override
	public Page<Food> getAllFood(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page page = foodrepository.findAll(pageable);

		return page;
	}

	@Override
	public Food updateFood(Food uptFood, Integer id) {
		Food oldFood = getFoodById(id);
		oldFood.setName(uptFood.getName());
		oldFood.setPrice(uptFood.getPrice());
		oldFood.setDescription(uptFood.getDescription());

		Food save = foodrepository.save(oldFood);

		return save;
	}

	
	
//	Since OwnerShip is given to Resto we cannnnnot possible to delete the food directly which are assigned to the Resto soo,
//	1st delete food from resto and then delete from db 
	
	
	@Override
	public void deleteFoodById(Integer id) {

		Food food = getFoodById(id); // Getting Food by Id , calling getFoodById(),,
                                          //Already have this method in this class so reuse
									 

		List<Restaurant> ListOfResto = food.getRestaurant();  //take list of resto which are assigned by this perticular food

		if (ListOfResto.size() == 0) {     //if no Resto assigned ,,,direct delete else,,
			foodrepository.delete(food);
			return;
		}

		for (Restaurant resto : ListOfResto) {  // iterate over each and every reasto and take/get that food from resto and remove it from resto
			resto.getFood().remove(food);
		}

		restaurantrepository.saveAll(ListOfResto);  // save all the list of resto after removing the food
		foodrepository.delete(food);  // also delete it from db

	}	

}
