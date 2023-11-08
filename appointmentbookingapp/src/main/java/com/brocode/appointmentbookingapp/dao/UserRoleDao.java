package com.brocode.appointmentbookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brocode.appointmentbookingapp.entity.UserRole;



public interface UserRoleDao extends JpaRepository<UserRole, Integer>{
	public UserRole findByAuthority(String authority);
}
