<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.company-name {
	width: 50%;
}

.file-input-wrapper {
	background-color: #18a689 !important;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn_cancle').on('click', function(){
		   document.location.href='/ecard-webapp/companies/list';
	});
	$('.btn-upload').on('click', function(){
		$('.file-name').val($('.file-input-name').text());
		$('#upload').submit();
	});
	
	 $('input[type=file]').bootstrapFileInput();
});
</script>
</head>
<body>
	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header col-sm-12">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header">
						<fmt:message key="company.action.csv" />
					</h4>
				</div>

				<div class="float-right float-right-manage"></div>
			</div>
		</div>

		<!-- END BAR TOP -->
		<div
			class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
			<!-- <div class="col-sm-8 col-xs-offset-2" id="data-table"> -->
			<div class="container">
				<form class="form-horizontal" role="form" id="upload"
					action="/ecard-webapp/companies/uploadFile"
					enctype="multipart/form-data" method="post">
					<div class="form-group">
						<div class="col-sm-2 col-sm-offset-2">
							<label class="control-label" for="date" style="display: block;"><fmt:message
									key="company.department.name" /></label> <label class="control-label"
								for="name" style="line-height: 40px;"><fmt:message
									key="company.action.fileupload" /></label>
						</div>

						<div class="col-sm-1 company-name">
							<h4 style="width: 100%; display: block; padding-left: 39px;">
								<c:out
									value="${groupCompanyDepartmentVO.groupCompanyInfo.groupCompanyName}" />
							</h4>
							<input type="hidden" class="form-control group-company-id"
								name="groupCompanyId"
								value='<c:out value="${groupCompanyDepartmentVO.groupCompanyInfo.groupCompanyId}" />'>
							<h4
								style="margin-top: 15px; line-height: 30px; padding-left: 40px;">
								<input type="file" name="file" class="submit-1" id="files"
									multiple title="フォルダ選択" style="left: -196.859375px; top: 3px;">
							</h4>
							<input type="hidden" class="form-control file-name" name="name"
								value=''>
						</div>
						<div class="form-group"></div>
						<div class="form-group" style="margin-left: -180px;">
							<div class="col-sm-offset-5 col-sm-2"
								style="float: left; margin-left: 350px;">
								<button type="button" class="btn btn-primary btn_cancle ">
									<fmt:message key="company.action.cancel" />
								</button>
							</div>
							<div class="col-sm-offset-5 col-sm-2"
								style="float: left; margin-left: 33px;">
								<button type="button" class="btn btn-primary btn-upload ">
									<fmt:message key="company.action.submit" />
								</button>
							</div>
						</div>
				</form>
				<!-- </div> -->
			</div>
		</div>
		<!-- BAR BODY -->

	</div>

</body>
</html>