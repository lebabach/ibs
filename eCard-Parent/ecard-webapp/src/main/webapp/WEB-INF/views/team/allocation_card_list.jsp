<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<style>
thead, tbody { display: block; width:100% }

#paging1 tbody {
    height: 250px;       /* Just for the demo          */
    overflow-y: auto;    /* Trigger vertical scroll    */
    overflow-x: hidden;  /* Hide the horizontal scroll */
}
#paging1 tbody tr {
   width:1201px;
}
.table-column{
	width:33%;
	padding-right:0px
}
.txt-table{
	float:right;
	width:230px;
	height: 33px;
}
.title-abc{
	background:#f5f5f5;
	padding:10px;
	display:inline-block;
	text-align:left;
	width:1201px;
}
</style>
<script>

function checkNumber(number){
	if(number==""){
		return 0;
	}else if($.isNumeric(number)){
 		 return parseInt(number)
 	}else{
 		return -1; 
 	}
  }
function checkValidationForm(){
	var target_count = $('.target_count2').val();
	var checkValidation=true;
	if(checkNumbersOfTargetCount() ==false){
		alert("半角英数字で入力してください");
		checkValidation=false;
	}
	if(checkNumbersOfCardNotCharacters()==false){
		alert("半角英数字で入力してください");
		checkValidation=false;
	}
	return checkValidation;
}

function checkNumbersOfCardNotCharacters(){
	var check=true;
	$("#paging1 input.txt-table").each(function() {
		var number=checkNumber($(this).val());
		if(number==-1){
			check=false;
			return false;
		}
	});
}

function checkNumbersOfTargetCount(){
	var number=checkNumber($('.target_count2').val());
	if(number==-1){
		return false;
	}
	return true;
}
function resetValidationForm(){
	$(".sp-target_count").text("");
}

