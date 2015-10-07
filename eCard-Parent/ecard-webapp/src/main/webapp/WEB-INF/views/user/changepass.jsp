<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
.mesage_error {
	color: red;
	display: none;
	margin-top: 10px !important;
	margin-left: 153px !important;
}

.btn-lg {
	padding: 2px 16px;
}

.form-group {
	margin-bottom: 0;
}

.clearfix {
	margin-bottom: 4px;
}

.navbar-right {
	margin-right: -40px;
}

.clearfix a.active {
	font-weight: bold;
}

.navbar-left {
	float: left;
	margin-top: 45px;
}

.navbar-left li {
	position: relative;
	margin-left: 20px;
}

.navbar-top-links .dropdown-menu li {
	margin-left: 0;
}

.navbar-left li a {
	padding: 0;
	min-height: inherit;
}

.navbar-left li a .label {
	line-height: 12px;
	padding: 2px 5px;
	position: absolute;
	right: -3px;
	top: -8px;
}

.navbar-static-top {
	margin-bottom: 0;
	float: right;
	background: none;
	position: absolute;
	right: -15px;
	bottom: -13px;
}

.a-new-pc {
	float: right;
	border: 2px solid #000;
	text-align: center;
	padding: 5px 10px;
	color: #000;
}

.btn_back img {
	width: 12px;
	margin-right: 6px;
}

.content_notice {
	max-width: 171px;
}
/**/
.div-list {
	display: inline-block;
	margin: 30px auto 0 auto;
	text-align: center;
	width: 100%;
}

.list-profile {
	margin: 0;
	padding: 0;
	width: auto;
	display: block;
}

.list-profile li {
	border-bottom: 1px solid #b1b1b1;
	width: auto;
	display: block;
	padding-bottom: 5px;
	margin-bottom: 5px;
	text-align: left;
	list-style: none;
	padding: 0 60px 10px 60px;
	margin-bottom: 10px;
}

.list-profile li p {
	width: 100%;
	margin: 0;
	padding: 0;
}

.list-profile li .p-1 {
	color: #555;
	font-size: 13px;
}

.list-profile li .p-2 {
	color: #000;
	font-size: 15px;
}

.box-1 {
	display: block;
	height: auto;
	margin: 0 auto;
	text-align: left;
	width: 800px;
}

