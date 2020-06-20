package com.library.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.User;
import com.library.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
		User u = new User();
		u.setFirstName("Shaleen");
		u.setLastName(String.valueOf(userRepository.getAllUsers()));
		List<User> list = new ArrayList<>();
		list.add(u);

		return list;
	}
}
