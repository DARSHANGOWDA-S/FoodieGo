package com.google.Online_Food_Order.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="Orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JsonIgnore
	@NotNull
	@NotBlank
	private Restaurant restaurant;

	@JsonIgnore
	@NotNull
	@NotBlank
	@OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;
	
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;
	
	
	private Double TotalPrice;
	
	@ManyToOne
	@JsonIgnore
	@NotNull
	@NotBlank
	@JoinColumn(name = "user_id")
	private User user;
	
}
