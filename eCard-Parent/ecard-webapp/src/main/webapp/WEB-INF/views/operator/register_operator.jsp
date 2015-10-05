<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.lang.Integer" %>
 <%@ page import="java.util.List" %>
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
		if(!isValidEmailAddress($("#email").val())){
			$(".sp-email-format").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		
		if($("#compositionId").val()=="1"){
			$(".sp-compositionId").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($("#roleId").val()==""){
			$(".sp-roleId").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($('.useDate').val()==""){
			$(".sp-useDate").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
		if($('.endDate').val()==""){
			$(".sp-endDate").text("<fmt:message key='validate.null'/>");
			checkValidation=false;
		}
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
		$(".sp-email-format").text("");
		$(".sp-compositionId").text("");
		$(".sp-roleId").text("");
		$(".sp-endDate").text("");
		$(".sp-useDate").text("");
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
		$('#btnSaveUserProfile').on('click', function() {
			var email = $("#email").val();
			resetValidationForm();
			if(!checkValidationForm()){				
				return false;
			}
			$.ajax({
				type : 'POST',
				url : 'checkexistemail',
				data : 'email=' + email
			}).done(function(resp, status, xhr) {
				if (resp == 0) {
					$('#registerUserProfileForm').submit();
				} else {
					$(".sp-email-format").text("<fmt:message key='operator.check.email.exist'/>");
				}
			}).fail(function(xhr, status, err) {
				checkExist = false;
			});

		});

		$('#companyId').on('change', function() {
			var companyId = $("#companyId").val();
			$.ajax({
				type : 'POST',
				url : '/ecard-webapp/operators/getDepartment',
				data : 'departmentId=' + $("#companyId").val(),
				success : function(response) {
					reloadDepartment(response);
					setGroupCompany();
				},
			});

		});
		$('#departmentId').on('change', function() {
			setGroupCompanyDepartment();
		});

		$('.btn_cancle').on('click', function() {
			document.location.href = '/ecard-webapp/operators/list'
		});

		$(".btn_address")
				.click(
						function() {
							var zipcodeString = $('#zipCode').val();
							if (zipcodeString.indexOf('-') > 1) {
								$(".sp-zipCode")
										.text(
												"<fmt:message key='validate.zipcode'/>");
								return false;
							}
							console.log('asdfasdf= ' + typeof zipcode);
							if (zipcodeString != '') {
								$
										.ajax(
												{
													type : 'POST',
													url : '/ecard-webapp/cards/zipCodeSearch',
													data : 'zipCode='
															+ zipcodeString,
												})
										.done(
												function(resp, status, xhr) {
													if (resp != '') {
														$(".sp-zipCode").text(
																"");
														if (resp.length > 0) {
															for (i = 0; i < resp.length; ++i) {
																$(
																		'#address'
																				+ (i + 1))
																		.val(
																				resp[i]);
															}
														}
													} else {
														$(".sp-zipCode")
																.text(
																		"<fmt:message key='validate.zipcode'/>");

													}
												})
										.fail(
												function(xhr, status, err) {
													$(".sp-zipCode")
															.text(
																	"<fmt:message key='validate.zipcode'/>");

												});
							}
						});
		var date = new Date();
		$('#datepicker').datepicker({
			language : 'ja',
			format : 'yyyy年MMdd日',
			forceParse : true,
			autoclose : true
		}).on('changeDate', function(ev) {
			$("input[name=useDate]").val(ev.date);
		}).datepicker("setDate", "0");

		$('#datepickerEndDate').datepicker({
			language : 'ja',
			format : 'yyyy年MMdd日',
			forceParse : true,
			autoclose : true
		}).on('changeDate', function(ev) {
			$("input[name=endDate]").val(ev.date);
		}).datepicker("setDate", "2037-12-31");

		$('#roleAdminId').on('change', function() {
			var role = $(this).val();
			$('input[name=roleAdminId]').val(role);
			console.log(role);
		});
		$('#roleId').on('change', function() {
			var role = $(this).val();
			$('input[class=roleId]').val(role);
			console.log(role);			
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
			<h4 class="h4-header">登録オペレータ</h4>
			</div>

			<div class="float-right float-right-button"></div>
			 <div class="col-sm-3" style = "float: right;margin: 0 auto;">
                   <h4 class="h4-header">
                       <span><button type="button" class="btn btn-primary btn_cancle"
									data-dismiss="modal">キャンセル</button></span>
                       <span><button type="button" class="btn btn-primary"
									data-dismiss="modal" id="btnSaveUserProfile">保存</button></span>
                    </h4>
             </div>
		</div>
       
	</div>

	<!-- END BAR TOP -->
	<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
		<form class="form-horizontal" id="registerUserProfileForm" action="/ecard-webapp/operators/register", method="POST" accept-charset="UFT-8" autocomplete="off">
			<div class="col-sm-12">
				<div class="col-sm-6">
					<!-- BEGIN FORM -->

					<fieldset>
						<div class="form-group">
							<label for="title-of-honour" class="control-label col-xs-2"><fmt:message key="operator.email"/><span class="error-color">*</span></label>
							<div class="col-xs-4">
								<input name="email" type="text" class="form-control" id="email"  size="40"  autocomplete="off" >
									<span class='sp-email-format' style="color:red"></span>
							</div>
						</div>
						<%-- <div class="form-group">
							<label for="lastname" class="control-label col-xs-2"><fmt:message key="operator.password"/><span class="error-color">*</span></label>
							<div class="col-xs-4">
								<input name = "password" type="password" class="form-control" id="password"
									maxlength="40" size="40"   autocomplete="off">
									<span class='sp-password' style="color:red"></span>
									
							</div>
						</div> --%>
					</fieldset>
					<div class="form-group">
							<label for="groupComposition" class="control-label col-xs-2"><fmt:message key="operator.role.service"/><span class="error-color">*</span></label>
							<div class="col-xs-4">
								 <form>
				                       <select id = "roleId" class="form-control" >
				                        <option value = "1">未選択 </option>
				                         <option value = '6'>スーパーバイザー</option>
				                         <option value = '5'>承認者（拠点リーダー）</option>
				                         <option value = '4'>オペレーター</option>
				                        </select>
				                </form>
								<span class='sp-roleId' style="color:red"></span>
							</div>
							<input type="hidden" name="roles.roleId" class = "roleId" value="1" />
					</div>
					<div class="form-group">
							<label for="groupComposition" class="control-label col-xs-2"><fmt:message key="operator.role.admin"/><span class="error-color">*</span></label>
							<div class="col-xs-4">
								 <form   >
				                        <select id ="roleAdminId" class="form-control" name ="">
				                         <option value = "1">未選択 </option>
				                         <c:if test="${not pageContext.request.isUserInRole('ROLE_AUTHORITY_USER') }">
				                          <option value = '7'>推進管理者</option> 
					                      <option value = '3'>保守担当者</option>
					                     </c:if>
					                     <option value = '2'>社内管理者</option>
					                    
				                        </select>
				                </form>
				                <input type="hidden" class = "role-admin" name="roleAdminId" value='1'/>
								<span class='sp-roleId' style="color:red"></span>
							</div>
					</div>
					
					<div class="form-group ">
						<div class="col-xs-4 col-xs-offset-2">
							<ul class="list-li">
								<div  style="width:274px">
									<li><input icheck type="checkbox" class="i-checks"
										name="sfManualLinkFlg" id="all_card" value="1"/> <a href="#"><fmt:message key="operator.salesforce"/></a></li>
								</div>
							 </ul>
						</div>
					</div>
					 <div class="row form-group">
                         <label class="control-label col-sm-2 col-xs-offset-0" for="name">利用開始日</label>
                         <div class="col-sm-4">
                         	<div class="input-group date">
                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                               <input type="text" id="datepicker"  class="form-control" name="useDateShow" value='<fmt:formatDate value="${operatoredit.user.useDate}" pattern="yyyy年MM月dd日" />'>
                               <input type="hidden" id="useDate" class ="useDate" name="useDate" value='<fmt:formatDate value="${operatoredit.user.useDate}" pattern="yyyy年MM月dd日"/>'/>
                              
                           </div>
                            <span class='sp-useDate' style="color:red"></span>
                         </div>
                     </div>
                     <div class="row form-group">
                         <label class="control-label col-sm-2 col-xs-offset-0" for="name">利用終了日</label>
                         <div class="col-sm-4">
                         	<div class="input-group date">
                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                               <input type="text" id="datepickerEndDate" class="form-control" name="endDateShow" value='<fmt:formatDate value="${operatoredit.user.endDate}" pattern="yyyy年MM月dd日" />'>
                               <input type="hidden" name="endDate" class = "endDate" value='<fmt:formatDate value="${operatoredit.user.endDate}" pattern="yyyy年MM月dd日"/>'/>
                               
                           </div>
                           <span class='sp-endDate' style="color:red"></span>
                         </div>
                     </div>
					<%-- <div class="row form-group ">
						<div class="col-sm-10 col-xs-offset-0 ">
							<ul class="list-li">
								<div class="col-sm-4" style="width:274px">
									<li><input icheck type="checkbox" class="i-checks"
										name="groupDataDlFlg" id="all_card" value="1"/> <a href="#"><fmt:message key="operator.groupDataDlFlg"/></a></li>
									<li><input icheck type="checkbox" class="i-checks"
										name="allDataDlFlg" id="ungraded" value="1"/> <a href="#"><fmt:message key="operator.allDataDlFlg"/></a></li>
								</div>
								<div class="col-sm-4" style="width:274px">
									<li><input icheck type="checkbox" class="i-checks"
										name="helpdeskFlg" id="not_update" value="1"/> <a href="#"><fmt:message key="operator.helpdeskFlg"/></a></li>
									<li><input icheck type="checkbox" class="i-checks"
										name="sfManualLinkFlg" id="not_update" value="1"/> <a href="#"><fmt:message key="operator.sfManualLinkFlg"/></a></li>
								</div>
							</ul>
							<ul class="list-li">
								<div class="col-sm-4" style="width:274px">
									<li><input icheck type="checkbox" class="i-checks"
										name="groupDataDlRequestFlg" id="groupDataDlRequestFlgId" value="1"/> <a href="#"><fmt:message key="operator.group_data_dl_request_flg"/></a></li>
									<li><input icheck type="checkbox" class="i-checks"
										name="allDataDlRequestFlg" id="allDataDlRequestFlgId" value="1"/> <a href="#"><fmt:message key="operator.all_data_dl_request_flg"/></a></li>
								</div>
							</ul>
						</div>
					</div> --%>
				</div>

				<div class="col-sm-6">
					<fieldset>
					<!-- bach.le https://livepass.backlog.jp/view/MEISHI-377 -->
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.companyname"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<form:select name="groupCompanyId" class="form-control" path="operatorregisterData" id="companyId">
							    		<form:options items="${operatorregisterData.get('listCompany')}" />
								</form:select>
								<span class='sp-companyName' style="color:red"></span>
								<input type="hidden" name = "groupCompanyId"  id ="groupCompanyId" value="">
								<input type="hidden" name = "companyName" id = "companyName" value="">
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.departmentName"/><span style="color:red">*</span></label>
							<div class="col-xs-6">
								<%-- <form:select name="departmentGroupCompanyDepartmentName" class="form-control" path="operatorregisterData" id="departmentId">
							    		<form:options items="${operatorregisterData.get('listDepartment')}" />
								</form:select> --%>
								<input type="text" name = "departmentName" id="departmentName" class="form-control" value=""  size="40">
								<span class='sp-departmentName' style="color:red"></span>								
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.positionName"/></label>
							<div class="col-xs-6">
								<input name="positionName" type="text" class="form-control" id="positionName"
									 size="40" >
							</div>
						</div>
						<div class="form-group"><hr align="left" style="margin-left: 16px; width: 737px;"/></div>
						<div class="form-group">
							<label for="title-of-honour" class="control-label col-xs-4"><fmt:message key="operator.lastname"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								   <input name="lastName" type="text" class="form-control" id="lastName"
									 size="40" >
									<span class='sp-lastName' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="lastName" class="control-label col-xs-4"><fmt:message key="operator.firstname"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
							   <input name="firstName" type="text" class="form-control" id="firstName"
									 size="40" >
								
									<span class='sp-firstName' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.lastnamekana"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="lastNameKana" type="text" class="form-control" id="lastNameKana"
									 size="40" >
									<span class='sp-lastNameKana' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="lastname-furigana" class="control-label col-xs-4"><fmt:message key="operator.firstnamekana"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="firstNameKana" type="text" class="form-control" id="firstNameKana"
									 size="40" >
									<span class='sp-firstNameKana' style="color:red"></span>
							</div>
						</div>
						
						<%-- <div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.zipcode"/><span class="error-color">*</span></label>
							<div class="col-xs-4 ch-color-link" >
							   <div class=" inline block-inline" style ="position: relative;margin: 0 auto; width: auto;">
								<input name="zipCode" type="text" class="form-control" id="zipCode"
									maxlength="40" size="40" >
									 <a style = "position: absolute;left: 298px;top: 0px;width: 50px;height: 32px;" class="ch-address btn_address" >編集</a>
								 </div>
								  
									<span class='sp-zipCode' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.address1"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="address1" type="text" class="form-control" id="address1"
									maxlength="40" size="40" >
									<span class='sp-address1' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.address2"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="address2" type="text" class="form-control" id="address2"
									maxlength="40" size="40" >
									<span class='sp-address2' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.address3"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="address3" type="text" class="form-control" id="address3"
									maxlength="40" size="40" >
									<span class='sp-address3' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.telnumbercompany"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="telNumberCompany" type="text" class="form-control" id="telNumberCompany"
									maxlength="40" size="40" >
									<span class='sp-telNumberCompany' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.telnumberdepartment"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="telNumberDepartment" type="text" class="form-control" id="telNumberDepartment"
									maxlength="40" size="40" >
									<span class='sp-telNumberDepartment' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.telnumberdirect"/><span class="error-color">*</span></label>
							<div class="col-xs-6">
								<input name="telNumberDirect" type="text" class="form-control" id="telNumberDirect"
									maxlength="40" size="40" >
									<span class='sp-telNumberDirect' style="color:red"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.fax"/>
							</label>
							<div class="col-xs-6">
								<input name="faxNumber" type="text" class="form-control" id="faxNumber"
									maxlength="40" size="40" >
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.mobilenumber"/>
							</label>
							<div class="col-xs-6">
								<input name="mobileNumber" type="text" class="form-control" id="mobileNumber"
									maxlength="40" size="40" >
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.companyUrl"/>
							</label>
							<div class="col-xs-6">
								<input name="companyUrl" type="text" class="form-control" id="companyUrl"
									maxlength="40" size="40" >
							</div>
						</div> --%>
						<div class="form-group"><hr align="left" style="margin-left: 16px; width: 737px;"/></div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.memo1"/>
							</label>
							<div class="col-xs-6">
								<input name="memo1" type="text" class="form-control" id="memo1"
									 size="40" >
							</div>
						</div>
						<div class="form-group">
							<label for="middlename" class="control-label col-xs-4"><fmt:message key="operator.memo2"/>
							</label>
							<div class="col-xs-6">
								<input name="memo2" type="text" class="form-control" id="memo2"
									 size="40" >
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
