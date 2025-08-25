package com.google.Online_Food_Order.Service;

import com.google.Online_Food_Order.Entity.Order;
import com.google.Online_Food_Order.Entity.OrderStatus;
import com.google.Online_Food_Order.dto.BillResponse;
import com.google.Online_Food_Order.dto.OrderRequest;
import com.google.Online_Food_Order.dto.PaymentDto;

public interface OrderService {

	BillResponse generateBill(OrderRequest orderrequest);

	String payAndPlaceOrder(PaymentDto paymentdto);

	Order getOrderById(int id);
	void deleteOrder(int id);
	
	Order updateStatusByAdmin(OrderStatus status,Integer id);
	
	String cancelOrder(Integer id);

}
