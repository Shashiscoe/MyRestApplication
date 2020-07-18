package com.example.MyRestApplication.dioimpl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.MyRestApplication.dio.UserServiceDio;
import com.example.MyRestApplication.entity.User;

@Repository
public class UserServiceDioImpl implements UserServiceDio {

	@Autowired
	private EntityManager entityManager;


	@Override
	public int saveUser(User user) {
		entityManager.persist(user);
		return user.getId();
	}
}
