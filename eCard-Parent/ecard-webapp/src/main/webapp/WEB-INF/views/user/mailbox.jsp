<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
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
	display: inline-block;
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
</style>

<style type="text/css">
.label-c {
	color: #666;
	width: 150px;
}

.input-c {
	width: 524px;
	padding: 10px;
	color: #666;
}

.form {
	width: 490px;
	display: inline-block;
	text-align: center;
}

.textarea {
	display: block;
	border: 1px solid #b1b1b1;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	padding: 10px 9px 8px 9px;
	background-color: #f2f2f2;
	box-shadow: 0 3px 4px #ccc inset;
	width: 100%;
	height: 300px;
	color: #666666;
	font-weight: bold;
	font-size: 14px;
}

.submit{
    cursor: pointer;
    font-size: 1.4em;
    margin: 20px 0;
    padding: 10px 12px 8px 12px;
    border: 1px solid #e3157a;
    border-radius: 3px;
    background-color: #e3157a;
    color: #ffffff !important;
    vertical-align: middle;
    text-shadow: 0 -1px 2px #000;
    white-space: nowrap;
    font-size: 14px;
    width: 100%;
    display: block;

     }

.mesage_error {
	color: red;
	display: none;
	width: 220px;
	text-align: left
}
</style>
<div class="div-list">
	<div class="box-1">
		<div class="title-box-1">ご意見・不具合の連絡</div>
		<div class="content-box-1">
			<form class="form" id="submitForm" method="POST"
				action="/ecard-webapp/user/mailbox"
				accept-charset="UFT-8">
				<textarea class="textarea" name="contents" id="contents"></textarea>
				<span class="mesage_error error_contents"></span>
				<input type="button" value="送信" class="submit" id="btnSend">
			</form>
		</div>
	</div>
</div>

<script>
	$(document).ready(function() {
		$("#btnSend").click(function() {
			resetValidationForm();
			if (!checkValidationForm()) {
				return false;
			}
			$("#submitForm").submit();
		});

	});

	function checkValidationForm() {
		var checkValidation = true;
		var contents = $("#contents").val();
		if (contents.length <= 0) {
			$(".error_contents").text(
					"<fmt:message key='edit.card.validate'/>");
			checkValidation = false;
			$(".mesage_error").css("display", "block");
		} 
		return checkValidation;
	}
	function resetValidationForm() {
		$(".error_contents").text("");

	}
</script>