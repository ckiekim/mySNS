<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="mysns.member.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="member" class="mysns.member.Member"/>
<jsp:setProperty name="member" property="*"/>
<jsp:useBean id="memDao" class="mysns.member.MemberDAO"/>

<%
	String action = request.getParameter("action");	// 컨트롤러 요청 action 코드값
	switch (action) {
	case "new":			// 신규 회원등록
		if(memDao.addMember(member))
			out.println("<script>alert('정상적으로 등록 되었습니다. 로그인 하세요!!');opener.window.location.reload();window.close();</script>");
		else
			out.println("<script>alert('같은 아이디가 존재 합니다!!');history.go(-1);</script>");
		break;
	case "login":		// 로그인
		if(memDao.login(member.getUid(), member.getPasswd())) {
			// 로그인 성공시 세션에 "uid" 저장
			session.setAttribute("uid", member.getUid());
			Member m = memDao.getOneMember(member.getUid());
			session.setAttribute("name", m.getName());
			response.sendRedirect("sns_control.jsp?action=getall");
		} else {
			out.println("<script>alert('아이디나 비밀번호가 틀렸습니다.!!');history.go(-1);</script>");
		}
		break;
	case "logout":
		session.removeAttribute("uid");		// 컨트롤러 요청 action 코드값
		session.removeAttribute("name");
		response.sendRedirect("sns_control.jsp?action=getall");	
	}
%>