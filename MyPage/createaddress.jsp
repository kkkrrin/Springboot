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

<script type="text/javascript">
	
	function sendIt(){
		
		var f = document.myForm;
		
		f.action = "<%=cp%>/createAddress_ok.do";
		
		f.submit();
		
	}

</script>

</head>
<body>
<main class="poptents">
			<form name="myForm" method="post">
				<div class="uidelivsets">
					<ul class="list" id="addressul">
						
						<li class="name">
							<div class="hdt">배송지 명칭</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="addressAlias" placeholder="배송지 명칭을 입력해주세요 ex)회사" value="">
								</div>
							</div>
						</li>
						
						<li class="name">
							<div class="hdt">Country/Region</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="Region" placeholder="나라" value="">
								</div>
							</div>
						</li>
						<li class="user">
							<div class="hdt">Full name</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="userName" placeholder="이름을 입력해주세요" >
								</div>
							</div>
						</li>
						
						<li class="user">
							<div class="hdt">Street address</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="streetAddress" placeholder="주소를 입력하세요" value="">
								</div>
							</div>
						</li>
						
						<li class="user">
							<div class="hdt">detail address</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="detailAddress" placeholder="상세주소를 입력하세요" value="">
								</div>
							</div>
						</li>
						
						<li class="user">
							<div class="hdt">City</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="City" placeholder="도시를 입력해주세요"value="">
								</div>
							</div>
						</li>
					
					
						<li class="phone">
							<div class="hdt">Phone number</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="phoneNumber" placeholder="번호를 입력해주세요" title="휴대폰번호" value="">
								</div>
							</div>
						</li>
				

					</ul>
				</div>
			
				<div class="btnList bot">
					<button type="button" onclick="" class="btn lg d">취소</button>
					<button type="button" id="addBtn" onclick="sendIt();" class="btn lg a disabled">저장</button>
				</div>
				</form>
			</main>
</body>
</html>