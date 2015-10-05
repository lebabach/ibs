<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.lang.Integer" %>
 <%@ page import="java.util.List" %>
 <%@ page import="com.ecard.webapp.constant.CommonConstants" %>
<script>
	function isValidEmailAddress(emailAddress) {
	    var pattern = new RegExp(/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i);
	    return pattern.test(emailAddress);
	};
	function checkValidationForm(){
		var checkValidation=true;
		if($("#firstName").val()==""){
			$(".sp-firstName").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}	
		if($("#lastName").val()==""){
			$(".sp-lastName").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#firstNameKana").val()==""){
			$(".sp-firstNameKana").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#lastNameKana").val()==""){
			$(".sp-lastNameKana").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#companyId").val()=="0"){
			$(".sp-companyName").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#departmentId").val()=="0"){
			$(".sp-departmentName").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		/* if($("#positionName").val()==""){
			$(".sp-positionName").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		} */
		if($("#zipCode").val()==""){
			$(".sp-zipCode").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#address1").val()==""){
			$(".sp-address1").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#address2").val()==""){
			$(".sp-address2").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#address3").val()==""){
			$(".sp-address3").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#telNumberCompany").val()==""){
			$(".sp-telNumberCompany").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#telNumberDepartment").val()==""){
			$(".sp-telNumberDepartment").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#telNumberDirect").val()==""){
			$(".sp-telNumberDirect").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#roleId").val()==""){
			$(".error-role").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if(!isValidEmailAddress($("#email").val())){
			$(".sp-email-format").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		/*
		if($("#password").val()==""){
			$(".sp-password").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}*/
		return checkValidation;
	}
	function resetValidationForm(){
		$(".sp-firstName").text("");
		$(".sp-lastName").text("");
		$(".sp-firstNameKana").text("");
		$(".sp-lastNameKana").text("");
		$(".sp-companyName").text("");
		$(".sp-departmentName").text("");
		$(".sp-positionName").text("");
		$(".sp-zipCode").text("");
		$(".sp-address1").text("");
		$(".sp-address2").text("");
		$(".sp-address3").text("");
		$(".sp-telNumberCompany").text("");
		$(".sp-telNumberDepartment").text("");
		$(".sp-telNumberDirect").text("");
		$(".sp-email").text("");
		//$(".sp-password").text("");
		$(".error-role").text(" ");
		$(".sp-email-format").text("");
	}
	
	function reloadDepartment(department){
		var firstText=$("#departmentId option").first().text();
		$('#departmentId').find('option').remove().end().append('<option value="0">'+firstText+'</option>').val('0');
		
		$.each( department, function( index, value ){
			 $('#departmentId').append($('<option/>', { 
			        value: value.groupCompanyId,
			        text : value.groupCompanyDepartmentName 
			    }));
		});
	}
	
	function setGroupCompany(){
		if($("#companyId").val()!="0"){
			$("#groupCompanyId").val($("#companyId").val());
			$("#companyName").val($("#companyId").find(":selected").text());
		}
	}
	
	function setGroupCompanyDepartment(){
		if($("#departmentId").val()!="0"){
			$("#departmentName").val($("#departmentId").find(":selected").text());
		}
	}
	
	$(function() {
		/*submit save profile*/
		$('#btnSaveUserProfile').on('click', function() {
			resetValidationForm();
			if(!checkValidationForm()){
				return false;
			}
			$("input[name=useDate]").val($('#datepicker').datepicker('getDate'));
	    	$("input[name=useDate]").value = $('#datepicker').datepicker('getDate'); 
	    	$("input[name=endDate]").val($('#datepickerEndDate').datepicker('getDate'));
	    	$("input[name=endDate]").value = $('#datepickerEndDate').datepicker('getDate');
	    	
			$('#editUserProfileForm').submit();
		});
		
		$('#companyId').on('change', function() {
			var companyId=$("#companyId").val();
			$.ajax({
				type: 'POST',
				url: '/ecard-webapp/operators/getDepartment',
				data: 'departmentId='+ $("#companyId").val(),
				success: function(response){
					reloadDepartment(response);
					setGroupCompany();
			    },
			})
		});
		
        $('#departmentId').on('change', function() {
            if($("#departmentId").val()!=0){  
                setGroupCompanyDepartment();
            }
            
        });
        
        $("#companyId").val($("#groupCompanyId").val());
        if($("#companyId").val()!="0"){
        	$("#companyName").val($("#companyId").find(":selected").text());	
        }
        
        $("#departmentId option").filter(function() {
            return this.text == $("#departmentName").val(); 
        }).attr('selected', true);

		
		
        /*submit save delete profile*/
	       
		if("${operatoredit.user.allDataDlFlg}" == 1){
			$('#editUserProfileForm input[name="allDataDlFlg"]').prop("checked", true);
			$('#editUserProfileForm input[name="allDataDlFlg"]').closest('div').addClass("checked");
		}	
		if("${operatoredit.user.groupDataDlFlg}" == 1) {
			$('#editUserProfileForm input[name="groupDataDlFlg"]').prop("checked", true);
			$('#editUserProfileForm input[name="groupDataDlFlg"]').closest('div').addClass("checked");
		}
		if("${operatoredit.user.helpdeskFlg}" == 1) {
			$('#editUserProfileForm input[name="helpdeskFlg"]').prop("checked", true);
			$('#editUserProfileForm input[name="helpdeskFlg"]').closest('div').addClass("checked");
		}
		if("${operatoredit.user.sfManualLinkFlg}" == 1) {
			$('#editUserProfileForm input[name="sfManualLinkFlg"]').prop("checked", true);
			$('#editUserProfileForm input[name="sfManualLinkFlg"]').closest('div').addClass("checked");
		}
		
		if("${operatoredit.user.groupDataDlRequestFlg}" == 1) {
			$('#editUserProfileForm input[name="groupDataDlRequestFlg"]').prop("checked", true);
			$('#editUserProfileForm input[name="groupDataDlRequestFlg"]').closest('div').addClass("checked");
		}
		if("${operatoredit.user.allDataDlRequestFlg}" == 1) {
			$('#editUserProfileForm input[name="allDataDlRequestFlg"]').prop("checked", true);
			$('#editUserProfileForm input[name="allDataDlRequestFlg"]').closest('div').addClass("checked");
		}
		if("${operatoredit.user.useStopFlg}" == 1) {
			$('#editUserProfileForm input[name="useStopFlg"]').prop("checked", true);
			$('#editUserProfileForm input[name="useStopFlg"]').closest('div').addClass("checked");
		}
		 $('.role-service option').each(function() {
			if($(this).val() == "${operatoredit.user.roles.roleId}"){
				$(this).attr('selected','selected');
			} 
		    
		});
		 $('.role-admin option').each(function() {
				if($(this).val() == "${operatoredit.user.roleAdminId}"){
					$(this).attr('selected','selected');
				} 
			    
		});
		$('#roleId').on('change', function() {
			var role = $(this).val();
			$('input[name=roleId]').val(role);
			console.log(role);			
		});
		$('#roleAdminId').on('change', function() {
			var role = $(this).val();
			$('input[name=roleAdminId]').val(role);
			console.log(role);			
		});
		$('.btn_cancle').on('click', function() {
			document.location.href='/ecard-webapp/operators/list'
		});
		
		 $('.btn_delete').on('click', function() {
			 if (confirm('<fmt:message key="operator.list.confirmDelete"/>')) {
				  var userId = "${operatoredit.user.userId}";
					$.ajax({
						type: 'POST',
						url: '/ecard-webapp/operators/delete',
						data: 'userId='+ userId
					}).done(function(resp, status, xhr) {
						if (resp === 0)
							document.location.href='/ecard-webapp/operators/list';
						else
							alert('Error 1');
					}).fail(function(xhr, status, err) {
						alert('Error');
					});
			 }
	    });
		 
		 $('.btn_leave').on('click', function() {
			 if (confirm('<fmt:message key="operator.list.confirmDelete"/>')) {
			  var userId = "${operatoredit.user.userId}";
				$.ajax({
					type: 'POST',
					url: '/ecard-webapp/operators/userLeave',
					data: 'userId='+ userId
				}).done(function(resp, status, xhr) {
					if (resp === 0)
						document.location.href='/ecard-webapp/operators/list';
					else
						alert('Error 1');
				}).fail(function(xhr, status, err) {
					alert('Error');
				});
			 }
	    });
		 
		 $(".btn_address" ).click(function() {
				var zipcodeString = $('#zipCode').val();
				if (zipcodeString.indexOf('-') > 1) {
					$(".error_zipcode").text("<fmt:message key='validate.zipcode'/>");
					$(".mesage_error").css("display","block");
					return false;
				}
				console.log('asdfasdf= '+ typeof zipcode);
				if(zipcodeString != ''){
					$.ajax({
						type: 'POST',
						url: '/ecard-webapp/cards/zipCodeSearch',
						data: 'zipCode='+zipcodeString,
					}).done(function(resp, status, xhr) {
						if(resp != ''){
							$(".sp-zipCode").text("");
							if(resp.length > 0){
								for (i = 0; i < resp.length; ++i) {
								    $('#address'+(i+1)).val(resp[i]);
								}
							}
						}else{
							$(".sp-zipCode").text("<fmt:message key='validate.zipcode'/>");
							
						}
					}).fail(function(xhr, status, err) {
						$(".sp-zipCode").text("<fmt:message key='validate.zipcode'/>");
						
					});
				}
			});
		 $(".btn_resetPassword").click(function() {
			var answer=confirm("<fmt:message key='operator.reset.password'/>");
			if (answer==true){
				var userId = "${operatoredit.user.userId}";
				var email = "${operatoredit.user.email}";
				console.log(userId+" = "+email);
				$.ajax({
					type: 'POST',
					url: "<c:url value='/checkemail'/>",
					data: 'email='+email,
				}).done(function(resp, status, xhr) {
					if (resp != ''){
						var sendding = "IBS";
						 $.ajax({
							type: 'POST',
							url: "<c:url value='/sendMailWebapp'/>?recipientName="+sendding+"&recipientEmail="+email+"&password="+resp.status,
							cache: false,
						}).done(function(resp, status, xhr) {
							 if (resp == 'Success'){
								 alert("パスワード再設定の通知を登録されたメールアドレスへ送信しました");
							}else{
								//alert("Error send mail");
							} 
						}).fail(function(xhr, status, err) {
							 alert('Have a problem when send mail');
						}); 
						
					}else{
						alert('登録されていないメールアドレスです');
					}
				}).fail(function(xhr, status, err) {
					alert(resp);
				});
			} else {
			    return false;
			}
			
		 });
		 
	});
	
	$(document).ready(function(){
		
		$("input[name=useStop]").on('ifChecked', function(event){			
			$("input[name=useStopFlg]").val(1);
        });
        $("input[name=useStop]").on('ifUnchecked', function(event){        	
        	$("input[name=useStopFlg]").val(0);        
        });
        
       	$('#datepicker').datepicker({				
		        language: 'ja',
		        format: 'yyyy年MMdd日',
		        forceParse: true,
		        autoclose: true
			}).on('changeDate', function (ev) {
		    	$("input[name=useDate]").val(ev.date);
		});
		$('#datepickerEndDate').datepicker({
	        language: 'ja',
	        format: 'yyyy年MMdd日',
	        forceParse: true,
	        autoclose: true
		}).on('changeDate', function (ev) {
	    	$("input[name=endDate]").val(ev.date);
		});
		
	});
	
</script>
<style>
.ch-color-link .ch-address{
background-color: blue;
}
</style>
<!-- CENTER SIDE -->
<div id="center-side" class="col-sm-12">
	<!-- BAR TOP -->
	<div class="row bg-white box-shadow menu-top-header">
		<div class="col-sm-12" id="ct1">
			<div class="float-left ">
				<h4 class="h4-header">オペレーター編集</h4>
			</div>
             <div class="col-sm-4" style = "float: right;margin: 0 auto;">
                   <h4 class="h4-header">
                   		
                       <span>
                       	<c:if test="${operatoredit.user.useStopFlg==0}">
             				<input type="checkbox" class="i-checks" name="useStop" id="useStopFlg" value="0"/>利用停止
						</c:if>
						
						<c:if test="${operatoredit.user.useStopFlg!=0}">
							<input type="checkbox" checked class="i-checks" name="useStop" id="useStopFlg" value="1"/>利用停止	
						</c:if>
                       
                       </span>                       
                       <span><button type="button" class="btn btn-primary btn_delete"
									data-dismiss="modal">削除</button></span>
                       <span><button type="button" class="btn btn-primary btn_leave"
									data-dismiss="modal">退職</button></span>
                       <span><button type="button" class="btn btn-primary btn_cancle"
									data-dismiss="modal">キャンセル</button></span>
                       <span><button type="button" class="btn btn-primary"
									data-dismiss="modal" id="btnSaveUserProfile">保存</button></span>
															
                    </h4>
             </div>
			<div class="float-right float-right-button"></div>
		</div>

	</div>

	<!-- END BAR TOP -->
	<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
		<form class="form-horizontal" id="editUserProfileForm" action="/ecard-webapp/operators/saveEdit", method="POST" accept-charset="UFT-8">
			<input type="hidden" name = "userId" value="${operatoredit.user.userId}">
			<input type="hidden" name = "useStopFlg" id="useStopFlg" value="${operatoredit.user.useStopFlg}">
			<div class="col-sm-12">
				<div class="col-sm-6">
					<!-- BEGIN FORM -->
					<fieldset>
						<div class="form-group">
							<label for="title-of-honour" class="control-label col-xs-2"><fmt:message key="operator.email"/><span style="color:red">*</span></label>
							<div class="col-md-5">
								<input name="email" type="text" class="form-control" id="email"  size="40" value="${operatoredit.user.email}">
									<span class='sp-email-format' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname" class="control-label col-xs-2"><fmt:message key="operator.password"/><span style="color:red">*</span></label>
							<div class="col-md-8 ch-color-link">
								<div class=" inline block-inline" style ="position: relative;margin: 0 auto; width: 100%;">
								<!-- <input name = "resetPassword" type="password" class="form-control" id="resetPassword"
									maxlength="40" size="40" style="width:240px"> --> 
									<!-- <span class='sp-password' style="color:red"></span> -->
									<a class="ch-address btn_resetPassword">パスワードを再送信する</a>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="form-group">
							<label for="groupComposition" class="control-label col-xs-2"><fmt:message key="operator.role.service"/><span class="error-color">*</span></label>
							<div class="col-xs-4">
								 <form>
				                      <c:if test="${pageContext.request.isUserInRole('ROLE_AUTHORITY_USER') }">
	 							       <% if(Integer.parseInt(session.getAttribute("gourpCompanyId").toString()) != CommonConstants.CO_LTD_FRONTIER_CHALLENGE){ %>
						                        <select id = "roleId" class="form-control role-service" disabled >
						                        <option value = '1'> 未選択  </option>
						                         <option value = '6'>スーパーバイザー</option>
						                         <option value = '5'>承認者（拠点リーダー）</option>
						                         <option value = '4'>オペレーター</option> 
						                        </select>
					                        <%}else{ %>
		 				                       <select id = "roleId" class="form-control role-service" >
		 				                         <option value = "1">未選択 </option>
		 				                         <option value = '6'>スーパーバイザー</option>
						                         <option value = '5'>承認者（拠点リーダー）</option>
		 			                             <option value = '4'>オペレーター</option>
		 			                           </select>
	 				                    <%} %>
	 			                   </c:if>
	 			                   <c:if test="${ not pageContext.request.isUserInRole('ROLE_AUTHORITY_USER')  }"> 
	 				                        <select id = "roleId" class="form-control role-service" >
	 				                        <option value = '1'> 未選択  </option>
	 				                         <option value = '6'>スーパーバイザー</option>
	 				                         <option value = '5'>承認者（拠点リーダー）</option>
	 				                         <option value = '4'>オペレーター</option> 
	 				                        </select>
	 			                   </c:if>
				                </form>
								<span class='sp-roleId' style="color:red"></span>
								<input type="hidden" name="roleId" class = "roleId" id = "roleId" value="${operatoredit.user.roles.roleId}" /> 
							</div>
					</div>
					<div class="form-group">
							<label for="groupComposition" class="control-label col-xs-2"><fmt:message key="operator.role.admin"/><span class="error-color">*</span></label>
							<div class="col-xs-4">
								 <form>
				                        <select id ="roleAdminId" class="form-control role-admin" >
				                        <option value = '1'> 未選択  </option>
				                          <option value = '7'>推進管理者</option> 
					                      <option value = '3'>保守担当者</option>
					                     <option value = '2'>社内管理者</option>
					                   
				                        </select>
				                </form>
								<span class='sp-roleId' style="color:red"></span>
								<input type="hidden" name="roleAdminId"  value="${operatoredit.user.roleAdminId}" /> 
							</div>
					</div>
					<div class="row form-group ">
						<div class="col-sm-10 col-xs-offset-0 ">
							<ul class="list-li">
								<%-- <div class="col-sm-4">
									<li><input icheck type="checkbox" class="i-checks"
										name="groupDataDlFlg" id="all_card" value="1"/> <a href="#"><fmt:message key="operator.groupDataDlFlg"/></a></li>
									<li><input icheck type="checkbox" class="i-checks"
										name="allDataDlFlg" id="ungraded" value="1"/> <a href="#"><fmt:message key="operator.allDataDlFlg"/></a></li>
								</div> --%>
								<div class="col-sm-4 col-xs-offset-2">
									<%-- <li><input icheck type="checkbox" class="i-checks"
										name="helpdeskFlg" id="not_update" value="1"/> <a href="#"><fmt:message key="operator.helpdeskFlg"/></a></li> --%>
									<li><input icheck type="checkbox" class="i-checks"
										name="sfManualLinkFlg" id="not_update" value="1"/> <a href="#"><fmt:message key="operator.sfManualLinkFlg"/></a></li>
								</div>
							</ul>
							<%-- <ul class="list-li">
								<div class="col-sm-4" style="width:274px">
									<li><input icheck type="checkbox" class="i-checks"
										name="groupDataDlRequestFlg" id="groupDataDlRequestFlgId" value="1"/> <a href="#"><fmt:message key="operator.group_data_dl_request_flg"/></a></li>
									<li><input icheck type="checkbox" class="i-checks"
										name="allDataDlRequestFlg" id="allDataDlRequestFlgId" value="1"/> <a href="#"><fmt:message key="operator.all_data_dl_request_flg"/></a></li>
								</div>
							</ul> --%>
						</div>
					</div>
					<%-- <div class="row form-group">
                         <label class="control-label col-sm-3 col-xs-offset-0" for="name">利用開始日</label>
                         <div class="col-sm-7">
                         <div class="input-group date">
                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span><input type="text" name="useDateShow" value='<fmt:formatDate value="${operatoredit.user.useDate}" pattern="yyyy年MM月dd日" />'>
                           </div>
                         </div>
                     </div> --%>
                     <div class="row form-group">
                         <label class="control-label col-sm-2 col-xs-offset-0" for="name">利用開始日</label>
                         <div class="col-sm-5">
                         	<div class="input-group date">
                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                               <input type="text" id="datepicker" class="form-control" name="useDateShow" value='<fmt:formatDate value="${operatoredit.user.useDate}" pattern="yyyy年MM月dd日" />'>
                               <input type="hidden" id="useDate" name="useDate" value='<fmt:formatDate value="${operatoredit.user.useDate}" pattern="yyyy年MM月dd日"/>'/>
                           </div>
                         </div>
                     </div>
                     <div class="row form-group">
                         <label class="control-label col-sm-2 col-xs-offset-0" for="name">利用終了日</label>
                         <div class="col-sm-5">
                         	<div class="input-group date">
                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                               <input type="text" id="datepickerEndDate" class="form-control" name="endDateShow" value='<fmt:formatDate value="${operatoredit.user.endDate}" pattern="yyyy年MM月dd日" />'>
                               <input type="hidden" name="endDate" value='<fmt:formatDate value="${operatoredit.user.endDate}" pattern="yyyy年MM月dd日"/>'/>
                           </div>
                         </div>
                     </div>
				</div>

				<div class="col-sm-6">
					<fieldset>
					<!-- bach.le https://livepass.backlog.jp/view/MEISHI-377 -->
					
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.companyname"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<form:select name="groupCompanySelect" class="form-control" path="operatoredit" id="companyId">
						    		<form:options items="${operatoredit.company}" />
								</form:select>
									<span class='sp-companyName' style="color:red"></span>
                                    <input type="hidden" name = "groupCompanyId"  id ="groupCompanyId" value="${operatoredit.user.groupCompanyId}">
									<input type="hidden" name = "companyName" id = "companyName" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.departmentName"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<%-- <form:select name="groupComposition.compositionId" class="form-control" path="operatoredit" id="departmentId">
						    		<form:options items="${operatoredit.department}" />
								</form:select> --%>
								<input name="departmentName" type="text" class="form-control" id="departmentName"
									 size="40" value="${operatoredit.user.departmentName}">
									<span class='sp-positionName' style="color:red"></span>
									
									<%-- <span class='sp-departmentName' style="color:red"></span>
                                    <input type="hidden" name = "departmentName" id = "departmentName" value="${operatoredit.user.departmentName}"> --%>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.positionName"/></label>
							<div class="col-xs-6">
								<input name="positionName" type="text" class="form-control" id="positionName"
									 size="40" value="${operatoredit.user.positionName}">
									<span class='sp-positionName' style="color:red"></span>
									
							</div>
						</div>
						<div class="form-group"><hr align="left" style="margin-left: 16px; width: 737px;"/></div>
						<div class="form-group">
							<label for="title-of-honour" class="control-label col-xs-4"><fmt:message key="operator.lastname"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="lastName" type="text" class="form-control" id="lastName"
									size="40" value="${operatoredit.user.lastName}">
									<span class='sp-lastName' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="lastName" class="control-label col-xs-4"><fmt:message key="operator.firstname"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="firstName" type="text" class="form-control" id="firstName"
									 size="40" value="${operatoredit.user.firstName}">
									<span class='sp-firstName' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname-furigana" class="control-label col-xs-4"><fmt:message key="operator.lastnamekana"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="lastNameKana" type="text" class="form-control" id="lastNameKana"
									 size="40" value="${operatoredit.user.lastNameKana}">
									<span class='sp-firstNameKana' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.firstnamekana"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="firstNameKana" type="text" class="form-control" id="firstNameKana"
									 size="40" value="${operatoredit.user.firstNameKana}">
									<span class='sp-lastNameKana' style="color:red"></span>
							</div>
						</div>
						<div class="form-group"><hr align="left" style="margin-left: 16px; width: 737px;"/></div>
						<%-- <div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.companyUrl"/>
							</label>
							<div class="col-xs-6">
								<input name="companyUrl" type="text" class="form-control" id="companyUrl"
									maxlength="40" size="40" value="${operatoredit.user.companyUrl}">
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.zipcode"/><span style="color:red">*</span></label>
							<div class="col-md-6 ch-color-link" >
							   <div class=" inline block-inline" style ="position: relative;margin: 0 auto; width: 100%;">
								<input name="zipCode" type="text" class="form-control" id="zipCode" style="width:200px"
									maxlength="40" size="40" value="${operatoredit.user.zipCode}">
									 <a style = "position: absolute;right: 0px;top: 0px;width: 50px;height: 32px;" style="width: 120px;" class="ch-address btn_address" >編集</a>
								  </div>
								  
									<span class='sp-zipCode' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.address1"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="address1" type="text" class="form-control" id="address1"
									maxlength="40" size="40" value="${operatoredit.user.address1}">
									<span class='sp-address1' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.address2"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="address2" type="text" class="form-control" id="address2"
									maxlength="40" size="40" value="${operatoredit.user.address2}">
									<span class='sp-address2' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.address3"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="address3" type="text" class="form-control" id="address3"
									maxlength="40" size="40" value="${operatoredit.user.address3}">
									<span class='sp-address3' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.telnumbercompany"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="telNumberCompany" type="text" class="form-control" id="telNumberCompany"
									maxlength="40" size="40" value="${operatoredit.user.telNumberCompany}">
									<span class='sp-telNumberCompany' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.telnumberdepartment"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="telNumberDepartment" type="text" class="form-control" id="telNumberDepartment"
									maxlength="40" size="40" value="${operatoredit.user.telNumberDepartment}">
									<span class='sp-telNumberDepartment' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.telnumberdirect"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<input name="telNumberDirect" type="text" class="form-control" id="telNumberDirect"
									maxlength="40" size="40" value="${operatoredit.user.telNumberDirect}">
									<span class='sp-telNumberDirect' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.fax"/>
							</label>
							<div class="col-xs-6">
								<input name="faxNumber" type="text" class="form-control" id="faxNumber"
									maxlength="40" size="40" value="${operatoredit.user.faxNumber}">
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.mobilenumber"/>
							</label>
							<div class="col-xs-6">
								<input name="mobileNumber" type="text" class="form-control" id="mobileNumber"
									maxlength="40" size="40" value="${operatoredit.user.mobileNumber}">
							</div>
						</div> --%>
						
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.memo1"/>
							</label>
							<div class="col-xs-6">
								<input name="memo1" type="text" class="form-control" id="memo1"
									size="40" value="${operatoredit.user.memo1}">
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.memo2"/>
							</label>
							<div class="col-xs-6">
								<input name="memo2" type="text" class="form-control" id="memo2"
								 size="40" value="${operatoredit.user.memo2}">
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</form>
	</div>
</div>
<!-- BAR BODY -->

</div>
<!-- END CENTER SIDE  -->
<!-- END RIGHT SIDE -->
