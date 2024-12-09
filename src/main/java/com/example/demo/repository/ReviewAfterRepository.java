package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ReviewAfter;

//JPA용 Repository
@Repository
public interface ReviewAfterRepository extends JpaRepository<ReviewAfter, Integer> {


	long countByContentContaining(String value);

	Page<ReviewAfter> findByContentContaining(String value, Pageable pageable);

}