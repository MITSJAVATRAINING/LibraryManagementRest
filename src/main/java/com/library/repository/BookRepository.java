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
import com.library.dto.UserBookMapping;

@Repository
public class BookRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static String SELECT_AUTHOR = "select author_id as id, author_name as value from author;";

	private static String SELECT_CATEGORY = "select category_id as id, category_name as value from category";

	private static String INSERT_INTO_BOOK = "INSERT INTO book(BOOK_NAME,CATEGORY_ID, AUTHOR_ID, TOTAL_BOOKS, AVAILABLE_BOOKS) "
			+ "VALUES(?,?,?,?,? )";

	private static String INSERT_INTO_BOOK_MAPPING = "INSERT INTO USERBOOKMAPPING(user_id, book_id, issue_Date) "
			+ "VALUES(?,?, CURRENT_TIMESTAMP )";
	
	private static String DELETE_BOOK_MAPPING = "DELETE FROM USERBOOKMAPPING where user_id = ? and book_id = ? ";

	private static String SELECT_BOOK = "select b.book_id, b.book_name, b.author_id, b.category_id, b.total_books, b.available_books, "
			+ "a.author_name, c.category_name " + "from book b " + "left outer join author a "
			+ "on a.author_id = b.author_id " + "left outer join category c " + "on c.category_id = b.category_id";

	private static String UPDATE_AVAILABLE_BOOKS = "update Book " + "set available_Books = available_Books + ? "
			+ "where book_id = ?";
	
	private static String UPDATE_ISSUED_BOOKS_TO_USER = "update user " + "set total_issued_book = total_issued_book + ? "
			+ "where user_id = ?";

	private static String SELECT_ISSUED_BOOK = "select um.id, um.user_Id, um.book_id, um.issue_Date, b.book_name "
			+ "from userbookmapping um " + "left outer join book b " + "on b.book_id = um.book_id";

	public List<TypeValueId> getAuthors() {
		return jdbcTemplate.query(BookRepository.SELECT_AUTHOR, new TypeValueRowMapper());
	}

	public List<TypeValueId> getCategories() {
		return jdbcTemplate.query(BookRepository.SELECT_CATEGORY, new TypeValueRowMapper());
	}

	public List<Book> getBooks() {
		return jdbcTemplate.query(BookRepository.SELECT_BOOK, new BookRowMapper());
	}

	public List<UserBookMapping> getIssuedBooks() {
		return jdbcTemplate.query(BookRepository.SELECT_ISSUED_BOOK, new IssuedBookRowMapper());
	}

	public int addBook(Book book) {
		return jdbcTemplate.update(BookRepository.INSERT_INTO_BOOK, new Object[] { book.getBookName(),
				book.getCategoryId(), book.getAuthorId(), book.getTotalBooks(), book.getTotalBooks() });
	}

	public int issueBook(UserBookMapping book) {
		jdbcTemplate.update(BookRepository.INSERT_INTO_BOOK_MAPPING,
				new Object[] { book.getUserId(), book.getBookId() });
		jdbcTemplate.update(BookRepository.UPDATE_ISSUED_BOOKS_TO_USER, new Object[] { 1, book.getUserId() });
		return jdbcTemplate.update(BookRepository.UPDATE_AVAILABLE_BOOKS, new Object[] { -1, book.getBookId() });
	}
	
	public int returnBook(UserBookMapping book) {
		jdbcTemplate.update(BookRepository.DELETE_BOOK_MAPPING,
				new Object[] { book.getUserId(), book.getBookId() });
		jdbcTemplate.update(BookRepository.UPDATE_ISSUED_BOOKS_TO_USER, new Object[] { -1, book.getUserId() });
		return jdbcTemplate.update(BookRepository.UPDATE_AVAILABLE_BOOKS, new Object[] { 1, book.getBookId() });
	}

}

class IssuedBookRowMapper implements RowMapper<UserBookMapping> {

	@Override
	public UserBookMapping mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserBookMapping book = new UserBookMapping();
		book.setMappingId(rs.getInt(1));
		book.setUserId(rs.getInt(2));
		book.setBookId(rs.getInt(3));
		book.setIssueDate(rs.getDate(4));
		book.setBookName(rs.getString(5));
		return book;
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
		book.setTotalBooks(rs.getInt(5));
		book.setAvailableBooks(rs.getInt(6));
		book.setAuthorName(rs.getString(7));
		book.setCategoryName(rs.getString(8));
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
