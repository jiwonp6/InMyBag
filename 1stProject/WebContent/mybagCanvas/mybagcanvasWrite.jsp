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
	<link href="${conPath }/css/header.css" rel="stylesheet">
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style></style>
	<script type="application/javascript">
    function draw() {
      var canvas = document.getElementById("canvas");
      if (canvas.getContext) {
        var ctx = canvas.getContext("2d");
        ctx.drawImage(image, x, y, width, height);
      }
    }
  </script>
  <style>
  	canvas#canvas{
  		border:1px solid red;
  		background-image: 
  	}
  </style>
</head>
<body onload="draw();">
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/mybagboardWrite.do" method="post" enctype="multipart/form-data">
			<table>
				<caption>WHAT'S IN MYBAG</caption>
				<tr>
					<td>첨부파일</td>
					<td>
						<canvas id="canvas" width="250" height="300"></canvas>
					</td>
				</tr>
				<tr>
					<td>content</td>
					<td><textarea name="cContent" rows="3" cols="10"></textarea></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="글쓰기" class="btn">
						<input type="reset" value="취소" class="btn"> 
						<input type="button" value="목록" class="btn" onclick="location.href='${conPath}/mybagboardList.do'">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>