<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="resources/css/layout.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/pwdcheck.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->



<script type="text/javascript">
	
	function sendIt(){
		
		var f = document.myForm;
		
		f.action = "<%=cp%>/update.do";
		
		f.submit();
		
	}

</script>

</head>
<body>


<main class="container page login srch" id="container">


			<div class="inr" style="min-height: 503.097px;">
	
				<div class="contents" id="contents">

					<div class="pc-tit">
						<h2>비밀번호 확인</h2>
					</div>


		<div class="fake-pop">
			<div class="result">
				<p class="sub">회원님의 소중한 개인정보 보호를 위해<br> 비밀번호 확인 후 변경이 가능합니다.</p>
			</div>
			<form action="" name="myForm" method="post">	
			<div class="member-input email mt60">
			
				<ul class="list">
					<li>
						<div class="input del">
							<input type="password" id="userPwd" name="userPwd" placeholder="비밀번호를 입력해주세요" maxlength="20"/>
						</div>
						
					</li>
				</ul>
			
			</div>
			<div class="pbt mt30">
				<div class="btnSet" id="confirmBtn">
					<button class="btn lg gray" onclick="sendIt();">확인</button>
				</div>
			</div>
			</form>
		</div>
	</div>
</div>
</main>


</body>
</html>