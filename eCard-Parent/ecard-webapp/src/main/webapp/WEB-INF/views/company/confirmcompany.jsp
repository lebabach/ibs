<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
.company-name{
 width: 50%;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('.btn_cancle').on('click', function(){
		   document.location.href='/ecard-webapp/companies/list';
	});
});
</script>
</head>
<body>
 <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
            <!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header col-sm-12">
                <div class="col-sm-12">
                    <div class="float-left">
                        <h4 class="h4-header">会社確認</h4>
                    </div>
                    <div class="col-sm-3" style = "float: right;margin: 0 auto;">
                           <h4 class="h4-header" style="margin:0;padding:8px 0">
                               <span><button type="button" class="btn btn-primary btn_cancle"
                                            data-dismiss="modal">キャンセル</button></span>
                            </h4>
                     </div>
                    <div class="float-right float-right-manage">
                         
                    </div>
                </div>
            </div>
            
            <!-- END BAR TOP -->
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
                    <!-- <div class="col-sm-8 col-xs-offset-2" id="data-table"> -->
                        <div class="container">
                          <form class="form-horizontal" role="form">
                            <div class="form-group">
                                <div class="col-sm-2 col-sm-offset-4">
                                     <!-- <label class="control-label" for="date" style="display:block;">ID　</label> -->
                                     <label class="control-label" for="date" style="display:block;">会社名　</label>
                                     <label class="control-label" for="name" style="line-height:40px;">グループ参加日</label>
                                </div>
                             
	                              <div class="col-sm-1 company-name">
	                                <%-- <h4 style="width:100%;display:block;"><c:out value="${groupCompanyDepartmentVO.groupCompanyInfo.groupCompanyId}" /></h4> --%>
	                                <h4 style="width:100%;display:block;"><c:out value="${groupCompanyDepartmentVO.groupCompanyInfo.groupCompanyName}" /></h4>
	                                <h4 style="margin-top:15px;line-height:30px;"><fmt:formatDate value="${groupCompanyDepartmentVO.groupCompanyInfo.createDate}" pattern="yyyy年 MM月dd日" /></h4>
	                              </div>  
                             </div>
                           <!--  <div class="form-group" style="margin-left:-180px;">        
                              <div class="col-sm-offset-5 col-sm-2">
                                <button type="button" class="btn btn-primary btn_cancle ">キャンセル</button>
                              </div>
                            </div> -->
                          </form>
                        <!-- </div> -->
                    </div>
            </div>
            <!-- BAR BODY -->
            
        </div>

</body>
</html>