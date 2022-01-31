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
<title>Insert title here</title>

<link rel="stylesheet" href="resources/css/layout.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/address.css" type="text/css"/>
<link rel="stylesheet" href="resources/css/inquiry.css" type="text/css"/>

</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>


<main class="container lnb page my" id="container">
			<div class="inr" style="min-height: 482.097px;">
				<!-- 본문 -->
				<div class="contents" id="contents" style="min-height: 655.347px;">
		
					<div class="pc-tit">
						<h2>고객 문의</h2>
					</div>
				
					<div class="swiper-container tabSwiper custmHelp swiper-container-fade swiper-container-initialized swiper-container-horizontal swiper-container-autoheight">
						<div class="swiper-pagination swiper-pagination-clickable swiper-pagination-bullets" id="viewGbDiv">
							<li class="swiper-pagination-bullet swiper-pagination-bullet-active">
							<span>1:1 문의</span>
							</li>
							<li class="swiper-pagination-bullet" >
							<span>상품 Q&amp;A</span>
							</li>
						</div>
						
						
							
						<div class="swiper-wrapper" style="height: 328px;">
							<div class="swiper-slide swiper-slide-active" style="width: 1010px; opacity: 1; transform: translate3d(0px, 0px, 0px); min-height: 328.111px;">
								<div class="infoBox flex">
									<span class="listCount">총 <strong class="b" id="iqrCount">${inquiryCount }건 &nbsp;&nbsp;</strong></span>
									<button type="button" class="btn b mlA btn_inquiry"  onclick="javascript:location.href='<%=cp%>/Createinquiry.do'"> 문의하기</button>
								</div>
								
								<c:if test="${inquiryCount>0 }"> 
								<c:forEach var="addto" items="${lists }">
								<!-- 리스트 -->
								<div class="my-accd s0420" id="iqr_myAccd" style="display: block;">	
									<div class="uiqnalist">
										<ul class="uiAccd qalist" id="uiIqr">
											<li name="inquiryNm">
													<div class="hBox">
														<div class="tit" name="inquiryContent" >${addto.inquiryContent }</div>
														<div class="info">
															<div class="dds">
																<em class="dd date">${addto.created }</em> <em class="dd rep com">답변대기</em>
															</div>
															<div class="stat">
																<div class="uidropmu dmenu">
																	<div class="list">
																		<ul class="menu">
																			<li><button type="button" class="bt" onclick="javascript:location.href='<%=cp%>/deleteInquiry.do?userId=${addto.userId}&inquiryContent=${addto.inquiryContent}'">삭제</button></li>
																		</ul>
																	</div>
																</div>
															</div>
														</div>
													</div>
													<div class="cBox" style="display: none;"></div>
												</li>
											</ul>
									</div>
								</div>
								</c:forEach>
								</c:if>
								
								<c:if test="${inquiryCount==0 }"> 
								<div class="no_data auto_h i1 view" id="iqr_noData">
									<div class="inr">
										<div class="msg">등록된 문의글이 없습니다.</div>
									</div>
								</div>
								</c:if>
								
							</div>
						</div>
		
				</div>
			</div>	
		</div>
		
</main>





















<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>