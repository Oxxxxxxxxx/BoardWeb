package com.yedam.common;

import lombok.Data;

//페이징 계산 위한 클래스
@Data
public class PageDTO {
	
	//현재 페이지 정보. 1..3..10
	// 이전, 이후 정보
	int page;
	int startPage, endPage;
	boolean prev, next;
	
	public PageDTO(int page, int totalCnt) { //page : 3, totalCnt: 76건 16page
		this.page = page;
		this.endPage = (int)(Math.ceil(page / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		
		int realEnd = (int)(Math.ceil(totalCnt/5.0));
		this.endPage = this.endPage > realEnd ? realEnd : this.endPage;
		
		//이전, 이후 여부
		prev = this.startPage > 1;
		next = this.endPage < realEnd ? true : false;
	}
}
