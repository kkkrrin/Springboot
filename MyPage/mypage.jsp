<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("UTF-8");
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mypage</title>
<link rel="stylesheet" href="resources/css/layout.css" type="text/css"/>
<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>
<!-- 강사님꺼 -->


<script type="text/javascript">
	
	function sendIt(){
		
		var f = document.myForm;
		
		f.action = "<%=cp%>/petprofilereturn.do";
		
		f.submit();
		
	}

</script>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>


<h3> 마이페이지 </h3><br/>

<main class="container lnb page my home" id="container">

<div class="inr" >
	<div class="contents" id="contents" >
		<section class="sect top">
			<dl>
				<dt class="pic">
					<img class="img" alt="힝" src="mypage/${dto.saveName }" style="width: 100px; height: 100px;">
				</dt>
				<dd class="info-bx">
					<p>
						<span class="nm">${dto.userNick }님</span>
						<button class="btn re" onclick="javascript:location.href='<%=cp%>/updatecheck.do'">수정</button>
					</p>
					<ul class="data">
						<li>
							<span>등급</span>
			
							<i>${dto.grade }</i>
							
						</li>
						
						<li>
							<span>POINT</span>
							 ${dto.point }
						</li>
					</ul>
				</dd>
			</dl>
		</section>
		
		<section class="myinfo">
			<div class="sect line">
				<dl class="my">
					<dt>
						<strong><span>마이펫 관리</span></strong>
					</dt>
					
					<c:if test="${petCount==0 }"> 
					
					<dd>
						<div class="pet">
							
							<p class="ex">
							"마이펫 등록하고&nbsp;"
							<span>예방접종 내역을 관리하세요.</span>
							</p>
							<button class="btn add" onclick="javascript:location.href='<%=cp%>/petInsert.do'">
								<i class="fas fa-dog">마이펫 등록하기</i>
							</button>
						</div>
					</dd>
					</c:if>
			
					<c:if test="${petCount>0 }"> 
					<div>
							
						<form action="" name="myForm" method="post">
						<ul class="img-set">
						
							<c:forEach var="petdto" items="${lists }">
							<li>
							
								<input type="hidden" name="petName" value="${petdto.petName }" > 
								<a href="/petprofilereturn.do?petName=${petdto.petName }"><img src="mypage/${petdto.saveName }" alt="${petdto.petName }" name="${petdto.petName }" style="width: 50px; height: 50px;"/></a>

							</li>
							</c:forEach>	
						</ul>
						</form>
						
						<button class="btn re" onclick="javascript:location.href='<%=cp%>/petInsert.do'">마이펫 추가하기</button>
						
					</div>
					</c:if>
				</dl>
			</div>

			<dl class="zzim">
				<dt>
					<strong><span>마이 찜 리스트</span></strong>
				</dt>
				<dd>
					<a href="/MyzzimList.do?categoryName=TV">전체보기</a>
					<ul class="zzim-list">
						 
						<li>
							<b>TV</b>
							<a href="/MyzzimList.do?categoryName=TV">${TvCount }</a>
						</li>
						
						<li>
							<b>로그</b> 
							<a href="/MyzzimList.do?categoryName=community">${LogCount }</a>
						</li>
						
						<li>
							<b>스토어</b>
							<a href="/MyzzimList.do?categoryName=store">${StoreCount }</a>
						</li>
					</ul>
				</dd>
			</dl>
			
			<dl class="sect watch">
				<dt>
					<a href="">
					<strong><span>최근 본 영상</span></strong>
					<span class="fas fa-angle-double-right">전체보기</span>
					</a>
				</dt>
				
				<!-- 영상이 없으면 -->
				
				<dd>
					<p>최근 시청한 영상이 없습니다. 반려동물과 함께하는 펫TV를 시청해보세요!</p>
				</dd>
			</dl>
			
			<dl class="sect shop line">
				<dt>
					<strong><span>나의 쇼핑정보</span></strong>
				</dt>
				<dd>
				
					<ul class="coupon">
						<li>
							<i class="fas fa-gift"></i>
							<a class="my1" href="/mycoupon.do">
							<span>내쿠폰</span>
							</a>
						</li>
						
						<li>
							<i class="far fa-clock"></i>
							<a class="my2"  href="">
							<span>최근본상품</span>
							</a>
						</li>
						
						<li>
							<i class="far fa-bookmark"></i>
							<a class="my3" href="<%=cp%>/jArticle.action">
							<span>찜한상품</span>
							</a>
						</li>
						<li>
							<i class="fas fa-shopping-cart"></i>
							<a class="my4" href="/WishList.do">
							<span>장바구니</span>
							</a>
						</li>
					</ul>	
					<div class="barwarp">
						<ul class="bar">
							<li>
							<a href="" >
							<span class="tit">주문/배송</span>   
							<span class="fas fa-angle-right"></span>
							
							</a>
							</li>
							
							<li>
							<a href="">
							<span class="tit">취소/반품/교환</span>
								<span class="fas fa-angle-right"></span>
							
							</a>
							</li>
							
							<li>
							<a href="">
							<span class="tit">재입고 알림</span>
								<span class="fas fa-angle-right"></span>
							
							</a>
							</li>
									
							<li>
							<a href="" >
							<span class="tit">상품후기</span>
								<span class="fas fa-angle-right"></span>
							
							</a>
							</li>
				
						</ul>
						
						<ul class="bar line">
							<li>
							<a href="/inquiry.do" >
							<span class="tit">고객 문의</span>
								<span class="fas fa-angle-right"></span>
							
							</a>
							</li>
							
							<li>
							<a href="/addressInfo.do" >
							<span class="tit">배송지 관리</span>
								<span class="fas fa-angle-right"></span>
							
							</a>
							</li>
							
							<li>
							<a href="/pwdcheck.do">
							<span class="tit">비밀번호 설정</span>
								<span class="fas fa-angle-right"></span>
							
							</a>
							</li>
							
							<li>
							<a href="/cardinfo.do" >
							<span class="tit">결제카드 관리</span>
								<span class="fas fa-angle-right"></span>
							</a>
							</li>
						</ul>
					</div>
					
					<div class="line mt25" style="cursor:pointer;" onclick="">
						<div class="invite">
							<span class="ex">어바웃펫에 <em>친구를 초대 </em>하고
							<span class="block">친구와 함께 혜택을 받아보세요</span>
							</span>
						</div>
					</div>
				</dd>
			</dl>
		</section>
	</div>
</div>
</main>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>