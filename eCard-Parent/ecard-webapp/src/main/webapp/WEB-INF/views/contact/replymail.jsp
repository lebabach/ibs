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
$(document).ready(function() {
	 $(document).on('click', '.btn_back_reply', function() {
		 document.location.href="<c:url value='/contacts/listcontact'/>";
	 });
	 
	 $(document).on('click', '.btn_back', function() {
		 document.location.href="<c:url value='/contacts/listcontact'/>";
	 });
	 
 	 $(document).on('click', '.btn_add', function() {
 		debugger;
 		if(!checkValidationForm()){				
			return false;
		}
 		
		$("#formSubmit").submit();
	 });
});
function checkValidationForm(){
	var checkValidation=true;
	if($("#answerText").val()==""){
		$(".sp-answerText").text("<fmt:message key='validate.null'/>");
		checkValidation=false;
	}	
	return checkValidation;
}
</script>
<body>
  <div class="container-fluid padding-top20 bg-container height100per">

        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
        	<!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header col-sm-12">
            	<div class="col-sm-12">
                	<div class="float-left">
                    	<h4 class="h4-header">お問い合わせ回答</h4>
                    </div>

                    <div class="float-right float-right-manage">
                        <button type="button" class="btn btn-primary btn_back_reply">閉じる</button>
                    </div>
                </div>
            </div>

            <!-- END BAR TOP -->
            
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
                <div class="col-sm-12 container table-list-operator">
                <form method="POST" action="<c:url value='/contacts/reply'/>" accept-charset="UFT-8" id="formSubmit">
                    <div class="row " id="data-table">
                        <div class="ibox-content  ibox-content-question ibox-custom01">
                            <table class="table container paging" style="margin-top: -50px; padding: 0;">
                                <thead>
                                    <tr>
                                        <th>名前</th>
                                        <th>会社名</th>
                                        <th>部署</th>
                                        <th>役職</th>
                                        <th>日付</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <c:choose>
										    <c:when test="${empty contactNotification.name}">
											      <td><c:out value="${contactNotification.name}" /></td>
											 </c:when>
											 <c:otherwise>
											       <td><c:out value="${contactNotification.lastName}" />  <c:out value="${contactNotification.firstName}" /></td>
											  </c:otherwise>
									</c:choose>
                                    <td><c:out value="${contactNotification.companyName}" /></td>
                                    <td><c:out value="${contactNotification.departmentName}" /></td>
                                    <td><c:out value="${contactNotification.positionName}" /></td>
                                    <td><fmt:formatDate value='${contactNotification.createDate}' pattern="yyyy年 MM月dd日" /></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>

                <div class="col-sm-12" style="margin-top: 0px;">
                   <!-- BEGIN FORM -->
                        <div class="col-xs-12">
                            <table style="width:100%">
                                <caption>内容</caption>
                                <tr>
                                    <td></td>
                                    <td>
                                        <div style="margin-top: 0px; margin-left: 0px;">
                                        <textarea class="form-control" rows="10" disabled><c:out value="${contactNotification.inquiryText}" /></textarea>
                                        <input type="hidden" name="inquiryId" value="${contactNotification.inquiryId}">
                                        <input type="hidden" name="email" value="${contactNotification.email}">
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </div>

                        <div class="col-xs-12" style="margin-top: 30px;">
                            <table style="width:100%">
                                <caption>回答</caption>
                                <tr>
                                    <td></td>
                                    <td>
                                        <div style="margin-top: 0px; margin-left: 0px;">
                                          <c:if test="${contactNotification.answerFlg == 1}">	
                                          	<textarea class="form-control" rows="10" disabled name ="answerText" id="answerText"><c:out value="${contactNotification.answerText}" /></textarea>
                                          </c:if>
                                          <c:if test="${contactNotification.answerFlg != 1}">	
                                          	<textarea class="form-control" rows="10" name ="answerText" id="answerText"></textarea>
                                          </c:if>
                                          <span class='sp-answerText' style="color:red"></span>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td align="right">
                                    	<c:if test="${contactNotification.answerFlg != 1}">
                                    		<div style="margin-top: 20px; margin-left: 10px;">
                                            	<button type="button" class="btn btn-primary btn_add" style="width:150px !important">回答送信</button>
                                        	</div>
                                    	</c:if>
                                    	<c:if test="${contactNotification.answerFlg == 1}">
                                    		<div style="margin-top: 20px; margin-left: 10px;">
                                            	<button type="button" class="btn btn-primary btn_back" style="width:150px !important">戻る</button>
                                        	</div>
                                    	</c:if>
                                        
                                    </td>
                                </tr>
                            </table>
                        </div>
                       </form>

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