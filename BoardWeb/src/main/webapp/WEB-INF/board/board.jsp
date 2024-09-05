<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="mt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<td colspan="2" rowspan="3"><c:if test="${!empty board.image }">
				<img width="1000px" src="images/${board.image }">
			</c:if></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td><%=bd.getWriter()%></td>
	</tr>
	<tr>
		<th>작성일시</th>
		<td><%=bd.getCreationDate()%></td>
	</tr>

</table>

<form action="removeBoard.do" name="actForm">
	<input type="hidden" name="keyword" value="${kw }"> <input
		type="hidden" name="searchCondition" value="${sc }"> <input
		type="hidden" name="page" value="${page }"> <input
		type="hidden" name="bno" value="${board.boardNo }">
</form>
<!-- <td><button class="btn btn-info" onclick="history.back()">목록으로</button></td>  -->
<a class="btn btn-info" onclick="form_submit('boardList.do')">목록으로</a>
<a class="btn btn-success ${board.writer ne logid ? 'disabled' : '' }"
	href="modifyBoard.do?page=${page }">수정</a>
<a class="btn btn-danger" onclick="form_submit('removeBoard.do')">삭제</a>
<c:if test="${!empty message }">
	<span style="color: red;">${message }</span>
</c:if>

<script>
//매개값으로 이동할 컨트롤을 받아서 파라미터를 전달.
	function form_submit(uri){
		document.forms.actForm.action = uri;
		document.forms.actForm.submit();
	}
</script>

<jsp:include page="../includes/footer.jsp"></jsp:include>