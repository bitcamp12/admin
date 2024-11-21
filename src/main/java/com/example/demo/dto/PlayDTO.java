package com.example.demo.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PlayDTO {
	private int playSeq;
	private String play_name;
	private LocalDate start_date;
	private LocalDate end_date;
	private String imageFileName;
	private String imageOriginalFileName;
	private String description;
	private String address;
	private String total_actor;
}
