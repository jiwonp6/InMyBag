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
	<link href="${conPath }/css/login.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
	<c:if test="${not empty joinErrorMsg }">
		<script>
			alert('${joinErrorMsg}');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty joinResult }">
		<script>
			alert('${joinResult}');
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/adminlogin.let" method="post">
			<table>
				<tr class="title">
					<td><h1>LOGIN</h1>(관리자모드)</td>
				</tr>
				<tr>
					<td><input type="text" name="aId" value="${aId }" required="required" 
								placeholder="아이디" 
								style="width:400px; height:50px; font-size:15px; border:0.5px solid lightgray;">
					</td>
				</tr>
				<tr>
					<td><input type="password" name="aPw" required="required"
								placeholder="비밀번호" style="width:400px; height:50px; font-size:15px; border:0.5px solid lightgray;">
					</td>
				</tr>
				<tr><td>&nbsp;</td></tr>
				<tr><td>&nbsp;</td></tr>
				<tr>
					<td>
						<input type="submit" value="LOGIN" class="btn" style="height:30px;">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>