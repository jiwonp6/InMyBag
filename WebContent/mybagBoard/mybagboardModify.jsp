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
	<link href="${conPath }/css/write.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<article class="write">
		<section>
		<form action="${conPath }/mybagboradModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="bId" value="${mybagboard.bId }"> 
			<input type="hidden" name="dbFilename" value="${mybagboard.bFilename }">
			<div class="title">
				<h2>WHAT'S IN MY BAG 수정</h2>
			</div>
			<table>
				<tr class="pic">
					<td><img src="${conPath }/qnaBoardFileUp/${qnaboard.qFilename}" style="width: 280px; height: 300px; border-radius:20px; padding:5px;"></td>
				</tr>
				<tr>
					<td><input type="text" name="bName" class="title" value="${mybagboard.bName }" required="required" size="30"></td>
				<tr>
				<tr>
					<td><textarea name="bContent" rows="5" cols="32">${mybagboard.bContent }</textarea></td>
				</tr>
				<tr>
					<td style="font-size: 13px;">
						<c:if test="${not empty mybagboard.bFilename }">
							원첨부파일 : <a>${mybagboard.bFilename}</a>
						</c:if> 
						<c:if test="${empty mybagboard.bFilename }">
						 	원첨부파일 : 첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr>
					<td style="padding-top:10px;"><input type="file" name="bFilename"></td>
				</tr>
				<tr class="write">
					<td>
						<input type="submit" value="수정" class="btn">
						<input type="reset" value="이전" class="btn" onclick="history.back()">
					</td>
				</tr>
			</table>
		</form>
	</section>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>