<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
      background: url(/ecard-webapp/assets/img/faq1.png) no-repeat left 15px;
   }
   .ul-left-li{
      background: url(/ecard-webapp/assets/img/faq2.png) no-repeat left 15px;
      

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
<div class="" style="border: solid 1px #f3f3f4;background: #e3e3e3;">
      <div class="row clearfix">        
          <div class="col-md-2 " style="width: 150px; display:inline-block">
            <div class="form-group">
                <div class="icon-addon addon-md">
                    <!-- <form name="myform">  -->
						<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal-example">自分の名刺を検索</button>
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
  	<h4>名刺一覧</h4>
  	<div class="row " style="margin-bottom: 10px">
          <div class="col-md-2 m-b-xs setDisplayTerm" style="width:180px;padding-right: 0 !important;">
            <select id="selectSortBox" class="input-sm form-control input-s-sm inline">
              <option value="0" selected>すべて</option>
              <option value="1" >最近取り込んだ名刺</option>
              <option value="2" >最近見た名刺</option>
              <option value="3" >データ入力待ち</option>
            </select>
          </div>
          
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
                <option value="5" selected>交換月順</option>
                <option value="1">氏名順</option>
                <option value="2">会社名順</option>
                <option value="6">タグ一覧から</option>                          
            </select>
          </div>
          
          
	  </div>
    <div class="business_card_book">
    <c:choose>
	     <c:when test="${empty searchDetail}">
		     <c:forEach var="nameSort" items="${lstNameSort}" varStatus="loopCount">
		   		<div class="list-group" style="margin-bottom: 0px !important" id="<c:out value='${nameSort}'/>">
		   			<c:if test="${loopCount.count == 1}">
			        	<div class="ul-left-li active list-group-item-title "><c:out value="${nameSort}" /></div>       		
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
			        </c:if>
			        <c:if test="${loopCount.count != 1}">
			       		<div class="ul-left-li list-group-item-title "><c:out value="${nameSort}" />		       			
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
      
      var searchDetail ='<c:out value="${searchDetail}" />';
      if(searchDetail!=null&&searchDetail!=""){
	   		var freeText = $("#hid-freeText").val();
	   		var owner = $("#hid-owner").val();
	   		var company = $("#hid-company").val();
	   		var department = $("hid-#department").val();
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
      
      var page = 1;
      var isLoading = 0;
      

 $(document).ready(function(){
	 $(document).on('click', '.list-group', function(event)  {
		// Hidden others and change icon
        $(".list-group" ).not($(this)).find('.list-group-item-title').removeClass('active');
        $(".list-group" ).not($(this)).find('.list-group-item ').removeClass('show-content');
        $(".list-group" ).not($(this)).find('.list-group-item ').addClass('no-show-content');
        // Show data end change icon
        if($(this).find('.list-group-item-title').hasClass('active')){
        	$(this).find('.list-group-item-title').removeClass('active');
            $(this).find('.list-group-item ').removeClass('show-content');
            $(this).find('.list-group-item ').addClass('no-show-content');
        } else {
        	$(this).find('.list-group-item-title').addClass('active');
            $(this).find('.list-group-item ').removeClass('no-show-content');
            $(this).find('.list-group-item ').addClass('show-content');	
        }
        if($(this).find('.list-group-item').length <= 0){
        	var self = $(this);
            var strDate = $(this).attr("id");
        	var typeSort = 5;
           	var typeSearch = $("#selectSortBox option:selected").val();
           	strDate = strDate.slice(0,2)+"/"+strDate.slice(2,strDate.length+1);
              $.ajax({
    			type: 'POST',
    			url: 'search',
    			data: 'page=' +strDate + "&typeSort=" +typeSort + "&typeSearch=" + typeSearch
    		  }).done(function(resp, status, xhr) {
    			   var listGroupItem = "";
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
    							 isLoading++;
    							 reloadICheck();
    							 getImageFromSCP(v.imageFile);
    					 });
    					 self.append(listGroupItem);
    				});
    				
    			}).fail(function(xhr, status, err) {
    				
    			});
        }
        
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
     $(document).on('click', '.list-group .icheckbox_square-green', function(e) {
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
         var typeSort =  $(this).val();
         if(parseInt(typeSort) == 0){
        	 $("#sort_cnd").show();
        	 $("#bulk_tag").show();
        	 $("#deleteTag").show();
         }else if(parseInt(typeSort) == 1){
        	 $("#sort_cnd").hide();
        	 $(".business_card_book").html("");
        	 $(".business_card_book").append('<div class="list-group-item pointer show-content">'
			   +'<div class="row row-new">'
	           +'<div class="col-md-1 col-xs-1">'
	           +'  <div class="icheckbox_square-green">'
	           +'   <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" class="i-checks" name="bla" value="5084" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div> </div> </div>'
	           +' <div class="col-md-5">'
	           +'   <div class="col-xs-11 mg-top">'
	           +'    <p class="name">吉岡  陽子</p>'
	           +'   <p class="livepass">東京健康スホーツ大学</p>'
	           +'   <p class="department_and_position"> </p>'
	           +'  <p class="num">03-4567-8901</p>'
	           +'  <p class="mail"><a href="#">meai@c4icom</a></p>'
	           +' </div>'
	           +' </div>'
	           +' <div class="col-md-6">'
	           +' <div class="col-xs-5" style=" display: table;">'
	           +'   </div>'
	           +'    <div class="col-xs-7">'
	           +'      <img src="data:image/png;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAD1AZADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD260srX7FB/o0P+rT+Cp/sNr/z7Q/9+6Sy/wCQfbf9cU/lVmgCv9htf+faH/v3R9htf+faH/v3ViigCv8AYbX/AJ9of+/dH2G1/wCfaH/v3ViigCv9htf+faH/AL90fYbX/n2h/wC/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/wBhtf8An2h/790fYbX/AJ9of+/dWKKAK/2G1/59of8Av3R9htf+faH/AL91YooAr/YbX/n2h/790fYbX/n2h/791YooAr/YbX/n2h/790fYbX/n2h/791YooAr/AGG1/wCfaH/v3R9htf8An2h/791YooAr/YbX/n2h/wC/dH2G1/59of8Av3ViigCv9htf+faH/v3R9htf+faH/v3ViigCv9htf+faH/v3R9htf+faH/v3ViigCv8AYbX/AJ9of+/dH2G1/wCfaH/v3ViigCv9htf+faH/AL90fYbX/n2h/wC/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/wBhtf8An2h/790fYbX/AJ9of+/dWKKAK/2G1/59of8Av3R9htf+faH/AL91YooAr/YbX/n2h/790fYbX/n2h/791YooAr/YbX/n2h/790fYbX/n2h/791YooAr/AGG1/wCfaH/v3R9htf8An2h/791YooAr/YbX/n2h/wC/dH2G1/59of8Av3ViigCv9htf+faH/v3WbaatpltD5D3MCOjv8n93562q8rv/APkK3X/Xd/8A0OgD0uw/5B9t/wBcU/lVmq1l/wAg+2/64p/KrNABRRRQAUUjHaKz9Q1jT9Mhjlu7yGBHkSFC7cb3PyCgDRoqPej/AMaUeYn99KAJKKzrHVbDVbb7TZXMNzCHZN6NxvSpby9tdOspL29mSC2hTe8j/cSgC5RVC51fT7Wxe9mvIEtUTe8m+pLTULXUbVLqzmSaB03o6P1oAt0Vm3mt6ZZWs91dX9tHBAm+R94+Snw6vp90kbw3sLo/3Nj/AH6AL9FFFABRRVI6lZvfTWP2qH7TCnmPDv8AnRP71AF2is3S9b0/W4JJtPukuUR9jlK0qACiiigAooooAKKoadqtlq9r9psLiO5h3sm9P76mpri6hsrWS6uZkhhRN7yO/wAiUAWaKzYtV064upLWG7heaJEleNW+6j/ceprHULTVbKO+sLmO5t3HySRv8j0AXKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACvK7//AJCt1/13f/0OvVK8rv8A/kK3X/Xd/wD0OgD0uw/5B9t/1xT+VWarWX/IPtv+uKfyqzQAUUUUAU9RtbW+0+e2vYI54HT50k+49fM96ZdR8M6fqH9hJD51zD88GjPGj/P/AAPv+evpjUp3tNOnmis3vHVP9Sn33rxTV/hVdX9rDeyaQmn7r2GNdM0+bf5MG/53d/43oA0PBMEJ8epp9xoVpbeTZPexyPpD2cyPv2fJ+8ep/GOn6MfHT2B03w9BJNZfbZLr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" class="img-responsive img-thumb pull-right" alt="Responsive image">'
	           +'      <input class="hidden" name="fileImageName" value="P1510010000021700S.jpg">'
	           +'    </div> '
	           +'</div></div></div>');
        	 
         }else if (parseInt(typeSort) == 2){
        	 $("#sort_cnd").hide();
        	 $("#bulk_tag").hide();
        	 $("#deleteTag").hide();
        	 $(".business_card_book").html("");
        	 $(".business_card_book").append('<div class="list-group-item pointer show-content">'
			   +'<div class="row row-new">'
	           +'<div class="col-md-1 col-xs-1"></div>'
	           +' <div class="col-md-5">'
	           +'   <div class="col-xs-11 mg-top">'
	           +'    <p class="name">吉岡  陽子</p>'
	           +'   <p class="livepass">東京健康スホーツ大学</p>'
	           +'   <p class="department_and_position"> </p>'
	           +'  <p class="num">03-4567-8901</p>'
	           +'  <p class="mail"><a href="#">meai@c4icom</a></p>'
	           +' </div>'
	           +' </div>'
	           +' <div class="col-md-6">'
	           +' <div class="col-xs-5" style=" display: table;">'
	           +'   </div>'
	           +'    <div class="col-xs-7">'
	           +'      <img src="data:image/png;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAD1AZADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD260srX7FB/o0P+rT+Cp/sNr/z7Q/9+6Sy/wCQfbf9cU/lVmgCv9htf+faH/v3R9htf+faH/v3ViigCv8AYbX/AJ9of+/dH2G1/wCfaH/v3ViigCv9htf+faH/AL90fYbX/n2h/wC/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/wBhtf8An2h/790fYbX/AJ9of+/dWKKAK/2G1/59of8Av3R9htf+faH/AL91YooAr/YbX/n2h/790fYbX/n2h/791YooAr/YbX/n2h/790fYbX/n2h/791YooAr/AGG1/wCfaH/v3R9htf8An2h/791YooAr/YbX/n2h/wC/dH2G1/59of8Av3ViigCv9htf+faH/v3R9htf+faH/v3ViigCv9htf+faH/v3R9htf+faH/v3ViigCv8AYbX/AJ9of+/dH2G1/wCfaH/v3ViigCv9htf+faH/AL90fYbX/n2h/wC/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/wBhtf8An2h/790fYbX/AJ9of+/dWKKAK/2G1/59of8Av3R9htf+faH/AL91YooAr/YbX/n2h/790fYbX/n2h/791YooAr/YbX/n2h/790fYbX/n2h/791YooAr/AGG1/wCfaH/v3R9htf8An2h/791YooAr/YbX/n2h/wC/dH2G1/59of8Av3ViigCv9htf+faH/v3WbaatpltD5D3MCOjv8n93562q8rv/APkK3X/Xd/8A0OgD0uw/5B9t/wBcU/lVmq1l/wAg+2/64p/KrNABRRRQAUUjHaKz9Q1jT9Mhjlu7yGBHkSFC7cb3PyCgDRoqPej/AMaUeYn99KAJKKzrHVbDVbb7TZXMNzCHZN6NxvSpby9tdOspL29mSC2hTe8j/cSgC5RVC51fT7Wxe9mvIEtUTe8m+pLTULXUbVLqzmSaB03o6P1oAt0Vm3mt6ZZWs91dX9tHBAm+R94+Snw6vp90kbw3sLo/3Nj/AH6AL9FFFABRRVI6lZvfTWP2qH7TCnmPDv8AnRP71AF2is3S9b0/W4JJtPukuUR9jlK0qACiiigAooooAKKoadqtlq9r9psLiO5h3sm9P76mpri6hsrWS6uZkhhRN7yO/wAiUAWaKzYtV064upLWG7heaJEleNW+6j/ceprHULTVbKO+sLmO5t3HySRv8j0AXKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACvK7//AJCt1/13f/0OvVK8rv8A/kK3X/Xd/wD0OgD0uw/5B9t/1xT+VWarWX/IPtv+uKfyqzQAUUUUAU9RtbW+0+e2vYI54HT50k+49fM96ZdR8M6fqH9hJD51zD88GjPGj/P/AAPv+evpjUp3tNOnmis3vHVP9Sn33rxTV/hVdX9rDeyaQmn7r2GNdM0+bf5MG/53d/43oA0PBMEJ8epp9xoVpbeTZPexyPpD2cyPv2fJ+8ep/GOn6MfHT2B03w9BJNZfbZLr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" class="img-responsive img-thumb pull-right" alt="Responsive image">'
	           +'      <input class="hidden" name="fileImageName" value="P1510010000021700S.jpg">'
	           +'    </div> '
	           +'</div></div></div>');
         }else if (parseInt(typeSort) == 3){
        	 $("#sort_cnd").hide();
        	 $("#bulk_tag").hide();
        	 $("#deleteTag").hide();
        	 $(".business_card_book").html("");
        	 $(".business_card_book").append('<div class="list-group-item pointer show-content">'
			   +'<div class="row row-new">'
	           +'<div class="col-md-1 col-xs-1"></div>s'
	           +' <div class="col-md-5">'
	           +'   <div class="col-xs-11 mg-top">'
	           +'   <p class="livepass">東京健康スホーツ大学</p>'
	           +'  <p class="num">03-4567-8901</p>'
	           +' </div>'
	           +' </div>'
	           +' <div class="col-md-6">'
	           +' <div class="col-xs-5" style=" display: table;">'
	           +'   </div>'
	           +'    <div class="col-xs-7">'
	           +'      <img src="data:image/png;base64,/9j/4AAQSkZJRgABAgAAAQABAAD/2wBDAAgGBgcGBQgHBwcJCQgKDBQNDAsLDBkSEw8UHRofHh0aHBwgJC4nICIsIxwcKDcpLDAxNDQ0Hyc5PTgyPC4zNDL/2wBDAQkJCQwLDBgNDRgyIRwhMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjIyMjL/wAARCAD1AZADASIAAhEBAxEB/8QAHwAAAQUBAQEBAQEAAAAAAAAAAAECAwQFBgcICQoL/8QAtRAAAgEDAwIEAwUFBAQAAAF9AQIDAAQRBRIhMUEGE1FhByJxFDKBkaEII0KxwRVS0fAkM2JyggkKFhcYGRolJicoKSo0NTY3ODk6Q0RFRkdISUpTVFVWV1hZWmNkZWZnaGlqc3R1dnd4eXqDhIWGh4iJipKTlJWWl5iZmqKjpKWmp6ipqrKztLW2t7i5usLDxMXGx8jJytLT1NXW19jZ2uHi4+Tl5ufo6erx8vP09fb3+Pn6/8QAHwEAAwEBAQEBAQEBAQAAAAAAAAECAwQFBgcICQoL/8QAtREAAgECBAQDBAcFBAQAAQJ3AAECAxEEBSExBhJBUQdhcRMiMoEIFEKRobHBCSMzUvAVYnLRChYkNOEl8RcYGRomJygpKjU2Nzg5OkNERUZHSElKU1RVVldYWVpjZGVmZ2hpanN0dXZ3eHl6goOEhYaHiImKkpOUlZaXmJmaoqOkpaanqKmqsrO0tba3uLm6wsPExcbHyMnK0tPU1dbX2Nna4uPk5ebn6Onq8vP09fb3+Pn6/9oADAMBAAIRAxEAPwD260srX7FB/o0P+rT+Cp/sNr/z7Q/9+6Sy/wCQfbf9cU/lVmgCv9htf+faH/v3R9htf+faH/v3ViigCv8AYbX/AJ9of+/dH2G1/wCfaH/v3ViigCv9htf+faH/AL90fYbX/n2h/wC/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/wBhtf8An2h/790fYbX/AJ9of+/dWKKAK/2G1/59of8Av3R9htf+faH/AL91YooAr/YbX/n2h/790fYbX/n2h/791YooAr/YbX/n2h/790fYbX/n2h/791YooAr/AGG1/wCfaH/v3R9htf8An2h/791YooAr/YbX/n2h/wC/dH2G1/59of8Av3ViigCv9htf+faH/v3R9htf+faH/v3ViigCv9htf+faH/v3R9htf+faH/v3ViigCv8AYbX/AJ9of+/dH2G1/wCfaH/v3ViigCv9htf+faH/AL90fYbX/n2h/wC/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/2G1/59of+/dH2G1/59of+/dWKKAK/wBhtf8An2h/790fYbX/AJ9of+/dWKKAK/2G1/59of8Av3R9htf+faH/AL91YooAr/YbX/n2h/790fYbX/n2h/791YooAr/YbX/n2h/790fYbX/n2h/791YooAr/AGG1/wCfaH/v3R9htf8An2h/791YooAr/YbX/n2h/wC/dH2G1/59of8Av3ViigCv9htf+faH/v3WbaatpltD5D3MCOjv8n93562q8rv/APkK3X/Xd/8A0OgD0uw/5B9t/wBcU/lVmq1l/wAg+2/64p/KrNABRRRQAUUjHaKz9Q1jT9Mhjlu7yGBHkSFC7cb3PyCgDRoqPej/AMaUeYn99KAJKKzrHVbDVbb7TZXMNzCHZN6NxvSpby9tdOspL29mSC2hTe8j/cSgC5RVC51fT7Wxe9mvIEtUTe8m+pLTULXUbVLqzmSaB03o6P1oAt0Vm3mt6ZZWs91dX9tHBAm+R94+Snw6vp90kbw3sLo/3Nj/AH6AL9FFFABRRVI6lZvfTWP2qH7TCnmPDv8AnRP71AF2is3S9b0/W4JJtPukuUR9jlK0qACiiigAooooAKKoadqtlq9r9psLiO5h3sm9P76mpri6hsrWS6uZkhhRN7yO/wAiUAWaKzYtV064upLWG7heaJEleNW+6j/ceprHULTVbKO+sLmO5t3HySRv8j0AXKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACvK7//AJCt1/13f/0OvVK8rv8A/kK3X/Xd/wD0OgD0uw/5B9t/1xT+VWarWX/IPtv+uKfyqzQAUUUUAU9RtbW+0+e2vYI54HT50k+49fM96ZdR8M6fqH9hJD51zD88GjPGj/P/AAPv+evpjUp3tNOnmis3vHVP9Sn33rxTV/hVdX9rDeyaQmn7r2GNdM0+bf5MG/53d/43oA0PBMEJ8epp9xoVpbeTZPexyPpD2cyPv2fJ+8ep/GOn6MfHT2B03w9BJNZfbZLr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" class="img-responsive img-thumb pull-right" alt="Responsive image">'
	           +'      <input class="hidden" name="fileImageName" value="P1510010000021700S.jpg">'
	           +'    </div> '
	           +'</div></div></div>');
         }
     });

       /* $('#sort-card-cnd').on('change', function() {
       	$.xhrPool.abortAll();
        $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
       	var typeSort = $(this).val();
       	var typeSearch = $("#selectSortBox option:selected").val();
       	id_manager = 0;
          $.ajax({
			type: 'POST',
			url: 'search',
			data: 'page=' +id_manager + "&typeSort=" +typeSort + "&typeSearch=" + typeSearch
		  }).done(function(resp, status, xhr) {
			 $('.business_card_book').html("");
			   var listGroup = "";
			   var listGroupItem = "";
				$.each( resp.data, function( key, value ) {
					if(value.nameSort.replace("/","").trim()==""){
						value.nameSort="NULL";
						listGroup = $('.business_card_book').append(
								'<div class="list-group" style="margin-bottom: 0px !important; margin-top: 10px !important;" id="'+value.nameSort.replace("/","").trim()+'">'
						        +'<div class="list-group-item-title">'+"#"+'</div></div>');
					}else
						{
						listGroup = $('.business_card_book').append(
								'<div class="list-group" style="margin-bottom: 0px !important; margin-top: 10px !important;" id="'+value.nameSort.replace("/","").trim()+'">'
						        +'<div class="list-group-item-title">'+value.nameSort+'</div></div>');
						}
					
					 $.each( value.lstCardInfo, function (k,v) {
						 listGroupItem += '<div class="list-group-item pointer">'
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
				    					+	'</div> </div> </div> </div>';				    		 
	    					 $('.business_card_book').find("#"+value.nameSort.replace("/","").trim()).append(listGroupItem);    
							 isLoading++;
							 reloadICheck();
							 getImageFromSCP(v.imageFile);
					 });
					 
				});
				
				id_manager++;
			}).fail(function(xhr, status, err) {
				
			});
       }); */
       
       /* $('#selectSortBox').on('change', function(event) {
    	   
    	   if($("#selectSortBox option:selected").val() != 0){
    		   disableTagSort("none");   
    	   } else {
    		   disableTagSort("block");
    	   }
    	   
    	   
    	   $.xhrPool.abortAll();
    	   $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
    	   var tagName = $("#selectSortBox option:selected").text(); 
    	   var typeSort = $("#sort-card-cnd option:selected").val();
          	var typeSearch = $("#selectSortBox option:selected").val();    	   
		   id_manager= 0;
		   $.ajax({
			type: 'POST',
			url: 'search',
			data: 'page=' +id_manager + "&typeSort=" +typeSort + "&typeSearch=" + typeSearch
		  }).done(function(resp, status, xhr) {
			 $('.business_card_book').html("");
			   var str = "";
				$.each( resp.data, function( key, value ) {	
					str = $('.business_card_book').append(
						'<div class="list-group" style="margin-bottom: 0px !important; margin-top: 10px !important;" id= "'+value.nameSort.replace("/","").trim()+'">'
				        +'<div class="list-group-item-title">'+value.nameSort+'</div>');
					 $.each( value.lstCardInfo, function (k,v) {
							isLoading = isLoading + 1;							 		
								str.append(	'<div class="list-group-item pointer">'
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
							 isLoading++;
							 reloadICheck();
							 getImageFromSCP(v.imageFile);
					 });
				});
				id_manager++;
			}).fail(function(xhr, status, err) {
				
			});
    	   
       }); */
       
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
		   		
		   	 })
		   	 
});/* END READY DOCUMENT  */
 
      
      function ListSearch(freeText,owner,company,department,position,name,parameterFlg){
    	  	disableBtnSort();
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
 			    	setDataSearch(data);
 			    	$("#titleSearch").text($('#parameterFlg').find(":selected").text());
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
			var target = $('img[name="'+fileImageName+'"]');			
			$.ajax({
    	        type: 'POST',
    	        url: 'getImageFile',
    	        data: 'fileImage='+fileImageName,    	        
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
	   	
	   	$(document).on('click', '.business_card_book .list-group-item', function() {
        	cardId = parseInt($(this).find('input[name=bla]').val());
            window.location.href = '<c:url value="/user/card/details/'+cardId+'"/>';
	       }).hover(function() {
	         $(this).toggleClass('hover');
        });
	 
       $(document).on('ifChecked','.business_card_book input[name=bla]',function(event) {
    	   //$(".balloon").css("display","block");
           $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
	  	     
        });
        
       $(document).on('ifUnchecked','.business_card_book input[name=bla]',function(event){  
        	$(".balloon").css("display","none");
          if($(".icheckbox_square-green.checked").size() == 1){ 
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
		        	 deleteCardTag(json);
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
	   		var modal=	'<div class="list-group-item pointer">'
	   			+  '<div class="row row-new">'
	   			+	'<div class="col-md-1 col-xs-1">'
	   			+	 '<div class="icheckbox_square-green" style="display:none">'
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
	   		var data='<div class="list-group" id= "titleOfSearch">'
	   			+'<div class="list-group-item-title" style="height:46px">'
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
	   		$(".btn-group").attr('style', "display:none !important");
	   		$("#sort-card-cnd").attr('style', "display:none !important");
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

    </script>
