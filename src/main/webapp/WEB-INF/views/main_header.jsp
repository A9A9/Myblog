<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"> -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}style.css">
<title>Insert title here</title>
</head>
<body>
	<!--헤더에 네비게이션을 넣어서 메뉴바를 생성-->
	<!-- <img id="fl" src="" width="200" height="70" /> -->
	<c:choose>
		<c:when test="${not empty sessionScope.loginUser}">
			<h2>로그인 성공</h2>
	${sessionScope.loginUser.getUserName()}
	<a href="logout">로그아웃</a>
	<a href="profile">내 프로필</a>
		</c:when>
		<c:otherwise>
			<form class="login-form" action="" method="post" name="loginForm">
				<p class="float">
					<input type="text" name="userId" placeholder="아이디">
				</p>
				<p class="float">
					<input type="password" name="userPw" placeholder="비밀번호"
						class="showpassword">
				</p>
				<p class="clearfix">
					<input type="submit" name="submit" value="로그인" class="submit">
				</p>
				<a href="join">회원가입</a>
			</form>

			<from action="join", method="get" name="joinForm"> <input type="submit"
				value="회원가입"> </from>
		</c:otherwise>
	</c:choose>

	<ul>

		<!-- 
		<li><a href="#">동물원소개</a></li>
		<li><a href="info.html">관람안내</a></li>
		<li><a href="#">커뮤니티</a></li>
		<li><a href="animal.html">동물도서관</a></li>
		<li><a href="list.jsp">자유게시판</a></li>
		<!--<li><a id="ri" href="login.html">Login</a></li>
		 -->
	</ul>
	</nav>
</body>
</html>