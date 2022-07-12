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
	<link href="${conPath }/css/write2.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<article class="write">
		<section>
		<form action="${conPath }/faqboradModify.do" method="post" enctype="multipart/form-data">
			<input type="hidden" name="pageNum" value="${param.pageNum }">
			<input type="hidden" name="fId" value="${faqboard.fId }"> 
			<input type="hidden" name="dbFilename" value="${faqboard.fFilename }">
			<div class="title">
				<h2>FAQ</h2>
			</div>
			<table>
				<tr>
					<td class="id">>제목</td>
					<td><input type="text" name="fTitle" class="title"
						value="${faqboard.fTitle }" required="required" size="30"></td>
				</tr>
				<tr>
					<td class="id">본문</td>
					<td><textarea name="fContent" rows="3" cols="32">${faqboard.fContent }</textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right; padding:0 30px 20px 0; font-size: 13px;">
						<c:if test="${not empty faqboard.fFilename }">
							원첨부파일 : <a>${faqboard.fFilename}</a>
						</c:if> 
						<c:if test="${empty faqboard.fFilename }">
							원첨부파일 : 첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr>
					<td style="padding-top:10px;"><input type="file" name="fFilename"></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: right;">
						<input type="file" name="fFilename">
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="수정" class="btn">
						<input type="button" value="목록" class="btn" onclick="location='${conPath}/faqboardList.do?pageNum=${param.pageNum }'">
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