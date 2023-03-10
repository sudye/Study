package com.studycafe.prac.dao;

import java.util.ArrayList;

import com.studycafe.prac.dto.ScreservDto;
import com.studycafe.prac.dto.seatDto;

public interface TodayTicketDao {
	
	
	//당일권 관련
	public void regist(int seatNo, String userId, String ticketName, String selectedDate);
	public ArrayList<seatDto> registTodayConfirm();
	public ArrayList<seatDto> searchSeat(String seatNo,String selectedDate);
	public ArrayList<ScreservDto> searchReservation(int seatNo, String selectedDate);
	public void makeReservation(int seatNo,String userId, String selectedDate, String selectedTime);
	
	
	//시간제(정액권) 관련
	public void BuySTicket(String sticketName, String suserId, String sremainTime);
	public void updateRemainTime(String suserId);
}
