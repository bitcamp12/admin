package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.bean.MemberPaging;
import com.example.demo.entity.Member;
import com.example.demo.entity.Notice;
import com.example.demo.entity.ReviewAfter;
import com.example.demo.entity.ReviewBefore;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.repository.ReviewAfterRepository;
import com.example.demo.repository.ReviewBeforeRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.NoticeService;
import com.example.demo.service.ReviewAfterService;
import com.example.demo.service.ReviewBeforeService;
import com.example.demo.service.SellerService;

import jakarta.servlet.http.HttpSession;

// Admin = 사이트 관리자
@Controller
@RequestMapping(value="/secure/admin")
public class AdminController {
	
	@Autowired
	private ReviewBeforeService reviewBeforeService;
	
	@Autowired
	private ReviewAfterService reviewAfterService;
	
	@Autowired
	private SellerService sellerService;
	
	@Autowired
    private MemberService memberService;

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private ReviewBeforeRepository reviewBeforeRepository;
	
	@Autowired
	private ReviewAfterRepository reviewAfterRepository;
	
	@Autowired
	HttpSession httpSession;
	
    @GetMapping("/index")
	public String index(Model model) {
    	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
    	String role = (String) httpSession.getAttribute("role");
    	String name = (String) httpSession.getAttribute("name");
    	System.out.println(name);
    	if("ADMIN".equals(role) && name != null) {
    		model.addAttribute("name", name);
    		return "admin/index";  // index.html 템플릿을 렌더링
    	}else {
    		return "redirect:/secure/login";
    	}
        
    }
    
	@GetMapping("/logout")
	public String logout() {
		httpSession.invalidate();
    	return "redirect:/secure/login";
    }
	
    @GetMapping("/memberList")
	public String memberList(Model model) {
    	Map<String, Object> members = memberService.getMembersWithPaging(1);
    	model.addAttribute("members", members.get("members"));
    	model.addAttribute("memberPaging", members.get("memberPaging"));
    	model.addAttribute("currentPage", members.get("currentPage"));
    	model.addAttribute("totalPages", members.get("totalPages"));
    	model.addAttribute("totalCount", members.get("totalCount"));
        return "admin/body/memberList";  // index.html 템플릿을 렌더링
    }
 
