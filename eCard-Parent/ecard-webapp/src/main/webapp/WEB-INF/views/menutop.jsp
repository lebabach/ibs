<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<div id="content-side" class="col-sm-10">
	<!-- BAR TOP -->
	<div class="row bg-white box-shadow menu-top menu-top-header">
		<div class="col-sm-12">
			<div class="float-left">
				<h4 class="h4-header" id="content-header">
					<fmt:message key="menu.headingSystem" />
				</h4>
			</div>

			<div class="float-right">
				<%-- <c:if test="${not pageContext.request.isUserInRole('ROLE_OPERATOR')}">
					<a href="<c:url value='/user/home'/>" data-toggle="tooltip"
						title="View as User"><i class="fa fa-user icon-rounded"></i></a>
				</c:if> --%>
			</div>

		</div>
	</div>

	<!-- END BAR TOP -->
	<div
		class="row bg-white box-shadow box-marginTop5 padding-top-bottom menu-top">
		<!-- progress bar screen -->
		<div class="container container-progress" style="display: block;height:500px;">
		<c:if test="${pageContext.request.isUserInRole('ROLE_LEADER') or pageContext.request.isUserInRole('ROLE_OPERATOR') or pageContext.request.isUserInRole('ROLE_SUPERVISOR') or pageContext.request.isUserInRole('ROLE_ADMIN') }">
				<div class="row ">
					<div id="carousel" class="carousel col-md-10 ">
						<c:forEach var="teamInfo" items="${teamDisInfos}">
							<div class="item one">
								<div class="item_head one_1">
									<p class="wrap_menutop" title="${teamInfo.teamName}">
										<c:out value="チーム ${teamInfo.teamName}" />
									</p>
								</div>
								<div class="progress-content">
									<p>
										目標入力数 <span><c:out value="${teamInfo.processNumber}" /></span>
									</p>
									<p>
										入力数 <span><c:out value="${teamInfo.memberNumber}" /></span>
									</p>
									<p>
										進捗状況 <span><c:out value="${teamInfo.stateProgress}%" /></span>
									</p>
								</div>
							</div>
						</c:forEach>
					</div>
	
				</div>
			</c:if>
		</div>
		<!-- END PROGRESS BAR SCREEN -->
		<!-- BENGIN BACKUP RESTORE SCREEN -->


		<!-- BENGIN EXPORT CSV SCREEN -->
		<div class="container container-left export-csv "
			style="display: none;">
			<div class="wrapper-log">
				<!-- BEGIN SEARCH -->
				<div class="row clearfix export-csv header-content">
					<div class="col-md-6 col-md-offset-3">Export card</div>
				</div>
				<!-- END SEARCH -->
				<div class="row clearfix export-input">
					<div class="col-md-6 col-md-offset-3 column">
						<div class="input-group  col-sm-10">
							<!--  <input type="file" name="file"  style="display:none;" id="file_upload"/> -->
							<input type="file" name="file" class="form-control upload_btn"
								id="input_file_none"> <input type="text"
								class="form-control upload_btn" id="input_file">

						</div>
					</div>
				</div>
				<!-- END SEARCH -->
				<div class="row clearfix">
					<div class="col-md-6 col-md-offset-7 column">
						<div class="input-group search-log-input-text">
							<button type="button" class="btn btn-primary">Export</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- END EXPORT CSV SCREEN -->
		<!-- BENGIN DOWNLOAD PERMISSION -->
		<div class="container container-left download-permission "
			style="display: none;">
			<form class="form-horizontal">
				<fieldset>
					<div class="form-group">
						<div class="col-xs-10 col-xs-offset-3">
							<label for="fistname" class="control-label col-xs-10 ">ダウンロード申請</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-12 col-xs-offset-3">
							<label for="fistname" class="control-label col-xs-12 ">承認待ち：会社情報ダウンロード申請5件、全社情報ダウンロード2件</label>
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6 col-xs-offset-3">
							<input type="text" class="form-control" id="title-of-honour"
								maxlength="40" size="40" placeholder="会社情報ダウンロード申請">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6 col-xs-offset-3">
							<input type="text" class="form-control" id="lastname"
								maxlength="40" size="40" placeholder="桜井幸子　スーバーバイザー">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6 col-xs-offset-3">
							<input type="text" class="form-control" id="title-of-honour"
								maxlength="40" size="40" placeholder="山口　百恵リーダー">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6 col-xs-offset-3">
							<input type="text" class="form-control" id="lastname"
								maxlength="40" size="40" placeholder="山崎　沙樹">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6 col-xs-offset-3">
							<input type="text" class="form-control" id="title-of-honour"
								maxlength="40" size="40" placeholder="雪の下有紀">
						</div>
					</div>
					<div class="form-group">
						<div class="col-xs-6 col-xs-offset-3">
							<input type="text" class="form-control" id="lastname"
								maxlength="40" size="40" placeholder="由比ヶ浜衣">
						</div>
					</div>
					<div class="form-group">
						<label for="fistname"
							class="control-label col-xs-2 col-xs-offset-3">部署名</label>
						<div class="col-xs-4 ">
							<input type="text" class="form-control" id="fistname"
								maxlength="40" size="40">
						</div>
					</div>
					<div class="form-group">
						<label for="fistname"
							class="control-label col-xs-2  col-xs-offset-3">名称</label>
						<div class="col-xs-4">
							<input type="text" class="form-control" id="fistname"
								maxlength="40" size="40">
						</div>
					</div>
					<div class="form-group ">
						<div class="col-xs-2 col-xs-offset-4">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">承認</button>
						</div>
						<div class="col-xs-2 ">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">非承認</button>
						</div>
					</div>


				</fieldset>
			</form>
		</div>
		<!-- END DOWNLOAD PERMISSION -->
	</div>
	<div class="buildVersion">
		<p>
			<fmt:message key='build.version' />
		</p>
	</div>

	<script src="<c:url value='/assets/js/jquery-2.1.1.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/js/bootstrap.min.js'/>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/assets/js/plugins/slimscroll/jquery.slimscroll.min.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/js/plugins/iCheck/icheck.min.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/s/plugins/pace/pace.min.js'/>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/assets/js/plugins/peity/jquery.peity.min.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/js/demo/peity-demo.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/js/jquery.dataTables.min.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/js/slick.min.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/js/clockpicker.js'/>"
		type="text/javascript"></script>
	<script src="<c:url value='/assets/js/admin.js'/>"
		type="text/javascript"></script>