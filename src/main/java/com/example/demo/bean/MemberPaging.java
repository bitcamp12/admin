package com.example.demo.bean;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MemberPaging {
	private int currentPage;
	private int pageBlock = 4;	 // 페이징 UI에서 보여질 그룹화 처리시킬 페이징 개수
	private int pageSize = 5;	 // 한 페이지당 보여지는 데이터 수 
	private int totalItems;  // 전체 데이터 개수
	private StringBuffer pagingHTML; //페이징 UI를 HTML로 생성 위한 StringBuffer 객체
	
	public void makePagingHTML() {
		pagingHTML = new StringBuffer();
		
		//전체 페이지 수 계산
		int totalPage = (totalItems + pageSize -1) / pageSize;
		
		//시작 페이지와 끝 페이지 계산
		int startPage = (currentPage -1)/ pageBlock * pageBlock + 1;
		int endPage = startPage + pageBlock -1;
		if(endPage > totalPage) endPage = totalPage;
		
		pagingHTML.append("<div class='pagination'>");
		
		//이전 블록 링크
		if(currentPage > 1) {
			pagingHTML.append("<span class='nav-button' onclick='memberPaging("+ (currentPage-1) +")'>이전</span>");
		}
		
		//페이지 번호 링크
		for(int i=startPage; i<=endPage; i++) {
			if(i == currentPage) {
				pagingHTML.append("<span class='active' onclick='memberPaging(" + i + ")'>" + i + "</span>");
			} else {
				pagingHTML.append("<span onclick='memberPaging(" + i + ")'>" + i + "</span>");
			}
		}
		
		//다음 블록 링크
		if(currentPage < totalPage) {
			pagingHTML.append("<span class='nav-button' onclick='memberPaging(" + (currentPage+1) + ")'>다음</span>");				
		}
	}	
}
