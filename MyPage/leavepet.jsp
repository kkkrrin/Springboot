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
<link rel="stylesheet" href="resources/css/leavepet.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->

</head>
<body>
<main class="container page sett" id="container">

	
	<div class="inr" style="min-height: 402.047px;">
		<!-- 본문 -->
		<div class="contents" id="contents">
	
		
			<div class="pc-tit">
				<h2>회원탈퇴</h2>
			</div>
		
			<div class="fake-pop">
	
				<div class="member-input">
					<ul class="list">
						<li>
							<strong class="tit">회원탈퇴 안내</strong>
							<div class="dot-txt">
								<p>회원탈퇴후 24시간 동안 재가입이 안됩니다. 24시간 지난 후 신규 회원가입이 가능합니다.</p>
								<p>회원탈퇴시, 회원님께서 보유하신 비현금성 포인트와 쿠폰 등은 모두 삭제됩니다.</p>
								<p>진행중인 거래 내역이 있거나, 현금성 포인트 등이 있다면 즉시 탈퇴가 불가능합니다.</p>
							</div>
						</li>
					</ul>
					<ul class="list bt-line">
						<li>
							<strong class="tit">회원탈퇴 사유</strong>
							<div class="flex-wrap">
									<label class="radio"><input type="radio" name="mbrLevRsnCd" value="10"><span class="txt">할인혜택 부족</span></label>
								</div>
							<div class="flex-wrap">
									<label class="radio"><input type="radio" name="mbrLevRsnCd" value="20"><span class="txt">상품가격과 품질 불만</span></label>
								</div>
							<div class="flex-wrap">
									<label class="radio"><input type="radio" name="mbrLevRsnCd" value="30"><span class="txt">교환/환불 불만</span></label>
								</div>
							<div class="flex-wrap">
									<label class="radio"><input type="radio" name="mbrLevRsnCd" value="40"><span class="txt">A/S불만</span></label>
								</div>
							<div class="flex-wrap">
									<label class="radio"><input type="radio" name="mbrLevRsnCd" value="50"><span class="txt"> 개인정보의 노출 우려</span></label>
								</div>
							<div class="flex-wrap">
									<label class="radio"><input type="radio" name="mbrLevRsnCd" value="60"><span class="txt">서비스이용안함</span></label>
								</div>
							<div class="flex-wrap">
									<label class="radio"><input type="radio" name="mbrLevRsnCd" value="90"><span class="txt">기타</span></label>
								</div>
							</li>
					</ul>
				</div>
				<div class="textarea">
					<textarea name="mbrLevContent" placeholder="탈퇴사유를 입력해주세요." style="height:200px;"></textarea>
				</div>
				
				<div class="pbt mt20">
					<strong class="tit">정말 회원탈퇴 하시겠습니까?</strong>
					<div class="btnSet mt24">
						<a href="<%=cp %>/mypage.do" class="btn lg a base" >취소</a>
						<a href="<%=cp %>/leavepet_ok.do" class="btn lg a base">회원탈퇴</a>
						
					</div>
			</div>
		</div>
	</div>
</main>


</body>
</html>