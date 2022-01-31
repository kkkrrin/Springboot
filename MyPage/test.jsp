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
<link rel="stylesheet" href="resources/css/test.css" type="text/css"/>
<script src="/webjars/jquery/3.6.0/dist/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="resources/css/nextInfo.css">


<script type="text/javascript">

$(document).ready(function(){
	
	window.onload = function(){
		
		$('.btn_on').hide();
		
	}

	$(".tag_btn").click(function(e){

		if($(this).hasClass("active")){
			
			$(this).attr('class', 'tag_btn');

		}else{

			$(this).attr('class', 'tag_btn active');
			
		}
		if($('.tag_btn').hasClass("active")){
			$('.btn_off').hide();
			$('.btn_on').show();
			
		}else{
			$('.btn_on').hide();
			$('.btn_off').show();
		}
		
	});

});

function openModal(modalname){
	  document.get
	  $("#modal").fadeIn(300);
	  $("."+modalname).fadeIn(300);
	}

	$("#modal, .close").on('click',function(){
	  $("#modal").fadeOut(300);
	  $(".modal-con").fadeOut(300);
	});
	
function closeModal(modalname){
	document.get
	$("modal").fadeOut(300);
	$(".modal-con").fadeOut(300);
}

function sendIt(){

	var tagArray = "";
	
	$(".tag_btn").each(function(){
		
		if($(this).hasClass("active")){
			tagArray += $(this).attr("value") + ",";
		}

	});
	
	$.ajax({
		url : 'update_ok.do',
		type : 'post',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data : {
			tagArray : tagArray
		},
		success : function(data){
			
			alert('관심태그 설정이 완료 되었습니다.');

			location.href="/mypage.do";

		},
		error : function(error){
			console.log(error);
			alert(error);
		}
	});
	

}


</script>





</head>
<body>


<div id="wrap">
  <a href="javascript:openModal('modal1');" class="button modal-open">모달열기1</a>
</div>

<div id="modal"></div>
  <div class="modal-con modal1">
    <a href="javascript:closeModal('modal1');" class="close">X</a>
    <p class="title">제목</p>
    <div class="con">
			
						<div class="filter-area t2">
							<div class="filter-item">
								<div class="tag">
									<input type="button" class="tag_btn" value="강아지"/>
									<input type="button" class="tag_btn" value="재미"/>
									<input type="button" class="tag_btn" value="귀여움"/>
									<input type="button" class="tag_btn" value="다이어트"/>
									<input type="button" class="tag_btn" value="고양이"/>
									<input type="button" class="tag_btn" value="힐링"/>
									<input type="button" class="tag_btn" value="행동교정"/>
									<input type="button" class="tag_btn" value="감동"/>
									<input type="button" class="tag_btn" value="구조"/>
									<input type="button" class="tag_btn" value="훈련"/>
								</div>
							</div>
						</div>			
		
				<div class="pbt pull">
						<div>
							<input type ="button" class="btn_off" onclick="" value="완료"/>
						</div>
						<div>
							<a href="" id="finbtn" class="btn_on" data-content="" data-url="">완료</a>
						</div>
					
					
					</div>
			
			
			
    </div>
  </div>
 

</body>
</html>