package com.brocode.appointmentbookingapp.service;

import java.util.List;



//import org.springframework.security.core.userdetails.UserDetailsService;

import com.brocode.appointmentbookingapp.entity.User;

public interface UserService
//extends UserDetailsService
{
	public void createAdmin();
	
	public List<User> getAllUsers();
	
	public User getUser(String phNo);
	
	public User createUser(User user);
	
	public User updateUser(User user);
}
