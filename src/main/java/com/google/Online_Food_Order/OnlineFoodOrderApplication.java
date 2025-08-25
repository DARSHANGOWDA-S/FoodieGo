package com.google.Online_Food_Order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class OnlineFoodOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineFoodOrderApplication.class, args);
	}

}
