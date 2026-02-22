package com.example.springBootMultipleTables.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootMultipleTables.paylod.TaskDto;
import com.example.springBootMultipleTables.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	// save task
	@PostMapping("/{userId}/addTasksToUser")
	public ResponseEntity<TaskDto> saveTask(@PathVariable(name = "userId") long userId,
			@Valid @RequestBody TaskDto taskDto) {

		return new ResponseEntity<>(taskService.saveTask(userId, taskDto), HttpStatus.CREATED);
	}
	@PreAuthorize(value="ROLE_ADMIN")
	// get all tasks

	@GetMapping("/{userId}/findAllTasks")
	public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable("userId") long userId) {
		return new ResponseEntity<>(taskService.getAllTasks(userId), HttpStatus.OK);
	}
	
	@PreAuthorize(value="ROLE_ADMIN")
	// get individual task
	@GetMapping("/taskId:{taskId}/userId:{userId}")
	public ResponseEntity<TaskDto> getTask(@PathVariable("taskId") long taskId, @PathVariable("userId") long userId) {
		return new ResponseEntity<>(taskService.getTask(taskId, userId), HttpStatus.OK);
	}
	
	
	@PreAuthorize(value="ROLE_ADMIN")

	// delete individual task
	
	@DeleteMapping("/taskId:{taskId}/userId:{userId}/deleteTask")
	public ResponseEntity<String> deleteTaskById(@PathVariable("taskId") long taskId,
			@PathVariable("userId") long userId) {
		taskService.deleteTask(taskId, userId);
		return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
	}
	/*
	 * spring security ni use chesi multiple ways lo api's ni secure cheskovachu 1.
	 * form based authentication 2.Basic Authentication 3. JWT Authentication 4.
	 * QAuth Authentication JWT Authentication -> 3 parts => 1. verification of user
	 * credentials 2. generate jwt for authenticated user 3. handling API calls
	 * based on valid JWT ================================== 1.verification of user
	 * credentials: -------------------------------------
	 * 
	 * @Bean
	 * 
	 * @AuthentiationManager
	 * 
	 * @Bean
	 * 
	 * @secutityFilterChain
	 * -------------------------------- 
	 * 1.adding security
	 * dependency(default form based security enable avvudhi )
	 * 2. run as spring boot application 
	 * 3.security password will generate hashcode value is the key ->go
	 * to browser and open the URL then it ask login then only we can access out
	 * application  (form based authentication).
	 * ------------------------------------
	 * implement JWT  for this we have to validate the user for that 
	 * we are going to use those two bean  @annotations 
	 * we are going to create new package for security configuration 
	 * create two beans and then go an create ///router///
	 * loginDTo create 
	 * -------------------------------------------
	 * UserDetailsService->(loadUserByUsername method is unimplemented one )
	 * Collection<? extends GrantedAuthority> ->SimpleGrantedAuthority
	 * ------------------------------------------------
	 * @PreAuthorize(value="ROLE_ADMIN")
	 * 
	 * ----------------------------------------------------
	 * jwt is encoded string format it has 3 parts
	 * 1.header(algorithem related things used  to encode the password)
	 * 2.payload(dhenikosam generate chesamo dhaniki sambhandhinchina details will store)(whom the token refers to)
	 * 3.signature
	 * to generate jwt we have to add jwt dependency
	 * --------------------------------------------------------
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

}
