<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style>
.mesage_error {
	color: red;
	display: none;
}
</style>

<div class="container-fluid padding-top20 bg-container height100per">


	<!-- CENTER SIDE -->
	<div id="center-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12 ch-check" id="ct1">
				<div class="float-left col-sm-6">
					<h4 class="h4-header">オペレータCSV登録</h4>

				</div>
				<div class="col-sm-3" style="float: right; margin: 0 auto;">
					<h4 class="h4-header">
						<span><button type="button"
								class="btn btn-primary btn_cancle" data-dismiss="modal">キャンセル</button></span>
						<span><button type="button" class="btn btn-primary"
								data-dismiss="modal" id="btnUploadUser">登録</button></span>
					</h4>
				</div>


			</div>

		</div>

		<!-- END BAR TOP -->
		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
			<div style="margin: 0 auto">
				<div class="col-md-12 col-xs-offset-5">
					<form class="form-horizontal" role="form" id="upload"
						action="/ecard-webapp/data/uploadOperatorCSV"
						enctype="multipart/form-data" method="post">
						<div class="row" style="margin-bottom: 5px">
							<select id="groupId" name="roles"
								style="width: 170px; height: 32px;">
								<option value=''>会社選択</option>
								<c:forEach var="listGroupCompany" items="${listGroupCompany}"
									varStatus="loop">
									<option value='${listGroupCompany.groupCompanyId}'><c:out
											value="${listGroupCompany.groupCompanyName}" /></option>
								</c:forEach>
							</select>
							<p class="mesage_error group_company"></p>
							<input type="text" name="groupCompanyId" class="hidden" value="">
						</div>
						<div class="row">
							<input type="file" name="file" class="submit-1" id="files">
							<p class="mesage_error file_name"></p>

						</div>
					</form>
				</div>
				<div class="row" style="text-align: center; margin: 25px;">
					<p class="mesage_error error_exception"></p>
				</div>
			</div>
		</div>
	</div>
	<!-- BAR BODY -->

</div>


<script type="text/javascript">
	$(document).ready(function(){
	    $('.i-checks').iCheck({
	    checkboxClass: 'icheckbox_square-green',
	    radioClass: 'iradio_square-green'
	
	  });
	
	  $('.img-icon-rotate').tooltip();
	  //rotate image
	  var value = 0
	  $(".img-icon-rotate").rotate({
	    bind:
	    {
	      click: function(){
	        value +=90;
	        $("#img-icon-rotated").rotate({ animateTo:value});
	      }
	    }
	  });
	  //
	   function centerModal() {
	      $(this).css('display', 'block');
	      var $dialog = $(this).find(".modal-dialog");
	      var offset = ($(window).height() - $dialog.height()) / 2;
	      // Center modal vertically in window
	      var path_image = $(".img-icon-rotated").attr('src');
	      $("#img-icon-rotated").attr("src", path_image);
	      $dialog.css("margin-top", offset);
	  }
	
	  $('.modal').on('show.bs.modal', centerModal);
	  $(window).on("resize", function () {
	      $('.modal:visible').each(centerModal);
	  });
	
	  $('.js-ch-click').click(function(){
	      $('.js-ch-click input').click();
	  });
	  
	  var error = getUrlParameter('error');
	  if(error == "formatCSV"){
		  $(".error_exception").text("CSV形式が正しくないため、データの取り込みは行われませんでした。");
          $(".mesage_error").css("display","block");
	  }
	  
	  if(error == "insertData"){
		  $(".error_exception").text("取り込みできなかったデータがあります。出力されたCSVをご確認ください");
          $(".mesage_error").css("display","block");
	  }
	  
	  if(error == "Error"){
		  $(".error_exception").text("データ登録中に内部エラーが発生しました。  管理者に問い合わせてください。");
          $(".mesage_error").css("display","block");
	  }
	  
	});

	$('.btn_cancle').click(function(){
		document.location.href='/ecard-webapp/manager/home';
	});
	
	$('select').on('change', function() {		
		$('input[name=groupCompanyId]').val(this.value);
		$('input[name=groupCompanyId]').value = this.value;
	});
	
	$('#btnUploadUser').on('click', function(){
		validationFile();
		checkValidationForm();
		if(!checkValidationForm()){			
			return false;
		}
		if(!validationFile()){			
			return false;
		}
		$('#upload').submit();
	});
	/* Function util */
		function validationFile(){
		var checkValidation=true;
		var x = document.getElementById("files");
		var txt;
		if ('files' in x) {
	        if (x.files.length != 1) {
	        	$(".file_name").text("<fmt:message key='import.file.validate'/>");
	            $(".mesage_error").css("display","block");
	            checkValidation = false;
	        } else {
	        	if (x.files.length == 0) {
	                txt = "Select one or more files.";
	            } else {
	                for (var i = 0; i < x.files.length; i++) {
	                    
	                    var file = x.files[i];
	                    if ('name' in file) {
	                        txt += "name: " + file.name + "<br>";
	                    }
/* 	                    if ('size' in file) {
	                    	if(file.size > 1000000){
	                    		$(".file_name").text("<fmt:message key='import.file.validate.large'/>");
	        	        		$(".mesage_error").css("display","block");
	        	        		checkValidation = false;
	        	        	}
	                        
	                    } */
	                }
	            }
	        }
	        $('.file_name').innerHTML = txt;
	    }
		return checkValidation;
	}
	function checkValidationForm(){
		var checkValidation=true;
		if($("#groupId").val() == ""){
			$(".group_company").text("<fmt:message key='import.group.company.validate'/>");
			checkValidation = false;
			$(".mesage_error").css("display","block");
		}
		/* if($('.file-input-name').text() == ""){
			$(".file_name").text("<fmt:message key='import.file.validate'/>");
			checkValidation = false;
			$(".mesage_error").css("display","block");
		} */
		return checkValidation;
	};
	
	function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;

	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');

	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
	
</script>