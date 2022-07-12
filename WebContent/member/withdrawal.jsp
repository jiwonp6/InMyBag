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
	<script src="//code.jquery.com/jquery-1.12.4.min.js"></script>
	<style>
		table.textbox{
			padding:100px;
		}
		p.textpool{
			text-align: center;
			line-height: 100px;
		}
		td.btn{
			text-align: center;
			padding:10px;
		}
	</style>
	<script>
		$(document).ready(function() {
			$('input[name="CheckingmPw"]').keyup(
					function() {
						var checkingmPw = $('input[name="CheckingmPw"]').val();
						var mPw = ${member.mPw };
						if (checkingmPw == mPw) {
							$('#mpwChkResult').text('확인');
						} else {
							$('#mpwChkResult').html('<b>비밀번호 불일치</b>');
						}
					}); //pw check
			$('form').submit(function() {
				var mpwChkResult = $('#mpwChkResult').text().trim();
				if (mpwChkResult != '확인') {
					alert('비밀번호를 확인하세요');
					$('input[name="CheckingmPw"]').focus();
					return false;
				}
			});
		});
	</script>
</head>
<body>
<jsp:include page="../main/header.jsp" />
	<form action="${conPath }/withdrawal.let" method="post">
	<input type="hidden" name="mId" value="${member.mId }">
	<table class="textbox">
		<tr>
			<td>
				<fieldset>
					<legend>비밀번호확인</legend>
					<p class="textpool" >
						비밀번호
						<input type="password" name="CheckingmPw" required="required" placeholder="비밀번호를 입력해주세요"
							style="width: 300px; height: 40px; font-size: 12px; border: 0.5px solid lightgray;">
					</p>
					<div id="mpwChkResult">&nbsp; &nbsp;</div>
				</fieldset>
			</td>
		</tr>
		<tr>
			<td class="btn" colspan="2">
				<input type="submit" value="회원탈퇴" class="btn">
				<input type="button" value="이전" class="btn" onclick="history.back()">
			</td>
		</tr>
	</table>
	</form>
<jsp:include page="../main/footer.jsp" />
</body>
</html>