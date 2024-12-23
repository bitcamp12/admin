package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Notice;

//JPA용 Repository
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {

	long countByTitleContaining(String value);
 // 필요한 경우, 커스텀 쿼리 메서드 선언

	Page<Notice> findByTitleContaining(String value, Pageable pageable);

}