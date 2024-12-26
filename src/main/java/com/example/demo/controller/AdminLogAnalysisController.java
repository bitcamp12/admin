package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.AdminLogAnalysisService;

@Controller
@RequestMapping("/secure/admin")
public class AdminLogAnalysisController {
	
	@Autowired
	private AdminLogAnalysisService adminLogAnalysisService;
	
	@GetMapping("/analysis")
	public String analysisPage(Model model) {
		try {
			
			// 방문자 통계 데이터 수집
            List<Map<String, Object>> hourlyStats = adminLogAnalysisService.getHourlyStats();
            System.out.println("hourlyStats: " + hourlyStats);  // 데이터 확인하기
            
            List<Map<String, Object>> dailyStats = adminLogAnalysisService.getDailyStats();
            long totalVisitors = adminLogAnalysisService.getTotalVisitorCount();

            // 데이터 유효성 검증
            if (hourlyStats == null || hourlyStats.isEmpty()) {
                model.addAttribute("error", "시간별 통계 데이터를 불러올 수 없습니다.");
                return "admin/body/analysis";
            }
            
            // 최고 인기 시간대 계산
            Map<String, Object> peakHour = null;
            long maxVisits = 0;
            if (hourlyStats != null) {
                for (Map<String, Object> stat : hourlyStats) {
                    long visits = ((Number) stat.get("visit_count")).longValue();
                    if (visits > maxVisits) {
                        maxVisits = visits;
                        peakHour = stat;
                    }
                }
            }
            
            System.out.println("=== Debug Logs ===");
            System.out.println("hourlyStats: " + hourlyStats);
            System.out.println("dailyStats: " + dailyStats);
            System.out.println("totalVisitors: " + totalVisitors);
            System.out.println("peakHour: " + peakHour);
           
            // 모델에 데이터 추가
            model.addAttribute("hourlyStats", hourlyStats);
            model.addAttribute("dailyStats", dailyStats);
            model.addAttribute("totalVisitors", totalVisitors);
            model.addAttribute("peakHour", peakHour);
            
            
            return "admin/body/analysis";
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "통계 데이터를 불러오는 중 오류가 발생했습니다.");
            return "admin/body/analysis";
        }
    }
}