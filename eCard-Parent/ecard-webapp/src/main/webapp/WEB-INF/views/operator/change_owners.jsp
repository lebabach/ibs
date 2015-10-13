<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
$(document).ready(function() {
	 $(document).on('click', '.btn_back_reply', function() {
		 document.location.href="<c:url value='/manager/home'/>";
	 });
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
	                                    <td><div class="i-checks"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" value="<c:out value="${cardInfo.cardId}" />" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
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
                                            <form>
                                                <input value="" style="width:300px; height:30px;">
                                                <input value="検索" style="padding-left:10px; padding-right:10px; height:30px;" type="submit">
                                            </form>
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
                                <tbody>
                                <tr id="rowData">
                                    <td><div class="i-checks"><label class=""> <div class="iradio_square-green" style="position: relative;"><input type="radio" value="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
                                    <td>名刺　A夫</td>
                                    <td>(株)インテリジェンス</td>
                                    <td>営業部営業一課　課長</td>
                                    <td>課長</td>
                                    <td>kazuo.yamada@inte.co.jp</td>
                                </tr>
                                <tr id="rowData">
                                    <td><div class="i-checks"><label class=""> <div class="iradio_square-green" style="position: relative;"><input type="radio" value="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
                                    <td>名刺　B夫</td>
                                    <td>(株)テンプスタッフ</td>
                                    <td>営業部営業一課</td>
                                    <td></td>
                                    <td>kazuo.yamada@inte.co.jp</td>
                                </tr>
                                <tr id="rowData">
                                    <td><div class="i-checks"><label class=""> <div class="iradio_square-green" style="position: relative;"><input type="radio" value="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
                                    <td>名刺　E夫</td>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <td>kazuo.yamada@inte.co.jp</td>
                                </tr>
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