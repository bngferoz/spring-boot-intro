package com.springFeroz.photoapp.users.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateUserRequestModel {

	@NotNull(message = "First Name can not be null")
	@Size(min = 2, message = "First Name must not be less than two characters")
	private String firstName;
	
	@NotNull(message = "Last Name can not be null")
	@Size(min = 2, message = "Last Name must not be less than two characters")
	private String lastName;
	
	@NotNull(message = "Password can not be null")
	@Size(min = 8, max = 16, message = "First Name must be equal or greater than 8 characters and less than 16 characters")
	private String password;
	
	@NotNull(message = "Email can not be null")
	@Email
	private String email;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
