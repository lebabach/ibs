<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
$(document).ready(function() {
	 $('#table-1').dataTable( {
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
	    } );
	 
	     $(document).on('click', '#checkAll', function() { 
			
			$("#table-1 tbody").find(".i-checks-chk_all").each(function( index ) {
				 $(this).prop('checked', true);
				 $(this).parent().attr("class","icheckbox_square-green checked");
				
			});
		});
	     $(document).on('click', '#removeAll', function() {
			$("#table-1 tbody").find(".i-checks-chk_all").each(function( index ) {
				  $(this).prop('checked', false);
				  $(this).parent().attr("class","icheckbox_square-green");  
			});
		});
	     
	     $(document).on('click', '.btn_back_reply', function() { 
	    	 document.location.href="<c:url value='/operators/confirm/"+${userLeave.userId}+" '/>";
		});
	     
	     $(".criteriaSearch").keyup(function (e) {
			  if (e.which == 13) {
				  $('#criteriaSearch').trigger('click');
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
				 $(function() {
					 var str = "";
					 $('.content_user').html(" ");
			            $.each(response.data, function(i, item) {
			            	$('.content_user').append($('<tr id="rowData" class="tr1">').append(
			            			    $('<td>').append(
			            			    		$('<div class="i-checks">').append(
			            			    				'<label class=""> <div class="iradio_square-green" style="position: relative;"><input type="radio" value="'+item.userId+'"  style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label>'
			            			    				)
			            			    ),
			                            $('<td>').text(item.firstName + " " + item.lastName),
			                            $('<td>').text(item.companyName),
			                            $('<td>').text(item.departmentName),
			                            $('<td>').text(item.positionName),
			                            $('<td>').text(item.email)
				                        	
				                          )
	                
					                    );
			            	$('.i-checks').iCheck({
			                    checkboxClass: 'icheckbox_square-green',
			                    radioClass: 'iradio_square-green'
			                     
			                  });

			            });
			        });
				 $('#modelAddTag').modal('show');
				 
	        },
           error: function (response) {
           	alert("Error");
           }
		});
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
                
                
                <!-- DATA TABLE -->
                <div class="col-sm-12 container table-list-operator">
                    <div class="row" id="data-table" >
                        <div class="ibox-content  ibox-custom01">
                            <table id="table-1" class="table container paging" style="margin-top: -54px; padding: 0; position: relative; z-index:9">
                                <thead>
                                    <tr role="row"><td colspan="6" style="background-color: #fff; padding-left: 0;" rowspan="1">所有者（変更元） <c:out value="${userLeave.lastName}" /> <c:out value="${userLeave.firstName}" /></td></tr>
                                    <tr>
                                        <th></th>
                                        <th>名前</th>
                                        <th>会社名</th>
                                        <th>部署</th>
                                        <th>役職</th>
                                    </tr>
                                </thead>
                                <tbody>
                                   <c:forEach var="cardInfo" items="${listCardInfo}">
	                                <tr id="rowData">
	                                    <td><div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" class = "i-checks-chk_all" value="<c:out value="${cardInfo.cardId}" />" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
	                                    <td><c:out value="${cardInfo.lastName}" />　<c:out value="${cardInfo.firstName}" /></td>
	                                    <td><c:out value="${cardInfo.companyName}" /></td>
	                                    <td><c:out value="${cardInfo.departmentName}" /></td>
	                                    <td><c:out value="${cardInfo.positionName}" /></td>
	                                </tr>
	                                </c:forEach>
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
			                                <tr id="rowData">
			                                    <td><div class="i-checks"><label class=""> <div class="iradio_square-green" style="position: relative;"><input type="radio" value="<c:out value="${userInfo.userId}" />" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
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
                                <button type="submit" class="btn btn-primary" style="width:150px !important">所有者変更</button>
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