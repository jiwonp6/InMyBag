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
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script>
	var rpageNum;
	var bId = ${mybagboard.bId };
	/* var rId = ${replymybag.rId }; */
	$(document).ready(function(){
		var pageCnt = Number('${pageCnt}');
		var totCnt = Number('${totCnt}');
		if(totCnt<=2){
			$('.plus').css('display','none');
		}
		rpageNum = Number($('.rpageNum').last().val());
		if(pageCnt == rpageNum){
			$('.plus').css('display','none');
		}
		$('.plus').click(function(){
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
						$('.plus').css('display','none');
					}
				}
			});//ajax
		});// 더보기 버튼
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
	<c:if test="${empty member }">
		<script>
			alert('로그인 후 이용가능합니다.');
			history.back();
		</script>
	</c:if>
	<jsp:include page="../main/header.jsp"/>
	<article class="content">
		<section>
			<div>
				<c:if test="${not empty mybagboard.bFilename }">
					<p><img src="${conPath }/mybagBoardFileUp/${mybagboard.bFilename}" width="240" height="300"></p>
				</c:if>
				<c:if test="${empty mybagboard.bFilename }">
					<p><img src="${conPath }/mybagBoardFileUp/noneImg.png" width="240" height="300"></p>
				</c:if>
			</div>
			<div>
				<c:if test="${like eq 0}">
	            	<img src="${conPath }/img/dlike.png" class="like" width="20px" height="20px">  <!-- 안눌러진상태 -->
	          	</c:if>
	            <c:if test="${like eq 1}">
		        	<img src="${conPath }/img/like.png" class="like" width="20px" height="20px"> <!-- 눌러진상태 -->
			    </c:if>
			</div>
			<section class="table">
				<table>
					<tr>
						<td colspan="2">${mybagboard.bId }번 글(${mybagboard.mId})</td>
					</tr>
					<tr>
						<td>제목</td>
						<td>${mybagboard.bName }</td>
					</tr>
					<tr>
						<td>본문</td>
						<td><div class="bonmun"><pre>${mybagboard.bContent}</pre></div></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
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
							 <input type="button" value="LIST" class="btn" onclick="location='${conPath}/main.do'">
						</td>
					</tr>
				</table>
			</section>
			<section  class="reply">
				<form action="${conPath }/replymybagWrite.do" method="post">
					<table>
						<caption>댓글(${totCnt })</caption>
						<tr>
							<td><input type="hidden" name="bId" value="${mybagboard.bId }"></td>
							<td><input type="hidden" name="rpageNum" value="${rpageNum }"></td>
							<td><input type="hidden" name="pageNum" value="${param.pageNum }"></td>
							<td>${member.mId }</td>
							<td>
								<input type="text" name="rContent" placeholder="댓글입력창" required="required" class="reply" >
							</td>
							<td>
								<input type="submit" value="댓글쓰기" class="btn">
							</td>
						</tr>
					</table>
				</form>
				<c:if test="${totCnt==0 }">
					<tr><td colspan="6">등록된 댓글이 없습니다</td></tr>
				</c:if>
				<c:if test="${totCnt!=0 }">
					<c:forEach items="${replymybagList }" var="replymybag">
						<table>
							<tr>
								<td>${replymybag.rId }</td>
								<td>${replymybag.mId }</td>
								<td class="left">
									<c:forEach var="n" begin="1"
										end="${replymybag.rIndent }">
										<c:if test="${n==replymybag.rIndent }">└</c:if>
										<c:if test="${n!=replymybag.rIndent }"> &nbsp; &nbsp; </c:if>
									</c:forEach> ${replymybag.rContent }
								</td>
							</tr>
						</table>
						<div id="replyReply"></div>
					</c:forEach>
					<div id="appendDiv"></div>
					<button class="plus">더보기</button>
				</c:if>
			</section>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp"/>
</body>
</html>