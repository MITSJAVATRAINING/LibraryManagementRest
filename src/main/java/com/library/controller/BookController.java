package com.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.Book;
import com.library.dto.LibraryResponse;
import com.library.dto.TypeValueId;
import com.library.services.BookService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/library/book")
public class BookController {

	@Autowired
	BookService bookService;

	@RequestMapping(method = RequestMethod.GET, value = "/authors")
	@ResponseBody
	public LibraryResponse<TypeValueId> getAuthors() {
		LibraryResponse<TypeValueId> response = new LibraryResponse<TypeValueId>();

		try {
			List<TypeValueId> authors = bookService.getAuthors();
			response.setList(authors);
			response.setStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode(500);
			response.setStatusMsg(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/categories")
	@ResponseBody
	public LibraryResponse<TypeValueId> getCategories() {
		LibraryResponse<TypeValueId> response = new LibraryResponse<TypeValueId>();

		try {
			List<TypeValueId> category = bookService.getCategories();
			response.setList(category);
			response.setStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode(500);
			response.setStatusMsg(e.getMessage());
		}
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/addbook")
	@ResponseBody
	public LibraryResponse<?> addBook(@RequestBody Book book) {

		LibraryResponse<?> response = new LibraryResponse<Book>();

		try {
			bookService.addBook(book);
			response.setStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode(500);
			response.setStatusMsg(e.getMessage());
		}

		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/books")
	@ResponseBody
	public LibraryResponse<Book> getBooks() {
		LibraryResponse<Book> response = new LibraryResponse<Book>();

		try {
			List<Book> books = bookService.getBooks();
			response.setList(books);
			response.setStatusCode(200);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode(500);
			response.setStatusMsg(e.getMessage());
		}
		return response;
	}


}
