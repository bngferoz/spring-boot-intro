package com.springFeroz.app.ws.exceptions;

public class UserServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1319312265020447005L;
	public UserServiceException()
	{
		
	}
	public UserServiceException(String message)
	{
		super(message);
	}

}
