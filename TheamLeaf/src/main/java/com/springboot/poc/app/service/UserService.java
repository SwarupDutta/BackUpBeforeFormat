package com.springboot.poc.app.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.springboot.poc.app.entities.User;

@Service
public class UserService {
	private List<User> userList = new ArrayList<>();
	public List<User> getAllUsers(){
		return userList;
	}
	public void addUser(User user) {
		userList.add(user);
	}
} 