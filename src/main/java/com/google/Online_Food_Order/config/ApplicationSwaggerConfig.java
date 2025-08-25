package com.google.Online_Food_Order.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class ApplicationSwaggerConfig {
	
	@Bean
	OpenAPI openAPI() {
		Server localhost=new Server();
		localhost.setUrl("http://localhost:8080");
		localhost.setDescription("Local Envinorment");
		
		Contact contact=new Contact();
		contact.setEmail("sagargowda64@gmail.com");
		contact.setName("Sagar N B");
		
		Info info=new Info().title("Online_Food_Order").version("1.0").contact(contact).description("This documentation exposes API to endpoinds to manage food application ");
		
		
		return new OpenAPI().info(info).servers(List.of(localhost));
		
		
		
				
	}

}
