<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../includes/header.jsp"></jsp:include>
<h3>로그인 화면</h3>

<c:if test="${!empty message }">
	<p style="color: red;"><c:out value="${message }" /></p>
</c:if>

<form action="login.do" method="post">
	<table class="table">
		<tr>
			<th>아이디</th>
			<th><input type="text" name="id"></th>
		</tr>
		<tr>
			<th>비밀번호</th>
			<th><input type="password" name="pw"></th>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="로그인"></td>
		</tr>
	</table>
</form>
<jsp:include page="../includes/footer.jsp"></jsp:include>