function listAllication(teamId) {
	$.ajax({
		type: 'POST',
		url: 'listAllocation',
		data: 'teamId='+teamId
	}).done(function(resp, status, xhr) {
		$('.content-user').html("");
		
		if(resp[0] != null){
		    $('.current_count').text(resp[0].totalCardTeam);
		    $('.target_count1').val(resp[0].targetCount);
		    $('.target_count2').val(resp[0].targetCount);
		}else{
			$('.current_count').text(0);
			$('.target_count1').val(0);
		    $('.target_count2').val(0);
		}
		if(parseInt($('.current_count').text()) != 0){
			$('.btn-add').css('display','none');
		} else {
			$('.btn-add').css('display','inline-block');
		}
		$.each( resp, function( key, value ) {
			if(value.name != null && value.name1 != null && value.name2 != null){						
            	$('.content-user').append($('<tr  class="tr1">').append(
	                                      $('<td class="table-column">').text(value.name).append(
	                                    		  $('<input type="hidden" name="userId" value="'+value.userId+'"> <input type="text" name="teamdivide" class="txt-table" value="'+value.teamdivide+'">')
	                                    		  ),
	                                      $('<td class="non-lpadding">').text('<fmt:message key="team.allocation.card"/>'),
	                                      $('<td class="table-column">').text(value.name1).append(
	                                    		  $('<input type="hidden" name="userId" value="'+value.userId1+'"> <input type="text" name="teamdivide" class="txt-table" value="'+value.teamdivide1+'">')
	                                    		  ),
	                                      $('<td class="non-lpadding">').text('<fmt:message key="team.allocation.card"/>'),
	                                      $('<td class="table-column">').text(value.name2).append(
	                                    		  $('<input type="hidden" name="userId" value="'+value.userId2+'"> <input type="text" name="teamdivide" class="txt-table" value="'+value.teamdivide2+'">')
	                                    		  ),
	                                      $('<td class="non-lpadding">').text('<fmt:message key="team.allocation.card"/>')
		                        		
	                        	
        
		                    ));
			}else if(value.name != null && value.name1 != null){
				$('.content-user').append($('<tr  class="tr1">').append(
                        $('<td class="table-column">').text(value.name).append(
                      		  $('<input type="hidden" name="userId" value="'+value.userId+'"> <input type="text" name="teamdivide" class="txt-table" value="'+value.teamdivide+'">')
                      		  ),
                        $('<td class="non-lpadding">').text('<fmt:message key="team.allocation.card"/>'),
                        $('<td class="table-column">').text(value.name1).append(
                      		  $('<input type="hidden" name="userId" value="'+value.userId1+'"> <input type="text" name="teamdivide" class="txt-table" value="'+value.teamdivide1+'">')
                      		  ),
                        $('<td class="non-lpadding">').text('<fmt:message key="team.allocation.card"/>') ));
			}else if (value.name != null){
				$('.content-user').append($('<tr  class="tr1">').append(
                        $('<td class="table-column">').text(value.name).append(
                      		  $('<input type="hidden" name="userId" value="'+value.userId+'"> <input type="text" name="teamdivide" class="txt-table" value="'+value.teamdivide+'">')
                      		  ),
                        $('<td class="non-lpadding">').text('<fmt:message key="team.allocation.card"/>') ));
			}
			
			$("#paging1 input.txt-table").each(function() {
				$(this).blur(function(){
					var total=0;
					$("#paging1 input.txt-table").each(function() {
						var number=checkNumber($(this).val());
						if(number==-1){
							alert("半角英数字で入力してください");
							return false;
						}else{
							total+=number;
						}
					});
					$(".current_count").text(total);
					return false;
				});
			});

		});
	}).fail(function(xhr, status, err) {
		alert('Error');
	});
}
$(function() {
	if(parseInt($("#listTeam").val())==0){
		$('.content-user').html("");
		$('.target_count1').val(0)
		$('.target_count2').val(0)
		$('.current_count').text('0');
		$('.btn-add').css('display','none');
	}
	if(parseInt($("#listTeam option:selected" ).val()) == 0){
		$('.content-user').html("");
	}
	$("#paging1 input.txt-table").each(function() {
		$(this).blur(function(){
			var total=0;
			$("#paging1 input.txt-table").each(function() {
				var number=checkNumber($(this).val());
				if(number==-1){
					alert("半角英数字で入力してください");
					return false;
				}else{
					total+=number;
				}
			});
			$(".current_count").text(total);
			return false;
		});
	});
	
	$("#listTeam").on('change', function() {
		var teamId  = $(this).val();
		
		var totalCurrent = $('input[name=total-card]').val();
		// resetTeamAllocation
		if (teamId != 0 ) {
			$.ajax({
				type: 'POST',
				url: 'resetAllocationTeam',
	     		data: 'teamId='+teamId
			}).done(function(resp, status, xhr) {
				var totalUpdate = resp + parseInt(totalCurrent);
				$('.card_number').text(totalUpdate + '枚');
				$('input[name=total-card]').val(totalUpdate);
				resetValidationForm();
				listAllication(teamId);
				
			}).fail(function(xhr, status, err) {
				alert('Error');
			});
		} else {
			$('.content-user').html("");
			$('.target_count1').val(0)
			$('.target_count2').val(0)
			$('.current_count').text('0');
			$('.btn-add').css('display','none');
		}		
	});
	
	$('.btn-add').on('click', function(){
		var teamId = $('#listTeam option:selected').val();
		var target_count = checkNumber( $('.target_count2').val());
		var current_count = checkNumber( $('.current_count').text());
		var total_card = parseInt($('.total-card').val());
		var sum_tagert = 0;
		 resetValidationForm();
			if(!checkValidationForm()){
				return false;
		} 
		 if(total_card < target_count ){
			alert('データ入力待ちの名刺は、◯枚しかないため、割り当てられません。');
			return false;
		} 
		var listUser = new Array();
		var listCardId = new Array();
		$('input[name=cardId]').each(function(){
			var cardId = $(this).val();
			listCardId.push({ 'cardId' : cardId});
		});
		
		$('.content-user tr').each(function() {
			$(this).find('td.table-column').each (function() {
			   var userId=	$(this).find('input[name=userId]').val();
			   var  teamdivide=	$(this).find('input[name=teamdivide]').val();
			   //alert(teamdivide);
			    if(teamdivide != ""){
			        sum_tagert = sum_tagert + parseInt(teamdivide);
			    	listUser.push({ 'userId' : userId,'teamdivide':teamdivide});
			    }
			    
			});
	   });	
		
		/* if(parseInt(sum_tagert) > target_count ){
			alert('データ入力待ちの名刺は、◯枚しかないため、割り当てられません。');
			return false;
		}  */
		
		if(current_count>target_count){
			if (confirm('<fmt:message key="team.allocation.confirm.target"/>')) {
				$.ajax({
					type: 'POST',
					url: 'saveAllocation',
					 dataType: 'json', 
					 contentType: 'application/json',
					 mimeType: 'application/json',
					data:JSON.stringify({"listUser":listUser,"teamId":teamId,"target_count":target_count,"current_count":target_count}) 
				}).done(function(resp, status, xhr) {
					if(resp){
						console.log(resp);
						$('.card_number').text(resp.totalCard + '枚');
						$('.total-card').val(resp.totalCard);
						$('.btn-add').css('display','none');
					}
				}).fail(function(xhr, status, err) {
					alert('Error');
				});
				
			}
		}else if(current_count<target_count){
			if (confirm('<fmt:message key="team.allocation.confirm.current"/>')) {
				$.ajax({
					type: 'POST',
					url: 'saveAllocation',
					 dataType: 'json', 
					 contentType: 'application/json',
					 mimeType: 'application/json',
					data:JSON.stringify({"listUser":listUser,"teamId":teamId,"target_count":target_count,"current_count":target_count}) 
				}).done(function(resp, status, xhr) {
					if(resp){
						console.log(resp);
						$('.card_number').text(resp.totalCard + '枚');
						$('.total-card').val(resp.totalCard);
						$('.btn-add').css('display','none');
					}
				}).fail(function(xhr, status, err) {
					alert('Error');
				});
			}
		}else{
		
				$.ajax({
					type: 'POST',
					url: 'saveAllocation',
					 dataType: 'json', 
					 contentType: 'application/json',
					 mimeType: 'application/json',
					data:JSON.stringify({"listUser":listUser,"teamId":teamId,"target_count":target_count,"current_count":target_count}) 
				}).done(function(resp, status, xhr) {
					if(resp){
						console.log(resp);
						$('.card_number').text(resp.totalCard + '枚');
						$('.total-card').val(resp.totalCard);
						$('.btn-add').css('display','none');
					}
				}).fail(function(xhr, status, err) {
					alert('Error');
				});
		}

		
   });
	$('.target_count1').bind('input', function() {
		$('.target_count2').val($(this).val());
	});
});

