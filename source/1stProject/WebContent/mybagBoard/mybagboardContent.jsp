<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link href="${conPath }/css/mybagBoardContent.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
		var rpageNum;
		var bId = ${mybagboard.bId };
		$(document).ready(function(){
			var pageCnt = Number('${pageCnt}');
			var totCnt = Number('${totCnt}');
			if(totCnt<=2){
				$('button').css('display','none');
			}
			rpageNum = Number($('.rpageNum').last().val());
			if(pageCnt == rpageNum){
				$('button').css('display','none');
			}
			$('button').click(function(){
				rpageNum = Number($('.rpageNum').last().val());
				if(isNaN(rpageNum)){
					rpageNum=1;
				}
				$.ajax({
					url : '${conPath}/replyappend.do',
					type : 'get',
					dataType : 'html',
					data : {"rpageNum":(rpageNum+1), "bId":(bId)},
					success : function(data){
						$('#appendDiv').append(data);
						rpageNum = Number($('.rpageNum').last().val());
						if(pageCnt <= rpageNum){
							$('button').css('display','none');
						}
					}
				});//ajax
			});// 더보기 버튼
		});	
	</script>
	<script>
	$(document).ready(function(){
		$('.like').click(function(){
			  var likeNum = Number('${like }');
			  if(likeNum==0){
				  $(this).attr('src', 'img/like.png');
				  location.href='${conPath}/likemybagWrite.do?mId='+'${member.mId}&bId='+'${mybagboard.bId}';
			  }else if(likeNum ==1){
				  likeNum=0;
				  $(this).attr('src', 'img/dlike.png');
				  location.href='${conPath}/likemybagDelete.do?mId='+'${member.mId}&bId='+'${mybagboard.bId}';
			  }
		  });//좋아요버튼
	});
	</script>
</head>
<body>
	<c:if test="${empty member && empty admin }">
		<script>
			alert('로그인 이후 이용가능 합니다');
			location.href = "loginView.let";
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<article class="content">
		<section>
			<div class="boardId">
				<hr>
				<h3>No.${mybagboard.bId }(${mybagboard.mId})</h3>
				<hr>
			</div>
			<div class="pic">
				<c:if test="${not empty mybagboard.bFilename }">
					<p><img src="${conPath }/mybagBoardFileUp/${mybagboard.bFilename}" width="240" height="300"></p>
				</c:if>
				<c:if test="${empty mybagboard.bFilename }">
					<p><img src="${conPath }/mybagBoardFileUp/noneImg.png" width="240" height="300"></p>
				</c:if>
			</div>
			<div class="likey">
				<c:if test="${like eq 0}">
	            	<p><img src="${conPath }/img/dlike.png" class="like" width="20px" height="20px"></p>  <!-- 안눌러진상태 -->
	          	</c:if>
	            <c:if test="${like eq 1}">
		        	<p><img src="${conPath }/img/like.png" class="like" width="20px" height="20px"></p> <!-- 눌러진상태 -->
			    </c:if>
			</div>
			<table class="content">
				<tr>
					<td style="font-weight: bold">${mybagboard.bName }</td>
				</tr>
				<tr>
					<td>
						<div class="bonmun"><pre>${mybagboard.bContent}</pre></div>
					</td>
				</tr>
				<tr>
					<td style="text-align: right; font-size: 10px;">
						<c:if test="${not empty mybagboard.bFilename }">
							<a href="${conPath }/mybagBoardFileUp/${mybagboard.bFilename}" target="_blank">${mybagboard.bFilename}</a>
						</c:if>
						<c:if test="${empty mybagboard.bFilename }">
							 첨부파일없음
						</c:if>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<c:if test="${not empty param.pageNum }">
							<input type="button" value="LIST" class="btn" onclick="location='${conPath}/main.do'">
						</c:if>
						<c:if test="${empty param.pageNum && not empty member}">
							<input type="button" value="LIST" class="btn" onclick="location='${conPath}/myboardList.let'">
						</c:if>
						<c:if test="${empty param.pageNum && not empty admin}">
							<input type="button" value="뒤로가기" class="btn" onclick="history.back()">
						</c:if>
					</td>
				</tr>
			</table>
			<section>
				<form action="${conPath }/replymybagWrite.do" method="post">
					<table class="reply">
						<caption><h4>댓글(${totCnt })</h4></caption>
						<tr>
							<td><input type="hidden" name="bId" value="${mybagboard.bId }"></td>
							<td><input type="hidden" name="rpageNum" value="${rpageNum }"></td>
							<td><input type="hidden" name="pageNum" value="${param.pageNum }"></td>
							<c:if test="${not empty member }">
							<td class="reply_mId">${member.mId }</td>
								<td>
									<input type="text" name="rContent" placeholder="댓글입력창" required="required" class="reply">
								</td>
								<td>
									<input type="submit" value="댓글" class="btn">
								</td>
							</c:if>
						</tr>
					</table>
				</form>
				<c:if test="${totCnt==0 }">
					<table class="reply">
						<tr><td colspan="6">등록된 댓글이 없습니다</td></tr>
					</table>
				</c:if>
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${replymybagList }" var="replymybag">
						<table class="reply">
							<tr>
								<th class="replyreply_mId"> ${replymybag.mId } </th>
							</tr>
							<tr>
								<td class="left">
									<c:forEach var="n" begin="1"
										end="${replymybag.rIndent }">
										<c:if test="${n==replymybag.rIndent }">└</c:if>
										<c:if test="${n!=replymybag.rIndent }"> &nbsp; &nbsp; </c:if>
									</c:forEach> ${replymybag.rContent }
								</td>
							</tr>
						</table>
					</c:forEach>
					<div id="appendDiv"></div>
					<div class="btn">
						<button class="btn" >더보기</button>
					</div>
				</c:if>
			</section>
		</section>
	</article>
	<footer style="margin-top:50px;">
		<jsp:include page="../main/footer.jsp"/>
	</footer>
</body>
</html>