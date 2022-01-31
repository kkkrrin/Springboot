<%@ page contentType="text/html; charset=UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>about Pet</title>
<link rel="stylesheet" href="resources/css/layout.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/infoupdate.css" type="text/css"/>
<link rel="stylesheet" type="text/css" href="resources/css/nextInfo.css">
<link rel="stylesheet" href="resources/css/test.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->

<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>



<script type="text/javascript">

		function sendIt(){
			
			var f = document.myForm;
			
			f.action = "<%=cp%>/update_ok.do";
			
			f.submit();
			
		}

</script>


</head>
<body>

<main class="container page login srch" id="container">
<div class="inr" style="min-height: 589.082px;">
	<!-- 본문 -->
	<div class="contents" id="contents">
		<div class="pc-tit">
			<h2>회원정보 수정</h2>
		</div>

		<div class="fake-pop">
			<div class="pct">
				<div class="poptents">
					<!-- 프로필 사진 -->
					<form action="" name="myForm" method="post" enctype="multipart/form-data">
					
					<div class="my-picture">
						<p class="picture">
							<input type="file" id="file" name="files" value="${dto.saveName }" class="btn edit"/>
						</p>
					</div>
		
				<div class="member-input">
						<ul class="list">
								<li>
									<strong class="tit requied">아이디</strong>
									<p class="info">필수 입력 정보</p>
									<div class="input disabled coms">
										<input type="text" id="userId" value="${dto.userId }" name="userId" readonly="readonly"/>
									</div>
								</li>
								
								<li>
									<strong class="tit">이메일</strong>
									<div class="input disabled">
										<input type="text" id="userMail" value="${dto.userMail }" name="userMail"/>
									</div>
								</li>
								
								<li>
									<strong class="tit requied">닉네임</strong>
									<div class="input coms">
										<input type="text" id="userNick" value="${dto.userNick }" name="userNick"/>
									</div>
								</li>
							
								<input type="hidden" value="${dto.num }"/>

							<div class="pbt pull">
							
								<div>
									<button id="nextBtn1" class="btn lg gray" onclick="sendIt();">다음</button>
								</div>
					
					
							</div>
							
						</ul>
					</div>
					
				</form>
				</div>
			</div>
				
					<div class="btn-area">
						<a href="<%=cp %>/leavepet.do">회원탈퇴</a>
					</div>
					
			</div>
		</div>
	</div>
</main>
</body>
</html>