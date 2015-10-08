<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

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
  .navbar-right{
    margin-right: -40px;
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
            <select id="selectSortBox" class="input-sm form-control input-s-sm inline" id="sort-card-connect">
              <option value="0" selected>ラベルで絞り込み</option>
              <option value="1">​つ​な​が​っ​て​い​る​人​</option>
              <option value="2">​ラ​ベ​ル​な​し​</option>                          
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
                  <div class="col-sm-12" style="border-bottom: solid 1px #c1c1c1;">
                    <table class="table" id="paging">
                    <!-- <table class="" id="paging" style="width: 100%;max-width: 100%;margin-bottom:10px">                      -->
                      <col width="10%">
                      <col width="80%">
                      <col width="10%">
                      <tbody>
                          <tr id="rowData">
                            <td><input type="checkbox" class="i-checks" id="1"></td>                  
                            <td class="nametag">Mark111111111</td>
                            <td><a href="#" class="delTag"><i class="fa fa-trash"></i></a></td>
                          </tr>
                          <tr id="rowData">
                            <td><input type="checkbox" class="i-checks" id="1"></td>                  
                            <td class="nametag">22222222222222222244444444222244444</td>
                            <td><a href="#" class="delTag"><i class="fa fa-trash"></i></a></td>
                          </tr>
                          <tr id="rowData">
                            <td><input type="checkbox" class="i-checks " id="1"></td>                  
                            <td>33333</td>
                            <td><a href="#" class="delTag"><i class="fa fa-trash"></i></a></td>
                          </tr>
                      </tbody>
                    </table>
                </div>
                </div>
                
                <div class="">
                  <div class="input-group lbl" style="padding: 15px;">
                    <input type="text" class="form-control" placeholder="新規タグを追加">
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

    <div class="searchTargetSwitcher">
      <div class="clearfix">
      <a href="javascript:void(0)" class="BusinessCardList active"> あなたの名刺 </a>
      <div class="ManagerSearch_box" style="display: none;">
        <div class="lbl_srh">
          <dl class="srhbox_round">
            <dt><a class="search_tag_index_btn" href="javascript: void(0);"></a><i class="fa fa-search"></i></dt>
            <dd><span class="iptxt"><input type="text" class="search_tag_index" value="" style="color: rgb(171, 171, 171);"></span></dd>
          </dl>
        </div>
        <div class="">
          <div class="col-sm-12" style="border-bottom: solid 1px #c1c1c1;">
            <table class="table" id="mSearch">
              <col width="10%">
              <col width="90%">              
              <tbody>
                  <tr id="rowData_1">
                    <td><input type="checkbox" class="i-checks"></td>                  
                    <td class="nametag"></td>
                  </tr>
                  <tr id="rowData_2">
                    <td><input type="checkbox" class="i-checks"></td>                  
                    <td class="nametag"></td>
                  </tr>
                  <tr id="rowData_3">
                    <td><input type="checkbox" class="i-checks "></td>                  
                    <td class="nametag"></td>
                  </tr>
                  <tr id="rowData_4">
                    <td><input type="checkbox" class="i-checks "></td>                  
                    <td class="nametag"></td>
                  </tr>
                  <tr id="rowData_5">
                    <td><input type="checkbox" class="i-checks "></td>                  
                    <td class="nametag"></td>
                  </tr>
              </tbody>
            </table>
        </div>
        </div>
        
        <div class="" style="margin-top:5px">
          <div class="ac">
            <p><a href="#">選択した検索条件を使用する</a></p>
            <p><a href="#">選択した検索条件を削除</a></p>
          </div>
        </div>
      </div> 
      <!-- <a class="export"> Export CSV</a> -->
    </div>
    </div>
    
 

  
  <!--  End Header -->

  <!-- Start Container -->
  <div id="container" class="container">
    <div class="business_card_book">
    <!-- for here  -->
    <c:forEach var="cardInfoPCVo" items="${lstCardInfoPCVo}">
		      <div class="list-group" id= "<c:out value='${cardInfoPCVo.nameSort}' />">
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
      <!-- end eaxh  -->
    </div>
</div>

<!--  End Header -->

  <!-- End Container -->
    
<script>
     var request = null;
      var id_manager = 1;
      var totalCardInfo = '<c:out value="${totalCardInfo}"/>';
      var page = 1;
      var isLoading = 0;
      $(window).scroll(function() {     	  
    	  if($('.row-new').length < parseInt(totalCardInfo)){
    		   var typeSort = $('#sort-card-cnd').val();
    		   /* if(isLoading != 0){    			   
    			   $('body').scrollTop($(window).height()*2);
    			   return false;
    		   }  */
	    	   if($(window).scrollTop() + $(window).height()  >= ($(document).height())) {
	    	    	// Call ajax here	    	   		
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
       
      /*  $(document).on('ifChecked','input[name=bla]',function(event) {
         $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
       });
       
       $(document).on('ifUnchecked','input',function(event){     
         if($(".icheckbox_square-green").find('.checked').size() == 1){
           $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
           $(".addTagCard").css("display","none");  
         }          
       }); */

       // Process add tag and delete
       $("#deletePeople").click(function(e){
         
       });

    /*    $(document).on('click','#addTag',function(e){
         if($(".balloon").css('display') == 'block')
           $(".balloon").css("display","none");
         else
           $(".balloon").css("display","block");
       }); */

       $('.makefriend').click(function(e){
         if($(this).find('button').hasClass('btn-success')){
           $(this).find('button').removeClass('btn-success');
           $(this).find('button').addClass('btn-default');
           $(this).find('button').text("取り消す");
           return false;
         } 
          
         if($(this).find('button').hasClass('btn-default')){
           $(this).find('button').removeClass('btn-default');
           $(this).find('button').addClass('btn-success');
           $(this).find('button').text("追加");
           return false;
         }

       });
       $('.mail').click(function(e) {
         console.log('Go to mailbox');
         e.stopPropagation();
       });

       // Click to personal details page
       

       // Sort name card
       $('.clearfix a').click(function(e) {
         $(this).parent().find('a').removeClass("active");
         $(this).addClass("active");

         // Process with Business Card List
         if($(".BusinessCardList").hasClass("active")) {                
           $(".business_card_book").css("display", "block");
           $(".peopleSearchResults").css("display", "none");
           $(".network_card_book").css("display", "none");                
           $(".setDisplayTerm").css("display","block");
           $(".network_card_book").css("display", "none");
           $(".manager_search_card").css("display","none");
           $(".ManagerSearch_box").css("display","none");
         }

         // Process with IBS Card List
         if($(".IBSNetworkCardList").hasClass("active")) {              
           $(".business_card_book").css("display", "none");
           $(".peopleSearchResults").css("display", "block");
           $(".network_card_book").css("display", "none");
           $(".setDisplayTerm").css("display","none");
           $(".IBSNetworkCardList_popup").css("display","none");
           $(".manager_search_card").css("display","none");
           $(".ManagerSearch_box").css("display","none");
         }

         // if($(".ManagerSearch").hasClass("active")) {
         //   // $(".business_card_book").css("display", "none");
         //   // $(".peopleSearchResults").css("display", "none");
         //   // $(".setDisplayTerm").css("display","none");
         //   // $(".manager_search_card").css("display","block");
         //   // $(".searchTargetSwitcher").css("display", "none");
         // }
       });
       // Remove tag
       
       
       $('#sort-card-cnd').on('change', function() {
       	$.xhrPool.abortAll();
       	var typeSort = $(this).val();
       	id_manager = 0;
          $.ajax({
			type: 'POST',
			url: 'search',
			data: 'page=' +id_manager + "&typeSort=" +typeSort
		}).done(function(resp, status, xhr) {
			 $('.business_card_book').html("");
			   var str = "";
				$.each( resp.data, function( key, value ) {	
					str = $('.business_card_book').append(
						'<div class="list-group" style="margin-bottom: 0px !important; margin-top: 20px !important;" id= "'+value.nameSort.replace("/","").trim()+'">'
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
    				debugger;
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
          $( "#btn-success2, #btn-success3, #close-x" ).click(function() {
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
           
           
           
           
        
});/* END READY DOCUMENT  */
 
 
 		
      // Manager Search 
      $(".ManagerSearch").click(function(event) {        
        if($(".ManagerSearch").hasClass("active")) {
          $(".ManagerSearch_box").css("display","none");
        } else {
          $(".ManagerSearch_box").css("display","block");
        }        
      });

      /* $('.search_tag_index').change(function(event) {
        $('#rowData_'+id_manager).find(".nametag").html(this.value);
        if(id_manager >=5)
          id_manager = 1;
        else
          id_manager =id_manager + 1;          
        this.value = '';        
        return false;
      }); */
      $('.ac').click(function(event) {
        var command = $( event.target ).html();        
        if (command == '選択した検索条件を使用する') {
          // Save values and return IBSNetworkCardList
          $(".BusinessCardList").addClass("active");
          $(".searchTargetSwitcher").css("display", "block");
          $(".ManagerSearch").removeClass("active");
          $(".business_card_book").css("display", "block");
          $(".peopleSearchResults").css("display", "none");
          $(".network_card_book").css("display", "none");                
          $(".setDisplayTerm").css("display","block");
          $(".network_card_book").css("display", "none");
          $(".manager_search_card").css("display","none");
          $(".ManagerSearch_box").css("display","none");
        } 
        if (command == '選択した検索条件を削除') {
          // Clear text and disable checkbox     
          $("#mSearch").find("td .i-checks").iCheck('uncheck');
          // $(".ManagerSearch_box").css("display","none");
          // $(".BusinessCardList").addClass("active");
          // $(".ManagerSearch").removeClass("active");
          
        }
      });

      // Search function
      /* $('#search-card').change(function(event) {
        if($('.ManagerSearch').hasClass('active')) {
          $('#managercard_'+id_manager+' form input').val();
          $('#managercard_'+id_manager+' form input').val(this.value);
          
          if(id_manager >=5)
            id_manager = 1;
          else
            id_manager =id_manager + 1;          
          this.value = '';
        } else {
          console.log('AA',this.value);  
          this.value = '';
        }
        return false;
      });
 */
      // Process with Label
      $('#addLabel').click(function(event) {
        // Get value from input and append to list
        var label = $("#addLabel").parent().parent().find('input').val();        
        if(label.trim() != ""){
          $("#paging tbody").append('<tr id="rowData">'+
                                      '<td><input type="checkbox" class="i-checks" id="1"></td>'+         
                                      '<td class="nametag">'+label+'</td>'+
                                      '<td><a href="#" class="delTag"><i class="fa fa-trash"></i></a></td>'+
                                    '</tr>');
        
          reloadICheck();
        }
        // Clear
        $("#addLabel").parent().parent().find('input').val('');  
      });

      $(".balloon").on('click', '.delTag', function() {
        $(this).parent().parent().remove();
      });

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
		/*  Util function */
		function reloadICheck(){
			$('.i-checks').iCheck({
     	          checkboxClass: 'icheckbox_square-green',
     	          radioClass: 'iradio_square-green',                
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
						'<div class="list-group" style="margin-bottom: 0px !important; margin-top: 20px !important;" id= "'+nameSort.replace("/","").trim()+'">'
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
	   	function resetValidationForm() {
	   		$(".error_common").text("");

	   	}
	   	
	   	$(document).on('click', '.business_card_book .list-group-item', function() {
        	cardId = parseInt($(this).find('input[name=bla]').val());
            window.location.href = '<c:url value="/user/card/details/'+cardId+'"/>';
	       }).hover(function() {
	         $(this).toggleClass('hover');
        });

       $(document).on('ifChecked','input[name=bla]',function(event) {
          $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
        });
        
        $(document).on('ifUnchecked','input',function(event){     
          if($(".icheckbox_square-green").find('.checked').size() == 1){
            $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
            $(".addTagCard").css("display","none");  
          }          
        });

        // Process add tag and delete
        $("#deletePeople").click(function(e){
          
        });

        $(document).on('click','#addTag',function(e){
          if($(".balloon").css('display') == 'block')
            $(".balloon").css("display","none");
          else
            $(".balloon").css("display","block");
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
	   		var modal=	'<div class="modal-body userSearchs" style="border-bottom: 1px solid #b1b1b1">'
		   	   	+	'<label for="exampleInputEmail1">自分の名刺検索で使用可能</label>'
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
    </script>
