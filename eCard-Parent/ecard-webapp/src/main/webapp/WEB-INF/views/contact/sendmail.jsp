
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid padding-top20 bg-container height100per">

		<!-- RIGHT SIDE -->
		<div id="right-side" class="col-sm-12">
			<!-- BAR TOP -->
			<div class="row bg-white box-shadow menu-top-header col-sm-12">
				<div class="col-sm-12">
					<div class="float-left">
						<h4 class="h4-header">メール送信</h4>
					</div>

					<div class="float-right float-right-manage"></div>
				</div>
			</div>

			<!-- END BAR TOP -->
			<div
				class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
				<div class="container col-sm-8 col-xs-offset-2" id="data-table">
					<div class="container">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="control-label col-sm-1" for="email">タイトル</label>
								<div class="col-sm-4">
									<input type="email" class="form-control" id="email">

								</div>

								<div class="col-sm-4 col-xs-offset-1">
									<div class="input-group">
										<input type="text" class="form-control"
											placeholder="<fmt:message key="contact.sendmail.input.placeholder"/>">
										<span class="input-group-btn">
											<button type="button" class="btn btn-primary">
												<fmt:message key="contact.sendmail.button.search" />
											</button>
										</span>
									</div>
								</div>

							</div>
							<div class="form-group">
								<label class="control-label col-sm-1" for="content">メッセージ</label>
								<div class="col-sm-4">
									<textarea class="form-control" rows="5" id="comment"></textarea>
								</div>
								<div class="col-sm-4 col-xs-offset-1">
									<table class="table container">
										<tbody>
											<tr id="rowData">
												<td><input type="checkbox"
													class="i-checks i-checks-chk_all" name="chkALL" id="chkAll">
													<a href="#"> Abc@gmail.com</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-1 col-sm-6">
									<div class="checkbox">
										<label><input type="checkbox"
											class="i-checks i-checks-chk_all" name="chkALL" id="chkAll">
											全ユーザに送信</label>
									</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-2">
									<button type="submit" class="btn btn-primary">送信＆保存</button>
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