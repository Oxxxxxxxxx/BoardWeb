<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<link rel="stylesheet" href="//cdn.datatables.net/2.1.5/css/dataTables.dataTables.min.css">
<script src="js/jquery-3.7.1.js"></script>
<script src="//cdn.datatables.net/2.1.5/js/dataTables.min.js"></script>

<h3>게시글 목록</h3>

<c:choose>
	<c:when test="${!empty message }">
		<p style="color: red;">"${message }"</p>
	</c:when>
	<c:otherwise>
		<table id="example" class="display" style="width:100%"> 
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일시</th>
				</tr>
			</thead>
			<tbody>
				<!-- for (BoardVO board : list) { -->
				<c:forEach var="board" items="${list }">
					<tr>
						<td><c:out value="${board.boardNo }" /></td>
						<td><a href="getBoard.do?page=${paging.page }&bno=${board.boardNo}&keyword=${kw }&searchCondition=${sc }">${board.title }</td>
						<td>${board.writer }</td>
						<td>${board.viewCnt }</td>
						<td><fmt:formatDate value="${board.creationDate}"
								pattern="yyyy.MM.dd HH:mm:ss" /></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성일시</th>
				</tr>
			</tfoot>
		</table>
		
	
	</c:otherwise>
	
</c:choose>

<script>
	new DataTable('#example');
</script>




<p>${'문자열, 숫자, boolean null' }</p>
<p>${3+5 > 10 ? '참입니다' : '거짓입니다.'}</p>

<%
String name = "hong";
%>

<c:set var="name" value="Hongkildong"></c:set>
<c:out value="">"${name }"</c:out>
<c:set var="age" value="20"></c:set>
<c:out value="${age >= 20 ? '성인' : '미성년' }"></c:out>

<c:if test="${name ne 'Hongkildong' }">
	<p>털렸습니다</p>
</c:if>

