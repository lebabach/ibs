<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
var listCardId = [];
var checkAll = false;
var check = "";
var listUncheckAll = [];
var dataTables;
$(document).ready(function() {
	dataTables = $('#table-1').dataTable( {
		"dom" : '<<t>ip>',
		"iDisplayLength" : 5,
		"processing": true,
		"serverSide": true,
		"searching": false,
		"ordering": false,
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
		"ajax": {
			"url": '<c:url value="/operators/searchCardTag"/>',
			"type": "POST",
			"data": function (dataTableRequest) {
				dataTableRequest.tagId = $('#tagId').val();
				dataTableRequest.id ='<c:out value="${userLeave.userId}"/>';
				return dataTableRequest;
			},
			"dataSrc": "data",
			"error": function(xhr) {
				alert('error datatable')
			}
			
		},
		"columns": [
			{ "data": "cardId",
				"createdCell": function (td, cellData, rowData, row, col) {
					if(listCardId.length > 0 && listCardId.indexOf(rowData.cardId)>-1){
						$(td).html('<div class="i-checks"><label class=""> <div class="icheckbox_square-green checked" style="position: relative;"><input type="checkbox" class = "i-checks-chk_all" value="'+rowData.cardId+'" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div>');
					}else{
					 $(td).html('<div class="i-checks"><label class=""> <div class="icheckbox_square-green '+check+'" style="position: relative;"><input type="checkbox" class = "i-checks-chk_all" value="'+rowData.cardId+'" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div>');
					}
				}
					
			},
				
			{ "data": "lastName",
				"createdCell": function (td, cellData, rowData, row, col) {
					 $(td).html(rowData.lastName + ' ' + rowData.firstName );
					
			}},
			{ "data": "companyName"},
			{ "data": "departmentName"},
			{ "data": "positionName"},
			{ "data": "tagName"},
		],
		
	}); 
	
	$('#tagId').on('change', function() {
		var tagId = $(this).val();
		$("input[name=tagId]").val(tagId);
		dataTables.api().destroy();
		dataTables = $('#table-1').dataTable( {
			"dom" : '<<t>ip>',
			"iDisplayLength" : 5,
			"processing": true,
			"serverSide": true,
			"searching": false,
			"ordering": false,
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
			"ajax": {
				"url": '<c:url value="/operators/searchCardTag"/>',
				"type": "POST",
				"data": function (dataTableRequest) {
					dataTableRequest.tagId =tagId;
					dataTableRequest.id ='<c:out value="${userLeave.userId}"/>';
					return dataTableRequest;
				},
				"dataSrc": "data",
				"error": function(xhr) {
					alert('error datatable')
				}
			},
			"columns": [
				{ "data": "cardId",
					"createdCell": function (td, cellData, rowData, row, col) {
						if(listCardId.length > 0 && listCardId.indexOf(rowData.cardId)> -1){
							$(td).html('<div class="i-checks"><label class=""> <div class="icheckbox_square-green checked" style="position: relative;"><input type="checkbox" class = "i-checks-chk_all" value="'+rowData.cardId+'" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div>');
						}else{
						 $(td).html('<div class="i-checks"><label class=""> <div class="icheckbox_square-green '+check+'" style="position: relative;"><input type="checkbox" class = "i-checks-chk_all" value="'+rowData.cardId+'" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div>');
						}
					}},
				{ "data": "lastName",
					"createdCell": function (td, cellData, rowData, row, col) {
						 $(td).html(rowData.lastName + ' ' + rowData.firstName );
						
				}},
				{ "data": "companyName"},
				{ "data": "departmentName"},
				{ "data": "positionName"},
				{ "data": "tagName"},
			],
	   });
	});
	
 /*   $('#table-1').dataTable( {
	        "dom": '<<t>ip>',
	        "ordering": false,
	        "iDisplayLength": 5,
	        "language": {
				"zeroRecords": '見つかりませんでした。',
				"emptyTable": '見つかりませんでした。',
				"info": 'ページ表示件数',
				"infoEmpty": 'ページ表示件数',
				"paginate": {
					"previous": '前へ',
					"next": '次へ'
				}
			}
	    } ); */
	    

	   
	     $(document).on('click', '#checkAll', function() { 
	    	 checkAll = true;
	    	 listUncheckAll = [];
	    	 listCardId = [];
	    	 check = "checked";
			$("#table-1 tbody").find(".i-checks-chk_all").each(function( index ) {
				 $(this).prop('checked', true);
				 $(this).parent().attr("class","icheckbox_square-green checked");
			});
		});
	     $(document).on('click', '#removeAll', function() {
	    	 checkAll = false;
	    	 check="";
	    	 listUncheckAll = [];
	    	 listCardId = [];
			$("#table-1 tbody").find(".i-checks-chk_all").each(function( index ) {
				  $(this).prop('checked', false);
				  $(this).parent().attr("class","icheckbox_square-green");  
			});
		});
	       
	     $(document).on('ifChecked', "#table-1 input[type='checkbox']", function() {
	    	 listCardId.push($(this).val());
	     });
	    	       

	     $(document).on('ifUnchecked', "#table-1 input[type='checkbox']", function() {
	    	 var i = listCardId.indexOf($(this).val());
	    	 if(i != -1) {
	    		 listCardId.splice(i, 1);
	    	 }
	    });
	     
	     $(document).on('click', '.btn_back_reply', function() { 
	    	 document.location.href="<c:url value='/operators/confirm/"+${userLeave.userId}+" '/>";
		});
	     
	     $(".criteriaSearch").keyup(function (e) {
			  if (e.which == 13) {
				  $('#criteriaSearch').trigger('click');
			  }
		 });
	     
	     $(document).on('click', '#table-1 .icheckbox_square-green', function(e) {
	    	  	if($(this).attr("class").indexOf("checked") == -1){
	    	  		$(this).removeClass('icheckbox_square-green');
	    	  		 $(this).removeClass("icheckbox_square-green hover");
	    	      	 $(this).addClass("icheckbox_square-green checked");
	    	      	 var cardId = $(this).find("input[type=checkbox]").val();
	    	      	  listCardId.push(parseInt(cardId));
	    	      	 return false;
	    	  	}else{
	    	  		$(this).removeClass("icheckbox_square-green checked");
	    	  		 $(this).addClass("icheckbox_square-green"); 
	    	  		var cardId =$(this).find("input[type=checkbox]").val();
	    	  		listUncheckAll.push(parseInt(cardId));
	    	  		 var i = listCardId.indexOf(parseInt(cardId));
	    	    	 if(i != -1) {
	    	    		 listCardId.splice(i, 1);
	    	    	 }
	    	  		 return false;
	    	  	}
	    	  	
	    });
	 
});

  $(document).on('click', '#criteriaSearch', function() {
	   $('.content_user').html("");
	   var criteriaSearch = $('.criteriaSearch').val();
	   console.log(criteriaSearch);
	   if (criteriaSearch == ""){
		   document.location.href="<c:url value='/operators/changeowner/"+${userLeave.userId}+" '/>";
	   }
	   
		$.ajax({
			type: 'POST',
			url: '<c:url value="/operators/searchList"/>',
			cache: false,
			data: 'criteriaSearch='+criteriaSearch,
			success: function(response) {
				$('#paging').dataTable().fnClearTable();
				 $(function() {
					 var str = "";
					 $('.content_user').html(" ");
			            $.each(response.data, function(i, item) {
			            	$('.content_user').append($('<tr  class="tr1">').append(
			            			    $('<td>').append(
			            			    		$('<div class="i-checks">').append(
			            			    				' <input type="radio" name = "bla" value="'+item.userId+'"  style="position: absolute; opacity: 0;">'
			            			    		)
			            			    ),
			                            $('<td>').text(item.firstName + " " + item.lastName),
			                            $('<td>').text(item.companyName),
			                            $('<td>').text(item.departmentName),
			                            $('<td>').text(item.positionName),
			                            $('<td>').text(item.email)
				                        	
	                          )
              
		                    );

			            });
		            	$('.i-checks').iCheck({
		                    checkboxClass: 'icheckbox_square-green',
		                    radioClass: 'iradio_square-green'
		                     
		                  });
			        });
				 
	        },
           error: function (response) {
           	alert("Error");
           }
		});
	});
  
  $(document).on('ifChecked', "#paging input[type='radio']", function() {
	  var userAssign = $(this).val();
	  var nameAssign = $(this).parent().parent().parent().parent().find("td:nth-child(2)").text();
	  $("input[name=userAssign]").val(userAssign);
	  $("input[name=nameAssign]").val(nameAssign);
  });
  $(document).on('click', '.btn-assign', function() {
	  var userLeave = parseInt($("input[name=userLeave]").val());
	  var userAssign = $("input[name=userAssign]").val();
	  var nameAssign = $("input[name=nameAssign]").val();
	  var tagId =    $("input[name=tagId]").val();
	  var count = 0;
	  if(!checkAll && listCardId.length == 0){
		  BootstrapDialog.show({
	             title: '警告',
	             message: '名刺を選択してください'
	        });
		  return false;
	  }
	  if(userAssign == ""){
		  BootstrapDialog.show({
	             title: '警告',
	             message: 'ユーザを選択してください'
	        });
		  return false;
	  }
	  if(checkAll){
		  var total = $('#table-1_info').text();
		  var totalUncheck = 0;
		  if(listUncheckAll.length > 0){
			  totalUncheck=listUncheckAll.length;
		  }
		  count = parseInt(total)-totalUncheck;
	  }else{
		  count = listCardId.length;
	  }
	  if (confirm('<c:out value="${userLeave.lastName}"/> <c:out value="${userLeave.firstName}"/> さんの名刺'+count+'件を、'+nameAssign +' さんの所有とします。よろしいですか？')) {
		  $.ajax({
				type: 'POST',
				url: '<c:url value="/operators/updateCardUser"/>',
				 dataType: 'json', 
				 contentType: 'application/json',
				 mimeType: 'application/json',
				data:JSON.stringify({"userLeave":userLeave,"userAssign":userAssign,"nameAssign":nameAssign,"tagId":tagId,"checkAll":checkAll,"listCardId":listCardId,"listUncheckAll":listUncheckAll}) 
			}).done(function(resp, status, xhr) {
				document.location.href="<c:url value='/operators/changeowner/"+resp+" '/>";
			}).fail(function(xhr, status, err) {
				BootstrapDialog.show({
		             title: 'エラー',
		             message: 'エラーアサイン'
		        });
			  return false;
			});
	  }
	  
  });
  $(document).on('click', '.paginate_button', function() { 
 	 if(checkAll){
 	 	 $("#table-1 tbody").find(".i-checks-chk_all").each(function( index ) {
 				 $(this).prop('checked', true);
 				 $(this).parent().attr("class","icheckbox_square-green checked");
 			});
 	  }else{
 	 	 $("#table-1 tbody").find(".i-checks-chk_all").each(function( index ) {
 				  $(this).prop('checked', false);
 				  $(this).parent().attr("class","icheckbox_square-green");  
 		 });
 	 	 if(listCardId.length > 0){
 	 		$.each(listCardId, function(idcard, vcard){
 	 			$("#table-1 tbody").find("input[value="+vcard+"]").prop('checked', true);
 	 			$("#table-1 tbody").find("input[value="+vcard+"]").parent().attr("class","icheckbox_square-green checked");
 	 		});
 	 	 }
 	  }
	});
  

  
