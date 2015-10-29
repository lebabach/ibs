<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style type="text/css">
.a-new-pc {
    float: right;
    text-align: center;
    padding: 5px 10px;
    color: #000;
    font-weight: bold;
}
a {
    color: #E3157A;
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
	bottom: -7px;
}

.searchTargetSwitcher {
	background: #fff;
	display: block;
	padding-bottom: 5px;
}

.list-group-item-title {
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
	font-family: "メイリオ", Meiryo, "ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro",
		"ＭＳ Ｐゴシック", "MS PGothic", sans-serif !important;
}

.list-group-item {
	background: #fff;
	border: 1px solid #b1b1b1;
}

.row-new {
	display: table;
	padding: 12px 0 10px 0;
	margin: 0;
	float: none;
	width: 100%;
}

.col-md-1 {
	display: table-cell;
	vertical-align: middle;
	width: 50px;
	float: none;
}

.col-md-5 {
	display: table-cell;
	vertical-align: middle;
	width: auto;
}

.col-md-6 {
	display: table-cell;
	vertical-align: middle;
	width: 290px;
	float: none;
}

.career_section{
	padding: 0px;
}

.career_date a, #rowData a{
    color: #676a6c;
}

.name1{
	margin: 10px 0 0 21px !important;
	width: 25%;
}
</style>

<c:if test="${not empty isExpried and isExpried == true}">
<style>
.card{
	background-color: red !important;
	padding : 8px 3px 10px 10px !important;
}
</style>
</c:if>

<div id="details" class="container">
	<!-- Start banner -->
	<div class="row animated fadeInRight">
		<div class="rel" style="margin-top:0">
			<div class="bg">
				<%-- <img src="<c:url value='/assets/img/bg-1.jpg'/>"> --%>
			</div>
			<div>
				<a href="javascript:goBack();" class="btn_back"><span><img
						src="<c:url value='/assets/img/mt.png'/>">戻る</span></a>
			</div>
			<div class="abs">
				<div style="padding:10px;">名刺所有者</div>
				
				<div class="name1">
					<dt style="font-size: 20px;">
						<b><c:out value="${userInfoDetail.name}" /></b>
					</dt>
					<p style="font-size: 15px; word-wrap: break-word;">
						<c:out value="${userInfoDetail.companyName}" />
					</p>
				</div>
				<!-- <ul class="icon">
                   <li class="ic_ml"><span><span class="none">Eight Link</span></span></li>
                   
                   </ul> -->
				<div class="card">
					<div class="card_img">
						<c:if test="${empty imageFile}">
							<a href="#" title="Image from Unsplash" data-target="#myModal"
							id="popup"> <img id="imageresource"
							src='<c:url value="/assets/img/card_08.png"></c:url>' style="border: 1px solid #b1b1b1; max-width: 330px; max-height: 190px;"></a>	
						</c:if>
						<c:if test="${not empty imageFile}">
							<a href="#" title="Image from Unsplash" data-target="#myModal"
							id="popup"> <img id="imageresource"
							src="data:image/png;base64,${imageFile}" style="border: 1px solid #b1b1b1; max-width: 330px; max-height: 190px;"></a>	
						</c:if>
					</div>
					<c:if test="${ isExpried == true }">
						<div style="color:#fff; width:100%; margin-top:5px"><fmt:message key="msg.card.disabled" /></div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

	<!-- End banner -->

	<div class="row" style="margin-top: 25px">
		<c:if test="${ isMyCard == true }">
		<button id="addTag" class="btn btn-primary " type="button">
			<i class="fa fa-tag"></i>
		</button> <span style="font-weight: bold;"><fmt:message key="button.addTag" /></span>
		</c:if>

		<c:if test="${ isMyCard == true and sfManualLinkFlg == true}">
		<a href="#" class="a-new-pc" data-toggle="modal" id="sfLoginLink">セールスフォース連携</a>
		<div class="modal" id="modal-login-saleforce" tabindex="-1">
            <div class="modal-dialog">
               <div class="modal-content">
                <!-- modal header -->
                  <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">
                      <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="modal-label" style="font-size: 13pt;font-weight: 600;">Salesforce</h4>
                  </div>
                  <!-- modal body -->
                    <div class="modal-body">
                      <div id="loading-copy" style="display: none; z-index: 1000; position: fixed;top: 50%;left: 50%;margin-top: -50px;margin-left: -50px;">
							<img src="<c:url value='/assets/img/loading-card.gif'/>" />
					  </div>
					  
                      <div class="form-group" id="errors" style="padding:10px 0 10px 0; text-align: center;"></div>
                      <div class="form-group">
                          <input type="email" class="form-control" id="sfEmail" placeholder="ID" value="" name="login_id">
                      </div>
                      <div class="form-group">
                          <label></label>
                          <input type="password" class="form-control" id="sfPassword" placeholder="パスワード" value="" name="login_pass">
                      </div>
                  </div>
                    <div class="modal-footer" style="text-align:center;">
                    <button style="width:200px;" type="button" class="btn btn-info" id="loginSaleForce">ログイン</button>
                  </div>
              </div>
           </div>
      </div>
		</c:if>

		<div class="balloon lbl_balloon" style="display: none; margin-top: 10px">
			<div class="">
				<div class="col-sm-12" style="border-bottom: solid 1px #c1c1c1;">
					<table class="table" id="tags">
						<col width="10%">
						<col width="80%">
						<col width="10%">
						<tbody>
						</tbody>
					</table>
				</div>
			</div>

			<div class="">
				<div class="input-group lbl" style="padding: 15px 15px 10px 15px; display:inline-block; width:400px">
					<form name="addTag" id="addTag">
						<input type="hidden" value="${ cardInfo.cardId }" name="cardId" />
						<input type="text" class="form-control" placeholder="新規タグを追加" style="margin:0; width:317px;" name="tagName" id="tagName">
						<span class="input-group-btn">
							<button id="addLabel" type="button" class="btn btn-success">作成</button>
						</span>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="margin-top: 20px">
		<!-- Left side -->
		<div class="col-xs-5">
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading" style="height: 40px;">
						<div style="float: left">
							<h5>名刺交換日</h5>
						</div>

						<c:if test="${ isMyCard == true }">
						<div class="div-pen div-pen-contact" style="float: right">
							<span><fmt:message key="pen.contact" /></span>
							<a> <i class="fa fa-pencil"></i></a>
						</div>
						</c:if>
					</div>
<style type="text/css">
.edit2, .div-pen-contact{
	cursor: pointer;
}

.div-pen-ac {
	background: #dfdfdf;
	padding: 1px 8px;
}

.p-date {
	border: none;
	background: none;
	margin-left: 15px;
	padding-bottom: 8px;
}

.p-date-input {
	display: block;
	border: 1px solid #b1b1b1;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	padding: 10px 9px 8px 9px;
	background-color: #f2f2f2;
	box-shadow: 0 3px 4px #ccc inset;
	width: 300px;
	color: #666666;
	font-weight: bold;
	font-size: 14px;
	margin: 0 15px;
}

.p-fomr {
	display: inline-block;
	width: 100%;
	border-top: 1px solid #b1b1b1;
	padding: 12px 17px 10px 17px;
	margin: 10px 0 0 0;
	text-align: center;
	background: #e2f3ff;
}

.p-fomr2 {
    display: inline-block;
    width: 100%;
    border-top: 1px solid #B1B1B1;
    padding: 12px 17px 10px;
    margin: 10px 0px 0px;
    text-align: center;
    background: #E2F3FF none repeat scroll 0% 0%;
}

.input-submit {
    font-size: 1.2em;
    display: inline-block;
    padding: 5px 12px 2px;
    border: 1px solid #E3157A;
    border-radius: 3px;
    background-color: #E3157A;
    color: #FFF !important;
    vertical-align: middle;
    text-shadow: 0px -1px 2px #000;
    white-space: nowrap;
}

