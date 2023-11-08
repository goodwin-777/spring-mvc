package com.brocode.appointmentbookingapp.entity;

import java.util.List;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Appointment {
	@EmbeddedId
	private AppointmentId id;
	
	private String hour9;
	private String hour10;
	private String hour11;
	
	@ManyToMany(mappedBy = "appointments", fetch = FetchType.EAGER)
	private List<User> users;

	@Override
	public String toString() {
		return "Appointment [id=" + id.getDate() + " " +id.getDoctor().getFirstName() + ", hour9=" + hour9 + ", hour10=" + hour10 + ", hour11=" + hour11 + ", users=" + "]";
	}
	
	
	
}
