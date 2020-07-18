package com.example.MyRestApplication.service;

import java.util.List;

import com.example.MyRestApplication.dto.UserDto;

public interface UserService {
	
	public int saveUser(UserDto user);

	public List<UserDto> getUsers();

}
