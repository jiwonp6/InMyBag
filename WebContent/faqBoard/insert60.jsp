<%@page import="com.lec.mybag.dao.FaqBoardDao"%>
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
			FaqBoardDao fDao = FaqBoardDao.getInstance();
			if(i%5==1){
				fDao.writeFaqBoard("AA"+i, "회원가입방법"+i, "회원가입방법입니다", null, "192.168.10."+i);			
			}else if(i%5==2){
				fDao.writeFaqBoard("AA"+i, "로그인이 안될때"+i, "로그인방법입니다", null, "192.168.10."+i);			
			}else if(i%5==3){
				fDao.writeFaqBoard("AA"+i, "회원탈퇴시"+i, "회원탈퇴시", "drawal.docx", "192.168.10."+i);			
			}else if(i%5==4){
				fDao.writeFaqBoard("AA"+i, "고객센터에문의주세요"+i, "고객센터로", null, "192.168.10."+i);			
			}else if(i%5==0){
				fDao.writeFaqBoard("AA"+i, "질문게시판에 글쓰는 방법"+i, "질문", null, "192.168.10."+i);			
			}	
		}
		response.sendRedirect("../faqboardList.do");
	%>
</body>
</html>