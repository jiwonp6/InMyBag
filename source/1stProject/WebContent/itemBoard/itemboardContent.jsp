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
	<link href="${conPath }/css/mybagBoardContent.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<article class="content">
		<section>
			<div class="boardId">
				<hr>
				<h3>No.${itemboard.iId }(${itemboard.mId})</h3>
				<hr>
			</div>
			<div class="pic">
				<c:if test="${not empty itemboard.iFilename }">
					<p><img src="${conPath }/itemBoardFileUp/${itemboard.iFilename}" width="240" height="300"></p>
				</c:if>
				<c:if test="${empty itemboard.iFilename }">
					<p><img src="${conPath }/itemBoardFileUp/noneImg.png" width="240" height="300"></p>
				</c:if>
			</div>
			<section class="table">
				<table class="content">
					<tr>
						<td style="font-weight: bold">Q : ${itemboard.iTitle }</td>
					</tr>
					<tr>
						<td><div class="bonmun"><pre>${itemboard.iContent}</pre></div></td>
					</tr>
					<tr>
						<td style="text-align: right; font-size: 10px;">
							<c:if test="${not empty itemboard.iFilename }">
								<a href="${conPath }/itemBoardFileUp/${itemboard.iFilename}" target="_blank">${itemboard.iFilename}</a>
							</c:if>
							<c:if test="${empty itemboard.iFilename }">
								 첨부파일없음
							</c:if>
						</td>
					</tr>
					<tr>
						<td class="btn" colspan="2">
						 	<button class="btn" onclick="location='${conPath}/itemboardReplyView.do?iId=${itemboard.iId }&pageNum=${param.pageNum }'">REPLY</button>
						 	<input type="button" value="LIST" class="btn" onclick="location='${conPath}/itemboardList.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
				</table>
			</section>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>