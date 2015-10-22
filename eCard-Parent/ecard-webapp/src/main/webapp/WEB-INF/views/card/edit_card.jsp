<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.lang.Integer"%>
<%@ page import="java.util.List"%>
<script type="text/javascript">
history.pushState(null, null, null);

window.addEventListener("popstate", function() {
history.pushState(null, null, null);
});
</script>

<script type="text/javascript">
	var base64 = "";
	/* function rotateBase64Image(base64data, callback,rorate) {
	debugger;
	   var canvas = document.getElementById("c");
	   var ctx = canvas.getContext('2d'); 
	   var image = new Image();
	   image.src = base64data;
	   image.onload = function() {
		   
	       ctx.translate(0, -image.width);
	       ctx.rotate(90 * Math.PI / 180);
	       ctx.drawImage(image, 0, 0); 
	      	$(".img-100").attr("src",""+canvas.toDataURL());
	       window.eval(""+callback+"('"+canvas.toDataURL()+"')");
	   };

	}  */

	var i = 0;
	/* function rotateBase64Image(base64data, callback) {
		var canvas = document.getElementById("canvas");
		var ctx = canvas.getContext("2d");

		var image = new Image();
		
		image.onload = function() {
			canvas.width = image.height;
			canvas.height = image.width;
			ctx.translate(canvas.width / 2, canvas.height / 2);
			i++;
			ctx.rotate(i * Math.PI / 2);
			
			ctx.translate(- canvas.width / 2, - canvas.height / 2);
			ctx.drawImage(image, 0, 0);
			window.eval("" + callback + "('" + canvas.toDataURL() + "')");
		};
		image.src = base64data;
		
	}  */
	
	/* function rotateBase64Image(base64data, callback) {
		debugger
		var canvas = document.getElementById("canvas");
		var ctx = canvas.getContext("2d");
		var cw = canvas.width;
		var ch = canvas.height;
		
		var image = new Image();
		image.src = base64data;
		image.onload = function() {
			canvas.width = ch;
			canvas.height = cw;
			cw = canvas.width;
            ch = canvas.height;
            ctx.save();
            // translate and rotate
            ctx.translate(cw, ch / cw);
            ctx.rotate(Math.PI / 2);
            // draw the previows image, now rotated
            ctx.drawImage(image, 0, 0);  
            window.eval("" + callback + "('" + canvas.toDataURL() + "')");
            ctx.restore();
           
            // clear the temporary image
            image = null;
		};
		
		
	} */
	var canvas;

	var angleInDegrees=0;
	
	function drawRotated(degrees){
	    if(canvas) document.body.removeChild(canvas);
	    var canvas = document.getElementById("canvas");
		var ctx = canvas.getContext("2d");
		
	    canvas.style.width="20%";
	    var image = new Image();
	    image.src=$(".img-100").attr("src");
	    if(degrees == 90 || degrees == 270) {
			canvas.width = image.height;
			canvas.height = image.width;
	    } else {
			canvas.width = image.width;
			canvas.height = image.height;
	    }
	    
	    ctx.clearRect(0,0,canvas.width,canvas.height);
	    if(degrees == 90 || degrees == 270) {
			ctx.translate(image.height/2,image.width/2);
	    } else {
		    ctx.translate(image.width/2,image.height/2);
	   }
	    ctx.rotate(degrees*Math.PI/180);
	    
	    ctx.drawImage(image,-image.width/2,-image.height/2);
	    
	    //document.body.appendChild(canvas);
	    callback(canvas.toDataURL());
	}
	
	function callback(base64data) {
		$(".img-100").attr("style", "display:none");
		$(".img-temp").removeAttr("style");
		$(".img-temp").attr("src", base64data);
		var rote = getRoteImage($("#rote-image").val());
		$("#rote-image").val(rote + 90);
	}

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

		var companyNameKanaId = $('#companyNameKanaId').val();
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
	}

	function getRoteImage(roteOfImage) {
		var rote = 0;
		if ($.isNumeric(roteOfImage)) {
			rote = parseInt(roteOfImage)
		} else {
			rote = 0;
		}
		return rote;
	}
	function removerDate() {
		var date = $('input[name=contactDate]').val().toString();
		if (date.indexOf(".0") > 0) {
			date_rep = date.replace(".0", "");
			date_new = new Date(date_rep);
			$('input[name=contactDate]').val(date_new);
		}
	}
	function checkApproval() {
		if($('input[name=approvalStatus]').val()=="1"){
			alert('他の人が入力中です');
			return false;
		}
		return true;
	}
	$(document).ready(function() {
						var ENUM_APPROVAL = 1;
						var ENUM_TEMP = 3;
						var ENUM_TEMP_SAVE = 4;
						$("#rote-image").val(0);
						
						$(".img-100").onload=function(){
						    drawRotated(0);
						}
						
						$('#tempSave').on('click',function() {
								/* resetValidationForm();
								if (!checkValidationForm()) {
									return false;
								} */
								if (($('.input-group.date').datepicker('getDate').toString() != "Invalid Date")) {
									$('input[name=contactDate]').val($('.input-group.date').datepicker('getDate'));
									$('input[name=contactDate]').value = $('.input-group.date').datepicker('getDate');
								} else {
									removerDate();
								}
								$("#cardInforEditSubmitForm").attr("action","/ecard-webapp/cards/editDirect");
								/* $('input[name=approvalStatus]',
										'#cardInforEditSubmitForm')
										.val(ENUM_TEMP); */
								$('#cardInforEditSubmitForm').submit();

						});
						$('#saveTempStatus4').on('click',function() {
								resetValidationForm();
								if (!checkValidationForm()) {
									return false;
								}
								if (($('.input-group.date').datepicker('getDate').toString() != "Invalid Date")) {
									$('input[name=contactDate]').val($('.input-group.date').datepicker('getDate'));
									$('input[name=contactDate]').value = $('.input-group.date').datepicker('getDate');
								} else {
									removerDate();
								}
								//$("#cardInforEditSubmitForm").attr("action", "/ecard-webapp/cards/editDirect");
								$('input[name=approvalStatus]','#cardInforEditSubmitForm').val(ENUM_TEMP_SAVE);
								$('#cardInforEditSubmitForm').submit();

						});
						$('#moveFix').on('click',function() {
								 resetValidationForm();
								if (!checkValidationForm()) {
									return false;
								} 
								if (($('.input-group.date').datepicker('getDate').toString() != "Invalid Date")) {
									$('input[name=contactDate]').val($('.input-group.date').datepicker('getDate'));
									$('input[name=contactDate]').value = $('.input-group.date').datepicker('getDate');
								} else {
									removerDate();
								}
								$("#cardInforEditSubmitForm").attr("action","/ecard-webapp/cards/editDirect");
								$('input[name=approvalStatus]','#cardInforEditSubmitForm').val(ENUM_TEMP);
								$('#cardInforEditSubmitForm').submit();

					   });
						$('#approvalSave').on('click',function() {
								resetValidationForm();
								if (!checkValidationForm()) {
									return false;
								}
								if (!checkApproval()) {
									return false;
								}
								
								var cardOwnerId = $("input[name=cardOwnerId]").val();
								var cardId = $("input[name=cardId]").val();
								var cardName = $("input[name=cardName]").val();
                                 if(cardName == ""){
                                     cardName =$("input[name=lastName]").val() + $("input[name=firstName]").val();
                                 }
								var msg = cardName + " さんの名刺が承認されました。";
								var type = 4;
								if (($('.input-group.date').datepicker('getDate').toString() != "Invalid Date")) {
									$('input[name=contactDate]').val($('.input-group.date').datepicker('getDate'));
									$('input[name=contactDate]').value = $('.input-group.date').datepicker('getDate');
								} else {
									removerDate();
								}
								$("#cardInforEditSubmitForm").attr("action","/ecard-webapp/cards/editDirect");
								$('input[name=approvalStatus]','#cardInforEditSubmitForm').val(ENUM_APPROVAL);
								$('#cardInforEditSubmitForm').submit();
						
								 	/* $.ajax({
													type : 'POST',
													url : '/ecard-webapp/notification/push',
													data : 'cardOwnerId='
															+ cardOwnerId
															+ '&msg='
															+ msg
															+ '&cardId='
															+ cardId
															+ '&type='
															+ type
									}).done(function(resp,status, xhr) {
													$("#cardInforEditSubmitForm").attr("action","/ecard-webapp/cards/editDirect");
													$('input[name=approvalStatus]','#cardInforEditSubmitForm').val(ENUM_APPROVAL);
													$('#cardInforEditSubmitForm').submit();
												})
									.fail(function(xhr,status, err) {
													alert('Error');
									});  */

						});
						$('#backToManage').on('click',function() {
								$.ajax({
									  type: "POST",
									  url: '/ecard-webapp/cards/updateIsEditting',
									  data: 'Id='+$("input[name=cardId]").val(),
									  success: function(){
										  document.location.href = '/ecard-webapp/cards/list';
									  },
								});
											
							});

						//get address code 
						$("#zipcode").change(function(){
						  	  //does some stuff;
						  	  $("#addressFull").text($("#p-region").val()+" "+$("#p-locality").val() +" "+$("#extended-address").val());
						  	$("#addressFull").val($("#p-region").val()+" "+$("#p-locality").val() +" "+$("#extended-address").val());
						});
						$(".btn_address")
								.click(
										function() {
											var zipcodeString = $('#zipcode')
													.val();
											console.log(zipcodeString
													.indexOf('-'));
											if (zipcodeString.indexOf('-') > 1
													|| zipcodeString
															.indexOf(' ') > 1) {
												//$(".error_zipcode").text("<fmt:message key='edit.card.validate.zipcode'/>");			
												$(".mesage_error").css(
														"display", "block");
												return false;
											}

											if (zipcodeString != '') {
												$.ajax({
															url : "http://zipcloud.ibsnet.co.jp/api/search",
															data : {
																"zipcode" : zipcodeString
															},
															dataType : "jsonp",
															success : function(
																	data) {
																if (data.status === 200
																		&& data.results != null) {
																	var addr = data.results[0].address1
																			+ " "
																			+ data.results[0].address2
																			+ " "
																			+ data.results[0].address3;
																	$(
																			'#addressFull')
																			.val(
																					addr);
																	;
																}
																;
															}
														});
											}
										});

						function centerModal() {
							$(this).css('display', 'block');
							var $dialog = $(this).find(".modal-dialog");
							var offset = ($(window).height() - $dialog.height()) / 2;
							// Center modal vertically in window
							var path_image = $(".img-icon-rotated").attr('src');
							$("#img-icon-rotated").attr("src", path_image);
							$dialog.css("margin-top", offset);
						}

						$('.modal').on('show.bs.modal', centerModal);
						$(window).on("resize", function() {
							$('.modal:visible').each(centerModal);
						});

						//bach.le reset rotation
						function resetRotationOfImages() {
							value = 0;
							$("#rote-image").val(0);
						}

						/*rotate  image  */

						var value = 0
						$('.btn_rotate_180').click(function() {
							value += 180;
							var rote = getRoteImage($("#rote-image").val());

							$("#rote-image").val(rote + 180);
							$(".img-100").rotate({
								animateTo : value
							});
						});

						/*rotate left 90*/
						$('.btn_rotate_left_90').click(function() {
							//var value = 0
							value -= 90;
							var rote = getRoteImage($("#rote-image").val());
							$("#rote-image").val(rote + 270);
							$(".img-100").rotate({
								animateTo : value
							});
						});
						/*rotate rightt  90*/
						$('.btn_rotate_right_90').click(
								function() {
									/*  value += 90;
									$(".img-100").rotate(
											{
												animateTo : value,
												callback : function() {
													//console.log("value :" + value);
													rotateBase64Image($(this)
															.attr("src"),
															'callback', 90);
												}
											});  */
											
									angleInDegrees= (angleInDegrees + 90) % 360;
								    drawRotated(angleInDegrees); 

								});

						var date = new Date();
						$('.input-group.date').datepicker({
							language : 'ja',
							todayHighlight : true,
							keyboardNavigation : false,
							format : 'yyyy年MMdd日',
							forceParse : true,
							autoclose : true,
							calendarWeeks : true

						});
						$(".img-100").imagezoomsl({

							zoomrange : [ 1, 12 ],
							zoomstart : 4,
							innerzoom : true,
							magnifierborder : "none"
						});
						$(".img-temp").imagezoomsl({

							zoomrange : [ 1, 12 ],
							zoomstart : 4,
							innerzoom : true,
							magnifierborder : "none"
						}); 

						//Auto convert kana
						$.fn.autoKana("#lastname", "#lastnamekana", {
							katakana : true
						});
						$.fn.autoKana("#fistname", "#firstnamekana", {
							katakana : true
						});
						$.fn.autoKana("#companyname", "#companyNameKanaId", {
							katakana : true
						});

					});
