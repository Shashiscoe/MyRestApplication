package com.example.MyRestApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MyRestApplication.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
