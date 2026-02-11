package com.example.springBootMultipleTables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootMultipleTables.paylod.UserDto;
import com.example.springBootMultipleTables.service.UserService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
	@Autowired
	private UserService userService;
	
	
	//store user into DB
	@PostMapping("/userRegister")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
	
		return new ResponseEntity<>(userService.createUser(user),HttpStatus.CREATED);
	}

}
