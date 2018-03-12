<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
    <link href="<c:url value="/resources/css/blog.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script>
	$(document).ready(
			function() {
					var userId = "user1_id";
					function getfolderSelect(data){
						$('select#folders').empty();
						var option = "<option value='#'>CATEGORY</option>";
						for (var i = 0; i < data.length; i++) {
							option += "<option value='" + data[i].folderIndex + "'>" + data[i].folderName + "</option>";
						}
						$('select#folders').append(option);
					}
					$.getJSON('/folders/' + userId, function(data) {
						getfolderSelect(data);
					});
					
				
				function getPosts(folderIndex,num) {
					$.getJSON("/posts/" + folderIndex + "/" + num, function(data) {
					var posts = "<table class='table table-condensed'>";
					for (var i = 0; i < data.length; i++) {
						posts += "<tr><td style='width:40%'>" + data[i].postDate + "</td>" 
						+ "<td><a href='javascript:;' class='post' value='"
						+ data[i].postIndex+ "'>"
						+ data[i].postTitle
						+ "</a></td></tr>";
					}
					posts += "</table>"
					$('div#posts').append(posts);
					});
				}
				
				function getPages(folderIndex) {
					$.getJSON('/pages/' + folderIndex, function(data) {
						for (var i = 0; i <= data/5; i++) {
							$('div#pages').append(
									"<a href='javascript:;' class='page' value='" + (i+1)+ "'> [" + (i+1) + "] </a>");
						}
					});
				}
				
				function folders(data) {
					$('div#folderManager').empty();
					var folders = "<button type='button' id='folderAdd' style='margin:'> + </button> <br> <h3> 폴더 이름 </h3>";
					for (var i = 0; i < data.length; i++) {
						folders += "<form id = 'foldersForm" + data[i].folderIndex + "'>" 
						+ "<input type='text' name='folderName' value='" + data[i].folderName + "'/> &nbsp&nbsp"
						+ "<button type='button' class='folderModify' value='" + data[i].folderIndex+ "'> 수정 </button>"
						+ "<button type='button' class='folderDelete' value='" + data[i].folderIndex+ "'> 삭제 </button><br/>"	
						+ "</form>";
					}
					$('div#folderManager').append(folders);
				}
				
				function post(data) {
					$('div#post').empty();
					$('div#post').append(
							"<table class='table table-striped'><tr><th>" + data.postTitle + "</th></tr>" 
							+ "<tr><td style='text-align:right'>" + data.postDate+ "</td></tr>"
							+ "<tr><td style='word-break:break-all'>" + data.postFile+ "<br>"
							+ data.postContent+ "</pre></td></tr>"
							+ "<tr><td style='text-align:right'><button type = 'button' id ='postModify' value ='"+ data.postIndex +"'>수정</button>"
							+ "<button type='button' id='postRemove' value ='"+ data.postIndex +"'>삭제</button></td></tr></table>");
				}
					$('select#folders').change(
							function() {
								$('div#folderManager').empty();
								$('div#postAddBtn').empty();
								$('div#post').empty();
								$('div#posts').empty();
								$('div#pages').empty();
								var folderIndex = $('select#folders option:selected').val();
								if(folderIndex != '#') {
									$('div#postAddBtn').append(
									"<button type='button' id='postAddBtn' value ='"+ folderIndex +"'>글쓰기</button>");
									$.getJSON('/pages/' + folderIndex, function(data) {
										if (data != 0) {
											getPosts(folderIndex, 1);
											getPages(folderIndex);
										}
									});
								}
					});
					
					$(document).on('click','a.page',function(){
						$('div#posts').empty();
						var pageNum = $(this).attr('value');
						var folderIndex = $('select#folders option:selected').val();
						getPosts(folderIndex,pageNum);
					});
					
					$('button#folderManagerBtn').click(function(){
						$('div#postAddBtn').empty();
						$('div#posts').empty();
						$('div#pages').empty();
						$('div#post').empty();
						$.getJSON('/folders/' + userId, function(data) {
							folders(data);
						});
					});
					
					$(document).on('click','button#folderAdd',function(){
						$('div#folderManager').empty();
						$('div#folderManager').append(
								"<form id = 'folderAddForm'>"
								+"<br>폴더명  :<input type=text name = 'folderName' style='margin-bottom:10px;'/>"
								+ "<br> <button type = 'button' style='margin-left: 90px;'id ='folderInsert'>등록</button>"
								+ "<button type='button' id='folderCancel'>취소</button>"
								+"</form>"
						);
						
					});
					
					jQuery.fn.serializeObject = function() {
					    var obj = null;
					    try {
					        if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
					            var arr = this.serializeArray();
					            if (arr) {
					                obj = {};
					                jQuery.each(arr, function() {
					                    obj[this.name] = this.value;
					                });
					            }
					        }
					    } catch (e) {
					        alert(e.message);
					    } finally {
					    }
					 
					    return obj;
					};
	
					$(document).on('click','button#folderInsert',function(){
						var newFolder = $("form#folderAddForm").serializeObject();
						$.ajax({
							url :'folder/' + userId,
					        method : 'post',
							dataType : 'json',
							data : JSON.stringify(newFolder),
							processData : true,
							contentType : "application/json; charset=UTF-8",
							success : function(data) {
								getfolderSelect(data);
								folders(data);
							},
						    error : function(xhr, stat, err) {
						    	alert("다른 폴더명을 입력해주세요.");
						    	console.log(err);
						    }
						});
					});
					
	
					$(document).on('click','button#folderCancel',function(){
						$.getJSON('/folders/' + userId, function(data) {
							folders(data);
						});
					});
					
					$(document).on('click','button.folderModify',function(){
						var folderIndex =  $(this).attr('value');
						var newFolder = $("form#foldersForm"+ folderIndex).serializeObject();
						$.ajax({
							url :'folder/' + userId + "/" + folderIndex,
					        method : 'put',
							dataType : 'json',
							data : JSON.stringify(newFolder),
							processData : true,
							contentType : "application/json; charset=UTF-8",
							success : function() {
								$.getJSON('/folders/' + userId, function(data) {
									getfolderSelect(data);
									folders(data);
								});
							},
						    error : function(xhr, stat, err) {
						    	alert("다른 폴더명을 입력해주세요.");
						    	$.getJSON('/folders/' + userId, function(data) {
									folders(data);
								});
						    	console.log(err);
						    }
						});
					});
					
					$(document).on('click','button.folderDelete',function(){
						var result = confirm("정말로 지우시겠습니까?");
						if(result){
						var folderIndex =  $(this).attr('value');
							$.ajax({
								url :'folder/' + userId + "/" + folderIndex,
						        method : 'delete',
								success : function(data) {
									getfolderSelect(data);
									folders(data);
								}
							});
						} else {}
					});
					
					$(document).on('click','a.post',function(){
						var postIndex = $(this).attr('value');
						$.getJSON('/post/' + postIndex, function(data){
							post(data);
						});
					});
					$(document).on('click','button#postRemove',function(){
						var result = confirm("정말로 지우시겠습니까?");
						if(result){
						var postIndex =  $(this).attr('value');
							$.ajax({
								url :'post/' + postIndex,
						        method : 'delete',
								success : function(folderIndex) {
									$('div#post').empty();
									$('div#posts').empty();
									$('div#pages').empty();
									$('div#postAddBtn').append(
											"<button type='button' id='postAddBtn' value ='"+ folderIndex +"'>글쓰기</button>");
									$.getJSON('/pages/' + folderIndex, function(data) {
										if (data != 0) {
												getPosts(folderIndex, 1);
												getPages(folderIndex);	
											}
									});
								}
							});
						} else {}
					});
					
					$(document).on('click','button#postAddBtn',function(){
						$('div#folderManager').empty();
						$('div#postAddBtn').empty();
						$('div#posts').empty();
						$('div#pages').empty();
						$('div#post').empty();
						var folderIndex = $(this).attr('value');
						$('div#post').append(
							"<form id='postAddForm'><table class='table table-striped'>"
							+"<tr><td>제목</td>"
							+"<td><input type='text' name='postTitle' /></td></tr>"
							+"<tr><td colspan='2'><h5>첨부할 파일을 드래그 하세요.</h5>"
							+"<div class ='fileDrop'></div><div class='uploadedList'></div></td></tr>"
							+"<tr><td colspan='2'><textarea name='postContent'></textarea></td></tr>"
							+"</table></form>"
							+"<button type='button' id='postInsert' style='margin-left:800px;' value ='"+ folderIndex +"'> 등록</button>"
							+ "<br/><a href='javascript:;' id='toPosts' style='margin-left:790px;' value ='"+ folderIndex +"'>목록으로</a>"
							);
							
					});
					$(document).on("dragenter dragover",".fileDrop", function(event) {
						event.preventDefault();
					});
					
					$(document).on("drop",".fileDrop", function(event) {
						event.preventDefault();
		
						var files = event.originalEvent.dataTransfer.files;
						var file = files[0];
							
						var formData = new FormData();
						formData.append("file", file);

						$.ajax({
							url: '/uploadAjax',
							data: formData,
							dataType: 'text',
							processData: false, 
							contentType: false, 
							type: 'POST',
							success: function(data) {
								var str ="";
									
								if(checkImageType(data)) {
									str="<div><a target='_blank' class = 'fileNameHref' href='displayFile?fileName="+ getImageLink(data) + "'>"
									+"<img src='displayFile?fileName="+ data + "'></a>"
									+ "<small data-src='" + data +"'>X</small></div>";
								} else {
									str ="<div><a class = 'fileNameHref' href='displayFile?fileName="+ data + "'>" + getOriginalName(data) + "</a>"
									+ "<small data-src='" + data +"'>X</small></div>";
								}
								$(".uploadedList").append(str);
							}
						});
					});
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
					
					$(document).on('click','button#postInsert',function(){
						var folderIndex = $(this).attr('value');
						var postForm = $("form#postAddForm");
						alert($(".uploadedList .fileNameHref").attr('href'));
						var file = "<input type='hidden' name='postFile' value='" + $(".uploadedList .fileNameHref").attr('href') + "'>";
						postForm.append(file);
						var newPost = $("form#postAddForm").serializeObject();
						$.ajax({
							url :'post/' + folderIndex,
					        method : 'post',
							dataType : 'json',
							data : JSON.stringify(newPost),
							processData : true,
							contentType : "application/json; charset=UTF-8",
							success : function(data) {
								$('div#posts').empty();
								$('div#pages').empty();
								getPosts(folderIndex, 1);
								getPages(folderIndex);
								post(data);
							}
						});
					});
					
					$(document).on('click','a#toPosts',function(){
						$('div#folderManager').empty();
						$('div#postAddBtn').empty();
						$('div#post').empty();
						$('div#posts').empty();
						$('div#pages').empty();
						var folderIndex = $(this).attr('value');
							$('div#postAddBtn').append(
							"<button type='button' id='postAddBtn' value ='"+ folderIndex +"'>글쓰기</button>");
							$.getJSON('/pages/' + folderIndex, function(data) {
								if (data != 0) {
									getPosts(folderIndex, 1);
									getPages(folderIndex);
									
								}
							});
					});
					
					
					
					$('button#postSearchBtn').click(function(){
						$('div#folderManager').empty();
						$('div#postAddBtn').empty();
						$('div#posts').empty();
						$('div#pages').empty();
						$('div#post').empty();
						var postSearch = $('input#postSearch').val();
						$.getJSON('post/search/' + userId + "/" +postSearch, function(data){
							
							for (var i = 0; i < data.length; i++) {
								$('div#post').append(
										"<table class='table table-striped'><tr><th>" + data[i].postTitle + "</th></tr>" 
										+ "<tr><td style='text-align:right'>" + data[i].postDate+ "</td></tr>"
										+ "<tr><td>" + data[i].postFile+ "</td></tr>"
										+ "<tr><td style='word-break:break-all'><pre>" + data[i].postContent+ "</pre></td></tr>"
										+ "<tr><td style='text-align:right'><button type = 'button' id ='postModify' value ='"+ data[i].postIndex +"'>수정</button>"
										+ "<button type='button' id='postRemove' value ='"+ data[i].postIndex +"'>삭제</button></td></tr></table>");
							}
						}).error(function() { 
							$('div#post').append("<p>검색 결과가 없습니다.</p>");
							});
					});
				
			});
</script>
</head>
<body>
	<div>
		<header>
			<select id="folders" class="form-control" style="width : 140px;">
			</select>
			<h1>blog</h1>
			<div id="searchDiv" class="input-group">
				<input type="text" id="postSearch" class="form-control" /> 
				<span class="input-group-btn">
					<button type="button" id="postSearchBtn" class="btn btn-default">
 						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
				</button>
				</span>
			</div>

			<button type="button" id="folderManagerBtn" class="btn btn-primary">
			<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
			</button>
		</header>
		<section >
			<div id="folderManager"></div>
			<div id="postAddBtn"></div>
			<div id="posts"></div>
			<div id="pages"></div>
			<div id="post"></div>
		</section>
		<footer></footer>
	</div>
</body>
</html>