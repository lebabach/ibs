<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false"%>

<script type="text/javascript">
$(document).ready(function(){
	function checkValidationForm(){
		var checkValidation=true;
		if($("#tv_teamName").val()==""){
			$(".sp-teamName").text("未記入で承認申請した場合はエラー");
			checkValidation=false;
		}	
		if($("#tv_target").val()==""){
			$(".sp-target").text("未記入で承認申請した場合はエラー");
			checkValidation=false;
		}
		return checkValidation;
	}
	function resetValidationForm(){
		$(".sp-teamName").text("");
		$(".sp-target").text("");
	}
	$('#btnSaveTeamAdd').on('click', function(){
			resetValidationForm();
			if(!checkValidationForm()){
				return false;
			}
			var cbUser = new Array();
			$('input[name=chkUser]:checked').each(function() {
				cbUser.push($(this).val());
			});
			
			if(cbUser.length > 0 )
			{			
				$.ajax({
					type: 'POST',
					url: '../teams/registerteam',
					cache: false,
					data: {"cbUser":cbUser,"teamName":$('#tv_teamName').val().trim(),"targetCount":$('#tv_target').val().trim()}
				}).done(function(resp, status, xhr){
					if(resp == 1){
						window.location.href = "../teams/list";
					} else {
						alert('Error');
					}
				}).fail(function(resp, status, xhr){
					alert('Error');
				});
			}

	});
});
</script>
<div class="container-fluid padding-top20 bg-container height100per">

	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header col-sm-12">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header">メール送信</h4>
				</div>

				<div class="float-right float-right-manage"></div>
			</div>
		</div>

		<!-- END BAR TOP -->
		<div
			class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
			<div class="container col-sm-8 col-xs-offset-2" id="data-table">
				<div class="container">
					<form class="form-horizontal" role="form"
						action="/ecard-webapp/teams/register" method="POST"
						accept-charset="UFT-8">
						<div class="form-group">
							<label class="control-label col-sm-1" for="email">チーム名</label>
							<div class="col-sm-2">
								<input name="teamName" class="form-control" id="tv_teamName">
								<span class='sp-teamName' style="color: red"></span>
							</div>

							<div class="col-sm-3">
								<div class="input-group">
									<p
										style="width: 300px; padding: 5px 10px; background: #1ab394; font-weight: bold;">チームメンバーを追加</p>
								</div>
							</div>

						</div>
						<div class="form-group">
							<div class="col-sm-3">
								<label class="control-label col-sm-3"
									style="padding: 0; line-height: 30px; width: 27%;">目標登録数</label>
								<div class="col-sm-8">
									<input name="targetCount" class="form-control" id="tv_target"
										style="margin-left: 11px; width: 165px;" placeholder="100">
									<span class='sp-target' style="color: red"></span>
								</div>
							</div>
							<div class="col-sm-4 "
								style="overflow: scroll; width: 400px; height: 200px;">
								<table class="table container">
									<tbody>
										<tr style="background: #c3c3c3;">
											<td>氏名</td>
											<td>権限</td>
											<td>部署</td>
										</tr>
										<c:forEach var="userObject" items="${teamDisplayVO.userInfos}"
											varStatus="loop">
											<tr class="${loop.index%2==0?'odd':'even'}">
												<td><input type="checkbox"
													class="i-checks i-checks-chk_all" name="chkUser"
													value="<c:out value="${userObject.userId}"/>"> <c:out
														value="${userObject.lastName}" /></td>
												<td><c:out value="${userObject.companyName}" /></td>
												<td><c:out value="${userObject.departmentName}" /></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-2">
								<a href='<c:url value="/teams/list"/>'
									class="btn btn-primary btn_cancle" role="button">キャンセル</a>
							</div>
							<div class="col-xs-2">
								<button type="button" class="btn btn-primary js-23 "
									id="btnSaveTeamAdd">完了</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- BAR BODY -->

	</div>
	<!-- END RIGHT SIDE -->
</div>