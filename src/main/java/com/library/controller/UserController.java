package com.library.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.library.dto.User;
import com.library.services.UserService;

@RestController
@RequestMapping("/library")
public class UserController {

	@Autowired
	UserService userService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	@ResponseBody
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
}
