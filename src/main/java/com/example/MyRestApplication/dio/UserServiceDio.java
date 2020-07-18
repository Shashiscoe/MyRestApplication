package com.example.MyRestApplication.dio;

import java.util.List;

import com.example.MyRestApplication.entity.User;

public interface UserServiceDio {

	public int saveUser(User user);

	public List<User> getUsers();

	public User getUser(int id);

}
