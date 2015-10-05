<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<script>
	$(document).ready(function() {
		$('input').prop("disabled", true);
	});
</script>
<div class="container-fluid padding-top20 bg-container height100per">
	<!-- CENTER SIDE -->
	<div id="center-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12 ch-check" id="ct1">
				<div class="float-left col-sm-6">
					<h4 class="h4-header">ユーザー詳細</h4>
				</div>
				<div class="float-right">
					<a href="/ecard-webapp/operators/list" id="add"><i class="fa icon-rounded">戻る</i></a> 
				</div>
			</div>
		</div>
		<!-- END BAR TOP -->
		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">

			<div class="col-sm-12">
				<div class="col-sm-6">
					<!-- BEGIN FORM -->
					<form class="form-horizontal">
						<fieldset>
							<div class="form-group">
								<label for="title-of-honour" class="control-label col-xs-2"><fmt:message key="operator.confirm.email"/></label>
								<div class="col-xs-4">
									<p class="confirm"><c:out value="${operatorConfirm.email}" /></p>
								</div>
							</div>
							
							<div class="form-group">
								<label for="lastname" class="control-label col-xs-2"><fmt:message key="operator.confirm.role"/></label>
								<div class="col-xs-4">
								  <c:if test="${operatorConfirm.roles != null}">
									<p class="confirm"><c:out value="${operatorConfirm.roles.roleName}" /></p>
									
								 </c:if>
								</div>
							</div>
							<div class="form-group">
								<label for="lastname" class="control-label col-xs-2">推進管理</label>
								<div class="col-xs-4">
								  <c:if test="${operatorConfirm.roleAdminId != null}">
									<p class="confirm">
									<display:column>
									    <c:choose>
									        <c:when test="${operatorConfirm.roleAdminId == 7}">推進管理者</c:when>
									        <c:when test="${operatorConfirm.roleAdminId == 3}">保守担当者</c:when>
									        <c:when test="${operatorConfirm.roleAdminId == 2}">社内管理者</c:when>
									        <c:when test="${operatorConfirm.roleAdminId == 2}">ユーザー</c:when>
									        <c:otherwise>ユーザー</c:otherwise>
									    </c:choose>
									</display:column>
									</p>
								 </c:if>
								</div>
							</div>
						</fieldset>
					</form>
					<div class="row form-group ">
						<div class="col-xs-4 col-xs-offset-2">
							<ul class="list-li">
								<div  style="width:274px">
									<c:if test="${operatorConfirm.sfManualLinkFlg==1}">
										<li><input icheck type="checkbox" class="i-checks" checked disabled
										name="sfManualLinkFlg" id="all_card" value="${operatorConfirm.sfManualLinkFlg}"/> <a href="#"><fmt:message key="operator.salesforce"/></a></li>
									</c:if>
									<c:if test="${operatorConfirm.sfManualLinkFlg==0}">
										<li><input icheck type="checkbox" class="i-checks" disabled
										name="sfManualLinkFlg" id="all_card" value="${operatorConfirm.sfManualLinkFlg}"/> <a href="#"><fmt:message key="operator.salesforce"/></a></li>
									</c:if>
									
								</div>
							 </ul>
						</div>
					</div>
					<div class="row form-group">
                         <label class="control-label col-sm-2 col-xs-offset-0" for="name">利用開始日</label>
                         <div class="col-sm-6">
                         	<div class="input-group date">
                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                               <input type="text" id="datepicker"  class="form-control" name="useDateShow" value='<fmt:formatDate value="${operatorConfirm.useDate}" pattern="yyyy年MM月dd日" />'>
                               <input type="hidden" id="useDate" class ="useDate" name="useDate" value='<fmt:formatDate value="${operatorConfirm.useDate}" pattern="yyyy年MM月dd日"/>'/>
                              
                           </div>
                            
                         </div>
                     </div>
                     <div class="row form-group">
                         <label class="control-label col-sm-2 col-xs-offset-0" for="name">利用終了日</label>
                         <div class="col-sm-6">
                         	<div class="input-group date">
                               <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                               <input type="text" id="datepickerEndDate" class="form-control" name="endDateShow" value='<fmt:formatDate value="${operatorConfirm.endDate}" pattern="yyyy年MM月dd日" />'>
                               <input type="hidden" name="endDate" class = "endDate" value='<fmt:formatDate value="${operatorConfirm.endDate}" pattern="yyyy年MM月dd日"/>'/>
                               
                           </div>
                           
                         </div>
                     </div>
				</div>

				<div class="col-sm-6">
					<div class="row" id="content-detail-card" style="width: 100%;">
						<form class="c-form-13">
						    <div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.companyName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.companyName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.departmentName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.departmentName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.positionName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.positionName}" /></p>
								</div>
							</div>
							<div class="form-group"><hr align="left" style="margin-left: 16px; width: 737px;"/></div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.lastName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.lastName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.firstName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.firstName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.lastNameKana"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.lastNameKana}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.firstNameKana"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.firstNameKana}" /></p>
								</div>
							</div>
							<div class="form-group"><hr align="left" style="margin-left: 16px; width: 737px;"/></div>
							<%-- <div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.zipCode"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.zipCode}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.address1"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.address1}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.address2"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.address2}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.address3"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.address3}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.telNumberCompany"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.telNumberCompany}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.telNumberDepartment"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.telNumberDepartment}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.telNumberDirect"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.telNumberDirect}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.faxNumber"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.faxNumber}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.mobileNumber"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.mobileNumber}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.companyUrl"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.companyUrl}" /></p>
								</div>
							</div> --%>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.memo1"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.memo1}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="operator.confirm.memo2"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${operatorConfirm.memo2}" /></p>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- BAR BODY -->
</div>
