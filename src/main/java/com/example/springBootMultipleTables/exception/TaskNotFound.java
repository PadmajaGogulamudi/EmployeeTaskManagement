package com.example.springBootMultipleTables.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class TaskNotFound extends RuntimeException {
	public TaskNotFound(String msg) {
		super(msg);
	}

}
