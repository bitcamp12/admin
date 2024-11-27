package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookDAO bookDAO;
	
	@Autowired
	private BookRepository bookRepository;

	public List<Book> getBookList() {
		return bookRepository.findAll();
	}
	
}
