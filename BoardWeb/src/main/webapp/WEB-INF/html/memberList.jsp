<%@page import="com.yedam.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.service.MemberService"%>
<%@page import="com.yedam.service.MemberServiceImpl"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// jsp -> 서블릿으로 변환되어져서 실행.
// memberList.jsp -> memberList_jsp.java -> memberList_jsp.class 실행
List<MemberVO> list = (List<MemberVO>) request.getAttribute("memberList");
%>
<h3>회원 목록</h3>
<table class="table">
	<thead>
		<tr>
			<th>회원 아이디</th>
			<th>회원 이름</th>
			<th>이메일</th>
			<th>권한</th>
		</tr>
	</thead>
	<tbody>
		<%
		for (MemberVO member : list) {
		%>
		<tr>
			<td><a href="getMember.do?id=<%=member.getMemberId()%>"><%=member.getMemberName()%></a></td>
			<td><%=member.getMemberName()%></td>
			<td><%=member.getEmail()%></td>
			<td><%=member.getAuthority()%></td>
		</tr>
		<%
		}
		%>
	</tbody>
</table>
