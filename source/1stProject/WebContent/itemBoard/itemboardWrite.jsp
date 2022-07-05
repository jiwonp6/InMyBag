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
	<link href="${conPath }/css/write.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<article class="write">
		<section>
		<form action="${conPath }/itemboardWrite.do" method="post" enctype="multipart/form-data">
			<div class="title">
				<h2>ITEM Q&A</h2>
			</div>
			<table>
				<tr class="pic">
					<td>이미지</td>
				</tr>
				<tr>
					<td><input type="text" name="iTitle" required="required" placeholder="Q : 제목을 입력해주세요" class="bName"></td>
				</tr>
				<tr>
					<td><textarea name="iContent" rows="5" cols="32" placeholder="내용을 입력해주세요"></textarea></td>
				</tr>
				<tr>
					<td><input type="file" name="iFilename"></td>
				</tr>
				<tr class="write">
					<td>
						<input type="submit" value="글쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/itemboardList.do'">
					</td>
				</tr>
			</table>
		</form>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>