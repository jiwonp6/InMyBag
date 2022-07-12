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
	<link href="${conPath }/css/write2.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<article class="write">
	<section>
		<form action="${conPath }/faqboardWrite.do" method="post" enctype="multipart/form-data">
			<div class="title">
				<h2> FAQ </h2>
			</div>		
			<table>
				<tr>
					<td class="id">제목</td>
					<td><input type="text" name="fTitle" class="title" required="required" size="30"></td>
				</tr>
				<tr>
					<td class="id">본문</td>
					<td><textarea name="fContent" rows="3" cols="32"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;"><input type="file" name="fFilename"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="글쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/faqboardList.do'">
					</td>
				</tr>
			</table>
		</form>
	</section>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>