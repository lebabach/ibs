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
</style>
<script>
	$(function() {
		$("#includedqa").load("https://bc-ribbon.temp-holdings.co.jp/qa.html");
	});
</script>

<style type="text/css">
.label-c {
	color: #666;
	width: 150px;
}

.input-c {
	width: 524px;
	padding: 10px;
	color: #666;
}

.category_title {
	background: #f0eee4;
	text-align: left;
	font-weight: bold;
	font-size: 15px;
	line-height: 40px;
	padding: 0 10px;
	margin-bottom: 15px;
}

.dl {
	display: block;
	width: auto;
	text-align: left;
	margin: 0 10px;
	font-family: "ã¡ã¤ãªãª", Meiryo, "ãã©ã®ãè§ã´ Pro W3",
		"Hiragino Kaku Gothic Pro", "ï¼­ï¼³ ï¼°ã´ã·ãã¯", "MS PGothic",
		sans-serif !important;
}

.dl dt {
	float: left;
	width: 25px
}

.dl dt img {
	width: 25px;
}

.dl dd {
	margin-left: 35px;
	font-weight: bold;
	color: #555;
	font-size: 1em;
	display: block;
	width: auto;
	margin-bottom: 10px;
}

.dl dd.dd-maB {
	margin-bottom: 20px;
	font-weight: normal;
}
</style>
<div class="div-list">
	<div class="box-1">
		<div class="title-box-1">FAQ</div>
		<div class="content-box-1" style="padding: 0">
			<div class="category_title">用語</div>
			<dl class="dl">
				<dt>
					<img src="img/icon-q.png">
				</dt>
				<dd>ãBC-RIBBONãã¨ã¯ã©ããªæå³ã§ããï¼</dd>
				<dt>
					<img src="img/icon-a.png">
				</dt>
				<dd class="dd-maB">BCã¨ã¯BUSINESS
					CARD(ååº)ã®ç¥ã§ããRIBBONã¯ããã³ãã°ã«ã¼ãã®æ³äººãã¼ã¿ãã¼ã¹ã®ååããã¨ã£ã¦ãã¾ããæ³äººãã¼ã¿ãã¼ã¹ã®ãRIBBONãã¨åãããååº(ãå®¢æ§)ã¨ãã³ãã°ã«ã¼ããâçµã¶âãã°ã«ã¼ãåç¤¾åå£«ãâçµã¶âã¨ããæå³ãè¾¼ãã¦ãã¾ãã</dd>

				<dt>
					<img src="img/icon-q.png">
				</dt>
				<dd>ããã¼ã¿ç»é²ã»ã³ã¿ã¼ãã¨ã¯ä½ã§ããï¼</dd>
				<dt>
					<img src="img/icon-a.png">
				</dt>
				<dd class="dd-maB">ã¹ã­ã£ã³ããååºæå ±ã®ä¿®æ­£ã»ç¢ºèªãè¡ãã»ã³ã¿ã¼ã§ãããã­ã³ãã£ã¢ãã£ã¬ã³ã¸ã«ã¦éç¨ãè¡ã£ã¦ãã¾ãã</dd>

				<dt>
					<img src="img/icon-q.png">
				</dt>
				<dd>ãæè¿åãè¾¼ãã ååºããæè¿è¦ãååºããæè¿ç¹ãã£ãååºãã®ãæè¿ãã¨ã¯ãã¤ã¾ã§ã§ããï¼</dd>
				<dt>
					<img src="img/icon-a.png">
				</dt>
				<dd class="dd-maB">ç´è¿7æ¥éã§ã</dd>
			</dl>

			<div class="category_title">名刺撮影</div>
			<dl class="dl">
				<dt>
					<img src="img/icon-q.png">
				</dt>
				<dd>æ®å½±ããååºãæ ãå°ãããã¦ãã¾ãã¾ãããããã¼ã¿åããã¾ããï¼</dd>
				<dt>
					<img src="img/icon-a.png">
				</dt>
				<dd class="dd-maB">æ®å½±ããç»åãæ ããããã¦ããããã¼ããã¦ããã¨æ­£ç¢ºã«ãã¼ã¿åããã¾ãããã§ããéãæ­£ç¢ºã«æ®å½±ããé¡ããã¾ãã</dd>
			</dl>
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
