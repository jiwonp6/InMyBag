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
	<link href="conPath/css/modify.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
		$(document).ready(function() {
			$('input[name="oldmPw"]').keyup(
					function() {
						var oldmPw = $('input[name="oldmPw"]').val();
						var mPw = ${member.mId };
						if (oldmPw == mPw) {
							$('#pwChkResult').text('확인');
						} else {
							$('#pwChkResult').html('<b>비밀번호 불일치</b>');
						}
					}); // new pw check
			$('input[name="newmPw"], input[name="mPwChk"]').keyup(
				function() {
					var newmPw = $('input[name="newmPw"]').val();
					var mPwChk = $('input[name="mPwChk"]').val();
					if (newmPw == mPwChk) {
						$('#newpwChkResult').text('비밀번호 일치');
					} else {
						$('#newpwChkResult').html('<b>비밀번호 불일치</b>');
					}
				}); // new pw check
			$('input[name="mEmail"]').keyup(
				function() {
					var patternMail = /^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+){1,2}$/; // 메일 패턴
					var mEmail = $(
						'input[name="mEmail"]').val();
						if (patternMail.test(mEmail)) {
							$.ajax({
								url : '${conPath}/emailConfirm.let',
								type : 'post',
								dataType : 'html',
								data : "mEmail="+ mEmail,
								success : function(data) {
											$('#emailConfirmResult').html(data);
										}
							});//ajax
						} else if (!mEmail) {
							$('#emailConfirmResult').html(' &nbsp; ');
						} else {
							$('#emailConfirmResult').html('메일 형식을 지켜주세요');
						}//if
				});// mEmail keyup 이벤트
			$('form').submit(function() {
				var pwChkResult = $('#pwChkResult').text().trim();
				var newpwChkResult = $('#newpwChkResult').text().trim();
				var emailConfirmResult = $('#emailConfirmResult').text().trim();
				if (pwChkResult != '확인') {
					alert('비밀번호를 확인하세요');
					$('input[name="oldmPw"]').focus();
					return false;
				}else if (newpwChkResult != '비밀번호 일치') {
					alert('비밀번호를 확인하세요');
					$('input[name="newmPw"]').focus();
					return false;
				} else if (emailConfirmResult != ''&& emailConfirmResult != '사용 가능한 메일입니다') {
					alert('이메일을 확인하세요');
					$('input[name="mEmail"]').focus();
					return false;
				}
			});
		});//mIdConfirm의 click이벤트
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/modify.let" method="post">
			<table>
				<tr>
					<td>
						<div class="modify_comment">
							<h2>회원님</h2>
							<h1>회원정보수정을 진행해주세요</h1>
							<h4>* 표시가 있는 사항은 필수 입력사항입니다</h4>
							<h5>(반드시 입력해주세요)</h5>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<fieldset>
							<legend>회원정보수정</legend>
							<div class="wrap">
								<p class="id">아이디*</p>
								<p>
									<input type="text" name="mId" readonly="readonly"
										value="${member.mId }"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<div id="idConfirmResult">&nbsp; &nbsp;</div>
								<p class="id">변경전 비밀번호*</p>
								<p>
									<input type="password" name="oldmPw" required="required"
										placeholder="비밀번호 확인"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<div id="oldpwChkResult">&nbsp; &nbsp;</div>
								<p class="id">변경할 비밀번호*</p>
								<p>
									<input type="password" name="newmPw"
										placeholder="비밀번호 변경을 위하시면 입력해주세요"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<p class="id">변경할 비밀번호확인 *</p>
								<p>
									<input type="password" name="mPwChk"
										placeholder="비밀번호 확인을 위해 입력해주세요"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<div id="pwChkResult">&nbsp; &nbsp;</div>
								<p class="id">이름*</p>
								<p>
									<input type="text" name="mName" required="required" value="${member.mName }"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<p class="id">생년월일</p>
								<p>
									<input type="text" name="mBirth" id="mBirth" value="${member.mBirth }"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<p class="id">메일*</p>
								<p>
									<input type="email" name="mEmail" value="${member.mEmail }"
										style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
								</p>
								<div id="emailConfirmResult">&nbsp; &nbsp;</div>
							</div>
						</fieldset>
					</td>
				</tr>
				<tr></tr>
				<tr>
					<td colspan="2"><br> <br> <br></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="submit" value="MODIFY" class="btn">
						<input type="reset" value="RESET" class="btn">
						<input type="button" value="회원탈퇴" onclick="location.href='${conPath}/withdrawal.do'" class="btn">	
					</td>
				</tr>
				<tr>
					<td colspan="2"><br> <br> <br></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="../main/footer.jsp" />
	
</body>
</html>