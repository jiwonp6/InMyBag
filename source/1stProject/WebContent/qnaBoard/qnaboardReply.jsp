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
	<style>
	#content_form {
		width: 800px;
		height: 400px;
		margin: 100px auto 0px;
	}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function() {
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/qnaboardReply.do" method="post" enctype="multipart/form-data">
			<!-- reply.do시 필요한 원글 정보 : qGroup, qStep, qIndent
			                              지금 입력할 답변글 : qId, qTitle, qContent, pageNum -->
			<input type="hidden" name="qGroup" value="${originQnaBoard.qGroup }">
			<input type="hidden" name="qStep" value="${originQnaBoard.qStep }">
			<input type="hidden" name="qIndent" value="${originQnaBoard.qIndent }"> 
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<table>
				<caption>${originQnaBoard.qId }번글의답변쓰기 폼</caption>
				<tr>
					<td>제목</td>
					<td><input type="text" name="qTitle" required="required"
						size="30"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="qContent" rows="3" cols="32"></textarea></td>
				</tr>
				<tr>
					<td>첨부파일</td>
					<td><input type="file" name="qFilename"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="답변쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/qnaboardList.do'">
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>