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
    <style>
    .lnbFixed {
        position: fixed;
        top: 0;
        right:0;
      }
    </style>
    <script>
      $( document ).ready( function() {
        var jbOffset = $( '.lnb' ).offset();
        $( window ).scroll( function() {
          if ( $( document ).scrollTop() > jbOffset.top ) {
            $( '.lnb' ).addClass( 'lnbFixed' );
          }
          else {
            $( '.lnb' ).removeClass( 'lnbFixed' );
          }
        });
      } );
    </script>
</head>
<body>
	<header>
		<section class="gnb"  id="top">
			<c:if test="${member eq null && admin eq null}">
				<ul>
					<li><a href="${conPath }/joinView.let">JOIN US</a></li>
					<li><a href="${conPath }/loginView.let">LOGIN</a></li>
				</ul>
			</c:if>
			<c:if test="${not empty member}">
				<ul>
					<li class="mybag">
						<a>MY BAG</a>
						<ul class="mybag_sub">
							<li><a>${member.mId }님</a></li>
							<li><a href="${conPath }/myboardList.let">MY BAG</a></li>
							<li><a href="${conPath }/likeyList.let">LIKEY!</a></li>
							<li><a href="${conPath }/modifyView.let">MODIFY</a></li>
							<li><a href="${conPath }/logout.let">LOGOUT</a></li>
						</ul>
					</li>
				</ul>
			</c:if>
			<c:if test="${not empty admin }">
				<ul>
					<li class="mybag">
						<a>관리자</a>
						<ul class="mybag_sub">
							<li><a>${admin.aId }님</a></li>
							<li><a href="${conPath }/allView.let">회원관리</a></li>
							<c:if test="${admin.aKing eq 1 }">
								<li><a href="${conPath }/allAdminView.let">관리자관리</a></li>
								<li><a href="${conPath }/adminjoinView.let">관리자등록</a></li>
							</c:if>
							<li><a href="${conPath }/adminmodifyView.let">MODIFY</a></li>
							<li><a href="${conPath }/adminlogout.let">LOGOUT</a></li>
						</ul>
					</li>
				</ul>
			</c:if>
		</section>
		<section class="logo">
			<img src="${conPath }/img/logo.png" style="width: 450px; height: 280px;">
		</section>
		<section class="lnb">
			<ul>
				<li><a href="${conPath }/main.do">WhatsInMyBag</a></li>
				<li><a href="${conPath }/itemboardList.do">ITEM</a></li>
				<li><a href="${conPath }/noticeboardList.do">COMMUNITY</a></li>
			</ul>
		</section>
	</header>
</body>
</html>