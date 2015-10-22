<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	session="false"%>
<style>
.hidden-column {
	display: none;
}
</style>
<script type="text/javascript">
history.pushState(null, null, null);

window.addEventListener("popstate", function() {
history.pushState(null, null, null);
});
</script>


<script type="text/javascript">
var dataTables = '';


function initDatatables(status, user, criteria) {
	var dataTables  = $('#listCard').dataTable({
		"fnDrawCallback" : function() {
			loadDataComplete();
		},
		"dom" : '<<t>ip>',
		"iDisplayLength" : 5,
		"processing" : true,
		"serverSide" : true,
		"searching" : false,
		"ordering" : false,
		"language" : {
			"zeroRecords" : '<fmt:message key="operator.list.table.emptyTable"/>',
			"emptyTable" : '<fmt:message key="operator.list.table.emptyTable"/>',
			"info" : '<fmt:message key="operator.list.table.info"/>',
			"infoEmpty" : '<fmt:message key="operator.list.table.info"/>',
			"paginate" : {
				"previous" : '<fmt:message key="operator.list.table.paginate.previous"/>',
				"next" : '<fmt:message key="operator.list.table.paginate.next"/>'
			}
		},
		"bStateSave": true,
		"ajax" : {
			"url" : "search",
			"type" : "POST",
			"data" : function(dataTableRequest) {
				console.log(dataTableRequest);
				var statusOperator = '';
				if (typeof status === 'undefined')
					statusOperator = $('#statusSort').val();
				else
					statusOperator = status;
				
				var criteriaOperator = '';
				if (typeof criteria === 'undefined')
					criteriaOperator = $('#criteriaSearch').val();
				else
					criteriaOperator = criteria;
				
				var userOperator = '';
				if (typeof user === 'undefined')
					userOperator = $('#dateSort').val();
				else
					userOperator = user;				
				dataTableRequest.criteriaSearch = criteriaOperator;
				dataTableRequest.status = statusOperator;
				dataTableRequest.userId = userOperator;
				return dataTableRequest;
			},
			"dataSrc" : "data",
			"error" : function(xhr) {
				alert('error datatable')
			}
		},
		"columns" : [
				 {
					"data" : "cardIndexNo",
						"createdCell" : function(
								td,
								cellData,
								rowData,
								row,
								col) {
							if (cellData) {
								$(td).html("</a><input type='hidden' id='cardId' value='"+rowData.cardId+"'/>" +cellData );
							}
						}
				}, 
				{
					"data" : "imageFile",
					"createdCell" : function(
							td, cellData,
							rowData, row,
							col) {
						if (cellData) {
							$(td).html("<a href='#'><img src='data:image/png;base64,"+cellData+"' width='90' /></a>");
						}
					}
				},
				{
					"data" : "name"
				},
				{
					"data" : "companyName"
				},
				{
					"data" : "positionName"
				},
				{
					"data" : "email"
				},
				{
					"data" : "mobileNumber"
				},
				{
					"data" : "createDate"
				}
				,
				<c:if test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
				{
					"data" : "approvalStatus",
					"createdCell" : function(
							td, cellData,
							rowData, row,
							col) {
						if (cellData) {
							$(td).html(getStatus(cellData));
						}
					}
				},
				</c:if>
				{
					"data" : "is_editting",
					"className" : "hidden-column"
				},
				{
					"data" : "cardId",
					"className" : "ch-color-link",
					"createdCell" : function(
							td, cellData,
							rowData, row,
							col) {
						if (cellData) {
							
							<c:choose>
						    <c:when test="${pageContext.request.isUserInRole('ROLE_OPERATOR')}">
						        $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
						    </c:when>
						    <c:when test="${pageContext.request.isUserInRole('ROLE_LEADER')}">
					           $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
					        </c:when>
						    <c:otherwise>
						         $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> "
									+ "<a class='ch-del'><fmt:message key = 'operator.list.delete'/></a>");
						       
						    </c:otherwise>
						  </c:choose>
							
						}
					}
				}, ],
			});
	return dataTables;
}
function loadDataIsEditting(){
	$('#listCard').find("tr").each(function(){
		if ($(this).attr("class") != undefined && ($(this).attr("class") == "odd" || $(this).attr("class") == "even")) {
			var is_editting_column=$(this).find(".hidden-column");
			if(is_editting_column.text()=="1"){
				var status= is_editting_column.prev();
				if(status.text()=="<fmt:message key = 'card.status.inputWating'/>"){
					status.text("<fmt:message key = 'card.status.inputWatingEditting'/>");
					
				}else if(status.text()=="<fmt:message key = 'card.status.editWating'/>"){
					status.text("<fmt:message key = 'card.status.editWatingEditting'/>");
					
				}else if(status.text()=="<fmt:message key = 'card.status.approvedPending'/>"){
					status.text("<fmt:message key = 'card.status.approvedPendingEditting'/>");
					
				}
				var is_role="${pageContext.request.isUserInRole('ROLE_SUPERVISOR')}";
				//$(this).find(".ch-edit").next().remove();
				if(is_role!="true"){
					$(this).find(".ch-edit").remove();
				}
					
				
			}
		}
	});
}

