<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>게시글 상세정보</h3>
<%
BoardVO bd = (BoardVO) request.getAttribute("board");
%>
<table class="table">
	<tr>
		<th>글번호</th>
		<td><%=bd.getBoardNo()%></td>
	</tr>
	<tr>
		<th>제목</th>
		<td><%=bd.getTitle()%></td>
	</tr>
	<tr>
		<th>내용</th>
		<td><%=bd.getContent()%></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=bd.getWriter()%></td>
	</tr>
	<tr>
		<th>작성일시</th>
		<td><%=bd.getCreationDate()%></td>
	</tr>
	<tr>
		<!-- <td><button class="btn btn-info" onclick="history.back()">목록으로</button></td>  -->
		<td><a class="btn btn-info" href="boardList.do?page=${page }">목록으로</a>
			<a class="btn btn-success" href="boardList.do?page=${page }">수정</a> <a
			class="btn btn-danger" href="boardList.do?page=${page }">삭제</a></td>
	</tr>
</table>
<jsp:include page="../includes/footer.jsp"></jsp:include>