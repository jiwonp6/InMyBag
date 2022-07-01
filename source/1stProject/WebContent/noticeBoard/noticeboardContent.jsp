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
	<link href="${conPath }/css/header.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<article class="content">
		<section>
			<div>
				<c:if test="${not empty noticeboard.nFilename }">
				</c:if>
			</div>
			<section class="table">
				<table>
					<tr>
						<td colspan="2">${noticeboard.nId }번 글</td>
					</tr>
					<tr>
						<td>작성자</td>
						<td>관리자</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${noticeboard.nTitle }</td>
					</tr>
					<tr>
						<td>본문</td>
						<td><div class="bonmun"><pre>${noticeboard.nContent}</pre></div></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<c:if test="${not empty noticeboard.nFilename }">
								<p><img src="${conPath }/noticeBoardFileUp/${noticeboard.nFilename}" width="240" height="300"></p>
								<a href="${conPath }/noticeBoardFileUp/${noticeboard.nFilename}" target="_blank">${noticeboard.nFilename}</a>
							</c:if>
							<c:if test="${empty noticeboard.nFilename }">
								 첨부파일없음
							</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<c:if test="${not empty admin }">
						 		<button onclick="location='${conPath}/noticeboardModifyView.do?nId=${noticeboard.nId }&pageNum=${param.pageNum }'">수정</button>
						 		<button onclick="location='${conPath}/noticeboardDelete.do?nId=${noticeboard.nId }&pageNum=${param.pageNum }'">삭제</button>
						 	</c:if>
						</td>
					</tr>
					<tr>
						<td colspan="2">
						 	<input type="button" value="LIST" class="btn" onclick="location='${conPath}/noticeboardList.do?pageNum=${param.pageNum }'">
						</td>
					</tr>
				</table>
			</section>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>