<%@page import="com.lec.mybag.dao.NoticeBoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<%
		for(int i=0 ; i<30 ; i++){
			NoticeBoardDao nDao = NoticeBoardDao.getInstance();
			if(i%3==1){
				nDao.writeNoticeBoard("AA"+i, "회원가입알림"+i, "전달"+i, null, "192.168.10."+i);			
			}else if(i%3==2){
				nDao.writeNoticeBoard("AA"+i, "로그인알림"+i, "알림"+i, null, "192.168.10."+i);			
			}else if(i%3==0){
				nDao.writeNoticeBoard("AA"+i, "회원탈퇴관련"+i, "알림", "notice.docx", "192.168.10."+i);			
			}	
		}
		response.sendRedirect("../noticeboardList.do");
	%>
</body>
</html>