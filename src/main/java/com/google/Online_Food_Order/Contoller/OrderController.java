package com.google.Online_Food_Order.Contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.Online_Food_Order.Entity.Order;
import com.google.Online_Food_Order.Entity.OrderStatus;
import com.google.Online_Food_Order.Service.OrderService;
import com.google.Online_Food_Order.dto.BillResponse;
import com.google.Online_Food_Order.dto.OrderRequest;
import com.google.Online_Food_Order.dto.PaymentDto;
import com.google.Online_Food_Order.dto.ResponseStructure;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	@Autowired
	OrderService orderservice;

	@PostMapping("/generateBill")
	public ResponseEntity<ResponseStructure<BillResponse>> generateBill(@Valid @RequestBody OrderRequest orderrequest) {
		BillResponse bill = orderservice.generateBill(orderrequest);

		ResponseStructure<BillResponse> rs = new ResponseStructure<>();
		rs.setData(bill);
		rs.setMessage("Bill has been generated");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<>(rs, HttpStatus.CREATED);

	}

	@PostMapping("/payBill")
	public ResponseEntity<ResponseStructure<String>> payAndPlaceOrder(@Valid @RequestBody PaymentDto paymentdto) {

		String payAndPlaceOrder = orderservice.payAndPlaceOrder(paymentdto);
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData(payAndPlaceOrder);
		rs.setMessage("Order placed");
		rs.setStatusCode(HttpStatus.CREATED.value());

		return new ResponseEntity<>(rs, HttpStatus.CREATED);

	}



	@GetMapping("getorderBYId/{id}")
	public ResponseEntity<ResponseStructure<Order>> getorderBYId(@PathVariable int id) {
		Order orderById = orderservice.getOrderById(id);
		ResponseStructure<Order> rs = new ResponseStructure<>();
		rs.setData(orderById);
		rs.setMessage(" Order fetched with id :" + id);
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(rs, HttpStatus.OK);

	}

	@DeleteMapping("delete/{id}")

	public ResponseEntity<ResponseStructure<Order>> deleteOrder(@PathVariable int id) {
		orderservice.deleteOrder(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
	
	@PatchMapping("updateStatus/{oid}")
	public ResponseEntity<ResponseStructure<Order>> updateStatusByAdmin(@PathVariable int oid,
			@RequestParam OrderStatus status){
		Order updateStatusByAdmin = orderservice.updateStatusByAdmin(status, oid);
		ResponseStructure<Order> rs = new ResponseStructure<>();
		rs.setData(updateStatusByAdmin);
		rs.setMessage(" Status of the Order :" + oid+" updated");
		rs.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	
	@PatchMapping("cancelOrder/{oid}")
	public ResponseEntity<ResponseStructure<String>> cancelOrder(@PathVariable int oid){
		String cancelOrder = orderservice.cancelOrder(oid);
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setData(cancelOrder);
		rs.setMessage(" Order Cancelled");
		rs.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(rs, HttpStatus.OK);
	}
	

}
