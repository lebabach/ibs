<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style>
</style>

<div class="container-fluid padding-top20 bg-container height100per">


	<!-- CENTER SIDE -->
	<div id="center-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12 ch-check" id="ct1">
				<div class="float-left col-sm-6">
					<h4 class="h4-header">利用者CSV登録</h4>

				</div>
				<div class="col-sm-3" style="float: right; margin: 0 auto;">
					<h4 class="h4-header">
						<span><button type="button"
								class="btn btn-primary btn_cancle" data-dismiss="modal">キャンセル</button></span>
						<span><button type="button" class="btn btn-primary"
								data-dismiss="modal" id="btnSaveUserProfile">登録</button></span>
					</h4>
				</div>


			</div>

		</div>

		<!-- END BAR TOP -->
		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom">
			<div style="margin: 0 auto">
				<div class="col-md-12 col-xs-offset-5">
					<div class="row">
						<form style="padding-bottom: 10px; color: black;">
							<select id="role" name="roles"
								style="width: 110px; height: 32px;">
								<option value=''>会社選択</option>
								<option>IBS</option>
								<option value='1'>Livepass</option>
								<option value='3'>Livepass</option>
							</select>
						</form>
					</div>
					<div class="row">
						<form style="padding-bottom: 10px; color: black;">
							<select id="role" name="roles"
								style="width: 110px; height: 32px;">
								<option value=''>CSVファイル選択</option>
								<option>デフォルト</option>
								<option value='1'>123.csv</option>
								<option value='3'>abc/csv</option>
							</select>
						</form>
					</div>
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
	});
	
	$('.btn_cancle').click(function(){
		document.location.href='/ecard-webapp/manager/home';
	});
</script>