package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class ModifyMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 파라메타 4개 값을 읽어서 db반영 -> 목록으로 이동.
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id"); // 사용자의 요청정보중에서 id값을 읽도록 함.
		String name = request.getParameter("name");
		String pw = request.getParameter("pass");
		String mail = request.getParameter("email"); // input태그의 name속성

		MemberVO mvo = new MemberVO();
		mvo.setMemberId(id);
		mvo.setMemberName(name);
		mvo.setPassword(pw);
		mvo.setEmail(mail);

		MemberService svc = new MemberServiceImpl();
		
		//회원등록이 정상적일 경우 => 회원목록 페이지 출력.
		//회원등록이 비정상적일 경우 => 회우너등록 페이지 이동 (msg 전달)
		//현재 페이지 : addmember.do -> 페이지 재지정 방식 1)forwarding: 파라메타전달 2)redirect :파라메타 불가
		if (svc.modifyMember(mvo)) {
			response.sendRedirect("memberList.do");
		}
		else {
			request.setAttribute("message", "등록중에 오류가 있습니다.");
			request.getRequestDispatcher("html/modifyForm.tiles")
			.forward(request, response);
		}
	}

}
