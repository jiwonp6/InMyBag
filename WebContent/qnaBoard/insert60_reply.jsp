<%@page import="com.lec.mybag.dao.QnaBoardDao"%>
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
		for(int i=0 ; i<60 ; i++){
			QnaBoardDao qDao = QnaBoardDao.getInstance();
			if(i%5==1){
				qDao.replyQnaBoard("AA"+i, "답변"+i, "답변입니다", null, "192.168.10."+i, i, 1, 1);			
			}else if(i%5==3){
				qDao.replyQnaBoard("AA"+i, "답변임"+i, "답변입니다", "reply.docx", "192.168.10."+i, i, 1, 1);				
			}	
		}
		response.sendRedirect("../qnaboardList.do");
	%>
</body>
</html>