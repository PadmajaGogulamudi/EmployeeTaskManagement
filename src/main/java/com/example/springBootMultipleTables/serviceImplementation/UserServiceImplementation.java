package com.example.springBootMultipleTables.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.springBootMultipleTables.entity.Users;
import com.example.springBootMultipleTables.paylod.UserDto;
import com.example.springBootMultipleTables.repo.UserRepository;
import com.example.springBootMultipleTables.service.UserService;
@Service
public class UserServiceImplementation implements UserService {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		//before adding user we have to encode the password
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		Users saveduser=userRepo.save(user_Dto_ToEntity(userDto));
		return user_entity_to_Dto(saveduser);
	}
	private Users user_Dto_ToEntity(UserDto userDto) {
		Users user=new Users();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		
		return user;
	}
	private UserDto user_entity_to_Dto(Users saveduser) {
		UserDto user=new UserDto();
		user.setId(saveduser.getId());
		user.setName(saveduser.getName());
		user.setEmail(saveduser.getEmail());
		user.setPassword(saveduser.getPassword());
		return user;
		
	}

}
