package com.studycafe.prac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScreservDto {
	
	private int reservNo;
	private int seatNo;
	private String userId;
	private String selectedDate;
	private String selectedTime;
	private String reservDate;
	
}
