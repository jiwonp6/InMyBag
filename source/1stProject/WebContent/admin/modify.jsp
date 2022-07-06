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
	<link href="${conPath }/css/modify.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
		$(document).ready(function() {
			$('input[name="oldaPw"]').keyup(
					function() {
						var oldaPw = $('input[name="oldaPw"]').val();
						var aPw = ${admin.aPw };
						if (oldaPw == aPw) {
							$('#oldpwChkResult').text('확인');
						} else {
							$('#oldpwChkResult').html('<b>비밀번호 불일치</b>');
						}
					}); //pw check
			$('input[name="newaPw"], input[name="aPwChk"]').keyup(
				function() {
					var newaPw = $('input[name="newaPw"]').val();
					var aPwChk = $('input[name="aPwChk"]').val();
					if (newaPw == aPwChk) {
						$('#newpwChkResult').text('비밀번호 일치');
					} else {
						$('#newpwChkResult').html('<b>비밀번호 불일치</b>');
					}
				}); // new pw check
			$('form').submit(function() {
				var oldpwChkResult = $('#oldpwChkResult').text().trim();
				var newpwChkResult = $('#newpwChkResult').text().trim();
				if (pwChkResult != '확인') {
					alert('비밀번호를 확인하세요');
					$('input[name="oldaPw"]').focus();
					return false;
				} else if (newpwChkResult != '비밀번호 일치') {
					alert('비밀번호를 확인하세요');
					$('input[name="newaPw"]').focus();
					return false;
				}
			});
		});
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<article>
		<section class="field">
		<form action="${conPath }/adminmodify.let" method="post">
			<div class="title">
				<h2>관리자 ${admin.aId } 님</h2>
				<h1>회원정보수정 페이지입니다</h1>
			</div>
			<table>
				<tr>
					<td>
						<fieldset>
							<legend>회원정보수정</legend>
							<div class="wrap">
								<p class="id">아이디*</p>
								<p>
									<input type="text" name="aId" readonly="readonly"
										value="${admin.aId }"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<div id="idConfirmResult">&nbsp; &nbsp;</div>
								<p class="id">변경전 비밀번호*</p>
								<p>
									<input type="password" name="oldaPw" required="required"
										placeholder="비밀번호 확인"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<div id="oldpwChkResult">&nbsp; &nbsp;</div>
								<p class="id">변경할 비밀번호*</p>
								<p>
									<input type="password" name="newaPw"
										placeholder="비밀번호 변경을 위하시면 입력해주세요"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<p class="id">변경할 비밀번호확인 *</p>
								<p>
									<input type="password" name="aPwChk"
										placeholder="비밀번호 확인을 위해 입력해주세요"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<div id="newpwChkResult">&nbsp; &nbsp;</div>
								<p class="id">이름*</p>
								<p>
									<input type="text" name="aName" required="required" value="${admin.aName }"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
							</div>
						</fieldset>
					</td>
				</tr>
				<tr class="btn">
					<td colspan="2">
						<input type="submit" value="MODIFY" class="btn" style="height: 30px;">
						<input type="reset" value="RESET" class="btn" style="height: 30px;">
						<input type="button" value="관리자탈퇴" onclick="location.href='${conPath}/adminwithdrawal.let'" class="btn" style="height: 30px;">	
					</td>
				</tr>
				<tr>
					<td colspan="2"><br> <br> <br></td>
				</tr>
			</table>
		</form>
		</section>
	</article>
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>