package com.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.User;
import com.library.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getUsers() {
		return userRepository.getUsers();
	}
	
	public int addUser(User user) {
		return userRepository.addUser(user);
	}
}
