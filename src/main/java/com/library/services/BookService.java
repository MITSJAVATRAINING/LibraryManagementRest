package com.library.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.Book;
import com.library.dto.TypeValueId;
import com.library.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public List<TypeValueId> getAuthors() {
		return bookRepository.getAuthors();
	}

	public List<TypeValueId> getCategories() {
		return bookRepository.getCategories();
	}

	public int addBook(Book book) {
		return bookRepository.addBook(book);
	}

	public List<Book> getBooks() {
		return bookRepository.getBooks();
	}
}
