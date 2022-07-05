<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="conPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/noticeBoardContent.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<article>
		<section class="content">
			<div class="boardId">
				<hr>
				<h3>QnA ${qnaboard.qId }. ${qnaboard.qTitle }</h3>
				<hr>
			</div>
			<table>
				<tr class="boardwriter">
					<td>
						작성자 : 
						<c:if test="${not empty qnaboard.mId }">
							${qnaboard.mId }
						</c:if>
						<c:if test="${not empty qnaboard.aId }">
							관리자
						</c:if>
					</td>
				</tr>
				<tr class="content">
					<td><pre>${qnaboard.qContent}</pre></td>
				</tr>
				<tr class="file">
					<td>
						<c:if test="${not empty qnaboard.qFilename }">
							<a href="${conPath }/qnaBoardFileUp/${qnaboard.qFilename}" target="_blank">${qnaboard.qFilename}</a>
						</c:if>
						<c:if test="${empty qnaboard.qFilename }">
							첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr class="btn">
					<td>
						<c:if test="${empty member and not empty admin}">
							<button onclick="location='${conPath}/qnaboardReplyView.do?qId=${qnaboard.qId }&pageNum=${param.pageNum }'">REPLY</button>
						</c:if>
					</td>
				</tr>
				<tr class="btn">
					<td>
						<input type="button" value="LIST" class="btn" onclick="location='${conPath}/qnaboardList.do?pageNum=${param.pageNum }'">
					</td>
				</tr>
			</table>
		</section>
	</article>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>