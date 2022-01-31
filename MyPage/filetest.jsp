<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String cp = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	
	function sendIt(){
		f=document.myForm;
		
		f.action="<%=cp%>/filetest_ok.do"; 
		f.submit(); 
	}
	
	
</script>


</head>
<body>
<h3>file upload test </h3>

<form action="" name="myForm" method="post" enctype="multipart/form-data">

	<p>
		파일 : <input type="file" id="file" name="files"/>
	</P>

	<input type="button" value="업로드" onclick="sendIt();"/>

</form>


</body>
</html>