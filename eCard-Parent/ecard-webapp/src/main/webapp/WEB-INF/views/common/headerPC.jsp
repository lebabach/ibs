<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.btn-lg {
	padding: 2px 16px !important;
}

.form-group {
	margin-bottom: 0 !important;
}

.clearfix {
	margin-bottom: 4px !important;
}

.navbar-right {
	margin-right: -40px !important;
}

.clearfix a.active {
	font-weight: bold !important;
}

.navbar-left {
	float: left !important;
	margin-top: 45px !important;
}

.navbar-left li {
	position: relative !important;
	margin-left: 20px !important;
}

.navbar-top-links .dropdown-menu li {
	margin-left: 0 !important;
}

.navbar-left li a {
	padding: 0 !important;
	min-height: inherit !important;
}

.navbar-left li a .label {
	line-height: 12px !important;
	padding: 2px 5px !important;
	position: absolute !important;
	right: -3px !important;
	top: -8px !important;
}

.navbar-static-top {
	margin-bottom: 0 !important;
	float: right !important;
	background: none !important;
	position: absolute !important;
	right: -15px !important;
	bottom: -7px !important;
}

.searchTargetSwitcher {
	background: #fff !important;
	display: block !important;
	padding-bottom: 5px !important;
}

.list-group-item-title {
	background: -moz-linear-gradient(center top, #f4f4f4, #e6e6e6) repeat
		scroll 0 0 rgba(0, 0, 0, 0) !important;
	border: 1px solid #b1b1b1 !important;
	border-radius: 4px 4px 0 0 !important;
	box-shadow: 0 1px 1px #fff inset !important;
	font-weight: bold !important;
	padding: 14px 30px 10px !important;
	text-align: left !important;
	color: #666 !important;
	font-weight: bold !important;
	font-family: "メイリオ", Meiryo, "ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro",
		"ＭＳ Ｐゴシック", "MS PGothic", sans-serif !important;
}

.list-group-item {
	background: #fff !important;
	border: 1px solid #b1b1b1 !important;
}
</style>
<div class="row border-bottom"
	style="text-align: center; background: #fff;">
	<div
		style="display: inline-block; width: 770px; margin: 0 auto; padding: 0; position: relative;">
		<a href=""><img src="<c:url value='/assets/img/login_logo_pc.png'/>" style="float: left; width: 80px; margin: 5px 0;"></a>


		<ul class="nav navbar-top-links navbar-left">
			<li class="dropdown"><a class="dropdown-toggle count-info"
				data-toggle="dropdown" href="#"> <i class="fa fa-flag"></i> <span
					class="label label-warning">1</span>
			</a>
				<ul class="dropdown-menu notification dropdown-messages "
					style="padding: 0">
					<li>
						<div class="dropdown-messages-box">
							<p style="padding: 10px 10px 0 10px;">アップデート</p>
						</div>
					</li>
					<li>
						<table class="table table-hover" id="notification">

							<tbody>
								<tr class="pointer" id="4575476666">
									<td width="30%" style="vertical-align: middle"><img
										alt="image" style="width: 100%" src="img/businesscard-1.png"></td>
									<td width="60%" style="vertical-align: middle">
										<div class="content_notice">Mark111111111333333333333333333333333333333333333333333333</div>
										<div class="date">3日前</div>
									</td>
									<td width="10%" style="vertical-align: middle"><a href="#"
										class=""><i class="fa fa-angle-right"></i></a></td>
								</tr>
								<tr class="pointer" id="5654686866">
									<td style="vertical-align: middle"><img alt="image"
										style="width: 100%" src="img/businesscard-1.png"></td>
									<td style="vertical-align: middle">
										<div class="content_notice">2345555</div>
										<div class="date">3日前</div>
									</td>
									<td style="vertical-align: middle"><a href="#" class=""><i
											class="fa fa-angle-right"></i></a></td>
								</tr>
							</tbody>
						</table> <!-- </div> -->
					</li>
				</ul></li>
		</ul>
		<nav class="navbar navbar-static-top" role="navigation"
			style="margin-bottom: 0; float: right;">
			<ul class="nav navbar-top-links navbar-right">

				<li>
					<p>${pageContext.request.remoteUser}</p>
				</li>

				<li>
					<!-- <a href="login.html">
                      <i class="fa fa-sign-out"></i> ログアウト
                  </a> --> <a data-toggle="dropdown"
					class="dropdown-toggle" href="#"> <i class="fa fa-user"><b
							class="caret"></b></i>
				</a>
					<ul class="dropdown-menu animated fadeInRight m-t-xs">
						<li><a href="profile">プロフィール名刺</a></li>
						<li><a href="download.html">分の名刺をダウン</a></li>
						<li><a href="contact">ロード -パスワード 設定</a></li>
						<li><a href="faq">FAQ</a></li>
						<li><a href="mailbox">ご意見・不具合の連絡</a></li>
						<!-- Profile -->
						<li class="divider"></li>
						<li><a href="<c:url value='/j_spring_security_logout'/>">ログアウト</a></li>						
					</ul>
				</li>
			</ul>
		</nav>
	</div>
</div>