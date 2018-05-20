<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<!-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css"> -->
<!-- <link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/style.css"> -->
	<!-- <link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/style.css" /> -->
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<title>Insert title here</title>


</head>
<body>
	<!--헤더에 네비게이션을 넣어서 메뉴바를 생성-->
	<!-- <img id="fl" src="" width="200" height="70" /> -->
	<c:choose>
		<c:when test="${not empty sessionScope.loginUser}">
		<div class="loginHeader" >
			<span style="color:white">${sessionScope.loginUser.getUserName()} 회원님</span>
			<button type="button" onclick="location.href='logout'" class="btn btn-default">로그아웃</button>
			<button type="button" onclick="location.href='profile'" class="btn btn-default">내 프로필</button>
		</div>
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
				<p>
					<input class="btn btn-default" type="submit" name="submit" value="로그인" class="submit">
					<button class="btn btn-default" type="button" onclick="location.href='firstSignup'">회원가입</button>
				</p>
			</form>
		</c:otherwise>
	</c:choose>
</body>
</html>