<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
$(document).ready(function(){
	$('#btnBackToManageCard').on('click', function(){
		   document.location.href='/ecard-webapp/cards/list';
	});
});
</script>
<!-- BODY -->
<div class="bg-detail">
  <!-- BODY -->
  <div class="container padding-top20 bg-container height100per container-collectname">
	 <!-- CENTER SIDE -->
	
	 <div id="center-side" class="col-sm-12 ch-title">
	     <div class="row">
	         <h4 class="ch-pdl">完了ページ</h4>
	     </div>
	 </div>
	 <div id="center-side" class="col-sm-12">
	 	<div class="row bg-white box-shadow">
	         <div class="col-sm-12 ch-complate" id="ct1">
	             <h4>名刺情報の登録（承認/修正依頼/承認申請）が完了いたしました。</h4>
	             <div class="ch-form-10">
	                 <button class="btn" type="button" id="btnBackToManageCard">一覧へ戻る</button>
	             </div>
	         </div>
	         
	     </div>
	 </div> <!-- END CENTER SIDE  -->
	 <!-- END RIGHT SIDE -->
	</div>
</div>
<!-- END BODY -->