package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Member;

//JPA용 Repository
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	Page<Book> findByBookSeq(int int1, Pageable pageable);
	
	@Query("SELECT b FROM Book b WHERE b.seatNum IS NOT NULL")
	List<Book> AllFindBookList();

	long countByBookSeq(int int1);

}