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
			<h1>회원 리스트</h1>
			<div>
				<form action="${conPath }/searchMemberView.let" method="post">
					<table>
						<tr>
							<td>
								<input type="text" name="search_mId" class="search" required="required" placeholder="회원검색">
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
				<c:if test="${totCnt==0 }">
					<tr>
						<td colspan="5">회원이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${memberList }" var="member">
						<tr>
							<td>${member.mId }</td>
							<td>${member.mName }</td>
							<td>${member.mBirth }</td>
							<td>${member.mEmail }</td>
							<td>
						 		<button onclick="location='${conPath}/withdrawalMember.let?mId=${member.mId }'" class="btn">탈퇴처리</button> /
						 		<button onclick="location='${conPath}/myboardList.let?mId=${member.mId }'" class="btn">글보기</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/allView.let?pageNum=${startPage-1 }">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${startPage }" end="${endPage }">
				<c:if test="${n == pageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != pageNum }">
					[ <a href="${conPath }/allView.let?pageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/allView.let?pageNum=${endPage+1}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>