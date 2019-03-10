package com.example.demo.product.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class User {
	private long id;
	@Pattern(regexp="[A-Za-z]{1,128}", message = "Invalid input.")
	private String firstName;
	@Pattern(regexp="[A-Za-z]{1,128}", message = "Invalid input.")
	private String lastName;
	@Size(min=6,max=50 , message="Invalid password. Length must be more than 6 characters.")
	private String password;
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please enter a valid email.")
	private String email;
	private String address;


	public User() {
	}

	public User(long id, @Pattern(regexp = "[A-Za-z]{1,128}", message = "Invalid input.") String firstName, @Pattern(regexp = "[A-Za-z]{1,128}", message = "Invalid input.") String lastName, @Size(min = 6, max = 50, message = "Invalid password. Length must be more than 6 characters.") String password, @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please enter a valid email.") String email, String address) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.email = email;
		this.address = address;
	}
}

