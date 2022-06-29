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
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div id="content_form">
		<form action="${conPath }/modify.do" method="post">
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