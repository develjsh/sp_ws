<%@page import="com.herbmall.member.model.MemberVO"%>
<%@page import="com.herbmall.member.model.MemberService"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:useBean id="memService" class="com.herbmall.member.model.MemberService" 
	scope="session"></jsp:useBean>
<body>
	<%
		//login.jsp에서 post방식으로 서브밋
		//=> 파라미터인 아이디, 비밀번호 이용해서 로그인 처리
		//=> 로그인 성공하면 세션에 아이디 저장, 쿠키에 아이디 저장
		//1
		request.setCharacterEncoding("utf-8");
		String userid=request.getParameter("userid");
		String pwd=request.getParameter("pwd");
		String saveId=request.getParameter("saveId");
		
		//2
		String msg="로그인 처리 실패!", url="/login/login.jsp";
		try{
			int result=memService.loginCheck(userid, pwd);
			if(result==MemberService.LOGIN_OK){ //로그인 성공
				MemberVO vo=memService.selectMember(userid);
			
				//[1] 세션에 저장
				session.setAttribute("userid", userid);
				session.setAttribute("userName", vo.getName());
				
				//[2] 쿠키에 저장
				if(saveId!=null){ //체크한 경우-on
					Cookie ck = new Cookie("ck_userid", userid);
					ck.setMaxAge(1000*24*60*60); //쿠키 유효기간 : 1000일
					ck.setPath("/"); //지정한 경로와 하위 경로에 쿠키 전송
					response.addCookie(ck);					
				}else{//체크하지 않은 경우-null
					Cookie ck = new Cookie("ck_userid", userid);
					ck.setMaxAge(0); //쿠키 유효기간 : 0 => 쿠키 제거됨
					ck.setPath("/"); //지정한 경로와 하위 경로에 쿠키 전송
					response.addCookie(ck);
				}
				
				msg=vo.getName()+ "님 로그인되었습니다.";
				url="/index.jsp";
			}else if(result==MemberService.PWD_DISAGREE){
				msg="비밀번호가 일치하지 않습니다.";
			}else if(result==MemberService.ID_NONE){
				msg="해당 아이디가 존재하지 않습니다.";				
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





