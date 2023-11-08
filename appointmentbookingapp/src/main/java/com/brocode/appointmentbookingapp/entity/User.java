package com.brocode.appointmentbookingapp.entity;

import java.util.Collection;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class User {
	@Id
	@Column(name = "phone_number")
	private String phNo;
	
	@Column(name = "first_name")
	@NotBlank(message = "*")
	private String firstName;
	
	@Column(name = "last_name")
	@NotBlank(message = "*")
	private String lastName;
	
	@Column(columnDefinition = "BOOLEAN")
	private boolean status;
	
	@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^*-]).{8,}$",
			 message = "password must contain at least eight characters, at least one number and both lower and uppercase letters and special characters")
	private String password;
	private String email;
	private String address;
	
	@Pattern(regexp = "^[1-9][0-9]{5}$", message = "Enter a valid pincode")
	private String pincode;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.MERGE)
	private List<Appointment> appointments;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Cascade(CascadeType.ALL)
	@JoinTable(name = "userrole",
				joinColumns = @JoinColumn(name = "phone_number"),
				inverseJoinColumns = @JoinColumn(name = "role"))
	private Collection<UserRole> role;
}
