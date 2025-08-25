package com.google.Online_Food_Order.Exception;

public class PaymentFailedException extends RuntimeException{
	
	public PaymentFailedException(String message) {
		super(message);
	}
}