</script>
<style>
.col-sm-12 span {
	color: red;
}

.mesage_error {
	color: red;
	display: none;
}

.ch-color-link .ch-address {
	background-color: blue;
}

.container-collectname {
	width: 100% !important;
}
</style>
<!-- BODY -->
<div class="bg-detail">
	<!-- BODY -->
	<c:if
		test="${pageContext.request.isUserInRole('ROLE_OPERATOR') and (cardInfo.approvalStatus == 4 or cardInfo.approvalStatus == 5)}">
		<c:if test="${not permissionEdit}">
			<script type="text/javascript">
				alert("You haven't permission to edit this card");				
			</script>
			<meta http-equiv="Refresh"
				content="0; url='<c:url value='/cards/list"'/>'">
		</c:if>
	</c:if>

	<form id="cardInforEditSubmitForm" class="h-adr" method="POST" action="/ecard-webapp/cards/edit/${cardInfo.cardId}" accept-charset="UFT-8">
		<input type="hidden" name="cardId" value="${cardInfo.cardId}">
		<input type="hidden" name="cardOwnerId"
			value="${cardInfo.cardOwnerId}"> <input type="hidden"
			name="approvalStatus" value="${cardInfo.approvalStatus}"> <input
			type="hidden" name="cardBackImgFile"
			value="${cardInfo.cardBackImgFile}"> <input type="hidden"
			name="cardName" value="${cardInfo.name}">
		<!-- CENTER SIDE -->
		<div id="center-side" class="col-sm-12 ch-title">
			<div class="row">
				<div class="col-sm-6">
					<span style="color: black; margin-right: 100px">名刺情報</span> <span
						style="color: #6D6363;"><c:out
							value="${cardInfo.cardIndexNo}"></c:out></span>
				</div>
				<div class="col-sm-6 ch-color-link">
					<c:choose>
						<c:when
							test="${pageContext.request.isUserInRole('ROLE_SUPERVISOR')}">
							<a id="approvalSave" class="ch-edit float-right-button">承認</a>
							<a id="tempSave" class="ch-edit float-right-button">一時保存</a>
							<a id="saveTempStatus4" class="ch-edit float-right-button">承認申請</a>
							<a id="moveFix" class="ch-edit float-right-button">修正依頼</a>
							<a id="backToManage" class="ch-edit float-right-button">戻る</a>
						</c:when>
						<c:when test="${pageContext.request.isUserInRole('ROLE_LEADER')}">
							<a id="approvalSave" class="ch-edit float-right-button">承認</a>
							<a id="moveFix" class="ch-edit float-right-button">修正依頼</a>
							<a id="backToManage" class="ch-edit float-right-button">戻る</a>
						</c:when>
						<c:when
							test="${pageContext.request.isUserInRole('ROLE_OPERATOR')}">
							<a id="saveTempStatus4" class="ch-edit float-right-button">承認申請</a>
							<a id="tempSave" class="ch-edit float-right-button">一時保存</a>
							<a id="backToManage" class="ch-edit float-right-button">戻る</a>
						</c:when>
					</c:choose>
				</div>
			</div>
		</div>
		<div id="center-side" class="col-sm-12">
			<div class="row bg-white box-shadow">

				<div class="col-sm-12" id="ct1">


					<div class="float-left" style="width: 35%;">
						<div style="margin-left: 10px; margin-top: 10px;">
							<!-- <input class="btn btn-default btn_rotate_180" type="button" value="180°回転" style="color: #666666; width:100px">
			             <input class="btn btn-default btn_rotate_left_90" type="button" value="左に90°回転" style="color: #666666; width:100px"> -->
							<input class="btn btn-default btn_rotate_right_90" type="button"
								value="右に90°回転" style="color: #666666; width: 100px">
						</div>
						<h4 class="h4-header">
							<a href="#">
								<div style="position: fixed; width: 33%;" data-toggle="modal"
									data-target="#myModal" id="imageDetail">
									<img src="data:image/png;base64,${cardInfo.imageFile}"
										class="img-100 img-full img-icon-rotated"> <img
										src="data:image/png;base64,${cardInfo.imageFile}"
										class="img-temp img-full img-icon-rotated"
										style="display: none">
								</div>
							</a>
						</h4>
						<input type="hidden" name="imageFile"
							value="data:image/png;base64,${cardInfo.imageFile}">
						<canvas id="canvas" style='display: none' width="1000px"
							height="602px"></canvas>
						<input type="hidden" name="rote" id="rote-image" value="">

					</div>

					<!-- <div class="float-right flr-400" style="width: 60%;"> -->
					<div class="row box-shadow bg-white float-right flr-400"
						style="width: 60%;">
						<div class="col-sm-12 right-side-top "></div>
						<div class="row" id="content-detail-card" style="width: 100%;">
							<form class="c-form-13">
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.name' />
										</h4>
									</div>
									<div class="float-right float-right-content">
										<h4 class=""></h4>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.lastname' />
											<span> * </span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="lastName" type="text"
											value="${cardInfo.lastName}" class="form-control"
											id="lastname" placeholder="">
										<p class="mesage_error error_lastname"></p>

									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.firstname' />
											<span> * </span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="firstName" type="text"
											value="${cardInfo.firstName}" class="form-control"
											id="fistname" placeholder="">
										<p class="mesage_error error_firstname"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.lastnamekana' />
											<span></span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="lastNameKana" type="text"
											value="${cardInfo.lastNameKana}" class="form-control"
											id="lastnamekana" placeholder="">
										<p class="mesage_error error_lastnamekana"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.firstnamekana' />
											<span></span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="firstNameKana" type="text"
											value="${cardInfo.firstNameKana}" class="form-control"
											id="firstnamekana" placeholder="">
										<p class="mesage_error error_fistnamekana"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.contactinfo' />
										</h4>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.teletephonenumber1' />
											<span> * </span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="telNumberCompany" type="text"
											value="${cardInfo.telNumberCompany}" class="form-control"
											id="telcompany" placeholder="">
										<p class="mesage_error error_telcompany"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.teletephonenumber2' />
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="telNumberDepartment" type="text"
											value="${cardInfo.telNumberDepartment}" class="form-control"
											id="teldepartment" placeholder="">
										<p class="mesage_error error_teldepartment"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.mobilenumber' />
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="mobileNumber" type="text"
											value="${cardInfo.mobileNumber}" class="form-control"
											id="mobilenumber" placeholder="">
										<p class="mesage_error error_mobilenumber "></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.faxnumber' />
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="faxNumber" type="text"
											value="${cardInfo.faxNumber}" class="form-control"
											id="exampleInputName2" placeholder="">
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.email' />
											<span> * </span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="email" type="email" value="${cardInfo.email}"
											class="form-control" id="email" placeholder="">
										<p class="mesage_error error_email"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.companyname' />
											<span> * </span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="companyName" type="text"
											value="${cardInfo.companyName}" class="form-control"
											id="companyname" placeholder="">
										<p class="mesage_error error_companyname"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.companynamekana' />
											<span> * </span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="companyNameKana" type="text"
											value="${cardInfo.companyNameKana}" class="form-control"
											id="companyNameKanaId" placeholder="">
										<p class="mesage_error error_companyNameKana"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.departmentname' />
											<span></span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="departmentName" type="text"
											value="${cardInfo.departmentName}" class="form-control"
											id="departmentname" placeholder="">
										<p class="mesage_error error_departmentName"></p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.positisionname' />
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="positionName" type="text"
											value="${cardInfo.positionName}" class="form-control"
											id="exampleInputName2" placeholder="">
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.computerUrl' />
										</h4>
									</div>
									<div class="float-right float-right-content">
										<input name="companyUrl" type="text"
											value="${cardInfo.companyUrl}" class="form-control"
											id="exampleInputName2" placeholder="">
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='edit.card.zipcode' />
											<span> * </span>
										</h4>
									</div>
									<div class="float-right float-right-content  ch-color-link">
										<div class=" inline block-inline"
											style="position: relative; margin: 0 auto; width: auto">
											<input name="zipCode" type="text" value="${cardInfo.zipCode}"
												class="form-control p-postal-code" id="zipcode"
												placeholder="" style="width: 322px">
											<!-- <a
												style="position: absolute; left: 198px; top: 0px; width: 150px; height: 32px;"
												class="ch-address btn_address">郵便番号から住所を入力</a> -->
										</div>
										<p class="mesage_error error_zipcode"></p>

										<span class="p-country-name" style="display: none;">Japan</span>

										<input type="text" class="p-region" readonly="" id="p-region"
											style="display: none;"> <input type="text"
											class="p-locality" readonly="" id="p-locality"
											style="display: none;"> <input type="text"
											class="p-street-address p-extended-address"
											id="extended-address" style="display: none;">
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='card.detail.addressFull' />
											<span></span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<textarea name="addressFull" class="form-control"
											id="addressFull" placeholder="" rows="3">${cardInfo.addressFull}</textarea>
										<p class="mesage_error error_addressFull" />
										<p style="margin: 5px; width: 100%; font-size: 12px;">
											<fmt:message key='card.memo.addressFull' />
										</p>
									</div>
								</div>
								<div class="col-sm-12">
									<div class="float-left">
										<h4>
											<fmt:message key='card.list.dateupdate' />
											<span></span>
										</h4>
									</div>
									<div class="float-right float-right-content">
										<div class="input-group date">
											<span class="input-group-addon"><i
												class="fa fa-calendar"></i></span> <input name="contactDate1"
												class="form-control create-date"
												value="<fmt:formatDate value="${cardInfo.contactDate}" pattern="yyyy年MM月dd日" />" />
											<input type="hidden" name="contactDate"
												class="form-control create-date"
												value="${cardInfo.contactDate}" />
										</div>
									</div>
								</div>
						</div>
	</form>
</div>
<!-- END BODY -->
<style>
.float-right-button {
	float: right;
	margin-left: 5px !important
}
</style>
