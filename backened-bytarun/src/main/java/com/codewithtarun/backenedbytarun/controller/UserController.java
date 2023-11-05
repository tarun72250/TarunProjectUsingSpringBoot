package com.codewithtarun.backenedbytarun.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codewithtarun.backenedbytarun.exception.UserNotFoundException;
import com.codewithtarun.backenedbytarun.model.User;
import com.codewithtarun.backenedbytarun.repository.UserRepository;

@RestController
@CrossOrigin("*")
public class UserController {

	@Autowired
	private UserRepository userrepo;
	
	@PostMapping("/user")
	User newUser(@RequestBody User newUser)
	{
		return userrepo.save(newUser);
	}
	
	//get all users
	@GetMapping("/users")
	List<User> getAllUsers(){
		return userrepo.findAll();
	}
	
	//get user by particular id 
	@GetMapping("/user/{id}")
	User getUserById(@PathVariable Long id)
	{
		return userrepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
	}
	
	//for updating edit existing record in db
	@PutMapping("/user/{id}")
	User updateUser(@RequestBody User newUser,@PathVariable long id)
	{
		return userrepo.findById(id).map(user ->{
			user.setUsername(newUser.getUsername());
			user.setName(newUser.getName());
			user.setEmail(newUser.getEmail());
			return userrepo.save(user);
		}).orElseThrow(()->new UserNotFoundException(id));
	}
	
	
	@DeleteMapping("/user/{id}")
	String deleteUser(@PathVariable long id)
	{
		if(!userrepo.existsById(id))
		{
			throw new UserNotFoundException(id);
		}
		userrepo.deleteById(id);
		return "User with id :"+id+" has been deleted succesfully";
		
	}
	
}
