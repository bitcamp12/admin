package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

//JPA용 Repository
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	//ID로 관리자 찾기
	Optional<Admin> findById(String id);
	
	//ID와 이름으로 관리자 찾기
	Optional<Admin> findByIdAndName(String id, String name);
}
