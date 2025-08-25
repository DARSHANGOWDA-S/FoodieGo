package com.google.Online_Food_Order.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderItemRequest {

	@NotNull
	private Integer FoodId;
	@Min(1)
	private int quantity;

}
