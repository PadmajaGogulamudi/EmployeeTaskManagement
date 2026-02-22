package com.example.springBootMultipleTables.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootMultipleTables.paylod.LoginDto;
import com.example.springBootMultipleTables.paylod.UserDto;
import com.example.springBootMultipleTables.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserService userService;
	@Autowired
	private AuthenticationManager authenticationManager;
    // store user into DB
	@PostMapping("/userRegister")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		
		return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
	}

	@PostMapping("/login") 
	public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseEntity<>("user loged in successfully", HttpStatus.OK);
	}
}
