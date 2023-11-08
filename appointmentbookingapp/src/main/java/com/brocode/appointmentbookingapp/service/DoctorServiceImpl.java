package com.brocode.appointmentbookingapp.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.brocode.appointmentbookingapp.dao.DepartmentDao;
import com.brocode.appointmentbookingapp.dao.DoctorDao;
import com.brocode.appointmentbookingapp.entity.Department;
import com.brocode.appointmentbookingapp.entity.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService{

	private DoctorDao doctorDao;
	private DepartmentDao departmentDao;
	
	public DoctorServiceImpl(DoctorDao doctorDao, DepartmentDao departmentDao) {
		this.doctorDao = doctorDao;
		this.departmentDao = departmentDao;
	}

	@Override
	public void createDoctors() {
		
		Department department1 = departmentDao.findById("Cardiology").get();
		Department department2 = departmentDao.findById("Gastroenterology").get();
		Department department3 = departmentDao.findById("Neurology").get();
		Department department4 = departmentDao.findById("Oncology").get();
		Department department5 = departmentDao.findById("Orthopedics").get();
		
		Doctor doctor = new Doctor(0, "John", "Doe", department1);
		Doctor doctor2 = new Doctor(0, "Jai", "Kumar", department1);
		Doctor doctor3 = new Doctor(0, "Srinivasan", "MS", department1);
		Doctor doctor4 = new Doctor(0, "Ramya", "Jayaram", department1);
		Doctor doctor5 = new Doctor(0, "Anuradha", "G", department1);
		
		Doctor doctor6 = new Doctor(0, "John", "Doe", department2);
		Doctor doctor7 = new Doctor(0, "Jai", "Kumar", department2);
		Doctor doctor8 = new Doctor(0, "Srinivasan", "MS", department2);
		Doctor doctor9 = new Doctor(0, "Ramya", "Jayaram", department2);
		Doctor doctor10 = new Doctor(0, "Anuradha", "G", department2);
		
		Doctor doctor11 = new Doctor(0, "John", "Doe", department3);
		Doctor doctor12 = new Doctor(0, "Jai", "Kumar", department3);
		Doctor doctor13 = new Doctor(0, "Srinivasan", "MS", department3);
		Doctor doctor14 = new Doctor(0, "Ramya", "Jayaram", department3);
		Doctor doctor15 = new Doctor(0, "Anuradha", "G", department3);
		
		Doctor doctor16 = new Doctor(0, "John", "Doe", department4);
		Doctor doctor17 = new Doctor(0, "Jai", "Kumar", department4);
		Doctor doctor18 = new Doctor(0, "Srinivasan", "MS", department4);
		Doctor doctor19 = new Doctor(0, "Ramya", "Jayaram", department4);
		Doctor doctor20 = new Doctor(0, "Anuradha", "G", department4);
		
		Doctor doctor21 = new Doctor(0, "John", "Doe", department5);
		Doctor doctor22 = new Doctor(0, "Jai", "Kumar", department5);
		Doctor doctor23 = new Doctor(0, "Srinivasan", "MS", department5);
		Doctor doctor24 = new Doctor(0, "Ramya", "Jayaram", department5);
		Doctor doctor25 = new Doctor(0, "Anuradha", "G", department5);
		
		List<Doctor> cardiologyDoctors = Arrays.asList(doctor, doctor2, doctor3, doctor4, doctor5);
		List<Doctor> gastroenterologyDoctors = Arrays.asList(doctor6, doctor7, doctor8, doctor9, doctor10);
		List<Doctor> neurologyDoctors = Arrays.asList(doctor11, doctor12, doctor13, doctor14, doctor15);
		List<Doctor> oncologyDoctors = Arrays.asList(doctor16, doctor17, doctor18, doctor19, doctor20);
		List<Doctor> orthopedicsDoctors = Arrays.asList(doctor21, doctor22, doctor23, doctor24, doctor25);

		department1.setDoctors(cardiologyDoctors);
		department2.setDoctors(gastroenterologyDoctors);
		department3.setDoctors(neurologyDoctors);
		department4.setDoctors(oncologyDoctors);
		department5.setDoctors(orthopedicsDoctors);
		
		departmentDao.save(department1);
		departmentDao.save(department2);
		departmentDao.save(department3);
		departmentDao.save(department4);
		departmentDao.save(department5);
		
	}

	@Override
	public List<Doctor> getAlldoctors() {
		List<Doctor> doctors = doctorDao.findAll();
		return doctors;
	}

	@Override
	public Doctor getDoctorById(int id) {
		Doctor doctor = doctorDao.getReferenceById(id);
		return doctor;
	}
	
	@Override
	public Doctor saveDoctor(Doctor doctor) {
		Doctor dbDoctor = doctorDao.save(doctor);
		return dbDoctor;
	}
}
