package com.example.demo.product.DTO;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LoginDTO {
	@NotNull
	private String email;
	@NotNull
	private String password;
}
