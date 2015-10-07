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
	padding: 0 60px 10px 60px;
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
	font-family: "ã¡ã¤ãªãª", Meiryo, "ãã©ã®ãè§ã´ Pro W3",
		"Hiragino Kaku Gothic Pro", "ï¼­ï¼³ ï¼°ã´ã·ãã¯", "MS PGothic",
		sans-serif !important;
}

.content-box-1 {
	background-color: #fff;
	border-bottom: 1px solid #b1b1b1;
	border-left: 1px solid #b1b1b1;
	border-radius: 0 0 4px 4px;
	border-right: 1px solid #b1b1b1;
	padding: 15px 0 0 0;
	text-align: center;
}

.label-c {
	color: #666;
	width: 150px;
}

.input-c {
	width: 524px;
	padding: 10px;
	color: #666;
}

.input-submit {
	cursor: pointer;
	font-size: 1.4em;
	margin: 20px auto 20px 153px;
	padding: 10px 12px 8px 12px;
	border: 1px solid #12476a;
	border-radius: 3px;
	background: linear-gradient(#307cae, #27648d);
	background: -webkit-gradient(linear, left top, left bottom, from(#307cae),
		to(#27648d));
	background: -moz-linear-gradient(top, #307cae, #27648d);
	background-color: #307caf;
	color: #ffffff !important;
	vertical-align: middle;
	text-shadow: 0 -1px 2px #000;
	box-shadow: 1px 1px 0 #649dc2 inset;
	white-space: nowrap;
	-ms-filter:
		"progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae, EndColorStr=#27648d)";
	filter: progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae,
		EndColorStr=#27648d);
	font-size: 14px;
	width: 524px;
	display: block;
}
</style>
<div class="div-list">
	<div class="box-1">
		<div class="title-box-1">パスワード設定</div>
		<div class="content-box-1">
			<form>
				<ul class="list-profile">
					<li><label class="label-c">ç¾å¨ã®ãã¹ã¯ã¼ã</label> <input
						class="input-c" value=""></li>
					<li><label class="label-c">æ°ãããã¹ã¯ã¼ã</label> <input
						class="input-c" value=""></li>
					<li><label class="label-c">æ°ãããã¹ã¯ã¼ãï¼ç¢ºèªï¼</label>
						<input class="input-c" value=""></li>
					<li
						style="border: none; padding-bottom: 0; text-align: center; margin: 0 auto;">

						<input type="submit" class="input-submit"
						value="ãã¹ã¯ã¼ããå¤æ´">
					</li>
				</ul>
			</form>
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
