package com.brocode.appointmentbookingapp.service;

import com.brocode.appointmentbookingapp.entity.UserRole;

public interface UserRoleService {
	public void createAuthorities();
	
	public UserRole save(UserRole userRole);
	
	public UserRole findByAuthority(String authority);
}
