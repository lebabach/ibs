 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
function validateFrom(){
	var chechValidate= true;
	var tileMail = $(".titleMail").val();
	var contentMail =$('.contentMail').val();
	if(tileMail==""){
		$('.titleMail').css("border","1px solid red");
		chechValidate = false;
	}
	if(contentMail==""){
		$('.contentMail').css("border","1px solid red");
		chechValidate = false;
	}
	return chechValidate;
}


$(document).ready(function() {
	 $(document).on('click', '.btn_back_reply', function() {
		 document.location.href="<c:url value='/manager/home'/>";
	 });
	 $('#paging1').dataTable( {
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
	 
	    $(document).on('ifClicked', '#paging .icheckbox_square-green', function(event){
	    	$('.btn_add').removeAttr("disabled");
	    	$('#paging1').css("border","none");
		});
	     $(document).on('ifUnchecked', '#paging .icheckbox_square-green input', function(event){     
	          if($(".icheckbox_square-green").find('.checked').size() == 1){
	        	  $('.btn_add').attr("disabled",true);
	          }          
	   });
	     $(document).on('ifClicked', '#paging1 .icheckbox_square-green', function(event){
		    	$('.btn_remove').removeAttr("disabled");
			});
		     $(document).on('ifUnchecked', '#paging1 .icheckbox_square-green input', function(event){     
		          if($(".icheckbox_square-green").find('.checked').size() == 1){
		        	  $('.btn_remove').attr("disabled",true);
		          }          
		   });
		     
		  
	     $(document).on('click', '.btn_add', function(event){
			 var listUserId = new Array();
			 $("#paging .icheckbox_square-green").find('.checked').each( function(event){
				var userId = $(this).find("input[name=userId]").val();
		    	listUserId.push( parseInt(userId));
			}); 
			 $.ajax({
					type: 'POST',
					url: 'adduser',
					data: 'listUserId=' +listUserId
				}).done(function(resp, status, xhr) {
					$('#paging1 .content-user').find('tr.odd').hide();
					
					$.each( resp, function( key, value ) {
			            	$('.content-user').append($('<tr  class="tr1">').append(
				                                      $('<td class="table-column">').append(
				                                    		  $('<div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" value="'+value.userId+'" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div>')
				                                    		  ),
				                                      $('<td>').text(value.name),
				                                      $('<td>').text(value.companyName),
				                                      $('<td>').text(value.departmentName),
				                                      $('<td>').text(value.positionName)
					                    ));
			            	$('.i-checks').iCheck({
			                    checkboxClass: 'icheckbox_square-green',
			                    radioClass: 'iradio_square-green'
			                     
			                  });
						 
					});
					
					$("#paging .icheckbox_square-green").find('.checked').each( function(){
						//$(this).removeClass('checked');	
						$('#paging input').iCheck('uncheck');
					}); 
					 $('.btn_add').attr("disabled",true);
					 
				}).fail(function(xhr, status, err) {
					alert('Error');
				});
		});
	     
	     $(document).on('click', '.btn_remove', function(event){
			 var listUserId = new Array();
			 $("#paging1 .icheckbox_square-green").find('.checked').each( function(event){
				  $(this).closest("tr").remove();
			}); 
			 if ($("#paging1 tbody").find('tr').length == 1){
				 $('#paging1 .content-user').find('tr.odd').show();
				 $('.btn_remove').attr("disabled",true);
			 }
			 
			 if($(".icheckbox_square-green").find('input').size() > 0){
	        	  $('.btn_remove').attr("disabled",true);
	          }  
			
		});
	     
	     $("#paging *").prop('disabled', true);
		 $("#paging1 *").prop('disabled', true);
		 $(".btn-search").prop('disabled', true);
		 $(".btnSend").prop('disabled', true);
		 
		 
	     $(document).on('ifClicked', '.iradio_square-green', function(event){
				var sendType = parseInt(event.target.value);
				 $(".btnSend").prop('disabled', false);
				$("input[name=sendType]").val(sendType);
				
				if(sendType ==1 | sendType == 2){
					$("#paging *").attr("disabled", "disabled");
					$("#paging1 *").attr("disabled", "disabled");
					$(".btn-search").attr("disabled", "disabled");
				}else if(sendType == 3){
					$("#paging *").prop('disabled', false);
					$("#paging1 *").prop('disabled', false);
					$(".btn-search").prop('disabled', false);
				}
				
			});
	     
	     $('#companyId').on('change', function() {
				var companyId=$(this).val();
				$("input[name=companyId]").val(companyId);
				
				
		});
	     
	     $('.btnSend').on('click', function() {
				var sendType =parseInt($("input[name=sendType]").val());
				var companyId =parseInt($("input[name=companyId]").val());
				var tileMail = $(".titleMail").val();
				var contentMail =$('.contentMail').val();
				if(!validateFrom()){
					return false;
				}
				var userId = "";
				$('#paging1 tbody').find('input[type=checkbox]').each(function(){
					userId = userId + "," + $(this).val();
				});
				var user_send_to = "";
				if(sendType ==1){
					user_send_to = 0;
				}else if(sendType == 2){
					if(parseInt(companyId) == 0){
						alert("<fmt:message key='msg.choose.company.group'/>");
						return false;
					}
					user_send_to = companyId;
				}else if(sendType ==3){
					if(userId == ""){
						$('#paging1').css("border","5px solid red");
						return false;
					}
					if(userId.length > 0){
						userId = userId.substring(1,userId.length);
					}
					user_send_to = userId;
				}
				
				$.ajax({
					type: 'POST',
					url:'sendmailcontact',
					data:{"sendType":sendType,"userSendTo":user_send_to,"titleMail":tileMail,"contentMail":contentMail}
				}).done(function(resp, status, xhr) {
				    if(resp ==0){
				    	 document.location.href="<c:url value='/mails/displayMail'/>";
				    }else{
				    	
				    }
				}).fail(function(xhr, status, err) {
						alert('Error connect send mail');
				});  
				
			});
	     
	     
	      $(".seach_user").keyup(function (e) {
			  if (e.which == 13) {
				  $('#formSearchUser').submit();
			  }
		 });  
});


	
</script>
<style>
.radio-send{
  position: relative !important;
}

.radio-send .iradio_square-green{
  position: absolute !important;
  display: inline-block !important;
}
.lblsend{
     margin: 0 auto;
    margin-left: 34px;
}

</style>
<body>
  <!-- BODY -->
      <div class="container-fluid padding-top20 bg-container height100per">

        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
        	<!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header">
            	<div class="col-sm-12">
                	<div class="float-left">
                    	<h4 class="h4-header">メール送信</h4>
                    </div>

                    <div class="float-right float-right-manage">
                        <button type="button" class="btn btn-primary btn_back_reply">閉じる</button>
                    </div>
                </div>
            </div>
            <!-- END BAR TOP -->
            <!-- END BAR TOP -->
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
                <div class="form-group float-right">
                  <div style="margin: 10px 10px -20px;">
                    <a href='<c:url value="/mails/displayPastMail"/>' class="btn btn-primary">過去送信メールを見る</a>
                  </div>
                </div>
                <input type="hidden" name="sendType" value="">
                <input type="hidden" name="companyId" value="0">
                <div class="col-sm-12 group-send" style = "margin-bottom: 40px;">
                   <!-- BEGIN FORM -->
                    <div class="form-group">
                        <div class="col-xs-12" id ="tblSendAll">
                            <div class="i-checks">
                                <label class="send-all">
                                      <div class="i-checks radio-send">
										<input type="radio" value="1"  name="inquiryId" style="position: absolute; opacity: 0;">
										<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
									 </div>
                                    <div class = "lblsend">全グループへ送信</div>
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-12">
                            <table id = "tblSendComp">
                                <tr>
                                    <td>
                                        <div class="i-checks ">
                                            <label class="send-company">
                                                <div class="i-checks radio-send">
													<input type="radio" value="2"  name="inquiryId" style="position: absolute; opacity: 0;">
													<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
												</div>
                                                <div class = "lblsend">会社別送信</div>
                                                                                                            
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 10px; margin-top: 7px;">
                                        <select class="form-control m-b" name="account" id = "companyId">
                                            <option value='0'>--- 会社別送信-----</option>
                                            <c:forEach var="company" items="${mailGroupVO.lstcompanyDisplayVO}" varStatus="loop">
											  <option value='<c:out value="${company.groupCompanyId}" />'><c:out value="${company.groupCompanyName}" /></option>
											 </c:forEach>
                                        </select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <div class="col-xs-12">
                         
                            <table id = "tblSendUsr">
                                <tr>
                                    <td>
                                        <div class="i-checks " style="margin-top: 5px;">
                                            <label class="send-user">
                                                    <div class="i-checks radio-send">
													<input type="radio" value="3" name="inquiryId" style="position: absolute; opacity: 0;">
													<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
													</div>
                                                     <div class = "lblsend">送信者検索</div>  
                                            </label>
                                        </div>
                                    </td>
                                    <form method="post" id ="formSearchUser" action="displayMail">
                                    <td>
                                        <div style="margin-left: 10px;">
                                            <input type="text" placeholder="" class="form-control seach_user" name="criteriaSearch" style="width: 201px">
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 100px">
                                            <button type="submit" class="btn btn-primary btn-search">検索</button>
                                        </div>
                                    </td>
                                      </form>
                                </tr>
                            </table>
                        
                        </div>


                    </div>
                </div>
                <!-- DATA TABLE -->
                <div class="col-sm-12 container table-list-operator">
                    <div class="row" id="data-table">
                        <div class="ibox-content   ibox-custom01">
                            <table class="table container paging" id="paging" style="margin-top: -84px; padding: 0;">
                                <thead>
                                    <tr>
                                        <th></th>
                                        <th>名前</th>
                                        <th>会社名</th>
                                        <th>部署</th>
                                        <th>役職</th>
                                    </tr>
                                </thead>
                                <tbody class="content_user1">
                                <c:forEach var="user" items="${mailGroupVO.userInfoResultVOs}">
	                                <tr id="">
	                                    <td><div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" value="<c:out value="${user.userId}" />" name = "userId"style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
	                                    <c:choose>
											    <c:when test="${empty user.name}">
											        <td><c:out value="${user.lastName}" />  <c:out value="${user.firstName}" /></td>
											        
											    </c:when>
											    <c:otherwise>
											         <td><c:out value="${user.name}" /></td>
											    </c:otherwise>
										</c:choose>
	                                    <td><c:out value="${user.companyName}"/></td>
	                                    <td><c:out value="${user.departmentName}"/></td>
	                                    <td><c:out value="${user.positionName}"/></td>
	                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                     
                                <button type="button" disabled class="btn btn-primary btn_add" style="width:130px !important">送信リストへ追加</button>
                          
                        </div>
                    </div>
                </div>

                <div class="col-sm-12 container table-list-operator">
                    <div class="row " id="data-table">
                        <div class="ibox-content   ibox-custom01">
                            <table class="table container paging" id="paging1" style="margin-top: -48px; padding: 0;">
                                <thead>
                                    <tr>
                                        <td colspan="5"  style="background-color: #fff; padding-left: 0;">送信者リスト</td>
                                    </tr>
                                    <tr>
                                        <th></th>
                                        <th>名前</th>
                                        <th>会社名</th>
                                        <th>部署</th>
                                        <th>役職</th>
                                    </tr>
                                </thead>
                                <tbody class = "content-user">  
                                </tbody>
                            </table>
                                <button type="submit" disabled class="btn btn-primary btn_remove" style="width:150px !important">送信リストから削除</button>
                            
                        </div>
                    </div>
                </div>

                <div class="col-sm-12" style="margin-top: 30px;">
                   <!-- BEGIN FORM -->
                        <div class="col-xs-12">
                            <table style="width:100%">
                                <caption>送信内容</caption>
                                <tr>
                                    <td>題目</td>
                                    <td>
                                        <div style="margin-left: 10px;">
                                            <input type="text" placeholder="" class="form-control titleMail" style="width: 100%">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <div style="margin-top: 10px; margin-left: 10px;">
                                        <textarea class="form-control contentMail" rows="10"></textarea>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td>
                                        <div style="margin-top: 20px; margin-left: 10px;">
                                            <button type="button" class="btn btn-primary btnSend" style="width:150px !important">送信</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>


                    </div>
                </div>

            </div>
            <!-- BAR BODY -->
        </div>
        <!-- END RIGHT SIDE -->

      </div>
      <!-- END BODY -->
</body>
</html>