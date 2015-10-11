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

.ui-mobile .ui-page-active {
    display: block;
    overflow: visible !important;
    overflow-x: hidden !important;
}

.label-c{
        color: #666;
        width: 150px;
      }
      .input-c{
        width: 524px;
        padding: 10px;
        color: #666;
      }
      /*edit 9/10*/
      .category_title {
       
      text-align: left;
      font-weight: bold;
      font-size: 15px;
      line-height: 40px;
      padding: 0 10px 0 20px;
      margin-bottom: 15px;
      background: url(/ecard-webapp/assets/img/faq2.png) no-repeat 10px center #f0eee4;
     }
    .category_title.active{
       background: url(/ecard-webapp/assets/img/faq1.png) no-repeat 10px center #f0eee4;
      } 
      /*end edit 9/10*/
   .dl{
      display: block;
      width: auto;
      text-align: left;
      margin: 0 10px;
      font-family: "メイリオ",Meiryo,"ヒラギノ角ゴ Pro W3","Hiragino Kaku Gothic Pro","ＭＳ Ｐゴシック","MS PGothic",sans-serif !important;
   }
   .dl dt{
      float: left;
      width: 25px
   }
   .dl dt img{
     width: 25px;
   }
   .dl dd{
    margin-left: 35px;
    font-weight: bold;
    color: #555;
    font-size: 1em;
    display: block;
    width: auto;
    margin-bottom: 10px;
   }    
   .dl dd.dd-maB{
    margin-bottom: 20px;
    font-weight: normal;
   }
</style>

<div class="div-list">
	<div class="box-1">
	    	<div class="title-box-1">FAQ</div>
	    	<div class="content-box-1" style="padding:0">  
             <!-- /*edit 9/10*/ -->
            <div class="category_title active">用語</div>
            <dl class="dl">
              <dt><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
              <dd>「BC-RIBBON」とはどんな意味ですか？</dd>
              <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
              <dd class="dd-maB">BCとはBUSINESS CARD(名刺)の略です。RIBBONは、テンプグループの法人データベースの名前からとっています。法人データベースの「RIBBON」と同じく、名刺(お客様)とテンプグループを”結ぶ”、グループ各社同士を”結ぶ”という意味を込めています。</dd>

              <dt><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
              <dd>「データ登録センター」とは何ですか？</dd>
              <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
              <dd  class="dd-maB">スキャンした名刺情報の修正・確認を行うセンターです。フロンティアチャレンジにて運用を行っています。</dd>

              <dt><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
              <dd>「最近取り込んだ名刺」「最近見た名刺」「最近繋がった名刺」の「最近」とはいつまでですか？</dd>
              <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
              <dd  class="dd-maB">直近7日間です</dd>
            </dl>

            <div class="category_title">名刺撮影</div>
            <dl class="dl" style="display:none">
              <dt><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
              <dd>撮影した名刺が枠が少しずれてしまいましたが、データ化されますか？</dd>
              <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
              <dd class="dd-maB">撮影した画像が枠からずれていたり、ぼやけていると正確にデータ化されません。できる限り正確に撮影をお願いします。</dd>
            </dl>  

            <div class="category_title">名刺撮影</div>
            <dl class="dl" style="display:none">
              <dt><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
              <dd>撮影した名刺が枠が少しずれてしまいましたが、データ化されますか？</dd>
              <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
              <dd class="dd-maB">撮影した画像が枠からずれていたり、ぼやけていると正確にデータ化されません。できる限り正確に撮影をお願いします。</dd>
            </dl> 
             <!-- end edit 9/10*/ --> 
	    	</div>
	    </div>
</div>
<script src="js/jquery-2.1.1.js"></script>
    <!-- /*edit 9/10*/ -->
    <script type="text/javascript">
        $( "#btn-success" ).click(function() {
           $('.modal-content').hide(); 
           $('.modal-content-new').show(); 
        });
         $( ".category_title" ).click(function() {

           if($(this).hasClass('active')) {
              $(this).next('.dl').hide();
              $(this).removeClass('active');
            }else{
              $(this).next('.dl').show();
              $(this).addClass("active");
            }
           
           
        });       
     </script>