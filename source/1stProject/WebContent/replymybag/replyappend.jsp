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
		<table>
			<c:forEach items="${replymybagList }" var="replymybag">
						<table>
							<tr>
								<td>${replymybag.rId }</td>
								<td>${replymybag.mId }</td>
								<td class="left">
									<c:forEach var="n" begin="1"
										end="${replymybag.rIndent }">
										<c:if test="${n==replymybag.rIndent }">└</c:if>
										<c:if test="${n!=replymybag.rIndent }"> &nbsp; &nbsp; </c:if>
									</c:forEach> ${replymybag.rContent }
								</td>
							</tr>
							<tr>
								<td colspan="3">
									<button id="dap">답글</button>
								</td>
							</tr>
						</table>
			</c:forEach>
		</table>
		<input type="hidden" name="rpageNum" class="rpageNum" value="${rpageNum }">
	</c:if>
</body>
</html>