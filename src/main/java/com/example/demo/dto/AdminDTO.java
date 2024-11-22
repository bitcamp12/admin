package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO {    
	private String searchKeyword; // 검색어

	private int adminSeq;
	private String id;
	private String name;
	private String password;
}
