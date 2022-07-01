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
	<link href="conPath/css/header.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
</head>
<body>
	<form action="${conPath }/replymybagReply.do" method="post">
			<!-- reply.do시 필요한 원글 정보 : rGroup, rStep, rIndent
			                              지금 입력할 답변글 : rId, rContent, rpageNum -->
			<input type="hidden" name="rGroup" value="${originReplyMyBag.rGroup }">
			<input type="hidden" name="rStep" value="${originReplyMyBag.rStep }">
			<input type="hidden" name="rIndent" value="${originReplyMyBag.rIndent }"> 
			<input type="hidden" name="rpageNum" value="${param.rpageNum }">
			<table>
				<caption>${originReplyMyBag.rId }번글의답변쓰기</caption>
				<tr>
					<td>${member.mId }</td>
					<td><textarea name="iContent" rows="3" cols="32"></textarea></td>
					<td><input type="submit" value="답변쓰기" class="btn"></td>
				</tr>
			</table>
		</form>
</body>
</html>