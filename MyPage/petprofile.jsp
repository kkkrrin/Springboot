<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="resources/css/petInsert.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->


<script type="text/javascript">

		function sendIt(){
			
			var f = document.myForm;
			
			f.action = "<%=cp%>/petInsert2.do";
			
			f.submit();
			
		}

</script>

</head>
<body>

<div>
	
	<div class="pet-wrap">	
		<div class="pc-tit">
			<h2>마이펫 등록</h2>
		</div>	
		
		<hr color="#669aff"/>
		
		<div class="step-area mt10">
		<c:if test="${info.petCate=='강아지' }">
			<p class="tit"> <span id="spanPetGb">강아지</span>프로필을 채워주세요</p>
			<form action="" name="myForm" method="post" enctype="multipart/form-data">
				<div class="my-picture medium2 animal mt40">
					
					<p class="picture">
						 <input type="file" id="file" name="files" class="btn edit"/>
					</p>
					
				</div>
				
				<div class="member-input mt40">
					<ul class="list">
						<li>
							<strong id="petGbNmArea" class="tit">견종</strong>
							<p class="info">*필수 입력 정보</p>
							
							<div class="input del">
								<input type="text" id="petKind" name="petKind" placeholder="견종을 입력하세요"/>
							</div>
						</li>
						
						<li>
							<strong class="tit requied">이름</strong>
							<div class="input del">
							<input type="text" id="petName" name="petName" class="required_item" placeholder="이름을 입력해주세요.">
							</div>
						</li>
						 
						<li>
							<strong class="tit mb10">성별</strong>
							<div class="flex-rap">
							<label><input type="radio" name="petGender" value="male"><span class="txt">수컷</span></label>
							<label><input type="radio" name="petGender" value="female"><span class="txt">암컷</span></label>
							</div>
						</li>
						
						<li>
							<strong class="tit requied mt11 mb10">나이</strong>
							
								<div class="flex-wrap">
									<div class="input del t2">
										<input type="text" id="petAge" name="petAge"  placeholder="0">
										<div class="inputInfoTxt">세</div>
									</div>	
								</div>
							
						</li>
						
						<li>
							<strong class="tit">몸무게</strong>
							<div class="flex-wrap">
							<div class="input del t2">
								<input type="text" id="petWeight" class="moveStop" name="petWeight">
								<div class="inputInfoTxt">kg</div>
							</div>
							</div>
						</li>
					</ul>
				</div>
				
				<div class="btnSet pull">
					<a href=""  class="btn lg d" onclick="">이전</a>
					<button id="nextBtn1" class="btn lg gray" onclick="sendIt();">다음</button>
				</div>
			
			
			</form>
	
		</c:if>
		
		
		<c:if test="${info.petCate=='고양이' }">
			<p class="tit"><span id="spanPetGb">고양이</span>프로필을 채워주세요</p>
			
			<form action="" name="myForm" method="post" enctype="multipart/form-data">
				<div class="my-picture medium2 animal mt40">
					
					<p class="picture">
						 <input type="file" id="file" name="files" class="btn edit"/>
					</p>
					
				</div>
				
				<div class="member-input mt40">
					<ul class="list">
						<li>
							<strong id="petGbNmArea" class="tit">묘종</strong>
							<p class="info">*필수 입력 정보</p>
							
							<div class="input del">
								<input type="text" id="petKind" name="petKind" placeholder="묘종을 입력하세요"/>
							</div>
						</li>
						
						<li>
							<strong class="tit requied">이름</strong>
							<div class="input del">
							<input type="text" id="petName" class="required_item" name="petName" placeholder="이름을 입력해주세요.">
							</div>
						</li>
						 
						<li>
							<strong class="tit mb10">성별</strong>
							<div class="flex-rap">
							<label><input type="radio" name="petGender" value="male"><span class="txt">수컷</span></label>
							<label><input type="radio" name="petGender" value="female"><span class="txt">암컷</span></label>
							</div>
						</li>
						
						<li>
							<strong class="tit requied mt11 mb10">나이</strong>
							
								<div class="flex-wrap">
									<div class="input del t2">
										<input type="text" id="petAge" name="petAge"  placeholder="0">
										<div class="inputInfoTxt">세</div>
									</div>	
								</div>
							
						</li>
						
						<li>
							<strong class="tit">몸무게</strong>
							<div class="flex-wrap">
							<div class="input del t2" data-txt="kg">
								<input type="text" id="petWeight" class="moveStop" name="petWeight">
								<div class="inputInfoTxt">kg</div>
							</div>
							</div>
						</li>
					</ul>
				</div>
				
				<div class="btnSet pull">
					<a href=""  class="btn lg d" onclick="">이전</a>
					<button id="nextBtn1" class="btn lg gray" onclick="sendIt();">다음</button>
				</div>
			
			</form>
			
		</c:if>
		
		
		
		</div>
	</div>
</div>
</body>
</html>