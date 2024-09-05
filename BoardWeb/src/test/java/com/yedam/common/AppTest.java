package com.yedam.common;

import com.yedam.service.BoardService;
import com.yedam.service.BoradServiceImpl;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		SearchDTO search = new SearchDTO();
		search.setSearchCondition("W");
		search.setKeyword("user01");
		search.setPage(5);
		
		
		
		//목록
		BoardService svc = new BoradServiceImpl();
		svc.boardList(search).forEach(System.out::println);
	}
}
