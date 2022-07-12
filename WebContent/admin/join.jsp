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
	<link href="${conPath }/css/join.css" rel="stylesheet">
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
	<script>
		$(document).ready(function() {
			$('input[name="aId"]').keyup(function() {
				var aId = $('input[name="aId"]').val();
				$.ajax({
					url : '${conPath}/adminidConfirm.let',
					type : 'post',
					dataType : 'html',
					data : "aId=" + aId,
					success : function(data) {
						$('#idConfirmResult').html(data);
					}
				});//ajax	
			});// aId keyup 이벤트
			$('input[name="aPw"], input[name="aPwChk"]').keyup(
				function() {
					var aPw = $('input[name="aPw"]').val();
					var aPwChk = $('input[name="aPwChk"]').val();
					if (aPw == aPwChk) {
						$('#pwChkResult').text('비밀번호 일치');
					} else {
						$('#pwChkResult').html('<b>비밀번호 불일치</b>');
					}
				}); // pw check
			$('form').submit(function() {
				var idConfirmResult = $('#idConfirmResult').text().trim();
				var pwChkResult = $('#pwChkResult').text().trim();
				if (idConfirmResult != '사용 가능한 ID') {
					alert('사용가능한 ID로 가입하세요');
					$('input[name="aId"]').focus();
					return false;
				} else if (pwChkResult != '비밀번호 일치') {
					alert('비밀번호를 확인하세요');
					$('input[name="aPw"]').focus();
					return false;
				} 
			});
		});//mIdConfirm의 click이벤트
	</script>
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/adminjoin.let" method="post">
			<table>
				<caption>
					<br><br><br>
					<h2>관리자 회원가입</h2>
					<br><br><br>
				</caption>
				<tr>
					<td>
						<fieldset>
							<legend>회원가입</legend>
							<div class="wrap">
								<p class="id"> 아이디*</p>
								<p><input type="text" name="aId" required="required" 
										placeholder="아이디를 입력해주세요" 
										style="width:300px; height:40px; font-size:12px; border:0.5px solid lightgray;"></p>
								<div id="idConfirmResult">&nbsp; &nbsp;</div>
								<p class="id"> 비밀번호*</p>
								<p><input type="password" name="aPw" required="required"
										placeholder="비밀번호를 입력해주세요" 
										style="width:300px; height:40px; font-size:12px; border:0.5px solid lightgray;"></p>
								<p class="id"> 비밀번호확인 *</p>
								<p><input type="password" name="aPwChk" required="required"
										placeholder="비밀번호 확인을 위해 입력해주세요" 
										style="width:300px; height:40px; font-size:12px; border:0.5px solid lightgray;"></p>
								<div id="pwChkResult">&nbsp; &nbsp;</div>
								<p class="id"> 이름*</p>
								<p><input type="text" name="aName" required="required"
										style="width:300px; height:40px; font-size:12px; border:0.5px solid lightgray;"></p>
							</div>
						</fieldset>
					</td>
				</tr>
				<tr class="privacy">
					<td>
						<fieldset>
							<legend>이용약관</legend>
							<p class="textpool">
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
							</p>
							<br>							
							<p class="agree">동의함<input type="checkbox" required="required" > &nbsp; &nbsp; </p>
						</fieldset>
					</td>
				</tr>
				<tr></tr>
				<tr class="privacy">
					<td>
						<fieldset>
							<legend>개인정보 이용약관</legend>
							<p class="textpool">
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
								Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.
							</p>
							<br>
							<p class="agree">동의함<input type="checkbox" required="required" > &nbsp; &nbsp; </p>
						</fieldset>
					</td>
				</tr>
				<tr>
					<td>
					<br>
					<br>
					<br>
					</td>
				</tr>
				<tr class="submit">
					<td style="text-align: center;">
						<input type="submit" value="JOIN US" class="btn" style="height: 30px;">
						<input type="button" value="LOGIN" class="btn" onclick="location.href='${conPath}/adminloginView.let'" style="height: 30px;">
					</td>
				</tr>
				<tr>
					<td>
					<br>
					<br>
					<br>
					</td>
				</tr>
				</table>
		</form>
	</div>
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