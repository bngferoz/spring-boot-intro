package com.springFeroz.app.ws.ui.controller;

import java.awt.PageAttributes.MediaType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springFeroz.app.ws.exceptions.UserServiceException;
import com.springFeroz.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.springFeroz.app.ws.ui.model.request.UserDetailsRequestModel;
import com.springFeroz.app.ws.ui.model.response.UserRest;
import com.springFeroz.app.ws.userservice.UserService;

@RestController
@RequestMapping("/users")//http://localhost:8080/users
public class UserController {
	
	@Autowired
	UserService userService;
	
	Map<String,UserRest> users;
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "50") int limit,
			@RequestParam(value = "sort", required = false, defaultValue = "desc") String sort)
	{
		return "get users was called with page : "+page+" and limit: "+limit+" sort: "+sort;
	}
	
//	@GetMapping(path="/{userId}", 
//			produces = {
//					"application/json","application/xml"
//			})
//	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
//	{
//		UserRest returnValue = new UserRest();
//		returnValue.setEmail("x@x.com");
//		returnValue.setFirstName("Feroz");
//		returnValue.setLastName("Ahmed");
//		return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK); 
//	}
	
	@GetMapping(path="/{userId}", 
			produces = {
					"application/json","application/xml"
			})
	public ResponseEntity<UserRest> getUser(@PathVariable String userId)
	{
		//if(true)throw new UserServiceException("User service exception is thrown!");
		if(users.containsKey(userId))
		{

			return new ResponseEntity<UserRest>(users.get(userId),HttpStatus.OK); 
		}
		else
		{

			return new ResponseEntity<UserRest>(HttpStatus.NO_CONTENT); 
		}
	}
	
	
	@PostMapping(consumes  = {
			"application/json","application/xml"
	},produces = {
			"application/json","application/xml"
	})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails)
	{
		UserRest returnValue = userService.createUser(userDetails);
		return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK); 
	}
	
	@PutMapping(path="/{userId}",
			consumes  = {
					"application/json","application/xml"
			},produces = {
					"application/json","application/xml"
			})
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails)
	{
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails; 
	}
	
	@DeleteMapping(path="/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable String userId)
	{
		users.remove(userId);
		return ResponseEntity.noContent().build();
	}
}