.input-submit-date {
    font-size: 1.2em;
    display: inline-block;
    padding: 5px 12px 2px;
    border: 1px solid #E3157A;
    border-radius: 3px;
    background-color: #E3157A;
    color: #FFF !important;
    vertical-align: middle;
    text-shadow: 0px -1px 2px #000;
    white-space: nowrap;
}

.input-reset {
    font-size: 1.2em;
    display: inline-block;
    padding: 5px 12px 2px;
    border: 1px solid #A5A5A5;
    border-radius: 3px;
    background: #E0E0E0 -moz-linear-gradient(center top , #E0E0E0, #C8C8C8) repeat scroll 0% 0%;
    color: #666 !important;
    vertical-align: middle;
    text-shadow: 0px 1px 0px #FFF;
    white-space: nowrap;
}

.input-reset-date {
    font-size: 1.2em;
    display: inline-block;
    padding: 5px 12px 2px;
    border: 1px solid #A5A5A5;
    border-radius: 3px;
    background: #E0E0E0 -moz-linear-gradient(center top , #E0E0E0, #C8C8C8) repeat scroll 0% 0%;
    color: #666 !important;
    vertical-align: middle;
    text-shadow: 0px 1px 0px #FFF;
    white-space: nowrap;
}

.career_date{
	border-bottom: 0px !important;
}
</style>
					<script type="text/javascript">
						function goBack(){
							if(document.referrer.indexOf("user/home") >= 0){
								window.location = "/ecard-webapp/user/home";
							}else{
								window.history.back();
								return false;	
							}
							
						}
						function checkValidationForm() {
							var checkValidation = true;
							var email = $("#email").val();
							var validationMail=/^([\w-]+(?:\.[\w-]+)*)[@＠]+((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
							if (email == "") {
								$("#requireEmail").text(
										"Emailを入力してください");
								checkValidation = false;
								$("#requireEmail").css("display", "block");
							}else if (!(validationMail.test(email))) {
								$("#requireEmail").text(
								"Email が正しくありません");
								checkValidation = false;
								$("#requireEmail").css("display", "block");
							}
							return checkValidation;
						}
						function resetValidationForm() {
							$("#requireEmail").text("");
						}
                          $(document).ready(function(){
                        	$("#lblContactDate").show();
                   	   	    $("#frmEditContactDate input[name=contactDate]").hide();
	                        
                        	$('.p-date').datepicker({
 	      							language : 'ja',
 	      							todayHighlight : true,
 	      							keyboardNavigation : false,
 	      							format : 'yyyy年MMdd日',
 	      							forceParse : true,
 	      							autoclose : true,
 	      							calendarWeeks : true
   					     	});
                        	  
                        	  $('.div-pen-contact').on({
                               'click':function(){
                            	   	   $("#lblContactDate").hide();
                            	   	   $("input[name=contactDate]").show();
	                            	   
                                        $(".p-date").addClass("p-date-input");
                                        $('.p-fomr').show();
                                        $('.div-pen').addClass('div-pen-ac') 
                                        $(".p-date").removeAttr('readonly');
                                  }
                              });
                        	  
                        	  $('.input-reset-date').on({
	                               'click':function(){
	                            	   $("#lblContactDate").show();
                            	   	   $("input[name=contactDate]").hide();
                            	   	   
	                                          $(".p-date").removeClass("p-date-input");
	                                          $('.p-fomr').hide();
	                                          $('.div-pen').removeClass('div-pen-ac') 
	                                          $(".p-date").attr('readonly', 'readonly');
	                                  }
                              }); 
                        	  
	                              $('.div-pen').on({
	                               'click':function(){
	                                          $(".p-date").addClass("p-date-input");
	                                          $('.p-fomr').show();
	                                          $('.div-pen').addClass('div-pen-ac') 
	                                          $(".p-date").removeAttr('readonly');
	                                  }
	                              });   
	                              /* $('.input-reset,.input-submit').on({
	                               'click':function(){
	                                          $(".p-date").removeClass("p-date-input");
	                                          $('.p-fomr').hide();
	                                          $('.div-pen').removeClass('div-pen-ac') 
	                                          $(".p-date").attr('readonly', 'readonly');
	                                  }
	                              });   */   
                              
                              	$("#editContactDate").click(function(){
                              		var dateTime = new Date($("input[name=contactDate]").datepicker("getDate"));
                              		var strDateTime =  dateTime.getFullYear() + "-" + (dateTime.getMonth()+1) + "-" + dateTime.getDate();
                              		//console.log(strDateTime);
                              		$.ajax({
										  type: "POST",
										  url: "<c:url value='/user/editContactDate' />",
										  data: 'contactDate='+ strDateTime +'&cardId='+$("input[name=cardId]").val(),
										  success: function(){
											  BootstrapDialog.show({
					                				title: '<fmt:message key="popup.title.info" />',
					               	             	message: '<fmt:message key="edit.contactDate.success" />'
					                	      });
											  window.location.href = "<c:url value='/user/card/details/' />"+$("input[name=cardId]").val();
										  },
										  error: function(){
											  BootstrapDialog.show({
					                				title: '<fmt:message key="popup.title.info" />',
					               	             	message: '<fmt:message key="edit.contactDate.failed" />'
					                	      });
										  }
									});
                              	});
                          });   
                          
                      </script>
                      
					<div class="panel-body" style="padding: 12px 0 0 0;">
						<form name="frmEditContactDate" id="frmEditContactDate">
							<input type="hidden" value="${ cardInfo.cardId }" name="cardId" />
							<label id="lblContactDate" style="display:none;margin-left:15px;font-weight:normal;"><fmt:formatDate value='${ cardInfo.contactDate }' pattern="yyyy年MM月dd日"/></label>
							<input value="<fmt:formatDate value='${ cardInfo.contactDate }' pattern="yyyy年MM月dd日"/>" class="p-date" name="contactDate" readonly="readonly">
							
							<p class="p-fomr" style="display: none">
								<input type="button" class="input-submit-date" value="保存" id="editContactDate"> 
								<input type="reset" class="input-reset-date" value="キャンセル">
							</p>
						</form>
					</div>
					<!-- <div class="panel-footer" style="height:50px">
                       <button class="btn btn-sm btn-primary" type="submit">Save</button>
                       </div> -->
				</div>
				
				<c:if test="${ fn:length(listCardMemo) gt 5 }">
					<style type="text/css">
						.panel-memo{
							height : 500px;
							overflow-y: auto;
							overflow-x: hidden; 
						}
					</style>
				</c:if>
				
				<c:if test="${ isMyCard == true }">
				<div class="panel panel-default panel-memo">
					<div class="panel-heading">
						<h5>メモ</h5>
					</div>
<style type="text/css">
.ul-memo {
	width: 100%;
	display: inline-block;
	margin: 0;
	padding: 0;
}

.ul-memo li {
	display: block;
	width: auto;
	position: relative;
	padding: 4px 10px;
	border-top: 1px dotted #b1b1b1;
	margin-top: -1px;
}

.ul-memo li p {
	color: #666;
	display: inline-block;
	width: 90%;
	margin: 0;
}

.ul-memo li p.p-date-n {
	font-size: 0.9em;
	color: #b1b1b1;
}

.ul-memo li span {
	position: absolute;
	top: 15px;
	right: 7px;
	color: #666;
	width: 17px;
	height: 22px;
	font-size: 14px;
	text-align: center;
	cursor: pointer;
}

.panel-body-memo {
	border-bottom: 1px solid #ddd;
}

.career_section{
	border-bottom : 0px !important;
}
</style>
					<script type="text/javascript">
                          $(document).ready(function(){
                        	  //Show scrollbar
                              if($('.ul-memo li').length > 5){
                             	 $(".panel-memo").attr("style", "height:367px; overflow-x:hidden; overflow-y:auto;")
                              }
                        	
                              $('.click-memo').click(function(){
                                     var memo = $('#textarea-memo').val();
                                     
                                     if(memo == ""){
                                          alert("保存するメモがありません");
                                     }
                                     else{
                                          var json = {"memo" : memo, "cardId" : $("input[name=cardId]").val()};
                                          addCardMemo(json);
                                     }
                                     //Show scrollbar
                                     if($('.ul-memo li').length > 5){
                                    	 $(".panel-memo").attr("style", "height:367px; overflow-x:hidden; overflow-y:auto;")
                                     }
                              });    
                             
                           });     
                      </script>
					<div class="panel-body">
						<textarea id="textarea-memo" class="form-control custom-control"
							rows="3" style="resize: vertical; margin-bottom: 5px;"
							></textarea>
						<button class="btn btn-sm btn-primary pull-right click-memo">保存</button>
					</div>
					
					<ul class="ul-memo">
					<c:forEach var="cardMemo" items="${listCardMemo}" varStatus="loop">
						<li>
							<p style="font-size:10pt;"><c:out value="${ cardMemo.memo }"></c:out> </p>
							<p class="p-date-n"><fmt:formatDate value='${ cardMemo.create_date }' pattern="yyyy年MM月dd日"/></p><span class="delMemo" id="${ cardMemo.seq }" class='span-close'>x</span>
						</li>
					</c:forEach>
					</ul>
				</div>
				</c:if>
				
				<div class="row" style="padding: 10px;">
					<p style="color:red;">コンタクト履歴は他ユーザに公開されるため、機密情報等についてはメモ欄にご入力下さい。</p>
				</div>
				<!-- Contact history -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h5 style="position: relative;">コンタクト履歴
                        <c:if test="${ isMyCard == true }">
                        <button data-toggle="modal" data-target="#modal-example2" class="btn btn-primary btn-lg" 
                        	style=" position: absolute;right: 0;top: -4px; padding:6px 9px; font-size:13px;">追加</button>
                        </c:if>	
                        </h5>
                    </div>

                     <div class="modal" id="modal-example2" tabindex="-1">
                          <div class="modal-dialog">
                             <form action="<c:url value='/user/addContactHistory' />" method="post" name="saveContactFrm" id="saveContactFrm">
	                             <div class="modal-content">
	                              <!-- modal header -->
	                                <div class="modal-header">
	                                  <button type="button" class="close" data-dismiss="modal">
	                                    <span aria-hidden="true">×</span>
	                                  </button>
	                                  <h4 class="modal-title" id="modal-label">コンタクト履歴の追加</h4>
	                                </div>
	                                <!-- modal body -->
	                                  <div class="modal-body">
	                                    <div class="form-group">
	                                        <label for="contactDate">コンタクト日付</label>
	                                        <input class="form-control" id="contactDate" name="contactDate" id="contactDate" placeholder="" value="">
	                                    </div>
	                                    <div class="form-group">
	                                      <label for="contactMemo">内容</label>
	                                        <textarea style="width:100%; height:200px; border:1px solid #e5e6e7" name="contactMemo" id="contactMemo"></textarea>
	                                    </div>
	                                </div>
	                                  <div style="text-align: center;">コンタクト履歴は公開されるため、機密情報を入力しないこと</div>
	                                  <div class="modal-footer" style="text-align:center;">
	
	                                  <button style="width:200px;" type="button" class="btn btn-info" id="saveContactHistory">登録</button>
	                                </div>
	                            </div>
                            </form>
                         </div>
                    </div>                
					
					<c:if test="${fn:length(contactHistoryList) gt 3}">
						<style type="text/css">
							#contact-hist-body {
								height: 340px;
								overflow-y: auto;
								overflow-x: hidden;
							}
						</style>
					</c:if>
					<style type="text/css">
                      .delContactHist{
                    		float: right;
							width: 17px;
							height: 22px;
							font-size: 14px;
							text-align: center;
							cursor: pointer;
                      }
					  #modal-example2 .dropdown-menu{z-index:100000 !important;}
					  .datepicker{z-index:10000000 !important;}
					  #old_card_body a, #old_card_body tr{
					  	cursor: pointer;
					  }
                    </style>
                    <div class="panel-body" id="contact-hist-body">
                        <c:if test="${ not empty contactHistoryList }">
                        	<c:forEach var="contactHistory" items="${contactHistoryList}" varStatus="loop">
	                        <div class="career_section selected" style="border-bottom:0px;">
	                            <div class="career_date" style="font-weight: bold !important;">
	                            	<fmt:formatDate value='${ contactHistory.contactDate }' pattern="yyyy年MM月dd日"/>
	                            	<div class="delContactHist" id="${ contactHistory.contactHistoryId }">x</div>
	                            </div>
	                            <div>
	                                <table class="table">
	                                    <tbody>
	                                        <tr id="rowData">
	                                            <td>
	                                                <p><c:out value="${ contactHistory.contactMemo }"></c:out></p>
	                                            </td>
	                                        </tr>
	                                    </tbody>
	                                </table>
	                            </div>
	                        </div>
	                        </c:forEach>
                        </c:if>
                    </div>
                </div>
				<!-- End contact history -->
				
				<!-- User connected -->
				<c:if test="${fn:length(listOldCard) gt 5}">
					<style type="text/css">
						#old_card_body {
							height: 340px;
							overflow-y: auto;
							overflow-x: hidden;
						}
					</style>
				</c:if>
				
				<c:if test="${ not empty listOldCard }">
				<script type="text/javascript">
					$(document).ready(function(){
						$(".oldCard").click(function(){
							//console.log(this.id);
							window.location.href = "<c:url value='/user/card/details/'/>"+this.id;
						});
					})
				</script>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5>名刺履歴</h5>
					</div>
					<div class="panel-body" id="old_card_body">
						<c:if test="${ not empty listOldCard }">
							<c:forEach var="oldCardList" items="${listOldCard}" varStatus="loop">
							<div class="career_section selected oldCard" style="border-bottom:0px;" id="${ oldCardList.cardId }">
								<div class="career_date " style="font-weight: bold !important;">
									<fmt:formatDate value='${ oldCardList.contactDate }' pattern="yyyy年MM月dd日"/>
								</div>
								<div>
									<table class="table">
										<tbody>
											<tr id="rowData">
												<td>
													<p>
													<c:out value="${ oldCardList.companyName }"></c:out></p>
													<p></p>
													<p>
													<c:out value="${ oldCardList.departmentName }"></c:out></p>
												</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							</c:forEach>
						</c:if>						
					</div>
				</div>
				</c:if>
				<!-- End User connected -->
			</div>
			<c:if test="${ isMyCard == true }">
			<button id="delBusinessCard" type="button"
				class="btn btn-block btn-outline btn-primary">
				<i class="fa fa-trash"></i> この名刺を削除する
			</button>
			</c:if>
		</div>

		<!-- End Left side -->

		<!-- Right side -->
		<div class="col-xs-7">
			<div class="panel panel-default">
				<div class="panel-heading" style="height: 40px;">
					<div class="col-md-12">
						<div style="float: left">
							<h5>名刺情報</h5>
						</div>
						<c:if test="${ isMyCard == true }">
						<div class="edit2" style="float: right">
							<span><fmt:message key="pen.contact" /></span>
							<a id="editPersonalInfo"> <i class="fa fa-pencil"></i>
							</a>
						</div>
						</c:if>
					</div>
				</div>
				<style type="text/css">
