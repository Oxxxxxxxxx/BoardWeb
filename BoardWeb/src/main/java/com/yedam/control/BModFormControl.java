package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.BoardService;
import com.yedam.service.BoradServiceImpl;
import com.yedam.vo.BoardVO;

public class BModFormControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//회원아이디 파라메타: id
		String writer = request.getParameter("writer");
		
		//조회한 정보를 JSP전달.
		BoardService svc = new BoradServiceImpl();
		BoardVO mv = svc.getboard(Integer.parseInt(bno));

		request.setAttribute("modifyboard", );
		
		request.getRequestDispatcher("board/modifyboard.tiles").forward(request, response);

	}

}
