package com.example.springBootMultipleTables.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springBootMultipleTables.entity.Users;

public interface UserRepository extends JpaRepository<Users,Long>{

}
