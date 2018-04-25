<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Myblog</title>

<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>

<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script>
	$(document).ready(
		function () {
			$('button#blogSearchBtn').click(function(){
				$('div#blogs').empty();
				var blogSearch = $('input#blogSearch').val();
				$.getJSON('blogSearch/' + blogSearch, function(data){
					alert(data.length)
					if(data.length > 0) {				
						for(var i = 0; i< data.length; i++) {
							$('#blogs').append(
									"<a style='text-decoration:none; color:black; margin:10px;' href=''>" + data[i].userName + "</a> <emsp>"
									+ "<a style='text-decoration:none; color:black; margin:10px;' href=''>" + data[i].blogName + "</a> <emsp> <br>"
							);
						}
					}
					else {
						alert("검색 결과가 없습니다.");					
					}
				}).error(function() {
				});
			});
		});
</script>

<body>
	<header><jsp:include page="main_header.jsp"></jsp:include> </header>
	<section>
		<c:choose>
			<c:when test="${not empty sessionScope.loginUser}">
				<div class="blogMainTable">
					<table>
						<tr>
							<th class="th_center" style="font-size:17pt;">
								${sessionScope.loginUser.getBlogName()}
							</th> 
						</tr>
						<tr>
							<td>
								<div class="loginDiv">
									<img style="border-radius:500px;" src = "${sessionScope.loginUser.getProfilePhoto()}" width="450px" height="450px">
								</div>
							</td>
						</tr>
						<tr>
							<th class="th_center" style="font-size:15pt;">
								<div class="margin10">
									${sessionScope.loginUser.getUserName()}(${sessionScope.loginUser.getNickName()})
								</div>
							</th>
						</tr>
						<tr>
							<th class="th_center" style="font-size:15pt;">
								<div class="margin10">
									${sessionScope.loginUser.getUserEmail()}
								</div>
							</th>
						</tr>
					</table>
					<button id="goMyblog" type="button" onclick="location.href=''" class="btn btn-default">내 블로그</button>
				</div>
			</c:when>
			<c:otherwise>
		
			<div class="myblogH1">
					<h1>My blog</h1>
			</div>
			
			<div class="input-group" id="mainSearch">
				<input type="text" id="blogSearch" class="form-control"> 
				<span class="input-group-btn">
					<button type="button" id="blogSearchBtn" class="btn btn-default">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					</button>
				</span>
			</div>
			
			<div class="printBlogs" id="blogs">
				
			</div>
			</c:otherwise>
		</c:choose>
		</section>
		<footer></footer>
</body>
</html>