<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta id="viewport" name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/assets/css/style_ibs.css'/>" type="text/css" media="all" />
<style type="text/css">
.success{

 color:blue;
 text-align: left;
 width: 100%;
  padding-top: 20px;
}
.error{
  color:red;
  text-align: left;
  width: 100%;
  padding-top: 20px;
}
</style>
<script type="text/javascript">
function isValidEmailAddress(emailAddress) {
    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
    return pattern.test(emailAddress);
};
$(document).ready(function() {
	$( ".btn_forgot" ).click(function() {
		  var email =$('.input-1').val();
		   if(isValidEmailAddress(email)){
			   $.ajax({
					type: 'POST',
					url: 'checkemail',
					cache: false,
					data: 'email='+email
				}).done(function(resp, status, xhr) {
					console.log('AAA = '+resp);
					var currentDate = new Date().getTime();
					if (resp.status != ''){
						if(resp.useStopFlg == 1){
							$('#message_error').removeClass("success");
							$('#message_error').addClass("error");
							$('.message_ibs').text("このIDは利用停止中です。管理者宛お問合せください。");
							return false;
						}
						if(resp.leaveFlg == 1){
							$('#message_error').removeClass("success");
							$('#message_error').addClass("error");
							$('.message_ibs').text("このIDは利用できません。");
							return false;
						}
						
						if(resp.useDate > currentDate){
							$('#message_error').removeClass("success");
							$('#message_error').addClass("error");
							$('.message_ibs').text("このIDは利用準備中です。");
							return false;
						}
						
						if(resp.endDate < currentDate){
							$('#message_error').removeClass("success");
							$('#message_error').addClass("error");
							$('.message_ibs').text("このIDは利用できません。");
							return false;
						}
						var sendding = "phuongnguyen";
						 $.ajax({
							type: 'POST',
							url: "<c:url value='/sendMailWebapp'/>?recipientName="+sendding+"&recipientEmail="+email+"&password="+resp.status,
							cache: false,
						}).done(function(resp, status, xhr) {
							if (resp == 'Success'){
								   $('#message_error').removeClass("error");
								   $('#message_error').addClass("success");
								   $('.message_ibs').text("パスワード再設定の通知を登録されたメールアドレスへ送信しました");
							
							}else{
								alert("Error send mail");
							} 
						}).fail(function(xhr, status, err) {
							 alert('Error');
						}); 
						
					}else{
						$('#message_error').addClass( "error" );
						 $('.message_ibs').text("登録されていないメールアドレスです ");
					}
				}).fail(function(xhr, status, err) {
					 alert('Error');
				});
			   
		   }else{
			   //alert('Email invalid!')
			   $('#message_error').addClass( "error" );
			   $('.message_ibs').text("メールアドレスが正しくありません");
		   }
		});
	 $("#forgotpassword").keyup(function (e) {
		  if (e.which == 13) {
			  $('.btn_forgot').trigger('click');
		  }
	 });
});
  
</script>
</head>
<body>
    <div id="header">
            <h1><a href="/">BC-RIBBON</a></h1>
      </div>
      <div id="content">
           <div id="inner">
           		<div class="box-1">
                	<div class="title-box-1">
                    	パスワードをお忘れの場合
                    </div>
                    <div class="content-box-1">
                        <p class="p-box-1">パスワードをお忘れの場合、ログインIDを入力の上、送信してください。</p>
                        <p class="p-box-1">登録されているメールアドレス宛に、パスワード発行メールをお送りします。</p>
                        <form class="box-1-form">
                        	<input type="text" class="input-1" id= "forgotpassword" value="" placeholder = "ID">
                            <input type="button" class="submit-1 btn_forgot" value="送 信">
                            </br>
                            <div class = "message_ibs" id ="message_error"></div>
                        </form>
                        
                    </div>    
                </div>
           </div>
      </div>     
</body>
</html>