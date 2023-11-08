package com.brocode.appointmentbookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brocode.appointmentbookingapp.entity.Appointment;
import com.brocode.appointmentbookingapp.entity.AppointmentId;


public interface AppointmentDao extends JpaRepository<Appointment, AppointmentId>{

}
