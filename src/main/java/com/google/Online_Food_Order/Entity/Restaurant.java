package com.google.Online_Food_Order.Entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull
	@NotBlank
	private String name;
	
	
	@NotNull
	@NotBlank
	private String address;
	
	@NotNull(message = "Contact number is required")
	@Min(value = 1000000000L, message = "Contact number must be at least 10 digits")
	private long contactNumber;
	
	@NotNull
	@NotBlank
	private String email;
	@CreationTimestamp
	private LocalDateTime createdAt;
	@UpdateTimestamp
	private LocalDateTime updatedAt;

		@ManyToMany
		@JoinTable(                        //used To create FK in owner side
		    name = "restaurant_food",
		    joinColumns = @JoinColumn(name = "resto_id"),
		    inverseJoinColumns = @JoinColumn(name = "food_id")
		)
		private List<Food> food;
		
		@OneToMany(mappedBy = "restaurant")   // Simple the table which contain the FK that will be owner
		private List<Order> orders;            // Here the list of orders contain the FK of Restaurant so ...


}
