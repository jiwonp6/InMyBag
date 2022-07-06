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
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/qnaboardreplyModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="qId" value="${qnaboard.qId }"> 
			<input type="hidden" name="dbFilename" value="${qnaboard.qFilename }">
			<table>
				<caption>QnaBoard 수정</caption>
				<tr>
					<td>제목</td>
					<td><input type="text" name="qTitle"
						value="${qnaboard.qTitle }" required="required" size="30"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="qContent" rows="3" cols="32">${qnaboard.qContent }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="qFilename" class="btn">
						 
						<c:if test="${not empty qnaboard.qFilename }">
							<p>원첨부파일 : </p>
							<p>
								<img src="${conPath }/qnaBoardFileUp/${qnaboard.qFilename}" width="250" height="300">
							</p>
							<a>${qnaboard.qFilename}</a>
						</c:if> 
						<c:if test="${empty qnaboard.qFilename }">
						 		원첨부파일 : 첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="reset" value="이전" class="btn" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>