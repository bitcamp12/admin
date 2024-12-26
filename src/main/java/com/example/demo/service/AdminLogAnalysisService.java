package com.example.demo.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dao.VisitorLogDAO;

@Service
public class AdminLogAnalysisService {
    
    @Autowired
    private VisitorLogDAO visitorLogDAO;
    
    // 시간대별 통계
    public List<Map<String, Object>> getHourlyStats() {
        return visitorLogDAO.getHourlyStats();
    }
    
    // 요일별 통계
    public List<Map<String, Object>> getDailyStats() {
        return visitorLogDAO.getDailyStats();
    }
    
    // 총 방문자 수
    public long getTotalVisitorCount() {
        return visitorLogDAO.getTotalVisitorCount();
    }
}