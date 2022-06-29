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
	<link href="${conPath }/css/qnaboardList.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
		$(document).ready(function(){
			$('tr').click(function(){
				var qId = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
				if(!isNaN(qId)){
					location.href = '${conPath}/qnaboardContent.do?qId='+qId+'&pageNum=${pageNum}';
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
	<c:if test="${not empty qnaboaredResult }">
		<script>alert('${qnaboaredResult}');</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<article>
		<header>
			<section>
				<ul>
					<li><a href="${conPath }/noticeboardList.do">NOTICE</a></li>
					<li><a href="${conPath }/faqboardList.do">FAQ</a></li>
					<li><a href="${conPath }/qnaboardList.do">QnA</a></li>
				</ul>
			</section>
			<h1>QnA BOARD</h1>
			<p>홈페이지 관련 문의사항은 여기에 글을 남겨주세요.</p>
		</header>
		<div>
			<a href="${conPath }/qnaboardWriteView.do">글쓰기</a>
		</div>
		<section class="qna">
			<table>
				<tr>
					<td colspan="4">
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
					<tr><td colspan="4">등록된 글이 없습니다</td></tr>
				</c:if>
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${qnaboardList }" var="qnaboard">
						<tr>
							<td>${qnaboard.qId }</td>
							<td class="left">
								<c:forEach var="n" begin="1" end="${qnaboard.qIndent }">
									<c:if test="${n==qnaboard.qIndent }">└</c:if>
									<c:if test="${n!=qnaboard.qIndent }"> &nbsp; &nbsp; </c:if>
								</c:forEach>
								${qnaboard.qTitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
								<c:if test="${not empty qnaboard.qFilename }">
									<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
								</c:if>
							</td>
							<td>
								<c:if test="${not empty qnaboard.mId }">
									${qnaboard.mId }
								</c:if>
								<c:if test="${not empty qnaboard.aId }">
									관리자
								</c:if>
							</td>
							<td>${qnaboard.qHit }</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${startPage > BLOCKSIZE }">
				[ <a href="${conPath }/qnaboardList.do?pageNum=${startPage-1 }"> 이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${startPage }" end="${endPage }">
				<c:if test="${n == pageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != pageNum }">
					[ <a href="${conPath }/qnaboardList.do?pageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${endPage<pageCnt }">
			  [ <a href="${conPath }/qnaboardList.do?pageNum=${endPage+1}"> 다음 </a> ]
			</c:if>
		</footer>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>