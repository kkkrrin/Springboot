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
<link rel="stylesheet" href="resources/css/address.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/inquiry.css" type="text/css"/>


<script type="text/javascript">
	
	function sendIt(){
		
		var f = document.myForm;
		
		f.action = "<%=cp%>/Createinquiry_ok.do";
		
		f.submit();
		
	}

</script>


</head>
<body>
<main class="container page my" id="container">
<div class="pbd inquire">
		<div class="phd">
			<div class="in">
				<h1 class="tit">1:1 문의하기</h1>
			</div>
		</div>
		
		<form action="" name="myForm" method="post" enctype="multipart/form-data">
			<input type="hidden" name="cusNo" value="">
			<div class="pct" >
				<main class="poptents">		
					<div class="uiqnaset">
						<div class="inquire">
							
							<section class="sect" data-sid="ui-inputs">
								<div style="position: relative;">
									<span class="select-pop mo-t-fixed pc-option-w100 set">
									
										<select class="Cinput_box" name="inquiryCate"  id="inquiryCate" data-select-title="문의 유형선택">
											<option value="">유형을 선택해주세요</option>
											<option value="회원/정보관리">회원/정보관리</option>
											<option value="주문/결제">주문/결제</option>
											<option value="배송">배송</option>
											<option value="취소/교환/반품/환불">취소/교환/반품/환불</option>
											<option value="영수증/증빙서류">영수증/증빙서류</option>
											<option value="상품/이벤트">상품/이벤트</option>
											<option value="기타">기타</option>
										</select>
									</span>
								</div>
							</section>
						</div>
						<div class="set memo">
							<div class="textarea">
								<textarea id="inquiryContent" name="inquiryContent" placeholder="문의 내용을 입력해 주세요 (최소 10자 이상)"></textarea>
							</div>
						</div>
						<div class="set file t2">
							<div>
									
								<p>
								 사진첨부하기 : <input type="file" id="file" name="files" />
								 <input type="hidden" id="file"  name="files" value="files"/>
								 <!-- 사진 안첨부하는 방법 ?????? -->
								</P>
								
							</div>
						
						</div>
					
						<div class="set gud onWeb_b">
							<div class="hdt">
								<span class="tit">유의사항</span>
							</div>
							<div class="cdt info-txt t3" data-ui-tog="ctn open" data-ui-tog-val="tog_guds_1">
								<ul>
									<li>개인정보가 포함된 문의 시 비공개로 문의해 주시기 바랍니다.</li>
									<li style="margin-top:0px;">저작권 침해, 음락, 욕설, 비방글, 판매, 광고 및 홍보성의 글은 임의로 삭제 처리 될 수 있습니다.</li>
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
		
		
	</div>
</main>
</body>
</html>