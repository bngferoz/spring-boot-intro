package com.springFeroz.photoapp.users.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springFeroz.photoapp.users.service.UsersService;
import com.springFeroz.photoapp.users.shared.UserDTO;
import com.springFeroz.photoapp.users.ui.model.LoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private UsersService usersService;
	private Environment environment;
	
	public AuthenticationFilter()
	{}
	
	public AuthenticationFilter(Environment environment, UsersService usersService, AuthenticationManager authenticationManager)
	{
		this.usersService = usersService;
		this.environment = environment;
		super.setAuthenticationManager(authenticationManager);
		
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException
	{
		try
		{
			LoginRequestModel cred = new ObjectMapper()
					.readValue(req.getInputStream(), LoginRequestModel.class);
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
						cred.getEmail(), 
						cred.getPassword(),
						new ArrayList<>()));
		}
		catch (IOException e) {
			throw new RuntimeException();
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, 
			FilterChain chain, Authentication auth) throws IOException, ServletException{
		String userName = ((User) auth.getPrincipal()).getUsername();
		UserDTO userDetails = usersService.getUserDetailsByEmail(userName);
		
		String token = Jwts.builder()
				.setSubject(userDetails.getUserId())
				.setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(environment.getProperty("token.expiration_time"))))
				.signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
				.compact();
		res.addHeader("token", token);
		res.addHeader("userId", userDetails.getUserId());
	}

}
