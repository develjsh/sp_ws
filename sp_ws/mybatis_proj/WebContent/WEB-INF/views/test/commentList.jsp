<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test/commentList.jsp</title>
</head>
<body>
	<h1>comment 목록</h1>
	<table border="1">
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>작성일</th>
			<th>내용</th>
		</tr>
		
		<c:forEach var="vo" items="${list }">
			<tr>
				<td>${vo.commentNo }</td>
				<td>${vo.userId }</td>
				<td>${vo.regDate }</td>
				<td>${vo.commentContent }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>