.title-box-1 {
	background: -moz-linear-gradient(center top, #f4f4f4, #e6e6e6) repeat
		scroll 0 0 rgba(0, 0, 0, 0);
	border: 1px solid #b1b1b1;
	border-radius: 4px 4px 0 0;
	box-shadow: 0 1px 1px #fff inset;
	font-weight: bold;
	padding: 14px 30px 10px;
	text-align: left;
	color: #666;
	font-weight: bold;
	font-family: "ã¡ã¤ãªãª", Meiryo, "ãã©ã®ãè§ã´ Pro W3",
		"Hiragino Kaku Gothic Pro", "ï¼­ï¼³ ï¼°ã´ã·ãã¯", "MS PGothic",
		sans-serif !important;
}

.content-box-1 {
	background-color: #fff;
	border-bottom: 1px solid #b1b1b1;
	border-left: 1px solid #b1b1b1;
	border-radius: 0 0 4px 4px;
	border-right: 1px solid #b1b1b1;
	padding: 15px 0 0 0;
	text-align: center;
}

.label-c {
	color: #666;
	width: 150px;
}

.input-c {
	width: 524px;
	padding: 10px;
	color: #666;
}

.input-submit {
	cursor: pointer;
	font-size: 1.4em;
	margin: 20px auto 20px 153px;
	padding: 10px 12px 8px 12px;
	border: 1px solid #12476a;
	border-radius: 3px;
	background: linear-gradient(#307cae, #27648d);
	background: -webkit-gradient(linear, left top, left bottom, from(#307cae),
		to(#27648d));
	background: -moz-linear-gradient(top, #307cae, #27648d);
	background-color: #307caf;
	color: #ffffff !important;
	vertical-align: middle;
	text-shadow: 0 -1px 2px #000;
	box-shadow: 1px 1px 0 #649dc2 inset;
	white-space: nowrap;
	-ms-filter:
		"progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae, EndColorStr=#27648d)";
	filter: progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae,
		EndColorStr=#27648d);
	font-size: 14px;
	width: 524px;
	display: block;
}
</style>
<div class="div-list">
	<div class="box-1">
		<div class="title-box-1">パスワード設定</div>
		<div class="content-box-1">
			<form id="updatePassForm" class="h-adr" method="POST"
				accept-charset="UFT-8">
				<ul class="list-profile">
					<li><label class="label-c">現在のパスワード</label> <input
						class="input-c" value="" type="password" id="oldpass">
					<p class="mesage_error error_oldpass"></p></li>
					<li><label class="label-c">新しいパスワード</label> <input
						class="input-c" value="" type="password" id="newpass1">
					<p class="mesage_error error_newpass1"></p></li>
					<li><label class="label-c">新しいパスワード（確認）</label> <input
						class="input-c" value="" type="password" id="newpass2">
					<p class="mesage_error error_newpass2"></p></li>
					<li
						style="border: none; padding-bottom: 0; text-align: center; margin: 0 auto;">

						<input type="button" class="input-submit" value="パスワードを変更">
					</li>
				</ul>
			</form>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$(".input-submit").click(function() {
			resetValidationForm();
			if (!checkValidationForm()) {
				return false;
			}
			var pass=$("#newpass1").val();
			debugger;
			$.ajax({
			    type: 'POST',
			    url: 'updatePass',
			    data: { 
			        'newPass':pass
			    },
			    success: function(msg){
			        if(msg==true){
			        	window.location = "home";
			        }
			    }
			});
		});

	});

	function checkValidationForm() {
		var checkValidation = true;
		var oldpass = $("#oldpass").val();
		var newpass1 = $("#newpass1").val();
		var newpass2 = $("#newpass2").val();
		
		if (newpass1.length < 8) {
			$(".error_newpass1").text(
					"<fmt:message key='user.profile.length.pass'/>");
			checkValidation = false;
			$(".mesage_error").css("display", "block");
		}
		
		if (newpass2.length < 8) {
			$(".error_newpass2").text(
					"<fmt:message key='user.profile.length.pass'/>");
			checkValidation = false;
			$(".mesage_error").css("display", "block");
		}
		
		if (oldpass == newpass1) {
			$(".error_newpass1").text(
					"<fmt:message key='user.profile.old.pass.equal.new.pass'/>");
			checkValidation = false;
			$(".mesage_error").css("display", "block");
		}
		
		if (newpass2 != newpass1) {
			$(".error_newpass2").text(
					"<fmt:message key='user.profile.new.pass1.equal.new.pass2'/>");
			checkValidation = false;
			$(".mesage_error").css("display", "block");
		}
		
		var regexLetterAndNumber = /^(?=.*[a-zA-Z])(?=.*[0-9])/;
		if (!(regexLetterAndNumber.test(newpass1))) {
			$(".error_newpass1").text(
					"<fmt:message key='user.profile.format.pass'/>");
			checkValidation = false;
			$(".mesage_error").css("display", "block");
		}
		
		if(checkValidation){
			$.ajax({
			    type: 'POST',
			    url: 'checkPass',
			    async: false,
			    data: { 
			        'oldpass': $("#oldpass").val()
			    },
			    success: function(data){
			        if(data!=true){
			        	$(".error_oldpass").text(
						"<fmt:message key='user.profile.oldpass.not.match'/>");
						checkValidation = false;
						$(".mesage_error").css("display", "block");
			        }
			    }
			});
		} 
		return checkValidation;
	}
	function resetValidationForm() {
		$(".error_oldpass").text("");
		$(".error_newpass1").text("");
		$(".error_newpass2").text("");

	}
</script>
