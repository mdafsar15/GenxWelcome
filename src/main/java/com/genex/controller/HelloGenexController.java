package com.genex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.genex.dto.LoginDto;
import com.genex.response.Response;
import com.genex.service.UserService;

@RestController
public class HelloGenexController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/hello")
	public String firstPage(@RequestHeader String token) {
	return "Welcome To Genx info technologies Pvt. Ltd.";
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDto loginDTO) {
		Response response = userService.login(loginDTO);
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
