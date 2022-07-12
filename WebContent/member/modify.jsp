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
			$('input[name="oldmPw"]').keyup(
				function() {
					var oldmPw = $('input[name="oldmPw"]').val();
					var mPw = ${member.mPw };
					if (oldmPw == mPw) {
						$('#oldpwChkResult').text('확인');
					} else {
						$('#oldpwChkResult').html('<b>비밀번호 불일치</b>');
					}
				}); //pw check
			$('input[name="mEmail"]').keyup(
				function() {
					var patternMail = /^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+){1,2}$/; // 메일 패턴
					var mEmail = $('input[name="mEmail"]').val();
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
				var oldpwChkResult = $('#oldpwChkResult').text().trim();
				var newpwChkResult = $('#newpwChkResult').text().trim();
				var emailConfirmResult = $('#emailConfirmResult').text().trim();
				var mEmail = $('input[name="mEmail"]').val();
				if (oldpwChkResult != '확인') {
					alert('비밀번호를 확인하세요');
					$('input[name="oldmPw"]').focus();
					return false;
				} else if (newpwChkResult != '비밀번호 일치') {
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
	<article>
		<section class="field">
		<form action="${conPath }/modify.let" method="post">
			<div class="title">
				<h2>${member.mId } 님</h2>
				<h1>회원정보수정 페이지입니다</h1>
				<h4>* 표시가 있는 사항은 필수 입력사항입니다</h4>
				<h5>(반드시 입력해주세요)</h5>
			</div>
			<table>
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
								<div id="newpwChkResult">&nbsp; &nbsp;</div>
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
				<tr class="btn">
					<td colspan="2">
						<input type="submit" value="MODIFY" class="btn" style="height: 30px;">
						<input type="reset" value="RESET" class="btn" style="height: 30px;">
						<input type="button" value="회원탈퇴" onclick="location.href='${conPath}/withdrawalagreeView.let'" class="btn" style="height: 30px;">	
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
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script>
		$( function() {
	    	$( "#mBirth" ).datepicker({
				dateFormat : 'yy-mm-dd',
				changeMonth : true, // 월을 바꿀 수 있는 셀렉트 박스 표시
				monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
								'7월', '8월', '9월', '10월', '11월', '12월' ],
				showMonthAfterYear : true,
				yearSuffix : '년', // "2020년 3월"
				showOtherMonths : true,
				dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ],
				changeYear : true, // 년을 바꿀 수 있는 셀렉트 박스 표시
				minDate : '-100y', // 현재 날짜로부터 100년 이전까지 표시
				maxDate : 'y', // 현재 날짜이전까지만 표시
				yearRange : 'c-100:c+100', // 년도 선택 셀렉트 
			});
	   } );
	</script>
</body>
</html>