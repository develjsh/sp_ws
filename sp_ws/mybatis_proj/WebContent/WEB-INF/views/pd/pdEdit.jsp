<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pdEdit.jsp</title>
</head>
<body>

<h1>상품 수정</h1>
<form name="frmWrite" method="post" 
	action="<c:url value='/pd/pdEdit.do'/>">
	<!-- 수정시  no가 필요하므로 hidden 필드에 넣어서 넘긴다 -->
	<input type="hidden" name="no" value="${param.no}">
	
	<label for="pdName">상품명</label>
	<input type="text" name="pdName" id="pdName" 
		value="${dto.pdName}"><br>
	<label for="price">가격</label>
	<input type="text" name="price" id="price"
		value="${dto.price}">
	<br><br>
	<input type="submit" value="수정">
	<input type="reset" value="취소">	
</form>
<hr>
<a href="<c:url value='/pd/pdList.do'/>">상품 목록</a>	

</body>
</html>