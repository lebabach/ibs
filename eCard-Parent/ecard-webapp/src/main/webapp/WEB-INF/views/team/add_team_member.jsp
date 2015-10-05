<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<style>
.column-left {
	width: 145px;
}

.column-right {
	padding-left: 0px;
	width: 300px
}

.member {
	margin-left: 8px
}

thead, tbody {
	display: block;
	width: 100%
}

#paging tbody {
	height: 200px; /* Just for the demo          */
	overflow-y: auto; /* Trigger vertical scroll    */
	overflow-x: hidden; /* Hide the horizontal scroll */
}

tbody td {
	width: 325px
}

.div-bar-top {
	width: 100%;
	display: inline-block;
	text-align: center;
	margin: 0 auto;
}

.div-bar-top2 {
	float: none !important;
	margin-left: auto !important;
}

.scrollbarVerital{
	width: 400px;
    height: 200px;
    overflow-y: scroll;
}
</style>

<script>
function replaceSpaceInString(string){
	return string.replace(/\s+/g, '-').toLowerCase();
}
function copyTableSearch(){
	var resultData = $('<tbody></tbody>');
	$("#listUserNotExitedSearch input").each(function() {
		var trId=$(this).closest("tr").attr("id").replace("search_","");
		var trItem = $("<tr id="+trId+"><td class='table-column' align='left'><span></span></td></tr>");
		var textItem = $(this).parent().next().clone();
		$('span', trItem).append($(this).clone());
		$('span', trItem).append(textItem);
		resultData.append(trItem);
		
	});
	
	$("#listUserNotExited > tbody").remove();
	$("#listUserNotExited").append(resultData);
	
	 $('#listUserNotExited .i-checks').iCheck({
        checkboxClass : 'icheckbox_square-green',
        radioClass : 'iradio_square-green'

     });
}
$(function() {
	
	$("#listUserNotExited .memberName").each(function() {
		$(this).attr("name",replaceSpaceInString($(this).attr("name")));
	});
	
	$("#listUserNotExitedSearch .memberNameSearch").each(function() {
		$(this).attr("name",replaceSpaceInString($(this).attr("name")));
	});
	
	$("#add").click(function(){
		var listUser = new Array();
		$("#listUser input[id^=user_]").each(function() {
			var userId=$(this).val();
			listUser.push({ 'userId' : userId});
		});
		 $.ajax({
			type: 'POST',
			url: '../add-team-member/',
			 dataType: 'json', 
			 contentType: 'application/json',
			 mimeType: 'application/json',
			data:JSON.stringify({"listUser":listUser,"teamId":"${team.teamId}"}) 
		}).done(function(resp, status, xhr) {
			window.location.href = "/ecard-webapp/teams/list"
		}).fail(function(xhr, status, err) {
			alert('Error');
		});
	});
	$(".ch-del").click(function(){
		$("#listUser  input[id^=user_]").each(function() {
			if($(this).is(':checked')){
				$(this).iCheck('uncheck');
				var tr=$(this).parent().parent().parent().parent();
				$("#listUserNotExited > tbody").prepend(tr);
				$("#listUserNotExitedSearch > tbody").append((tr.attr("id","search_"+tr.attr("id"))).clone());
			}
			
		});
		return false;
	});
	$(".ch-ok").click(function(){
		$("#listUserNotExited input[id^=user_]").each(function() {
			if($(this).is(':checked')){
				$(this).iCheck('uncheck');
				var tr=$(this).parent().parent().parent().parent();
				$("#listUser > tbody").prepend(tr);
				var trSearchId=tr.clone().attr("id");
				$("#listUserNotExitedSearch").find("#search_"+trSearchId).remove();
			}
			
		});
		return false;
	});
	$("#txtSearch").keypress(function(e){
		 if(e.which == 13) {
			var name=$(this).val();
			if(name!="" && name!=undefined){
				name=replaceSpaceInString(name);
				var resultData = $('<tbody></tbody>');
				$("#listUserNotExitedSearch input[name*="+name+"]").each(function() {
					var trId=$(this).closest("tr").attr("id").replace("search_","");
					var trItem = $("<tr id="+trId+"><td class='table-column' align='left'><span></span></td></tr>");
					var textItem = $(this).parent().next().clone();
					$('span', trItem).append($(this).clone());
					$('span', trItem).append(textItem);
					resultData.append(trItem);
					
				});
				
				$("#listUserNotExited > tbody").remove();
				$("#listUserNotExited").append(resultData);
				
				 $('#listUserNotExited .i-checks').iCheck({
		            checkboxClass : 'icheckbox_square-green',
		            radioClass : 'iradio_square-green'

		         });
			}else{
				copyTableSearch();
			}
			
			return false;		
	    }
	});
	
	 $('.i-checks').iCheck({
	        checkboxClass : 'icheckbox_square-green',
	        radioClass : 'iradio_square-green'

	    });
	
});
</script>

