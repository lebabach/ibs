<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#btn_back').on('click', function(){
		document.location.href='/ecard-webapp/cards/edit/${cardInfoWithRoteVO.cardInfo.cardId}';
	});
	
	$('.btn-add').on('click', function(){
			var date = $('input[name=contactDate]').val();
			var date_rep;
			if(date.indexOf("ICT") > 0){
				date_rep = date.replace("ICT", "GMT");
			}else{
				date_rep = date.replace("JST", "GMT");
			}
			
			var contactDate = new Date(date_rep);
		   $('input[name=contactDate]').val(contactDate);
		    $('#formsubmitCard').submit();
		
	   });
});
</script>
<!-- BODY -->
<div class="bg-detail">
  <!-- BODY -->
  <form class="container padding-top20 bg-container height100per container-collectname" id= "formsubmitCard" method="POST" action="/ecard-webapp/cards/editSuccess" accept-charset="UFT-8">
	<input type="hidden" name="cardId" value="${cardInfoWithRoteVO.cardInfo.cardId}">
	<input type="hidden" name="approvalStatus" value="${cardInfoWithRoteVO.cardInfo.approvalStatus}">
	<input type="hidden" name="cardBackImgFile" value="${cardInfoWithRoteVO.cardInfo.cardBackImgFile}">
	<!-- CENTER SIDE -->

	<div id="center-side" class="col-sm-12 ch-title">
		<div class="row">
			<h4 class="ch-pdl">登録確認</h4>
		</div>
	</div>
	<div id="center-side" class="col-sm-12">
		<div class="row bg-white box-shadow">			
				<div class="col-sm-12" id="ct1">
					<div class="row">
						<div class="col-sm-5">
							<h4 class="padding-lef">以下の内容で名刺情報を登録（承認/修正依頼/承認申請）します。
	ご確認のうえ、ページ下部より登録してください。</h4>
						</div>
					</div>
					<div class="float-left" style="width: 35%;">
						<h4 class="h4-header"><a href="#"><img src="${cardInfoWithRoteVO.cardInfo.imageFile}" class="img-100 img-full"></a></h4>
						<input type="hidden" name="imageFile" value="${cardInfoWithRoteVO.cardInfo.imageFile}">
						<input type="hidden" name="rote" id="rote-image" value="${cardInfoWithRoteVO.rote}">
					</div>
				<!-- <div class="float-right flr-400" style="width: 60%;"> -->
					<div class="row box-shadow bg-white float-right flr-400" style="width: 60%;">
					   <div class="col-sm-12 right-side-top">        
						</div>
						<div class="row" id="content-detail-card" style="width:100%;">
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>氏名</h4>
								</div>
								<div class="float-right float-right-content">
								   <h4 class=""></h4>
								</div>
							</div> 
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>氏名（姓）</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.lastName}"/></p>
								   <input type="hidden" name="lastName" value="${cardInfoWithRoteVO.cardInfo.lastName}">
								</div>
							</div> 
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>氏名（名）</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.firstName}"/></p>
								   <input type="hidden" name="firstName" value="${cardInfoWithRoteVO.cardInfo.firstName}">
								</div>
							</div> 
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>氏名（姓）カナ</h4>
								</div>
								<div class="float-right float-right-content">
								  <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.lastNameKana}"/></p>
								  <input type="hidden" name="lastNameKana" value="${cardInfoWithRoteVO.cardInfo.lastNameKana}">
								</div>
							</div>  
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>氏名（名）カナ </h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.firstNameKana}"/></p>
								   <input type="hidden" name="firstNameKana" value="${cardInfoWithRoteVO.cardInfo.firstNameKana}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>連絡先 </h4>
								</div>
								<div class="float-right float-right-content">
								   
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>電話番号 1</h4>
								</div>
								<div class="float-right float-right-content">	   
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.telNumberCompany}"/></p>
								   <input type="hidden" name="telNumberCompany" value="${cardInfoWithRoteVO.cardInfo.telNumberCompany}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>電話番号 2</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.telNumberDepartment}"/></p>
								   <input type="hidden" name="telNumberDepartment" value="${cardInfoWithRoteVO.cardInfo.telNumberDepartment}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>電話番号(直通)</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.telNumberDirect}"/></p>
								   <input type="hidden" name="telNumberDirect" value="${cardInfoWithRoteVO.cardInfo.telNumberDirect}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>携帯電話番号</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.mobileNumber}"/></p>
								   <input type="hidden" name="mobileNumber" value="${cardInfoWithRoteVO.cardInfo.mobileNumber}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>FAX番号</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.faxNumber}"/></p>
								   <input type="hidden" name="faxNumber" value="${cardInfoWithRoteVO.cardInfo.faxNumber}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>メールアドレス</h4>
								</div>
								<div class="float-right float-right-content">
								  <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.email}"/></p>
								  <input type="hidden" name="email" value="${cardInfoWithRoteVO.cardInfo.email}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>会社名</h4>
								</div>
								<div class="float-right float-right-content">
								  <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.companyName}"/></p>
								  <input type="hidden" name="companyName" value="${cardInfoWithRoteVO.cardInfo.companyName}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>会社名カナ</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.companyNameKana}"/></p>
								   <input type="hidden" name="companyNameKana" value="${cardInfoWithRoteVO.cardInfo.companyNameKana}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>部署名</h4>
								</div>
								<div class="float-right float-right-content">							  
								  <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.departmentName}"/></p>
								  <input type="hidden" name="departmentName" value="${cardInfoWithRoteVO.cardInfo.departmentName}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>役職名</h4>
								</div>
								<div class="float-right float-right-content">								   
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.positionName}"/></p>
								   <input type="hidden" name="positionName" value="${cardInfoWithRoteVO.cardInfo.positionName}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>会社URL</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.companyUrl}"/></p>
								   <input type="hidden" name="companyUrl" value="${cardInfoWithRoteVO.cardInfo.companyUrl}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>郵便番号</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.zipCode}"/></p>
								   <input type="hidden" name="zipCode" value="${cardInfoWithRoteVO.cardInfo.zipCode}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>住所（都道府県）</h4>
								</div>
								<div class="float-right float-right-content">  
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.address1}"/></p>
								   <input type="hidden" name="address1" value="${cardInfoWithRoteVO.cardInfo.address1}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>住所（市区町村）</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.address2}"/></p>
								   <input type="hidden" name="address2" value="${cardInfoWithRoteVO.cardInfo.address2}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>住所（番地）</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.address3}"/></p>
								   <input type="hidden" name="address3" value="${cardInfoWithRoteVO.cardInfo.address3}">
								   <input type="hidden" name="address4" value="${cardInfoWithRoteVO.cardInfo.address4}">
								   <input type="hidden" name="addressFull" value="${cardInfoWithRoteVO.cardInfo.addressFull}">
								</div>
							</div>
							<%-- <div class="col-sm-12">
								<div class="float-left">
								   <h4>備考１</h4>
								</div>
								<div class="float-right float-right-content"> 
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.memo1}"/></p>
								   <input type="hidden" name="memo1" value="${cardInfoWithRoteVO.cardInfo.memo1}">
								</div>
							</div>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>備考２</h4>
								</div>
								<div class="float-right float-right-content">
								   <p class="confirm"><c:out value="${cardInfoWithRoteVO.cardInfo.memo2}"/></p>
								   <input type="hidden" name="memo2" value="${cardInfoWithRoteVO.cardInfo.memo2}">
								</div>
							</div> --%>
							<div class="col-sm-12">
								<div class="float-left">
								   <h4>名刺交換日</h4>
								</div>
								<div class="float-right float-right-content">
								   <h4 style="width:250px;"><fmt:formatDate value="${cardInfoWithRoteVO.cardInfo.contactDate}" pattern="yyyy年MM月dd日" /></h4>
								   <input type="hidden" name="contactDate" class ="updateDate" value='<c:out value="${cardInfoWithRoteVO.cardInfo.contactDate}" />'>
								   <input type="hidden" name="rote" id="rote-image" value="${cardInfoWithRoteVO.cardInfo.contactDate}">
								</div>
							</div>
						</div>
						<div class="ch-form-10">
							<button class="btn" id="btn_back" type="button">戻る</button>
							<button class="btn btn-add" type="button">登録</button>
                        </div>
					</div>
			   <!--  </div> -->
				</div>	
			</div>
		</div> <!-- END CENTER SIDE  -->
	<!-- END RIGHT SIDE -->
	</form>
</div>
<!-- END BODY -->