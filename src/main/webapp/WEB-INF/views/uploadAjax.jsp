<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<style>
.fileDrop { 
	width: 150px;
	height: 150px;
	border: 1px dotted blue;
	}
</style>
</head>
<body>
	<h3> ${sessionScope.loginUser.getUserName()} 프로필 수정</h3>
	<div class="fileDrop">
	<c:choose>
		<c:when test="${empty sessionScope.loginUser.getProfilePhoto()}">
			<img src = "${pageContext.servletContext.contextPath}/resources/profilePhoto.png" width="150px" height="150px">
		</c:when>
		<c:otherwise>
			<img src = "" width="150px" height="150px">
		</c:otherwise>
	</c:choose>
	</div>
	<div> 유저 사진 : ${sessionScope.loginUser.getProfilePhoto()} </div>
	
	<input type="file" id="input_img">
	
	
	
	<div class="uploadedList"></div>
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
		$(".fileDrop").on("dragenter dragover", function(event) {
			event.preventDefault();
		});
		
		$(".fileDrop").on("drop", function(event) {
			event.preventDefault();
			uploadAjax(event);
		});
		
		function uploadAjax(event) {
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			//console.log(file);
			
			var formData = new FormData();
			formData.append("file", file);
			formData.append("userId", "${sessionScope.loginUser.getUserId()}");
			alert("drop");  
			
			 
			$.ajax({
				url: 'uploadAjax',
				data: formData,
				dataType: 'text',
				processData: false, 
				contentType: false, 
				type: 'POST',
				success: function(data) {
					alert(data);
				}
			});
		}
	</script>
	
	<form action="profileSuccess" method="post">
		이름 : <input type="text" name="userName" value=${sessionScope.loginUser.getUserName()}> <br>
		이메일 : <input type="text" name="userEmail" value=${sessionScope.loginUser.getUserEmail()}> <br>
		닉네임 : <input type="text" name="NickName" value=${sessionScope.loginUser.getNickName()}> <br>
		블로그 이름 : <input type="text" name="blogName" value=${sessionScope.loginUser.getNickName()}> <br>
		프로필 소개 : <input type="text" name="profileIntro" value=${sessionScope.loginUser.getProfileIntro()}> <br>
		<input type="hidden" name="userId" value=${sessionScope.loginUser.getUserId()}>
		<input type="submit" value="수정완료">
	</form>
</body>
</html>