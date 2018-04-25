<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script language="javascript">
	var idnum = 0;
	var pwnum = 0;
	$(document).ready(function(){
		$('#id').keyup(function(){
			var id = $('#id').val();
			$.ajax({
				url:'userIdDuplicationCheck',
				type:'post', 
				data:{userId:$('#id').val()},
				success : function(data) {
					if(data == 1){
						$('#id').css('background-color', '#b0f6ac');
						idnum = 1;
					} 
					else {
						$('#id').css('background-color', '#ffcece');
						idnum = 0;
					}
				}
			});
		});
	});
	
	function passwordCheck() {
		var userPw = document.getElementById("pw").value;
		var userPw_check = document.getElementById("pwCheck").value;

		if (userPw_check == "") {
			document.getElementById("pwCheck").style.backgroundColor="#ffcece";
			pwnum = 0;
		} else if (userPw != userPw_check) {
			document.getElementById("pwCheck").style.backgroundColor="#ffcece";
			pwnum = 0;
		} else {
			document.getElementById("pwCheck").style.backgroundColor="#b0f6ac";
			pwnum = 1;
		}
	}

	function next() {
		if (idnum == 1 && pwnum == 1) {
			alert("확인됨");
			return true;
		} else {
			alert("아이디, 비밀번호를 확인해주세요")
			return false;
		}
	}
</script>
</head>
<body>
	<header><jsp:include page="main_header.jsp"></jsp:include> </header>
	<section>
		<div id="join1Div1">
			<h1>회원가입</h1>
			<form id="joinForm" action="join_1" method="post" onsubmit="return next();">
				<table>
					<tr>
						<label class="control-label" for="inputSuccess1">아이디</label><br>
						<input type="text" name="userId" id="id" class="form-control"><br>
					</tr>
					<tr>
						<label class="control-label" for="inputSuccess1">비밀번호</label>
						<input type="password" name="userPw" id="pw" oninput="passwordCheck()" class="form-control"><br>
					</tr>
					<tr>
						<label class="control-label" for="inputSuccess1">비밀번호 확인</label>
						<input type="password" name="userPw_check" id="pwCheck"	oninput="passwordCheck()" class="form-control"><br>
					</tr>
					<tr>
						<label class="control-label" for="inputSuccess1">이름</label>
						<input type="text" name="userName" class="form-control"><br>
					</tr>
					<tr>
						<label class="control-label" for="inputSuccess1">이메일</label>
						<input type="text" name="userEmail" class="form-control"><br>
					</tr>
					<tr>
						<input id=join_nextBtn class="btn btn-default" type="submit" value="다음">
						<input id=join_resetBtn class="btn btn-default" type="reset" value="다시작성">
					</tr>
				</table>
			</form>
		</div>
	</section>
</body>
</html>