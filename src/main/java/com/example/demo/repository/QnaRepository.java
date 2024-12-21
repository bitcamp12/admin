package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Qna;

//JPA용 Repository
@Repository
public interface QnaRepository extends JpaRepository<Qna, Integer> {
 // 필요한 경우, 커스텀 쿼리 메서드 선언
	
	  // LEFT JOIN을 사용한 Qna와 Reply 페이징
    @Query("SELECT q FROM Qna q LEFT JOIN q.reply r")
    Page<Qna> findAllWithReply(Pageable pageable);

	long countByTitleContaining(String value);

	Page<Qna> findByTitleContaining(String value, Pageable pageable);

	@Query("SELECT q FROM Qna q LEFT JOIN q.reply r where q.title like %:value% ")
	Page<Qna> findAllWithReplyKeyword(Pageable pageable, @Param(value = "value") String value);

}