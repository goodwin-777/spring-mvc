package com.brocode.appointmentbookingapp.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	UserDetailsManager detailsManager(DataSource dataSource) {
		JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
		manager.setUsersByUsernameQuery("select phone_number, password,"
				+ " status from user where phone_number = ?");
		manager.setAuthoritiesByUsernameQuery("select phone_number, role from userrole "
				+ "where phone_number = ?");
		return manager;
	}
	
//	@Bean
//	DaoAuthenticationProvider authenticationProvider(UserService userService) {
//		DaoAuthenticationProvider authentication = new DaoAuthenticationProvider();
//		authentication.setUserDetailsService(userService);
//		authentication.setPasswordEncoder(passwordEncoder());
//		return authentication;
//	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.authorizeHttpRequests(customizer->{customizer
			.requestMatchers("/appointmentBookings/register").permitAll()
			.requestMatchers("/appointmentBookings/signedUp").permitAll()
			.requestMatchers("/appointmentBookings/registerSuccessful").permitAll()
			.requestMatchers("/css/style.css").permitAll()
			.anyRequest().authenticated();
		
		})
		.formLogin(customizer->customizer.loginPage("/appointmentBookings/login")
				.defaultSuccessUrl("/appointmentBookings/",true)
				.loginProcessingUrl("/toSpring").permitAll())
		.logout(customizer->customizer.logoutUrl("/logout"))
		
		.exceptionHandling(customizer->customizer
				.accessDeniedPage("/accessDeniedPage"));
		
		return security.build();
	}
}
