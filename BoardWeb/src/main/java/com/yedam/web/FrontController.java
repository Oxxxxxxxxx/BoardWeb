package com.yedam.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.control.AddFormControl;
import com.yedam.control.AddMemberControl;
import com.yedam.control.GetmemberControl;
import com.yedam.control.IntroControl;
import com.yedam.control.MainControl;
import com.yedam.control.MemberListControl;
import com.yedam.control.ModFormControl;
import com.yedam.control.ModifyMemberControl;
import com.yedam.control.SubControl;

@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	// url pattern - 실행되는 기능 -> map 컬렉션에 지정
	Map<String, Control> map;
	
	public FrontController(){
		System.out.println("FrontController 생성자.");
		map = new HashMap<>();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메소드.");
		map.put("/main.do", new MainControl());
		map.put("/sub.do", new SubControl());
		map.put("/intro.do", new IntroControl());
		
		//기능등록.
		map.put("/addForm.do", new AddFormControl()); //회원등록페이지.
		map.put("/addMember.do", new AddMemberControl()); //회원등록처리
		map.put("/memberList.do", new MemberListControl());
		map.put("/getMember.do", new GetmemberControl()); // 회원아이디로 상세조회
		map.put("/modifyForm.do", new ModFormControl()); //수정화면 호출
		map.put("/modifyMember.do", new ModifyMemberControl()); //수정화면
	}
	// HttpServletRequest
	// HttpServletResponse
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); // 요청방식이 POST 일 경우에 body포함된 문자열 인코딩.
		
		System.out.println("service 메소드.");
		String uri = req.getRequestURI(); //http://localhost/BoardWeb/main.do
		String context = req.getContextPath(); // /BoardWeb
		String page = uri.substring(context.length());
		System.out.println(page);
		
		Control control = map.get(page);
		control.exec(req, resp);
		
	}
}
