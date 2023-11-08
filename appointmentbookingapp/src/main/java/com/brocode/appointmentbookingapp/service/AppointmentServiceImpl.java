package com.brocode.appointmentbookingapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.brocode.appointmentbookingapp.dao.AppointmentDao;
import com.brocode.appointmentbookingapp.entity.Appointment;
import com.brocode.appointmentbookingapp.entity.AppointmentId;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	private AppointmentDao appointmentDao;
	
	public AppointmentServiceImpl(AppointmentDao appointmentDao) {
		this.appointmentDao = appointmentDao;
	}
	
	@Override
	public Appointment createAppointment(Appointment appointment) {
		Appointment dbAppointment = appointmentDao.save(appointment);
		return dbAppointment;
	}

	@Override
	public Appointment find(AppointmentId id) {
		Optional<Appointment> optional = appointmentDao.findById(id);
		Appointment appointment = null;
		
		if (optional.isPresent()) {
			appointment = optional.get();
			return appointment;
		}
		return appointment;
	}

}
