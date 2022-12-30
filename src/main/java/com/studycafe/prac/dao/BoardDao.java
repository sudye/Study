package com.studycafe.prac.dao;

import java.util.ArrayList;
import java.util.List;


import com.studycafe.prac.dto.BoardDto;
import com.studycafe.prac.dto.Criteria;

public interface BoardDao {
	
	public void writeQuestion(String btitle, String bcontent, String buserid);//질문하기 insert
	public List<BoardDto> questionList(Criteria cri);//질문게시판 리스트 가져오기 select
	public BoardDto questionView(String bnum);//선택한 글 번호의 정보 가져오기 select
	public void questionModify(String bnum, String btitle, String bcontent, String buserid);//해당 글번호로 조회하여 질문 수정 update
	public void questionDelete(String bnum);//글 삭제 delete
	public int boardAllCount();//게시판 총 글의 개수 가져오기
}
