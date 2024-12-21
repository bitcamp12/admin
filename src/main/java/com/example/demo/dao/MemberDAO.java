package com.example.demo.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.MemberDTO;

@Mapper
public interface MemberDAO {
    
    // 회원 가입
    public void signUp(MemberDTO memberDTO);
    
    // 페이지별 회원 목록 조회 (페이지당 5명)
    @Select("SELECT member_seq, id, name, email, phone, gender, address, role, register_date " +
           "FROM member " +
           "ORDER BY member_seq DESC " +
           "LIMIT #{start}, 5")
    public List<MemberDTO> getMembersByPage(@Param("start") int start);
    
    // 전체 회원 수 조회
    @Select("SELECT COUNT(*) FROM member")
    public long getTotalCount();
    
    // 검색 결과 개수 조회 (검색어에 따른)
    @Select("SELECT COUNT(*) FROM member " +
            "WHERE id LIKE CONCAT('%', #{searchKeyword}, '%') " +
            "OR name LIKE CONCAT('%', #{searchKeyword}, '%') " +
            "OR email LIKE CONCAT('%', #{searchKeyword}, '%') " +
            "OR phone LIKE CONCAT('%', #{searchKeyword}, '%')")
    public int getSearchCount(@Param("searchKeyword") String searchKeyword);
    
    // 검색 결과 페이징 처리
    @Select("SELECT member_seq, id, name, email, phone, gender, address, role, register_date " +
            "FROM member " +
            "WHERE id LIKE CONCAT('%', #{keyword}, '%') " +
            "OR name LIKE CONCAT('%', #{keyword}, '%') " +
            "OR email LIKE CONCAT('%', #{keyword}, '%') " +
            "OR phone LIKE CONCAT('%', #{keyword}, '%') " +
            "ORDER BY member_seq DESC " +
            "LIMIT #{start}, 5")
    public List<MemberDTO> searchMembersByPage(
            @Param("keyword") String keyword,
            @Param("start") int start
    );
    
    // 전체 회원 목록 조회 (페이징용)
    @Select("SELECT member_seq, id, name, email, phone, gender, address, role, register_date " +
            "FROM member " +
            "ORDER BY member_seq DESC")
    public List<MemberDTO> getAllMembers();

    @Update("UPDATE member SET role = 'SELLER' WHERE member_seq = ${member_seq} ")
	public void setSeller(int memberSeqInt);

    @Update("UPDATE member SET role = 'USER' WHERE member_seq = ${member_seq} ")
	public void cancelSeller(int memberSeqInt);
}