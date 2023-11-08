package com.brocode.appointmentbookingapp.service;

import java.util.List;

import com.brocode.appointmentbookingapp.entity.Department;

public interface DepartmentService {

	public void createDepartments();
	
	public List<Department> getAllDepartments();
	
	public Department getDepartmentById(String dName);
}
