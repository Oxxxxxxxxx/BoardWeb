package com.yedam.common;

import com.yedam.service.BoardService;
import com.yedam.service.BoradServiceImpl;
import com.yedam.vo.BoardVO;

public class AppTest {
	public static void main(String[] args) {
		BoardVO board = new BoardVO();
		board.setTitle("수정....입력테스트2");
		board.setContent("수정....내용입니다2");
		board.setWriter("kim");
		board.setBoardNo(257);
		
		
		
		//목록
		BoardService svc = new BoradServiceImpl();
		svc.removeboard(3);
		
	}
}
