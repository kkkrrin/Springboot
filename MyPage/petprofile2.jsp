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
<link rel="stylesheet" href="resources/css/petInsert.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->

<script type="text/javascript">

		function sendIt(){
			
			var f = document.myForm;
	
			f.action = "<%=cp%>/petInsert_ok.do";
			
			f.submit();
	
		}

</script>

</head>
<body>


<main class="container page my" id="container">
<div>
	<div class="pet-wrap">
		
		<div class="pc-tit">
			<h2>마이펫 등록</h2>
		</div>
		<hr color="#669aff"/>

		<div><!-- 일단공백 --></div>
		
		<form action="" name="myForm" method="post">
		<div class="step-area">
										
			<p class="tit">
				마지막이에요!<br/>
					<span id="petNmArea">${info.petName }</span>에 대해 조금 더 알려주시겠어요?
			</p>
			<p class="sut-txt">
				추가정보를 등록하시면 다양한 맞춤추천을 받을 수 있어요!
			</p>
			
			<div class="member-input t2">
				<ul class="list">
					<li>
						<strong class="tit">중성화 여부</strong>
						<div class="radiobox">
							<span class="radio"><input type="radio" name="petNeuter" id="petNeuter" value="중성화 전"><label for="radt_1" class="txt">중성화 전</label></span>
							<span class="radio"><input type="radio" name="petNeuter" id="petNeuter" value="중성화 완료"><label for="radt_2" class="txt">중성화 완료</label></span>
						</div>
					</li>
					
					<li>
						<strong class="tit newDn">염려질환
							
						</strong>
						<div class="filter-item">
							<div id="diseaseArea" class="tag" onclick="setpetDisease();">
								<select class="Cinput_box" name="petDisease"  id="petDisease">
									<option value="피부">피부</option>
									<option value="눈">눈</option>
									<option value="눈물">눈물</option>
									<option value="관절">관절</option>
									<option value="치아">치아</option>
									<option value="모질">모질</option>
									<option value="소화기">소화기</option>
									<option value="체중">체중</option>
									<option value="노화">노화</option>
									<option value="신장">신장</option>
								</select>
							</div>
						</div>
					</li>
					
				<li>
					<strong class="tit">알러지여부</strong>
					<div class="radiobox">
						<span class="radio"><input type="radio" name="petAllergy" id="petAllergy" value="있어요"><label for="radt_1" class="txt">있어요</label></span>
						<span class="radio"><input type="radio" name="petAllergy" id="petAllergy" value="없어요"><label for="radt_2" class="txt">없어요</label></span>
					</div>
					
					<div>
						<div>
							<div id="petAllergy" >
								<select class="Cinput_box" name="petAllergy"  id="petAllergy">
									<option value="Null">해당사항 없음</option>
									<option value="소고기">소고기</option>
									<option value="유제품">유제품</option>
									<option value="물고기">물고기</option>
									<option value="양고기">양고기</option>
									<option value="밀">밀</option>
									<option value="닭">닭</option>
									<option value="옥수수">옥수수</option>
									<option value="달걀">달걀</option>
								</select>

							</div>
						</div>
					</div>
				</li>	
				</ul>
			</div>
			
			<div class="btnSet">
				<a href="" class="btn lg d"  onclick="" >이전</a>
				<button class="btn lg a" onclick="sendIt();">다음</button>
			</div>	
		</div>
		
		</form>			
	</div>
</div>
</main>

</body>
</html>