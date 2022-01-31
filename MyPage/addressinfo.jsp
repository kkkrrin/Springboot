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


<c:if test="${count==0 }"> 

<main class="container lnb page my" id="container">
	<div class="inr" style="min-height: 482.097px;">			
	
			<div class="contents" id="contents" style="min-height: 655.347px;">
		
			<div class="pc-tit">
				<h2>배송지 관리</h2>
				<button class="btn circle_add add" name="addAddressBtn" onclick="javascript:location.href='<%=cp%>/createAddress.do'" style="color: #669aff;">배송지 추가</button>
			</div>
			<div class="noneBoxPoint" id="noneAddressBox">
				<div>
					<span class="noneBoxPoint_img2"></span>
					<div class="noneBoxPoint_infoTxt" style="color:#666;">등록된 배송지가 없습니다.</div>
				</div>
			</div>
	
		</div>
	</div>
</main>

</c:if>



<c:if test="${count>0 }"> 
	
<main class="container lnb page my" id="container">
	<div class="inr" style="min-height: 330.991px;">			
		
		<div class="contents" id="contents" style="min-height: 503.821px;">
			
			<div class="pc-tit">
				<h2>배송지 관리</h2>
				<button class="btn circle_add add" name="addAddressBtn" onclick="javascript:location.href='<%=cp%>/createAddress.do'" style="color: #669aff;">배송지 추가</button>
			</div>
			
			<div class="uiDelisel">
				<ul class="delist">
					<c:forEach var="addto" items="${lists }">
					<li class="active" name="del_125271">
						<div class="box t2">
								
								<div class="new_tit_area on">
										${addto.addressAlias }
									</div>
								<div class="inf">
									<div class="adrs">${addto.streetAddress } &nbsp; ${addto.detailAddress }</div>
									<div class="usrs">												
										${addto.userName } / ${addto.phoneNumber }
									</div>
								</div>
								
							<%-- 	<a href="/addressupdate.do?addressAlias=${addto.addressAlias }"></a> --%>
								<div class="uidropmu dmenu">
									<ul class="menu">
										<li><button type="button" class="bt" name="deleteAddressBtn"  onclick="javascript:location.href='<%=cp%>/deleteAddress.do?addressAlias=${addto.addressAlias }'">삭제</button></li>
									</ul>									
								</div>
								
								 
							</div>
						</li>
						</c:forEach>
					</ul>
				</div>
		 	</div>	
		</div>
</main>							
	
</c:if>


<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>