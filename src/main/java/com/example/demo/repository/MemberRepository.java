package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Member;
import com.example.demo.entity.Member.Role;

//JPA용 Repository
@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

	@Query("select m from Member m where m.id = :id and m.password = :password and m.role ='SELLER'")
	Member findByLoginIdAndPassword(@Param("id")String id, @Param("password")String password);
 // 필요한 경우, 커스텀 쿼리 메서드 선언
	
	@Query("SELECT m FROM Member m WHERE m.role = 'SELLER'")
	List<Member> findAllSellerList();

	long countByRole(Role seller);

	Page<Member> findByRole(Role seller, Pageable pageable);

	Page<Member> findByRoleAndNameContaining(Role seller, String value, Pageable pageable);

	long countByRoleAndNameContaining(Role seller, String value);

}