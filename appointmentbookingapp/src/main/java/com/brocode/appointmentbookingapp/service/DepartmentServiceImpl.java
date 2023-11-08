package com.brocode.appointmentbookingapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brocode.appointmentbookingapp.dao.DepartmentDao;
import com.brocode.appointmentbookingapp.entity.Department;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	private DepartmentDao departmentDao;
	
	public DepartmentServiceImpl(DepartmentDao departmentDao, DoctorService doctorService) {
		this.departmentDao = departmentDao;
	}
	
	@Override
	public void createDepartments() {
		
		Department department = new Department();
		department.setDName("Cardiology");
		
		Department department2 = new Department();
		department2.setDName("Orthopedics");
		
		Department department3 = new Department();
		department3.setDName("Oncology");
		
		Department department4 = new Department();
		department4.setDName("Neurology");
		
		Department department5 = new Department();
		department5.setDName("Gastroenterology");
		
		departmentDao.saveAll(Arrays.asList(department, department2, department3, department4, department5));
	}

	@Override
	public List<Department> getAllDepartments() {
		List<Department> departments = departmentDao.findAll();
		return departments;
	}

	@Override
	public Department getDepartmentById(String dName) {
		Department department = departmentDao.getReferenceById(dName);
		return department;
	}

}
