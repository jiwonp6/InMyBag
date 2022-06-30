<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="conPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">	
</head>
<body>
	<c:if test="${empty error }">
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
						<td class="mid">${mybagboard.mId }</td>
					</tr>
				</table>
			</c:forEach>
		</div>
		<input type="hidden" name="pageNum" class="pageNum" value="${pageNum }">
		<c:if test="${pageCnt<=pageNum }">
			<jsp:include page="../main/footer.jsp"/>
		</c:if>
	</c:if>
</body>
</html>