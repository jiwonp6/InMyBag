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
	<link href="${conPath }/css/itemboardList.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style>
		button.btn{
			border:none;
			border-radius:5px;
			background-color: white;
			color:black;
			font-size:15px;
		}
		button.btn:hover{
			background-color: lightgray;
		}
	</style>
<script>
	$(document).ready(function() {
		$('.mybagboard').click(function() {
			var bId = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
			if (!isNaN(bId)) {
				location.href = '${conPath}/mybagboardContent.do?bId='+ bId;
			}
		});
		$('.itemboard').click(function() {
			var iId = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
			if (!isNaN(iId)) {
				location.href = '${conPath}/itemboardContent.do?iId='+ iId;
			}
		});
		$('.replymybag').click(function() {
			var bId = Number($(this).children().eq(1).text()); // 0번째 td안의 있는 text;
			if (!isNaN(bId)) {
				location.href = '${conPath}/mybagboardContent.do?bId='+ bId;
			}
		});
		$('.qnaboard').click(function() {
			var qId = Number($(this).children().eq(0).text()); // 0번째 td안의 있는 text;
			if (!isNaN(qId)) {
				location.href = '${conPath}/qnaboardContent.do?qId='+ qId;
			}
		});
	});
</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<article style="height:500px;">
		<header style="height:50px;">
			<h1>MyBag BOARD</h1>
		</header>
		<section style="height:300px;">
			<table>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>수정/삭제</th>
				</tr>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<c:if test="${btotCnt==0 }">
					<tr>
						<td colspan="6">작성한 글이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${btotCnt!=0 }">
					<c:forEach items="${mybagboardList }" var="mybagboard">
						<tr>
							<td>${mybagboard.bId }</td>
							<td class="left">${mybagboard.bName } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
								<c:if test="${not empty mybagboard.bFilename }">
									<img
										src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png"
										width="10">
								</c:if>
							</td>
							<td>${mybagboard.mId }</td>
							<td>${mybagboard.bHit }</td>
							<td>
						 		<button onclick="location='${conPath}/mybagboardModifyView.do?bId=${mybagboard.bId }'" class="btn">수정</button> /
						 		<button onclick="location='${conPath}/mybagboardDelete.do?bId=${mybagboard.bId }'" class="btn">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${bstartPage > bBLOCKSIZE }">
				[ <a href="${conPath }/myboardList.let?bpageNum=${bstartPage-1 }">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${bstartPage }" end="${bendPage }">
				<c:if test="${n == bpageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != bpageNum }">
					[ <a href="${conPath }/myboardList.let?bpageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${bendPage<bpageCnt }">
			  [ <a href="${conPath }/myboardList.let?bpageNum=${bendPage+1}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<article style="height:500px;">
		<header style="height:50px;">
			<h1>Reply myBag</h1>
		</header>
		<section style="height:300px;">
			<table>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<tr>
					<th>글번호</th>
					<th>mybag글번호</th>
					<th>댓글내용</th>
					<th>작성자</th>
					<th>수정/삭제</th>
				</tr>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<c:if test="${rtotCnt==0 }">
					<tr>
						<td colspan="5">작성한 글이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${rtotCnt!=0 }">
					<c:forEach items="${replymybagList }" var="replymybag">
						<tr class="replymybag">
							<td>${replymybag.rId }</td>
							<td>${replymybag.bId }</td>
							<td class="left">${replymybag.rContent }</td>
							<td>${replymybag.mId }</td>
							<td>
						 		<button onclick="location='${conPath}/replymybagModifyView.do?rId=${replymybag.rId }'" class="btn">수정</button> /
						 		<button onclick="location='${conPath}/replymybagDelete.do?rId=${replymybag.rId }'" class="btn">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${rstartPage > rBLOCKSIZE }">
				[ <a href="${conPath }/myboardList.let?rpageNum=${rstartPage-1 }">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${rstartPage }" end="${rendPage }">
				<c:if test="${n == rpageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != rpageNum }">
					[ <a href="${conPath }/myboardList.let?rpageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${rendPage<rpageCnt }">
			  [ <a href="${conPath }/myboardList.let?rpageNum=${rendPage+1}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<article style="height:500px;">
		<header style="height:50px;">
			<h1>ITEM BOARD</h1>
		</header>
		<section style="height:300px;">
			<table>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>수정/삭제</th>
				</tr>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<c:if test="${itotCnt==0 }">
					<tr>
						<td colspan="5">작성한 글이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${itotCnt!=0 }">
					<c:forEach items="${itemboardList }" var="itemboard">
						<tr class="itemboard">
							<td>${itemboard.iId }</td>
							<td class="left">
								<c:forEach var="n" begin="1" end="${itemboard.iIndent }">
									<c:if test="${n==itemboard.iIndent }">└</c:if>
									<c:if test="${n!=itemboard.iIndent }"> &nbsp; &nbsp; </c:if>
								</c:forEach> ${itemboard.iTitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
								<c:if test="${not empty itemboard.iFilename }">
									<img
										src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png"
										width="10">
								</c:if></td>
							<td>${itemboard.mId }</td>
							<td>${itemboard.iHit }</td>
							<td>
						 		<button onclick="location='${conPath}/itemboardModifyView.do?iId=${itemboard.iId }'" class="btn">수정</button> /
						 		<button onclick="location='${conPath}/itemboardDelete.do?iId=${itemboard.iId }'" class="btn">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${istartPage > iBLOCKSIZE }">
				[ <a href="${conPath }/myboardList.let?ipageNum=${istartPage-1 }">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${istartPage }" end="${iendPage }">
				<c:if test="${n == ipageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != ipageNum }">
					[ <a href="${conPath }/myboardList.let?ipageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${iendPage<ipageCnt }">
			  [ <a href="${conPath }/myboardList.let?ipageNum=${iendPage+1}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<article style="height:500px;">
		<header style="height:50px;">
			<h1>QnA BOARD</h1>
		</header>
		<section style="height:300px;">
			<table>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<tr>
					<th>글번호</th>
					<th>글제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>수정/삭제</th>
				</tr>
				<tr>
					<td colspan="5">
						<hr>
					</td>
				</tr>
				<c:if test="${qtotCnt==0 }">
					<tr>
						<td colspan="5">작성한 글이 없습니다</td>
					</tr>
				</c:if>
				<c:if test="${qtotCnt!=0 }">
					<c:forEach items="${qnaboardList }" var="qnaboard">
						<tr class="qnaboard">
							<td>${qnaboard.qId }</td>
							<td class="left">${qnaboard.qTitle } <!-- 글제목에 a태그를 걸지 말고 query로 tr을 클릭하면 상세보기 페이지로 가기 -->
								<c:if test="${not empty qnaboard.qFilename }">
									<img src="https://cdn-icons-png.flaticon.com/512/5088/5088374.png" width="10">
								</c:if>
							</td>
							<td>${qnaboard.mId }</td>
							<td>${qnaboard.qHit }</td>
							<td>
						 		<button onclick="location='${conPath}/qnaboardModifyView.do?qId=${qnaboard.qId }'" class="btn">수정</button> / 
						 		<button onclick="location='${conPath}/qnaboardDelete.do?qId=${qnaboard.qId }'" class="btn">삭제</button>
							</td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</section>
		<footer>
			<c:if test="${qstartPage > qBLOCKSIZE }">
				[ <a href="${conPath }/myboardList.let?qpageNum=${qstartPage-1 }">
					이전 </a> ]
			</c:if>
			<c:forEach var="n" begin="${qstartPage }" end="${qendPage }">
				<c:if test="${n == qpageNum }">
					<b> [ ${n } ] </b>
				</c:if>
				<c:if test="${n != qpageNum }">
					[ <a href="${conPath }/myboardList.let?qpageNum=${n }"> ${n } </a> ]
				</c:if>
			</c:forEach>
			<c:if test="${qendPage<qpageCnt }">
			  [ <a href="${conPath }/myboardList.let?qpageNum=${qendPage+1}">
					다음 </a> ]
			</c:if>
		</footer>
	</article>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>