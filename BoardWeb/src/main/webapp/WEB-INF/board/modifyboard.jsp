<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>회원수정화면</h3>

<form action="modifyBoard.do">
	<input type="hidden" name="writer" value="${board.writer }">
	<table class="table">
		<tr>
			<th>게시글 제목</th>
			<td><input type="text" name="title"
				value="${board.title }"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>"${board.writer }"</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><input type="text" name="content"
				value="${board.content }"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="submit" class="btn btn-primary">저장</button>
				<button class="btn btn-secondary">취소</button>
			</td>
		</tr>
	</table>
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>