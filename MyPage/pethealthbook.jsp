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
<!-- <link rel="stylesheet" href="resources/css/pethealthbook.css" type="text/css"/>
 -->

</head>

<body>
<main>
	<div>
		<div>
			<div class="pc-tit">
				<h2>
					건강수첩
				</h2>
			</div>
		</div>
		
		<section>
			<ul>
				<li>
					<h5>"접종안내"</h5>
				</li>
				<li class="active" style="min-height: 727.082px;">
					<div class="health-note contentHeight" id="catContent">
						<div class="item">
							<p class="tit">기초 접종</p>
							<div class="item-right">
								<p class="txt">1년 미만의 고양이는 기초접종이 필요합니다. 생후 8주부터 시작해 3주 간격으로 접종합니다.</p>
								<div class="table-type01">
									<p class="t-info">*선택사항</p>
									<table>
										<colgroup>
											<col style="width: 33%">
											<col style="width: 33%">
										</colgroup>
										<thead>
											<tr>
												<th scope="col">접종시기</th>
												<th scope="col">기초접종</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>생후 8주</td>
												<td>혼방예방백신 1차</td>
											</tr>
											<tr>
												<td>생후 11주</td>
												<td>혼방예방백신 2차</td>
											</tr>
											<tr>
												<td>생후 14주</td>
												<td>혼방예방백신 3차</td>
											</tr>
											<tr>
												<td>생후 21주</td>
												<td><span class="f-gray">광견병</span></td>
											</tr>
										</tbody>
									</table>
									<p class="b-info">항체가검사 : 3차 접종 후 2주뒤</p>
								</div>
							</div>
						</div>
						<div class="item">
							<p class="tit">정기 접종</p>
							<div class="item-right">
								<p class="txt">기초 예방접종을 마친 성묘는 정기 접종을 해야 합니다. 접종 주기는 권장사항입니다.</p>
								<div class="table-type01">
									<table>
										<colgroup>
											<col style="width: 50%">
											<col style="width: 50%">
										</colgroup>
										<thead>
											<tr>
												<th scope="col">예방접종명</th>
												<th scope="col">접종주기</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>혼방예방백신</td>
												<td>매년 또는 3년 1회</td>
											</tr>
											<tr>
												<td>광견병</td>
												<td>매년 1회</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
						<div class="item">
							<p class="tit">예방접종 종류</p>
							<div class="g-box">
								<p class="tit">혼합예방백신</p>
								<p class="txt">접촉 및 공기중으로 전염되는 호흡기 바이러스 질환으로 무기력, 식욕감퇴, 고열, 폐렴 등으로 진행될 수 있습니다.</p>
								<p class="tit">광견병</p>
								<p class="txt">강아지 뿐만 아니라 모든 포유동물에게 감염되며, 100% 치사율에 도달하는 무시무시한 질병입니다.</p>													
								<p class="tit">항체가검사</p>
								<p class="txt">백신 전/후, 항체가검사는 백신 접종 프로그램의 기본입니다. 반려묘가 예방접종을 했어도 항체가 높지않으면, 홍역이나 파보 바이러스로부터 안전하지 않습니다.</p>													
							</div>
						</div>
					</div>	
				</li>	
			</ul>
		</section>
		
	</div>
</main>


</body>
</html>