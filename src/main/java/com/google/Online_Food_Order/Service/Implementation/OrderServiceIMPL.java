package com.google.Online_Food_Order.Service.Implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.Online_Food_Order.Entity.Food;
import com.google.Online_Food_Order.Entity.Order;
import com.google.Online_Food_Order.Entity.OrderItem;
import com.google.Online_Food_Order.Entity.OrderStatus;
import com.google.Online_Food_Order.Entity.Restaurant;
import com.google.Online_Food_Order.Entity.User;
import com.google.Online_Food_Order.Exception.NoOrderFoundException;
import com.google.Online_Food_Order.Exception.PaymentFailedException;
import com.google.Online_Food_Order.Repository.OrderRepository;
import com.google.Online_Food_Order.Service.FoodService;
import com.google.Online_Food_Order.Service.OrderService;
import com.google.Online_Food_Order.Service.RestaurantService;
import com.google.Online_Food_Order.Service.UserService;
import com.google.Online_Food_Order.dto.BillResponse;
import com.google.Online_Food_Order.dto.OrderItemRequest;
import com.google.Online_Food_Order.dto.OrderRequest;
import com.google.Online_Food_Order.dto.PaymentDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Without using @AutoWired

public class OrderServiceIMPL implements OrderService {

	// Without using @AutoWired
	public final RestaurantService restaurantservice;
	public final FoodService foodservice;
	public final OrderRepository orderrepository;
	public final UserService userService;

	@Override
	public BillResponse generateBill(OrderRequest orderrequest) {
		Restaurant resto = restaurantservice.getById(orderrequest.getRestaurantId());
		StringBuilder summary = new StringBuilder();

		float totalprice = 0;

		for (OrderItemRequest items : orderrequest.getOrderItems()) {

			Food foodById = foodservice.getFoodById(items.getFoodId());
			float price = foodById.getPrice() * items.getQuantity();
			totalprice += price;
			summary.append(foodById.getName()).append("X").append(items.getQuantity()).append("=").append(price)
					.append("\n");

		}

		return new BillResponse(resto.getName(), summary.toString(), totalprice);
	}

	@Override
	public String payAndPlaceOrder(PaymentDto paymentdto) {

		if (paymentdto.isPaymentSuccessfull()) {
			

			Order order = new Order();
			order.setStatus(OrderStatus.PLACED);
			
			//To add / set Restaurant to Order
			Restaurant resto = restaurantservice.getById(paymentdto.getRestaurantId());
			order.setRestaurant(resto);
			
			//To set user to Order
			User userById = userService.getUserById(paymentdto.getUserId());
			order.setUser(userById);
			
			
			
			
			List<OrderItem> items = new ArrayList<>();
			double totalPrice = 0;

			for (OrderItemRequest request : paymentdto.getOrderItems()) {
				Food food = foodservice.getFoodById(request.getFoodId());
				int quantity = request.getQuantity();
				OrderItem orderItem = new OrderItem();

				orderItem.setFood(food);
				orderItem.setQunatity(quantity);
				orderItem.setOrder(order);

				items.add(orderItem);
				double price = food.getPrice() * request.getQuantity();
				totalPrice += price;

			}
			

			order.setOrderItems(items);
			order.setTotalPrice(totalPrice);
			orderrepository.save(order);
			return "Order has been placed";

		}
		// create custom exception[PaymentFailedException] and handle using global
		// exception
		else {
			throw new PaymentFailedException("Payment was not successful, hence order cannot be placed");
		}

	}

	@Override
	public Order getOrderById(int id) {
		Optional<Order> order = orderrepository.findById(id);
		if (order.isPresent()) {
			return order.get();
		} else {
			throw new NoOrderFoundException("No Orders Found with id : " + id);
		}
	}

	@Override
	public void deleteOrder(int id) {
		Order order = getOrderById(id);

		orderrepository.delete(order);
	}

	@Override
	public Order updateStatusByAdmin(OrderStatus status, Integer orderid) {
       Order order = getOrderById(orderid);
       order.setStatus(status);
		return orderrepository.save(order);
	}

	@Override
	public String cancelOrder(Integer orderid) {
          Order order = getOrderById(orderid);
          order.setStatus(OrderStatus.CANCELED);
          orderrepository.save(order);
		return "Your Order has been Cancelled, and Your Money will be refunded within 2 days";
	}

}
