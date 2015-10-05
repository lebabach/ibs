<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<style>
table.dataTable thead th{
 border: none !important;
}
</style>
<!-- START HEADER -->
<div class="row full_text_search">
  <div class="clearfix">
    
      <div class="col-md-2" style="width: 150px; display:inline-block">
        <div class="form-group">
            <div class="icon-addon addon-md">
                <!-- <form name="myform">  -->
		<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modal-example">検索画面</button>
		<!-- modal -->
                    <div class="modal" id="modal-example" tabindex="-1">
                        <div class="modal-dialog">

                            <!-- modal content -->
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
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">フリーワード</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="会社名・氏名・emailを入力 * or * でOR検索">
                                    </div>
                                    <h4 class="modal-title" id="modal-label" style="padding-bottom:10px;">必要に応じて入力してください</h4>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">会社名</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="例：田中物産株式会社">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">氏名</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="例：田中　太郎">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">email</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="例：tanaka@gmail.com">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">部署</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="例：管理部">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">役職</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="例：部長">
                                    </div>
                                    <div class="form-group">
                                        <label for="exampleInputEmail1">住所</label>
                                        <input type="email" class="form-control" id="exampleInputEmail1" placeholder="例：東京都千代田区神田">
                                    </div>
                                </div>
                                <!-- modal footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-primary">検索</button>
                                    <button type="button" class="btn btn-success" data-dismiss="modal">保存済みの検索条件を呼び出す</button>
                                    <button type="button" class="btn btn-info" id="registerSearch">検索条件を保存する</button>
                                </div>
                            </div>
                        </div>
                    </div>
                <!-- </form> -->
                
                <!-- <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>  -->
            </div>
        
        </div>
        
      </div>
     
      <div class="col-md-2 setDisplayTerm" style="float:right; margin-right:0;">
          <select class="input-sm form-control input-s-sm inline">
            <option value="0">名刺交換日順</option>
            <option value="1">氏名順</option>
            <option value="2">会社名順</option>                          
        </select>
      </div>    
      <div class="col-md-2 m-b-xs setDisplayTerm" style="float:right; width:110px; margin-right:0">
        <div class="btn-group" role="group" aria-label="...">
          <button id="addTag" class="btn btn-primary disabled" type="button">                  
            <i class="fa fa-tag"></i>               
          </button>
          <div class="balloon lbl_balloon" style="display: none;">
            <div class="lbl_srh">
              <dl class="srhbox_round">
                <dt><a class="search_tag_index_btn" href="javascript: void(0);"></a><i class="fa fa-search"></i></dt>
                <dd><span class="iptxt"><input type="text" class="search_tag_index" value="" style="color: rgb(171, 171, 171);"></span></dd>
              </dl>
            </div>
            <div class="">
              <div class="col-sm-12" style="border-bottom: solid 1px #c1c1c1;">
                <table class="table" id="page">
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
                <input type="text" class="form-control" placeholder="add more tag"> 
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

<div class="row searchTargetSwitcher">
	<div class="clearfix">
     	<a href="javascript:void(0)" class="BusinessCardList active"> あなたの名刺 </a>
     	<a href="javascript:void(0)" class="IBSNetworkCardList">グループネットワーク </a>
     	<!-- <a href="javascript:void(0)" class="ManagerSearch">検索条件管理</a> -->
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
         <!-- <div class="input-group lbl" style="padding: 15px;">            
             <span class="input-group-btn"> 
             <button id="addLabel" type="button" class="btn btn-success">Check</button> 
             <button id="addLabel" type="button" class="btn btn-success">DisableAll</button> 
             </span>
         </div> -->
       </div>
     </div> 
     <!-- <a class="export"> Export CSV</a> -->
   </div>
</div>
<!-- END HEADER -->

