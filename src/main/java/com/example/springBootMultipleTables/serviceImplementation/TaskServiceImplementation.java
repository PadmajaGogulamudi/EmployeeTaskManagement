package com.example.springBootMultipleTables.serviceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springBootMultipleTables.entity.Task;
import com.example.springBootMultipleTables.entity.Users;
import com.example.springBootMultipleTables.exception.UserNotFound;
import com.example.springBootMultipleTables.paylod.TaskDto;
import com.example.springBootMultipleTables.repo.TaskRepository;
import com.example.springBootMultipleTables.repo.UserRepository;
import com.example.springBootMultipleTables.service.TaskService;

@Service
public class TaskServiceImplementation implements TaskService {
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userrepo;
	@Autowired
	private TaskRepository taskRepo;

	@Override
	public TaskDto saveTask(long userId, TaskDto taskDto) {
		Users user = userrepo.findById(userId).orElseThrow(() -> new UserNotFound("user not found"));
		// System.err.println(taskDto.getTaskName());

		Task task = modelMapper.map(taskDto, Task.class);// setting user to task and moving to DB
		task.setUser(user);
		Task savedTask = taskRepo.save(task);
		TaskDto savedDto = modelMapper.map(savedTask, TaskDto.class);
		return savedDto;
	}

	@Override
	public List<TaskDto> getAllTasks(long userId) {
		Users user = userrepo.findById(userId).orElseThrow(() -> new UserNotFound("user not found"));
		List<Task> tasks = taskRepo.findByUserId(userId);
//		List<TaskDto> taskDtoList=new ArrayList<>();
//		for(Task task:tasks) {
//			taskDtoList.add(modelMapper.map(task, TaskDto.class));
//		}

		// return taskDtoList;
		return tasks.stream().map(task -> modelMapper.map(task, TaskDto.class)).collect(Collectors.toList());
	}

}
