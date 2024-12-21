package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Book;

//JPA용 Repository
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

	Page<Book> findByBookSeq(int int1, Pageable pageable);

	long countByBookSeq(int int1);

}