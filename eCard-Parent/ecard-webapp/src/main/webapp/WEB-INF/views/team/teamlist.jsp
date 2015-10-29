<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<script type="text/javascript">
	$(document).ready(function() {
		var dataTables = $('#teamlist').dataTable({
			"dom" : '<<t>ip>',
			"ordering" : false,
			"iDisplayLength" : 20,
			"language": {
				"zeroRecords": '<fmt:message key="operator.list.table.emptyTable"/>',
				"emptyTable": '<fmt:message key="operator.list.table.emptyTable"/>',
				"info": '<fmt:message key="operator.list.table.info"/>',
				"infoEmpty": '<fmt:message key="operator.list.table.info"/>',
				"paginate": {
					"previous": '<fmt:message key="operator.list.table.paginate.previous"/>',
					"next": '<fmt:message key="operator.list.table.paginate.next"/>'
				}
			},
		});
		
		$('a.ch-del').on('click', function() {
			if (confirm('<fmt:message key="team.list.confirmDelete"/>')) {
				var parentTrTag = $(this).parent().parent();
				var teamId = parentTrTag.find("td:first-child").text();
				 $.ajax({
					type: 'POST',
					url: 'delete',
					cache: false,
					data: 'teamId='+teamId
				}).done(function(resp, status, xhr) {
					if (resp === 0)
						location.reload();
					else
						alert('Error');
				}).fail(function(xhr, status, err) {
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
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12">
				<div class="float-left ">
					<h4 class="h4-header">
						<fmt:message key="team.list.title" />
					</h4>
				</div>
				<div class="float-right">
					<a id="add" href="<c:url value='/teams/register'/>"><i
						class="fa fa-plus icon-rounded"></i></a>
				</div>
			</div>
		</div>

		<!-- END BAR TOP -->
		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
			<!-- END SEARCH -->
			<!-- DATA TABLE -->
			<div class="col-sm-12 container table-list-operator">
				<div class="row " id="data-table">
					<div class="ibox-content ibox-content-company">
						<table class="table container dataTable no-footer" id="teamlist"
							style="margin-top: -84px;" role="grid"
							aria-describedby="paging_info">
							<thead>
								<tr role="row">
									<th><fmt:message key="team.no" /></th>
									<th><fmt:message key="team.team" /></th>
									<th><fmt:message key="team.membership" /></th>
									<th><fmt:message key="team.numberOfCard" /></th>
									<th><fmt:message key="team.action" /></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="team" items="${teamlistVO}" varStatus="loop">
									<tr id="${loop.index}" role="row"
										class="${loop.index%2==0?'odd':'even'}">
										<td><c:out value="${team.teamId}" /></td>
										<td><c:out value="${team.teamName}" /></td>
										<td><c:out value="${team.userCount}" /></td>
										<td><c:out value="${team.targetCount}" /></td>
										<td class="ch-color-link"><a class="ch-ok"
											href="<c:url value='/teams/display/${team.teamId}'/>"><fmt:message
													key="team.button.display" /></a> <a class="ch-edit"
											href="<c:url value='/teams/edit/${team.teamId}'/>"><fmt:message
													key="operator.list.edit" /></a> <a class="ch-add"
											href="<c:url value='/teams/add-team-member/${team.teamId}'/>"><fmt:message
													key="team.list.button.add" /></a> <a class="ch-del"><fmt:message
													key="operator.list.delete" /></a></td>
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
