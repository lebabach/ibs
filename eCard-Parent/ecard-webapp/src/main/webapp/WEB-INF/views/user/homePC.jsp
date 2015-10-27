<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="com.ecard.webapp.vo.UserSearchVO"%>
<style>
.mesage_error {
	color: red;
	margin-top: 10px !important;
	display: none;
}

.btn-lg{
    padding: 2px 16px;  
  }
  .form-group{
    margin-bottom: 0;
  }
  .clearfix{
    margin-bottom: 10px;
  }

  .clearfix a.active{
    font-weight: bold;
  }
  .navbar-left{
    float: left;
     margin-top: 45px;
  }
  .navbar-left li {
   
    position: relative;
    margin-left: 20px;

    }
    .navbar-top-links .dropdown-menu li{
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
  .navbar-static-top{
    margin-bottom: 0;
    float: right;
    background: none;
    position: absolute;
    right: -15px;
    bottom: -7px;
  }
  .searchTargetSwitcher{
    background: #fff;
     
     display: block;
     padding-bottom: 5px;
  }
  
  .ul-left-li.active{
      background: url(/ecard-webapp/assets/img/faq1.png) no-repeat left 15px top 17px !important;
   }
   .ul-left-li{
      background: url(/ecard-webapp/assets/img/faq2.png) no-repeat left 15px top 15px !important;
      

   }

   .no-show-content {
    display : none;
   }
   .show-content {
    display : block;
   }
   
  .list-group-item-title{
    background: -moz-linear-gradient(center top , #f4f4f4, #e6e6e6) repeat scroll 0 0 rgba(0, 0, 0, 0);
    border: 1px solid #b1b1b1;
    border-radius: 4px 4px 0 0;
    box-shadow: 0 1px 1px #fff inset;
    font-weight: bold;
    padding: 14px 30px 10px;
    text-align: left;
    color: #666;
    font-weight: bold;
    font-family: "メイリオ",Meiryo,"ヒラギノ角ゴ Pro W3","Hiragino Kaku Gothic Pro","ＭＳ Ｐゴシック","MS PGothic",sans-serif !important;
  }
  .list-group-item{
    background: #fff;
     border: 1px solid #b1b1b1;
  }
  .row-new{
    display: table;
    padding: 12px 0 10px 0;
    margin: 0;
    float: none;
    width: 100%;
  }
  .col-md-1{
      display: table-cell;
    vertical-align: middle;
    width: 50px;
    float: none;
}
.col-md-5{
    display: table-cell;
    vertical-align: middle;
    width: 100%;
}
.col-md-6{
        display: table-cell;
    vertical-align: middle;
    width: 290px;
    float: none;
}
.some_chk{
 background:url(../assets/img/checkbox.png) no-repeat !important ;
}
/* #paging .icheckbox_square-green {
    display: inline-block !important;
    *display: inline !important;
    vertical-align: middle !important;
    margin: 0 !important;
    padding: 0 !important;
    width: 22px !important;
    height: 22px !important;
    background: url(../assets/css/plugins/iCheck/checkbox.png) no-repeat !important;
    border: none !important;
    cursor: pointer !important;
}
#paging .icheckbox_square-green.hover {
    background-position: 0 -22px;
} */
</style>
<!-- START HEADER -->
<div id="loading-copy" style="display: none; z-index: 1000; position: fixed;top: 50%;left: 50%;margin-top: -100px;margin-left: -100px;">
	<img src="<c:url value='/assets/img/loading-card.gif'/>" />
</div>
<div class="" style="border: solid 1px #f3f3f4;background: #e3e3e3;">
	  
      <div class="row clearfix">        
          <div class="col-md-2 " style="width: 250px; display:inline-block">
            <div class="form-group">
                <div class="icon-addon addon-md">
                    <!-- <form name="myform">  -->
                    	<button class="btn btn-primary btn-lg" id="btn-home">名刺一覧</button>
						<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal-example">名刺検索</button>
                                <!-- modal -->
                                <div class="modal" id="modal-example" tabindex="-1">
                                    <div class="modal-dialog">                                        
                                        <!-- modal content -->
                                        <style type="text/css">
                                            .modal-content-new{
                                                background-clip: padding-box;
                                                background-color: #FFFFFF;
                                                border: 1px solid rgba(0, 0, 0, 0);
                                                border-radius: 4px;
                                                box-shadow: 0 1px 3px rgba(0, 0, 0, 0.3);
                                                outline: 0 none;
                                                position: relative;
                                                margin-top: 180px;
                                            }
                                        .list-new-n{
                                            display: table;
                                            width: 100%;
                                            margin: 0;
                                            padding: 0;
                                        }
                                        .checkbox-new{
                                            display: table-cell;
                                            vertical-align: middle;
                                            width: 50px;
                                        }
                                        .list-contetn{
                                            display: table-cell;
                                            vertical-align: middle;
                                            text-align: left;
                                        }
                                        .list-contetn p{
                                            display: block;
                                            width: auto;
                                            
                                        }
                                        .a-new-n{
                                            background:#dc0069;
                                            display: inline-block;
                                            border:none;
                                        }
                                        </style>
                                        
                                        
                                        <div class="modal-content-new" style="display:none">
                                            <div class="modal-header" id="lsUserSearchs" style="height: 579px;overflow-y: scroll;">
                                                <a class="close" id="close-x">
                                                    <span aria-hidden="true">×</span>
                                                </a>
                                                
                                                <h4 class="modal-title" id="modal-label">検索条件管理</h4>
                                            </div>
                                            <div class="modal-footer" style="width: 100%;
                                                display: inline-block;
                                                margin: 0 auto;
                                                text-align: center;">
                                                
                                                <a id="btn-success2" class="btn btn-success a-new-n">選択した検索条件を使用する</a>
                                                
                                                <a id="btn-success3" class="btn btn-success a-new-n">選択した検索条件を削除</a>
                                            </div>
                                        </div>
                                        <div class="modal-content">
                                            <!-- modal header -->
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                                <h4 class="modal-title" id="modal-label">検索条件を入力してください</h4>
                                            </div>
                                            <!-- modal body -->
                                            <div class="modal-body">
                                                <label for="parameterFlg">検索対象</label>
                                                <select class="form-control" id="parameterFlg" name="parameterFlg">
                                                    <option value="0">自分の名刺</option>
                                                    <option value="1">グループネットワーク</option>
                                                </select>
                                                <div class="form-group">
                                                    <label for="freeText">フリーワード</label>
                                                    <input type="email" class="form-control" name="freeText" id="freeText" placeholder="会社名・氏名・emailを入力 * and * でAND検索">
                                                        </div>
                                                <h4 class="modal-title" id="modal-label">必要に応じて入力してください</h4>
                                                <div class="form-group" style="display:none">
                                                    <label for="owner">所有者</label>
                                                    <input type="email" class="form-control" name="owner" id="owner" placeholder="">
                                                        </div>
                                                <div class="form-group">
                                                    <label for="company">会社名</label>
                                                    <input type="email" class="form-control" name="company" id="company" placeholder="">
                                                        </div>
                                                <div class="form-group">
                                                    <label for="department">部署</label>
                                                    <input type="email" class="form-control" name="department" id="department" placeholder="">
                                                        </div>
                                                <div class="form-group">
                                                    <label for="position">役職</label>
                                                    <input type="email" class="form-control" name="position" id="position" placeholder="">
                                                        </div>
                                                <div class="form-group">
                                                    <label for="name">氏名</label>
                                                    <input type="email" class="form-control" name="name" id="name" placeholder="">
                                                    <p class="mesage_error error_common">121121</p>
                                                        </div>
                                            </div>
                                            <!-- modal footer -->
                                            <div class="modal-footer">
                                                <div class="col-md-3">
                                                    <button type="button" class="btn btn-primary btn-lg">検索</button>
                                                </div>
                                                <a id="btn-success" class="btn btn-success" >保存済みの検索条件を呼び出す</a>
                                                <button type="button" class="btn btn-info">検索条件を保存する</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                    <!-- </form> -->
                    
                    <!-- <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>  -->
                </div>
            
            </div>
            
          </div>

      </div>

	      
</div>
  
  <!--  End Header -->

  <!-- Start Container -->
  <div id="container" class="container" style= "padding-top: 20px !important;" >
  	<div class="row" style="margin-bottom:20px">
  		<div class="col-md-4">
  			<h4><span id="title-search-loadmore">名刺一覧 </span> <span id="count-search"></span></h4>
  		</div>
  		<div class="col-md-8">
  			<span style="color:red;">本Webアプリは、他のグループ会社への共有が出来ない情報も含まれております。情報を取り扱う際には、詳細画面より共有可能な名刺かどうかをご確認ください。</span>
  		</div>
  	</div>

  	<div class="row " style="margin-bottom: 10px">
          <div class="col-md-2 m-b-xs setDisplayTerm" style="width:180px;padding-right: 0 !important;">
            <select id="selectSortBox" class="input-sm form-control input-s-sm inline">
              <option value="0" selected>すべて</option>
              <option value="1" >最近取り込んだ名刺</option>
              <option value="2" >最近見た名刺</option>
              <option value="3" >データ入力待ち</option>
             <!--  <option value="4" >繋がっている名刺</option>
              <option value="5" >最近繋がった名刺</option> -->
            </select>
          </div>
          <input type ="hidden" name= "totalCardConnect"  value = "">
          <%-- <div class="col-md-2 m-b-xs setDisplayTerm" style="width:140px;padding-left: 5px !important;">
            <select id="selectTagBox" class="input-sm form-control input-s-sm inline">
              <option value="0" selected>(タグ一覧から)</option>
              <c:forEach var="cardTag" items="${listTagGroup}">
              	<option value="${cardTag.tagId}"><c:out value="${cardTag.tagName}"/></option>
              </c:forEach>
                                        
            </select>
          </div>    --%>      
          
          <div class="col-md-3 m-b-xs setDisplayTerm" id = "bulk_tag" style="width:200px; padding-left: 10px !important;padding-right: 0px !important;">            
            <div class="btn-group" role="group" aria-label="..." style="float:right;">
              <button id="addTag" class="btn btn-primary disabled" type="button" style="height: 30px !important;text-align: center;padding-top: 3px;">選択名刺に一括タグ付け             
                <i class="fa fa-tag"></i>               
              </button>
              <div class="balloon lbl_balloon" style="display: none;">
                <div class="">
                  <div class="col-sm-12" style="border-bottom: solid 1px #c1c1c1;overflow-y: auto; max-height: 280px;">
                    <table class="table tagNameTable" id="paging" >
                    <!-- <table class="" id="paging" style="width: 100%;max-width: 100%;margin-bottom:10px">                      -->
                      <col width="10%">
                      <col width="80%">
                      <col width="10%">
                      <tbody>
                      <c:forEach var="cardTag" items="${listTagGroup}">
                          <tr id="rowData">
                            <td>
                                 <input type="checkbox"  class="i-checks" id="1" value="<c:out value='${cardTag.tagId}'/>">
                                 <input type="hidden" name= "userId"  value="<c:out value='${cardTag.userId}'/>">
                                 <input type="hidden" name= "cardId"  value="<c:out value='${cardTag.cardId}'/>"> 
                            </td>                  
                            <td class="nametag"><c:out value="${cardTag.tagName}" /></td>
                            <td><a href="#" class="delTag"><i class="fa fa-trash"></i></a></td>
                          </tr>
                       </c:forEach>
                      </tbody>
                    </table>
                </div>
                </div>
                
                <div class="" style = "display: inline-block;">
                  <div class="input-group lbl" style="padding: 15px;">
                    <input type="text" class="form-control" id ="tagCardName" placeholder="新規タグを追加">
                      <span class="input-group-btn"> 
                      <button id="addLabel" type="button" class="btn btn-success">作成</button> 
                      </span>
                  </div>
                </div>
              </div>                             
            </div>
            
          </div>    
          
          <div class="col-md-3 m-b-xs setDisplayTerm" id = "deleteTag" style="width:200px;padding-left : 5px !important;">            
            <div class="btn-group" role="group" aria-label="..." style="float:right;">           
              <button id="deletePeople" class="btn btn-primary disabled" type="button" style="height: 30px !important;text-align: center;padding-top: 3px;">選択名刺を一括削除
                <i class="fa fa-trash"></i></button>
            </div>            
          </div>
          
          <div class="col-md-3 setDisplayTerm" style="max-width:120px;padding-left : 5px !important;float:right" id = "sort_cnd">
              <select class="input-sm form-control input-s-sm inline" id = "sort-card-cnd" >                
                <option value="1" >名前順</option>
                <option value="2">会社名順</option>
                <option value="5" selected>交換月順</option>
                <option value="6">タグ順</option> 
                                          
            </select>
          </div>
          
          
	  </div>
    <div class="business_card_book">
    <c:choose>
	     <c:when test="${empty searchDetail}">
		     <c:forEach var="nameSort" items="${lstNameSort}" varStatus="loopCount">
		   		<div class="list-group" style="margin-bottom: 0px !important" id="<c:out value='${nameSort}'/>">
		   			<c:if test="${loopCount.count == 1}">
			        	<div class="ul-left-li active list-group-item-title ">
			        		<c:set var="string1" value="${nameSort}"/>
							<c:set var="string2" value="${fn:replace(string1,'/', '年')}" />
							<c:out value="${string2}月" />

			        	</div>
	       				<c:forEach var="cardInfoPCVo" items="${lstCardInfoPCVo}">  						        
					        <c:forEach var="cardInfo" items="${cardInfoPCVo.lstCardInfo}">
						        <div class="list-group-item pointer show-content">
						          <div class="row row-new">
						            <div class="col-md-1 col-xs-1">
						              <div class="icheckbox_square-green">
						                <input type="checkbox" class="i-checks" name="bla" value="<c:out value='${cardInfo.cardId}' />">
						              </div>
						            </div>
						            <div class="col-md-5">
						              <div class="col-xs-11 mg-top">
						                <p class="name"><c:out value="${cardInfo.lastName}" />  <c:out value="${cardInfo.firstName}" /></p>
						                <p class="livepass"><c:out value="${cardInfo.companyName}" /></p>
						                <p class="department_and_position"><c:out value="${cardInfo.departmentName}" /> <c:out value="${cardInfo.positionName}" /></p>
						                <p class="num"><c:out value="${cardInfo.telNumberCompany}" /></p>
						                <p class="mail"><a href="#"><c:out value="${cardInfo.email}" /></a></p>
						              </div>
						            </div>
						            <div class="col-md-6">
						              <div class="col-xs-5" style=" display: table;">
						                </div>
						                  <div class="col-xs-7">
						                    <img src="<c:url value='/assets/img/loading.gif'/>" class="img-responsive img-thumb pull-right" alt="Responsive image">
						                    <input class="hidden" name="fileImageName" value="${cardInfo.imageFile}">
						                  </div> 
						            </div>
						          </div>          
						        </div>
					        </c:forEach>						      
				     	</c:forEach>
				     	<c:if test="${lstCardInfoPCVo.size() < totalCardInfo}">
				     		<div class="list-group-item pointer show-content" style="text-align: center;">
					      	 	<a id="clickToLoadMore"  style="color: red;"> 次の10件を表示</a>
					      	</div>
				     	</c:if>
			     		
				     	
      			
			        </c:if>
			        <c:if test="${loopCount.count != 1}">
			       		<div class="ul-left-li list-group-item-title ">
			       			<c:set var="string1" value="${nameSort}"/>
							<c:set var="string2" value="${fn:replace(string1,'/', '年')}" />																	    
			        		<c:out value="${string2}月" />		       			
			       		</div>
		       		</c:if>
		       	</div>       	
	      	</c:forEach>	      	
	     </c:when>
	    <c:otherwise>
				<input class="hidden" id="hid-freeText" name="hid-freeText"
					value="${searchDetail.freeText}">
				<input class="hidden" id="hid-owner" name="hid-owner"
					value="${searchDetail.owner}">
				<input class="hidden" id="hid-company" name="hid-company"
					value="${searchDetail.company}">
				<input class="hidden" id="hid-department" name="hid-department"
					value="${searchDetail.department}">
				<input class="hidden" id="hid-position" name="hid-position"
					value="${searchDetail.position}">
				<input class="hidden" id="hid-name" name="hid-name"
					value="${searchDetail.name}">
				<input class="hidden" id="hid-parameterFlg" name="hid-parameterFlg"
					value="${searchDetail.parameterFlg}">
			</c:otherwise>
	</c:choose>
		  
    </div>
</div>

<!--  End Header -->

  <!-- End Container -->
    
<script>
	var request = null;     
    var id_manager = 1;
    var totalCardInfo = '<c:out value="${totalCardInfo}"/>';
    var typeLoading = 0;
    var page = 1;
    var searchDetail ='<c:out value="${searchDetail}" />';
    var arrayHirgana = ["ア","イ","ウ","エ","オ","カ","ガ","キ","ギ","ク","グ","ケ","ゲ","コ","ゴ","サ","ザ","シ","ジ","ス","ズ","セ","ゼ","ソ","ゾ",
                        "タ","ダ","チ","ヂ","ツ","ヅ","テ","デ","ト","ド","ナ","ニ","ヌ","ネ","ノ","ハ","バ","パ","ヒ","ビ","ピ","フ","ブ","プ","ヘ",
                        "ベ","ペ","ホ","ボ","ポ","マ","ミ","ム","メ","モ","ヤ","ユ","ヨ","ラ","リ","ル","レ","ロ","ワ"];
    
    var arrayAphabet = ["あ","い","う","え","お","か","が","き","ぎ","く","ぐ","け","げ","こ","ご","さ","ざ","し","じ","す","ず","せ","ぜ","そ","ぞ",
                        "た","だ","ち","ぢ","つ","づ","て","で","と","ど","な","に","ぬ","ね","の","は","ば","ぱ","ひ","び","ぴ","ふ","ぶ","ぷ","へ",
                        "べ","ぺ","ほ","ぼ","ぽ","ま","み","む","め","も","や","ゆ","よ","ら","り","る","れ","ろ","わ"];  
      if(searchDetail!=null&&searchDetail!=""){
	   		var freeText = $("#hid-freeText").val();
	   		var owner = $("#hid-owner").val();
	   		var company = $("#hid-company").val();
	   		var department = $("#hid-department").val();
	   		var position = $("#hid-position").val();
	   		var name = $("#hid-name").val();
	   		var parameterFlg = $("#hid-parameterFlg").val();
	   		var isDetail = $("#hid-isDetail").val();
	   		assignToInput(freeText,owner,company,department,position,name,parameterFlg);
	   		ListSearch(freeText,owner,company,department,position,name,parameterFlg);
	   		
      }
      
      function assignToInput(freeText,owner,company,department,position,name,parameterFlg) {
    	  	$("#freeText").text(freeText);
	   		$("#owner").text(owner);
 	   		$("#company").text(company);
 	   		$("#department").text(department);
 	   		$("#position").text(position);
 	   		$("#name").text(name);
 	   		
	 	   	$("#freeText").val(freeText);
	   		$("#owner").val(owner);
	   		$("#company").val(company);
	   		$("#department").val(department);
	   		$("#position").val(position);
	   		$("#name").val(name);
	   		$("#parameterFlg").val(parameterFlg);
	   	}
      
      var scrollAllow = 1;
      var isLoading = 0;
      
      $(window).scroll(function() {     	  
    	  var currentNumberCard = $('.list-group .active').parent().find('.row-new').length;
    	  var self = $('.list-group .active').parent();
    	  /* console.log("Scroll totalCardInfo = "+totalCardInfo);
    	  console.log("Scroll CurrentNumber = "+ currentNumberCard); */
    	  
    	  
    		   
	    	   if($(window).scrollTop() + $(window).height()  >= ($(document).height())) {
	    		   /* if(currentNumberCard < parseInt(totalCardInfo)){
	    		   if(typeLoading == 2 && scrollAllow == 1 && (parseInt($('#sort-card-cnd').val()) == 1 || parseInt($('#sort-card-cnd').val()) == 2 
	    				   || parseInt($('#sort-card-cnd').val()) == 5 || parseInt($('#sort-card-cnd').val()) == 6)){
	    			// Load more card
		     		  
		     		   var typeSort = $('#sort-card-cnd').val();
		     		   var typeSearch = $("#selectSortBox option:selected").val();
		     		   var strDate = self.attr("id");
		     		   if(typeSort == 5){
		               		strDate = strDate.slice(0,4)+"/"+strDate.slice(4,strDate.length+1);	
		               	}
		     		   
		     		   if(isLoading != 0){    			       			   
		     			   return false;
		     		   }
		     		   
		     		   console.log("PAGE = "+ id_manager);
		     			$.xhrPool.abortAll();
		    		    $.ajax({
		 	    			type: 'POST',
		 	    			url: 'search',
		 	    			data: 'page=' +id_manager + "&typeSort=" +typeSort + "&typeSearch=" + typeSearch + "&valueSearch=" + strDate
		 	    		  }).done(function(resp, status, xhr) {
		 	    			   var listGroupItem = "";
		 	    			   totalCardInfo = resp.recordsTotal;
		 	    			   console.log("TotalCardInfo = "+ totalCardInfo);
		 	    				$.each( resp.data, function( key, value ) {
		 	    					 $.each( value.lstCardInfo, function (k,v) {
		 	    						 listGroupItem += '<div class="list-group-item pointer show-content">'
		 	    				    					+'<div class="row row-new">'
		 	    				    					+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
		 	    				    					+    '<input type="checkbox" value='+v.cardId+' class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
		 	    				    					+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
		 	    				    					+	 '</div></div>'
		 	    				    					+	'<div class="col-md-5">'
		 	    				    					+		'<div class="col-xs-11 mg-top">'
		 	    				    					+ 			'<p class="name">'+ v.lastName + ' '+v.firstName +'</p>'
		 	    				    					+			'<p class="livepass">'+v.companyName+'</p>'
		 	    				    					+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
		 	    				    					+			'<p class="num">'+v.telNumberCompany+'</p>'
		 	    				    					+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
		 	    				    					+ '</div></div>'
		 	    				    					+	'<div class="col-md-6">'
		 	    				    					+	'<div class="col-xs-5" style=" display: table;"></div>'	
		 	    				    					+	'<div class="col-xs-7">'								
		 	    				    					+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
		 	    				    					+   '<input class="hidden" name="fileImageName" value='+v.imageFile+'>'
		 	    				    					+	'</div> </div> </div> </div>';
		 	    					 });
		 	    				});
		 	    				self.append(listGroupItem);
		 	    				getImageSCP();
		 	    				id_manager++;
		 	    			}).fail(function(xhr, status, err) {
		 	    				
		 	    			});
	    		   	}
	    	   } */
	    	    	// Call ajax here	
	    	    	if($('#titleOfSearch').length){
	    	    		//search
	    	    		var freeText = $("#freeText").val();
	           	   		var owner = $("#owner").val();
	           	   		var company = $("#company").val();
	           	   		var department = $("#department").val();
	           	   		var position = $("#position").val();
	           	   		var name = $("#name").val();
	           	   		var parameterFlg = $("#parameterFlg").val()
	           	   		
	           			if($("#parameterFlg").val()==0){
	           				owner="";	
	           	        }
		           	   	$.ajax({
		       			    type: 'POST',
		       			    url: 'searchCards',
		       			    dataType: 'json', 
		       				 contentType: 'application/json',
		       				 mimeType: 'application/json',
		       			     data: JSON.stringify({ 
		       			        'freeText':freeText,
		       			        'owner':owner,
		       			        'company':company,
		       			        'department':department,
		       			        'position':position,
		       			        'name':name,
		       			        'parameterFlg':parameterFlg,
		       			        'page':++id_manager
		       			    }),
		       			    success: function(data){
		       			    	setDataSearchLoadMore(data.cardInfo);
		       			    	loadICheck();
		       			    }
		       			});
	    	    	}
	    	    	if( isLoading == 0 &&typeLoading == 3 && (parseInt($('#selectSortBox').val()) == 4 || parseInt($('#selectSortBox').val()) == 5 )){
	    	    		var recentFlg =0;
	    	    		var total = 0;
	    	    		if(parseInt($('#selectSortBox').val()) == 4){
	    	    			recentFlg = 0;
	    	    		}else if (parseInt($('#selectSortBox').val()) == 5){
	    	    			recentFlg = 1;
	    	    		}
                       var total = parseInt($('input[name=totalCardConnect]').val()) ;
                       
	    	            if($('.business_card_book .list-group-item').length < total ){
	    	            	console.log("Total:" + total);
			    	    		 $.ajax({
			     					type: 'POST',
			     					url: 'listConnectUser',
			     					data: 'page=' + page + "&recentFlg=" +recentFlg
			     				}).done(function(resp, status, xhr) { 					
			     					$.each( resp.cardList, function( k, v ) {
			     						 $(".business_card_book .list-group").append('<div class="list-group-item pointer show-content">'
			     			 					+'<div class="row row-new">'
			     								+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
			     								+    '<input type="checkbox" value="'+v.cardId+'" class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
			     								+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
			     								+	 '</div></div>'
			     								+	'<div class="col-md-5">'
			     								+		'<div class="col-xs-11 mg-top">'
			     								+ 			'<p class="name">'+v.lastName +' '+ v.firstName+'</p>'
			     								+			'<p class="livepass">'+v.companyName+'</p>'
			     								+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
			     								+			'<p class="num">'+v.telNumberCompany+'</p>'
			     								+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
			     								+ '</div></div>'
			     								+	'<div class="col-md-6">'
			     								+	'<div class="col-xs-5" style=" display: table;"></div>'	
			     								+	'<div class="col-xs-7">'								
			     								+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
			     								+   '<input class="hidden" name="fileImageName" value="'+v.imageFile+'">'
			     								+	'</div> </div> </div> </div>');
			     			 					getImageFromSCP(v.imageFile);
			     					});
			     					page++;
			     				}).fail(function(xhr, status, err) {
			     					console.log('BBB='+err);
			     				}); 
	    	            }
	    	    	}
	    	    } 
    	  
    	});

 $(document).ready(function(){
	 $(document).on('click', '.list-group-item-title', function(event)  {
		
		typeLoading = 2;
		scrollAllow = 0;		 
		$.xhrPool.abortAll();
		var clickMySelf = 0;
        $('.list-group-item-title').not($(this)).removeClass('active');
        $('.list-group-item-title').not($(this)).parent().find('.list-group-item').removeClass('show-content');
        $('.list-group-item-title').not($(this)).parent().find('.list-group-item').addClass('no-show-content');

        if($(this).hasClass('active')){
        	clickMySelf = 1;
        	$(this).removeClass('active');
            $(this).parent().find('.list-group-item ').removeClass('show-content');
            $(this).parent().find('.list-group-item ').addClass('no-show-content');
        } else {
        	$(this).addClass('active');
            $(this).parent().find('.list-group-item ').removeClass('no-show-content');
            $(this).parent().find('.list-group-item ').addClass('show-content');	
        }
        if(clickMySelf == 1){
        	return false;
        }
        $("#clickToLoadMore").parent().remove();
        
       	var self = $(this).parent();        	
        var strDate = $(this).parent().attr("id");
        var typeSort = $("#sort-card-cnd option:selected").val();
       	var typeSearch = $("#selectSortBox option:selected").val();
       	var page = 0;
       	id_manager = 0;
       	isLoading = 0;
       	if(typeSort == 5){
       		strDate = strDate.slice(0,4)+"/"+strDate.slice(4,strDate.length+1);	
       	}
       	self.find('.list-group-item').remove();
       	$("#loading-copy").show();
          $.ajax({
   			type: 'POST',
   			url: 'search',
   			data: 'page=' + id_manager + "&typeSort=" +typeSort + "&typeSearch=" + typeSearch + "&valueSearch=" + strDate
   		  }).done(function(resp, status, xhr) {
   			   var listGroupItem = "";
   			   totalCardInfo = resp.recordsTotal;
   			   console.log("TotalCardInfo = "+ totalCardInfo);
   				$.each( resp.data, function( key, value ) {
   					 $.each( value.lstCardInfo, function (k,v) {
   						 listGroupItem += '<div class="list-group-item pointer show-content">'
   				    					+'<div class="row row-new">'
   				    					+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
   				    					+    '<input type="checkbox" value='+v.cardId+' class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
   				    					+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
   				    					+	 '</div></div>'
   				    					+	'<div class="col-md-5">'
   				    					+		'<div class="col-xs-11 mg-top">'
   				    					+ 			'<p class="name">'+ v.lastName + ' '+v.firstName +'</p>'
   				    					+			'<p class="livepass">'+v.companyName+'</p>'
   				    					+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
   				    					+			'<p class="num">'+v.telNumberCompany+'</p>'
   				    					+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
   				    					+ '</div></div>'
   				    					+	'<div class="col-md-6">'
   				    					+	'<div class="col-xs-5" style=" display: table;"></div>'	
   				    					+	'<div class="col-xs-7">'								
   				    					+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
   				    					+   '<input class="hidden" name="fileImageName" value='+v.imageFile+'>'
   				    					+	'</div> </div> </div> </div>';
   					 });
   				});
   				self.append(listGroupItem);
   				if(totalCardInfo > 10){
   					appendLoadMore(self);	
   				}
   				
   				getImageSCP();
   				scrollAllow = 1;
   				id_manager++;
   				$("#loading-copy").hide();
   			}).fail(function(xhr, status, err) {
   				$("#loading-copy").hide();
   			});
     });
	 
   	 $(".business_card_book .list-group").each(function() {
   		var id  = $(this).attr("id").replace('/', '');
   		$(this).attr("id",id);
   	 });
   	 
     $('.i-checks').iCheck({
       checkboxClass: 'icheckbox_square-green',
       radioClass: 'iradio_square-green',                
     });

       $("#deletePeople").click(function(e){
    	   if (confirm('<fmt:message key="card.list.confirmDelete"/>')) {
    		   var listCardId=[];
    			$(".icheckbox_square-green.checked").each(function(){
    	         cardId = $(this).find('input[name=bla]').val();
    	         listCardId.push({"cardId":cardId});    	         
    			});
    			$.ajax({
 					type: 'POST',
 					url: 'deleteListCard',
 					 dataType: 'json', 
 					 contentType: 'application/json',
 					 mimeType: 'application/json',
 					data:JSON.stringify({"listCardId":listCardId}) 
 				}).done(function(resp, status, xhr) {
 					i = 0;
 					if(resp != 0){
 						$(".list-group-item .checked").closest('.list-group-item').each(function(){
 						$(this).removeClass("checked")
 	 					  if($(this).parents('.list-group').find('.row-new').length==1){
 	 					    $(this).parents('.list-group').remove();
 	 					  } else {
 	 					    $(this).remove();
 	 					  }
 	 					});	
 					}
 					reloadICheck();
 					
 				}).fail(function(xhr, status, err) {
 					console.log('BBB='+err);
 				});
    			$(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
    	   } 
    	   
    	   
       });
       var i = 0;
     $(document).on('click', '.business_card_book .list-group-item .icheckbox_square-green', function(e) {
   	  	if($(this).attr("class").indexOf("checked") == -1){
   	  		$(this).removeClass('icheckbox_square-green');
   	  		 $(this).removeClass("icheckbox_square-green hover");
   	      	 $(this).addClass("icheckbox_square-green checked");
   	      	 i++;
          	 $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
   	      	 return false;
   	  	}else{
   	  		$(this).removeClass("icheckbox_square-green checked");
   	  		 $(this).addClass("icheckbox_square-green"); 
   	  		 i--;
   	  		 if(i== 0){
   	  	 	    $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
   	  		 }
     	
   	  		
   	  		 return false;
   	  	}
   	  
   }); 
     
     
     $('#selectSortBox').on('change', function(event) {
    	 $.xhrPool.abortAll();
         var typeSort =  $(this).val();
         typeLoading = 3;
         var recentFlg = 0;
         if(parseInt(typeSort) == 4){
 			recentFlg = 0;
 		}else if (parseInt(typeSort) == 5){
 			recentFlg = 1;
 		}
         if(parseInt(typeSort) == 4 || parseInt(typeSort) == 5){
        	  $.ajax({
					type: 'POST',
					url: 'totalConnect',
					data: 'recentFlg=' + recentFlg
				}).done(function(resp, status, xhr) { 					
					$('input[name=totalCardConnect]').val(resp);
				}).fail(function(xhr, status, err) {
					$('input[name=totalCardConnect]').val(0);
				});
         }
 		
         if(parseInt(typeSort) == 0){
        	 typeLoading = 2
        	 $("#sort_cnd").show();
        	 $("#bulk_tag").show();
        	 $("#deleteTag").show();
        	 $("#sort-card-cnd").trigger("change");
         }else if(parseInt(typeSort) == 1){
        	 $("#sort_cnd").hide();
        	 $("#bulk_tag").show();
        	 $("#deleteTag").show();
        		$.ajax({
 					type: 'POST',
 					url: 'getListPossesionCardRecent'
 				}).done(function(resp, status, xhr) { 					
 					$(".business_card_book").html("");
 					$(".business_card_book").append('<div class="list-group">');
 					$.each( resp, function( k, v ) {
 						 $(".list-group").append('<div class="list-group-item pointer show-content">'
 			 					+'<div class="row row-new">'
 								+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
 								+    '<input type="checkbox" value="'+v.cardId+'" class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
 								+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
 								+	 '</div></div>'
 								+	'<div class="col-md-5">'
 								+		'<div class="col-xs-11 mg-top">'
 								+ 			'<p class="name">'+v.lastName +' '+ v.firstName+'</p>'
 								+			'<p class="livepass">'+v.companyName+'</p>'
 								+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
 								+			'<p class="num">'+v.telNumberCompany+'</p>'
 								+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
 								+ '</div></div>'
 								+	'<div class="col-md-6">'
 								+	'<div class="col-xs-5" style=" display: table;"></div>'	
 								+	'<div class="col-xs-7">'								
 								+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
 								+   '<input class="hidden" name="fileImageName" value="'+v.imageFile+'">'
 								+	'</div> </div> </div> </div>');
 			 					getImageFromSCP(v.imageFile);
 					});
 				$(".business_card_book").append('</div>');
 				}).fail(function(xhr, status, err) {
 					console.log('BBB='+err);
 				});  
         }else if (parseInt(typeSort) == 2){
        	 $("#sort_cnd").hide();
        	 $("#bulk_tag").hide();
        	 $("#deleteTag").hide();
        	 $.ajax({
					type: 'POST',
					url: 'listCardRecent'
				}).done(function(resp, status, xhr) { 					
					$(".business_card_book").html("");
					$(".business_card_book").append('<div class="list-group">');
					$.each( resp, function( k, v ) {
						 $(".list-group").append('<div class="list-group-item pointer show-content">'
			 					+'<div class="row row-new">'
								+	'<div class="col-md-1 col-xs-1"><input type="hidden" name="cardId" value = "'+v.cardId+'"></div>'
								+	'<div class="col-md-5">'
								+		'<div class="col-xs-11 mg-top">'
								+ 			'<p class="name">'+v.lastName +' '+ v.firstName+'</p>'
								+			'<p class="livepass">'+v.companyName+'</p>'
								+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
								+			'<p class="num">'+v.telNumberCompany+'</p>'
								+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
								+ '</div></div>'
								+	'<div class="col-md-6">'
								+	'<div class="col-xs-5" style=" display: table;"></div>'	
								+	'<div class="col-xs-7">'								
								+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
								+   '<input class="hidden" name="fileImageName" value="'+v.imageFile+'">'
								+	'</div> </div> </div> </div>');
			 					getImageFromSCP(v.imageFile);
					});
				   $(".business_card_book").append('</div>');
				}).fail(function(xhr, status, err) {
					console.log('BBB='+err);
				});  
         }else if (parseInt(typeSort) == 3){
        	 $("#sort_cnd").hide();
        	 $("#bulk_tag").hide();
        	 $("#deleteTag").hide();
        	 $.ajax({
					type: 'POST',
					url: 'listCardPending'
				}).done(function(resp, status, xhr) { 					
					$(".business_card_book").html("");
					$(".business_card_book").append('<div class="list-group">');
					$.each( resp, function( k, v ) {
						 $(".list-group").append('<div class="list-group-item pointer show-content">'
			 					+'<div class="row row-new">'
								+	'<div class="col-md-1 col-xs-1"><input type="hidden" name="cardId" value = "none"></div>'
								+	'<div class="col-md-5">'
								+		'<div class="col-xs-11 mg-top">'
								+ 			'<p class="name">データ入力中</p>'
								+			'<p class="livepass">'+v.createDate+'</p>'
								+ '</div></div>'
								+	'<div class="col-md-6">'
								+	'<div class="col-xs-5" style=" display: table;"></div>'	
								+	'<div class="col-xs-7">'								
								+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
								+   '<input class="hidden" name="fileImageName" value="'+v.imageFile+'">'
								+	'</div> </div> </div> </div>');
			 					getImageFromSCP(v.imageFile);
					});
					$(".business_card_book").append('</div>');
				}).fail(function(xhr, status, err) {
					console.log('BBB='+err);
				});  
         }else if(parseInt(typeSort) == 4){
        	 isLoading = 0;
        	 $("#sort_cnd").hide();
        	 $("#bulk_tag").show();
        	 $("#deleteTag").show();
        	  page = 1;
        		$.ajax({
 					type: 'POST',
 					url: 'listConnectUser',
 					data: 'page=' +0 + "&recentFlg=" +0
 				}).done(function(resp, status, xhr) { 					
 					$(".business_card_book").html("");
 					$(".business_card_book").append('<div class="list-group">');
 					$.each( resp.cardList, function( k, v ) {
 						 $(".list-group").append('<div class="list-group-item pointer show-content">'
 			 					+'<div class="row row-new">'
 								+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
 								+    '<input type="checkbox" value="'+v.cardId+'" class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
 								+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
 								+	 '</div></div>'
 								+	'<div class="col-md-5">'
 								+		'<div class="col-xs-11 mg-top">'
 								+ 			'<p class="name">'+v.lastName +' '+ v.firstName+'</p>'
 								+			'<p class="livepass">'+v.companyName+'</p>'
 								+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
 								+			'<p class="num">'+v.telNumberCompany+'</p>'
 								+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
 								+ '</div></div>'
 								+	'<div class="col-md-6">'
 								+	'<div class="col-xs-5" style=" display: table;"></div>'	
 								+	'<div class="col-xs-7">'								
 								+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
 								+   '<input class="hidden" name="fileImageName" value="'+v.imageFile+'">'
 								+	'</div> </div> </div> </div>');
 			 					getImageFromSCP(v.imageFile);
 					});
 				   $(".business_card_book").append('</div>');
 				}).fail(function(xhr, status, err) {
 					console.log('BBB='+err);
 				});  
         }else if(parseInt(typeSort) == 5){
        	 $("#sort_cnd").hide();
        	 $("#bulk_tag").show();
        	 $("#deleteTag").show();
        	 isLoading = 0;
        	 page = 1;
        		$.ajax({
 					type: 'POST',
 					url: 'listConnectUser',
 					data: 'page=' +0 + "&recentFlg=" +1
 				}).done(function(resp, status, xhr) { 					
 					$(".business_card_book").html("");
 					$(".business_card_book").append('<div class="list-group">');
 					$.each( resp.cardList, function( k, v ) {
 						 $(".list-group").append('<div class="list-group-item pointer show-content">'
 			 					+'<div class="row row-new">'
 								+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
 								+    '<input type="checkbox" value="'+v.cardId+'" class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
 								+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
 								+	 '</div></div>'
 								+	'<div class="col-md-5">'
 								+		'<div class="col-xs-11 mg-top">'
 								+ 			'<p class="name">'+v.lastName +' '+ v.firstName+'</p>'
 								+			'<p class="livepass">'+v.companyName+'</p>'
 								+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
 								+			'<p class="num">'+v.telNumberCompany+'</p>'
 								+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
 								+ '</div></div>'
 								+	'<div class="col-md-6">'
 								+	'<div class="col-xs-5" style=" display: table;"></div>'	
 								+	'<div class="col-xs-7">'								
 								+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
 								+   '<input class="hidden" name="fileImageName" value="'+v.imageFile+'">'
 								+	'</div> </div> </div> </div>');
 			 					getImageFromSCP(v.imageFile);
 					});
 				   $(".business_card_book").append('</div>');
 				}).fail(function(xhr, status, err) {
 					console.log('BBB='+err);
 				});  
         }
     });

       $('#sort-card-cnd').on('change', function() {
	       	$.xhrPool.abortAll();
	       	typeLoading = 2;
	        $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
	       	var typeSort = $(this).val();
	       	var typeSearch = $("#selectSortBox option:selected").val();
	       	var valueSearch = "";
	       	id_manager = 0;
	       	isLoading = 0;
	       	
	       	$("#loading-copy").show();
	          $.ajax({
				type: 'POST',
				url: 'search',
				data: 'page=' +id_manager + "&typeSort=" +typeSort + "&typeSearch=" + typeSearch + "&valueSearch=" + valueSearch
			  }).done(function(resp, status, xhr) {
				 totalCardInfo = resp.recordsTotal;
	   			 console.log("TotalCardInfo = "+ totalCardInfo);
				 var listGroup = "";
				 var listGroupItem = "";
				 $('.business_card_book').html("");
				 $.each( resp.data, function( key, value ) {
					 if(typeSort == 5) {
						 nameShow = value.nameSort.replace("/","年")+"月";
					 /* } else if(typeSort == 6 && value.nameSort=="cardNoTag") {
						 nameShow = "（タグ設定なし)"; */
					 } else if (typeSort == 1 ){
						nameShow = changeAphabetToHigrana(value.nameSort);		 
					 } else {
						 nameShow = value.nameSort;
					 }
					 
					 
					 if(key == 0){	
						listGroup = $('.business_card_book').append(
								'<div class="list-group" style="margin-bottom: 0px !important;" id="'+value.nameSort.replace("/","").trim()+'">'
						        +'<div class="ul-left-li active list-group-item-title">'+nameShow+'</div></div>');
				 
						 $.each( value.lstCardInfo, function (k,v) {
							 listGroupItem += '<div class="list-group-item pointer show-content">'
					    					+'<div class="row row-new">'
					    					+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
					    					+'<input type="checkbox" value='+v.cardId+' class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
					    					+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
					    					+		'</div></div>'
					    					+	'<div class="col-md-5">'
					    					+		'<div class="col-xs-11 mg-top">'
					    					+ 			'<p class="name">'+ v.lastName + ' '+v.firstName +'</p>'
					    					+			'<p class="livepass">'+v.companyName+'</p>'
					    					+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
					    					+			'<p class="num">'+v.telNumberCompany+'</p>'
					    					+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
					    					+ '</div></div>'
					    					+	'<div class="col-md-6">'
					    					+	'<div class="col-xs-5" style=" display: table;"></div>'	
					    					+	'<div class="col-xs-7">'								
					    					+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
					    					+   '<input class="hidden" name="fileImageName" value='+v.imageFile+'>'
					    					+	'</div> </div> </div> </div>';				    		 
		    			    
						 });
						 $('.business_card_book').find("#"+value.nameSort.replace("/","").trim()).append(listGroupItem);
						 getImageSCP();
						 
						 
					 } else {
						 if(value.nameSort.replace("/","").trim()==""){
								value.nameSort="NULL";
								listGroup = $('.business_card_book').append(
										'<div class="list-group" style="margin-bottom: 0px !important;" id="NULL">'
								        +'<div class="ul-left-li list-group-item-title">'+"#"+'</div></div>');
						 } else {
								listGroup = $('.business_card_book').append(
										'<div class="list-group" style="margin-bottom: 0px !important;" id="'+value.nameSort.replace("/","").trim()+'">'
								        +'<div class="ul-left-li list-group-item-title">'+nameShow+'</div></div>');
						 }
						 
					 }
				 });
				if (totalCardInfo > 10){
   					appendLoadMore(self);
   				}
				id_manager++;
				$("#loading-copy").hide();
			}).fail(function(xhr, status, err) {
				$("#loading-copy").hide();
			});
       });
       
       
       $( "#btn-success" ).click(function() {
    	   resetValidationForm();
           $.ajax({
   			type: 'POST',
   			url: 'listSearchText/',
   			dataType: 'json', 
   			contentType: 'application/json',
   			mimeType: 'application/json',
   			success: function(data) {
   				//called when successful
   				if(data.hasData){
   					$('.modal-content').hide(); 
   	               $('.modal-content-new').show(); 
   	               DisplayContents(data.userSearchs);
   				}else{
   					$(".error_common").text("保存されている検索条件はありません");
			     $(".mesage_error").css("display", "block");
   				}
 			  },
 			  error: function(e) {
 				//called when there is an error
 				//console.log(e.message);
 			  }
    		});
           
       });
       
       $( "#btn-success2" ).click(function() {
 	         $('.modal-content').show(); 
 	         $('.modal-content-new').hide(); 
 	         var isCheck=false;
 	     	var freeText="",owner="",company="",department="",position="",name="",parameterFlg="";
	   		$(".modal-content-new .i-checks").each(function() {
	   			if($(this).is(':checked')){
	   				freeText=$(this).closest(".row.row-new").find(".hidden.freeText").val();
	   				owner=$(this).closest(".row.row-new").find(".hidden.owner").val();
	   				company=$(this).closest(".row.row-new").find(".hidden.company").val();
	   				department=$(this).closest(".row.row-new").find(".hidden.department").val();
	   				position=$(this).closest(".row.row-new").find(".hidden.position").val();
	   				name=$(this).closest(".row.row-new").find(".hidden.name").val();
	   				parameterFlg=$(this).closest(".row.row-new").find(".hidden.parameterFlg").val();
	   				setDisplayResearch(freeText,owner,company,department,position,name,parameterFlg);
	   				isCheck=true;
	   				return false;
	   			}
	   		});
	   		if(!isCheck){
	   			setDisplayResearch(freeText,owner,company,department,position,name,0);	
	   		}
	   		return false;
         });
         
         $( "#close-x" ).click(function() {
   	         $('.modal-content').show(); 
   	         $('.modal-content-new').hide(); 
           });
       
       $( "#parameterFlg" ).click(function() {
           if($(this).val()==0){
          	 $("#owner").closest(".form-group").attr("style","display:none");
           }
           if($(this).val()==1){
          	 $("#owner").closest(".form-group").removeAttr("style");
           }
       });
          
          $( ".btn-info" ).click(function() {
           	resetValidationForm();
   			if (!checkValidationForm()) {
   				return false;
   			}
   			var freeText = $("#freeText").val();
   	   		var owner = $("#owner").val();
   	   		var company = $("#company").val();
   	   		var department = $("#department").val();
   	   		var position = $("#position").val();
   	   		var name = $("#name").val();
   	   		var parameterFlg = $("#parameterFlg").val()
   	   		
   			if($("#parameterFlg").val()==0){
   				owner="";	
   	        }
   			
   			$.ajax({
   			    type: 'POST',
   			    url: 'addUserSearch',
   			    dataType: 'json', 
   				 contentType: 'application/json',
   				 mimeType: 'application/json',
   			    data: JSON.stringify({ 
   			        'freeText':freeText,
   			        'owner':owner,
   			        'company':company,
   			        'department':department,
   			        'position':position,
   			        'name':name,
   			        'parameterFlg':parameterFlg
   			    }),
   			    success: function(msg){
   			        if(msg==true){
   			        	 $(".error_common").text("検索条件を登録しました");
   					     $(".mesage_error").css("display", "block");
   			        }else{
   			        	$(".error_common").text("検索条件を保存できるのは5件までです。");
   					     $(".mesage_error").css("display", "block");
   			        }
   			    }
   			});
          });
          
          $("#freeText").change(function(){
        	  resetOthersInputText();
          });
          
          $("#owner,#company,#department,#position,#name").change(function(){
        	  resetFreeText()
          });
       
          
          $(".modal-content .btn-lg").click(function() {
        	  
             	resetValidationForm();
       			if (!checkValidationFormSearch()) {
       				return false;
       			}
       			var freeText = $("#freeText").val();
       	   		var owner = $("#owner").val();
       	   		var company = $("#company").val();
       	   		var department = $("#department").val();
       	   		var position = $("#position").val();
       	   		var name = $("#name").val();
       	   		var parameterFlg = $("#parameterFlg").val()
       	   		
       			if($("#parameterFlg").val()==0){
       				owner="";	
       	        }
       	   		$(".modal-header button").click();       	   		
       	   		ListSearch(freeText,owner,company,department,position,name,parameterFlg);
              });
          
          $("#btn-success3").click(function(){
		   		$('.modal-content').hide(); 
	            $('.modal-content-new').show(); 
		   		var id="";
		   		$(".modal-content-new .i-checks").each(function() {
		   			if($(this).is(':checked')){
		   				id=$(this).closest(".row.row-new").find(".hidden.id").val();
		   				return false;
		   			}
		   		});
		   		$.ajax({
	   			    type: 'POST',
	   			    url: 'deleteUserSearch',
	   			    data: { 
	   			        'id':id
	   			    },
	   			    success: function(data){
	   			    	if(data.hasData){
	   			         	DisplayContents(data.userSearchs);
	   			        }else{
	   			        	$(".error_common").text("検索条件を保存できるのは5件までです。");
	   					     $(".mesage_error").css("display", "block");
	   			        }
	   			    }
	   			});
		   		
		   	 });
		   	 
        	$("#btn-home").click(function(){
	    		 <%  
	    		 session.setAttribute("searchDetail", null);
	    		 %>
	    		 window.location.reload(true);
	    		
	    	});
		   	 
});/* END READY DOCUMENT  */
 
      
      function ListSearch(freeText,owner,company,department,position,name,parameterFlg){
		 
 	   		id_manager=0;
 			$.ajax({
 			    type: 'POST',
 			    url: 'searchCards',
 			    dataType: 'json', 
 				 contentType: 'application/json',
 				 mimeType: 'application/json',
 			    data: JSON.stringify({ 
 			        'freeText':freeText,
 			        'owner':owner,
 			        'company':company,
 			        'department':department,
 			        'position':position,
 			        'name':name,
 			        'parameterFlg':parameterFlg,
 			        'page':0
 			    }),
 			    success: function(data){
 			    	
 			    	$("#count-search").text(": "+data.count+" 件");
 			    	setDataSearch(data.cardInfo);
 			    	disableBtnSort();
 			    	loadICheck();
 			    	$("#btnCloseUserSearch").click(function(){
 			    		 <%  
 			    		 session.setAttribute("searchDetail", null);
 			    		 %>
 			    		location.reload();
 			    		
 			    	});
 			    }
 			});
 			
      }

      /* $(".balloon").on('click', '.delTag', function() {
        $(this).parent().parent().remove();
      }); */

      // Process with Notification List
      $("#notification").click(function(event) {
        // console.log($(this));
      });
      
      $(".business_card_book .img-responsive").each(function () {
    	  	isLoading=isLoading+1;
    		var target = $(this);
    	    var fileImageName =$(this).parent().find('input[name=fileImageName]').val();    	    
    	    $.ajax({
    	        type: 'POST',
    	        url: 'getImageFile',
    	        data: 'fileImage='+fileImageName
    	    }).done(function(resp, status, xhr){
    	    	if(resp == ""){
    	    		target.attr('src','');    	  
        	        target.attr('src','/ecard-webapp/assets/img/card_08.png');
    	    	} else {
    	    		target.attr('src','');    	  
        	        target.attr('src','data:image/png;base64,'+resp);	
    	    	}
    	    	isLoading=isLoading-1;
    	    }).fail(function(resp, status, xhr){
    	        //alert('Error');
    	    });
    	});
      
		function getImageFromSCP(fileImageName){
			isLoading = isLoading + 1;
			var target = $('img[name="'+fileImageName+'"]');			
			$.ajax({
    	        type: 'POST',
    	        url: 'getImageFile',
    	        data: 'fileImage='+fileImageName,    	        
    	    }).done(function(resp, status, xhr){
    	    	if(target == []){
    	    		target = $('img[name="'+fileImageName+'"]');
    	    	}
    	    	if(resp == ""){
    	    		target.attr('src','');    	  
        	        target.attr('src','/ecard-webapp/assets/img/card_08.png');
    	    	} else {
    	    		target.attr('src','');    	  
        	        target.attr('src','data:image/png;base64,'+resp);	
    	    	}
    	    	isLoading=isLoading-1;
    	    }).fail(function(resp, status, xhr){
    	        //alert('Error');
    	    });						
		}
		function createTableHasGroup(lastDate, v){
			$('.business_card_book #'+lastDate).append(
    	    		'<div class="list-group-item pointer">'
					+'<div class="row row-new">'
					+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green"><input type="checkbox" value='+v.cardId+' class="i-checks" name="bla"></div></div>'
					+	'<div class="col-md-5">'
					+		'<div class="col-xs-11 mg-top">'
					+ 			'<p class="name">'+ v.lastName + ' '+v.firstName +'</p>'
					+			'<p class="livepass">'+v.companyName+'</p>'
					+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
					+			'<p class="num">'+v.telNumberCompany+'</p>'
					+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
					+ '</div></div>'
					+	'<div class="col-md-6">'
					+	'<div class="col-xs-5" style=" display: table;"></div>'	
					+	'<div class="col-xs-7">'								
					+	'<img src="<c:url value='/assets/img/loading.gif'/>" class="lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
					+   '<input class="hidden" name="fileImageName" value='+v.imageFile+'>'
					+	'</div> </div> </div> </div>'  );
		}
		
		function createTableNoGroup(nameSort, v){
			 $('.business_card_book').append(
						'<div class="list-group" style="margin-bottom: 0px !important; margin-top: 10px !important;" id= "'+nameSort.replace("/","").trim()+'">'
				        +'<div class="list-group-item-title">'+nameSort+'</div>'										 
    	    		+ '<div class="list-group-item pointer">'
					+'<div class="row row-new">'
					+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green"><input type="checkbox" value='+v.cardId+' class="i-checks" name="bla"></div></div>'
					+	'<div class="col-md-5">'
					+		'<div class="col-xs-11 mg-top">'
					+ 			'<p class="name">'+ v.lastName + ' '+v.firstName +'</p>'
					+			'<p class="livepass">'+v.companyName+'</p>'
					+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
					+			'<p class="num">'+v.telNumberCompany+'</p>'
					+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
					+ '</div></div>'
					+	'<div class="col-md-6">'
					+	'<div class="col-xs-5" style=" display: table;"></div>'	
					+	'<div class="col-xs-7">'								
					+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
					+   '<input class="hidden" name="fileImageName" value='+v.imageFile+'>'
					+	'</div> </div> </div> </div></div>'
    	    );
		}
		
		function checkValidationForm() {
	   		var checkValidation = true;
	   		var freeText = $("#freeText").val();
	   		var owner = $("#owner").val();
	   		var company = $("#company").val();
	   		var department = $("#department").val();
	   		var position = $("#position").val();
	   		var name = $("#name").val();
	   		if($("#parameterFlg").val()==1){
	        	 if(freeText=="" &&company=="" &&department=="" &&position=="" &&name=="" &&owner==""){
	        		 $(".error_common").text(
							"<fmt:message key='edit.card.validate'/>");
					checkValidation = false;
					$(".mesage_error").css("display", "block");
	        	 }
	        }else{
	        	if(freeText=="" &&company=="" &&department=="" &&position=="" &&name==""){
		      		 $(".error_common").text(
							"<fmt:message key='edit.card.validate'/>");
					checkValidation = false;
					$(".mesage_error").css("display", "block");
		      	 }
	        }
	   		
	   		return checkValidation;
	   	}
		
		function checkValidationFormSearch() {
	   		var checkValidation = true;
	   		var freeText = $("#freeText").val();
	   		var owner = $("#owner").val();
	   		var company = $("#company").val();
	   		var department = $("#department").val();
	   		var position = $("#position").val();
	   		var name = $("#name").val();
	   		if($("#parameterFlg").val()==1){
	        	 if(freeText=="" &&company=="" &&department=="" &&position=="" &&name=="" &&owner==""){
	        		 $(".error_common").text(
							"検索条件を指定してください");
					checkValidation = false;
					$(".mesage_error").css("display", "block");
	        	 }
	        }else{
	        	if(freeText=="" &&company=="" &&department=="" &&position=="" &&name==""){
		      		 $(".error_common").text(
							"検索条件を指定してください");
					checkValidation = false;
					$(".mesage_error").css("display", "block");
		      	 }
	        }
	   		
	   		return checkValidation;
	   	}
		
	   	function resetValidationForm() {
	   		$(".error_common").text("");
	   	}
	   	
	 	$(document).on('click', '.business_card_book .list-group-item', function(e) {
	 		var cardIdStr = $(this).find('input[name=bla]').val();
	 		
	 		if(cardIdStr != "none" &&  cardIdStr != undefined){
        	    cardId = parseInt(cardIdStr);
                 window.location.href = '<c:url value="/user/card/details/'+cardId+'"/>';
	 		}
	       }).hover(function() {
	         $(this).toggleClass('hover');
        }); 
	 
       $(document).on('ifChecked','.business_card_book input[name=bla]',function(event) {
    	   //$(".balloon").css("display","block");
           $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
	  	     
        });
        
       $(document).on('ifUnchecked','.business_card_book input[name=bla]',function(event){  
        	$(".balloon").css("display","none");
          if($(".business_card_book .icheckbox_square-green.checked").size() == 1){ 
            $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
            $(".addTagCard").css("display","none");  
          }          
        }); 

        $(document).on('click','#addTag',function(e){
       	  
          if($(".balloon").css("display") == "block"){
            $(".balloon").css("display","none");
          }else{
            var listCardIdCheck = [];
    	   	var carIdCheck = 0;
        	var listCardTag = [];
        	$(".business_card_book .icheckbox_square-green.checked").each(function(){
   	            cardId = $(this).find('input[name=bla]').val();
   	            listCardIdCheck.push(parseInt(cardId));    	         
   			});
        	console.log("Lenth: " + listCardIdCheck.length);
   	       $("#paging tr").each(function(){
   	    	   var listCardIdTag = $(this).find("td input[name=cardId]").val();
   	    	    listCardTag = listCardIdTag.split(",");
 	  	    	  var check = false;
 					var same = false;
 					var intSame = 0;
 					var countCard = listCardIdCheck.length;
 	  	    	  $.each(listCardTag, function(idxtag, vtag){
 	  	    		$.each(listCardIdCheck, function(idcard, vcard){
 	  	    		  if (vtag == vcard){
     					 	check = true;
     	                    same = true;
     	                    intSame++;
     	                    return false;
 	        			}else{
 	        				check = false;
 	        		    }
 	  	    		});
 	  	    	  if (intSame > 0 & countCard == 1) {
 	  	    		return false;
 	              }
 	  	    	});
 	  	    	  
 	  	       if (intSame ==countCard) {
 	  	    	  $(this).find("td .icheckbox_square-green").removeClass("some_chk");
	  	          $(this).find("td .icheckbox_square-green").removeClass("not_chk");
 	  	    	   $(this).find("td .icheckbox_square-green").addClass("checked");
 	  	        }else if (same) {
 	  	          $(this).find("td .icheckbox_square-green").removeClass("checked");
 	  	          $(this).find("td .icheckbox_square-green").removeClass("not_chk");
 	  	          $(this).find("td .icheckbox_square-green").addClass("some_chk");
 	  	        }else {
 	  	        	$(this).find("td .icheckbox_square-green").removeClass("checked");
 	 	  	        $(this).find("td .icheckbox_square-green").removeClass("some_chk");
 	  	        	$(this).find("td .icheckbox_square-green").addClass("not_chk");
 	  	        }
   	        });
   	        $(".balloon").css("display","block");
          }
        });
        
        $(document).mouseup(function (e){
     		    var container = $(".balloon").parent();
     		    if (!container.is(e.target) && container.has(e.target).length === 0) {
     		    	$(".balloon").css("display","none");
     		     }
        }); 
        
        $(function() {
            $.xhrPool = [];
            $.xhrPool.abortAll = function() {
                $(this).each(function(i, jqXHR) {   //  cycle through list of recorded connection
                    jqXHR.abort();  //  aborts connection
                    $.xhrPool.splice(i, 1); //  removes from list by index
                });
            }
            $.ajaxSetup({
                beforeSend: function(jqXHR) { $.xhrPool.push(jqXHR); }, //  annd connection to list
                complete: function(jqXHR) {
                    var i = $.xhrPool.indexOf(jqXHR);   //  get index for current connection completed
                    if (i > -1) $.xhrPool.splice(i, 1); //  removes from list by index
                }
            });
        })
	   	function DisplayContents(obj){
	   		$(".modal-body.userSearchs").remove();
	   		$.each( obj, function( key, value ) {
	   	   		$("#lsUserSearchs").append(setModalBody(value.freeText,value.owner,value.company,value.department,value.position,value.name,value.seq,value.parameterFlg));
		   	   	
	   		});
		   	 $('.modal-content-new .i-checks').iCheck({
	            checkboxClass : 'icheckbox_square-green',
	            radioClass : 'iradio_square-green'

	         });
		   	
		   	 
	   	}
	   	
	   	function setModalBody(freeText,owner,company,department,position,name,seq,parameterFlg){
	   		var label="";
	   		if(parameterFlg==0){
	   			label="自分の名刺検索で使用可能";
   	        }else{
   	        	label="グループネットワーク検索で使用可能";
   	        }
	   		var modal=	'<div class="modal-body userSearchs" style="border-bottom: 1px solid #b1b1b1">'
		   	   	+	'<label for="exampleInputEmail1">'+label+'</label>'
		   	   	+   '<div class="row row-new">'
		   	   	+		'<div class="col-md-1 col-xs-1">'
		   	   	+			'<div class="iradio_square-green">'
		   	   	+				'<input type="radio" class="i-checks" name="bla">'
		   	   	+					'</div>'
		   	   	+		'</div>'
		   	   	+       '<input class="hidden id" name="seq" value="'+seq+'">'
		   	   	+       '<input class="hidden freeText" name="freeText" value="'+freeText+'">'
		   	   	+       '<input class="hidden owner" name="owner" value="'+owner+'">'
		   	   	+       '<input class="hidden company" name="company" value="'+company+'">'
		   	   	+       '<input class="hidden department" name="department" value="'+department+'">'
		   	   	+       '<input class="hidden position" name="position" value="'+position+'">'
		   	   	+       '<input class="hidden name" name="name" value="'+name+'">'
		   	    +       '<input class="hidden parameterFlg" name="parameterFlg" value="'+parameterFlg+'">'
		   	   	+		'<div class="col-md-5">'
		   	   	+			'<p>フリーワード:'+freeText+'</p>'
		   	   	+			'<p>所有者:'+owner+'</p>'
		   	   	+			'<p>会社名:'+company+' </p>'
		   	   	+			'<p>部署:'+department+' </p>'
		   	   	+			'<p>役職:'+position+' </p>'
		   	   	+			'<p>名前:'+name+' </p>'
		   	   	+		'</div>'
		   	   	+	'</div>'
		   	   	+'</div>'
	   		return modal;
	   	}

	   	function setDisplayResearch(freeText,owner,company,department,position,name,parameterFlg){
	   		$("#freeText").val(freeText);
	   		$("#owner").val(owner);
	   		$("#company").val(company);
	   		$("#department").val(department);
	   		$("#position").val(position);
	   		$("#name").val(name);
	   		$("#parameterFlg").val(parameterFlg);
	   		$( "#parameterFlg" ).click();
	   		return false;
	   	}
	   	

	    // Add card tag
	     $('#addLabel').click(function(event) {
	       $.xhrPool.abortAll();
	    	var listCardId = [];
	    	var cardId = 0 ;
	    	 $(".business_card_book .icheckbox_square-green.checked").each(function(){
   	            cardId = $(this).find('input[name=bla]').val();
   	            listCardId.push({"cardId":cardId});    	         
   			}); 
	    	//var cardId = $("input[name=cardId]").val();
	    	var tagName = $("#tagCardName").val();
			if(tagName =="" ){
				BootstrapDialog.show({
					title: 'Warning',
	             	message: 'Please enter tag name'
		        });			
			}else{
				   var check = true;
					$(".nametag").each(function(){
			    		if($(this).text().trim() == tagName.trim()){
			    			BootstrapDialog.show({
								title: 'Error',
				             	message: '既に作成されているラベルです'});
			    			check = false;
			    		}
			    	});
					if(check){
							$('#selectTagBox').find('option[value!=0]').remove();
							
							$.ajax({
					        	url: "<c:url value='/user/addTagHome' />",
					        	data: JSON.stringify({"tagName" : tagName, "listCardId" : listCardId}),
					        	type: "POST",
					        	beforeSend: function(xhr) {
					        		xhr.setRequestHeader("Accept", "application/json");
					        		xhr.setRequestHeader("Content-Type", "application/json");
					        	},
					        	success: function(response) {
					        		var respHTML = "";
					        		var isChecked = "";
					        		var listCardId = [];
				        			$(".business_card_book .icheckbox_square-green.checked").each(function(){
				           	            cardId = $(this).find('input[name=bla]').val();
				           	            listCardId.push(parseInt(cardId));    	         
				           			});
				        			console.log(listCardId);
					        		$.each(response, function(index, value){
					        			//isChecked = "";
					        			var check = false;
        								var same = false;
        								var intSame = 0;
        								var countCard = listCardId.length;
					        			$.each(value["listCardIds"], function(idx, vtag){
					        				$.each(listCardId, function(idcard, vcard){
						        			if (vtag == vcard){
				        					 	check = true;
				        	                    same = true;
				        	                    intSame++;
				        	                    return false;
						        			}else{
						        					check = false;
						        				}
						        			});
					        				 if (intSame > 0 & countCard == 1) {
					        	 	  	    		return false;
					        	 	         }
					        			});
					        			 if (intSame == countCard) {
					        				 console.log("Check");
					        				 respHTML += "<tr id='rowData'>"
						        					+ "<td><div style='position: relative;' class='icheckbox_square-green checked' id='"+value["tagId"]+"'>"
							        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
							        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
							        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
					                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
							    					+ "</td>"
							    					+ "<td class='nametag'>"+value["tagName"]+"</td>"
							    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
					        		        } else if (same) {
					        		        	console.log("Same");
					        		        	respHTML += "<tr id='rowData'>"
							    					+ "<td><div style='position: relative;' class='icheckbox_square-green some_chk' id='"+value["tagId"]+"'>"
							        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
							        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
							        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
					                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
							    					+ "</td>"
							    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
							    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
					        		        } else {
					        		        	console.log("Not");
					        		        	respHTML += "<tr id='rowData'>"
							    					+ "<td><div style='position: relative;' class='icheckbox_square-green not_chk' id='"+value["tagId"]+"'>"
							        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
							        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
							        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
					                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
							    					+ "</td>"
							    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
							    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
					        		        }
					        			 
					        			 $('#selectTagBox').append('<option value="'+value["tagId"]+'">'+value["tagName"]+'</option>');					        			    
					        			


					        		});
					        		$("#tagCardName").val('');
					        		$("#paging tbody").html("");  
					        		$("#paging tbody").html(respHTML);
					        	},
					        	error: function(){
								  BootstrapDialog.show({
				       				title: 'Information',
				      	             	message: 'Add tag failed'
				       	      		});
							  	}
					        });
						}
					}
			
	     });
	    
	     //check add tag card
	     $(document).on('ifChecked', "#paging tbody input[type='checkbox']", function(e) {
	    	 $(this).parent().removeClass('not_chk');
	    	 $(this).parent().removeClass('some_chk');
	    	 var listCardId = [];
		    	var cardId = 0 ;
		    	$(".business_card_book .icheckbox_square-green.checked").each(function(){
	   	            cardId = $(this).find('input[name=bla]').val();
	   	            listCardId.push({"cardId":cardId});    	         
	   			});
		    	var tagId = $(this).val();
		    	console.log("tagId add : " + tagId);
		    	console.log("listCardId add : " + listCardId[0].cardId);
		    	 var json = {"tagId" :tagId, "listCardId" : listCardId};
		    	 addCardTag(json);
		    	// reloadICheck();
	       });
	       
	     //uncheck remove tag card
	        $(document).on('ifUnchecked', "#paging tbody input[type='checkbox']", function(e) {
	    	   $(this).parent().removeClass('not_chk');
		    	 $(this).parent().removeClass('checked');
	    	   var listCardId = [];
		    	var cardId = 0 ;
		    	$(".business_card_book .icheckbox_square-green.checked").each(function(){
	   	            cardId = $(this).find('input[name=bla]').val();
	   	            listCardId.push({"cardId":cardId});    	         
	   			});
		    	var tagId = $(this).val();
		    	console.log("tagId remove: " + tagId);
		    	console.log("listCardId remove : " + listCardId[0].cardId);
		    	 var json = {"tagId" : tagId, "listCardId" : listCardId};
		    	deleteCardTag(json);
		    	//reloadICheck();
	      });

	    
	 	// Card tag
		    $(document).on('click', '#paging tbody .icheckbox_square-green', function(e) {
		    	var listCardId = [];
		    	var cardId = 0 ;
		    	$(".business_card_book .icheckbox_square-green.checked").each(function(){
	   	            cardId = $(this).find('input[name=bla]').val();
	   	            listCardId.push({"cardId":cardId});    	         
	   			});
		    	var tagId = $(this).find("input[type=checkbox]").val();
		    	
		    	if($(this).attr("class").indexOf("not_chk") > 0){
		    		 console.log("tagId add : " + tagId);
				     console.log("listCardId add: " + listCardId[0].cardId);
				     $(this).removeClass('icheckbox_square-green not_chk');
			    	 $(this).removeClass('icheckbox_square-green some_chk');
		    		 $(this).removeClass("icheckbox_square-green hover");
		        	 $(this).addClass("icheckbox_square-green checked");
		        	 //Add card tag
		        	 var json = {"tagId" : tagId, "listCardId" : listCardId};
		        	 //deleteCardTag(json);
		     	     addCardTag(json);
		    	 }else {
			    		console.log("tagId remove : " + tagId);
					    console.log("listCardId remove: " + listCardId[0].cardId);
			    		 $(this).removeClass("icheckbox_square-green checked");
			    		 $(this).removeClass('icheckbox_square-green some_chk');
			    		 $(this).addClass("icheckbox_square-green not_chk"); 
			    		//Delete card tag
			        	 var json = {"tagId" :tagId, "listCardId" : listCardId};
			     	     deleteCardTag(json);
			    }
		     });
	 	
	 	// Delete tag   
	    $(document).on("click",".delTag",function(e){
	    	var tagId = $(this).parent().parent().find('input[type=checkbox]').val();
       		deleteTag(tagId);
         });
	
	 	
	 	$(document).on("click", "#clickToLoadMore", function(e){
	 		var currentNumberCard = $('.list-group .active').parent().find('.row-new').length;
	    	var self = $('.list-group .active').parent();
	 		
	 		var typeSort = $('#sort-card-cnd').val();
   		    var typeSearch = $("#selectSortBox option:selected").val();
   		    var strDate = self.attr("id");
   		    if(typeSort == 5){
            	strDate = strDate.slice(0,4)+"/"+strDate.slice(4,strDate.length+1);	
            }
   		   
   		   if(isLoading != 0){    			       			   
   			   return false;
   		   }
   			$("#loading-copy").show();
   			$("#clickToLoadMore").parent().remove();
   		   console.log("PAGE = "+ id_manager);
   			$.xhrPool.abortAll();
  		    $.ajax({
	    			type: 'POST',
	    			url: 'search',
	    			data: 'page=' +id_manager + "&typeSort=" +typeSort + "&typeSearch=" + typeSearch + "&valueSearch=" + strDate
	    		  }).done(function(resp, status, xhr) {
	    			   var listGroupItem = "";
	    			   totalCardInfo = resp.recordsTotal;
	    			   console.log("TotalCardInfo = "+ totalCardInfo);
	    				$.each( resp.data, function( key, value ) {
	    					 $.each( value.lstCardInfo, function (k,v) {
	    						 listGroupItem += '<div class="list-group-item pointer show-content">'
	    				    					+'<div class="row row-new">'
	    				    					+	'<div class="col-md-1 col-xs-1"><div class="icheckbox_square-green">'
	    				    					+    '<input type="checkbox" value='+v.cardId+' class="i-checks" name="bla" style="position: absolute; opacity: 0;">'
	    				    					+ 		'<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>'
	    				    					+	 '</div></div>'
	    				    					+	'<div class="col-md-5">'
	    				    					+		'<div class="col-xs-11 mg-top">'
	    				    					+ 			'<p class="name">'+ v.lastName + ' '+v.firstName +'</p>'
	    				    					+			'<p class="livepass">'+v.companyName+'</p>'
	    				    					+			'<p class="department_and_position">'+v.departmentName+' '+v.positionName+'</p>'
	    				    					+			'<p class="num">'+v.telNumberCompany+'</p>'
	    				    					+			'<p class="mail"><a href="#">'+v.email+'</a></p>'
	    				    					+ '</div></div>'
	    				    					+	'<div class="col-md-6">'
	    				    					+	'<div class="col-xs-5" style=" display: table;"></div>'	
	    				    					+	'<div class="col-xs-7">'								
	    				    					+	'<img src="<c:url value='/assets/img/loading.gif'/>" class=" lazy img-responsive img-thumb pull-right" name="'+v.imageFile+'" alt="Responsive image">'	
	    				    					+   '<input class="hidden" name="fileImageName" value='+v.imageFile+'>'
	    				    					+	'</div> </div> </div> </div>';
	    					 });
	    				});
	    				self.append(listGroupItem);
	    				if (currentNumberCard < totalCardInfo){
	    					appendLoadMore(self);
	    				}
	    				
	    				getImageSCP();
	    				id_manager++;
	    				$("#loading-copy").hide();
	    			}).fail(function(xhr, status, err) {
	    				$("#loading-copy").hide();
	    			});
	 		
	 		
	 	});
	   	function resetFreeText() {
	   		$("#freeText").text("");
	   		$("#freeText").val("");
	   	}
	   	
	   	function resetOthersInputText() {
	   		$("#owner").text("");
   	   		$("#company").text("");
   	   		$("#department").text("");
   	   		$("#position").text("");
   	   		$("#name").text("");
	   		
	   		$("#owner").val("");
   	   		$("#company").val("");
   	   		$("#department").val("");
   	   		$("#position").val("");
   	   		$("#name").val("");
   	   		
	   	}
	   	
	   	function setListSearch(cardId,firstName,lastName,companyName,departmentName,positionName,telNumberCompany,imageFile,email){
	   		$("#titleSearch").text($('#parameterFlg').find(":selected").text());
	   		$("#title-search-loadmore").text($('#parameterFlg').find(":selected").text());
	   		
	   		var checkBox="";
	   		if($("#titleSearch").text()!="自分の名刺"){	
	   			checkBox='<div class="icheckbox_square-green" style="display:none">';	
	   		}else{
	   			checkBox='<div class="icheckbox_square-green">';
	   		}
	   		var modal=	'<div class="list-group-item pointer">'
	   			+  '<div class="row row-new">'
	   			+	'<div class="col-md-1 col-xs-1">'
	   			+	 checkBox
	   			+		'<input type="checkbox" class="i-checks" name="bla" value='+cardId+'>'
	   			+	 '</div>'
	   			+	'</div>'
	   			+	'<div class="col-md-5">'
	   			+	 '<div class="col-xs-11 mg-top">'
	   			+		'<p class="name">'+lastName +  firstName+'</p>'
	   			+		'<p class="livepass">'+companyName+'</p>'
	   			+		'<p class="department_and_position">'+departmentName+' '+positionName+'</p>'
	   			+		'<p class="num">'+telNumberCompany+'</p>'
	   			+		'<p class="mail"><a href="#">'+email+'</a></p>'
	   			+	 '</div>'
	   			+	'</div>'
	   			+	'<div class="col-md-6">'
	   			+	 '<div class="col-xs-5" style=" display: table;">'
	   			+		'</div>'
	   			+           '<div class="col-xs-7">'
	   			+			'<img src="<c:url value="/assets/img/loading.gif"/>" name='+imageFile+' class="img-responsive img-thumb pull-right" alt="Responsive image">'
	   			+			'<input class="hidden" name="fileImageName" value='+imageFile+'>'
	   			+'</div> '
	   			+	'</div>'
	   			+ '</div>'
	   			+'</div>'
	   		return modal;
	   	}
	   	
	   	function setDataSearch(cards){
	   		$(".business_card_book .list-group").remove();
	   		var listGroup=$(".business_card_book").append(SetListGroupSearch());
	   		$.each( cards, function( key, data ) {
	   			$(".business_card_book .list-group").append(setListSearch(data.cardId,data.firstName,data.lastName,data.companyName,data.departmentName,data.positionName,data.telNumberCompany,data.imageFile,data.email));
	   			getImageFromSCP(data.imageFile); 
	   		});
	   	}
	   	
	   	function setDataSearchLoadMore(cards){
	   		$.each( cards, function( key, data ) {
	   			$(".business_card_book .list-group").append(setListSearch(data.cardId,data.firstName,data.lastName,data.companyName,data.departmentName,data.positionName,data.telNumberCompany,data.imageFile,data.email));
	   			getImageFromSCP(data.imageFile); 
	   		});
	   	}
	   	
	   	function SetListGroupSearch(){
	   		var data='<div class="list-group" id= "titleOfSearch" >'
	   			+'<div class="list-group-item-title" style="height:46px; display:none" >'
	   			+'<button type="button" class="close" data-dismiss="modal" id="btnCloseUserSearch">'
	   			+'<span aria-hidden="true">×</span>'
	   			+'</button>'
	   			+'<span id="titleSearch"></span>'
	   			+'</div>'
	   			+'</div>'
   			return data;
	   	}
	   	
	   	function disableBtnSort(){
	   		$("#selectSortBox").attr('style', "display:none !important");
	   		$("#sort-card-cnd").attr('style', "display:none !important");
	   		$(".btn-group").attr('style', "display:none !important");
	   		if($("#titleSearch").text()=="自分の名刺"){	
	   			$(".btn-group").removeAttr('style');	
	   		}
	   	}
	   	
	   	function disableTagSort(style) {
	   		var str = "display:"+style+" !important"
	   		$("#sort-card-cnd").attr('style', str);
	   		$("#selectTagBox").attr('style', str);	   		
	   	}
	   	
		function reloadICheck(){
			$('.i-checks').iCheck({
     	          checkboxClass: 'icheckbox_square-green',
     	          radioClass: 'iradio_square-green',                
      	    		});
		}
		
		function addCardTag(json){
	     	$.ajax({
	        	url: "<c:url value='/user/addCardTagHome' />",
	        	data: JSON.stringify(json),
	        	type: "POST",
	        	
	        	beforeSend: function(xhr) {
	        		xhr.setRequestHeader("Accept", "application/json");
	        		xhr.setRequestHeader("Content-Type", "application/json");
	        	},
	        	success: function(response) {
	        		var respHTML = "";
	        		var isChecked = "";
	        		$.each(response, function(index, value){
	        			var check = false;
						var same = false;
						var intSame = 0;
						var listCardId = [];
	        			$(".business_card_book .icheckbox_square-green.checked").each(function(){
	           	            cardId = $(this).find('input[name=bla]').val();
	           	            listCardId.push(parseInt(cardId));    	         
	           			});
						var countCard = listCardId.length;
	        			$.each(value["listCardIds"], function(idx, vtag){
	        				$.each(listCardId, function(idcard, vcard){
		        			if (vtag == vcard){
        					 	check = true;
        	                    same = true;
        	                    intSame++;
        	                    return false;
		        			}else{
		        					check = false;
		        				}
		        			});
	        				 if (intSame > 0 & countCard == 1) {
	        	 	  	    		return false;
	        	 	         }
	        			});
	        			 if (intSame == countCard) {
	        				 console.log("Check");
	        				 respHTML += "<tr id='rowData'>"
		        					+ "<td><div style='position: relative;' class='icheckbox_square-green checked' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag'>"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }else if (same) {
	        		        	console.log("Same");
	        		        	respHTML += "<tr id='rowData'>"
			    					+ "<td><div style='position: relative;' class='icheckbox_square-green some_chk' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }else
	        		        {
	        		        	console.log("Not");
	        		        	respHTML += "<tr id='rowData'>"
			    					+ "<td><div style='position: relative;' class='icheckbox_square-green not_chk' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }

	        		});
	        		$("#tagCardName").val('');
	        		$("#paging tbody").html("");
	        		$("#paging tbody").html(respHTML);    		
	        	},
	        	error: function(){
				  BootstrapDialog.show({
	   				title: 'Warning',
	  	             	message: 'Add card tag failed'
	   	      		});
			  	}
	        });	
		}
		
		function deleteCardTag(json){
	     	$.ajax({
	        	url: "<c:url value='/user/deleteCardTagHome' />",
	        	data: JSON.stringify(json),
	        	type: "POST",
	        	
	        	beforeSend: function(xhr) {
	        		xhr.setRequestHeader("Accept", "application/json");
	        		xhr.setRequestHeader("Content-Type", "application/json");
	        	},
	        	success: function(response) {
	        		var respHTML = "";
	        		var isChecked = "";
	        		$.each(response, function(index, value){
	        			var check = false;
						var same = false;
						var intSame = 0;
						var listCardId = [];
	        			$(".business_card_book .icheckbox_square-green.checked").each(function(){
	           	            cardId = $(this).find('input[name=bla]').val();
	           	            listCardId.push(parseInt(cardId));    	         
	           			});
						var countCard = listCardId.length;
	        			$.each(value["listCardIds"], function(idx, vtag){
	        				$.each(listCardId, function(idcard, vcard){
		        			if (vtag == vcard){
        					 	check = true;
        	                    same = true;
        	                    intSame++;
        	                    return false;
		        			}else{
		        					check = false;
		        			}
		        			});
	        				if (intSame > 0 & countCard == 1) {
        	 	  	    		return false;
        	 	           }
	        			});
	        			 if (intSame == countCard) {
	        				 console.log("Check");
	        				 respHTML += "<tr id='rowData'>"
		        					+ "<td><div style='position: relative;' class='icheckbox_square-green checked' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag'>"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }else if (same) {
	        		        	console.log("Same");
	        		        	respHTML += "<tr id='rowData'>"
			    					+ "<td><div style='position: relative;' class='icheckbox_square-green some_chk' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }else
	        		        {
	        		        	console.log("Not");
	        		        	respHTML += "<tr id='rowData'>"
			    					+ "<td><div style='position: relative;' class='icheckbox_square-green not_chk' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }

	        		});
	        		$("#tagCardName").val('');
	        		$("#paging tbody").html("");
	        		$("#paging tbody").html(respHTML);    		
	        	},
	        	error: function(){
				  BootstrapDialog.show({
	   				title: 'Warning',
	  	             	message: 'Delete card tag failed'
	   	      		});
			  	}
	        });	
		}
		
		function deleteTag(id){
			$('#selectTagBox').find('option[value!=0]').remove();
	     	$.ajax({
	        	url: "<c:url value='/user/deleteTag' />",
	        	data: 'tagId='+ id,
	        	type: "GET",
	        	
	        	beforeSend: function(xhr) {
	        		xhr.setRequestHeader("Accept", "application/json");
	        		xhr.setRequestHeader("Content-Type", "application/json");
	        	},
	        	success: function(response) {
	        		var respHTML = "";
	        		var isChecked = "";
	        		$.each(response, function(index, value){
	        			var check = false;
						var same = false;
						var intSame = 0;
						var listCardId = [];
	        			$(".business_card_book .icheckbox_square-green.checked").each(function(){
	           	            cardId = $(this).find('input[name=bla]').val();
	           	            listCardId.push(parseInt(cardId));    	         
	           			});
						var countCard = listCardId.length;
	        			$.each(value["listCardIds"], function(idx, vtag){
	        				$.each(listCardId, function(idcard, vcard){
		        			if (vtag == vcard){
        					 	check = true;
        	                    same = true;
        	                    intSame++;
        	                    return false;
		        			}else{
		        					check = false;
		        				}
		        			});
	        				if (intSame > 0 & countCard == 1) {
        	 	  	    		return false;
        	 	            }
	        			});
	        			 if (intSame == countCard) {
	        				 console.log("Check");
	        				 respHTML += "<tr id='rowData'>"
		        					+ "<td><div style='position: relative;' class='icheckbox_square-green checked' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag'>"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }else if (same) {
	        		        	console.log("Same");
	        		        	respHTML += "<tr id='rowData'>"
			    					+ "<td><div style='position: relative;' class='icheckbox_square-green some_chk' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }else{
	        		        	console.log("Not");
	        		        	respHTML += "<tr id='rowData'>"
			    					+ "<td><div style='position: relative;' class='icheckbox_square-green not_chk' id='"+value["tagId"]+"'>"
			        				+ "<input style='position: absolute; opacity: 0;' type='checkbox' class='i-checks' value='"+value["tagId"]+"' name='checkTag'>"
			        				+ "<ins style='position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; background: rgb(255, 255, 255) none repeat scroll 0% 0%; border: 0px none; opacity: 0;' class='iCheck-helper'></ins></div>"
			        				+ "<input type='hidden' name= 'userId'  value='"+value["userId"]+"'>"
	                                + " <input type='hidden' name= 'cardId'  value='"+value["cardId"]+"'>"
			    					+ "</td>"
			    					+ "<td class='nametag' >"+value["tagName"]+"</td>"
			    					+ "<td><a href='javascript:void(0);' class='delTag' id='"+value["tagId"]+"'><i class='fa fa-trash'></i></a></td></tr>";
	        		        }
	        			 	$('#selectTagBox').append('<option value="'+value["tagId"]+'">'+value["tagName"]+'</option>');
	        		});
	        		$("#tagCardName").val('');
	        		$("#paging tbody").html("");
	        		$("#paging tbody").html(respHTML);		
	        	},
	        	error: function(){
				  BootstrapDialog.show({
	   				title: 'Warning',
	  	             	message: 'Delete tag failed'
	   	      		});
			  	}
	        });	
		}
		
		function loadICheck(){
			 $('.i-checks').iCheck({
		       checkboxClass: 'icheckbox_square-green',
		       radioClass: 'iradio_square-green',                
		     });
		}
		
		function getImageSCP() {
			$(".business_card_book .list-group .active").parent().find('.img-responsive').each(function (e) {
			  	
				var target = $(this);
				if($(this).attr('src') == '<c:url value='/assets/img/loading.gif'/>' || $(this).attr('src') == '<c:url value='/assets/img/card_08.png' />'){
					isLoading=isLoading+1;
				    var fileImageName =$(this).parent().find('input[name=fileImageName]').val();
	
				    $.ajax({
				        type: 'POST',
				        url: "<c:url value='/user/getImageFile' />",
				        data: 'fileImage='+fileImageName
				    }).done(function(resp, status, xhr){
				    	if(resp == ""){
				    		target.attr('src','');    	  
					        target.attr("src", "<c:url value='/assets/img/card_08.png' />");
				    	} else {
				    		target.attr('src','');    	  
					        target.attr('src','data:image/png;base64,'+resp);	
				    	}
				    	isLoading=isLoading-1;
				    }).fail(function(resp, status, xhr){
				        //alert('Error');
				    });
				}
			});
		}
		
		function formatDate(date) {
		    var d = new Date(date),
		        month = '' + (d.getMonth() + 1),		        
		        year = d.getFullYear();

		    if (month.length < 2) month = '0' + month;

		    return year + "年" + month + "月";
		}
		
		function changeAphabetToHigrana(character){
			var index = arrayHirgana.indexOf(character); 
			if(index != -1){
				return arrayAphabet[index];					
			} else {
				return character;
			}
		}
		
		function appendLoadMore(self){
			self.append(
				'<div class="list-group-item pointer show-content" style="text-align: center;">'
		      	+' 	<a id="clickToLoadMore" style="color: red;"> 次の10件を表示</a>'
		      	+'</div>'
			);
		}

    </script>

    
