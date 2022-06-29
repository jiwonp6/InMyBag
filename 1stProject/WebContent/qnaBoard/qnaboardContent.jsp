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
	<link href="${conPath }/css/itemboardContent.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<article class="content">
		<section>
			<section class="table">
				<table>
					<tr>
						<td colspan="2">${qnaboard.qId }번 글</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>
							<c:if test="${not empty qnaboard.mId }">
								${qnaboard.mId }
							</c:if>
							<c:if test="${not empty qnaboard.aId }">
								관리자
							</c:if>
						</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${qnaboard.qTitle }</td>
					</tr>
					<tr>
						<td>본문</td>
						<td><pre>${qnaboard.qContent}</pre></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<c:if test="${not empty qnaboard.qFilename }">
								<p><img src="${conPath }/qnaBoardFileUp/${qnaboard.qFilename}" width="240" height="300"></p>
								<a href="${conPath }/qnaBoardFileUp/${qnaboard.qFilename}" target="_blank">${qnaboard.qFilename}</a>
							</c:if>
							<c:if test="${empty qnaboard.qFilename }">
								 첨부파일없음
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<c:if test="${empty member and not empty admin}">
						 		<button onclick="location='${conPath}/qnaboardReplyView.do?qId=${qnaboard.qId }&pageNum=${param.pageNum }'">REPLY</button>
						 	</c:if>
						 	<input type="button" value="LIST" class="btn"
						 		onclick="location='${conPath}/qnaboardList.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
				</table>
			</section>
		</section>
	</article>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>