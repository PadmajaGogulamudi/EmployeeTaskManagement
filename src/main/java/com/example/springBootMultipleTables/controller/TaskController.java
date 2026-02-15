package com.example.springBootMultipleTables.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	// get all tasks

	@GetMapping("/{userId}/findAllTasks")
	public ResponseEntity<List<TaskDto>> getAllTasks(@PathVariable("userId") long userId) {
		return new ResponseEntity<>(taskService.getAllTasks(userId), HttpStatus.OK);
	}

	// get individual task
	@GetMapping("/taskId:{taskId}/userId:{userId}")
	public ResponseEntity<TaskDto> getTask(@PathVariable("taskId") long taskId, @PathVariable("userId") long userId) {
		return new ResponseEntity<>(taskService.getTask(taskId, userId), HttpStatus.OK);
	}

	// delete individual task
	@DeleteMapping("/taskId:{taskId}/userId:{userId}/deleteTask")
	public ResponseEntity<String> deleteTaskById(@PathVariable("taskId") long taskId,
			@PathVariable("userId") long userId) {
		taskService.deleteTask(taskId, userId);
		return new ResponseEntity<>("Task deleted successfully", HttpStatus.OK);
	}
	/*
	 * spring security ni use chesi multiple ways lo api's ni secure cheskovachu 
	 * 1. form based authentication
	 * 2.Basic Authentication
	 * 3. JWT Authentication
	 * 4. QAuth Authentication
	 * 
	 * 
	 * */

}
