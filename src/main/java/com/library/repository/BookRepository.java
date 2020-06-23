package com.library.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.library.dto.Book;
import com.library.dto.TypeValueId;

@Repository
public class BookRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static String SELECT_AUTHOR = "select author_id as id, author_name as value from author;";

	private static String SELECT_CATEGORY = "select category_id as id, category_name as value from category";

	private static String INSERT_INTO_BOOK = "INSERT INTO book(BOOK_NAME,CATEGORY_ID, AUTHOR_ID) " + "VALUES(?,?,? )";

	private static String SELECT_BOOK = "select b.book_id, b.book_name, b.author_id, b.category_id, "
			+ "a.author_name, c.category_name "
			+ "from book b "
			+ "left outer join author a "
			+ "on a.author_id = b.author_id "
			+ "left outer join category c "
			+ "on c.category_id = b.category_id";

	public List<TypeValueId> getAuthors() {
		return jdbcTemplate.query(BookRepository.SELECT_AUTHOR, new TypeValueRowMapper());
	}

	public List<TypeValueId> getCategories() {
		return jdbcTemplate.query(BookRepository.SELECT_CATEGORY, new TypeValueRowMapper());
	}

	public List<Book> getBooks() {
		return jdbcTemplate.query(BookRepository.SELECT_BOOK, new BookRowMapper());
	}

	public int addBook(Book book) {
		return jdbcTemplate.update(BookRepository.INSERT_INTO_BOOK,
				new Object[] { book.getBookName(), book.getCategoryId(), book.getAuthorId() });
	}

}


class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
		Book book = new Book();
		book.setBookId(rs.getInt(1));
		book.setBookName(rs.getString(2));
		book.setAuthorId(rs.getInt(3));
		book.setCategoryId(rs.getInt(4));
		book.setAuthorName(rs.getString(5));
		book.setCategoryName(rs.getString(6));
		return book;
	}

}

class TypeValueRowMapper implements RowMapper<TypeValueId> {

	@Override
	public TypeValueId mapRow(ResultSet rs, int rowNum) throws SQLException {
		TypeValueId typeValue = new TypeValueId();
		typeValue.setId(rs.getInt("id"));
		typeValue.setValue(rs.getString("value"));
		return typeValue;
	}

}
