package com.studycafe.prac.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.connector.Response;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studycafe.prac.dao.TodayTicketDao;
import com.studycafe.prac.dto.ScreservDto;
import com.studycafe.prac.dto.seatDto;


@Controller
public class TodayTicketController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping(value="/testpage")//이용금액표
	public String testpage() {
		
		
		return "testpage";
	}
	@RequestMapping(value="/testpage2")//이용금액표
	public String testpage2(Model model) {
		
		String [] testarray = {"0","1","0","1","0","0","1","0","0","1"};
		
		model.addAttribute("test",testarray);
		
		return "testpage2";
	}
	@RequestMapping(value="/tabletest")//이용금액표
	public String talbetest() {
		
		
		return "tabletest";
	}
	
	@RequestMapping(value="/loginpage")//이용금액표
	public String loginpage() {
		
		
		return "loginpage";
	}
	
	@RequestMapping(value="/TicketPrice")//이용금액표
	public String TicketPrice() {
		
		
		return "TicketPrice";
	}
	
	@RequestMapping(value="/ChooseTicket")//이용권선택
	public String chooseTicket() {
		
		
		return "ChooseTicket";
	}
	
	@RequestMapping(value="/TodayTicketView")
	public String TodayTicketView(HttpServletResponse response,HttpSession session) {
		String sessionId = (String) session.getAttribute("userId");
		
		if(sessionId==null) {
		try {
			response.setContentType("text/html; charset=UTF-8");      
	        PrintWriter out;
			out = response.getWriter();
			out.println("<script>alert('로그인이 필요한 서비스입니다.'); history.go(-1);</script>");
		    out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} return "ChooseTicket";
		}else {
		
		return "TodayTicketView";
		}
	}
	
	@RequestMapping(value="/searchSeat")
	public String searchSeat(HttpServletRequest request, Model model) {
		
		TodayTicketDao dao = sqlSession.getMapper(TodayTicketDao.class);
		
		String userId = request.getParameter("userId");
		String selectedDate = request.getParameter("selectedDate");
		int seatNo = Integer.parseInt(request.getParameter("seatNo").toString());
		
		ScreservDto dto= new ScreservDto();
		
		ArrayList<ScreservDto> reservInfo = dao.searchReservation(seatNo, selectedDate);
		
		String reservTimes= dto.getSelectedTime();
		
//		String[] DBselectedTimes = reservTimes.toArray(new String[reservTimes.size()]);
//		int n,f,x;
//		String[] DBselectedTimes = new String[Object.keys(reservTimes).length];
//		for(x=0;x<Object.keys(reservTimes).length;x++) {
//		
//		
//		}
////		String [] DBselectedTimes = {"0","3","6"};
//		 int[] IntDBselectedTimes = new int[DBselectedTimes.length];
//	        for (n = 0; n < DBselectedTimes.length; n++) {
//	        	IntDBselectedTimes[n] = Integer.parseInt(DBselectedTimes[n]);
//	        }
//		String [] OccupiedTimes = {"0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0","0"};
//		for(f=0;f<IntDBselectedTimes.length;f++) {
//			OccupiedTimes[IntDBselectedTimes[f]]= "1";
//		}
//		
	
			
		model.addAttribute("userId", userId);
		model.addAttribute("selectedDate", selectedDate);
		model.addAttribute("seatNo", seatNo);
//		model.addAttribute("opTimes", OccupiedTimes);
		
		return "TodayTicketView2";
	}

	@RequestMapping(value="/TodayTicketView2")
	public String TodayTicketView2() {
			
	
			
			
		
		return "TodayTicketView2";
	}
	
	@RequestMapping(value="/registToday")
	public String regist(HttpServletRequest request,HttpServletResponse response,Model model) {
		
		TodayTicketDao dao = sqlSession.getMapper(TodayTicketDao.class);
		
		int seatNo = Integer.parseInt(request.getParameter("seatNo").toString());
		String userId = request.getParameter("userId");
		String ticketName = request.getParameter("ticketName");
		String selectedDate = request.getParameter("selectedDate");
		String [] selectedTime = request.getParameterValues("selectedTime");
		
		//넘어온 체크박스값 정렬 후, 첫번째 값부터 마지막값까지 추출후 새 배열에 넣음
		Arrays.sort(selectedTime);
		int i;
		String [] selectedTimes= new String[selectedTime.length];
		for(i=0;i<selectedTime.length;i++) {	
		String number=selectedTime[i];
		selectedTimes[i]=number;
		}
		int intticketName= Integer.parseInt(ticketName);//ticketname을 int로 변환
			
			if(intticketName==selectedTime.length) {//체크박스의 갯수와 ticketname의 숫자를 비교해서 일치하지 않으면 뒤로돌려보냄
				dao.regist(seatNo, userId, ticketName, selectedDate);
					for(int n=1;n<=selectedTime.length;n++) {//ST[i]배열의 값을 각각 체크박스 갯수만큼 데이타베이스(선택시간)에 넣음 
						dao.makeReservation(seatNo, userId, selectedDate, selectedTimes[n-1]);
					}
					
				//-----------------방금 들어간 회원정보들을 결제 전 체크페이지에 전달	---------------------------
				ArrayList<seatDto> seatDto= dao.registTodayConfirm();
				
				seatDto fseatDto = seatDto.get(0);
				
				String[] TodayPrice = {"2,000","3,000","5,000","6,000","7,000"};
				String[] TodayTime = {"1","2","4","6","8"};
				for(int t=0;t<5;t++) {
					if(TodayTime[t].equals(ticketName)) {
						
						model.addAttribute("PayingPoint",TodayPrice[t]);//지불할 포인트 계산
					}
				}
				
				
			
				
				int[] Times= new int[17];//8부터 24까지 문자배열생성
				for(int j=0;j<16;j++) {
				Times[j]=j+8;	
				}
				  int[] intstTimes= new int[selectedTimes.length]; //체크박스 지정된 갯수만큼의 사이즈의 배열 생성
				  for (int k = 0; k < selectedTimes.length; k++) {
			            intstTimes[k] = Integer.parseInt(selectedTimes[k]);//체크박스 값들을 정수배열로 변환
			        }
				  int[] savedTimes= new int[intstTimes.length];//체크박스의 값들을 시간으로 치환해줄 배열생성
				  for (i=0;i < intstTimes.length;i++) {
					  savedTimes[i]=Times[intstTimes[i]];//체크박스값 배열들을 시간으로 치환하여 저장
				  }
				  String startTime = String.valueOf(savedTimes[0]); //시작시간을 저장
				  int lastindex = savedTimes[savedTimes.length-1];
				  lastindex = lastindex + 1 ;
				  String endTime = String.valueOf(lastindex); //종료시간 저장
				  
				  model.addAttribute("startTime",startTime);
				  model.addAttribute("endTime",endTime);
				  model.addAttribute("fseatDto", fseatDto);
				  
				//-----------------방금 들어간 회원정보들을 결제 전 체크페이지에 전달 끝	---------------------------
				return "registTodayConfirm";
			}else {
			try {
				response.setContentType("text/html; charset=UTF-8");      
		        PrintWriter out;
				out = response.getWriter();
				out.println("<script>alert('이용시간과 선택한 지정시간이 일치하지 않습니다.'); history.go(-1);</script>");
			    out.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "TodayTicketView2";
			}
		
		
		
		
	
	}	
	
	
	@RequestMapping(value="/registTodayConfirm")
	public String confirm(Model model) {

		
		
		return "registTodayConfirm";
	}
	
}
