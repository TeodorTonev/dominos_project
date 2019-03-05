package com.example.demo.product.DTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginDTO {
	private String email;
	private String password;
}