.input-new-1 {
	border: none;
	background: none;
}

.input-new-1-ac {
	display: block;
	border: 1px solid #b1b1b1;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	padding: 10px 9px 8px 9px;
	background-color: #f2f2f2;
	box-shadow: 0 3px 4px #ccc inset;
	width: 300px;
	color: #666666;
	font-weight: bold;
	font-size: 14px;
	margin: 0 15px;
}

label.error {
    color: #CC5965;
    display: inline-block;
    margin-left: 15px;
}

#editForm a{
	color: #333 !important;
}
</style>
				<script type="text/javascript">
                    $(document).ready(function(){
                    	$(".email-hide").show();
                        $("#email").hide();
                        $(".companyUrl-hide").show();
                        $("#companyURL").hide();
                        $("#email").blur(function(){
                        	resetValidationForm();
                        });
                    	$(".address-hide").show();
                    	$("#address").hide();	
                    
                            $('.edit2').on({
                             'click':function(){
                                        $(".input-new-1").addClass("input-new-1-ac");
                                        $('.p-fomr2').show();
                                        $('.edit2').addClass('div-pen-ac') 
                                        $(".input-new-1").removeAttr('readonly');
                                        
                                        $(".email-hide").hide();
                                        $("#email").show();
                                        $(".companyUrl-hide").hide();
                                        $("#companyURL").show();
                                        
                                        $(".address-hide").hide();
                                        $("#address").show();
                                }
                            });   
                            $('.input-reset').on({
                             'click':function(){
                                        $(".input-new-1").removeClass("input-new-1-ac");
                                        $('.p-fomr2').hide();
                                        $('.edit2').removeClass('div-pen-ac') 
                                        $(".input-new-1").attr('readonly', 'readonly');
                                        
                                        $(".email-hide").show();
                                        $("#email").hide();
                                        $(".companyUrl-hide").show();
                                        $("#companyURL").hide();
                                        
                                        $(".address-hide").show();
                                        $("#address").hide();
                                        
                                        validator.resetForm();
                                }
                            });     
                                                    
                            var validator = $("#editForm").validate({
                            	rules: {
                            		companyName: "required",
                            		companyNameKana: "required",
                            		fisrtName: "required",
                            		lastName: "required",
                            		fisrtNameKana: "required",
                            		lastNameKana: "required",
                    			    telNumberDepartment : {
                    			    	required: false,
                    			    	customphone: true
                    			    },
                    			    telNumberCompany : {
                    			    	required: false,
                    			    	customphone: true
                    			    },
                    			    mobileNumber : {
                    			    	required: false,
                    			    	customphone: true
                    			    },
                    			    urlvalidation : { 
                    			    	urlvalidation : true, 
                    			    	required: false
                    			    },
                    			    zipCode : {
                    			    	required: false,
                    			    	customphone: true
                    			    },
                    			    email : {
                    			    	required: false,
                    			    	email: false
                    			    }
                    			},
                    			messages: {
                    				companyName: '<fmt:message key="valid.companyName" />',
                    				companyNameKana: "<fmt:message key="valid.companyNameKana" />",
                    				fisrtName: "<fmt:message key="valid.name" />",
                            		lastName: "<fmt:message key="valid.name" />",
                            		fisrtNameKana: "<fmt:message key="valid.nameKana" />",
                            		lastNameKana: "<fmt:message key="valid.nameKana" />",
                    				telNumberDepartment: "<fmt:message key="valid.phoneNumber" />",
                    				telNumberCompany: "<fmt:message key="valid.phoneNumber" />",
                    				mobileNumber: "<fmt:message key="valid.phoneNumber" />",
                    				zipCode: "<fmt:message key="valid.zipCode" />"
                    			}
                            });
                            
                            var validSaveContactFrm = $("#saveContactFrm").validate({
                            	rules: {
                            		contactDate: "required"
                    			},
                    			messages: {
                    				contactDate: '<fmt:message key="valid.contactDate" />'
                    			}
                            });
                            
                            $(".input-submit").click(function(){
                          		if($("#editForm").valid()){
                          			if(!checkValidationForm()){
                          				return false;
                          			}
                           			$(".input-new-1").removeClass("input-new-1-ac");
                                    $('.p-fomr2').hide();
                                    $('.div-pen').removeClass('div-pen-ac') 
                                    $(".input-new-1").attr('readonly', 'readonly');
                                    $("input[name=contactDate]").datepicker("getDate");
                           			//$("#editForm").submit();
                                    saveEditCardInfo();                           			
                           		}
                           		else{
                           			$(".input-new-1").addClass("input-new-1-ac");
                                    $('.p-fomr2').show();
                                    $('.div-pen').addClass('div-pen-ac') 
                                    $(".input-new-1").removeAttr('readonly');
                                    
                           			return false;
                           		}
                            });
                            
                           
                    });
                    
                    function validateURL(value){
                      // URL validation from http://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url
                      var expression = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/gi;
                      var regex = new RegExp(expression);
                      return value.match(regex);
                    }

                    $.validator.addMethod("urlvalidation", function(value, element) {
                      return this.optional(element) || validateURL(value);
                    }, "<fmt:message key="valid.URL" />");
                    
                    $.validator.addMethod('customphone', function (value, element) {
                        return this.optional(element) || /^[0-9-+()●]+$/.test(value);
                    }, "<fmt:message key="valid.phoneNumber" />");
                    
                    $.validator.addMethod('customEmail', function (value, element) {
                        return this.optional(element) || /^([\w-]+(?:\.[\w-]+)*)＠((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i.test(value);
                    });
                    
                    function isValidEmailAddress(emailAddress) {
                	    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))[@][＠]((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
                	    return pattern.test(emailAddress);
                	};
                </script>
                
				<div class="panel-body" style="padding: 15px 15px 0 15px;">
					<form id="editForm">
						<input type="hidden" name="cardId" value="${ cardInfo.cardId }" />
						<input type="hidden" name="imageFile" value="${cardInfo.imageFile}" />
						<input type="hidden" name="fileOutputFlg" value="${cardInfo.fileOutputFlg}" />
						<input type="hidden" name="cardOwnerId" value="${cardInfo.cardOwnerId}" />
						<input type="hidden" name="publishStatus" value="${cardInfo.publishStatus}" />
						<input type="hidden" name="approvalStatus" value="${cardInfo.approvalStatus}" />
						<input type="hidden" name="contactDate"  value="<fmt:formatDate value='${ cardInfo.contactDate }' pattern="yyyy-MM-dd"/>" />
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_busho.png'/>"
										alt="${cardInfo.companyName}">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.companyName}" name="companyName" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.companyNameKana}" name="companyNameKana" readonly="readonly">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_com.png'/>"
										alt="${cardInfo.departmentName}">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.departmentName}" name="departmentName" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.positionName}" name="positionName" readonly="readonly">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_info.png'/>"
										alt="${cardInfo.lastNameKana}">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.lastName}" name="lastName" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.firstName}" name="firstName" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.lastNameKana}" name="lastNameKana" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.firstNameKana}" name="firstNameKana" readonly="readonly">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_mail.png'/>" alt="氏名">
								</dt>
								<dd>
									<div class="ipt_txt front_email email-hide"
										style="display: none">
										<a href="mailto:${cardInfo.email}" target="_blank">${cardInfo.email}</a>
									</div>
									<input type="email" class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.email}" name="email" id="email">
									<p style="color: #CC5965; font-weight:bold;display:none; padding-left: 15px;" id="requireEmail"><fmt:message key="valid.email" /></p>
									<p style="color: red; display: none; padding-left: 15px;" id="formatEmail"><fmt:message key="valid.email.format" /></p>
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_tel_com.png'/>"
										alt="会社電話">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.telNumberDepartment}" name="telNumberDepartment" id="telNumberDepartment"  readonly="readonly"/> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.telNumberCompany}" name="telNumberCompany" id="telNumberCompany" readonly="readonly"><br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.mobileNumber}" name="mobileNumber" id="mobileNumber" readonly="readonly" />
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_fax.png'/>" alt="会社FAX">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.faxNumber}" name="faxNumber" readonly="readonly">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/zip_code_ic.png'/>" alt="会社FAX">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.zipCode}" name="zipCode" id="zipCode" readonly="readonly">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_address.png'/>"
										alt="${cardInfo.address1}">
								</dt>
								<dd class="address-hide" style="display: none">
									<div class="ipt_txt front_email">
										<a href="#" target="_blank" id="linkMap" onclick="gotoMap();">
											<c:out value="${ cardInfo.address1 }"></c:out> 
											<c:out value="${ cardInfo.address2 }"></c:out> 
											<c:out value="${ cardInfo.address3 }"></c:out> 
											<c:out value="${ cardInfo.address4 }"></c:out> 
										</a>
									</div>
								</dd>	
								<dd id="address">
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address1}" name="address1" id="address1" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address2}" name="address2" id="address2" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address3}" name="address3" id="address3" readonly="readonly"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address4}" name="address4" id="address4" readonly="readonly"> <br/>
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_url.png'/>"
										alt="ホームページ">
								</dt>
								<dd>
									<div class="ipt_txt front_url1 companyUrl-hide" style="display: none;">
										<a href="${cardInfo.companyUrl}" target="_blank">${cardInfo.companyUrl}</a>
									</div>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.companyUrl}" name="companyUrl" id="companyURL" readonly="readonly">
								</dd>
							</dl>
						</div>
						
						<p class="p-fomr2"
							style="border: none; margin: 0 0 0 -15px; width: 107.4%; display: none">
							<input type="button" class="input-submit" id="btn-editForm" value="保存"> 
							<input type="reset" class="input-reset" value="キャンセル">
						</p>
					</form>
				</div>

			</div>
			
			<c:if test="${ fn:length(listCardConnect) gt 5 }">
				<style type="text/css">
					.listCardConnect{
						height : 500px;
						overflow-y: auto;
						overflow-x: hidden; 
					}
				</style>
			</c:if>
			<c:if test="${ not empty listCardConnect }">
			<!-- List card connected -->
			<div class="panel panel-default">
				<div class="panel-heading" style="height: 40px;">
					<div style="float: left; font-weight: bold;">
						<h5><fmt:message key="card.detail.connect" /></h5>
					</div>
				</div>
				
				<div class="panel-body listCardConnect" style="padding: 10px 0;">
				<style type="text/css">
                  .div-new{
                           padding: 0 10px 5px 10px !important;
                           margin-bottom: 10px !important;
                           border-left: none !important;
                            border-right: none !important;
                             border-top: none !important;
                           border-bottom: 1px solid #e7eaec !important;
                           border-radius:1px;
                  	}
                   .col-md-5-n{
                       width: 60%;
                       
                   }
                   .col-md-5-n .col-xs-11{width: 100%; padding: 0 !important;}
                   .col-md-5-n .col-xs-11 p{margin: 0 !important}
                   .col-md-5-n .col-xs-11 p.num{height: auto;}
                   .col-md-6{width:40%;  padding: 0 !important;}
                   .col-md-6-n .col-xs-5 { width: 60%; position: relative;}
                   .col-md-6-n .col-xs-5 img{
                       left: 0;
                       right: inherit;
                   }
                   .col-md-6-n .col-xs-7 { width: 40%;}
                   img.img-thumb-n{     max-width: 123px;
                       height: 74px;
                       margin-top: 18px;}
                   img.img-1{ margin-top: 40px; width: 28px;}
                   img.img-2{ width: 26px; margin-top: 71px;}
                   .mg-top {
					    margin-top: 5px;
					}
					.div-new a{
						color: #333 !important;
					}
                </style>
					<c:forEach var="listCardConnected" items="${listCardConnect}" varStatus="loop">
						<div class="list-group-item pointer div-new">
							<div class="row" style="margin-right: 0">
								<div class="col-md-5 col-md-5-n">
									<div class="col-xs-11 mg-top">
										<p class="name"><a href="<c:url value='/user/profile/${ listCardConnected.userId }'/>"><c:out value="${listCardConnected.name}"></c:out></a> </p>
										<p class="livepass"><a href="<c:url value='/user/profile/${ listCardConnected.userId }'/>"><c:out value="${listCardConnected.companyName}"></c:out></a></p>
										<p class="department_and_position">
											<a href="<c:url value='/user/profile/${ listCardConnected.userId }'/>"><c:out value="${listCardConnected.departmentName}"></c:out> </a>
											<a href="<c:url value='/user/profile/${ listCardConnected.userId }'/>"><c:out value="${listCardConnected.positionName}"></c:out></a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			<!-- List card connected -->
			</c:if>
		</div>
		<!-- End Right side -->
	</div>
