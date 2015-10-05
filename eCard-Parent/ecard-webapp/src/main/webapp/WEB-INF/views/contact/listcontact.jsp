<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<script type="text/javascript">
$(document).ready(function() {
			$(document).on('click', 'a.ch-del', function() {
				if (confirm('<fmt:message key="operator.list.confirmDelete"/>')) {
					var inquiryId = $(this).closest('tr').find('td:first-child input[name=inquiryId]').val();
					$.ajax({
						type: 'POST',
						url: 'delete',
						cache: false,
						data: 'inquiryId='+inquiryId
					}).done(function(resp, status, xhr) {
						if (resp === 0)
							location.reload();
						else
							alert('Error');
					}).fail(function(xhr, status, err) {
						alert('Error');
					});
				}
			});	
			$(document).on('ifClicked', '.iradio_square-green', function(event){
				var inquiryId = event.target.value;
				$('.btn_reply').removeAttr("disabled");
				 $("a.btn_reply").attr("href", "<c:url value='/contacts/displayReply?inquiryId='/>"+inquiryId);
			});
			$(document).on('click', '.btn-close', function(event){
				document.location.href="<c:url value='/manager/home'/>";
			});
});


	

</script>
<body>
   <div class="container-fluid padding-top20 bg-container height100per">

        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
        	<!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header">
            	<div class="col-sm-12">
                	<div class="float-left">
                    	<h4 class="h4-header">お問い合わせリスト</h4>
                    </div>

                    <div class="float-right float-right-manage">
                        <button type="button" class="btn btn-primary btn-close">閉じる</button>
                    </div>
                </div>
            </div>

            <!-- END BAR TOP -->
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
				<!-- <div class="col-sm-12">
					<div class="input-group">
						<input type="text" class="form-control" placeholder="Enter name, company name, phone ...">
						<span class="input-group-btn"> <button type="button" class="btn btn-primary">Search</button></span>
					</div>
				</div> -->
				<!-- END SEARCH -->
				<!-- DATA TABLE -->
				<div class="col-sm-12 container table-list-operator">

					<div class="row " id="data-table">
						<div class="ibox-content   ibox-custom01">
							<table class="table container" id="paging" style="margin-top: -84px;">
								<thead>
                                    <tr>
                                     <td colspan="9" style="background-color: #fff;">未回答のお問い合わせが10件あります。</td>
                                    </tr>
									<tr>
                                        <th></th>
										<th>申請者</th>
										<th>会社名</th>
										<th>部署</th>
										<th>役職</th>
										<th>日付</th>
                                        <th>回答</th>
                                        <th>内容</th>
									</tr>
								</thead>
								<tbody>
								   <c:forEach var="contact" items="${listContactNotification}">
										<tr >
		                                    <td><div class="i-checks"><label class=""> <div class="" style="position: relative;"><input type="radio" value="<c:out value="${contact.inquiryId}" />" name="inquiryId" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
											<c:choose>
											    <c:when test="${empty contact.name}">
											         <td><c:out value="${contact.lastName}" />  <c:out value="${contact.firstName}" /></td>
											    </c:when>
											    <c:otherwise>
											        <td><c:out value="${contact.name}" /></td>
											    </c:otherwise>
											</c:choose>
											<td><c:out value="${contact.companyName}" /></td>
											<td><c:out value="${contact.departmentName}" /></td>
											<td><c:out value="${contact.positionName}" /></td>
											<td><fmt:formatDate value='${contact.createDate}' pattern="yyyy年 MM月dd日" /></td>
		                                    <c:choose>
											    <c:when test="${contact.answerFlg != '0'}">
											        <td>回答済み</td>
											    </c:when>
											    <c:otherwise>
											         <td></td>
											    </c:otherwise>
											</c:choose>
		                                    <td style="word-break:break-all;width:30%;"><c:out value="${contact.inquiryText}" /></td>
										</tr>
								   </c:forEach>
								</tbody>
                                <tfoot>
                                <tr>
                                    <td colspan="8" align="right">
                                        <div class="float-right" style="float:right !important;">
                                            <a href="<c:url value='/contacts/displayReply'/>" disabled class="btn btn-primary btn_reply" style="width:100px !important">回答画面へ</a>
                                        </div>
                                    </td>
                                </tr>
                                </tfoot>
							</table>
						</div>
					</div>
				</div>
				<!-- END DATA TABLE -->
			</div>
            <!-- BAR BODY -->

        </div>
        <!-- END RIGHT SIDE -->
      </div>
      <!-- END BODY -->
</body>
</html>