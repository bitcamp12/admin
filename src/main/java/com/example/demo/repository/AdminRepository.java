package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;

//JPA용 Repository
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	// Optional 사용한 이유 -> 해당 조건에 맞는 Admin 객체가 없을 수도 있어서..
	@Query("select a from Admin a where a.id = :id")
	Optional<Admin> findById(@Param("id") String id);

//	@Query("select a from Admin a where a.id = :id and a.password = :password")
//	Admin findByLoginIdAndPassword(@Param("id") String id, @Param("password") String password);


}
