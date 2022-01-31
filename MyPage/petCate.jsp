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
<link rel="stylesheet" href="resources/css/petInsert.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->



<script type="text/javascript">

	function sendIt(){
		
		var f = document.myForm;
		
		f.action = "<%=cp%>/petInsert1.do";
		
		f.submit();
		
	}

</script>

</head>

<body>


<form action="" name="myForm" method="post">
<main class="container page my" id="container">
<div>
	<div class="pet-wrap">
		
		<div class="pc-tit">
			<h2>마이펫 등록</h2>
		</div>
		<hr color="#669aff"/>

		<div class="step-area">
			<p class="tit">어떤 <span>반려동물</span>과 함께하시나요?</p>

			<div class="step">
				<div class="choice">
					<ul>
						<li>
							<label class="radio">
								<input type="radio" class="required_item" name="petCate" id="petCate" value="강아지">
								<span>강아지</span>
							</label>
						</li>
						<li>
							<label>
								<input type="radio" class="required_item" name="petCate" id="petCate" value="고양이">
								<span>고양이</span>
							</label>
						</li>
					</ul>
				</div>
			</div>
			<div class="btnSet pull">
				<a href="" onclick="" class="btn lg d">취소</a>
				<button id="nextBtn1" class="btn lg gray"  onclick="sendIt();">다음</button>
			</div>
		</div>
	</div>
</div>
</main>
</form>

</body>
</html>