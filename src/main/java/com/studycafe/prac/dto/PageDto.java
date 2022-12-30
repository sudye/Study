package com.studycafe.prac.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
	
	private int startPage;//현재 화면에서 보여질 시작 페이지 번호
	private int endPage;//현재 화면에서 보여질 마지막 페이지 번호
	private boolean prev;
	private boolean next;
	private int total;//전체 페이지 개수
	
	private Criteria cri;
	
	public PageDto(Criteria cri, int total) {
		
		this.cri = cri;
		this.total = total;
		
		this.endPage = (int) Math.ceil((cri.getPageNum()/5.0)) * 5;//Math.ceil->올림
		// 총 57개의 글이 존재(total=57)
		// 1 2 3 4 5 next
		// prev 6 7 8 9 10 next 
		// prev 11 12 
		this.startPage = this.endPage - 4;
		
		int realEnd = (int) Math.ceil((total*1.0)/cri.getAmount());
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
		//이전, 다음 페이지 버튼 출력 여부 결정
	}
}
