package com.brocode.appointmentbookingapp.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.brocode.appointmentbookingapp.dao.UserDao;
import com.brocode.appointmentbookingapp.entity.User;
import com.brocode.appointmentbookingapp.entity.UserRole;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UserServiceImpl implements UserService{

	private UserDao userDao;
	
	private UserRoleService userRoleService;
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserServiceImpl(UserDao userDao, UserRoleService userRoleService,
						   BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDao = userDao;
		this.userRoleService = userRoleService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	public void createAdmin() {
		User user = new User();
		user.setPhNo("9789554431");
		user.setFirstName("Goodwin");
		user.setLastName("Enoch");
		user.setPassword(bCryptPasswordEncoder.encode("Admin@123"));
		user.setStatus(true);
		user.setPincode("635001");
		
		Collection<UserRole> roles = new ArrayList<UserRole>();
		roles.add(userRoleService.findByAuthority("ROLE_ADMIN"));
		user.setRole(roles);
		userDao.save(user);
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = userDao.findAll();
		return users;
	}
	
	@Override
	public User getUser(String phNo) {
		Optional<User> optional = userDao.findById(phNo);
		if (optional.isPresent()) {
			return optional.get();
		}
		else {
			throw new EntityNotFoundException("Wrong Phone number");
		}
	}
	
	@Override
	public User createUser(User user) {
		
		Optional<User> optional = userDao.findById(user.getPhNo());
		
		if (!optional.isPresent()) {
			user.setStatus(true);
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
			user.setRole(Arrays.asList(userRoleService.findByAuthority("ROLE_USER")));
			userDao.save(user);
			return user;
		}
		else {
			throw new EntityExistsException("Phone number already exists!");
		}
		
	}

	@Override
	public User updateUser(User user) {
		User dbUser = userDao.save(user);
		return dbUser;
	}

//	@ExceptionHandler
//	public String handleException(EntityExistsException exception, Model model) {
//		User user = new User();
//		model.addAttribute("user", user);
//		model.addAttribute("exception", exception.getMessage());
//		return "register";
//	}
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		User user = userDao.findByPhNo(username);
//		
//		if (user == null) {
//			throw new UsernameNotFoundException("Invalid phone number");
//		}
//		return new org.springframework.security.core.userdetails.User(user.getPhNo(), user.getPassword(), userRoles(user.getRole()));
//	}
//	
//	public Collection<GrantedAuthority> userRoles(Collection<UserRole> roles) {
//		Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
//		
//		for (UserRole role : roles) {
//			SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getAuthority());
//			collection.add(authority);
//		}
//		return collection;
//	}

}
