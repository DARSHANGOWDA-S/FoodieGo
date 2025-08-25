package com.google.Online_Food_Order.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	private String description;
	
	@NotNull
	@NotBlank  // ensures price > 0
	private float price;
	
	@JsonIgnore
	@NotNull
	@NotBlank
	@ManyToMany(mappedBy = "food")   // Giving Ownership To the Restaurant [Other Side],,
    List<Restaurant> restaurant;
}
