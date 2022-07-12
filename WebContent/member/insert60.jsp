<%@page import="com.lec.mybag.dto.MemberDto"%>
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
		MemberDao mDao = MemberDao.getInstance();
		HttpSession httpSession = request.getSession();
		String mId = "aa" + i;
		String mPw = "111";
		String mName = "aa" + i + "양";
		Date mBirth = null;
		String mEmail = "aa" + i + "@aa.com";
		MemberDto member = new MemberDto(mId, mPw, mName, mBirth, mEmail, null);
		mDao.joinMember(member);
	}
	response.sendRedirect("../loginView.let");
	%>
</body>
</html>