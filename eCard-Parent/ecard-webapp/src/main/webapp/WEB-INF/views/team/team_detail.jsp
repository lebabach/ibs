<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false"%>
<style>
.div-bar-top {
	float: none !important;
	margin-left: auto !important;
	margin-right: auto !important;
	width: 600px
}

.div-bar-top1 {
	float: none !important;
	margin-left: auto !important;
	margin-right: auto !important;
	width: 900px
}

.div-bar-top2 {
	width: 100%;
	display: inline-block;
	text-align: center;
	margin: 0 auto;
}
</style>
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
	<div
		class="container-fluid padding-top20 bg-container height100per div-bar-top2">

		<!-- RIGHT SIDE -->
		<div id="right-side" class="col-sm-12">
			<!-- BAR TOP -->
			<div class="row bg-white box-shadow menu-top-header col-sm-12">
				<div class="col-sm-12">
					<div class="float-left">
						<h4 class="h4-header">チーム一覧</h4>
					</div>

					<div class="float-right">
						<h4 class="h4-header">
							<span><button type="button" class="btn btn-primary"
									data-dismiss="modal" id="btnCancel"
									onclick="window.location.href='/ecard-webapp/teams/list'"
									style="float: right;">キャンセル</button></span>
						</h4>
					</div>
				</div>
			</div>

			<!-- END BAR TOP -->
			<div
				class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
				<div class="container col-sm-8 col-xs-offset-2" id="data-table"
					style="float: none !important; margin-left: auto !important;">
					<div class="container"
						style="width: 750px !important; padding-bottom: 50px;">
						<form id="teamEditForm" class="form-horizontal" role="form">
							<div class="form-group">
								<div class="div-bar-top">
									<span
										style="width: 100%; margin-left: 11px; text-align: left; display: block; font-weight: bold">チーム名:
										${teamVO.teamName}</span>

								</div>
							</div>
							<div class="col-sm-4 div-bar-top"
								style="overflow: scroll; height: 200px;">
								<table class="table container">
									<tbody>
										<tr style="background: #c3c3c3;">
											<td align="left">氏名</td>
											<td align="left">権限</td>
										</tr>

										<c:forEach var="members" items="${teamVO.userInfos}"
											varStatus="loop">
											<tr id="${loop.index}" role="row"
												class="${loop.index%2==0?'odd':'even'}">
												<td align="left"><c:out value="${members.name}" /></td>
												<td align="left"><c:out value="${members.companyName}" /></td>

											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
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