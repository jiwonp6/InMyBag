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
	<c:if test="${empty error }">
			<c:forEach items="${replymybagList }" var="replymybag">
				<table class="reply">
					<tr>
						<th class="replyreply_mId"> ${replymybag.mId } </th>
					</tr>
					<tr>
						<td class="left">
							<c:forEach var="n" begin="1" end="${replymybag.rIndent }">
							<c:if test="${n==replymybag.rIndent }">└</c:if>
							<c:if test="${n!=replymybag.rIndent }"> &nbsp; &nbsp; </c:if>
							</c:forEach> ${replymybag.rContent }
						</td>
					</tr>
				</table>
			</c:forEach>
		<input type="hidden" name="rpageNum" class="rpageNum" value="${rpageNum }">
	</c:if>
</body>
</html>