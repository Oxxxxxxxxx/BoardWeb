package com.yedam.control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoradServiceImpl;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String bno = request.getParameter("bno");
		String page = request.getParameter("page");
		
		//검색조건 searchCondition & keyword
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		
	
		BoardService svc = new BoradServiceImpl();
		BoardVO board = svc.getboard(Integer.parseInt(bno));
		request.setAttribute("board", board);
		request.setAttribute("page", page); // jsp페이지에 전달.
		
		request.setAttribute("sc", sc);
		request.setAttribute("kw", kw);
		
		//캉ㄴ트 증가.
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/board/board.jsp");
		rd.forward(request, response);
		
		
	}

}
