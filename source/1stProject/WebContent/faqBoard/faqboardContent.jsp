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
	<style></style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<article class="content">
		<section>
			<div>
				<c:if test="${not empty faqboard.fFilename }">
				</c:if>
			</div>
			<section class="table">
				<table>
					<tr>
						<td colspan="2">${faqboard.fId }번 글</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>관리자</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${faqboard.fTitle }</td>
					</tr>
					<tr>
						<td>본문</td>
						<td><div class="bonmun"><pre>${faqboard.fContent }</pre></div></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<c:if test="${not empty faqboard.fFilename }">
								<p><img src="${conPath }/faqBoardFileUp/${faqboard.fFilename }" width="240" height="300"></p>
								<a href="${conPath }/faqBoardFileUp/${faqboard.fFilename }" target="_blank">${faqboard.fFilename }</a>
							</c:if>
							<c:if test="${empty faqboard.fFilename }">
								 첨부파일없음
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<c:if test="${not empty admin }">
						 		<button onclick="location='${conPath}/faqboardModifyView.do?fId=${faqboard.fId }&pageNum=${param.pageNum }'">수정</button>
						 		<button onclick="location='${conPath}/faqboardDelete.do?fId=${faqboard.fId }&pageNum=${param.pageNum }'">삭제</button>
						 	</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<input type="button" value="LIST" class="btn" onclick="location='${conPath}/faqboardList.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
				</table>
			</section>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>