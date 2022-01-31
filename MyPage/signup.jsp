<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>

<script type="text/javascript">
	
	function sendIt(){
		f=document.myForm;
		
		f.action="<%=cp%>/signup_ok.do"; 
		f.submit(); 
	}
	
	
</script>

</head>
<body>

<h3>회원가입</h3>

<form action="" name="myForm" method="post" enctype="multipart/form-data">
	
					
	<p>
		프로필 업로드 : <input type="file" id="file" name="files"/>
	</P>
			
	
	<p>
		<label for="userId">아이디</label>
		<input type="text" id="userId" name="userId"/>
	</P>
	<p>
		<label for="userPwd">비밀번호</label>
		<input type="password" id="userPwd" name="userPwd"/>
	</P>
	<p>
		<label for="userMail">이메일</label>
		<input type="text" id="userMail" name="userMail"/>
	</P>
	<p>
		<label for="userNick">닉네임</label>
		<input type="text" id="userNick" name="userNick"/>
	</P>
	<p>
		<label for="userTag">관심태그</label>
		<input type="text" id="userTag" name="userTag"/>
	</P>
		

	<input type="button" value="회원가입" onclick="sendIt();"/>

</form>
	



</body>
</html>