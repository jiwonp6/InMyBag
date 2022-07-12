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
			<h1>검색된회원 리스트</h1>
			<div>
				<form action="${conPath }/searchMemberView.let" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="search_mId" value="${param.search_mId }" class="search" required="required">
								<input type="submit" value="검색" class="btn" placeholder="회원검색">
								<input type="button" value="모든회원" class="btn" onclick="location.href='${conPath}/allView.let'">
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
					<th>회원ID</th>
					<th>이름</th>
					<th>생년월일</th>
					<th>이메일</th>
					<th>탈퇴/회원글보기</th>
				</tr>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<c:if test="${stotCnt==0 }">
					<tr>
						<td colspan="5">회원이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${stotCnt!=0 }">
					<c:forEach items="${searchMember }" var="searchMember">
						<tr>
							<td>${searchMember.mId }</td>
							<td>${searchMember.mName }</td>
							<td>${searchMember.mBirth }</td>
							<td>${searchMember.mEmail }</td>
							<td>
						 		<button onclick="location='${conPath}/withdrawalMember.let?mId=${searchMember.mId }'" class="btn">탈퇴처리</button> /
						 		<button onclick="location='${conPath}/myboardList.let?mId=${searchMember.mId }'" class="btn">글보기</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${sstartPage > sBLOCKSIZE }">
				[ <a href="${conPath }/searchMemberView.let?spageNum=${sstartPage-1 }&search_mId=${param.search_mId}">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${sstartPage }" end="${sendPage }">
				<c:if test="${n == spageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != spageNum }">
					[ <a href="${conPath }/searchMemberView.let?spageNum=${n }&search_mId=${param.search_mId}"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${sendPage<spageCnt }">
			  [ <a href="${conPath }/searchMemberView.let?spageNum=${sendPage+1}&search_mId=${param.search_mId}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>