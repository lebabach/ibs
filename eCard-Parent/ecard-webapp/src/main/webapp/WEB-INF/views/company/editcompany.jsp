<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false"%>
<style>
.table-department td {
	width: 10%;
}

.mesage-error {
	color: red;
	display: none;
	width: 300px;
}

.fa-times {
	margin-left: 75px;
	z-index: 10;
	color: red;
	float: right;
}
</style>
<script type="text/javascript">
		$(document).ready(function() {
		    var max_fields      = 100; //maximum input boxes allowed
		    var x = 1; //initlal text box count
		    $(".add-field-button").click(function(e){ 
		    	var deparmentname = $('.deparment-name').val();
		    	if(deparmentname == ''){
		    		$('.error-deparment-name').text('<fmt:message key="validate.department"/>');
		    		$('.error-deparment-name').css('display','block');
		    		return false;
		    	}
		    	$('.error-deparment-name').text(' ');
		    	$('.error-deparment-name').css('display','none');
		    	var text = $('.deparment-name').val();
		        e.preventDefault();
		        if(x < max_fields){ 
		            x++; 
		            $(".table-department tbody").append('<tr  style="background:#F0FFFF;"><td style = "width: auto">'+text+'<input type="hidden" name="groupCompanyDepartments" value = "'+text+'"> <i class="fa fa-times btn-delete" href=""></i></td></tr>'); 
		            $('.deparment-name').val('');
		        }
		    });
		    
		    $(".btn-add").click(function(e){ 
		        $('#editCompanyForm').submit();
		    	
		    });  
		    
		    $(".btn_cancle").click(function(e){ 
		    	document.location.href='/ecard-webapp/companies/list';
		    });
		    
		    $(document).on('click', '.btn-delete', function() { 
			    	$(this).parent().remove();
			 });
		    
		    var date = new Date();
		    $('.input-group.date').datepicker({
		        todayBtn: "linked",
		        language: 'ja',
		        format: 'yyyy年MMdd日',
		        keyboardNavigation: false,
		        forceParse: false,
		        calendarWeeks: true,
		        startDate:date,
		        autoclose: true
		    });
		    
		});
		
		
</script>
<!-- BODY -->
<div class="container-fluid padding-top20 bg-container height100per">

	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-12">
		<div class="row bg-white box-shadow menu-top-header col-sm-12">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header">会社登録・修正画面</h4>
				</div>
				<div class="col-sm-3" style="float: right; margin: 0 auto;">
					<h4 class="h4-header" style="margin: 0; padding: 8px 0">
						<span><button type="button"
								class="btn btn-primary btn_cancle" data-dismiss="modal">キャンセル</button></span>
						<span><button type="button" class="btn btn-primary btn-add"
								data-dismiss="modal" id="btnSaveUserProfile">保存</button></span>
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
				<form class="form-horizontal" role="form" id="editCompanyForm"
					action="/ecard-webapp/companies/editCompany" method="POST"
					accept-charset="UFT-8">
					<%-- <div class="form-group">
                              <label class="control-label col-sm-3 col-xs-offset-2" for="date">ID</label>
                              <div class="col-sm-5">
                                <input type="text" class="form-control" id="company-id" name="groupCompanyId" value= '<c:out value="${groupCompanyDepartmentVO.groupCompanyInfo.groupCompanyId}" />'  maxlength="3" size="3">
                                <p class="mesage_error error_company_id"></p>
                              </div>
                            </div> --%>
					<div class="form-group">
						<label class="control-label col-sm-3 col-xs-offset-2" for="name">会社名</label>
						<div class="col-sm-5">
							<input type="text" class="form-control" id="company-name"
								name="groupCompanyName"
								value='<c:out value="${groupCompanyDepartmentVO.groupCompanyInfo.groupCompanyName}" />'>
							<input type="hidden" class="form-control" id="company-id"
								name="groupCompanyId"
								value='<c:out value="${groupCompanyDepartmentVO.groupCompanyInfo.groupCompanyId}" />'
								maxlength="3" size="3">
							<p class="mesage_error error_company_name"></p>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-3 col-xs-offset-2" for="name">グループ参加日</label>
						<div class="col-sm-5">
							<div class="input-group date">
								<span class="input-group-addon"><i class="fa fa-calendar"></i></span><input
									type="text" class="form-control create-date" name="createDate"
									value='<fmt:formatDate value="${groupCompanyDepartmentVO.groupCompanyInfo.createDate}" pattern="yyyy年 MM月dd日" />'>
							</div>
						</div>
					</div>

				</form>
				<!-- </div> -->
			</div>
		</div>
		<!-- BAR BODY -->

	</div>
	<!-- END RIGHT SIDE -->
</div>
<!-- END BODY -->