    @GetMapping("/sellerList")
 	public String sellerList(
 			@RequestParam(value="value", required = false) String value,
	        @PageableDefault(page=0, size=5, 
	        sort="memberSeq", direction = Sort.Direction.DESC) 
 			Pageable pageable,
	        Model model) {
    	
    	List<Member> sellerList = null;
    	long totalItems = 0;
    	 if(value == null || value.isEmpty()) {
    	 	sellerList = sellerService.getSellerPaging(pageable);
    	 	totalItems = sellerService.getTotalCount(); //전체 회원 수 조회
    	 }
    	 else {
    		 sellerList = sellerService.getSellerPagingKeyword(pageable, value);
    		 totalItems = sellerService.getTotalCountKeyword(value); //전체 회원 수 조회
    		 model.addAttribute("keyword", value);
    	 }
    	MemberPaging memberPaging = new MemberPaging();
 		
 		memberPaging.setCurrentPage(pageable.getPageNumber()+1);
 		memberPaging.setTotalItems((int)totalItems);
 		memberPaging.makePagingHTML();
 		
    	 model.addAttribute("sellerList", sellerList);
    	 model.addAttribute("paging", memberPaging.getPagingHTML() );
    	 
         return "admin/body/sellerList";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/noticeList")
 	public String noticeList(
 			@RequestParam(value="value", required = false) String value,
	        @PageableDefault(page=0, size=5, 
	        sort="noticeSeq", direction = Sort.Direction.DESC) 
 			Pageable pageable,
	        Model model) {
    	
    	List<Notice> noticeList = null;
    	long totalItems = 0;
    	 if(value == null || value.isEmpty()) {
    		noticeList = noticeService.getNoticePaging(pageable);
    	 	totalItems = noticeService.getTotalCount(); //전체 회원 수 조회
    	 }
    	 else {
    		 noticeList = noticeService.getNoticePagingKeyword(pageable, value);
    		 totalItems = noticeService.getTotalCountKeyword(value); //전체 회원 수 조회
    		 model.addAttribute("keyword", value);
    	 }
    	MemberPaging memberPaging = new MemberPaging();
 		
 		memberPaging.setCurrentPage(pageable.getPageNumber()+1);
 		memberPaging.setTotalItems((int)totalItems);
 		memberPaging.makePagingHTML();
 		
    	 model.addAttribute("noticeList", noticeList);
    	 model.addAttribute("paging", memberPaging.getPagingHTML() );
    	 
    	  return "admin/body/noticeList";  // index.html 템플릿을 렌더링
     }
    
   
    @GetMapping("/noticeDetail/{noticeSeq}")
    public String noticeDetail(@PathVariable("noticeSeq") int noticeSeq, Model model) {
        // noticeSeq를 사용하여 필요한 데이터를 모델에 추가
    	
    	Optional<Notice> notice = noticeRepository.findById(noticeSeq);
    	if(notice.isPresent()) {
    		  model.addAttribute("notice", notice.get());
    	}
    	// 관리자 정보 등 추가적인 데이터 처리 후 뷰 반환
        return "admin/body/noticeDetail";  // noticeDetail.html 템플릿을 렌더링
    }
    
    @GetMapping("/noticeWriteForm")
 	public String noticeWriteForm(Model model) {
     	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
         return "admin/body/noticeWriteForm";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/noticeUpdateForm/{noticeSeq}")
  	public String noticeUpdateForm(@PathVariable("noticeSeq") int noticeSeq, Model model) {
      	//관리자 정보 추후 추가하기 model 어트리뷰트..//	
    	Optional<Notice> notice = noticeRepository.findById(noticeSeq);
    	if(notice.isPresent()) {
    		  model.addAttribute("notice", notice.get());
    	}
          return "admin/body/noticeUpdateForm";  // index.html 템플릿을 렌더링
      }
    
    @GetMapping("/reviewAfterList")
 	public String reviewAfterList(
 			@RequestParam(value="value", required = false) String value,
	        @PageableDefault(page=0, size=5, 
	        sort="reviewAfterSeq", direction = Sort.Direction.DESC) 
 			Pageable pageable,
	        Model model) {
    	
    	List<ReviewAfter> reviewAfterList = null;
    	long totalItems = 0;
    	 if(value == null || value.isEmpty()) {
    		reviewAfterList = reviewAfterService.getReviewAfterPaging(pageable);
    	 	totalItems = reviewAfterService.getTotalCount(); //전체 회원 수 조회
    	 }
    	 else {
    		 reviewAfterList = reviewAfterService.getReviewAfterPagingKeyword(pageable, value);
    		 totalItems = reviewAfterService.getTotalCountKeyword(value); //전체 회원 수 조회
    		 model.addAttribute("keyword", value);
    	 }
    	MemberPaging memberPaging = new MemberPaging();
 		
 		memberPaging.setCurrentPage(pageable.getPageNumber()+1);
 		memberPaging.setTotalItems((int)totalItems);
 		memberPaging.makePagingHTML();
 		
    	 model.addAttribute("reviewAfterList", reviewAfterList);
    	 model.addAttribute("paging", memberPaging.getPagingHTML() );
    	 
    	  return "admin/body/reviewAfterList";  // index.html 템플릿을 렌더링
     }
    
    @GetMapping("/reviewBeforeList")
 	public String reviewBeforeList(
 			@RequestParam(value="value", required = false) String value,
	        @PageableDefault(page=0, size=5, 
	        sort="reviewBeforeSeq", direction = Sort.Direction.DESC) 
 			Pageable pageable,
	        Model model) {
    	
    	List<ReviewBefore> reviewBeforeList = null;
    	long totalItems = 0;
    	 if(value == null || value.isEmpty()) {
    		 reviewBeforeList = reviewBeforeService.getReviewBeforePaging(pageable);
    	 	totalItems = reviewBeforeService.getTotalCount(); //전체 회원 수 조회
    	 }
    	 else {
    		 reviewBeforeList = reviewBeforeService.getReviewBeforePagingKeyword(pageable, value);
    		 totalItems = reviewBeforeService.getTotalCountKeyword(value); //전체 회원 수 조회
    		 model.addAttribute("keyword", value);
    	 }
    	MemberPaging memberPaging = new MemberPaging();
 		
 		memberPaging.setCurrentPage(pageable.getPageNumber()+1);
 		memberPaging.setTotalItems((int)totalItems);
 		memberPaging.makePagingHTML();
 		
    	 model.addAttribute("reviewBeforeList", reviewBeforeList);
    	 model.addAttribute("paging", memberPaging.getPagingHTML() );
    	 
    	  return "admin/body/reviewBeforeList";  // index.html 템플릿을 렌더링
     }

}
