<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>pdDetail.jsp</title>
<script type="text/javascript" 
	src="<c:url value='/js/jquery-3.5.1.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$("#del").click(function(){
			if(confirm('삭제하시겠습니까?')){
				location.href
			="<c:url value='/pd/pdDelete.do?no=${param.no}'/>";
			}	
		});
		
	});
	
</script>
</head>
<body>

	<h1>상품 상세보기</h1>
	<p>번호 : ${param.no}</p>
	<p>상품명 : ${pdDto.pdName}</p>
	<p>가격 : <fmt:formatNumber value='${pdDto.price}' pattern="#,###"/>
		원</p>
	<p>등록일 : ${pdDto.regdate}</p>
	<br><br>
	<a href="<c:url value='/pd/pdList.do'/>">목록</a>
	<a href="<c:url value='/pd/pdEdit.do?no=${param.no}'/>">
		수정</a>
	<a href="#" id="del">삭제</a>
	
</body>
</html>




