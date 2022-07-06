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
	<link href="${conPath }/css/noticeboardList.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script>
		$(document).ready(function(){
			$('tr').click(function(){
				var nId = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				if(!isNaN(nId)){
					location.href = '${conPath}/noticeboardContent.do?nId='+nId+'&pageNum=${pageNum}';
				}
			});
		});
	</script>
</head>
<body>
	<c:if test="${empty member && empty admin}">
		<script>
			alert('로그인 이후 이용가능 합니다');
			location.href = "loginView.let";
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<article>
		<section class="head_menu">
				<h3>
					<a href="${conPath }/noticeboardList.do">NOTICE</a> |
					<a href="${conPath }/faqboardList.do">FAQ</a> |
					<a href="${conPath }/qnaboardList.do">QnA</a>
				</h3>
		</section>
		<header>
			<h1>NOTICE BOARD</h1>
			<p>공지사항 페이지입니다.</p>
		</header>
		<section>
			<div class="write">
				<c:if test="${not empty admin }">
					<a href="${conPath }/noticeboardWriteView.do">글쓰기</a>
				</c:if>
			</div>
			<table>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<tr>
					<th>글번호</th><th>글제목</th><th>작성자</th><th>조회수</th>
				</tr>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<c:if test="${totCnt==0 }">
					<tr><td colspan="6">등록된 글이 없습니다</td></tr>
				</c:if>
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${noticeboardList }" var="noticeboard">
						<tr>
							<td>${noticeboard.nId }</td>
							<td class="left">
								${noticeboard.nTitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
								<c:if test="${not empty noticeboard.nFilename }">
									<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
								</c:if>
							</td>
							<td>관리자</td>
							<td>${noticeboard.nHit }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<hr>
		</section>
		<footer class="paging">
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/noticeboardList.do?pageNum=${startPage-1 }"> 이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${startPage }" end="${endPage }">
				<c:if test="${n == pageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != pageNum }">
					[ <a href="${conPath }/noticeboardList.do?pageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/noticeboardList.do?pageNum=${endPage+1}"> 다음 </a> ]
			</c:if>
		</footer>
	</article>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>