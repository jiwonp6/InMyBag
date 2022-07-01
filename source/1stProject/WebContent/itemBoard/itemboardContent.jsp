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
	<link href="${conPath }/css/itemBoardContent.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
	<article class="content">
		<section>
			<div>
				<c:if test="${not empty itemboard.iFilename }">
					<p><img src="${conPath }/itemBoardFileUp/${itemboard.iFilename}" width="240" height="300"></p>
				</c:if>
				<c:if test="${empty itemboard.iFilename }">
					<p><img src="${conPath }/itemBoardFileUp/noneImg.png" width="240" height="300"></p>
				</c:if>
			</div>
			<section class="table">
				<table>
					<tr>
						<td colspan="2">${itemboard.iId }번 글(${itemboard.mId})</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${itemboard.iTitle }</td>
					</tr>
					<tr>
						<td>본문</td>
						<td><div class="bonmun"><pre>${itemboard.iContent}</pre></div></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<c:if test="${not empty itemboard.iFilename }">
								<a href="${conPath }/itemBoardFileUp/${itemboard.iFilename}" target="_blank">${itemboard.iFilename}</a>
							</c:if>
							<c:if test="${empty itemboard.iFilename }">
								 첨부파일없음
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<%-- <c:if test="${member.mId eq itemboard.mId }">
						 		<button onclick="location='${conPath}/itemboardModifyView.do?iId=${itemboard.iId }&pageNum=${param.pageNum }'">수정</button>
						 	</c:if>
						 	<c:if test="${member.mId eq itemboard.mId or not empty admin}">
						 		<button onclick="location='${conPath}/itemboardDelete.do?iId=${itemboard.iId }&pageNum=${param.pageNum }'">삭제</button>
						 	</c:if> --%>
						 	<button onclick="location='${conPath}/itemboardReplyView.do?iId=${itemboard.iId }&pageNum=${param.pageNum }'">REPLY</button>
						 	<input type="button" value="LIST" class="btn"
						 		onclick="location='${conPath}/itemboardList.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
				</table>
			</section>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>