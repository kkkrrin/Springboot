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

</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>


<div class="list-wrapper">
     
      <c:if test="${count==0 }">
		
		<main class="container lnb page my" id="container">
			<div class="inr" style="min-height: 482.097px;">			
			
					<div class="contents" id="contents" style="min-height: 655.347px;">
				
					<div class="pc-tit">
						<h2>카드 관리</h2>
						<button class="btn circle_add add" name="addAddressBtn" onclick="javascript:location.href='<%=cp%>/createCard.do'" style="color: #669aff;">카드추가</button>
					</div>
					
					<div class="noneBoxPoint" id="noneAddressBox">
						<div>
							<span class="noneBoxPoint_img2"></span>
							<div class="noneBoxPoint_infoTxt" style="color:#666;">등록된 카드가 없습니다.</div>
						</div>
					</div>
			
				</div>
			</div>
		</main>
		
      </c:if>
      
      
     <c:if test="${count>0 }">
      	
		<main class="container lnb page my" id="container">
			<div class="inr" style="min-height: 482.097px;">			
			
					<div class="contents" id="contents" style="min-height: 655.347px;">
				
					<div class="pc-tit">
						<h2>카드 관리</h2>
						<button class="btn circle_add add" name="addAddressBtn" onclick="javascript:location.href='<%=cp%>/createCard.do'" style="color: #669aff;">카드추가</button>
					</div>
      
			      <!-- 등록한 카드 반복 구간 -->
			      <c:forEach var="cddto" items="${lists }">
			         <div class="list-item card">
			                 <div class="thumbnail-area">
			                     <div class="thumbnail"><img src="upload/${cddto.saveName }" alt="${cddto.cardName }"></div>
			                 </div>
			                 <div class="description">
			                     <h4 class="name">${cddto.cardName}</h4>
			                     <ul class="info-list">
			                             ${cddto.userName }님의 등록된 ${cddto.cardName }<br>전월실적 관계없이 사용 횟수에 따라<br>최대 10,000 포인트 적립
			                      </ul>
			                 </div>
			          </div>
			          
			          <div class="uidropmu dmenu">
						<ul class="menu">
							<li><button type="button" class="bt" name="deleteAddressBtn"  onclick="javascript:location.href='<%=cp%>/deleteCard.do?userId=${cddto.userId }&cardNum=${cddto.cardNum }'">삭제</button></li>
						</ul>									
					 </div>
								
			        </c:forEach>
          
          		</div>
			</div>
		</main>
      </c:if> 
  
</div>





<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>