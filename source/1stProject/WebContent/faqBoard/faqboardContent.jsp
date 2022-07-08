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
				<h3>FAQ ${faqboard.fId }. ${faqboard.fTitle }</h3>
				<hr>
			</div>
			<table>
				<tr class="boardwriter">
					<td>작성자 : 관리자</td>
				</tr>
				<tr class="content">
					<td>${faqboard.fContent }</td>
				</tr>
				<tr class="file">
					<td>
						<c:if test="${not empty faqboard.fFilename }">
							<a href="${conPath }/faqBoardFileUp/${faqboard.fFilename }" target="_blank">${faqboard.fFilename }</a>
						</c:if>
						<c:if test="${empty faqboard.fFilename }">
							 첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr class="btn">
					<td>
					 	<c:if test="${not empty admin }">
					 		<button class="btn" onclick="location='${conPath}/faqboardModifyView.do?fId=${faqboard.fId }&pageNum=${param.pageNum }'">수정</button>
					 		<button class="btn" onclick="location='${conPath}/faqboardDelete.do?fId=${faqboard.fId }&pageNum=${param.pageNum }'">삭제</button>
				 		</c:if>
					 	<input type="button" value="LIST" class="btn" onclick="location='${conPath}/faqboardList.do?pageNum=${param.pageNum }'">
					</td>
				</tr>
			</table>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>