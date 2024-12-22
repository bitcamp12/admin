package com.example.demo.controller;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.MemberPaging;
import com.example.demo.dto.QnaDTO;
import com.example.demo.entity.Book;
import com.example.demo.entity.Play;
import com.example.demo.entity.PlayTimeTable;
import com.example.demo.repository.PlayRepository;
import com.example.demo.repository.PlayTimeTableRepository;
import com.example.demo.repository.QnaRepository;
import com.example.demo.repository.TheaterRepository;
import com.example.demo.service.BookService;
import com.example.demo.service.PlayService;
import com.example.demo.service.PlayTimeTableService;
import com.example.demo.service.QnaService;

import jakarta.servlet.http.HttpSession;

//Seller = 공연 관계자
@Controller
@RequestMapping(value = "/secure/seller")
public class SellerController {

	
	@Autowired
	private PlayTimeTableService playTimeTableService;
	
	@Autowired
	private PlayService playService;

	@Autowired
	private PlayRepository playRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private QnaRepository qnaRepository;

	@Autowired
	private QnaService qnaService;

	@Autowired
	private BookService bookService;

	@Autowired
	private PlayTimeTableRepository playTimeTableRepository;

	@Autowired
	HttpSession httpSession;

	@GetMapping("/index")
	public String index(Model model) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	
    	if (authentication != null && authentication.isAuthenticated()) {
    		String username = authentication.getName();
    		model.addAttribute("name", username);
    	}
		
		return "seller/index"; // index.html 템플릿을 렌더링
	}

