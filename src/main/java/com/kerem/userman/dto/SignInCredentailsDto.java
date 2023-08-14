package com.kerem.userman.dto;

import java.io.Serializable;

public class SignInCredentailsDto implements Serializable {
	private String email;
	private String password;
	
	
	
	public SignInCredentailsDto() {
	}

	public SignInCredentailsDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
