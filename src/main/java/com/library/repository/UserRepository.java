package com.library.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int getAllUsers() {
		return jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
	}
}
