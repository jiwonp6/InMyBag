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
	<link href="${conPath }/css/join.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style>
		p.textpool{
			text-align: center;
			line-height: 100px;
		}
		td.btn{
			text-align: center;
			padding:10px;
		}
	</style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<c:if test="${not empty param.mId }">
		<form action="${conPath }/withdrawal.let" method="post">
			<input type="hidden" name="mId" value="${param.mId }">
			<table>
				<tr>
					<td>
						<fieldset>
							<legend>탈퇴처리</legend>	
							<p class="textpool">
								인위적으로 회원 ${param.mId } 을(를) 탈퇴시키겠습니까?	
							</p>
							<br>
							<p class="agree">동의함<input type="checkbox" required="required" > &nbsp; &nbsp; </p>				
						</fieldset>
					</td>
				</tr>
				<tr>
					<td class="btn" colspan="2">
						<input type="submit" value="회원탈퇴" class="btn">
						<input type="button" value="이전" class="btn" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</c:if>
	<c:if test="${not empty param.aId }">
		<form action="${conPath }/adminwithdrawal.let" method="post">
			<input type="hidden" name="aId" value="${param.aId }">
			<table>
				<tr>
					<td>
						<fieldset>
							<legend>탈퇴처리</legend>	
							<p class="textpool">
								인위적으로 관리자 ${param.aId } 을(를) 탈퇴시키겠습니까?	
							</p>
							<br>
							<p class="agree">동의함<input type="checkbox" required="required" > &nbsp; &nbsp; </p>				
						</fieldset>
					</td>
				</tr>
				<tr>
					<td class="btn" colspan="2">
						<input type="submit" value="관리자탈퇴" class="btn">
						<input type="button" value="이전" class="btn" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</c:if>
<jsp:include page="../main/footer.jsp" />
</body>
</html>