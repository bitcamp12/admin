package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayTimeTableDTO {
	private int playTimeTableSeq;
	private int playSeq;
	private String start_time;
	private String end_time;
	private String start_dis_time;
	private String end_dis_time;
	private int min_rate;
	private int max_rate;
	private LocalDateTime target_date;
}
