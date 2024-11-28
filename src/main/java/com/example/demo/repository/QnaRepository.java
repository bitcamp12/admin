package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Qna;

//JPA용 Repository
@Repository
public interface QnaRepository extends JpaRepository<Qna, Integer> {
 // 필요한 경우, 커스텀 쿼리 메서드 선언
	
	@Query("SELECT q FROM Qna q LEFT JOIN q.reply r")
	List<Qna> findAllWithReply();

}