package com.example.springBootMultipleTables.service;

import java.util.List;

import com.example.springBootMultipleTables.paylod.TaskDto;

public interface TaskService {
	
	public TaskDto saveTask(long userId,TaskDto taskDto);
	public List<TaskDto> getAllTasks(long userId);
	public TaskDto getTask(long taskId,long userId);
	public void deleteTask(long taskId,long userId);

} 