function getStatus(k) {
	var status = new Object();
	status['approved'] = "<fmt:message key = 'card.status.approved'/>";
	status['inputWating'] = "<fmt:message key = 'card.status.inputWating'/>";
	status['editWating'] = "<fmt:message key = 'card.status.editWating'/>";
	status['approvedPending'] = "<fmt:message key = 'card.status.approvedPending'/>";
	status['recognitioning'] = "<fmt:message key = 'card.status.recognitioning'/>";
	return status[k];
}

function loadDataComplete() {
	$('#listCard').find("tr").each(function() {
						if ($(this).attr("class") != undefined && ($(this).attr("class") == "odd" || $(this).attr("class") == "even")) {
							var href = $(this).find("td").last().find('a').first().attr("href");
							var imageTd = $(this).children('td').eq(1);
							imageTd.find("a").attr("href", href);
							//https://livepass.backlog.jp/view/MEISHI-788
							var is_editting_column=$(this).find(".hidden-column");
							if(is_editting_column.text()=="1"){
								var is_role="${pageContext.request.isUserInRole('ROLE_SUPERVISOR')}";
								if(is_role!="true"){
									imageTd.find("a").removeAttr("href");
								}
									
								
							}
						}
					});
	//https://livepass.backlog.jp/view/MEISHI-788
	loadDataIsEditting();
}

	$(document).ready(function() {
						var status = sessionStorage.getItem('statusSearchOperator') || "";
						$('#statusSort').val(status);
						var user = sessionStorage.getItem('userSearchOperator') || 0;
						$('#dateSort').val(user);
						var criteria = sessionStorage.getItem('criteriaSearchOperator');
						$('#criteriaSearch').val(criteria);
						dataTables = initDatatables(status, user, criteria);
						
						$("#criteriaSearch").keyup(function (e) {
							  if (e.which == 13) {
								  $('#searchCard').trigger('click');
							  }
						 });

						$('#searchCard').on('click',function() {
							dataTables.api().destroy();
							dataTables = $('#listCard').dataTable({
												"fnDrawCallback" : function() {
												loadDataComplete();
											},
											"dom" : '<<t>ip>',
											"iDisplayLength" : 5,
											"processing" : true,
											"serverSide" : true,
											"searching" : false,
											"ordering" : false,
											"language" : {
												"zeroRecords" : '<fmt:message key="operator.list.table.emptyTable"/>',
												"emptyTable" : '<fmt:message key="operator.list.table.emptyTable"/>',
												"info" : '<fmt:message key="operator.list.table.info"/>',
												"infoEmpty" : '<fmt:message key="operator.list.table.info"/>',
												"paginate" : {
													"previous" : '<fmt:message key="operator.list.table.paginate.previous"/>',
													"next" : '<fmt:message key="operator.list.table.paginate.next"/>'
												}
											},
											"ajax" : {
												"url" : "search",
												"type" : "POST",
												"data" : function(dataTableRequest) {
													dataTableRequest.criteriaSearch = $('#criteriaSearch').val();
													dataTableRequest.status = $('#statusSort').val();
													dataTableRequest.userId = $('#dateSort').val();
													return dataTableRequest;
												},
												"dataSrc" : "data",
												"error" : function(
														xhr) {
													alert('error datatable')
												}
											},
											"columns" : [
												   {
													"data" : "cardIndexNo",
													"createdCell" : function(td,cellData,rowData,row,col) {
														if (cellData) {
															$(td).html("</a><input type='hidden' id='cardId' value='"+rowData.cardId+"'/>" +cellData );
														}
													 }
													},
													{
														"data" : "imageFile",
														"createdCell" : function(td,cellData,rowData,row,col) {
															if (cellData) {
																$(td).html("<a href='#'><img src='data:image/png;base64,"+cellData+"' width='90' /></a>");
															}
														}
													},
													{
														"data" : "name"
													},
													{
														"data" : "companyName"
													},
													{
														"data" : "positionName"
													},
													{
														"data" : "email"
													},
													{
														"data" : "mobileNumber"
													},
													{
														"data" : "createDate"
													},
													<c:if test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
													{
														"data" : "approvalStatus",
														"createdCell" : function(td,cellData,rowData,row,col) {
															if (cellData) {
																$(td).html(getStatus(cellData));
															}
														}
													},
													</c:if>
													{
														"data" : "is_editting",
														"className" : "hidden-column"
													},
													{
														"data" : "cardId",
														"className" : "ch-color-link",
														"createdCell" : function(td,cellData,rowData,row,col) {
															if (cellData) {
																<c:choose>
															    <c:when test="${pageContext.request.isUserInRole('ROLE_OPERATOR')}">
															    $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
															    </c:when>
															    <c:when test="${pageContext.request.isUserInRole('ROLE_LEADER')}">
														           $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
														        </c:when>
															    <c:otherwise>
															    $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> "
																		+ "<a class='ch-del'><fmt:message key = 'operator.list.delete'/></a>");
															    </c:otherwise>
															  </c:choose>
															}
														}
													}, ],
										});
						});

						$(document).on('click','a.ch-del',function() {
							if (confirm('<fmt:message key="card.list.confirmDelete"/>')) {
								if(confirm('<fmt:message key="operator.list.confirmDeleteAgain"/>')){
									var cardId = $(this).parent().parent().find('td input[id=cardId]').val();
									var target = $(this).closest('tr');
									$.ajax({
										type : 'POST',
										url : 'delete',
										cache : false,
										data : 'cardId='+ cardId
									}).done(function(resp,status,	xhr) {
										if (resp == 1) {
											dataTables.fnDeleteRow(target.prop('id'));
										      var currentPage = $('.current', '#listCard_paginate').text()
										      $('#listCard_paginate').find('.paginate_button').each(function () {
										       if ($(this).text() === currentPage)
										         $(this).click();
										      });
										} else {
											alert('Error');
										}
									}).fail(function(resp,status,xhr) {
										alert('Error');
									});
								}														
								
							}
						});
						
						$(document).on('click','a.ch-edit',function() {
							sessionStorage.setItem('statusSearchOperator', $('#statusSort').val());
							sessionStorage.setItem('userSearchOperator', $('#dateSort').val());
							sessionStorage.setItem('criteriaSearchOperator', $('#criteriaSearch').val());
							//sessionStorage.setItem('currentPageSearchOperator', $('.current', '#listCard_paginate').text());
						});
						
						$(document).on('click','a.paginate_button',function() {
							sessionStorage.removeItem('currentPageSearchOperator');
						})

						$('#statusSort').on('change', function() {
							var status = $(this).val();
							//$('#searchCard').click();
							dataTables.api().destroy();
							dataTables = $('#listCard').dataTable({
									"fnDrawCallback" : function() {
										loadDataComplete();
									},
									"dom" : '<<t>ip>',
									"iDisplayLength" : 5,
									"processing" : true,
									"serverSide" : true,
									"searching" : false,
									"ordering" : false,
									"language" : {
										"zeroRecords" : '<fmt:message key="operator.list.table.emptyTable"/>',
										"emptyTable" : '<fmt:message key="operator.list.table.emptyTable"/>',
										"info" : '<fmt:message key="operator.list.table.info"/>',
										"infoEmpty" : '<fmt:message key="operator.list.table.info"/>',
										"paginate" : {
											"previous" : '<fmt:message key="operator.list.table.paginate.previous"/>',
											"next" : '<fmt:message key="operator.list.table.paginate.next"/>'
										}
									},
									"ajax" : {
										"url" : "search",
										"type" : "POST",
										"data" : function(dataTableRequest) {
											dataTableRequest.criteriaSearch = $('#criteriaSearch').val();
											dataTableRequest.status = $('#statusSort').val();
											dataTableRequest.userId = $('#dateSort').val();
											return dataTableRequest;
										},
										"dataSrc" : "data",
										"error" : function(xhr) {
											alert('error datatable')
										}
									},
									"columns" : [
										   {
												"data" : "cardIndexNo",
												"createdCell" : function(td,cellData,rowData,row,col) {
													if (cellData) {
														$(td).html("</a><input type='hidden' id='cardId' value='"+rowData.cardId+"'/>" +cellData );
													}
												}
											},
											{
												"data" : "imageFile",
												"createdCell" : function(td,cellData,rowData,row,col) {
													if (cellData) {
														$(td).html("<a href='#'><img src='data:image/png;base64,"+cellData+"' width='90' /></a>");
													}
												}
											},
											{
												"data" : "name"
											},
											{
												"data" : "companyName"
											},
											{
												"data" : "positionName"
											},
											{
												"data" : "email"
											},
											{
												"data" : "mobileNumber"
											},
											{
												"data" : "createDate"
											},
											<c:if test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
											{
												"data" : "approvalStatus",
												"createdCell" : function(td,cellData,rowData,row,col) {
													if (cellData) {
														$(td).html(getStatus(cellData));
													}
												}
											},
											</c:if>
											{
												"data" : "is_editting",
												"className" : "hidden-column"
											},
											{
												"data" : "cardId",
												"className" : "ch-color-link",
												"createdCell" : function(td,cellData,rowData,row,col) {
													if (cellData) {
														<c:choose>
													    <c:when test="${pageContext.request.isUserInRole('ROLE_OPERATOR')}">
													    $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
													    </c:when>
													    <c:when test="${pageContext.request.isUserInRole('ROLE_LEADER')}">
												           $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
												        </c:when>
													    <c:otherwise>
													    $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> "
																+ "<a class='ch-del'><fmt:message key = 'operator.list.delete'/></a>");
													    </c:otherwise>
													  </c:choose>
													}
												}
											}, ],
								});
							
							/*$('a .ch-edit').on('click', function() {
								console.log('111111111')
								sessionStorage.setItem('status', $('#statusSort').val());
							});*/
							
						});
						
						$('#dateSort').on('change', function() {
							var status = $(this).val();
							
							dataTables.api().destroy();
							dataTables = $('#listCard').dataTable({
												"fnDrawCallback" : function() {
													loadDataComplete();
												},
												"dom" : '<<t>ip>',
												"iDisplayLength" : 5,
												"processing" : true,
												"serverSide" : true,
												"searching" : false,
												"ordering" : false,
												"language" : {
													"zeroRecords" : '<fmt:message key="operator.list.table.emptyTable"/>',
													"emptyTable" : '<fmt:message key="operator.list.table.emptyTable"/>',
													"info" : '<fmt:message key="operator.list.table.info"/>',
													"infoEmpty" : '<fmt:message key="operator.list.table.info"/>',
													"paginate" : {
														"previous" : '<fmt:message key="operator.list.table.paginate.previous"/>',
														"next" : '<fmt:message key="operator.list.table.paginate.next"/>'
													}
												},
												"ajax" : {
													"url" : "search",
													"type" : "POST",
													"data" : function(dataTableRequest) {
														dataTableRequest.criteriaSearch = $('#criteriaSearch').val();
														dataTableRequest.status = $('#statusSort').val();
														dataTableRequest.userId = $('#dateSort').val();
														return dataTableRequest;
													},
													"dataSrc" : "data",
													"error" : function(xhr) {
														alert('error datatable')
													}
												},
												"columns" : [
													   {
															"data" : "cardIndexNo",
															"createdCell" : function(td,cellData,rowData,row,col) {
																if (cellData) {
																	$(td).html("</a><input type='hidden' id='cardId' value='"+rowData.cardId+"'/>" +cellData );
																}
															}
														},
														{
															"data" : "imageFile",
															"createdCell" : function(td,cellData,rowData,row,col) {
																if (cellData) {
																	$(td).html("<a href='#'><img src='data:image/png;base64,"+cellData+"' width='90' /></a>");
																}
															}
														},
														{
															"data" : "name"
														},
														{
															"data" : "companyName"
														},
														{
															"data" : "positionName"
														},
														{
															"data" : "email"
														},
														{
															"data" : "mobileNumber"
														},
														{
															"data" : "createDate"
														},
														<c:if test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
														{
															"data" : "approvalStatus",
															"createdCell" : function(td,cellData,rowData,row,col) {
																if (cellData) {
																	$(td).html(getStatus(cellData));
																}
															}
														},
														</c:if>
														{
															"data" : "is_editting",
															"className" : "hidden-column"
														},
														{
															"data" : "cardId",
															"className" : "ch-color-link",
															"createdCell" : function(td,cellData,rowData,row,col) {
																if (cellData) {
																	<c:choose>
																    <c:when test="${pageContext.request.isUserInRole('ROLE_OPERATOR')}">
																    $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
																    </c:when>
																    <c:when test="${pageContext.request.isUserInRole('ROLE_LEADER')}">
															           $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> ");
															        </c:when>
																    <c:otherwise>
																    $(td).html( "<a href='<c:url value='/cards/edit/"+cellData+"'/>' class='ch-edit'><fmt:message key='operator.list.edit' /></a> "
																			+ "<a class='ch-del'><fmt:message key = 'operator.list.delete'/></a>");
																    </c:otherwise>
																  </c:choose>
																}
															}
														}, ],
											});
						
						//$('#searchCard').click();
						
							
						});
						
						if ('${cardSearchVO.status}')
							$('#statusSort').val('${cardSearchVO.status}');	
					
					});
