<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script language="javascript">
	var nicknum = 0;
	$(document).ready(function(){
		//var $form = $( "form#joinForm");
		//var id = $form.find( 'input[name="userId"]' ).val();
		$('#nick').keyup(function(){
			var nick = $('#nick').val();  
			//alert(nick);
			//alert($('#id').val());
			$.ajax({
				url:'nickNameDuplicationCheck',
				type:'post', 
				data:{nickName:$('#nick').val()}, 
				//data:JSON.stringify({userId:id}),
				//processData:true,
				//contentType:"application/json; charset=UTF-8",
				success : function(data) {
					if(data == 1){
						//alert(data);
						//alert("사용가능한 아이디입니다.");
						$('#nickNameCheckText').html("<b><font color=green size=4pt> 사용가능 </font></b>");   
						nicknum = 1;
					} 
						else {
						$('#nickNameCheckText').html("<b><font color=red size=4pt> 사용불가 </font></b>"); 
						//alert("중복된 아이디입니다.");
						nicknum = 0;
					}
				}
			});
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
</script>

</head>
<body>
	<h1>회원가입</h1>
	<form action="user" method="post">
		<table>
			<tr>
				<td>닉네임</td>
				<td><input type="text" name="nickName" id="nick"></td>
				<td id="nickNameCheckText" width="100"></td>
			</tr>
			<tr>
				<td>블로그 이름</td>
				<td><input type="text" name="blogName"></td>
			</tr>
			<tr>
				<td>프로필 소개</td>
				<td><input type="text" name="profileIntro"></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="회원가입">
					<input type="reset" value="다시작성"></td>
			</tr>
		</table>
	</form>

</body>
</html>