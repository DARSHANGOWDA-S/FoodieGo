package com.google.Online_Food_Order.Contoller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.Online_Food_Order.Entity.User;
import com.google.Online_Food_Order.Service.UserService;
import com.google.Online_Food_Order.dto.ResponseStructure;

import jakarta.validation.Valid;

@RequestMapping("myuser/api")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<User>> createUser(@Valid @RequestBody User user) {

		User saveduser = userService.createUser(user);
		ResponseStructure<User> rs = new ResponseStructure<>();
		rs.setData(saveduser);
		rs.setMessage("User Saved");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<>(rs, HttpStatus.CREATED);

	}

	@GetMapping("/getUserBYId/{id}")
	public ResponseEntity<ResponseStructure<User>> getUserBYId(@PathVariable int id) {
		User userById = userService.getUserById(id);
		ResponseStructure<User> rs = new ResponseStructure<>();
		rs.setData(userById);
		rs.setMessage("User Fetched");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(rs, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/getall")
	public ResponseEntity<ResponseStructure<List<User>>> getALLUser() {
		List<User> allUser = userService.getAllUser();
		ResponseStructure<List<User>> rs = new ResponseStructure<>();
		rs.setData(allUser);
		rs.setMessage("ALL Users Fetched");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(rs, HttpStatus.OK);
	}


	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable int id,@Valid @RequestBody User user){
		User updateUser = userService.updateUser(user, id);
		ResponseStructure<User> rs = new ResponseStructure<>();
		rs.setData(updateUser);
		rs.setMessage("User Updated");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	
	
	@PatchMapping("/uploadimage/{id}")
	public ResponseEntity<ResponseStructure<String>> uploadimage(@RequestParam MultipartFile image,
			@PathVariable int id)throws IOException{
		String uploadImage = userService.uploadImage(image, id);
		
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData(uploadImage);
		rs.setMessage("Image Uploaded");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/{id}/user/getimage")
	public ResponseEntity<byte[]> getImage(@PathVariable Integer id){
		byte[] image = userService.getImage(id);
	
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		
	}
		
	
	@DeleteMapping("/deleteimage/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteimage(@PathVariable int id){
		userService.deleteImage(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PatchMapping("/updateImage/{id}")
	public ResponseEntity<ResponseStructure<String>> updateImage(@RequestParam MultipartFile image,
			@PathVariable int id)throws IOException{
		
		String updateImage = userService.updateImage(image, id);
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData(updateImage);
		rs.setMessage("Image updated");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(rs, HttpStatus.OK);
		
		
	}
	
	
	
	
	
	
	
//	@PostMapping("/assgnOrder/{uid}")
//	public ResponseEntity<ResponseStructure<User>> assignOreder(@PathVariable int uid, @RequestBody Set<Integer> oid) {
//		User assignOrder = userService.assignOrder(uid, oid);
//		ResponseStructure<User> rs = new ResponseStructure<>();
//		rs.setData(assignOrder);
//		rs.setMessage("Order Assigned");
//		rs.setStatusCode(HttpStatus.OK.value());
//
//		return new ResponseEntity<>(rs, HttpStatus.OK);
//	}
	
	

}
