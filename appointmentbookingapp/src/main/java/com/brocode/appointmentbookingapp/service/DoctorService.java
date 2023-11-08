package com.brocode.appointmentbookingapp.service;

import java.util.List;

import com.brocode.appointmentbookingapp.entity.Doctor;

public interface DoctorService {
	
	public List<Doctor> getAlldoctors();

	public Doctor saveDoctor(Doctor doctor);
	
	public void createDoctors();

	public Doctor getDoctorById(int id);

}