<!-- BODY -->
<div class="container-fluid padding-top20 bg-container height100per">

	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-12 div-bar-top">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header col-sm-12">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header"><fmt:message key="team.add.team.title"/></h4>
				</div>

				<div class="float-right float-right-manage">
					<a style="display: inline-block;" href="/ecard-webapp/teams/list"><i
						class="fa icon-rounded"><fmt:message key="team.add.team.back"/></i></a>
						<a id="add" style="display: inline-block;"><i
						class="fa icon-rounded"><fmt:message key="team.add.team.save"/></i></a>
				</div>
			</div>
		</div>

		<!-- END BAR TOP -->
		<div
			class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
			<div class="container col-sm-8 col-xs-offset-2 div-bar-top2"
				id="data-table"
				style="width: 900px; padding-bottom: 77px; padding-left: 140px">
				<div class="container">
					<form class="form-horizontal" role="form" id="formSubmit" >
						<div class="form-group">
							<label class="control-label col-sm-1 column-left" for="team"><fmt:message key="team.add.team.name"/></label>
							<div class="col-sm-4">
								<span style="display: block;text-align: left;width: 100%;padding-top: 8px"><c:out value="${team.teamName}" /></span> 
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 column-left" for="team"><fmt:message key="team.add.team.members"/></label>
							<div class="col-sm-4">
								<label class="control-label col-sm-1 column-right" for="team"><fmt:message key="team.add.team.member.title.name"/></label>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 column-left" for="team"></label>
							<div class="col-sm-4 scrollbarVerital">
								<table class="table" id="listUser"
									style="margin-left: 0px; margin-bottom: 0px;">
									<tbody>
										<c:forEach var="user" items="${team.listUser}" varStatus="loop">
											<tr id="tr_${user.userId}">
											  <td class="table-column" align="left"><span><input
													icheck type="checkbox" class="i-checks memberName"name="${user.memberName}" value="${user.userId}"
													id="user_${user.userId}"><span class="member">${user.memberName}</span></span>
													</td>
											</tr>
										 </c:forEach>

									</tbody>
								</table>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 column-left" for="team"></label>
							<div class="col-sm-4 ch-color-link"
								style="margin-left: 0px; width: auto;">
								<a href="" class="ch-del"><fmt:message key="team.add.team.member.remove"/></a>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 column-left" for="team"><fmt:message key="team.add.team.member.search"/></label>
							<div class="col-sm-4">
								<input type="text"
									style="width: 360px; height: 30px" id="txtSearch">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 column-left" for="team"></label>
							<div class="col-sm-4 scrollbarVerital">
								<table class="table" id="listUserNotExited"
									style="margin-left: 0px; margin-bottom: 0px;">
									<tbody>
										<c:forEach var="user" items="${team.listNotExitedUser}" varStatus="loop">
											<tr id="tr_${user.userId}">
											  <td class="table-column" align="left"><span><input
													icheck type="checkbox" class="i-checks memberName" name="${user.memberName}"
													value="${user.userId}"
													id="user_${user.userId}"><span class="member">${user.memberName}</span></span></td>
											</tr>
										 </c:forEach>
										
									</tbody>
								</table>
								<table class="table" id="listUserNotExitedSearch"
									style="margin-left: 0px; margin-bottom: 0px; display:none">
									<tbody>
										<c:forEach var="user" items="${team.listNotExitedUser}" varStatus="loop">
											<tr id="search_tr_${user.userId}">
											  <td class="table-column" align="left"><span><input
													icheck type="checkbox" class="i-checks memberNameSearch" name="${user.memberName}"
													value="${user.userId}"
													id="user_search_${user.userId}"><span class="member">${user.memberName}</span></span></td>
											</tr>
										 </c:forEach>
										
									</tbody>
								</table>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-1 column-left" for="team"></label>
							<div class="col-sm-4 ch-color-link"
								style="margin-left: 0px; width: auto;">
								<a href="" class="ch-ok"><fmt:message key="team.add.team.member.add"/></a>
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
</body>
</html>
