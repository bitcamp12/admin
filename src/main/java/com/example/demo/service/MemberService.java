package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bean.MemberPaging;
import com.example.demo.dao.MemberDAO;
import com.example.demo.dto.MemberDTO;
import com.example.demo.entity.Member;
import com.example.demo.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired
	MemberDAO memberDAO;
	
	@Autowired //페이징 컴포넌트
	private MemberPaging memberPaging;
	
	@Autowired
	private MemberRepository memberRepository;

	private static final int PAGE_SIZE = 5; // 한 페이지당 표시할 회원 수
	private static final int PAGE_BLOCK = 4; // 페이징처리 표시 개수
	
	//회원가입
	public void signUp(MemberDTO memberDTO) {
		memberDAO.signUp(memberDTO);
	}	
	
	//회원 목록 조회(페이징 없이) 
	public List<MemberDTO> getAllMembers(int page){
		return memberDAO.getMembersByPage((page-1) * PAGE_SIZE);
	}
	
	// 페이지 정보 및 회원 목록 조회
	public Map<String, Object> getMembersWithPaging(int pageNo) {
		Map<String, Object> result = new HashMap<>();

		if (pageNo < 1) {
			pageNo = 1;
		}
		
		int start = (pageNo - 1) * PAGE_SIZE; 	//페이지 시작 위치 계산 (pg당 5개씩)
		
		long totalItems = memberDAO.getTotalCount(); //전체 회원 수 조회
		
		memberPaging.setCurrentPage(pageNo);
		memberPaging.setPageBlock(PAGE_BLOCK);
		memberPaging.setPageSize(PAGE_SIZE);
		memberPaging.setTotalItems((int)totalItems);
		memberPaging.makePagingHTML();
				
		//현재 페이지의 회원 목록 조회
		List<MemberDTO> members = memberDAO.getMembersByPage(start);
		
		// 맵으로 결과 집어넣기
		result.put("members", members);
		result.put("memberPaging", memberPaging);
		result.put("currentPage", pageNo);
		result.put("totalPages", (int)Math.ceil((double)totalItems / PAGE_SIZE));
		result.put("totalItems", totalItems);

		return result;
	}
	
	// 검색 결과 완료 후 페이징 처리
	public Map<String, Object> searchMembersWithPaging(String keyword, int pageNo) {
		Map<String, Object> result = new HashMap<>();
		
		try {
			// 검색어가 없을 때 전체 목록 그냥 반환하기
			if (keyword == null || keyword.trim().isEmpty()) {
				return getMembersWithPaging(pageNo); // 키워드 없을 경우 일반 목록 
			}
			
			if (pageNo < 1) pageNo = 1;
			
			int start = (pageNo - 1) * PAGE_SIZE;
			long totalItems = memberDAO.getSearchCount(keyword);
			int totalPages = (int) Math.ceil((double)totalItems / PAGE_SIZE);
	        
			
			// 페이지 번호가 총 페이지 수를 초과하는 경우
	        if (pageNo > totalPages && totalPages > 0) {
	        	pageNo = totalPages;
	            start = (pageNo - 1) * PAGE_SIZE;
            }
	            
	            	            
	        memberPaging.setCurrentPage(pageNo);
	        memberPaging.setPageBlock(PAGE_BLOCK);
	        memberPaging.setPageSize(PAGE_SIZE);
	        memberPaging.setTotalItems((int)totalItems);
	        memberPaging.makePagingHTML();
	            
	        //검색 결과 조회
	        List<MemberDTO> members = memberDAO.searchMembersByPage(keyword, start);
	            
	        result.put("members", members);
	        result.put("memberPaging", memberPaging);
	        result.put("pageNo", pageNo);
	        result.put("totalPages", totalPages);
	        result.put("totalItems", totalItems);
	        result.put("keyword", keyword);
	            
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "검색 중 오류가 발생했습니다.");
            result.put("members", List.of());
            result.put("currentPage", 1);
            result.put("totalPages", 0);
            result.put("totalItems", 0);
            result.put("keyword", keyword);
    	}
	        
        return result;
    }

	public List<Member> getSellerList() {
		List<Member> sellerList = memberRepository.findAllSellerList();
		return sellerList;
	}

	public void setSeller(int memberSeqInt) {
		memberDAO.setSeller(memberSeqInt);
	}

	public void cancelSeller(int memberSeq) {
		memberDAO.cancelSeller(memberSeq);
	}
}