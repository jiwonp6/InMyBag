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
	<link href="conPath/css/itemboardList2.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style>
		#content_form {
			width: 100%; height:1000px;
			margin: 30px auto 10px;
		}
		#content_form table tr { height: 10px;}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$('tr').click(function(){
				var iId = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				if(!isNaN(iId)){
					location.href = '${conPath}/itemboardContent.do?iId='+iId+'&pageNum=${pageNum}';
				}
			});
		});
	</script>
</head>
<body>
	<c:if test="${empty member }">
		<script>
			alert('로그인 이후 이용가능 합니다');
			history.back();
		</script>
	</c:if>
	<c:if test="${not empty itemboaredResult }">
		<script>alert('${itemboaredResult}');</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<div id="content_form">
		<p>
			<a href="${conPath }/itemboardWriteView.do">글쓰기</a>
		</p>
	<br>
		<c:if test="${totCnt==0 }">
			<div class="none">
				<p>등록된 글이 없습니다</p>
			</div>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<div id="content_form" style="width:100%;">
			<c:forEach items="${itemboardList }" var="itemboard">
					<div class="div1" style="width:100%">
						<table class="items" style="float:left; radius:10px;">
							<tr>
								<td><img src="${conPath }/itemBoardFileUp/${itemboard.iFilename}" width="250" height="300"></td>
							</tr>
							<tr>
								<td>${itemboard.iTitle }(${itemboard.iHit })</td>
							</tr>
							<tr>
								<td class="mid">${itemboard.mId }</td>
							</tr>
						</table>
					</div>
			</c:forEach>
			</div>
		</c:if>

	<div class="paging">
		<c:if test="${startPage > BLOCKSIZE }">
			[ <a href="${conPath }/itemboardList.do?pageNum=${startPage-1 }"> 이전 </a> ]
		</c:if>
		<c:forEach var="n" begin="${startPage }" end="${endPage }">
			<c:if test="${n == pageNum }">
				<b> [ ${n } ] </b>
			</c:if>
			<c:if test="${n != pageNum }">
				[ <a href="${conPath }/itemboardList.do?pageNum=${n }"> ${n } </a> ]
			</c:if>
		</c:forEach>
		<c:if test="${endPage<pageCnt }">
		  [ <a href="${conPath }/itemboardList.do?pageNum=${endPage+1}"> 다음 </a> ]
		</c:if>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>