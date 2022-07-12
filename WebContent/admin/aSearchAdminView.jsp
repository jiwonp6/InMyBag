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
			<h1>검색된관리자 리스트</h1>
			<div>
				<form action="${conPath }/searchAdminView.let" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="search_aId" value="${param.search_aId }" class="search" required="required">
								<input type="submit" value="검색" class="btn" placeholder="관리자검색">
								<input type="button" value="모든관리자" class="btn" onclick="location.href='${conPath}/allAdminView.let'">
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
				<c:if test="${stotCnt==0 }">
					<tr>
						<td colspan="3">관리자가 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${stotCnt!=0 }">
					<c:forEach items="${searchAdmin }" var="searchAdmin">
						<tr>
							<td>${searchAdmin.aId }</td>
							<td>${searchAdmin.aName }</td>
							<td>
						 		<button onclick="location='${conPath}/withdrawalAdmin.let?aId=${searchAdmin.aId }'" class="btn">탈퇴처리</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${sstartPage > sBLOCKSIZE }">
				[ <a href="${conPath }/searchAdminView.let?spageNum=${sstartPage-1 }&search_aId=${param.search_aId}">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${sstartPage }" end="${sendPage }">
				<c:if test="${n == spageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != spageNum }">
					[ <a href="${conPath }/searchAdminView.let?spageNum=${n }&search_aId=${param.search_aId}"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${sendPage<spageCnt }">
			  [ <a href="${conPath }/searchAdminView.let?spageNum=${sendPage+1}&search_aId=${param.search_aId}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>