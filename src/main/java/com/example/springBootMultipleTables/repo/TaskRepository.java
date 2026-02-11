package com.example.springBootMultipleTables.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootMultipleTables.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
	public List<Task> findByUserId(long userId);//findAllByUserId or findByUserId which one is correct
}
