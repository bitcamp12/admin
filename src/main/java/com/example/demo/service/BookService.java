package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public void bookDelete(int bookSeqInt) {
		bookDAO.bookDelete(bookSeqInt);
	}


	 // 전체 Book 개수 반환
    public long getTotalCount() {
        return bookRepository.count();
    }

    // 제목이나 설명에 키워드가 포함된 Book의 개수 반환
    public long getTotalCountKeyword(String value) {
        return bookRepository.countByBookSeq(Integer.parseInt(value));
    }

    // 페이징 처리된 Book 목록 (검색어 없이)
    public List<Book> getBookPaging(Pageable pageable) {
        Page<Book> pageResult = bookRepository.findAll(pageable); // 모든 Book 데이터를 페이징 처리
        return pageResult.getContent(); // 페이징된 Book 목록 반환
    }

    // 제목이나 설명에 키워드가 포함된 Book 목록 (검색어 기준)
    public List<Book> getBookPagingKeyword(Pageable pageable, String value) {
        Page<Book> pageResult = bookRepository.findByBookSeq(Integer.parseInt(value), pageable); // 키워드를 기준으로 페이징 처리
        return pageResult.getContent(); // 페이징된 Book 목록 반환
    }
	
}
