package com.yedam.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.common.PageDTO;
import com.yedam.common.SearchDTO;
import com.yedam.service.BoardService;
import com.yedam.service.BoradServiceImpl;
import com.yedam.vo.BoardVO;


public class BoardListControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page == null ? "1" : page;
		
		//검색조건 파라메타.
		String sc = request.getParameter("searchCondition");
		String kw = request.getParameter("keyword");
		
		if(sc == null || kw == null || sc.isEmpty() || kw.isEmpty()) {
			request.setAttribute("message", "검색조건을 입력하세요");
		}
		else {
			SearchDTO search = new SearchDTO();
			search.setSearchCondition(sc); //T,W,TW 
			search.setKeyword(kw); // Java, html
			search.setPage(Integer.parseInt(page));
			
			BoardService svc = new BoradServiceImpl();
			List<BoardVO> list = svc.boardList(search);
			request.setAttribute("list", list);
			
			//페이징 처리를 위한 기능.
			int totalCnt = svc.getTotalCnt(search);
			PageDTO paging = new PageDTO(Integer.parseInt(page), totalCnt);
			request.setAttribute("paging", paging);
			
			//jsp페이지에 전달.
			request.setAttribute("sc", sc);
			request.setAttribute("kw", kw);
			
			
		} // end of if.
		
		
		RequestDispatcher rd =  request.getRequestDispatcher("WEB-INF/board/boardList.jsp");
		rd.forward(request, response);
	}

}
