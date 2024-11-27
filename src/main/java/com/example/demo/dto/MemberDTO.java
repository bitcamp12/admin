package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {
	
    private int memberSeq;         // 회원 번호 (Primary Key)
    private String id;             // 아이디
    private String name;           // 이름
    private String password;       // 비밀번호
    private String email;          // 이메일
    private String phone;          // 전화번호
    private String address;        // 주소
    private String gender;         // 성별 (ENUM)
    private String snsToken;       // SNS 토큰
    private String role;             // 역할 (ENUM)
    private LocalDateTime registerDate; // 가입 날짜
    
    //paging 관련 필드
    private int page = 1;       // 현재 페이지 번호(기본 = 1페이지)
    private int size = 5;      // 페이지당 포함되는 데이터
    private int totalPages;     // 전체 페이지 수
    private long totalElements; // 전체 데이터 수

}
