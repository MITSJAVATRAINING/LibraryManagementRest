package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.LibraryResponse;
import com.library.dto.User;
import com.library.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/library/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/users")
	@ResponseBody
	public LibraryResponse<User> getUsers() {
		LibraryResponse<User> response = new LibraryResponse<User>();

		try {
			List<User> users = userService.getUsers();
			response.setList(users);
			response.setStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode(500);
			response.setStatusMsg(e.getMessage());
		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/adduser")
	@ResponseBody
	public LibraryResponse<?> addUser(@RequestBody User user) {

		LibraryResponse<?> response = new LibraryResponse<User>();

		try {
			userService.addUser(user);
			response.setStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode(500);
			response.setStatusMsg(e.getMessage());
		}

		return response;
	}
}
