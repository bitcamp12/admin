package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Admin;
//JPA용 Repository
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query("select a from Admin a where a.id = :id and a.password = :password")
	Admin findByLoginIdAndPassword(@Param("id")String id, @Param("password")String password);
 // 필요한 경우, 커스텀 쿼리 메서드 선언

}