<div id="content">
     <div id="inner">
     	<div class="business_card_book">
     		<div class="box-1">
          		 <div class="title-box-1" style="height: 15%;">
              		<!-- 7/2015 -->  
              	</div>
              
              	<table class="table-striped" style= "width: 100%;" id = "listBusinessCard">
              	  <thead>
              	    <tr>
              	     <th></th>
              	     <th></th>
              	     <th></th>
              	     </tr>
              	  </thead>
              	  <tbody>
		              	<c:if test="${not empty cardInfoResponse.getListCardInfo()}">
			              	<c:forEach var="cardInfo" items="${cardInfoResponse.getListCardInfo()}">
			              	     <tr> 
				              	     <td style="width: 10%; text-align: center;"><div class="icheckbox_square-green">
				                <input type="checkbox" class="i-checks" name="bla" value='<c:out value=" ${cardInfo.cardId} "/>'>
				              </div></td>
				              	     <td><div class="col-xs-11 mg-top ">
								                <p class="name"><c:out value="${cardInfo.lastName}"/> <c:out value=" ${cardInfo.firstName} "/></p>
								                <p class="livepass"><c:out value="${cardInfo.companyName}"/></p>
								                      <p class="department_and_position"><c:out value="${cardInfo.departmentName}"/></p>
								                <p class="num"><c:out value="${cardInfo.telNumberCompany}"/></p>
								                <p class="mail"><a href="#"><c:out value="${cardInfo.email}"/></a></p>
								              </div></td>
				              	     <%-- <td style= "padding: 25px 25px 25px 0px;"> <img src="data:image/png;base64,<c:out value="${cardInfo.imageFile}"/>" class="img-responsive img-thumb pull-right" alt="Responsive image"></td> --%> 
				              	     <td style= "padding: 25px 25px 25px 0px;"> 
				              	     	<img src="<c:url value='/assets/img/loading.gif'/>" class="img-responsive img-thumb pull-right" alt="Responsive image">
				              	     	<input class="hidden" name="fileImageName" value="${cardInfo.imageFile}">
				              	     </td>
			              	     </tr>
			              	</c:forEach>
		                </c:if>
              	  
              	  </tbody>
              	
              	</table>
	              	<%-- <div class="content-box-1 pointer ">
	              		<div class="row">
				            <div class="col-md-1 col-xs-1 ">
				              <div class="icheckbox_square-green">
				                <input type="checkbox" class="i-checks" name="bla" value='<c:out value=" ${cardInfo.cardId} "/>'>
				              </div>
				            </div>
				            <div class="col-md-7">
				              <div class="col-xs-11 mg-top ">
				                <p class="name"><c:out value="${cardInfo.lastName}"/> <c:out value=" ${cardInfo.firstName} "/></p>
				                <p class="livepass"><c:out value="${cardInfo.companyName}"/></p>
				                      <p class="department_and_position"><c:out value="${cardInfo.departmentName}"/></p>
				                <p class="num"><c:out value="${cardInfo.telNumberCompany}"/></p>
				                <p class="mail"><a href="#"><c:out value="${cardInfo.email}"/></a></p>
				              </div>
				            </div>
				            <div class="col-md-4 ">
				                  <div class="col-xs-7">
				                    <img src="data:image/png;base64,<c:out value="${cardInfo.imageFile}"/>" class="img-responsive img-thumb pull-right" alt="Responsive image">
				                  </div> 
				            </div>
				       </div>
	              	</div> --%>
               
          </div>
      
 		</div>
     </div>
</div>


<script type="text/javascript">
$(document).ready(function(){
	
    $("#registerSearch").click(function(){
        $.ajax({
            type: 'POST',
            url: '/registerSearchText',
            data: {}
        }).done(function(resp, status, xhr){
            if(resp == 0){
                window.location.href = "/user/home";
            } else {
                alert('123');
            }
        }).fail(function(resp, status, xhr){
            alert('Error');
        });
    });
        $("input[name=bla]").on('ifChecked', function(event){
            $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
          });
        $("input").on('ifUnchecked', function(event){     
          if($(".icheckbox_square-green").find('.checked').size() == 1){
            $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
            $(".addTagCard").css("display","none");  
          }          
        });
        
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green'
             
          });
        
        var dataTables = $('#listBusinessCard').dataTable({
			"dom" : '<<t>ip>',
			"ordering" : false,
			"iDisplayLength" : 2,
			"language": {
				"zeroRecords": '<fmt:message key="operator.list.table.emptyTable"/>',
				"emptyTable": '<fmt:message key="operator.list.table.emptyTable"/>',
				"info": '<fmt:message key="operator.list.table.info"/>',
				"infoEmpty": '<fmt:message key="operator.list.table.info"/>',
				"paginate": {
					"previous": '<fmt:message key="operator.list.table.paginate.previous"/>',
					"next": '<fmt:message key="operator.list.table.paginate.next"/>'
				}
			},
		});
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
          });
        
	});
    



$("tbody tr .img-responsive").each(function () {
	var target = $(this);
    var fileImageName =$(this).parent().find('input[name=fileImageName]').val();
    console.log('AAA= '+fileImageName);
    $.ajax({
        type: 'POST',
        url: 'getImageFile',
        data: 'fileImage='+fileImageName
    }).done(function(resp, status, xhr){
    	target.attr('src','');
        console.log('A= '+resp);
        target.attr('src','data:image/png;base64,'+resp);
    }).fail(function(resp, status, xhr){
        alert('Error');
    });
  
});
</script>