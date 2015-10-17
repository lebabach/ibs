<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<script type="text/javascript">
var dataTables;
	$(document).ready(function() {
		dataTables = $('#listUser').dataTable( {
			"dom" : '<<t>ip>',
			"iDisplayLength" : 5,
			"processing": true,
			"serverSide": true,
			"searching": false,
			"ordering": false,
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
			"ajax": {
				"url": "search",
				"type": "POST",
				"data": function (dataTableRequest) {
					dataTableRequest.criteriaSearch = $('#criteriaSearch').val();
					dataTableRequest.status = $('#status').val();
					return dataTableRequest;
				},
				"dataSrc": "data",
				"error": function(xhr) {
					alert('error datatable')
				}
			},
			"columns": [
				{ "data": "userIndexNo",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(rowData.userIndexNo != null){
					       $(td).html("<input type='hidden' id='userId' value='"+rowData.userId+"'/>"+ rowData.userIndexNo );
						}else{
							 $(td).html("<input type='hidden' id='userId' value='"+rowData.userId+"'/>");
						}
						
					}},
				{ "data": "lastName"
				},
				{ "data": "companyName"},
				{ "data": "positionName"},
				{ "data": "email"},
				{ "data": "mobileNumber"},
				{ "data": "useStopFlg",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(rowData.useStopFlg == 1){
					       $(td).html("停止");
						}else{
							 $(td).html("");
						}
						
					}	
				},
				{ "data": "leaveFlg",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(rowData.leaveFlg == 1){
					       $(td).html("停止");
						}else{
							 $(td).html("");
						}
						
					}		
				},
				{ "data": "createDate"},
				{ "data": "userId",
					"className": "ch-color-link",
					"createdCell": function (td, cellData, rowData, row, col) {
						$(td).html("<a href='<c:url value='/operators/confirm/"+cellData+"'/>' class='ch-ok'><fmt:message key='operator.list.confirm' /></a> "
								+ "<a href='<c:url value='/operators/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> "
								+ "<a class='ch-del'><fmt:message key = 'operator.list.delete'/></a>");
					}
				},
			],
		});
		$("#criteriaSearch").keyup(function (e) {
			  if (e.which == 13) {
				  $('#btnSearch').trigger('click');
			  }
		 });
		
		
		$('#btnSearch').on('click', function(){
			dataTables.api().destroy();
			dataTables = $('#listUser').dataTable( {
				"dom" : '<<t>ip>',
				"iDisplayLength" : 5,
				"processing": true,
				"serverSide": true,
				"searching": false,
				"ordering": false,
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
				"ajax": {
					"url": "search",
					"type": "POST",
					"data": function (dataTableRequest) {
						dataTableRequest.criteriaSearch = $('#criteriaSearch').val();
						dataTableRequest.status = $('#status').val();
						return dataTableRequest;
					},
					"dataSrc": "data",
					"error": function(xhr) {
						alert('error datatable')
					}
				},
				"columns": [
					{ "data": "userIndexNo",
						   "createdCell": function (td, cellData, rowData, row, col) {
							   if(rowData.userIndexNo != null){
							       $(td).html("<input type='hidden' id='userId' value='"+rowData.userId+"'/>"+ rowData.userIndexNo );
								}else{
									 $(td).html("<input type='hidden' id='userId' value='"+rowData.userId+"'/>");
								}
							}},
				   { "data": "lastName" },
					{ "data": "companyName"},
					{ "data": "positionName"},
					{ "data": "email"},
					{ "data": "mobileNumber"},
					{ "data": "useStopFlg",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(rowData.useStopFlg == 1){
						       $(td).html("停止");
							}else{
								 $(td).html("");
							}
							
						}	
					},
					{ "data": "leaveFlg",
						"createdCell": function (td, cellData, rowData, row, col) {
							if(rowData.leaveFlg == 1){
						       $(td).html("停止");
							}else{
								 $(td).html("");
							}
							
						}		
					},
					{ "data": "createDate"},
					{ "data": "userId",
						"className": "ch-color-link",
						"createdCell": function (td, cellData, rowData, row, col) {
							$(td).html("<a href='<c:url value='/operators/confirm/"+cellData+"'/>' class='ch-ok'><fmt:message key='operator.list.confirm' /></a> "
									+ "<a href='<c:url value='/operators/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> "
									+ "<a class='ch-del'><fmt:message key = 'operator.list.delete'/></a>");
						}
					},
				],
			});
		});
		
		$(document).on('click', 'a.ch-del', function() {
			if (confirm('<fmt:message key="operator.list.confirmDelete"/>')) {
				var userId = $(this).closest('tr').find('td:first-child input').val();
				var target = $(this).closest('tr');
				var tr = $(this).parent().parent();
				$.ajax({
					type: 'POST',
					url: 'delete',
					cache: false,
					data: 'userId='+userId
				}).done(function(resp, status, xhr) {
					if (resp === 0) {
						dataTables.fnDeleteRow(target.prop('id'));
					      var currentPage = $('.current', '#listUser_paginate').text()
					      $('#listUser_paginate').find('.paginate_button').each(function () {
					       if ($(this).text() === currentPage)
					         $(this).click();
					      });
					}
					else
						alert('Error');
				}).fail(function(xhr, status, err) {
					alert('Error');
				});
			}
		});
	});
</script>
<style>
.icon-white {
	background-color: #fff;
}
</style>
<!-- BODY -->
<div class="container-fluid padding-top10 bg-container height100per">
	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header">
						<fmt:message key="operator.list.title" />
					</h4>
				</div>

				<div class="float-right">
					<a href="<c:url value="/operators/register"/>" id="add"><i
						class="fa fa-plus icon-rounded icon-white"></i></a> <%-- <a
						href="<c:url value="/data/importOperatorByCSV"/>" id="addUser"><i
						class="fa fa fa-upload icon-rounded icon-white"></i></a> --%>
				</div>
			</div>
		</div>

		<!-- END BAR TOP -->
		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
			<!-- <form> -->
			<div class="col-sm-12">
				<div class="input-group">
					<input type="text" class="form-control" id="criteriaSearch"
						name="criteriaSearch"
						placeholder="<fmt:message key="operator.list.placeholder"/>" /> <span
						class="input-group-btn">
						<button type="button" id="btnSearch" class="btn btn-primary">
							<fmt:message key="operator.list.search" />
						</button>
					</span>
				</div>
			</div>
			<!-- </form> -->
			<!-- END SEARCH -->
			<!-- DATA TABLE -->
			<div class="col-sm-12 container table-list-operator">
				<div class="row " id="data-table">
					<div class="ibox-content ">
						<table class="table" id="listUser">
							<thead>
								<tr>
									<%--  <th><fmt:message key = "operator.list.id" /></th>
								    <th><fmt:message key = "operator.list.lastname" /></th>
								    <th><fmt:message key = "operator.list.firstname" /></th>
									<th><fmt:message key = "operator.list.lastnamekana" /></th>--%>
									<th><fmt:message key="operator.list.id" /></th>
									<th><fmt:message key="operator.list.name" /></th>
									<th><fmt:message key="operator.list.companyName" /></th>
									<th><fmt:message key="operator.list.position" /></th>
									<th><fmt:message key="operator.list.email" /></th>
									<th><fmt:message key="operator.list.phone" /></th>
									<th><fmt:message key="operator.list.stop" /></th>
									<th><fmt:message key="operator.list.leave" /></th>
									<th><fmt:message key="operator.list.createDate" /></th>
									<th><fmt:message key="operator.list.action" /></th>
								</tr>
							</thead>
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


