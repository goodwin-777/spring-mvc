package com.brocode.appointmentbookingapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.brocode.appointmentbookingapp.entity.User;

public interface UserDao extends JpaRepository<User, String>{
}
