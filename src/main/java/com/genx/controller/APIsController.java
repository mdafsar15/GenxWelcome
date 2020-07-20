package com.genx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genx.model.User;
import com.genx.service.UserDetailsServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class APIsController {
	
	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@GetMapping("/api/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public String userAccess() {
		return ">>> User Contents!";
	}

	@GetMapping("/api/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return ">>> Admin Contents";
	}
	
	@GetMapping(value = "/getAllNotes")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getAllUser() {

		List<User> users = userDetailsServiceImpl.getAllUser();
		return users;
	}
}
