<%@page import="com.lec.mybag.dto.AdminDto"%>
<%@page import="com.lec.mybag.dao.AdminDao"%>
<%@page import="java.sql.Date"%>
<%@page import="com.lec.mybag.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		for (int i = 0; i < 60; i++) {
		AdminDao aDao = AdminDao.getInstance();
		HttpSession httpSession = request.getSession();
		String aId = "AA" + i;
		String aPw = "111";
		String aName = "AA" + i;
		AdminDto admin = new AdminDto(aId, aPw, aName, 0) ;
		aDao.joinAdmin(admin);
	}
	response.sendRedirect("../loginView.let");
	%>
</body>
</html>