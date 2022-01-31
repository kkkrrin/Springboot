<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


<script type="text/javascript">
	
	function sendIt(){
		
		var f = document.myForm;
		
			
		if(!f.userId.value){
			
			alert("아이디를 입력하세요.");
			f.userId.focus();
			return;
			
		}
		if(!f.userPwd.value){
			
			alert("비밀번호를 입력하세요.");
			f.userPwd.focus();
			return;
			
		}
		
		f.action = "<%=cp%>/login_ok.do";
		
		f.submit();
		
	}

</script>


</head>
<body>
<h3>로그인</h3>

	<div>
		<form action="" method="post" name="myForm">
		<div>
			아이디<input type="text" name = "userId">
		</div>
		<div >
			비밀번호<input type="password" name = "userPwd" >
		</div>
		<div >
			<a  onClick="sendIt();">로그인</a>
		</div>
		</form>

	</div>
</body>
</html>