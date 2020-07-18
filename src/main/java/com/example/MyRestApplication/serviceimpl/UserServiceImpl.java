package com.example.MyRestApplication.serviceimpl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyRestApplication.dio.UserServiceDio;
import com.example.MyRestApplication.dto.UserDto;
import com.example.MyRestApplication.entity.User;
import com.example.MyRestApplication.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserServiceDio userServiceDio;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public int saveUser(UserDto userDto) {
		return userServiceDio.saveUser(modelMapper.map(userDto, User.class));
	}

}
