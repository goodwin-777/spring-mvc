package com.brocode.appointmentbookingapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brocode.appointmentbookingapp.dao.UserRoleDao;
import com.brocode.appointmentbookingapp.entity.UserRole;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDao userRoleDao;
	
	public UserRoleServiceImpl(UserRoleDao userRoleDao) {
		this.userRoleDao = userRoleDao;
	}
	
	@Override
	public void createAuthorities() {
		UserRole role = new UserRole();
		role.setAuthority("ROLE_USER");
		UserRole role2 = new UserRole();
		role2.setAuthority("ROLE_EMPLOYEE");
		UserRole role3 = new UserRole();
		role3.setAuthority("ROLE_ADMIN");
		
		List<UserRole> list = Arrays.asList(role, role2 , role3);
		Collection<UserRole> roles = list;
		
		
		userRoleDao.saveAll(roles);

	}

	@Override
	public UserRole save(UserRole userRole) {
		UserRole dbUserRole = userRoleDao.save(userRole);
		return dbUserRole;
	}

	@Override
	public UserRole findByAuthority(String authority) {
		UserRole userRole = userRoleDao.findByAuthority(authority);
		return userRole;
	}

}
