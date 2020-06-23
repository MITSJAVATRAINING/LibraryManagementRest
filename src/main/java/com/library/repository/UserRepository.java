package com.library.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.library.dto.User;

@Repository
public class UserRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static String INSERT_INTO_USER = "INSERT INTO USER(FIRST_NAME,LAST_NAME,ADDRESS,PHONE,EMAIL) "
			+ "VALUES(?,?,?,?,? )";
	
	private static String SELECT_USER = "SELECT * from USER";

	public List<User> getUsers() {
		return jdbcTemplate.query(UserRepository.SELECT_USER, new UserRowMapper());
	}

	public int addUser(User user) {
		return jdbcTemplate.update(UserRepository.INSERT_INTO_USER, new Object[] { user.getFirstName(),
				user.getLastName(), user.getAddress(), user.getPhone(), user.getEmail() });
	}
}

class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getInt("USER_ID"));
		user.setFirstName(rs.getString("FIRST_NAME"));
		user.setLastName(rs.getString("LAST_NAME"));
		user.setPhone(rs.getString("PHONE"));
		user.setEmail(rs.getString("EMAIL"));
		user.setAddress(rs.getString("ADDRESS"));
		return user;
	}
	
}