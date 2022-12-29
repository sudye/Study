package com.studycafe.prac.dao;

import com.studycafe.prac.dto.memberDto;

public interface MemberDao {
	
	public int joinMember(String userId, String userPw, 
			String userName, String userPhone, String userEmail, String userPoint, String usingTicket);
	
	public int checkUserId(String userId);
	
	public int checkUserIdAndPw(String userId, String userPw);
	
	public memberDto getMemberInfo(String userId);
	
	public void memberModify(String userId, String userPw, String userName, String userPhone,
			String userEmail, String userPoint);
	
	public void updateUticketPoint(String userId, String userPoint, String usingTicket);
}
