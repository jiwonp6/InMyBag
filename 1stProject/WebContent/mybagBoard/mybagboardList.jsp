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
	<link href="${conPath }/css/mybagBoard.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
     	function fun(bId){
    	  if(!isNaN(bId)){
				location.href = '${conPath}/mybagboardContent.do?bId='+bId+'&pageNum=${pageNum}';
			}
      	}
	</script>
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		var pageNum;
		var pageCnt = Number('${pageCnt}');
		$(document).ready(function(){
			$(window).scroll(function(){
				pageNum = Number($('.pageNum').last().val());
				if(isNaN(pageNum)){
					pageNum=1;
				}
				if($(window).scrollTop()+40>= $(document).height()-$(window).height()){
					$.ajax({
						url : '${conPath}/append.do',
						type : 'get',
						dataType : 'html',
						data : "pageNum="+(pageNum+1),
						success : function(data){
							$('#appendDiv').append(data);
							pageNum = Number($('.pageNum').last().val());
							if(pageCnt <= pageNum){
								$(window).off('scroll');
							}
						}
					});//ajax
				}//if
			});
		});
	</script>	
</head>
<body>
	<jsp:include page="../main/header.jsp"/>
	<c:if test="${not empty mybagboardResult }">
		<script>alert('${mybagboardResult}')</script>
	</c:if>
<article class="mybagBoard">
	<div>
		<p class="hit_menu">
			<a href="${conPath }/mybagboardWriteView.do">글쓰기</a> |
			<a href="${conPath }/mybagboardList.do">위로</a>
		</p>
		<br>
	</div>
	<br>
	<section class="table_wrap">
		<c:if test="${totCnt==0 }">
			<div class="none">
				<p>등록된 글이 없습니다</p>
			</div>
		</c:if>
		<c:if test="${totCnt!=0 }">
			<div class="table_wrap">
				<c:forEach items="${mybagboardList }" var="mybagboard">
				<table onclick="fun(${mybagboard.bId})">
					<tr>
						<td><img src="${conPath }/mybagBoardFileUp/${mybagboard.bFilename}" width="250" height="300"></td>
					</tr>
					<tr>
						<td>${mybagboard.bName }(${mybagboard.bHit })</td>
					</tr>
					<tr>
						<td>${mybagboard.mId }</td>
					</tr>
				</table>
				</c:forEach>
			</div>
		</c:if>
	<div id="appendDiv"></div>
	</section>
</article>
</body>
</html>