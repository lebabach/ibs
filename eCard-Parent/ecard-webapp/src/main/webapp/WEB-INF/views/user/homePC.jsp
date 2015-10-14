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
    margin-bottom: 4px;
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
        
          <div class="col-md-2" style="width: 150px; display:inline-block">
            <div class="form-group">
                <div class="icon-addon addon-md">
                    <!-- <form name="myform">  -->
						<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal-example">検索画面</button>
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
                                            <div class="modal-header" id="lsUserSearchs">
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
          <div class="col-md-2 m-b-xs setDisplayTerm" style="width:188px; display:inline-block">
            <select id="selectSortBox" class="input-sm form-control input-s-sm inline">
              <option value="0" selected>すべて</option>
              <c:forEach var="cardTag" items="${listTagGroup}">
              	<option value="${cardTag.tagId}"><c:out value="${cardTag.tagName}"/></option>
              </c:forEach>
                                        
            </select>
          </div>
         
          <div class="col-md-2 setDisplayTerm" style="float:right; margin-right:0;">
              <select class="input-sm form-control input-s-sm inline" id = "sort-card-cnd" >
                <option value="5" selected>名刺交換日順</option>
                <option value="1">氏名順</option>
                <option value="2">会社名順</option>                          
            </select>
          </div>    
          <div class="col-md-2 m-b-xs setDisplayTerm" style="float:right; width:155px; margin-right:0">
            <!--<img src="img/img-p-c-1.png" class="img-p-c-1" style="float:left; margin-top:-3px;">-->
            <div class="btn-group" role="group" aria-label="..." style="float:right;">
              <button id="addTag" class="btn btn-primary disabled" type="button">                  
                <i class="fa fa-tag"></i>               
              </button>
              <div class="balloon lbl_balloon" style="display: none;">
                <!--<div class="lbl_srh">
                  <dl class="srhbox_round">
                    <dt><a class="search_tag_index_btn" href="javascript: void(0);"></a><i class="fa fa-search"></i></dt>
                    <dd><span class="iptxt"><input type="text" class="search_tag_index" value="" style="color: rgb(171, 171, 171);"></span></dd>
                  </dl>
                </div>-->
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
                
                <div class="">
                  <div class="input-group lbl" style="padding: 15px;">
                    <input type="text" class="form-control" id ="tagCardName" placeholder="新規タグを追加">
                      <span class="input-group-btn"> 
                      <button id="addLabel" type="button" class="btn btn-success">作成</button> 
                      </span>
                  </div>
                </div>
              </div>               
              <button id="deletePeople" class="btn btn-primary disabled" type="button">
                <i class="fa fa-trash"></i></button>
            </div>
            
          </div>
              
     
      </div>
    </div>
  
  <!--  End Header -->

  <!-- Start Container -->
  <div id="container" class="container" style= "padding-top: 20px !important;" >
    <div class="business_card_book">
    <!-- for here  -->
    <c:choose>
	    <c:when test="${empty searchDetail}">
		      <c:forEach var="cardInfoPCVo" items="${lstCardInfoPCVo}">
			      <div class="list-group" style="margin-bottom: 10px !important;" id= "<c:out value='${cardInfoPCVo.nameSort}' />">
			        <div class="list-group-item-title"><c:out value="${cardInfoPCVo.nameSort}" /></div>
			        <!-- for item here  -->
			        <c:forEach var="cardInfo" items="${cardInfoPCVo.lstCardInfo}">
				        <div class="list-group-item pointer">
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
    
      <!-- end eaxh  -->
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
      $(window).scroll(function() {     	  
    	  if($('.row-new').length < parseInt(totalCardInfo)){
    		   var typeSort = $('#sort-card-cnd').val();
    		   var typeSearch = $("#selectSortBox option:selected").val();
    		   /* if(isLoading != 0){    			   
    			   $('body').scrollTop($(window).height()*2);
    			   return false;
    		   }  */
	    	   if($(window).scrollTop() + $(window).height()  >= ($(document).height())) {
	    	    	// Call ajax here	
	    	    	if(!$('#titleOfSearch').length){
	    	    		request = $.ajax({
							type: 'POST',
							url: 'search',
							data: 'page=' +id_manager + "&typeSort=" +typeSort
						}).done(function(resp, status, xhr) {
							var lastDate = $('.business_card_book .list-group').last().attr("id");
							$.each( resp.data, function( key, value ) {
								 if(value.nameSort.replace("/","").trim() == lastDate.trim()){
									 $.each( value.lstCardInfo, function (k,v) {									 
										 createTableHasGroup(lastDate, v);
										 reloadICheck();
										 isLoading=isLoading+1;
										 getImageFromSCP(v.imageFile); 
									 });
								 } else {
									 $.each( value.lstCardInfo, function (k,v) {
										 var lastDate = $('.business_card_book .list-group').last().attr("id");
										if(value.nameSort.replace("/","").trim() != lastDate.trim()){											
											 createTableNoGroup(value.nameSort, v);	 
											 reloadICheck();
											 isLoading=isLoading+1;
											 getImageFromSCP(v.imageFile);
										 }else{										 
											 createTableHasGroup(lastDate, v);
											 reloadICheck();
											 isLoading=isLoading+1;
											 getImageFromSCP(v.imageFile);
										 }
									 });
								 }
								 
							});
							
							
						}).fail(function(xhr, status, err) {
							//alert('Error');
						});
		        	    id_manager++;
	    	    	}else{
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
		       			    	setDataSearchLoadMore(data);
		       			    }
		       			});
	    	    	}
	        		
	    	    } 
    	  }
    	}); 

 $(document).ready(function(){
    	     	  
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
    			$(".icheckbox_square-green").find('.checked').each(function(){
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

       $('#sort-card-cnd').on('change', function() {
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
				//alert('Error');
			});
       });
       
       $('#selectSortBox').on('change', function(event) {
    	   $.xhrPool.abortAll();
    	   $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
    	   var tagName = $("#selectSortBox option:selected").text(); 
    	   var typeSort = $("#sort-card-cnd option:selected").val();
          	var typeSearch = $("#selectSortBox option:selected").val();    	   
    	   // If tagId == 0 => sortAll
		   // tagId != 0 => sortByTagName
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
				//alert('Error');
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
	   				return false;
	   			}
	   		});
	   		setDisplayResearch(freeText,owner,company,department,position,name,0);
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
    	   $(".balloon").css("display","none");
           $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
	  	     
        });
        
        $(document).on('ifUnchecked','.business_card_book input',function(event){  
        	$(".balloon").css("display","none");
          if($(".icheckbox_square-green").find('.checked').size() == 1){ 
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
        	$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
 	  	       if (check && intSame ==countCard) {
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
	   		return modal;
	   	}
	   	

	    // Add card tag
	     $('#addLabel').click(function(event) {
	       $.xhrPool.abortAll();
	    	var listCardId = [];
	    	var cardId = 0 ;
	    	$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
							$('#selectSortBox').find('option[value!=0]').remove();
							
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
				        			$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
					        			 if (check && intSame == countCard) {
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
					        			 
					        			 $('#selectSortBox').append('<option value="'+value["tagId"]+'">'+value["tagName"]+'</option>');					        			    
					        			


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
	     $("#paging input[type='checkbox']").on('ifChecked', function(event){
	    	 $(this).parent().removeClass('not_chk');
	    	 $(this).parent().removeClass('some_chk');
	    	 var listCardId = [];
		    	var cardId = 0 ;
		    	$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
	       $("#paging input[type='checkbox']").on('ifUnchecked', function(event){ 
	    	   $(this).parent().removeClass('not_chk');
		    	 $(this).parent().removeClass('checked');
	    	   var listCardId = [];
		    	var cardId = 0 ;
		    	$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
		    $(document).on('click', '#paging .icheckbox_square-green', function(e) {
		    	var listCardId = [];
		    	var cardId = 0 ;
		    	$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
	        			$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
	        			 if (check && intSame == countCard) {
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
	        			$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
	        			 if (check && intSame == countCard) {
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
			$('#selectSortBox').find('option[value!=0]').remove();
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
	        			$(".business_card_book .icheckbox_square-green").find('.checked').each(function(){
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
	        			 if (check  && intSame == countCard) {
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
	        			 	$('#selectSortBox').append('<option value="'+value["tagId"]+'">'+value["tagName"]+'</option>');
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
