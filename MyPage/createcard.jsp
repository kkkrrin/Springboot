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
		
		f.action = "<%=cp%>/createCard_ok.do";
		
		f.submit();
		
	}

</script>

</head>
<body>

	<h3>카드 등록하기</h3>
	
		<form action="" name="myForm" method="post" enctype="multipart/form-data">
			<div class="pct" >
				<main class="poptents">		
					<div class="uiqnaset">
						<div class="inquire">
							
							<section class="sect" data-sid="ui-inputs">
								<div style="position: relative;">
									<span class="select-pop mo-t-fixed pc-option-w100 set">
									<p>카드를 선택해주세요.</p>
										<select class="sList" name="cardName"  id="cardName" data-select-title="문의 유형선택">
											<option value="">카드를 선택해주세요</option>
											<option value="농협카드">농협카드</option>
											<option value="KB국민카드">KB국민카드</option>
											<option value="롯데카드">롯데카드</option>
											<option value="우리카드">우리카드</option>
											<option value="신한카드">신한카드</option>
											<option value="삼성카드">삼성카드</option>
											<option value="현대카드">현대카드</option>
											<option value="BC카드">BC카드</option>
										</select>
									</span>
								</div>
							</section>
						</div>
						
						<div class="set file t2">
							<div>	
								<p>
								 실물카드첨부 : <input type="file" id="file" name="files" />
								 <!-- 사진 안첨부하는 방법 ?????? -->
								</P>
							</div>
						</div>
						
						<li class="user">
							<div class="hdt">카드에 기재되어있는 이름을 입력해주세요</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="userName" placeholder="이름을 입력해주세요" >
								</div>
							</div>
						</li>
						
						<li class="user">
							<div class="hdt">카드에 기재되어있는 유효기간을 입력해주세요</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="cardValid" placeholder="유효기간을 입력해주세요" >
								</div>
							</div>
						</li>
							
						<li class="user">
							<div class="hdt">카드번호 16자리를 입력해주세요</div>
							<div class="cdt">
								<div class="input del">
									<input type="text" name="cardNum" placeholder="카드번호를 입력해주세요" >
								</div>
							</div>
						</li>
						
					
						<div class="set gud onWeb_b">
							<div class="hdt">
								<span class="tit">유의사항</span>
							</div>
							<div class="cdt info-txt t3" data-ui-tog="ctn open" data-ui-tog-val="tog_guds_1">
								<ul>
									<li>카드 사진은 앞면 사진만 첨부 부탁드립니다.</li>
									<li style="margin-top:0px;">저작권 침해, 음락, 욕설, 비방글, 판매, 광고 및 홍보성의 사진 임의로 삭제 처리 될 수 있습니다.</li>
								</ul>
							</div>
						</div>

					</div>				
				</main>
			</div>
			
			<div class="pbt fixed onWeb_b">
			<div class="bts">
				<button type="button" class="btn xl" onclick="">취소</button>
				<button type="button"  class="btn xl a insertBtn disabled" onclick="sendIt();">등록</button>
			</div>
		</div>
		</form>
		

</body>
</html>