</div>

<script>
     var id_manager = 1;
     $(document).ready(function(){
    	 var locationURL = ""+window.location;
         var arrParam = locationURL.split("/");
         var cardIdParam = arrParam[7];
         //Find get cardId
         var hCardId = $("#rowData").find('.card-Id').val();
         
         //Add contact history
         $('#saveContactFrm #contactDate').click(function(){
        	 $(".datepicker").css("zIndex", 100000000000);
         });
         
         $('#saveContactFrm #contactDate').datepicker({
   			language : 'ja',
   			todayHighlight : true,
   			keyboardNavigation : false,
   			format : 'yyyy年MMdd日',
   			forceParse : true,
   			autoclose : true,
   			calendarWeeks : true,
   			endDate: new Date()
       	 });
                           
         $("#saveContactHistory").click(function(){
       		if($("#saveContactFrm").valid()){
        	 	saveContactHistory(); 
       		}
         });
         
         $(document).on("click",".delContactHist",function(e){
 			//console.log(this.id);    		 
        	 var json = {"cardInfo" : {"cardId" : $("input[name=cardId]").val()}, "contactHistoryId" : this.id};  
    	   	 delContactHistory(json);
     	 });
         
         $("#loginSaleForce").click(function(){
       		if(validLoginSaleForce()){
        	 	loginSaleForce(); 
       		}
         });
         
         $("#sfLoginLink").click(function(){
        	 var lastName = $("input[name=lastName]").val();
        	 var firstName = $("input[name=firstName]").val();
        	 var companyName = $("input[name=companyName]").val();
        	 if(lastName == "" || firstName == "" || companyName == ""){
        		 BootstrapDialog.show({
     				title: '<fmt:message key="popup.title.info" />',
    	             	message: '<fmt:message key="valid.login.saleforce" />'
     	      		});
        	 }
        	 else{
        		 $(this).attr("data-target", "#modal-login-saleforce");
        	 }
         });
                      	 
         //Get tag for card
         getTagForCard();
         
    	 //Delete card bussiness
    	 $("#delBusinessCard").click(function(){
    		 var confirm = window.confirm('<fmt:message key="card.list.confirmDelete"/>');
    	     if(confirm){
	    		 $.ajax({
		   			  type: "POST",
		   			  url: "<c:url value='/user/delBusinessCard' />",
		   			  data: 'cardId='+ $("input[name=cardId]").val(),
		   			  success: function(){
		   				  window.location.href = "<c:url value='/user/home' />";
		   			  },
		   			  error: function(){
		   				  BootstrapDialog.show({
		        				title: '<fmt:message key="popup.title.info" />',
		       	             	message: '<fmt:message key="remove.card.failed" />'
		        	      		});
		   			  }
	   			});
    	     }
    	 });
    	 
    	 //Delete card memo
    	 $(document).on("click",".delMemo",function(e){
			//console.log(this.id);    		 
    		 var json = {"cardId" : $("input[name=cardId]").val(), "seq" : this.id};    		 
    		 delCardMemo(json);
    	 });
    	 
    	//Delete card tag
         //$(".delTag").click(function(){
       	 $(document).on("click",".delTag",function(e){
       		//console.log("tagId : "+e.target.id + "==> a: "+this.id);
       		deleteTag(this.id);
         });
    	
    	 var isEdit = (getUrlParameter('isEdit') != null) ? getUrlParameter('isEdit') : "";
    	 if(isEdit){
    		 BootstrapDialog.show({
	             title: '<fmt:message key="popup.title.info" />',
	             message: '<fmt:message key="popup.edit.card.success" />'
	        });
    	 }
    	 if(isEdit == false && getUrlParameter('isEdit') != null){
    		BootstrapDialog.show({
 				title: '<fmt:message key="popup.title.info" />',
	             	message: '<fmt:message key="edit.card.failed" />'
 	        });
    	 }
    	 
       $('.i-checks').iCheck({
         checkboxClass: 'icheckbox_square-green',
         radioClass: 'iradio_square-green',                
       });

       $("input[name=checkTag]").on('ifChecked', function(event){
         	//console.log("ifChecked 111");
         	var json = {"tagId" : $(this).val(), "cardId" : $("input[name=cardId]").val()};
    	    addCardTag(json);
       });
       
       $("input[name=checkTag]").on('ifUnchecked', function(event){     
    	   //console.log("ifUnchecked 111");
           var json = {"tagId" : $(this).val(), "cardId" : $("input[name=cardId]").val()};
           deleteCardTag(json);
       });

       $("#addTag").click(function(e){
         if($(".balloon").css('display') == 'block')
           $(".balloon").css("display","none");
         else
           $(".balloon").css("display","block");
       });

       $('.makefriend').click(function(e){
         if($(this).find('button').hasClass('btn-success')){
           $(this).find('button').removeClass('btn-success');
           $(this).find('button').addClass('btn-default');
           $(this).find('button').text("取り消す");
           return false;
         } 
          
         if($(this).find('button').hasClass('btn-default')){
           $(this).find('button').removeClass('btn-default');
           $(this).find('button').addClass('btn-success');
           $(this).find('button').text("追加");
           return false;
         }

       });
       $('.mail').click(function(e) {
         //console.log('Go to mailbox');
         e.stopPropagation();
       });

       // Click to personal details page
       $('.business_card_book .list-group-item').click( function() {
         //console.log("Move to personal details page"); 
         window.location.href = "personal_details.html"
       }).hover(function() {
         $(this).toggleClass('hover');
       });

       // Sort name card
       $('.clearfix a').click(function(e) {
         $(this).parent().find('a').removeClass("active");
         $(this).addClass("active");

         // Process with Business Card List
         if($(".BusinessCardList").hasClass("active")) {                
           $(".business_card_book").css("display", "block");
           $(".peopleSearchResults").css("display", "none");
           $(".network_card_book").css("display", "none");                
           $(".setDisplayTerm").css("display","block");
           $(".network_card_book").css("display", "none");
           $(".manager_search_card").css("display","none");
           $(".ManagerSearch_box").css("display","none");
         }

         // Process with IBS Card List
         if($(".IBSNetworkCardList").hasClass("active")) {              
           $(".business_card_book").css("display", "none");
           $(".peopleSearchResults").css("display", "block");
           $(".network_card_book").css("display", "none");
           $(".setDisplayTerm").css("display","none");
           $(".IBSNetworkCardList_popup").css("display","none");
           $(".manager_search_card").css("display","none");
           $(".ManagerSearch_box").css("display","none");
         }

         // if($(".ManagerSearch").hasClass("active")) {
         //   // $(".business_card_book").css("display", "none");
         //   // $(".peopleSearchResults").css("display", "none");
         //   // $(".setDisplayTerm").css("display","none");
         //   // $(".manager_search_card").css("display","block");
         //   // $(".searchTargetSwitcher").css("display", "none");
         // }
       });
       // Remove tag
       
     });
     // Manager Search 
     $(".ManagerSearch").click(function(event) {        
       if($(".ManagerSearch").hasClass("active")) {
         $(".ManagerSearch_box").css("display","none");
       } else {
         $(".ManagerSearch_box").css("display","block");
       }        
     });

     $('.search_tag_index').change(function(event) {
       $('#rowData_'+id_manager).find(".nametag").html(this.value);
       if(id_manager >=5)
         id_manager = 1;
       else
         id_manager =id_manager + 1;          
       this.value = '';        
       return false;
     });
     $('.ac').click(function(event) {
       var command = $( event.target ).html();        
       if (command == '選択した検索条件を使用する') {
         // Save values and return IBSNetworkCardList
         $(".BusinessCardList").addClass("active");
         $(".searchTargetSwitcher").css("display", "block");
         $(".ManagerSearch").removeClass("active");
         $(".business_card_book").css("display", "block");
         $(".peopleSearchResults").css("display", "none");
         $(".network_card_book").css("display", "none");                
         $(".setDisplayTerm").css("display","block");
         $(".network_card_book").css("display", "none");
         $(".manager_search_card").css("display","none");
         $(".ManagerSearch_box").css("display","none");
       } 
       if (command == '選択した検索条件を削除') {
         // Clear text and disable checkbox     
         $("#mSearch").find("td .i-checks").iCheck('uncheck');
         // $(".ManagerSearch_box").css("display","none");
         // $(".BusinessCardList").addClass("active");
         // $(".ManagerSearch").removeClass("active");
         
       }
     });

     // Search function
     $('#search-card').change(function(event) {
       if($('.ManagerSearch').hasClass('active')) {
         $('#managercard_'+id_manager+' form input').val();
         $('#managercard_'+id_manager+' form input').val(this.value);
         
         if(id_manager >=5)
           id_manager = 1;
         else
           id_manager =id_manager + 1;          
         this.value = '';
       } else {
        //console.log('AA',this.value);  
         this.value = '';
       }
       return false;
     });

     $(document).on('mouseover', '#tags .icheckbox_square-green', function(e) {
    	 $(this).addClass("icheckbox_square-green hover");
   	 });
     
     $(document).on('mouseout', '#tags .icheckbox_square-green', function(e) {
       	$(this).removeClass("icheckbox_square-green hover");
     	$(this).addClass("icheckbox_square-green");
     });
     
     //var isClick = true;
     $(document).on('click', '#tags .icheckbox_square-green', function(e) {
    	 //isClick = !isClick;
		 //console.log("Click 111 : "+$(this).attr("class"));
		 		 
    	 if($(this).attr("class").indexOf("checked") == -1){
    		 $(this).removeClass("icheckbox_square-green hover");
        	 $(this).removeClass("icheckbox_square-green");
        	 $(this).addClass("icheckbox_square-green checked");
        	 
        	 //Add card tag
        	 var json = {"tagId" : this.id, "cardId" : $("input[name=cardId]").val()};
     	     addCardTag(json);
    	 }
    	 else{
    		 $(this).removeClass("icheckbox_square-green checked");
    		 $(this).addClass("icheckbox_square-green");
    		 
    		//Delete card tag
        	 var json = {"tagId" : this.id, "cardId" : $("input[name=cardId]").val()};
     	     deleteCardTag(json);
    	 }
     });
          
     // Add card tag
     $('#addLabel').click(function(event) {
    	var tagName = $("input[name=tagName]").val();
    	var cardId = $("input[name=cardId]").val();
    	var json = {"tagName" : $("input[name=tagName]").val(), "cardId" : $("input[name=cardId]").val()};
	   	 
		if($("#tagName").val() == ""){
			BootstrapDialog.show({
				title: '<fmt:message key="popup.title.info" />',
             	message: '<fmt:message key="enter.tag.name" />'
	        });
		}
		else{
			$.ajax({
	        	url: "<c:url value='/user/addTag' />",
	        	data: JSON.stringify(json),
	        	type: "POST",
	        	
	        	beforeSend: function(xhr) {
	        		xhr.setRequestHeader("Accept", "application/json");
	        		xhr.setRequestHeader("Content-Type", "application/json");
	        	},
	        	success: function(response) {
	        		var respHTML = "";
	        		var isChecked = "";
	        		$.each(response, function(index, value){
	        			//console.log(JSON.stringify(value));
	        			//console.log(JSON.stringify(value["listCardIds"]));
	        			isChecked = "";
	        			$.each(value["listCardIds"], function(idx, v){
	        				//console.log("v : "+ v);
	        				if(v == $("input[name=cardId]").val()){
	        					//console.log("v : "+ v + " tagId : "+tagId);
	        					isChecked = "checked";
	        					return false;
	        				}
	        				
	        				if(v != $("input[name=cardId]").val()){
	        					isChecked = "";
	        				}
	        			});
	        			if(isChecked == "checked")
	        				{respHTML += "<tr id='rowData'>"
		    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
		        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
		        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
		    					+ "</td>"
		    					+ "<td>"+value["tagName"]+"</td>"
		    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
	        			else
	        				{respHTML += "<tr id='rowData'>"
		    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
		        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
		        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
		    					+ "</td>"
		    					+ "<td>"+value["tagName"]+"</td>"
		    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
	        		});
	        		$("#tagName").val('');
	        		$("#tags tbody").html(respHTML);    		
	        	},
	        	error: function(){
				  BootstrapDialog.show({
       				title: '<fmt:message key="popup.title.info" />',
      	             	message: '<fmt:message key="add.tag.failed" />'
       	      		});
			  	}
	        });
			
			//isClick = true;
			//console.log("isClick : "+isClick);
		}
		
     });
     
     // Process with Notification List
     $("#notification").click(function(event) {
       // console.log($(this));
     });

	//Get params URL
	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;
	
	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');
	
	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	
	function addCardTag(json){
     	$.ajax({
        	url: "<c:url value='/user/addCardTag' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
        		//console.log(JSON.stringify(response));
        		var respHTML = "";
        		var isChecked = "";
        		$.each(response, function(index, value){
        			//console.log(JSON.stringify(value));
        			//console.log(JSON.stringify(value["listCardIds"]));
        			isChecked = "";
        			$.each(value["listCardIds"], function(idx, v){
        				//console.log("v : "+ v);
        				if(v == $("input[name=cardId]").val()){
        					//console.log("v : "+ v + " tagId : "+tagId);
        					isChecked = "checked";
        					return false;
        				}
        				
        				if(v != $("input[name=cardId]").val()){
        					isChecked = "";
        				}
        			});
        			if(isChecked == "checked")
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        			else
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        		});
        		
        		$("#tagName").val('');
        		$("#tags tbody").html(respHTML);    		
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="add.card.tag.failed" />'
   	      		});
		  	}
        });	
	}
	
	function deleteCardTag(json){
     	$.ajax({
        	url: "<c:url value='/user/deleteCardTag' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
        		//console.log(JSON.stringify(response));
        		var respHTML = "";
        		var isChecked = "";
        		$.each(response, function(index, value){
        			//console.log(JSON.stringify(value));
        			//console.log(JSON.stringify(value["listCardIds"]));
        			isChecked = "";
        			$.each(value["listCardIds"], function(idx, v){
        				//console.log("v : "+ v);
        				if(v == $("input[name=cardId]").val()){
        					//console.log("v : "+ v + " tagId : "+tagId);
        					isChecked = "checked";
        					return false;
        				}
        				
        				if(v != $("input[name=cardId]").val()){
        					isChecked = "";
        				}
        			});
        			if(isChecked == "checked")
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        			else
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        		});
        		
        		$("#tagName").val('');
        		$("#tags tbody").html(respHTML);    		
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="delete.card.tag.failed" />'
   	      		});
		  	}
        });	
	}
	
	function deleteTag(id){
     	$.ajax({
        	url: "<c:url value='/user/deleteTag' />",
        	data: 'tagId='+ id,
        	type: "GET",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
        		//console.log(JSON.stringify(response));
        		var respHTML = "";
        		var isChecked = "";
        		$.each(response, function(index, value){
        			//console.log(JSON.stringify(value));
        			//console.log(JSON.stringify(value["listCardIds"]));
        			isChecked = "";
        			$.each(value["listCardIds"], function(idx, v){
        				//console.log("v : "+ v);
        				if(v == $("input[name=cardId]").val()){
        					//console.log("v : "+ v + " tagId : "+tagId);
        					isChecked = "checked";
        					return false;
        				}
        				
        				if(v != $("input[name=cardId]").val()){
        					isChecked = "";
        				}
        			});
        			if(isChecked == "checked")
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        			else
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        		});
        		
        		$("#tagName").val('');
        		$("#tags tbody").html(respHTML);    		
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="delete.tag.failed" />'
   	      		});
		  	}
        });	
	}
	
	function addCardMemo(json){
		$.ajax({
        	url: "<c:url value='/user/addCardMemo' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
        		var resp = "";
        		$.each(response, function(index, value){
        			//console.log(value);
        			resp += "<li><p style='font-size:10pt;'>"+ value["memo"] +"</p><p class='p-date-n'>"+formatDate(value["create_date"])+"</p><span class='delMemo' id='"+value["seq"]+"' class='span-close'>x</span></li>"; 
        		});
        		
        		$(".ul-memo").html(resp);
        		$('.ul-memo').show();
                $('.panel-body').addClass('panel-body-memo');
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="add.card.memo.failed" />'
   	      		});
		  	}
        });	
		$("#textarea-memo").val('');
	}
	
	function delCardMemo(json){
		$.ajax({
        	url: "<c:url value='/user/deleteCardMemo' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
	       		var resp = "";
        		$.each(response, function(index, value){
        			resp += "<li><p style='font-size:10pt;'>"+ value["memo"] +"</p><p class='p-date-n'>"+formatDate(value["create_date"])+"</p><span class='delMemo' id='"+value["seq"]+"' class='span-close'>x</span></li>"; 
        		});
        		
        		$(".ul-memo").html(resp);
        		$('.ul-memo').show();
                $('.panel-body').addClass('panel-body-memo'); 
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="delete.card.memo.failed" />'
   	      		});
		  	}
        });	
		$("#textarea-memo").val('');
		
		//Hide scrollbar
        if($('.ul-memo li').length <= 5){
       	 	$(".panel-memo").attr("style", "height:auto; overflow-x:hidden; overflow-y:hidden;");
       	 	$(".panel-heading").attr("style", "width:100%;");
        }
	}
	
	function getTagForCard(){
     	$.ajax({
        	url: "<c:url value='/user/listCardTag' />",
        	type: "GET",
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
        		//console.log(JSON.stringify(response));
        		var respHTML = "";
        		var isChecked = "";
        		$.each(response, function(index, value){
        			//console.log(JSON.stringify(value));
        			//console.log(JSON.stringify(value["listCardIds"]));
        			isChecked = "";
        			$.each(value["listCardIds"], function(idx, v){
        				//console.log("v : "+ v);
        				if(v == $("input[name=cardId]").val()){
        					//console.log("v : "+ v + " tagId : "+tagId);
        					isChecked = "checked";
        					return false;
        				}
        				
        				if(v != $("input[name=cardId]").val()){
        					isChecked = "";
        				}
        			});
        			if(isChecked == "checked")
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        			else
        				{respHTML += "<tr id='rowData'>"
	    					+ "<td><div style='position: relative;' class='icheckbox_square-green "+isChecked+"' id='"+value["tagId"]+"'>"
	        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
	        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
	    					+ "</td>"
	    					+ "<td>"+value["tagName"]+"</td>"
	    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";}
        		});
        		
        		$("#tags tbody").html(respHTML);
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="list.card.tag.failed" />'
   	      		});
		  	}
        });	
	}
	
	function formatDate(date) {
	    var d = new Date(date),
	        month = '' + (d.getMonth() + 1),
	        day = '' + d.getDate(),
	        year = d.getFullYear();

	    if (month.length < 2) month = '0' + month;
	    if (day.length < 2) day = '0' + day;

	    return year + "年" + month + "月" + day + "日";
	}
		
	function saveContactHistory(){
		var dateTime = new Date($("#saveContactFrm input[name=contactDate]").datepicker("getDate"));
  		var strDateTime =  dateTime.getFullYear() + "-" + (dateTime.getMonth()+1) + "-" + dateTime.getDate();
  		
		var json = {"cardInfo" : { "cardId" : $("input[name=cardId]").val() }, "contactDate" : strDateTime, "contactMemo" : $("#contactMemo").val()};
		$.ajax({
        	url: "<c:url value='/user/addContactHistory' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
        		window.location.reload(true);
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="add.card.memo.failed" />'
   	      		});
		  	}
        });	
	}
	
	function delContactHistory(json){
		//console.log(this.id);
		//var json = {"cardId" : $("input[name=cardId]").val(), "userId" : this.id};
		$.ajax({
        	url: "<c:url value='/user/deleteContactHistory' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
	       		console.log(response);
	       		var responseHTML = "";
	       		$.each(response, function(index, value){
		       		var contactDate = formatDate(value["contactDate"]);
	       			responseHTML += "<div class='career_section selected' style='border-bottom:0px;'><div class='career_date ' style='font-weight: bold !important;'>"
	            				+ contactDate 
	            				+ "<span class='delContactHist' id='"+ value["contactHistoryId"] +"'>x</span></div>"
	            				+ "<div><table class='table'><tbody>"
	                        	+ "<tr id='rowData'><td>"
	                            + "    <p>"+ value["contactMemo"] +"</p></td></tr></tbody></table></div></div>";
	       		});
	       		$("#contact-hist-body").html(responseHTML);
        	},
        	error: function(){
			  BootstrapDialog.show({
   				title: '<fmt:message key="popup.title.info" />',
  	             	message: '<fmt:message key="delete.contact.hist.failed" />'
   	      		});
		  	}
        });	 
		//Hide scrollbar
       	$("#contact-hist-body").attr("style", "height:auto; overflow-x:hidden; overflow-y:hidden;")
	}
	
	function loginSaleForce(){
		$("#loading-copy").show();
		
		var lastname = $("input[name=lastName]").val();
		var firstname = $("input[name=firstName]").val();
		var positionName = $("input[name=positionName]").val();
		var companyName = $("input[name=companyName]").val();
		var address1 = $("input[name=address1]").val();
		var address2 = $("input[name=address2]").val();
		var address3 = $("input[name=address3]").val();
		var address4 = $("input[name=address4]").val();
		var telNumberCompany = $("input[name=telNumberCompany]").val();
		var mobileNumber = $("input[name=mobileNumber]").val();
		var faxNumber = $("input[name=faxNumber]").val();
		var email = $("input[name=email]").val();
		var companyUrl = $("input[name=companyUrl]").val();
		var departmentName = $("input[name=departmentName]").val();
		var zipCode = $("input[name=zipCode]").val();
		var login_id = $("input[name=login_id]").val();
		var login_pass = $("input[name=login_pass]").val();
		
		var json = {"lastname": lastname, "firstname" : firstname, "positionName" : positionName, "companyName" : companyName, 
					"address1" : address1, "address2" : address2, "address3" : address3, "address4" : address4, 
					"telNumberCompany" : telNumberCompany, "mobileNumber" : mobileNumber, "faxNumber" : faxNumber, "email" : email, 
					"companyUrl" : companyUrl, "departmentName" : departmentName, "zipCode" : zipCode, "login_id" : login_id, "login_pass" : login_pass };
		$.ajax({
        	url: "<c:url value='/user/loginSaleForce' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(response) {
	       		console.log(response);
        		$("#loading-copy").hide();
        		
	       		if(response.toString().indexOf("INVALID_LOGIN") > 0 || response.faultcode == "sf:INVALID_LOGIN" || response.faultcode == "INVALID_LOGIN"){
	       			$("#errors").html("<label class='error' style='margin-left:0px; font-size:13pt;'><fmt:message key='sf.login.failed' /></label>");
	       		}
	       		else{
	       			var isChecked = false;
	       			if(response[0].success == true){
	       				isChecked = true;
	       				$('#modal-login-saleforce').find('.close').click();
	       				BootstrapDialog.show({
            				title: '<fmt:message key="popup.title.info" />',
           	             	message: '<fmt:message key="sf.login.success" />'
            	      	});
	       				
	       				if(isChecked)
	       					return false;
	       			}
	       			
	       			if(response[0].errors[0].statusCode == "INVALID_EMAIL_ADDRESS"){
	       				$("#errors").html("<label class='error' style='margin-left:0px; font-size:13pt;'>"+ response[0].errors[0].message +"</label>")
	       			}
	       		}
        	},
        	error: function(xhr, status, error){
        		$("#loading-copy").hide();
        		console.log("Error :"+ xhr.responseText);
		  	}
        });	
	}
	
	function validLoginSaleForce(){
		var sfEmail = $("#sfEmail").val();
		var sfPassword = $("#sfPassword").val();
		
		if(sfEmail == ""){
			$("#errors").html("<label class='error' style='margin-left:0px; font-size:13pt;'><fmt:message key='sf.loginId.blank' /></label>")
			return false;
		}
		else if(sfPassword == ""){
			$("#errors").html("<label class='error' style='margin-left:0px; font-size:13pt;'><fmt:message key='sf.password.blank' /></label>")
			return false;
		}
		else{
			$("#errors").html('');
			return true;
		}
	}

	function saveEditCardInfo(){
		var cardId = $("input[name=cardId]").val();
		var imageFile = $("#editForm input[name=imageFile]").val();
		var fileOutputFlg = $("input[name=fileOutputFlg]").val();
		var cardOwnerId = $("input[name=cardOwnerId]").val();
		var publishStatus = $("input[name=publishStatus]").val();
		var approvalStatus = $("input[name=approvalStatus]").val();		
		/////////////////
		var d = $("#editForm input[name=contactDate]").val();
		var dateTime = new Date((Number(d.split("-")[0])), (Number(d.split("-")[1]) - 1), (Number(d.split("-")[2])));
  		//var strDateTime =  dateTime.getFullYear() + "-" + (dateTime.getMonth()+1) + "-" + dateTime.getDate();
		/////////////////
		var address1 = $("input[name=address1]").val();
		var address2 = $("input[name=address2]").val();
		var address3 = $("input[name=address3]").val();
		var address4 = $("input[name=address4]").val();

		var lastName = $("input[name=lastName]").val();
		var firstName = $("input[name=firstName]").val();
		
		var companyName = $("input[name=companyName]").val();
		var companyNameKana = $("input[name=companyNameKana]").val();
		var departmentName = $("input[name=departmentName]").val();		
		var positionName = $("input[name=positionName]").val();
		var lastNameKana = $("input[name=lastNameKana]").val();
		var firstNameKana = $("input[name=firstNameKana]").val();
		var email = $("input[name=email]").val();
		var telNumberCompany = $("input[name=telNumberCompany]").val();
		var telNumberDepartment = $("input[name=telNumberDepartment]").val();		
		var mobileNumber = $("input[name=mobileNumber]").val();		
		var faxNumber = $("input[name=faxNumber]").val();
		var zipCode = $("input[name=zipCode]").val();
		var companyUrl = $("input[name=companyUrl]").val();
		
		var json = {"cardId": cardId, "imageFile" : imageFile, "fileOutputFlg" : fileOutputFlg, "cardOwnerId" : cardOwnerId, 
					"approvalStatus" : approvalStatus, "publishStatus" : publishStatus, "contactDate" : dateTime, "address1" : address1,
					"address2" : address2, "address3" : address3, "address4" : address4, "companyName" : companyName,
					"companyNameKana" : companyNameKana, "departmentName" : departmentName, "positionName" : positionName, "lastNameKana" : lastNameKana,
					"firstNameKana" : firstNameKana, "email" : email, "telNumberCompany" : telNumberCompany, "telNumberDepartment" : telNumberDepartment,
					"mobileNumber" : mobileNumber, "faxNumber" : faxNumber, "zipCode" : zipCode, "companyUrl" : companyUrl, "lastName":lastName, "firstName":firstName
					 };
		$.ajax({
        	url: "<c:url value='/user/editCardInfo' />",
        	data: JSON.stringify(json),
        	type: "POST",
        	dataType: 'json',
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(resp) {
				if(resp = true){
					BootstrapDialog.show({
	    				title: '<fmt:message key="popup.title.info" />',
	    				message: '<fmt:message key="popup.edit.card.success" />'
	    	      	});
	        		window.location.reload(true);
				} else {
					BootstrapDialog.show({
	    				title: '<fmt:message key="popup.title.info" />',
	    				message: '<fmt:message key="edit.card.failed" />'
	    	      	});
	        		window.location.reload(true);
				}
        	},
        	error: function(error){
        		BootstrapDialog.show({
    				title: '<fmt:message key="popup.title.info" />',
    				message: '<fmt:message key="edit.card.failed" />'
    	      	});
		  	}
        });	
	}
	
	function gotoMap(){
		var address = 'http://maps.google.com/maps?q=<c:out value="${ cardInfo.address1 }"></c:out> <c:out value="${ cardInfo.address2 }"></c:out> <c:out value="${ cardInfo.address3 }"></c:out> <c:out value="${ cardInfo.address4 }"></c:out>';
		var uri = encodeURI(address);
		window.location.href = uri;
	}
   </script>
   