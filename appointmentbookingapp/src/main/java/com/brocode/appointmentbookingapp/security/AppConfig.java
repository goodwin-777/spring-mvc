package com.brocode.appointmentbookingapp.security;

import org.springframework.context.annotation.Configuration;

import com.brocode.appointmentbookingapp.entity.Appointment;

@Configuration
public class AppConfig {

	public Appointment setHour(int time, Appointment appointment, String phNo) {
		switch (time) {
		case 9:
			appointment.setHour9(phNo);
			break;
		case 10:
			appointment.setHour10(phNo);
			break;
		case 11:
			appointment.setHour11(phNo);
			break;

		default:
			break;
		}
		return appointment;
	}
}
