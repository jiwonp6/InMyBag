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
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<article>
		<section class="content">
			<div class="boardId">
				<hr>
				<h3>NOTICE ${noticeboard.nId }. ${noticeboard.nTitle }</h3>
				<hr>
			</div>
			<table>
				<tr class="boardwriter">
					<td>작성자 : 관리자</td>
				</tr>
				<tr class="content">
					<td><pre>${noticeboard.nContent}</pre></td>
				</tr>
				<tr class="file">
					<td>
						<c:if test="${not empty noticeboard.nFilename }">
							<a href="${conPath }/noticeBoardFileUp/${noticeboard.nFilename}" target="_blank">${noticeboard.nFilename}</a>
						</c:if>
						<c:if test="${empty noticeboard.nFilename }">
							 첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr class="btn">
					<td>
					 	<c:if test="${not empty admin }">
					 		<button onclick="location='${conPath}/noticeboardModifyView.do?nId=${noticeboard.nId }&pageNum=${param.pageNum }'">수정</button>
					 		<button onclick="location='${conPath}/noticeboardDelete.do?nId=${noticeboard.nId }&pageNum=${param.pageNum }'">삭제</button>
					 	</c:if>
					</td>
				</tr>
				<tr class="btn">
					<td>
					 	<input type="button" value="LIST" class="btn" onclick="location='${conPath}/noticeboardList.do?pageNum=${param.pageNum }'">
					</td>
				</tr>
			</table>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>