</script>
 <!-- BODY -->
      <div class="container-fluid padding-top20 bg-container height100per">
        
        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
        	<!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header">
            	<div class="col-sm-12">
                	<div class="float-left">
                    	<h4 class="h4-header"><fmt:message key="team.allocation.title"/></h4>
                    </div>
                	
                    <div class="float-right">
                        <a href="../manager/home" id="add" style="display:inline-block;"><i class="fa icon-rounded"><fmt:message key="team.allocation.cancel"/></i></a>
                        <a href="#" id="add" class = "btn-add" style="display:inline-block;"><i class="fa icon-rounded"><fmt:message key="team.allocation.register"/></i></a>
                    </div>
                </div>
            </div>
            
            <!-- END BAR TOP -->
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
				<div class="col-sm-12">
					<table>
						<tr style="height:45px">
							<td colspan="2">
								<select id="listTeam" class ="team" style="width:210px;height: 33px">
								<option value='0'>チーム選択</option>
								<c:if test="${not empty allocationTeamVO.teamList}">
									<c:forEach var="team" items="${allocationTeamVO.teamList}" varStatus="loop">
									  <option value='<c:out value="${team.teamId}" />'><c:out value="${team.teamName}" /></option>
									 </c:forEach>
							   </c:if>
								</select>
							</td>
							<td></td>
							<td></td>
						</tr>
						<tr style="height:45px">
							<td><fmt:message key="team.allocation.total.number"/></td>
							<td><span style="float:right" class = "card_number"><c:out value="${allocationTeamVO.totalCard}" /> <fmt:message key="team.allocation.card"/> </span>
							<input type="hidden" class ="total-card"  name="total-card" value="${allocationTeamVO.totalCard}"> </td>
						
							<td></td>
							<td></td>
						</tr>
						<tr style="height:45px">
							<td><span style="margin-left: 15px; margin-right: 30px;"><fmt:message key="team.allocation.number.team.allocation"/></span></td>
							<c:choose>
							
									<c:when test="${pageContext.request.isUserInRole('ROLE_SUPERVISOR')}">
											<td><input type="text" name="target_count1" class ="target_count1" value="${allocationTeamVO.teamList[0].targetCount}" style=" height: 33px"/></br><span class='sp-target_count' style="color:red;"></span></td>
				                    </c:when>
					                <c:otherwise>
											<td><input type="text" name="target_count1" class ="target_count1" disabled="disabled" value="${allocationTeamVO.teamList[0].targetCount}" style=" height: 33px"/></br><span class='sp-target_count' style="color:red;"></span></td>
															       
									</c:otherwise>
				           </c:choose>
							
							<input type="hidden"  name = "target_count" class = "target_count2"  value="${allocationTeamVO.teamList[0].targetCount}"> </td>
							<td class="non-lpadding"> <fmt:message key="team.allocation.card"/> </td>
							<td><span style="margin-left: 15px; margin-right: 30px;"><fmt:message key="team.allocation.number.allocation"/></span></td>
							<td><label  class ="current_count" style="padding-right: 2px;margin-bottom: 0px;"><c:out value="${allocationTeamVO.lstUserVo[0].totalCardTeam}" /></br><span class='sp-current_count' style="color:red;"></span></td>
							<td class="non-lpadding"> <fmt:message key="team.allocation.card"/> </td>
						</tr>
					</table>
				</div>
				<!-- END SEARCH -->
				<!-- DATA TABLE -->
				
				<div class="col-sm-12 table-list-operator">
					<div class="row " id="data-table">
						<div class="ibox-content " >
							<div class="title-abc"><fmt:message key="team.allocation.team.list"/></div>
							<table class="table" id = "paging1"  style="width: 1201px;margin-left: 0px;">
								<tbody class = "content-user">
								<c:forEach var="user" items="${allocationTeamVO.lstUserVo}" varStatus="loop">
									<tr id="${loop.index}">
									   <c:if test="${not empty user.name}">  
										 	<td class="table-column"><c:out value="${user.name}" /><input type="hidden" name="userId"  value="${user.userId}"> 
										 		<input type="text" name="teamdivide" class="txt-table">
										 	</td>
											<td class="non-lpadding"> <fmt:message key="team.allocation.card"/> </td>
										</c:if>
										 <c:if test="${not empty user.name1}">  
											<td class="table-column"><c:out value="${user.name1}" /><input type="hidden" name="userId" value="${user.userId1}"> 
												<input type="text" name="teamdivide" class="txt-table">
											</td>
											<td class="non-lpadding"> <fmt:message key="team.allocation.card"/> </td>
										</c:if>
										<c:if test="${not empty user.name2}">  
											<td class="table-column"><c:out value="${user.name2}" /><input type="hidden" name="userId" value="${user.userId2}"> 
												<input type="text" name="teamdivide" class="txt-table">
											</td>
											<td class="non-lpadding"> <fmt:message key="team.allocation.card"/> </td>
										</c:if>
									</tr>
								 </c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- END DATA TABLE -->
			</div>
            <!-- BAR BODY -->
            
        </div>
        <!-- END RIGHT SIDE -->
      </div>
      <!-- END BODY -->
