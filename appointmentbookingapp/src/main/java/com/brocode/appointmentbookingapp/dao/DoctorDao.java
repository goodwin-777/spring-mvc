package com.brocode.appointmentbookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brocode.appointmentbookingapp.entity.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Integer>{

}
