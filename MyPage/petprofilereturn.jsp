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
<link rel="stylesheet" href="resources/css/petprofilereturn.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->


</head>
<body>
<main class="container lnb page my" id="container">
<div class="inr" style="min-height: 483.097px;">
	
	<div class="contents" id="contents" style="min-height: 656.458px;">
		<div class="mypet-admin">
			
			<div class="pc-tit">
				<h2>마이펫 정보</h2>
			</div>	
			
				<div class="mypet-profile">
					<div>
						<div class="img-box">
							<div>
								<div class="my-picture medium1">
									<p class="picture">
									<img src="upload/${dto.saveName }" alt="펫사진${dto.saveName }" style="height: 100%;width: 100%;">
									</p>
									<!-- <button class="btn edit"></button> -->
								</div>
								<p class="name">${dto.petName }</p>
							</div>
						</div>
						
						<div class="t-box">
							<div class="item">
								<p class="tit">기본 정보</p>
								<div class="g-box">
									<ul>
										<li>
											<p class="t1">
												${dto.petCate }</p>
											<p class="t2">
												${dto.petKind }</p>
										</li>
										<li>
											<p class="t1">나이</p>
											<p class="t2">${dto.petAge }</p>
										</li>
										<li>
											<p class="t1">성별</p>
											<p class="t2">
												${dto.petGender}</p>
										</li>
										<li>
											<p class="t1">몸무게</p>
											<p class="t2">
												${dto.petWeight}</p>
										</li>
									</ul>
								</div>
							</div>
							
							<div class="item">
								<p class="tit">건강 정보</p>
								<div class="g-box">
									<ul>
										<li>
											<p class="t1">중성화</p>
											<p class="t2">
												${dto.petNeuter }</p>
										</li>
										<li>
											<p class="t1">염려질환</p>
											<p class="t2">
												${dto.petDisease}</p>
										</li>
										<li>
											<p class="t1">알러지</p>
											<p class="t2">${dto.petAllergy }
											</p>
										</li>
									
									</ul>
								</div>
							</div>
						</div>  
					</div>
					
					<div class="btnSet set1">
							<a href="<%=cp %>/mypage.do" class="btn lg a">완료</a>
					</div>
				</div>
				
			</div>
		</div>
	</div>

</main>
</body>
</html>