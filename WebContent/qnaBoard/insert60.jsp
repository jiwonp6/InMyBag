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
				qDao.writeQnaBoard("aa"+i, "회원가입질문"+i, "질문"+i, null, "192.168.10."+i);			
			}else if(i%5==2){
				qDao.writeQnaBoard("aa"+i, "로그인질문"+i, "질문"+i, null, "192.168.10."+i);			
			}else if(i%5==3){
				qDao.writeQnaBoard("aa"+i, "회원탈퇴질문"+i, "질문", "qna.docx", "192.168.10."+i);			
			}else if(i%5==4){
				qDao.writeQnaBoard("aa"+i, "고객센터질문"+i, "질문"+i, null, "192.168.10."+i);			
			}else if(i%5==0){
				qDao.writeQnaBoard("aa"+i, "글쓰기질문"+i, "질문"+i, null, "192.168.10."+i);			
			}	
		}
		response.sendRedirect("../qnaboardList.do");
	%>
</body>
</html>