<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.height100per {
	height: 100%;
}
</style>
<script type="text/javascript">
	$(document).ready(function() {
		$("#criteriaSearch").keyup(function(e) {
			if (e.which == 13) {
				$('.btn_search').trigger('click');
			}
		});
		
		$("#actionLogDownload").click(function(e) {
			document.location.href = "<c:url value='/logs/actionlogdownload'/>";

		});
		
		$("#updateHistoryDownload").click(function(e) {
			document.location.href = "<c:url value='/logs/updatecarddownload'/>";
		});
		
	})
	
	/* $(document).on('click', "#actionLogDownload", function() {
		document.location.href = "<c:url value='/logs/actionlogdownload'/>";
	});
	
	$(document).on('click', "#updateHistoryDownload", function() {
		document.location.href ="<c:url value='/logs/updatecarddownload'/>";
	}); */
	

	
</script>
<!-- BODY -->

<!-- RIGHT SIDE -->
<div id="right-side" class="col-sm-12">
	<!-- BAR TOP -->
	<div class="row bg-white box-shadow menu-top-header">
		<div class="col-sm-12">
			<div class="float-left">
				<h4 class="h4-header">
					<fmt:message key="log.header" />
				</h4>
			</div>

		</div>
	</div>

	<!-- END BAR TOP -->
	<div class="row bg-white  box-marginTop5 padding-top-bottom">
		<div class="container container-left confirm-log">
			<div class="wrapper-log">
				<div class="row clearfix" style="margin-bottom: 20px">
						<div class="col-md-2 col-md-offset-3">
							<button id="actionLogDownload" class="btn btn-primary btn_search">
								<fmt:message key="operator.list.actionlog.download" />
							</button>
						</div>
						<div class="col-md-2 col-md-offset-2">
							<button id="updateHistoryDownload"
								class="btn btn-primary btn_search">
								<fmt:message key="operator.list.updatehistory.download" />
							</button>
						</div>
					</div>
				<!-- BEGIN SEARCH -->
				<form action="listlog" method="post" id="formSearchLog">
					
					<div class="row clearfix search-log header-content">
						<div class="col-md-6 col-md-offset-3">
							<div class="input-group search-log-input-text">
								<input type="text" class="form-control search_log"
									id="criteriaSearch" name="criteriaSearch"
									placeholder="Enter content search......"> <span
									class="input-group-btn">
									<button type="submit" class="btn btn-primary btn_search">
										<fmt:message key="operator.list.search" />
									</button>
								</span>
							</div>
						</div>
					</div>
				</form>
				<!-- END SEARCH -->
				<div class="row clearfix">
					<div class="col-md-6 col-md-offset-3 column">
						<div class="form-group search-log-input-textarea"
							style="height: 500px; overflow: overlay;">
							<table class="table">
								<thead>
									<tr style="background: #c3c3c3;">
										<th><fmt:message key="log.date" /></th>
										<th><fmt:message key="log.content" /></th>

									</tr>
								</thead>
								<tbody class="content_user">
									<c:forEach var="listlog" items="${listLogEventVo}">
										<tr>
											<td><fmt:formatDate value='${listlog.actionDate}'
													pattern="yyyy年 MM月dd日" /></td>
											<td class="content_log"><c:out
													value="${listlog.actionMessage}" /></td>

										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END DATA TABLE -->
	</div>
	<!-- BAR BODY -->
</div>
<!-- END BODY -->