</script>

 <!-- BODY -->
      <div class="container-fluid padding-top20 bg-container height100per">

        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
        	<!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header">
            	<div class="col-sm-12">
                	<div class="float-left">
                    	<h4 class="h4-header">所有者変更</h4>
                    </div>

                    <div class="float-right float-right-manage">
                        <button type="button" class="btn btn-primary btn_back_reply">閉じる</button>
                    </div>
                </div>
            </div>
            <!-- END BAR TOP -->
            <!-- END BAR TOP -->
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
                <input  name="userLeave" value="<c:out value="${userLeave.userId}"/>" type = "hidden">
                <input  name="userAssign" value="" type = "hidden">
                 <input  name="nameAssign" value="" type = "hidden">
                  <input  name="tagId" value="0" type = "hidden">
                <!-- DATA TABLE -->
                <div class="col-sm-12 container table-list-operator">
                    <div class="row" id="data-table" >
                        <div class="ibox-content  ibox-custom01">
                            <table id="table-1" class="table container paging" style="margin-top: -54px; padding: 0; position: relative; z-index:9">
                                <thead>
                                    <tr role="row">
                                        <td colspan="5" style="background-color: #fff; padding-left: 0;" rowspan="1">所有者（変更元） <c:out value="${userLeave.lastName}" />  <c:out value="${userLeave.firstName}" /></td>
                                        <td colspan="1" style="background-color: #fff; padding-left: 0;" rowspan="1">
                                          <div class="form-group">
													 <form>
											               <select id = "tagId" class="form-control role-service" >
											                  <option value = "">すべて </option>
											                  <c:forEach var="tagGroup" items="${listTagGroup}">
											                       <option value ='<c:out value="${tagGroup.tagId}" />'><c:out value="${tagGroup.tagName}" /></option>
											                  </c:forEach>
											               </select>
											         </form>
											 </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th>名前</th>
                                        <th>会社名</th>
                                        <th>部署</th>
                                        <th>役職</th>
                                        <th>タグ</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%-- <c:forEach var="cardInfo" items="${listCardInfo}">
	                                <tr id="rowData">
	                                    <td><div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" class = "i-checks-chk_all" value="<c:out value="${cardInfo.cardId}" />" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
	                                    <td><c:out value="${cardInfo.lastName}" />　<c:out value="${cardInfo.firstName}" /></td>
	                                    <td><c:out value="${cardInfo.companyName}" /></td>
	                                    <td><c:out value="${cardInfo.departmentName}" /></td>
	                                    <td><c:out value="${cardInfo.positionName}" /></td>
	                                    <td>ルート営業,頻繁に訪問,次郎さんへ移動</td>
	                                </tr>
	                                </c:forEach> --%> 
                                </tbody>
                            </table>
                            
                            <div style=" margin-left: 0; position: relative; z-index:99">
                                <button id="checkAll"  class="btn btn-primary" style="width:150px !important;">全て選択</button>
                                <button id="removeAll" class="btn btn-primary" style="width:150px !important;">選択の解除</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12 container table-list-operator">
                    <div class="row " id="data-table" >
                        <div class="ibox-content   ibox-custom01">
                            <table class="table container paging" style="margin-top: -10px; padding: 0;" id= "paging">
                                <thead>
                                    <tr>
                                        <td colspan="2"  style="background-color: #fff; padding-left: 0;">所有者（変更先）の検索</td>
                                        <td colspan="4" style="background-color: #fff; padding-left: 0; text-align:right;">
                                                <input class="criteriaSearch"  name="criteriaSearch" value="" style="width:300px; height:30px;">
                                                <input value="検索" style="padding-left:10px; padding-right:10px; height:30px;" id ="criteriaSearch"  type="button">
                                        </td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th>名前</th>
                                        <th>会社名</th>
                                        <th>部署</th>
                                        <th>役職</th>
                                        <th>メールアドレス</th>
                                    </tr>
                                </thead>
                                <tbody class="content_user">
		                                <c:forEach var="userInfo" items="${lstUserInfo}">
			                                <tr id="">
			                                    <td><div class="i-checks"><input type="radio" name = "bla" value="<c:out value="${userInfo.userId}" />" style="position: absolute; opacity: 0;"></div></td>
			                                    <td><c:out value="${userInfo.lastName}" />　<c:out value="${userInfo.firstName}" /></td>
			                                    <td><c:out value="${userInfo.companyName}" /></td>
			                                    <td><c:out value="${userInfo.departmentName}" /></td>
			                                    <td><c:out value="${userInfo.positionName}" /></td>
			                                    <td><c:out value="${userInfo.email}" /></td>
			                                </tr>
		                               </c:forEach>
                                </tbody>
                            </table>
                            <div style=" margin-left: 0;">
                                <button type="button" class="btn btn-primary btn-assign" style="width:150px !important">所有者変更</button>
                            </div>
                        </div>
                    </div>
                </div>

               
                </div>

            </div>
            <!-- BAR BODY -->
        </div>
        <!-- END RIGHT SIDE -->

      </div>
      <!-- END BODY -->