//	@GetMapping("/logout")
//	public String logout() {
//		httpSession.invalidate();
//		return "redirect:/secure/login";
//	}

	// 공연정보등록
	@GetMapping("/playRegisterForm")
	public String playReg(Model model) {
		return "seller/body/playRegisterForm";
	}

	// 공연정보수정
	@GetMapping("/playUpdate/{playSeq}")
	public String playUpdate(@PathVariable("playSeq") int playSeq, Model model) {
		Play play = null;
		try {
			play = playService.findById(playSeq);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (play != null) {
			model.addAttribute("play", play);
			ZoneId zoneId = ZoneId.systemDefault();
			Date startTime = Date.from(play.getStartTime().atZone(zoneId).toInstant());
			Date endTime = Date.from(play.getEndTime().atZone(zoneId).toInstant());
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
		}
		return "seller/body/playUpdate";
	}

	@GetMapping("/playList")
	public String playList(@RequestParam(value = "value", required = false) String value,
			@PageableDefault(page = 0, size = 5, sort = "playSeq", direction = Sort.Direction.DESC) Pageable pageable,
			Model model) {

		List<Play> playList = null;
		long totalItems = 0;
		if (value == null || value.isEmpty()) {
			playList = playService.getPlayPaging(pageable);
			totalItems = playService.getTotalCount(); // 전체 회원 수 조회
		} else {
			playList = playService.getPlayPagingKeyword(pageable, value);
			totalItems = playService.getTotalCountKeyword(value); // 전체 회원 수 조회
			model.addAttribute("keyword", value);
		}
		MemberPaging memberPaging = new MemberPaging();

		memberPaging.setCurrentPage(pageable.getPageNumber() + 1);
		memberPaging.setTotalItems((int) totalItems);
		memberPaging.makePagingHTML();

		model.addAttribute("playList", playList);
		model.addAttribute("paging", memberPaging.getPagingHTML());

		return "seller/body/playList"; // index.html 템플릿을 렌더링
	}
	
	
	@GetMapping("/bookList")
	public String bookList(@RequestParam(value = "value", required = false) String value,
			@PageableDefault(page = 0, size = 5, sort = "bookSeq", direction = Sort.Direction.DESC) Pageable pageable,
			Model model) {

		List<Book> bookList = null;
		long totalItems = 0;
		if (value == null || value.isEmpty()) {
			bookList = bookService.getBookPaging(pageable);
			totalItems = bookService.getTotalCount(); // 전체 회원 수 조회
		} else {
			bookList = bookService.getBookPagingKeyword(pageable, value);
			totalItems = bookService.getTotalCountKeyword(value); // 전체 회원 수 조회
			model.addAttribute("keyword", value);
		}
		MemberPaging memberPaging = new MemberPaging();

		memberPaging.setCurrentPage(pageable.getPageNumber() + 1);
		memberPaging.setTotalItems((int) totalItems);
		memberPaging.makePagingHTML();

		model.addAttribute("bookList", bookList);
		model.addAttribute("paging", memberPaging.getPagingHTML());

		return "seller/body/bookList"; // index.html 템플릿을 렌더링
	}


	@GetMapping("/qnaList")
	public String qnaList(@RequestParam(value = "value", required = false) String value,
			@PageableDefault(page = 0, size = 5, sort = "qnaSeq", direction = Sort.Direction.DESC) Pageable pageable,
			Model model) {

		List<QnaDTO> qnaList = null;
		long totalItems = 0;
		if (value == null || value.isEmpty()) {
			qnaList = qnaService.getQnaList(pageable);
			totalItems = qnaService.getTotalCount(); // 전체 회원 수 조회
		} else {
			qnaList = qnaService.getQnaPagingKeyword(pageable, value);
			totalItems = qnaService.getTotalCountKeyword(value); // 전체 회원 수 조회
			model.addAttribute("keyword", value);
		}
		MemberPaging memberPaging = new MemberPaging();

		memberPaging.setCurrentPage(pageable.getPageNumber() + 1);
		memberPaging.setTotalItems((int) totalItems);
		memberPaging.makePagingHTML();

		model.addAttribute("qnaList", qnaList);
		model.addAttribute("paging", memberPaging.getPagingHTML());

		return "seller/body/qnaList"; // index.html 템플릿을 렌더링
	}

	// QnA 게시판 - 답변 뷰
	@GetMapping("/qnaDetail/{qnaSeq}")
	@ResponseBody
	public ResponseEntity<QnaDTO> qnaDetail(@PathVariable("qnaSeq") int qnaSeq) {
		QnaDTO qnaDTO = qnaService.getQnaDetail(qnaSeq);

		if (qnaDTO != null) {
			return ResponseEntity.ok(qnaDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	// QnA 게시판 - 답변 등록
	@GetMapping("/qnaWrite")
	public String qnaWrite(@PathVariable("qnaSeq") int qnaSeq, Model model) {
		return "seller/body/qnaWrite";
	}

	// 공연 시간 등록
	@GetMapping("/timeRegisterForm")
	public String timeRegisterForm(Model model) {
		model.addAttribute("playList", playRepository.findAll());
		model.addAttribute("theaterList", theaterRepository.findAll());
		return "seller/body/timeRegisterForm";
	}

	// 공연 시간 등록
	@GetMapping("/timeUpdateForm/{seq}")
	public String timeUpdateForm(@PathVariable("seq") int seq, Model model) {

		model.addAttribute("timetable", playTimeTableRepository.findById(seq).get());
		model.addAttribute("playList", playRepository.findAll());
		model.addAttribute("theaterList", theaterRepository.findAll());

		ZoneId zoneId = ZoneId.systemDefault();
		Date targetDate = Date
				.from(playTimeTableRepository.findById(seq).get().getTargetDate().atZone(zoneId).toInstant());
		model.addAttribute("targetDate", targetDate);

		return "seller/body/timeUpdateForm";
	}


	@GetMapping("/timeList")
	public String timeList(@RequestParam(value = "value", required = false) String value,
			@PageableDefault(page = 0, size = 5, sort = "playTimeTableSeq", direction = Sort.Direction.DESC) Pageable pageable,
			Model model) {

		List<PlayTimeTable> timeList = null;
		long totalItems = 0;
		if (value == null || value.isEmpty()) {
			timeList = playTimeTableService.getTimePaging(pageable);
			totalItems = playTimeTableService.getTotalCount(); // 전체 회원 수 조회
		} else {
			timeList = playTimeTableService.getTimePagingKeyword(pageable, value);
			totalItems = playTimeTableService.getTotalCountKeyword(value); // 전체 회원 수 조회
			model.addAttribute("keyword", value);
		}
		MemberPaging memberPaging = new MemberPaging();

		memberPaging.setCurrentPage(pageable.getPageNumber() + 1);
		memberPaging.setTotalItems((int) totalItems);
		memberPaging.makePagingHTML();

		model.addAttribute("timeList", timeList);
		model.addAttribute("paging", memberPaging.getPagingHTML());

		return "seller/body/timeList"; // index.html 템플릿을 렌더링
	}
	
	
	
	
	
}

/*
 * // 공연정보등록
 * 
 * @GetMapping("/playRegisterForm") public String playReg(Model model) { return
 * "seller/body/playRegisterForm"; }
 * 
 * // 공연정보수정
 * 
 * @GetMapping("/playUpdate") public String playUdt(Model model) { return
 * "seller/body/playUpdate"; }
 * 
 * // 공연정보리스트
 * 
 * @GetMapping("/playList") public String playList(Model model) { return
 * "seller/body/playList"; }
 * 
 * // 예매관리
 * 
 * @GetMapping("/bookList") public String bookList(Model model) { return
 * "seller/body/bookList"; }
 */