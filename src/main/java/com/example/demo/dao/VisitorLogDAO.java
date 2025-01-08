package com.example.demo.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper 
public interface VisitorLogDAO {
    // 시간대별 방문자 통계 
    @Select("SELECT hour_of_day, COUNT(*) AS visit_count " +
            "FROM visitor_log " +
            "GROUP BY hour_of_day " +
            "ORDER BY hour_of_day")
    List<Map<String, Object>> getHourlyStats();
    
    
    // 요일별 방문자 통계
    @Select("SELECT day_of_week, COUNT(*) AS visit_count " +
            "FROM visitor_log " +
            "GROUP BY day_of_week " +
            "ORDER BY FIELD(day_of_week, 'MON', 'TUE', 'WED', 'THU', 'FRI', 'SAT', 'SUN')")
    List<Map<String, Object>> getDailyStats();
    
    // 전체 방문자 수
    @Select("SELECT COUNT(*) FROM visitor_log")
    long getTotalVisitorCount();
}