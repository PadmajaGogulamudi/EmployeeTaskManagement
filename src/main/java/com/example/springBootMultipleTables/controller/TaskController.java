package com.example.springBootMultipleTables.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springBootMultipleTables.entity.Task;
import com.example.springBootMultipleTables.paylod.TaskDto;
import com.example.springBootMultipleTables.service.TaskService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
public class TaskController {
	@Autowired
	private TaskService taskService;
	
	
    //save task
	@PostMapping("/{userId}/tasks")
	public ResponseEntity<TaskDto> saveTask(@PathVariable(name="userId") long userId,@Valid @RequestBody TaskDto taskDto){
		
		
		return new ResponseEntity<>(taskService.saveTask(userId, taskDto),HttpStatus.CREATED);
	}
	
	
	
	
	// get all tasks
	
	
	@GetMapping("/{userId}/findAllTasks")
	public List<TaskDto> getAllTasks(@PathVariable("userId") long userId){
		return taskService.getAllTasks(userId);
	}
	// get individual task
	// delete individual task
}
