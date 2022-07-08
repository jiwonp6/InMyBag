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
	<link href="${conPath }/css/mylikeyList.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
	<script>
		function fun(bId){
	    	  if(!isNaN(bId)){
					location.href = '${conPath}/mybagboardContent.do?bId='+bId;
				}
	      	}
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<article class="mylikeyList">
		<header>
			<h1>Me likey!</h1>
		</header>
		<section class="table_wrap">
			<c:if test="${totCnt==0 }">
				<div class="none">
					<p>좋아요 누른 글이 없습니다</p>
				</div>
			</c:if>
			<c:if test="${totCnt!=0 }">
				<div class="table_wrap">
					<c:forEach items="${likemybagList }" var="likemybag">
					<table onclick="fun(${likemybag.bId})">
						<tr>
							<td><img src="${conPath }/mybagBoardFileUp/${likemybag.bFilename}" width="250" height="300"></td>
						</tr>
						<tr>
							<td>${likemybag.bName }(${likemybag.bHit })</td>
						</tr>
						<tr>
							<td>${likemybag.mId }</td>
						</tr>
					</table>
					</c:forEach>
				</div>
			</c:if>
		</section>
		<footer>
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/likeyList.let?pageNum=${startPage-1 }">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${startPage }" end="${endPage }">
				<c:if test="${n == pageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != pageNum }">
					[ <a href="${conPath }/likeyList.let?pageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/likeyList.let?pageNum=${endPage+1}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
		<jsp:include page="../main/footer.jsp" />
</body>
</html>