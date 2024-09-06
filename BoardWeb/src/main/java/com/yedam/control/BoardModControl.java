package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoradServiceImpl;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardModControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title"); // 사용자의 요청정보중에서 id값을 읽도록 함.
		String content = request.getParameter("content"); // input태그의 name속성

		BoardVO board = new BoardVO();
		board.getTitle();
		board.getContent();
		
		BoardService bsv = new BoradServiceImpl();

		// 회원등록이 정상적일 경우 => 회원목록 페이지 출력.
		// 회원등록이 비정상적일 경우 => 회우너등록 페이지 이동 (msg 전달)
		// 현재 페이지 : addmember.do -> 페이지 재지정 방식 1)forwarding: 파라메타전달 2)redirect :파라메타 불가
		
		
		if (bsv.modifyboard(board)) {
			response.sendRedirect("boardList.do");
		} else {
			request.setAttribute("message", "수정중에 오류가 있습니다.");
			request.getRequestDispatcher("board/modifyForm.tiles").forward(request, response);
		}
	}

}
