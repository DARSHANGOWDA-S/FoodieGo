package com.google.Online_Food_Order.Entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	@NotBlank
	@Pattern(
			regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
			flags=Pattern.Flag.CASE_INSENSITIVE,
			message = "Invalid email format")
	private String email;
	
	@NotBlank
	@NotNull
	private String password;
	
	@NotNull
	private Long contactNum;
	
	@NotBlank
	private String address;
	private String gender;
	
	@Lob
	private byte[] iamge;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)// Imp to delete the User Even though its FK in Order
	private List<Order> orders; 

	
	
	
	
	
}
