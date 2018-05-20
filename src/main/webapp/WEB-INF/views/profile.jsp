<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>내 프로필</title>
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

	<!--<c:choose>
		<c:when test="${empty sessionScope.loginUser.getProfilePhoto()}">
			<img src = "${pageContext.servletContext.contextPath}/resources/profilePhoto.png" width="150px" height="150px">
		</c:when>
		<c:otherwise>
			<img src = "${sessionScope.loginUser.getProfilePhoto()}" width="150px" height="150px">
		</c:otherwise>
	</c:choose> 
	</div>-->
	
	<div class="uploadedList"></div>
	
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
	
	$(document).ready(function(){
		$(document).on("dragenter dragover", ".profile_fileDrop", function(event) {
			event.preventDefault();
		});
		
		$(document).on("drop",".profile_fileDrop", function(event) {
			event.preventDefault();
			uploadAjax(event);
		});
		
		function uploadAjax(event) {
			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			
			var formData = new FormData();
			formData.append("file", file);
			//formData.append("userId", "${sessionScope.loginUser.getUserId()}");
			//$('div.fileDrop').empty();
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
					
					var str ="";
					
					if(checkImageType(data)) {
						$(".fileNameHref").attr("src", "displayFile?fileName="+ getImageLink(data));
						
						//str="<div><a target='_blank' class = 'fileNameHref' href='displayFile?fileName="+ getImageLink(data) + "'>"
						//+"<img src='displayFile?fileName="+ data + "'></a>"
						//+ "<small data-src='" + data +"'>X</small></div>"; 
					} else {
						str ="<div><a class = 'fileNameHref' href='displayFile?fileName="+ data + "'>" + getOriginalName(data) + "</a>"
						+ "<small data-src='" + data +"'>X</small></div>";
						$(".profile_fileDrop").append(str);
					}
				}
			});
		}
		
		function checkImageType(fileName) {
			var pattern = /jpg|gif|png|jpeg/i;
			return fileName.match(pattern);
		}
		function getOriginalName(fileName) {
			if(checkImageType(fileName)) {
				return;
			}
			var idx = fileName.indexOf("_") + 1;
			return fileName.substr(idx);
		}
		function getImageLink(fileName) {
			if(!checkImageType(fileName)) {
				return;
			}
			var front = fileName.substr(0,12);
			var end = fileName.substr(14);
			
			return front + end;
		}
		
			$("#profileForm").submit(function(event) {
				event.preventDefault();
				alert("submit");
				var that = $(this);
				var str= "<input type='hidden' name='profilePhoto' value='" + $(".profile_fileDrop .fileNameHref").attr('src') + "'>";
				
				that.append(str);
				that.get(0).submit();
			})
		});
	</script>
</head>
<body>
	
	<header><jsp:include page="main_header.jsp"></jsp:include> </header>
	
	<section>	
		<div class="profileDiv">
			<h3 style="text-align:center"> ${sessionScope.loginUser.getUserName()}님의 프로필 수정</h3>
			<div class="profile_fileDrop">
				<img class="fileNameHref" src = ${sessionScope.loginUser.getProfilePhoto()} width="295px" height="295px">
			</div>
			<form id="profileForm" action="profileSuccess" method="post">
				이름  <input type="text" name="userName" value=${sessionScope.loginUser.getUserName()} class="form-control"> <br>
				이메일  <input type="text" name="userEmail" value=${sessionScope.loginUser.getUserEmail()} class="form-control"> <br>
				닉네임  <input type="text" name="NickName" value=${sessionScope.loginUser.getNickName()} class="form-control"> <br>
				블로그 이름  <input type="text" name="blogName" value=${sessionScope.loginUser.getBlogName()} class="form-control"> <br>
				프로필 소개  <input type="text" name="profileIntro" value=${sessionScope.loginUser.getProfileIntro()} class="form-control"> <br>
				<input type="hidden" name="userId" value=${sessionScope.loginUser.getUserId()}>
				<input type="submit" value="수정완료" class="btn btn-default">
			</form>
		</div>
		<!-- <div> 유저 사진 : ${sessionScope.loginUser.getProfilePhoto()} </div> -->
	</section>
	
</body>
</html>