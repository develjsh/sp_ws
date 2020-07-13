<%@page import="com.herbmall.member.model.MemberService"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../login/loginCheck.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>memberEdit_ok.jsp</title>
</head>
<body>
<jsp:useBean id="memVo" class="com.herbmall.member.model.MemberVO" 
	scope="page"></jsp:useBean>
<jsp:useBean id="memService" 
	class="com.herbmall.member.model.MemberService" 
	scope="session"></jsp:useBean>

<%
	//memberEdit.jsp에서 post방식으로 서브밋
	//1
	request.setCharacterEncoding("utf-8");
	String userid=(String)session.getAttribute("userid");
	
	String pwd=request.getParameter("pwd");
	String zipcode=request.getParameter("zipcode");
	String address=request.getParameter("address");
	String addressDetail=request.getParameter("addressDetail");
	String hp1=request.getParameter("hp1");
	String hp2=request.getParameter("hp2");
	String hp3=request.getParameter("hp3");
	String email1=request.getParameter("email1");
	String email2=request.getParameter("email2");
	String email3=request.getParameter("email3");
	
	//2
	String email="", hp="";
	if(hp2!=null && !hp2.isEmpty() && hp3!=null && !hp3.isEmpty()){
		hp=hp1+"-"+hp2+"-"+hp3;
	}
	
	if(email1!=null && !email1.isEmpty()){
		if(email2.equals("etc")){ //직접입력의 경우
			if(email3!=null && !email3.isEmpty()){
				email=email1+"@"+email3;
			}
		}else{
			email=email1+"@"+email2;			
		}
	}
	
	memVo.setAddress(address);
	memVo.setAddressDetail(addressDetail);
	memVo.setEmail(email);
	memVo.setHp(hp);
	memVo.setPwd(pwd);
	memVo.setUserid(userid);
	memVo.setZipcode(zipcode);
	
	String msg="회원정보 수정실패!", url="/member/memberEdit.jsp";
	try{
		int result=memService.loginCheck(userid, pwd);
		if(result==MemberService.LOGIN_OK){
			int cnt=memService.updateMember(memVo);
			
			if(cnt>0){
				msg="회원정보 수정되었습니다.";
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