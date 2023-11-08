package com.brocode.appointmentbookingapp.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.brocode.appointmentbookingapp.entity.Appointment;
import com.brocode.appointmentbookingapp.entity.AppointmentId;
import com.brocode.appointmentbookingapp.entity.Department;
import com.brocode.appointmentbookingapp.entity.Doctor;
import com.brocode.appointmentbookingapp.entity.User;
import com.brocode.appointmentbookingapp.security.AppConfig;
import com.brocode.appointmentbookingapp.service.AppointmentService;
import com.brocode.appointmentbookingapp.service.DepartmentService;
import com.brocode.appointmentbookingapp.service.DoctorService;
import com.brocode.appointmentbookingapp.service.UserRoleService;
import com.brocode.appointmentbookingapp.service.UserService;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/appointmentBookings")
public class AppController {
	
	private UserRoleService userRoleService;
	private UserService userService;
	private DepartmentService departmentService;
	private DoctorService doctorService;
	private AppointmentService appointmentService;
	
	private AppConfig appConfig;
	
	AppController(UserRoleService userRoleService,
				  UserService userService,
				  DepartmentService departmentService,
				  DoctorService doctorService, AppointmentService appointmentService,
				  AppConfig appConfig){
		this.userRoleService = userRoleService;
		this.userService = userService;
		this.departmentService = departmentService;
		this.doctorService = doctorService;
		this.appointmentService = appointmentService;
		this.appConfig = appConfig;
	}
	
	@PostConstruct
	public void createData() {
		userRoleService.createAuthorities();
		userService.createAdmin();
		departmentService.createDepartments();
		doctorService.createDoctors();
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String registerUser(User user,
			HttpSession session, Model m) {
		System.out.println(user);
		if(session.getAttribute("errorUser")==null) {
			m.addAttribute("errorUser", user);
			return "register";
		}
		m.addAttribute("errorUser",session.getAttribute("errorUser"));
		return "register";
	}
	
	@PostMapping("/signedUp")
	public String newUser(@ModelAttribute @Valid User user, BindingResult result,
						  HttpSession session,
						  Model model) {
		session.setAttribute("errorUser", user);
		if (result.hasErrors()) {
			return "redirect:register";
		}
		try {
			userService.createUser(user);
			return "signedup";
		}
		catch (EntityExistsException exception) {
			
			return "redirect:register?error="+ exception.getMessage();
		}
	}

	@GetMapping("/")
	public String home(Model model) {
		List<Department> departments = departmentService.getAllDepartments();
		List<User> users = userService.getAllUsers();
		List<Doctor> doctors = doctorService.getAlldoctors();
		model.addAttribute("departments", departments);
		model.addAttribute("users", users);
		model.addAttribute("doctors", doctors);
		return "home-page";
	}
	
	@GetMapping("/departments/{dName}")
	public String getDepartment(@PathVariable String dName, Model model,
			HttpSession session) {
		Department department = departmentService.getDepartmentById(dName);
		session.setAttribute("selectedDepartment", department);
		
		model.addAttribute("department", department);
		return "department";
	}

	@GetMapping("/doctors/{id}")
	public String getDoctor(@PathVariable int id, HttpSession session, Model model) {
		Doctor myDoctor = doctorService.getDoctorById(id);
		session.setAttribute("selectedDoctor", myDoctor);
		
		LocalDate date = LocalDate.now();
		
		List<Appointment> list =  new ArrayList<>();
		for(int i = 0; i < 7; i++) {
			LocalDate date2 = LocalDate.parse(date.toString()).plusDays(1);
			AppointmentId appointmentId = new AppointmentId();
			appointmentId.setDate(date2.toString());
			appointmentId.setDoctor(myDoctor);
			Appointment appointment = appointmentService.find(appointmentId);
			if (appointment == null) {
				Appointment appointmentNew = new Appointment();
				appointmentNew.setId(appointmentId);
				Appointment dbAppointment = appointmentService.createAppointment(appointmentNew);
				list.add(dbAppointment);
			}
			else {
				list.add(appointment);
			}
			date = date2;
		}
		session.setAttribute("selectedList", list);
		return "select-date";
	}
	
	@GetMapping("/appointments/{id}/{date}")
	public String getAppointment(@PathVariable("date") String date, @PathVariable("id") int id,
					HttpSession session) {
		AppointmentId appointmentId = new AppointmentId();
		appointmentId.setDoctor(doctorService.getDoctorById(id));
		appointmentId.setDate(date);
		Appointment appointment = appointmentService.find(appointmentId);
		session.setAttribute("appointment", appointment);
		session.setAttribute("appointmentId", appointmentId);
		return "select-time";
	}
	
	@GetMapping("/book")
	public String bookAppointment(@RequestParam int time, HttpSession session,
			 Authentication authentication,Model model) {
		Doctor doctor = (Doctor) session.getAttribute("selectedDoctor");
		Department department = (Department) session.getAttribute("selectedDepartment");
		AppointmentId appointmentId = (AppointmentId) session.getAttribute("appointmentId");

		appointmentId.setDoctor(doctor);
		
		Appointment appointment = appointmentService.find(appointmentId);
		String phNo = authentication.getName();
		User user = userService.getUser(phNo);
		String dName = department.getDName();
		Appointment dbAppointment = appConfig.setHour(time, appointment, phNo);
		List<Appointment> appointments = user.getAppointments();
		appointments.add(dbAppointment);
		user.setAppointments(appointments);
		User dbUser = userService.updateUser(user);
		model.addAttribute("user", dbUser);
		model.addAttribute("department", dName);
		model.addAttribute("appointments", appointments);
		model.addAttribute("doctor", appointmentId.getDoctor().getFirstName() + " " +appointmentId.getDoctor().getLastName());
		return "redirect:/appointmentBookings/appointments";
	}
	
	@GetMapping("/appointments")
	public String myAppointments(Authentication authentication, Model model) {
		String name = authentication.getName();
		User user = userService.getUser(name);
		
		List<Appointment> appointments = user.getAppointments();
		
		model.addAttribute("user", user);
		model.addAttribute("appointments", appointments);
		return "appointments";
	}
	@GetMapping("/profile")
	public String profileDetails(Authentication authentication, Model model) {
		String name = authentication.getName();
		System.out.println(name);
		User user = userService.getUser(name);
		model.addAttribute("user", user);
		return "profile";
	}
}
