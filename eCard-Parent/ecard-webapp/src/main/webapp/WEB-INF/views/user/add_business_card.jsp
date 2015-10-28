<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>


<style type="text/css">
.mesage_error {
	color: red;
	display: none;
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

.submit {
	cursor: pointer;
	font-size: 1.4em;
	margin: 20px 0;
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
	width: 100%;
	display: block;
}

.for-add-bu {
	display: inline-block;
	width: 93%;
	margin: 0;
	padding: 0;
}

.for-add-bu fieldset {
	display: inline-block;
	width: 100%;
	margin: 3px 0;
}

.for-add-bu fieldset label {
	font-size: 14px;
	color: #666;
	font-weight: bold;
	text-align: left;
	float: left;
	width: 150px;
}

.for-add-bu fieldset label span {
	color: #ff0000;
}

.l-a-b {
	line-height: 32px;
}

.input-a-b {
	float: left;
	width: 300px;
	height: 35px;
	text-indent: 10px;
	border: 1px solid #e5e6e7;
}

.text-a-b-d {
	float: left;
	width: 300px;
	text-align: left;
}

.text-a-b {
	float: left;
	width: 300px;
	height: 100px;
	text-indent: 10px;
	border: 1px solid #e5e6e7;
	margin-bottom: 3px;
}

.ch-regist{
      margin-top: -7px;
    }
</style>
<script type="text/javascript">
function checkValidationForm() {
	var checkValidation = true;
	var firstname = $("#fistname").val();
	var lastname = $("#lastname").val();
	var telcompany = $("#telcompany").val();
	var teldepartment = $("#teldepartment").val();
	var mobilenumber = $("#mobilenumber").val();
	var companyname = $("#companyname").val();
	var addressFull = $("#addressFull").val();
	var zipcode = $('#zipcode').val();
    var email = $("#email").val();
	var firstnamekana = $("#firstnamekana").val();
	var lastnamekana  = $("#lastnamekana").val();
	var companyNameKanaId = $('#companyNameKanaId').val();
	var faxnumber = $('#faxnumber').val();
	
	if (firstname == "") {
		$(".error_firstname").text(
				"<fmt:message key='edit.card.validate'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	if (lastname == "") {
		$(".error_lastname")
				.text("<fmt:message key='edit.card.validate'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	if (telcompany == "") {
		$(".error_telcompany").text(
				"<fmt:message key='edit.card.validate'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	
    if (email == "") {
        $(".error_email").text("<fmt:message key='edit.card.validate'/>");
        checkValidation = false;
        $(".mesage_error").css("display", "block");
    }

    
	if (companyname == "") {
		$(".error_companyname").text(
				"<fmt:message key='edit.card.validate'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}

	addressFull = addressFull.trim();
	if (addressFull == "" || addressFull.split(' ').length < 3) {
		$(".error_addressFull").text(
				"<fmt:message key='edit.card.validate'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}

	if (zipcode == "") {
		$(".error_zipcode").text("<fmt:message key='edit.card.validate'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	if (companyNameKanaId == "") {
		$(".error_companyNameKana").text(
				"<fmt:message key='edit.card.validate'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	var customPhone=/^[0-9-+()]+$/;
	if (faxnumber!="" && !(customPhone.test(faxnumber))) {
		$(".error_faxnumber").text(
			"<fmt:message key='edit.card.format.phone'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	if (telcompany!="" && !(customPhone.test(telcompany))) {
		$(".error_telcompany").text(
			"<fmt:message key='edit.card.format.phone'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	if (teldepartment!="" && !(customPhone.test(teldepartment))) {
		$(".error_teldepartment").text(
			"<fmt:message key='edit.card.format.phone'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	if (mobilenumber!="" && !(customPhone.test(mobilenumber))) {
		$(".error_mobilenumber").text(
			"<fmt:message key='edit.card.format.phone'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	if (zipcode!="" && !(customPhone.test(zipcode))) {
		$(".error_zipcode").text(
			"<fmt:message key='edit.card.format.phone'/>");
		checkValidation = false;
		$(".mesage_error").css("display", "block");
	}
	
	return checkValidation;
}
function resetValidationForm() {
	$(".error_firstname").text("");
	$(".error_lastname").text("");
	$(".error_telcompany").text("");
	$(".error_teldepartment").text("");
	$(".error_mobilenumber").text("");
	$(".error_companyname").text("");
	$(".error_departmentName").text("");
	$(".error_addressFull").text("");
	$(".error_zipcode").text("");
    $(".error_email").text("");
	$(".error_companyNameKana").text("");
	$(".error_telcompany").text("");
	$(".error_teldepartment").text("");
	$(".error_mobilenumber").text("");
	$(".error_faxnumber").text("");
}

function removerDate() {
	var date = $('input[name=contactDate]').val().toString();
	if (date.indexOf(".0") > 0) {
		date_rep = date.replace(".0", "");
		date_new = new Date(date_rep);
		$('input[name=contactDate]').val(date_new);
	}
}

function getContactDate(){
	if (($('.input-group.date').datepicker('getDate').toString() != "Invalid Date")) {
		$('input[name=contactDate]').val($('.input-group.date').datepicker('getDate'));
	} else {
		removerDate();
	}
}


$(document).ready(function() {
	//get address code 
	$("#zipcode").change(function(){
	  	  //does some stuff;
	  	  $("#addressFull").text($("#p-region").val()+" "+$("#p-locality").val() +" "+$("#extended-address").val());
	  	$("#addressFull").val($("#p-region").val()+" "+$("#p-locality").val() +" "+$("#extended-address").val());
	})
	
	$("#btnSave").click(function(){
		 resetValidationForm();
		if (!checkValidationForm()) {
			return false;
		}
		getContactDate();
		$("#submitForm").submit();
	})	
	
	$('.input-group.date').datepicker({
		language : 'ja',
		todayHighlight : true,
		keyboardNavigation : false,
		format : 'yyyy年MMdd日',
		forceParse : true,
		autoclose : true,
		calendarWeeks : true

	}).datepicker("setDate", "0");
	
	
});

</script>
<div class="div-list">
	<div class="box-1">
			<div class="title-box-1">名刺データ作成<button class="btn btn-sm btn-primary pull-right click-memo ch-regist" id="btnSave">登録</button></div>
		<div class="content-box-1">
			<form id="submitForm"class="for-add-bu h-adr" method="POST"
				action="/ecard-webapp/user/saveBusinessCard/"
				accept-charset="UFT-8">
				<fieldset>
					<label><fmt:message key='edit.card.name' /></label>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.lastname' /><span>*</span></label> <input
						class="input-a-b" name="lastName" id="lastname">
						<p class="mesage_error error_lastname"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.firstname' /> <span>*</span></label> <input
						class="input-a-b" name="firstName" id="fistname">
						<p class="mesage_error error_fistname"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.lastnamekana' /></label> <input value=""
						class="input-a-b" name="lastNameKana" id="lastnamekana">
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.firstnamekana' /></label> <input value=""
						class="input-a-b" name="firstNameKana" id="firstnamekana">
				</fieldset>

				<fieldset>
					<label><fmt:message key='edit.card.contactinfo' /></label>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.teletephonenumber1' /> <span>*</span></label> <input
						class="input-a-b" name="telNumberCompany" id="telcompany">
						<p class="mesage_error error_telcompany"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.teletephonenumber2' /></label> <input value=""
						class="input-a-b" name="telNumberDepartment" id="teldepartment">
						<p class="mesage_error error_teldepartment"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.mobilenumber' /></label> <input value=""
						class="input-a-b" name="mobileNumber" id="mobilenumber">
						<p class="mesage_error error_mobilenumber"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.faxnumber' /></label> <input value=""
						class="input-a-b" name="faxNumber" id="faxnumber">
						<p class="mesage_error error_faxnumber"></p>
				</fieldset>
				 <fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.email' /><span>*</span></label> <input
						class="input-a-b" name="email" id="email">
						<p class="mesage_error error_email"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.companyname' /><span>*</span></label> <input
						class="input-a-b" name="companyName" id="companyname">
						<p class="mesage_error error_companyname"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.companynamekana' /><span>*</span></label> <input
						class="input-a-b" name="companyNameKana" id="companyNameKanaId">
						<p class="mesage_error error_companyNameKana"></p>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.departmentname' /></label> <input value=""
						class="input-a-b" name="departmentName" id="departmentname">
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.positisionname' /></label> <input
						class="input-a-b" name="positionName" id="exampleInputName2">
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.computerUrl' /></label> <input value=""
						class="input-a-b" name="companyUrl" id="exampleInputName2">
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='edit.card.zipcode' /><span>*</span></label> 
					<input class="input-a-b p-postal-code" name="zipCode" id="zipcode">
						<p class="mesage_error error_zipcode"></p>
						
						<span class="p-country-name" style="display: none;">Japan</span>

										<input type="text" class="p-region" readonly="" id="p-region"
											style="display: none;"> 
										<input type="text"
											class="p-locality" readonly="" id="p-locality"
											style="display: none;"> 
										<input type="text"
											class="p-street-address p-extended-address"
											id="extended-address" style="display: none;">
				</fieldset>
				<fieldset style="text-align: left;">
					<label class="l-a-b"></label> 
					<span><fmt:message key='card.detail.zipCode.comment' /></span>
					
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='card.detail.addressFull' /></label>
					<div class="text-a-b-d">
						<textarea class="text-a-b" name="addressFull" id="addressFull"></textarea>
						<fmt:message key='card.memo.addressFull' />
						<p class="mesage_error error_addressFull" />
					</div>
				</fieldset>
				<fieldset>
					<label class="l-a-b"><fmt:message key='card.list.dateupdate' /><span></span></label>
					<div class="text-a-b-d">
						<div class="input-group date">
							<span class="input-group-addon"><i class="fa fa-calendar"></i></span>
							<input name="contactDate1" class="form-control create-date" readonly/>
							<input type="hidden" name="contactDate" class="form-control create-date"> 
						</div>
					</div>
					<p class="mesage_error error_lastname"></p>
				</fieldset> 

			</form>
		</div>
	</div>
</div>
