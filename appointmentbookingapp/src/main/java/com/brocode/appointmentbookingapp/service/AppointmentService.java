package com.brocode.appointmentbookingapp.service;

import com.brocode.appointmentbookingapp.entity.Appointment;
import com.brocode.appointmentbookingapp.entity.AppointmentId;

public interface AppointmentService {
	public Appointment createAppointment(Appointment appointment);

	public Appointment find(AppointmentId id);
	
}
