<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script language="javascript">
	var idnum = 0;
	var pwnum = 0;
	$(document).ready(function(){
		//var $form = $( "form#joinForm");
		//var id = $form.find( 'input[name="userId"]' ).val();
		$('#id').keyup(function(){
			var id = $('#id').val();  
			//alert(id);  
			//alert($('#id').val());
			$.ajax({
				url:'userIdDuplicationCheck',
				type:'post', 
				data:{userId:$('#id').val()}, 
				//data:JSON.stringify({userId:id}),
				//processData:true,
				//contentType:"application/json; charset=UTF-8",
				success : function(data) {
					if(data == 1){
						//alert(data);
						//alert("사용가능한 아이디입니다.");
						$('#idCheckText').html("<b><font color=green size=4pt> 사용가능 </font></b>");   
						idnum = 1;
					} 
					else {
						$('#idCheckText').html("<b><font color=red size=4pt> 사용불가 </font></b>"); 
						//alert("중복된 아이디입니다.");
						idnum = 0;
					}
				}
			});
		});
	});
	
/*	function idCheck() {
		alert("aa");
		var id = $('#id').val();
		alert("dd");
		$.ajax({
			url:'userIdDuplicationCheck',
			type:'post',
			data:{userId:id},
			success:function(data){
				if($.trim(data) == null){
					$('#idCheckText').html("<b><font color=red size=5pt> 사용가능 </font></b>");   
					idnum = 1;
				}
				else{
					$('#idCheckText').html("<b><font color=red size=5pt> 사용불가 </font></b>");   					
					idnum = 0;
				}
			}
		})
	}
	*/
	function passwordCheck() {
		var userPw = document.getElementById("pw").value;
		var userPw_check = document.getElementById("pwCheck").value;
		//alert("pass");
		//alert(userPw);

		if (userPw_check == "") {
			document.getElementById("pwCheckText").innerHTML = ""
			//alert("000");
			pwnum = 0;
			return false;
		} else if (userPw != userPw_check) {
			document.getElementById("pwCheckText").innerHTML = "<b><font color=red size=4pt> not ok </font></b>"
			//alert("111");
			pwnum = 0;
			return false;
		} else {
			document.getElementById("pwCheckText").innerHTML = "<b><font color=green size=4pt> ok </font></b>"
			//alert("222");
			pwnum = 1;
			return true;
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
	<%
		//session.invalidate();
		//session.removeAttribute("id_falg");
		//out.println(session.getAttribute("id_flag").toString());
	%>
	<h1>회원가입</h1>
	<form id="joinForm" action="join_1" method="post" onsubmit="return next();">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="userId" id="id"></td>
				<td id="idCheckText" width="100"></td>
				<button type="button" id="idCheck" >중복체크</button>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="userPw" id="pw"
					oninput="passwordCheck()"></td>
			</tr>
			<tr>
				<td>비밀번호 확인</td>
				<td><input type="password" name="userPw_check" id="pwCheck"
					oninput="passwordCheck()"></td>
				<td id="pwCheckText" width="100"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="userName"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="userEmail"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="다음">
					<input type="reset" value="다시작성"></td>
			</tr>
		</table>
	</form>

</body>
</html>