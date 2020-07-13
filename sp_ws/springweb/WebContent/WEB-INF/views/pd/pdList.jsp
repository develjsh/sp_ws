<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pdList.jsp</title>
</head>
<body>

<h1>상품 목록</h1>
<table border="1" style="width:500px">
	<tr>
		<th>번호</th>
		<th>상품명</th>
		<th>가격</th>
		<th>등록일</th>
	</tr>
	<c:if test="${empty list }">
		<tr>
			<td colspan="4">상품이 없습니다.</td>
		</tr>
	</c:if>
	<c:if test="${!empty list }">	
		<!-- 반복 시작 -->
		<c:forEach var="dto" items="${list }">
			<tr>
				<td>${dto.no}</td>
				<td><a href
					 ="<c:url value='/pd/pdDetail.do?no=${dto.no}'/>">
					${dto.pdName}</a></td>
				<td style="text-align: right">
					<fmt:formatNumber value="${dto.price}"
						pattern="#,###"/>원</td>
				<td><fmt:formatDate value="${dto.regdate}"
					pattern="yyyy-MM-dd"/> </td>
			</tr>
		</c:forEach>
		<!-- 반복 끝 -->
	</c:if>
</table>
<hr>
<a href="<c:url value='/pd/pdWrite.do'/>">상품 등록</a>

</body>
</html>




