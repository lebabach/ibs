<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
$(document).ready(function() {
	$('#backPage').on('click', function() {
		window.history.back();
	});
});
</script>
<!-- BODY -->
<div class="bg-detail">
	<div class="container padding-top20 bg-container height100per container-collectname">
		<!-- CENTER SIDE -->
		<div id="center-side" class="col-sm-12 ch-title">
			<div class="row">
				<div class="col-sm-8"><fmt:message key="card.detail.title"/></div>
				<div class="col-sm-2 ch-color-link" style="float:right">
					<a id="backPage" class="ch-edit" style="float:right"><fmt:message key="card.detail.back"/></a>
				</div>
				<%-- <div class="col-sm-2 ch-color-link">
					<a href="<c:url value="/cards/edit/${cardInfo.cardId}"/>" class="ch-edit"><fmt:message key="card.detail.edit"/></a>
				</div> --%>
			</div>
		</div>
		<div id="center-side" class="col-sm-12">
			<div class="row bg-white box-shadow">
	
				<div class="col-sm-12" id="ct1">
					<div class="float-left" style="width: 35%;">
						<h4 class="h4-header">
							<a href="#">
							<div style="position:fixed; width:26%;"><img src="data:image/png;base64,${cardInfo.imageFile}" class="img-100"></div></a>
						</h4>
					</div>
	
					<!-- <div class="float-right flr-400" style="width: 60%;"> -->
					<div class="row box-shadow bg-white float-right flr-400"
						style="width: 60%;">
						<div class="col-sm-12 right-side-top"></div>
						<div class="row" id="content-detail-card" style="width: 100%;">
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.lastName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.lastName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.firstName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.firstName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.lastNameKana"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.lastNameKana}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.firstNameKana"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.firstNameKana}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.telNumberCompany"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.telNumberCompany}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.telNumberDepartment"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.telNumberDepartment}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.telNumberDirect"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.telNumberDirect}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.mobileNumber"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.mobileNumber}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.faxNumber"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.faxNumber}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.email"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.email}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.companyName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.companyName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.companyNameKana"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.companyNameKana}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.departmentName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.departmentName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.positionName"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.positionName}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.companyUrl"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.companyUrl}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.zipCode"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.zipCode}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.address1"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.address1}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.address2"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.address2}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.address3"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.address3}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.address4"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.address4}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.memo1"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.memo1}" /></p>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
									<h4><fmt:message key="card.detail.memo2"/></h4>
								</div>
								<div class="float-right float-right-content">
									<p class="confirm"><c:out value="${cardInfo.memo2}" /></p>
								</div>
							</div>
						</div>
					</div>
					<!--  </div> -->
				</div>
	
			</div>
		</div>
		<!-- END CENTER SIDE  -->
		<!-- END RIGHT SIDE -->
	</div>
</div>
<!-- END BODY -->