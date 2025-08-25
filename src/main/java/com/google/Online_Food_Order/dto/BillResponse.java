package com.google.Online_Food_Order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BillResponse {
	
	private String restaurant;
	private String ordersummery;
	private float totalprice;

}
