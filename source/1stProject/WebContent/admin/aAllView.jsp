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
	<link href="${conPath }/css/allViewList.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<article>
		<header>
			<h1>관리자 리스트</h1>
			<div>
				<form action="${conPath }/searchAdminView.let" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="search_aId" class="search" required="required" placeholder="관리자검색">
								<input type="submit" value="검색" class="btn">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</header>
		<section>
			<table>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<tr>
					<th>관리자ID</th>
					<th>이름</th>
					<th>관리자탈퇴</th>
				</tr>
				<tr>
					<td colspan="3">
						<hr>
					</td>
				</tr>
				<c:if test="${totCnt==0 }">
					<tr>
						<td colspan="3">관리자가 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${adminList }" var="admin">
						<tr>
							<td>${admin.aId }</td>
							<td>${admin.aName }</td>
							<td>
						 		<button onclick="location='${conPath}/withdrawalAdmin.let?aId=${admin.aId }'" class="btn">탈퇴처리</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/allAdminView.let?pageNum=${startPage-1 }">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${startPage }" end="${endPage }">
				<c:if test="${n == pageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != pageNum }">
					[ <a href="${conPath }/allAdminView.let?pageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/allAdminView.let?pageNum=${endPage+1}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>