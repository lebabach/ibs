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
var dataTables;
$(document).ready(function() {
	 $(document).on('click', '.btn_back_reply', function() {
		 document.location.href="<c:url value='/mails/displayMail'/>";
	 });

	 
	 dataTables = $('#paging_1').dataTable( {
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
	 
	 var table = $('#paging1').dataTable( {
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
	 
	 $(document).on('click', '#paging td' ,function(target){
		$('input[name=selectAll]').parent().iCheck("uncheck");
		var idMail = $(this).parent().attr('id');
		var sendType = $(this).parent().find('input[name=sendType]').val();
		var showMailTitle = $(this).parent().find('input[name=mailTitle]').val();
		var showMailDetail = $(this).parent().find('input[name=mailDetail]').val();
		var companyName = $(this).parent().find('input[name=userSendTo]').val();
		
		if(sendType == 1) {
			showAll();
		} else if (sendType == 2) {
			showGroup();
			$('select[name=account]').html('');
			$('select[name=account]').append($('<option>', {
			    value: 1,
			    text: companyName
			}));
		} else {
			showUser();
			var listUser = $(this).parent().find('input[name=userId]').val();
			getUserInfo(listUser);
						 
		}
		
		/* Show content mail */
		$('#showMailTitle').val(showMailTitle);
		$('#showMailContent div textarea').val(showMailDetail);		
		$('.mail-content').css('display','block');
	 });
});

function showAll() {
	$('.list-user').css('display','none');
	$('.check-box').css('display','block');
	$('.selectGroup').css('display','none');
	$('.selectAll').css('display','block');
	$('input[name=selectAll]').parent().iCheck("check");
	$('input[name=selectGroup]').parent().iCheck("uncheck");
};
function showGroup() {
	$('.list-user').css('display','none');
	$('.check-box').css('display','block');
	$('.selectGroup').css('display','block');
	$('.selectAll').css('display','none');
	$('input[name=selectAll]').parent().iCheck("uncheck");
	$('input[name=selectGroup]').parent().iCheck("check");
};

function showUser() {
	$('.check-box').css('display','none');
	$('.list-user').css('display','block');
}
function getUserInfo(userList) {
	$.ajax({
		type: 'POST',
		url: "<c:url value='/mails/listUserForMail'/>?listUser="+userList,
		cache: false,
	}).done(function(resp, status, xhr) {
		$('.content-user').html('');
		$.each( resp.userId, function( key, value ) {
            	$('.content-user').append($('<tr  class="tr1">').append(
	                                      $('<td>').text(value.userName),
	                                      $('<td>').text(value.companyName),
	                                      $('<td>').text(value.departmentName),
	                                      $('<td>').text(value.positionName)
                ));
            	            	
		});		
	}).fail(function(xhr, status, err) {
		 alert('Error');
	});
}

</script>
<body>
   <!-- BODY -->
      <div class="container-fluid padding-top20 bg-container height100per">

        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
        	<!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header">
            	<div class="col-sm-12">
                	<div class="float-left">
                    	<h4 class="h4-header">過去送信メール</h4>
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
                    <div class="row" id="data-table">
                        <div class="ibox-content   ibox-custom01">
                            <table class="table container paging" id = "paging" style="margin-top: -48px; padding: 0;">
                                <thead>
                                    <tr>
                                        <td colspan="5"  style="background-color: #fff; padding-left: 0;">過去送信メール</td>
                                    </tr>
                                    <tr>
                                        <th>送信日時</th>
                                        <th>題目</th>
                                        <th>送信先</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="showListHistory" items="${showListHistory}" varStatus="loop">
                                	<tr id="${showListHistory.id}">
                                		<td style="width:25%">
		                            		<fmt:formatDate value="${showListHistory.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />	
		                            	</td>
		                            	<td style="width:50%"><c:out value="${showListHistory.mailTitle}"/></td>		                            	
		                            	<td style="width:25%">
		                            		<input type="hidden" name="sendType" value="${showListHistory.sendType}"/>
		                            		<input type="hidden" name="mailDetail" value="${showListHistory.mailDetail}"/>		                            		
		                            		<input type="hidden" name="mailTitle" value="${showListHistory.mailTitle}"/>
		                            		<input type="hidden" name="userSendTo" value="${showListHistory.userSendTo}"/>
		                            		<input type="hidden" name="userId" value="${showListHistory.userId}"/>
		                            		<c:if test="${showListHistory.sendType==1}">
		                            			<c:out value="全送信"/>
		                            		</c:if>
		                            		<c:if test="${showListHistory.sendType==2}">
		                            			<c:out value="${showListHistory.userSendTo}"/>
		                            		</c:if>
		                            		<c:if test="${showListHistory.sendType==3}">
		                            			<c:out value="${showListHistory.userSendTo}"/>
		                            		</c:if>
		                            		
		                            	</td>
                                	</tr>
                                </c:forEach>
<!--                                 <tr id="rowData">
                                    <td><div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" value="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
                                    <td>名刺　A夫</td>
                                    <td>(株)インテリジェンス</td>
                                    <td>営業部営業一課　課長</td>
                                    <td>課長</td>
                                </tr>
                                <tr id="rowData">
                                    <td><div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" value="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
                                    <td>名刺　B夫</td>
                                    <td>(株)テンプスタッフ</td>
                                    <td>営業部営業一課</td>
                                    <td></td>
                                </tr>
                                <tr id="rowData">
                                    <td><div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" value="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
                                    <td>名刺　E夫</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                </tr> -->
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12 check-box" style="display:none;">
                   <!-- BEGIN FORM -->
                    <div class="form-group">
                        <div class="col-xs-12 selectAll">
                            <div class="i-checks">
                                <label class="">
                                    <div class="icheckbox_square-green" style="position: relative;">
                                        <input type="checkbox" disabled name ="selectAll" style="position: absolute; opacity: 0;">
                                        <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
                                    </div>
                                    <i></i> 全送信
                                </label>
                            </div>
                        </div>
                        <div class="col-xs-12 selectGroup">
                            <table>
                                <tr>
                                    <td>
                                        <div class="i-checks">
                                            <label class="">
                                                <div class="icheckbox_square-green" style="position: relative;">
                                                    <input type="checkbox" disabled name ="selectGroup" style="position: absolute; opacity: 0;">
                                                    <ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
                                                </div>
                                                <i></i> 会社別送信
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <div style="margin-left: 10px; margin-top: 7px;">
                                        <select class="form-control m-b" name="account">
                                            <!-- <option>株式会社インテリジェンス</option>
                                            <option>(株)テンプスタッフ</option> -->
                                        </select>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12 list-user container table-list-operator" style="margin-top:-30px;">
                    <div class="row " id="data-table-user-list">
                        <div class="ibox-content   ibox-custom01">
                            <table class="table container paging" id = "paging_1" style="margin-top: -20px; padding: 0;">
                                <thead>
                                    <tr>
                                        <td colspan="5"  style="background-color: #fff; padding-left: 0;">送信者リスト</td>
                                    </tr>
                                    <tr>
                                        <th>名前</th>
                                        <th>会社名</th>
                                        <th>部署</th>
                                        <th>役職</th>
                                    </tr>
                                </thead>
                                <tbody class = "content-user">
                                	
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12 mail-content" style="margin-top: 30px;display:none">
                   <!-- BEGIN FORM -->
                        <div class="col-xs-12">
                            <table style="width:100%">
                                <caption>送信内容</caption>
                                <tr>
                                    <td>題目</td>
                                    <td>
                                        <div style="margin-left: 10px;">
                                            <input disabled id="showMailTitle" type="text" placeholder="" class="form-control" style="width: 100%">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td id="showMailContent">
                                        <div style="margin-top: 10px; margin-left: 10px;">
                                        <textarea disabled class="form-control" rows="10">BC-RIBBON事務局です。・・・・・・</textarea>
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