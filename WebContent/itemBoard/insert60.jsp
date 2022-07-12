<%@page import="com.lec.mybag.dao.ItemBoardDao"%>
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
			ItemBoardDao iDao = ItemBoardDao.getInstance();
			if(i%5==1){
				iDao.writeItemBoard("aa"+i, "item"+i, "어디껀가요?", "camera.png", "192.168.10."+i);			
			}else if(i%5==2){
				iDao.writeItemBoard("aa"+i, "item"+i, "이거 어디꺼?", "sunny.png", "192.168.10."+i);			
			}else if(i%5==3){
				iDao.writeItemBoard("aa"+i, "item"+i, "어디서 샀나요?", "tripod.png", "192.168.10."+i);			
			}else if(i%5==4){
				iDao.writeItemBoard("aa"+i, "item"+i, "어디서 살수있나여?", "watch.png", "192.168.10."+i);			
			}else if(i%5==0){
				iDao.writeItemBoard("aa"+i, "item"+i, "이거?", "wallet.png", "192.168.10."+i);			
			}	
		}
		response.sendRedirect("../itemboardList.do");
	%>
</body>
</html>