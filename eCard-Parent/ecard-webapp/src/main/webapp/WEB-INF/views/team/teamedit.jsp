<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page session="false" %>
<style>
	.div-bar-top{
		width:100%;
		display:inline-block;
		text-align:center;
		margin:0 auto;
	}
	.div-bar-top2{
		float:none !important;
		margin-left: auto !important;
	}
</style>
<script type="text/javascript">
function checkValidationForm(){
	var checkValidation=true;
	if($("#name").val()==""){
		$(".sp-name").text("<fmt:message key='validate.null'/>");
		checkValidation=false;
	}
	return checkValidation;
};
$(document).ready(function(){
	
	$('#btnSave').on('click', function(){
		if(!checkValidationForm()){
			return false;
		}
		$('#formSubmit').submit();
	});
});
</script>
<body>  
      <!-- BODY -->
     <div class="container-fluid padding-top20 bg-container height100per">
        
        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12 div-bar-top">
        	<!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header col-sm-12">
            	<div class="col-sm-12">
                	<div class="float-left">
                    	<h4 class="h4-header">チーム登録・修正画面</h4>
                    </div>
                	<div class="col-sm-3" style="float: right;margin: 0 auto;">
                           <h4 class="h4-header">
                        <span><button type="submit" class="btn btn-primary" data-dismiss="modal" id="btnSave" style="float: right;">保存</button></span>
                            <span><button type="button" class="btn btn-primary" data-dismiss="modal" onclick="window.location.href='/ecard-webapp/teams/list'"  id="btnSaveUserProfile" style="float: right;">キャンセル</button></span>
                            </h4>
                     </div>
                    <div class="float-right float-right-manage">
                         
                    </div>
                </div>
            </div>
            
            <!-- END BAR TOP -->
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
					<div class="container col-sm-8 col-xs-offset-2 div-bar-top2" id="data-table" style="width: 600px;padding-bottom: 77px;">
						<div class="container">
                          <form class="form-horizontal" role="form" action="${teamDisplayVO.action}" id="formSubmit"  method="POST" >
                            <div class="form-group">
                              <label class="control-label col-sm-1" for="team" style="width: 120px">チーム名 <span style="color:red">*</span></label>
                              <div class="col-sm-4">
                                <input name="name" type="text" class="form-control" id="name" maxlength="200" size="200" value="${teamDisplayVO.name}">
								<span class='sp-name' style="color:red"></span>
								 <input name="count" type="hidden" class="form-control" id="count" value="${teamDisplayVO.count}">
                              </div>
                            </div>
                          </form>
                        </div>
					</div>
			</div>
            <!-- BAR BODY -->
            
        </div>
        <!-- END RIGHT SIDE -->
      </div>
