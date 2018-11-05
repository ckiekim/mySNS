<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8" import="mysns.sns.*,mysns.member.*,java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 메시지 처리 빈즈 -->
<jsp:useBean id="msg" class="mysns.sns.Message" />
<jsp:useBean id="msgDao" class="mysns.sns.MessageDAO" />
<jsp:useBean id="reply" class="mysns.sns.Reply" />

<!-- 프로퍼티 set -->
<jsp:setProperty name="msg" property="*" />
<jsp:setProperty name="reply" property="*" />

<%
	// 기본 파라미터 정리
	String action = request.getParameter("action");	// 컨트롤러 요청 action 코드 값
	String cnt = request.getParameter("cnt");	// 다음 페이지 요청 카운트
	String suid = request.getParameter("suid");	// 특정 회원 게시물 only
	String home;		// 홈 URL
	int mcnt;			// 메시지 페이지 카운트
	
	if((cnt != null) && (suid !=null)) {
		// 각 action 처리후 메인으로 되돌아가기 위한 기본 url
		home = "sns_control.jsp?action=getall&cnt="+cnt+"&suid="+suid;
		mcnt = Integer.parseInt(cnt);
	} else {
		// 게시글 작성시에는 현재 상태와 상관 없이 전체 게시물의 첫페이지로 이동 하기 위한 url
		home = "sns_control.jsp?action=getall";
		// 첫페이지 요청인 경우, 기본 게시물 5개씩
		mcnt = 5;
	}
	
	// 댓글이 달린 게시물 위치 정보 -> accordion 상태 유지 목적
	request.setAttribute("curmsg", request.getParameter("curmsg"));

	switch(action) {
	case "newmsg":		// 새로운 메시지 등록
		if (msgDao.newMsg(msg))
			response.sendRedirect(home);
		else
			throw new Exception("메시지 등록 오류!!");
		break;
	case "newreply":	// 댓글 등록
		if (msgDao.newReply(reply))
			pageContext.forward(home);
		else
			throw new Exception("댓글 등록 오류!!");
		break;
	case "delmsg":		// 메시지 삭제
		if(msgDao.delMsg(msg.getMid())) 
			response.sendRedirect(home);			
		else
			throw new Exception("메시지 삭제 오류!!");;
		break;
	case "delreply":	// 댓글 삭제
		if(msgDao.delReply(reply.getRid()))
			pageContext.forward(home);
		else
			throw new Exception("댓글 삭제 오류!!");;
		break;
	case "getall":		// 전체 게시글 가져오기
		ArrayList<MessageSet> msgList = msgDao.getAll(mcnt, suid);
		MemberDAO memDao = new MemberDAO();
		ArrayList<Member> memberList = memDao.getNewMembers();

		request.setAttribute("msgList", msgList);		// 게시글 목록
		request.setAttribute("memberList", memberList);	// 신규 회원 목록
		request.setAttribute("suid", suid);	// 특정 회원 only인 경우 회원 uid 를 request scope에 저장
		request.setAttribute("cnt",mcnt);	// 현재 페이지 카운트 정보 저장

		pageContext.forward("sns_main.jsp");
		break;
	case "fav":			// 좋아요 추가
		msgDao.favorite(msg.getMid());
		pageContext.forward(home);
	}
%>
