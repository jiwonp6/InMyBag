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
		<section class="gnb">
			<c:if test="${member eq null}">
				<ul>
					<li><a href="${conPath }/joinView.do">JOIN US</a></li>
					<li><a href="${conPath }/loginView.do">LOGIN</a></li>
				</ul>
			</c:if>
			<c:if test="${not empty member}">
				<ul>
					<li class="mybag">
						<a>MY BAG</a>
						<ul class="mybag_sub">
							<li><a>${member.mId }님</a></li>
							<li><a href="${conPath }/uploadView.do">MY BAG</a></li>
							<li><a href="${conPath }/likeyView.do">LIKEY!</a></li>
							<li><a href="${conPath }/modifyView.do">MODIFY</a></li>
							<li><a href="${conPath }/logout.do">LOGOUT</a></li>
						</ul>
					</li>
				</ul>
			</c:if>
		</section>
		<section class="logo">
			<p>WHAT'S </p>
			<p> &nbsp; &nbsp; IN</p>
			<p> &nbsp; &nbsp; &nbsp; &nbsp; MY BAG</p>
		</section>
		<section class="lnb">
			<ul>
				<li><a href="${conPath }/main.do">WhatsInMyBag</a></li>
				<li><a href="${conPath }/itemboardList.do">ITEM</a></li>
				<li><a href="${conPath }/qnaboardList.do">COMMUNITY</a></li>
			</ul>
		</section>
	</header>
</body>
</html>