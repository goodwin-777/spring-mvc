package com.brocode.appointmentbookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brocode.appointmentbookingapp.entity.Department;

public interface DepartmentDao extends JpaRepository<Department, String>{

}
