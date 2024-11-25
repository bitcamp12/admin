package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO;
	
	private static final int PAGE_SIZE = 5;

	public void signUp(MemberDTO memberDTO) {
		memberDAO.signUp(memberDTO);
	}
	
	//secure/admin/index의 회원관리 첫 화면 표기 (전체 회원 조회)
	public List<MemberDTO> getAllMembers(int page){
		return memberDAO.getMembersByPage((page-1) * PAGE_SIZE);
	}
	
	// 페이지 정보 및 회원 목록 조회
	public Map<String, Object> getMembersWithPaging(int pageNo) {
		Map<String, Object> result = new HashMap<>();

		if (pageNo <1) {
			pageNo = 1;
		}
		
		int start = (pageNo - 1) * PAGE_SIZE; 	//페이지 시작 위치 계산 (pg당 5개씩)
		long totalCount = memberDAO.getTotalCount();
		int totalPages = (int) Math.ceil((double)totalCount / PAGE_SIZE);
		
		
		if (pageNo > totalPages && totalPages > 0) {
			pageNo = totalPages;
			start = (pageNo - 1) * PAGE_SIZE;
		}
		
		//현재 페이지의 회원 목록 조회
		List<MemberDTO> members = memberDAO.getMembersByPage(start);
		
		// 맵으로 결과 집어넣기
		result.put("members", members);
		result.put("currentPage", pageNo);
		result.put("totalPages", totalPages);
		result.put("totalCount", totalCount);
		
		return result;
	}
	
	// 검색 결과 완료 후 페이징 처리
	public Map<String, Object> searchMembersWithPaging(String keyword, int pageNo) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			//키워드 null 체크
			if (keyword == null || keyword.trim().isEmpty()) {
				return getMembersWithPaging(pageNo); // 키워드 없을 경우 일반 목록 
			}
			
			// 페이지 번호 유효성 검사
			if (pageNo <1) {
				pageNo = 1;
			}
			
			 int start = (pageNo - 1) * PAGE_SIZE;
	            long totalCount = memberDAO.getSearchCount(keyword);
	            int totalPages = (int) Math.ceil((double)totalCount / PAGE_SIZE);
	            
	            // 검색 결과가 없는 경우 처리
	            if (totalCount == 0) {
	                result.put("members", List.of());
	                result.put("currentPage", 1);
	                result.put("totalPages", 0);
	                result.put("totalCount", 0);
	                result.put("keyword", keyword);
	                return result;
	            }
	            
	            // 페이지 번호가 총 페이지 수를 초과하는 경우 처리
	            if (pageNo > totalPages) {
	                pageNo = totalPages;
	                start = (pageNo - 1) * PAGE_SIZE;
	            }
	            
	            List<MemberDTO> members = memberDAO.searchMembersByPage(keyword, start);
	            
	            result.put("members", members);
	            result.put("currentPage", pageNo);
	            result.put("totalPages", totalPages);
	            result.put("totalCount", totalCount);
	            result.put("keyword", keyword);
	            
	        } catch (Exception e) {
	            // 에러 로깅
	            e.printStackTrace();
	            // 에러 발생 시 빈 결과 반환
	            result.put("error", "검색 중 오류가 발생했습니다.");
	            result.put("members", List.of());
	            result.put("currentPage", 1);
	            result.put("totalPages", 0);
	            result.put("totalCount", 0);
	            result.put("keyword", keyword);
	        }
	        
	        return result;
	    }
	}