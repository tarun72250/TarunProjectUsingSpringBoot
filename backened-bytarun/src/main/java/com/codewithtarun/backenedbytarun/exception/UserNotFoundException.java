package com.codewithtarun.backenedbytarun.exception;

public class UserNotFoundException extends RuntimeException {

	
	public UserNotFoundException(Long id)
	{
		super("Could not find the User with id  :"+id);
	}
}
