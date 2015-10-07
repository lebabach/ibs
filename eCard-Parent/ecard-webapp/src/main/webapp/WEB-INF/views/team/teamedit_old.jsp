<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false"%>

<script type="text/javascript">
$(document).ready(function(){
	/* $.each( modelAttributeValue, function( index, value ){
	    console.log(value);
	}); */
	
	$('#btnCancelTeamEdit').on('click', function(){
		window.location.href = "../list";
	});
	$('#btnSaveTeamEdit').on('click', function(){
			var cbUser = new Array();
			$('input[name=chkUser]:checked').each(function() {
				cbUser.push($(this).val());
			});
			var url = window.location.href; 
			var _array = url.split('/');
			var teamID = _array[_array.length-1];
			
			if(cbUser.length > 0 || ($('#tv_teamName').val().trim() !== $('#tv_teamName_original').val().trim()) || ($('#tv_target').val().trim() !== $('#tv_target_original').val().trim()))
			{			
				$.ajax({
					type: 'POST',
					url: '../editexecute',
					cache: false,
					data: {"cbUser":cbUser,"teamID":teamID,"teamName":$('#tv_teamName').val().trim(),"targetCount":$('#tv_target').val().trim()}
				}).done(function(resp, status, xhr){
					if(resp == 1){
						location.reload();
					} else {
						alert("<fmt:message key="card.scancard.user.error"/>");
					}
				}).fail(function(resp, status, xhr){
					alert("<fmt:message key="card.scancard.user.error"/>");
				});
			}

	});
});
</script>
<body>
	<!-- BODY -->
	<div class="container-fluid padding-top20 bg-container height100per">

		<!-- RIGHT SIDE -->
		<div id="right-side" class="col-sm-12">
			<!-- BAR TOP -->
			<div class="row bg-white box-shadow menu-top-header col-sm-12">
				<div class="col-sm-12">
					<div class="float-left">
						<h4 class="h4-header">チーム送信</h4>
					</div>

					<div class="float-right float-right-manage"></div>
				</div>
			</div>

			<!-- END BAR TOP -->
			<div
				class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
				<div class="container col-sm-8 col-xs-offset-2" id="data-table">
					<div class="container">
						<form id="teamEditForm" class="form-horizontal" role="form">
							<div class="form-group">
								<label class="control-label col-sm-1" for="email">チーム名</label>
								<div class="col-sm-2">
									<input type="email" class="form-control" id="tv_teamName"
										value="<c:out value="${teamDisplayVO.teamInfo.teamName}"/>">
									<input type="hidden" id="tv_teamName_original"
										value="<c:out value="${teamDisplayVO.teamInfo.teamName}"/>">
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
										<input class="form-control" id="tv_target"
											style="margin-left: 11px; width: 165px;" placeholder="100"
											value="<c:out value="${teamDisplayVO.teamInfo.targetCount}"/>">
										<input type="hidden" id="tv_target_original"
											value="<c:out value="${teamDisplayVO.teamInfo.targetCount}"/>">

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
											<c:forEach var="userObject"
												items="${teamDisplayVO.userInfos}" varStatus="loop">
												<tr class="${loop.index%2==0?'odd':'even'}">
													<td><input type="checkbox"
														class="i-checks i-checks-chk_all" name="chkUser"
														value="<c:out value="${userObject.userId}"/>">&nbsp;&nbsp;&nbsp;<c:out
															value="${userObject.lastName}" /></td>
													<td><c:out value="${userObject.companyName}" /></td>
													<td><c:out value="${userObject.departmentName}" /></td>
												</tr>
											</c:forEach>
											<c:forEach var="usersInTeam"
												items="${teamDisplayVO.usersInTeam}" varStatus="loop">
												<tr class="${loop.index%2==0?'odd':'even'}">
													<td><input type="checkbox"
														class="i-checks i-checks-chk_all" name="chkUser"
														value="<c:out value="${usersInTeam.userId}"/>" checked>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<c:out
															value="${usersInTeam.lastName}" /></td>
													<td><c:out value="${usersInTeam.companyName}" /></td>
													<td><c:out value="${usersInTeam.departmentName}" /></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-2">
									<button type="button" class="btn btn-primary btn_cancle "
										id="btnCancelTeamEdit">キャンセル</button>
								</div>
								<div class="col-xs-2">
									<button type="button" class="btn btn-primary js-23 "
										id="btnSaveTeamEdit">完了</button>

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
	<!-- END BODY -->