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

<script src="https://kit.fontawesome.com/cd5dd810df.js" crossorigin="anonymous"></script>

</head>
<body>

<h3>
SpringJSP Test
</h3>
<a href="<%=cp %>/mypage.do">마이페이지</a>

<a href="<%=cp %>/signup.do">일단 회원가입</a>

<a href="<%=cp %>/login.do">일단 로그인</a>

<a href="<%=cp %>/logout.do">일단 로그아웃</a>
<br/>
<%-- <a href="<%=cp %>/filetest.do">파일업로드 테스트</a> --%>
<br/>
<a href="<%=cp %>/testStore.do">STORE</a>

<a href="<%=cp %>/testLog.do">LOG</a>

<a href="<%=cp %>/testTv.do">TV</a>

<br/><br/>

<a href="<%=cp %>/testjsp.do">TV</a>


</body>
</html>