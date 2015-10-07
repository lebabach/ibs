<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
.btn-lg {
	padding: 2px 16px;
}

.form-group {
	margin-bottom: 0;
}

.clearfix {
	margin-bottom: 4px;
}

.navbar-right {
	margin-right: -40px;
}

.clearfix a.active {
	font-weight: bold;
}

.navbar-left {
	float: left;
	margin-top: 45px;
}

.navbar-left li {
	position: relative;
	margin-left: 20px;
}

.navbar-top-links .dropdown-menu li {
	margin-left: 0;
}

.navbar-left li a {
	padding: 0;
	min-height: inherit;
}

.navbar-left li a .label {
	line-height: 12px;
	padding: 2px 5px;
	position: absolute;
	right: -3px;
	top: -8px;
}

.navbar-static-top {
	margin-bottom: 0;
	float: right;
	background: none;
	position: absolute;
	right: -15px;
	bottom: -13px;
}

.a-new-pc {
	float: right;
	border: 2px solid #000;
	text-align: center;
	padding: 5px 10px;
	color: #000;
}

.btn_back img {
	width: 12px;
	margin-right: 6px;
}

.content_notice {
	max-width: 171px;
}
/**/
.div-list {
	display: inline-block;
	margin: 30px auto 0 auto;
	text-align: center;
	width: 100%;
}

.list-profile {
	margin: 0;
	padding: 0;
	width: auto;
	display: block;
}

.list-profile li {
	border-bottom: 1px solid #b1b1b1;
	width: auto;
	display: block;
	padding-bottom: 5px;
	margin-bottom: 5px;
	text-align: left;
	list-style: none;
	padding: 0 30px 10px 30px;
	margin-bottom: 10px;
}

.list-profile li p {
	width: 100%;
	display: inline-block;
	margin: 0;
	padding: 0;
}

.list-profile li .p-1 {
	color: #555;
	font-size: 13px;
}

.list-profile li .p-2 {
	color: #000;
	font-size: 15px;
}

.box-1 {
	display: block;
	height: auto;
	margin: 0 auto;
	text-align: left;
	width: 800px;
}

.title-box-1 {
	background: -moz-linear-gradient(center top, #f4f4f4, #e6e6e6) repeat
		scroll 0 0 rgba(0, 0, 0, 0);
	border: 1px solid #b1b1b1;
	border-radius: 4px 4px 0 0;
	box-shadow: 0 1px 1px #fff inset;
	font-weight: bold;
	padding: 14px 30px 10px;
	text-align: left;
	color: #666;
	font-weight: bold;
	font-family: "メイリオ", Meiryo, "ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro",
		"ＭＳ Ｐゴシック", "MS PGothic", sans-serif !important;
}

.content-box-1 {
	background-color: #fff;
	border-bottom: 1px solid #b1b1b1;
	border-left: 1px solid #b1b1b1;
	border-radius: 0 0 4px 4px;
	border-right: 1px solid #b1b1b1;
	padding: 20px 0;
	text-align: center;
}
</style>


<div class="div-list">
	<div class="box-1">
		<div class="title-box-1">プロフィール名刺</div>
		<div class="content-box-1">
			<ul class="list-profile">
				<li>
					<p class="p-1">ä¼ç¤¾å</p>
					<p class="p-2">nhap test vao</p>
				</li>
				<li>
					<p class="p-1">é¨ç½²</p>
					<p class="p-2">CEO</p>
				</li>
				<li>
					<p class="p-1">å½¹è·</p>
					<p class="p-2">iOS</p>
				</li>
				<li>
					<p class="p-1">æ°å</p>
					<p class="p-2">Vidic phan thi</p>
				</li>
				<li style="border: none; padding-bottom: 0; margin-bottom: 0">
					<p class="p-1">ã¡ã¼ã«ã¢ãã¬ã¹</p>
					<p class="p-2">admin@test</p>
				</li>
			</ul>
		</div>
	</div>
</div>

<!-- Mainly scripts -->
<script src="js/jquery-2.1.1.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

<!-- Custom and plugin javascript -->
<script src="js/inspinia.js"></script>
<script src="js/plugins/pace/pace.min.js"></script>

<!-- Peity -->
<script src="js/plugins/peity/jquery.peity.min.js"></script>

<!-- Peity -->
<script src="js/demo/peity-demo.js"></script>

<!-- blueimp gallery -->
<script src="js/plugins/blueimp/jquery.blueimp-gallery.min.js"></script>
<script>
	$(document).ready(function() {
	});

	$('#delBusinessCard').click(function() {
		console.log('Delete a Personal detail card');
	});

	$('#editPersonalInfo').click(function() {
		console.log('Delete a Personal detail card 111111');
	});
	$(".more").toggle(function() {
		$(this).text("less..").siblings(".complete").show();
	}, function() {
		$(this).text("more..").siblings(".complete").hide();
	});

	$("#addTag").click(function(e) {
		if ($(".balloon").css('display') == 'block')
			$(".balloon").css("display", "none");
		else
			$(".balloon").css("display", "block");
	});

	$("#popup").on("click", function() {
		console.log('123');
		$('#imagepreview').attr('src', $('#imageresource').attr('src'));
		$('#imagemodal').modal('show');
	});
</script>