</script>
<!-- BODY -->
<!-- BODY -->

<div class="container-fluid padding-top20 bg-container height100per">
	<!-- LEFT SIDE -->
	<div id="left-side" class="col-sm-2">
		<!-- DATE SORT -->
		<div class="row box-shadow bg-white">
			<form>
				<!-- <select name="dateSort" id="dateSort" class="select-style">
					<option value="0">Date sort</option>
				</select> -->
				<select name="statusSort" id="statusSort" class="select-style">
					<c:choose>
						<c:when
							test="${pageContext.request.isUserInRole('ROLE_SUPERVISOR')}">
							<option value="" selected="selected">すべて</option>
							<option value="2">データ入力待ち</option>
							<option value="3">修正待ち</option>
							<option value="4">承認待ち</option>
							<option value="1">承認済み</option>
						</c:when>
						<c:when test="${pageContext.request.isUserInRole('ROLE_LEADER')}">
							<option value="2">データ入力待ち</option>
							<option value="3">修正待ち</option>
							<option value="4" selected="selected">承認待ち</option>
						</c:when>
						<c:when
							test="${pageContext.request.isUserInRole('ROLE_OPERATOR')}">
							<option value="2" selected="selected">データ入力待ち</option>
							<option value="3">修正待ち</option>
						</c:when>
					</c:choose>
				</select> <select name="dateSort" id="dateSort" class="select-style">
					<c:choose>
						<c:when
							test="${pageContext.request.isUserInRole('ROLE_OPERATOR')}">
							<c:forEach var="teamMember" items="${listUser}" varStatus="loop">
								<c:if test="${teamMember.userId == userId}">
									<option selected="selected"
										value='<c:out value="${teamMember.userId}" />'><c:out
											value="${teamMember.name}" /></option>
								</c:if>
								<c:if test="${teamMember.userId != userId}">
									<option value='<c:out value="${teamMember.userId}" />'><c:out
											value="${teamMember.name}" /></option>
								</c:if>
							</c:forEach>
						</c:when>
						<c:when
							test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
							<option selected="selected" value='0'>メンバー一覧</option>
							<c:forEach var="teamMember" items="${listUser}" varStatus="loop">
								<option value='<c:out value="${teamMember.userId}" />'><c:out
										value="${teamMember.name}" /></option>
								<%-- <c:if test="${teamMember.userId == userId}">
									<option selected="selected" value='<c:out value="${teamMember.userId}" />'><c:out value="${teamMember.name}" /></option>
								</c:if>
							    <c:if test="${teamMember.userId != userId}">
									<option value='<c:out value="${teamMember.userId}" />'><c:out value="${teamMember.name}" /></option>
								</c:if> --%>
							</c:forEach>
						</c:when>
					</c:choose>
				</select>

			</form>
		</div>
		<!-- END DATE SORT -->
		<!-- TAG GROUP -->
		<div class="row box-shadow bg-white box-marginTop5">
			<h4 class="h4-line">スキャン</h4>
			<div class="row">
				<div class="col-sm-12">
					<ul class="list-li">
						<li><a href="<c:url value='/cards/scancard'/>"
							id="manage_card"><i class="fa fa-print"></i>スキャンカード</a></li>
					</ul>
				</div>
			</div>
		</div>
		<!-- END TAG GROUP -->
	</div>
	<!-- END LEFT SIDE -->
	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-10">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header">
						<fmt:message key="card.list.title" />
					</h4>
				</div>

				<div class="float-right"></div>
			</div>
		</div>
		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
			<!-- <form> -->
			<div class="col-sm-12">
				<div class="input-group">
					<c:if
						test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
						<input type="text" class="form-control"
							value="${cardSearchVO.criteriaSearch}" id="criteriaSearch"
							placeholder="名前、会社名、電話番号など入力してください...">
						<span class="input-group-btn">
							<button type="button" id="searchCard" class="btn btn-primary">検索</button>
						</span>
					</c:if>
				</div>
			</div>
			<!-- </form> -->
			<!-- END SEARCH -->
			<!-- DATA TABLE -->
			<div class="col-sm-12 container">
				<div class="row" id="data-table">
					<div class="ibox-content">
						<div id="paging_wrapper" class="dataTables_wrapper no-footer">
							<table class="table dataTable no-footer" id="listCard"
								role="grid" aria-describedby="paging_info">
								<thead>
									<tr role="row">
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 105px;">カード ID</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 133px;">画像</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 59px;">名前</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 79px;">会社名</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 59px;">役割</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 141px;">Eメール</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 112px;">電話番号</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 93px;">作成日</th>
										<c:if
											test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
											<th class="sorting_disabled" rowspan="1" colspan="1"
												style="width: 93px;">ステータス</th>
										</c:if>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 93px; display: none">Is Editting</th>
										<th class="sorting_disabled" rowspan="1" colspan="1"
											style="width: 163px;">アクション</th>
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
</div>