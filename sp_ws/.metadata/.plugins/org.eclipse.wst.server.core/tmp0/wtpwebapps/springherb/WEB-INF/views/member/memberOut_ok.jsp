<%@page import="java.sql.SQLException"%>
<%@page import="com.herbmall.member.model.MemberService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../login/loginCheck.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberOut_ok.jsp</title>
</head>
<body>
<jsp:useBean id="memService" 
	class="com.herbmall.member.model.MemberService" 
	scope="session"></jsp:useBean>

<%
	//memberOut.jsp에서 post방식으로 서브밋
	//1
	request.setCharacterEncoding("utf-8");
	String userid=(String)session.getAttribute("userid");	
	String pwd=request.getParameter("pwd");
	
	//2
	String msg="회원탈퇴 실패!", url="/member/memberOut.jsp";
	try{
		int result=memService.loginCheck(userid, pwd);
		if(result==MemberService.LOGIN_OK){
			int cnt=memService.deleteMember(userid);
			
			if(cnt>0){
				msg="회원탈퇴 처리되었습니다.";
				url="/index.jsp";
				
				session.invalidate();
				
				Cookie ck = new Cookie("ck_userid", userid);
				ck.setMaxAge(0); //쿠키 유효기간 : 0 => 쿠키 제거됨
				ck.setPath("/"); //지정한 경로와 하위 경로에 쿠키 전송
				response.addCookie(ck);
			}
		}else if(result==MemberService.PWD_DISAGREE){
			msg="비밀번호가 일치하지 않습니다.";
		}
	}catch(SQLException e){
		e.printStackTrace();
	}
	
	//3
	request.setAttribute("msg", msg);
	request.setAttribute("url", url);	
%>

<jsp:forward page="../common/message.jsp"></jsp:forward>
</body>
</html>