<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="java.lang.Integer"%>
<%@ page import="java.util.List"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#btn_add_company').click(function(event) {
	        /* Act on the event */
	         window.location.href = "add";
	    });
		var dataTables = $('#listcompany').dataTable({
			"dom" : '<<t>ip>',
			"ordering" : false,
			"iDisplayLength" : 5,
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
		
		$(document).on('click', 'a.ch-del', function(){
			if (confirm('<fmt:message key="operator.list.confirmDelete"/>')) {
				var companyID = $(this).closest('tr').find('td:first').text();
				var target = $(this).closest('tr');
				$.ajax({
					type: 'POST',
					url: 'delete',
					data: 'companyID='+companyID
				}).done(function(resp, status, xhr) {
					if (resp == 0) {
						location.reload();
						//console.log(target.prop('id'));
						//dataTables.fnDeleteRow(target.prop('id'));
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
<!-- BODY -->
<div class="container-fluid padding-top20 bg-container height100per">
	<%  
               List<Integer> roles =( List<Integer>) session.getAttribute("rolesOfUser");
           %>
	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header">会社一覧</h4>
				</div>

				<div class="float-right float-right-manage">
					<!-- <button id="btn_import_company" class="btn btn-primary  btn_manage" type="button">                  
                             <i class="fa fa fa-upload"></i>              
                        </button> -->
					<!--  <button id="btn_add_company" class="btn btn-primary  btn_manage" type="button">                  
                            <i class="fa fa-edit"></i>               
                        </button> -->
					<c:if test="${pageContext.request.isUserInRole('ROLE_ADMIN') }">
						<span><a href='<c:url value="/companies/add"/>'
							class="btn btn-primary btn_cancle" data-dismiss="modal">新規追加</a></span>
					</c:if>
				</div>
			</div>
		</div>

		<!-- END BAR TOP -->
		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
			<!-- <div class="col-sm-12">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Enter name, company name, phone ..."> 
						<span class="input-group-btn"> <button type="button" class="btn btn-primary">Search</button></span>
					</div>
				</div> -->
			<!-- END SEARCH -->
			<!-- DATA TABLE -->
			<div class="col-sm-12 container table-list-operator">
				<div class="row " id="data-table">
					<div class="ibox-content ibox-content-company">
						<table class="table container" id="listcompany"
							style="margin-top: -84px;">
							<thead>
								<tr>
									<th><fmt:message key="company.no" /></th>
									<th><fmt:message key="company.name" /></th>
									<th><fmt:message key="company.createdate" /></th>
									<th><fmt:message key="company.action" /></th>
								</tr>
							</thead>
							<tbody>

								<c:forEach var="companyObject" items="${lstcompanyDisplayVO}"
									varStatus="loop">
									<tr id="${loop.index}" role="row">
										<td><c:out value="${companyObject.groupCompanyIdIndex}" /></td>
										<td><c:out value="${companyObject.groupCompanyName}" /></a></td>
										<td><fmt:formatDate value="${companyObject.createDate}"
												pattern="yyyy年 MM月dd日" /></td>
										<td class="ch-color-link"><a
											href='<c:url value="/companies/confirm/${companyObject.groupCompanyId}"/>'
											class="ch-ok"><fmt:message key="company.action.view" /></a> <a
											href='<c:url value="/companies/edit/${companyObject.groupCompanyId}"/>'
											class="ch-edit"><fmt:message key="company.action.edit" /></a>

											<%--  <a href='<c:url value="/companies/adddepartment/${companyObject.groupCompanyId}"/>' class="ch-scv"><fmt:message key="company.action.csv"/></a> --%>

											<a class="ch-del"><fmt:message
													key="company.action.delete" /></a></td>
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
