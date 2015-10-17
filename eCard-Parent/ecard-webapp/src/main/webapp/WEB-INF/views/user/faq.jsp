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
      .ul-left .ul-left-li.active{
      background: url(/ecard-webapp/assets/img/faq1.png) no-repeat left 5px;
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
   
   .ul-left{
      width: 100%;
      display: inline-block;
      margin: 0;
      padding:0;
   }
   .ul-left .ul-left-li{
      color: #000;
      font-size: 14px;
      display: inline-block;
      text-align: left;
      background: url(/ecard-webapp/assets/img/faq2.png) no-repeat left 5px;
      width: 185px;
      padding-left: 15px;
   }
   .ul-left .ul-left-li.active{
      background: url(/ecard-webapp/assets/img/faq1.png) no-repeat left 5px;
   }
   .ul-left .ul-left-li ul{
      margin:  0 0 0 10px;
      width: auto;
      display: block;
      padding: 0;
   }
   .ul-left .ul-left-li ul li{
      color: #2415e3;
      font-size: 14px;
      display: inline-block;
      text-align: left;
      width: 100%;
      background:none;
      margin: 2px 0;
   }
   .ul-left .ul-left-li ul li a{
      color: #2415e3;
      text-decoration: underline;
   }
   .ul-left .ul-left-li ul li a:hover{
    text-decoration: none;
   }
</style>

<div class="div-list">  
    	<div class="box-1">
        <div class="box-1-left" style="width:200px; float:left;">
          <ul class="ul-left">
              <li class="ul-left-li active">用語
                  <ul class="ul-sub">
                      <li><a onclick="gettop(1)">「BC-RIBBON」とはどんな意味ですか？</a></li>
                      <li><a onclick="gettop(2)">「データ登録センター」とは何ですか？</a></li>
                      <li><a onclick="gettop(3)">「最近取り込んだ名刺」「最近見た名刺」「最近繋がった名刺」の「最近」とはいつまでですか？</a></li>
                      <li><a onclick="gettop(4)">「繋がっている名刺」とはどのような名刺を指しますか？</a></li>
                      <li><a onclick="gettop(5)">「タグ管理」とはどんな機能ですか？</a></li>
                      <li><a onclick="gettop(6)">検索画面の「グループネットワーク」とは何ですか？</a></li>
                      
                  </ul>
              </li>
              <li class="ul-left-li">名刺撮影
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(7)">撮影した名刺が枠が少しずれてしまいましたが、データ化されますか？</a></li>
                      <li><a onclick="gettop(8)">何枚までまとめて撮影できますか？</a></li>
                      <li><a onclick="gettop(9)">フラッシュ撮影はできますか？</a></li>
                      <li><a onclick="gettop(10)">撮影した名刺画像はスマホに保存されているのですか？</a></li>
                  </ul>
              </li>
              <li class="ul-left-li">名刺データ化
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(11)">撮影した名刺は、データ化までにどの程度時間がかかりますか？</a></li>
                      <li><a onclick="gettop(12)">1日の取込限度枚数はありますか？</a></li>
                      <li><a onclick="gettop(13)">名前カナもデータ化されますか？</a></li>
                      <li><a onclick="gettop(14)">名刺の裏面もデータ化されますか？</a></li>
                      <li><a onclick="gettop(15)">スマホでの撮影以外で、データ化する方法はありませんか？</a></li>
                      <li><a onclick="gettop(16)">データ登録センターへ取込依頼する場合は、データ化までにどの程度時間がかかりますか？</a></li>
                      <li><a onclick="gettop(17)">データ登録センターへ取込依頼する場合は、名刺のコピーでも良いですか？</a></li>
                      <li><a onclick="gettop(18)">日本語以外の対応言語はありますか？</a></li>
                      <li><a onclick="gettop(19)">データ化する際位に通信料はどの程度発生しますか？</a></li>
                  </ul>
              </li>
              <li class="ul-left-li">名刺検索
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(20)">AND検索はできますか？</a></li>
                      <li><a onclick="gettop(21)">検索条件は何件保存できますか？</a></li>
                  </ul>
              </li>
              <li class="ul-left-li">名刺管理機能
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(22)">名刺交換日の登録はできますか？</a></li>
                      <li><a onclick="gettop(23)">名刺交換日が不明な名刺はどうすれば良いですか？</a></li>
                      <li><a onclick="gettop(24)">名刺の所有者を変更したい場合はどうしたら良いですか？</a></li>
                      <li><a onclick="gettop(25)">外部システムとの連携はできますか？</a></li>
                      <li><a onclick="gettop(26)">同じ名刺を2枚登録し、データが2つできてしまいました。名刺を統合することはできますか？</a></li>
                      <li><a onclick="gettop(27)">名刺情報のダウンロードはできますか？</a></li>
                      <li><a onclick="gettop(28)">既にデータ化されている名刺データの取込はできますか？</a></li>
                  </ul>
              </li>
              <li class="ul-left-li">名刺情報共有範囲
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(29)">BC-RIBBONユーザであれば、全ての名刺を閲覧することができますか？</a></li>
                      <li><a onclick="gettop(30)">BC-RIBBONを導入している企業はどこですか？</a></li>
                      <li><a onclick="gettop(31)">名刺を他ユーザへ公開したくない場合、非公開設定はできますか？</a></li>
                  </ul>
              </li>
              <li class="ul-left-li">退職・異動
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(32)">退職する際の対応事項はありますか？</a></li>
                      <li><a onclick="gettop(33)">グループ会社へ異動する予定ですが、異動先でも使用できますか？</a></li>
                  </ul>
              </li>
              <li class="ul-left-li">アプリ操作
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(34)">ログアウトはどうすればできますか？</a></li>
                      <li><a onclick="gettop(35)">ログインIDの保存は解除できますか？</a></li>
                  </ul>
              </li>
              <li class="ul-left-li">その他
                  <ul class="ul-sub"  style="display:none">
                      <li><a onclick="gettop(36)">スマホを持っていない場合は利用できませんか？</a></li>
                      <li><a onclick="gettop(37)">プロフィールはどうすれば編集できますか？</a></li>
                      <li><a onclick="gettop(38)">閲覧できる名刺情報をもとにして営業活動を行っても問題ありませんか？</a></li>
                      <li><a onclick="gettop(39)">推奨環境などはありますか？</a></li>
                  </ul>
              </li>
          </ul>

          </dt>
        </div>
        <!-- end  box-1-left-->
        <div class="box-1-right" style="display: block;height: auto;margin: 0 auto;text-align: left;width: 580px; float:right">

    	    	<div class="title-box-1">FAQ</div>
    	    	<div class="content-box-1" style="padding:0">  
                 <!-- /*edit 9/10*/ -->
                <div class="category_title">用語</div>
                <dl class="dl">
                  <dt class="href-1-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>「BC-RIBBON」とはどんな意味ですか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">BCとはBUSINESS CARD(名刺)の略です。RIBBONは、テンプグループの法人データベースの名前からとっています。法人データベースの「RIBBON」と同じく、名刺(お客様)とテンプグループを”結ぶ”、グループ各社同士を”結ぶ”という意味を込めています。</dd>
                  <dt class="href-2-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>「データ登録センター」とは何ですか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd  class="dd-maB">スキャンした名刺情報の修正・確認を行うセンターです。フロンティアチャレンジにて運用を行っています。</dd>
                  <dt class="href-3-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>「最近取り込んだ名刺」「最近見た名刺」「最近繋がった名刺」の「最近」とはいつまでですか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd  class="dd-maB">直近7日間です。</dd>
                  <dt class="href-4-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>「繋がっている名刺」とはどのような名刺を指しますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd  class="dd-maB">自身以外に他ユーザも所有している名刺のことを指します。同じ名刺を通して他ユーザと繋がっているという意味です。</dd>
                  <dt class="href-5-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>「タグ管理」とはどんな機能ですか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd  class="dd-maB">名刺ごとに任意のタグをつけグルーピングできる機能です。</dd>
                  <dt class="href-6-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>検索画面の「グループネットワーク」とは何ですか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd  class="dd-maB">テンプグループ内のBC-RIBBONユーザを指します。グループネットワークでの検索を行うとテンプグループ内の他ユーザの名刺情報を検索できます。</dd>
                </dl>

                <div class="category_title">名刺撮影</div>
                <dl class="dl">
                  <dt class="href-7-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>撮影した名刺が枠が少しずれてしまいましたが、データ化されますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">撮影した画像が枠からずれていたり、ぼやけていると正確にデータ化されません。できる限り正確に撮影をお願いします。</dd>
                  <dt class="href-8-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>何枚までまとめて撮影できますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">30枚まで連続撮影が可能です。</dd>
                  <dt class="href-9-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>フラッシュ撮影はできますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">フラッシュ撮影はできません。フラッシュを使うと光が反射してしまい読み取りができなくなります。明るい場所で撮影をお願いします。</dd>
                  <dt class="href-10-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>撮影した名刺画像はスマホに保存されているのですか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">スマホには保存されません。画像を送信した時点で、自動的に削除されます。</dd>
                </dl>  

                <div class="category_title">名刺データ化</div>
                <dl class="dl">
                  <dt class="href-11-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>撮影した名刺は、データ化までにどの程度時間がかかりますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">16時までに取込を行った名刺は、原則翌営業日中のデータ化となります。</dd>
                  <dt class="href-12-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>1日の取込限度枚数はありますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">一度に送信できる枚数は30枚となりますが、取込の限度枚数はありません。</dd>
                  <dt class="href-13-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>名前カナもデータ化されますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">名刺情報からデータ登録センターで判断できるものに関してはデータ化されます。判断がつかないものに関してはデータ化されません。名刺のデータ化完了後、必要に応じて修正をお願いします。</dd>
                  <dt class="href-14-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>名刺の裏面もデータ化されますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">表面のみのデータ化となり、裏面はデータ化されません。裏表それぞれ取込みを行った場合も別名刺としてデータ化されます。</dd>
                  <dt class="href-15-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>スマホでの撮影以外で、データ化する方法はありませんか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">30枚以上の名刺取込については、データ登録センターへの取込依頼が可能です。メールフォーマットにてご依頼の上、名刺をデータ登録センターまで郵送して下さい。また、今後スキャナ接続機能の実装を検討しておりますので、実装後はスキャナより取込が可能なります。※使用するメールフォーマットはユーザガイドに記載があります。</dd>
                  <dt class="href-16-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>データ登録センターへ取込依頼する場合は、データ化までにどの程度時間がかかりますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">原則、名刺100枚までは、名刺受け取りから5営業日中のデータ化となります。101枚以上の場合は、ご依頼時にデータ登録センターより納期についてお伝えいたします。</dd>
                  <dt class="href-17-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>データ登録センターへ取込依頼する場合は、名刺のコピーでも良いですか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">コピーは不可となります。名刺原本をお送りください。</dd>
                  <dt class="href-18-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>日本語以外の対応言語はありますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">英語のみ対応しています。</dd>
                  <dt class="href-19-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                  <dd>データ化する際位に通信料はどの程度発生しますか？</dd>
                  <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                  <dd class="dd-maB">名刺画像送信時に1枚あたり100KB程度となります。アプリ自体の容量は3MB程度となります。</dd>
                </dl>
                <div class="category_title">名刺検索</div>
                <dl class="dl">
                    <dt class="href-20-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>AND検索はできますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">フリーワード検索では、検索ワードの間にスペースを入れて検索するとAND検索ができます。詳細検索では、項目を組み合わせてAND検索が可能です。※フリーワード検索と詳細検索のAND検索はできません。</dd>
                    <dt class="href-21-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>検索条件は何件保存できますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">5件です。</dd>
                </dl>
                
                <div class="category_title">名刺管理機能</div>
                <dl class="dl">
                    <dt class="href-22-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>名刺交換日の登録はできますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">名刺に「yyyymmdd」の形式で交換日を手書きで記載いただければ、その日付を名刺交換日として登録します。日付の記載がない場合は、取込日が交換日となります。※2015年9月30日以前に取得した名刺を2015年10月1日以降の日付で交換日登録を行うと情報漏えい事故に繋がりますのでご注意ください。</dd>
                    <dt class="href-23-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>名刺交換日が不明な名刺はどうすれば良いですか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">名刺に「不明」とご記載下さい。「1970/01/01」として交換日登録されます。</dd>
                    <dt class="href-24-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>名刺の所有者を変更したい場合はどうしたら良いですか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">BC-RIBBON管理者までお問合せ下さい。</dd>
                    <dt class="href-25-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>外部システムとの連携はできますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">Salesforseへの連携が可能です。名刺詳細画面下部にSalesforse連携ボタンがあります。BC-RIBBON管理者に連絡の上、権限申請を行って下さい。権限付与後、利用が可能です。今後その他のシステムとも連携を検討しています。</dd>
                    <dt class="href-26-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>同じ名刺を2枚登録し、データが2つできてしまいました。名刺を統合することはできますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">2015年11月リリース予定のPC版に実装予定です。</dd>
                    <dt class="href-27-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>名刺情報のダウンロードはできますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">2015年11月リリース予定のPC版に実装予定です。</dd>
                    <dt class="href-28-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>既にデータ化されている名刺データの取込はできますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">2015年11月リリース予定のPC版に実装予定です。</dd>
                </dl>
                <div class="category_title">名刺情報共有範囲</div>
                <dl class="dl">
                    <dt class="href-29-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>BC-RIBBONユーザであれば、全ての名刺を閲覧することができますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">所属する会社の名刺であれば、全てのデータを閲覧することが可能です。所属会社以外の名刺については、2015年10月1日以降取得した名刺のみ閲覧可能となります。※テンプグループでは、2015年10月1日に法人情報のグループ共同利用について告知を行ったため</dd>
                    <dt class="href-30-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>BC-RIBBONを導入している企業はどこですか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">現時点では、以下企業にて導入しています。※順次拡大予定です
                        ・テンプホールディングス株式会社<br>
                        ・テンプスタッフ・クロス株式会社<br>
                        ・株式会社インテリジェンス<br>
                        ・株式会社インテリジェンス ビジネスソリューションズ<br>
                        ・株式会社フロンティアチャレンジ<br>
                        ・インテリジェンスHITO総合研究所</dd>
                    <dt class="href-31-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>名刺を他ユーザへ公開したくない場合、非公開設定はできますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    /ecard-webapp/assets/img/icon-a.pngmaB">名刺自体を非公開にすることはできません。他ユーザへの非公開項目となっているメモ欄がありますので、顧客の機密情報などはメモ欄にご記載下さい。非公開機能については今後の実装を検討しています。</dd>
                </dl>
                <div class="category_title">退職・異動</div>
                <dl class="dl">
                    <dt class="href-32-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>退職する際の対応事項はありますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">退職が決まった際は各社のBC-RIBBON管理者にご連絡下さい。所有する名刺データを移行する必要がありますので、名刺の引き継ぎ先もお知らせ下さい。</dd>
                    <dt class="href-33-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>グループ会社へ異動する予定ですが、異動先でも使用できますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">異動先の会社がBC-RIBBONを導入していれば、異動先のアカウントとして使用可能です。ただし、特別な場合を除き、法人を跨いでの名刺情報の移行はできません。</dd>
                </dl>
                <div class="category_title">アプリ操作</div>
                <dl class="dl">
                    <dt class="href-34-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>ログアウトはどうすればできますか？/ecard-webapp/assets/img/icon-q.png            <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">アプリを終了すればログアウトされます。</dd>
                    <dt class="href-35-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>ログインIDの保存は解除できますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">ログイン画面で「IDを覚える」チェックを外してログインすると、次回のログイン以降IDの入力欄が表示されます。</dd>
                </dl>
                <div class="category_title">その他</div>
                <dl class="dl">
                    <dt class="href-36-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>スマホを持っていない場合は利用できませんか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB"> スマホ・タブレットをお持ちでない方もPC版をご利用いただけます。名刺データの取り込みについては、データ登録センターにご依頼下さい。また、今後スキャナ接続機能の実装を検討しておりますので、実装後はスキャナより取込が可能となります。※PC版のリリースは2015年11月1日を予定しています。
</dd>
                    <dt class="href-37-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>プロフィールはどうすれば編集できますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">プロフィールの編集はできません。BC-RIBBON管理者にお問合せ下さい。</dd>
                    <dt class="href-38-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>閲覧できる名刺情報をもとにして営業活動を行っても問題ありませんか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">他ユーザが所有する名刺情報をもとに営業活動を行う場合は、必ず名刺の所有者に確認し、紹介などをしてもらった上で行って下さい。了承を取らずに営業アプローチを行う行為は禁止されています。</dd>
                    <dt class="href-39-qa"><img src="/ecard-webapp/assets/img/icon-q.png"></dt>
                    <dd>推奨環境などはありますか？</dd>
                    <dt><img src="/ecard-webapp/assets/img/icon-a.png"></dt>
                    <dd class="dd-maB">推奨環境は以下の通りです。<br><br>
                        ・アプリ<br>
                        iOS  iOS7.1以上　iOS9.0.1対応済み<br>
                        Android AndroidOS 4.0.2以上<br><br>
                        
                        ・Web<br>
                        Chrome44以上<br>
                        Safari　7以上<br>
                        FireFox　39以上<br>
                        IE9以上</dd>
                </dl>
                 <!-- end edit 9/10*/ --> 
    	    	</div>
             <!-- end  box-1-right-->
	    </div>
    </div> 
<script src="js/jquery-2.1.1.js"></script>
    <!-- /*edit 9/10*/ -->
    <script type="text/javascript">
    function gettop(id){
        var tong = $('.href-'+id+'-qa').offset().top -100;
        $('html, body').animate({scrollTop: tong}, 'slow');
     }
 
     $( "#btn-success" ).click(function() {
        $('.modal-content').hide(); 
        $('.modal-content-new').show(); 
     });
      $( ".ul-left-li" ).click(function() {

        if($(this).hasClass('active')) {
           
         }else{
           $('.ul-left-li').removeClass('active');
           $(this).addClass("active");
           $('.ul-sub').hide();
           
           $(this).find('.ul-sub').show();
           
         }
        
        
     });         
     </script>