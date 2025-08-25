package com.google.Online_Food_Order.Exception;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.google.Online_Food_Order.dto.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ResponseStructure<String>> noSuchElementException(NoSuchElementException exception){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setData(exception.getMessage());
		rs.setMessage("Exception created and handled");
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(PaymentFailedException.class)
	public ResponseEntity<ResponseStructure<String>> PaymentFailedException(PaymentFailedException exception){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setData(exception.getMessage());
		rs.setMessage("Exception handled");
		rs.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		
		return new ResponseEntity<>(rs,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@ExceptionHandler(NoFoodFoundException.class)
	public ResponseEntity<ResponseStructure<String>> NoFoodFoundException(NoFoodFoundException exception){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setData(exception.getMessage());
		rs.setMessage("Exception handled");
		rs.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		
		return new ResponseEntity<>(rs,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
	@ExceptionHandler(NoRestarantException.class)
	public ResponseEntity<ResponseStructure<String>> NoRestarantException(NoRestarantException exception){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setData(exception.getMessage());
		rs.setMessage("Exception handled");
		rs.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		
		return new ResponseEntity<>(rs,HttpStatus.NOT_ACCEPTABLE);
		
	}
	@ExceptionHandler(NoOrderFoundException.class)
	public ResponseEntity<ResponseStructure<String>> NoOrderFoundException(NoOrderFoundException exception){
		
		ResponseStructure<String> rs=new ResponseStructure<>();
		rs.setData(exception.getMessage());
		rs.setMessage("Exception handled");
		rs.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
		
		return new ResponseEntity<>(rs,HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
	

}
