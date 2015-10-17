<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style type="text/css">
.navbar-static-top{
	float: left;
}
.navbar-fixed-top, .navbar-static-top{
	background: none;
}

.nav-menu-left {
    left: 10px !important;
    bottom: 20px !important;
}
.img-thumb{
	margin-top: 32px;
}
</style>
	
<!-- Start Container -->
  <div id="container" class="container" style="margin-top:-40px">
  	<div class="row">
  		<div class="col-md-4">
  			<p style="font-size: 13pt; color: #000">名刺検索結果 ： <c:out value="${fn:length(cardInfoList)}"></c:out> 件</p>
  		</div>
  		<div class="col-md-8">
  			<span style="color:red;">本Webアプリは、他のグループ会社への共有が出来ない情報も含まれております。情報を取り扱う際には、詳細画面より共有可能な名刺かどうかをご確認ください。</span>
  		</div>
  	</div>
  	<div class="row" style="margin:10px 0 15px 0px;">
  		<button class="btn btn-primary btn-md" id="backListCard">組織画面へ戻る</button>
  	</div>
  	
    <div class="business_card_book">
      <div class="list-group">
        <c:if test="${ not empty cardInfoList }">
        	<c:forEach var="cardInfo" items="${cardInfoList}" varStatus="loop">
	        <div class="list-group-item pointer">
	          <div class="row row-new" id="${ cardInfo.cardId }">
	            <div class="col-md-1 col-xs-1"></div>
	            <div class="col-md-5">
	              <div class="col-xs-11 mg-top">
	                <p class="name"><c:out value="${ cardInfo.name }"></c:out></p>
	                <p class="livepass"><c:out value="${ cardInfo.companyName }"></c:out></p>
	                      <p class="department_and_position"><c:out value="${ cardInfo.departmentName }"></c:out> <c:out value="${ cardInfo.positionName }"></c:out></p>
	                <p class="num"><c:out value="${ cardInfo.telNumberCompany }"></c:out></p>
	                <p class="mail"><a href="mailto:${ cardInfo.email }"><c:out value="${ cardInfo.email }"></c:out></a></p>
	              </div>
	            </div>
	            <div class="col-md-6">
	              <div class="col-xs-5">
	                </div>
	                  <div class="col-xs-7">
	                  	<c:if test="${empty cardInfo.imageFile }">
							<a href="#" id="popup"> 
								<img id="imageresource" src='<c:url value="/assets/img/card_08.png"></c:url>'></a>	
						</c:if>
						<c:if test="${not empty cardInfo.imageFile}">
							<a href="#" title="" id="popup"> 
							<img src="<c:url value='/assets/img/loading.gif'/>" class="img-responsive img-thumb pull-right" alt=""></a>
							<input name="fileImageName" type="hidden" value="${cardInfo.imageFile}" />	
						</c:if>
	                  </div> 
	            </div>
	          </div>          
	        </div>
	        </c:forEach>
        </c:if>
      </div>
	<!-- End list group -->
    </div>
</div>  
<!-- End Container -->

<script type="text/javascript">
var isLoading = 0;
$(document).ready(function(){
	$("#backListCard").click(function(){
		goBack();
	});
	
	$(".row-new").click(function(){
		//console.log(this.id);
		window.location.href = "<c:url value='/user/card/details' />/"+this.id;
	});
	
	$(".business_card_book .img-responsive").each(function () {
	  	isLoading=isLoading+1;
		var target = $(this);
	    var fileImageName =$(this).parent().parent().find('input[name=fileImageName]').val();

	    $.ajax({
	        type: 'POST',
	        url: "<c:url value='/user/getImageFile' />",
	        data: 'fileImage='+fileImageName
	    }).done(function(resp, status, xhr){
	    	if(resp == ""){
	    		target.attr('src','');    	  
		        target.attr("src", "<c:url value='/assets/img/card_08.png' />");
	    	} else {
	    		target.attr('src','');    	  
		        target.attr('src','data:image/png;base64,'+resp);	
	    	}
	    	isLoading=isLoading-1;
	    }).fail(function(resp, status, xhr){
	        //alert('Error');
	    });
	});
	
});

function goBack(){
	window.history.back();
	return false;
}
</script>