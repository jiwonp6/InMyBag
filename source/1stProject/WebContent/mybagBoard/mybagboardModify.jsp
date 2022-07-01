<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/mybagboradModify.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="bId" value="${mybagboard.bId }"> 
			<input type="hidden" name="dbFilename" value="${mybagboard.bFilename }">
			<table>
				<caption>ITEM Q&A</caption>
				<tr>
					<td>제목</td>
					<td><input type="text" name="bName"
						value="${mybagboard.bName }" required="required" size="30"></td>
				</tr>
				<tr>
					<td>본문</td>
					<td><textarea name="bContent" rows="3" cols="32">${mybagboard.bContent }</textarea></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="bFilename" class="btn">
						 
						<c:if test="${not empty mybagboard.bFilename }">
							<p>원첨부파일 : </p>
							<p>
								<img src="${conPath }/mybagBoardFileUp/${mybagboard.bFilename}" width="250" height="300">
							</p>
							<a>${mybagboard.bFilename}</a>
						</c:if> 
						<c:if test="${empty mybagboard.bFilename }">
						 		원첨부파일 : 첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="button" value="목록" class="btn"
							onclick="location='${conPath}/main.do?pageNum=${param.pageNum }'">
						<input type="reset" value="이전" class="btn"
							onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>