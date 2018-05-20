<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
<link href="<c:url value="/resources/main.css" />" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script language="javascript">
	var nicknum = 0;
	$(document).ready(function(){
		$('#nick').keyup(function(){
			var nick = $('#nick').val();
			$.ajax({
				url:'nickNameDuplicationCheck',
				type:'post', 
				data:{nickName:$('#nick').val()},
				success : function(data) {
					if(data == 1){
						$('#nick').css('background-color', '#b0f6ac');
						nicknum = 1;
					} 
					else {
						$('#nick').css('background-color', '#ffcece');
						nicknum = 0;
					}
				}
			});
		});
	

		function next() {
			if (nicknum == 1) {
				alert("확인됨");
				return true;
			} else {
				alert("아이디, 비밀번호를 확인해주세요")
				return false;
			}
		}
		
		$(document).on("dragenter dragover", ".join2_fileDrop", function(event) {
			event.preventDefault();
		});	
	
		$(document).on("drop",".join2_fileDrop", function(event) {
			event.preventDefault();

			var files = event.originalEvent.dataTransfer.files;
			var file = files[0];
			
			var formData = new FormData();
			formData.append("file", file);

			$.ajax({
				url: 'uploadAjax',
				data: formData,
				dataType: 'text',
				processData: false, 
				contentType: false, 
				type: 'POST',
				success: function(data) {
					var str ="";
					
					if(checkImageType(data)) {
						$(".fileNameHref").attr("src", "displayFile?fileName="+ getImageLink(data)); 
					} else {
						str ="<div><a class = 'fileNameHref' href='displayFile?fileName="+ data + "'>" + getOriginalName(data) + "</a>"
						+ "<small data-src='" + data +"'>X</small></div>";
						$(".join2_fileDrop").append(str);
					}
				}
			});
		});
		
		// 전송받은 문자열이 이미지 파일인지를 확인하는 작업을 하는 메소드
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

		$("#joinForm").submit(function(event) {
			event.preventDefault();
			alert("submit");
			var that = $(this);
			var str= "<input type='hidden' name='profilePhoto' value='" + $(".join2_fileDrop .fileNameHref").attr('src') + "'>";
			
			that.append(str);
			that.get(0).submit();
		})	
	});
</script>

</head>
<body>
	<header><jsp:include page="main_header.jsp"></jsp:include> </header>
	<section>
		<div id="join1Div2">
			<h1>회원가입</h1>
			<form action="user" method="post" id=joinForm>
				<table>
					<tr>
						<label class="control-label" for="inputSuccess1">프로필 사진</label>
						<div class="join2_fileDrop">
							<img class="fileNameHref" src = "displayFile?fileName=/profilePhoto.png" width="295px" height="295px">
						</div><br>
					</tr>
					<tr>
						<label class="control-label" for="inputSuccess1">닉네임</label>
						<input type="text" name="nickName" id="nick" class="form-control"><br>
						<td id="nickNameCheckText" width="100"></td>
					</tr>
					<tr>
						<label class="control-label" for="inputSuccess1">블로그 이름</label>
						<input type="text" name="blogName" class="form-control"><br>
					</tr>
					<tr>
						<label class="control-label" for="inputSuccess1">프로필 소개</label>
						<input type="text" name="profileIntro" class="form-control"><br>
					</tr>
					<tr>
						<input style="margin:10px" class="btn btn-default" type="submit" value="회원가입">
						<input class="btn btn-default" type="reset" value="다시작성">
					</tr>
				</table>
			</form>
		</div>
	</section>
</body>
</html>