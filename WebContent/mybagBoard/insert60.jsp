<%@page import="com.lec.mybag.dao.MyBagBoardDao"%>
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
			MyBagBoardDao bDao = MyBagBoardDao.getInstance();
			if(i%5==1){
				bDao.writeMyBagBoard("aa"+i, "myBag"+i, "가방 : zara", "mybag1.jpg", "192.168.10."+i);			
			}else if(i%5==2){
				bDao.writeMyBagBoard("aa"+i, "myBag"+i, "가방 : zara", "mybag2.jpg", "192.168.10."+i);			
			}else if(i%5==3){
				bDao.writeMyBagBoard("aa"+i, "myBag"+i, "가방 : zara", "mybag3.jpg", "192.168.10."+i);			
			}else if(i%5==4){
				bDao.writeMyBagBoard("aa"+i, "myBag"+i, "가방 : zara", "mybag4.jpg", "192.168.10."+i);			
			}else if(i%5==0){
				bDao.writeMyBagBoard("aa"+i, "myBag"+i, "가방 : zara", "mybag5.jpg", "192.168.10."+i);			
			}	
		}
		response.sendRedirect("../main.do");
	%>
</body>
</html>