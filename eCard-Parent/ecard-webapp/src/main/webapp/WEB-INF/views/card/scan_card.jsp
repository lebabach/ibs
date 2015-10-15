<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<link rel="stylesheet"
	href="<c:url value='/assets/css/style_scan.css'/>" type="text/css"
	media="all" />

<script type="text/javascript">
var base64 = "";
function rotateBase64Image(base64data, callback,rorate) {
    var canvas = document.getElementById("c");
    var ctx = canvas.getContext('2d');
    var image = new Image();
    image.src = base64data;
    image.onload = function() {
    	
        ctx.translate(image.width, image.height);
        ctx.rotate(rorate * Math.PI / 180);
        ctx.drawImage(image, 0, 0); 
       
        window.eval(""+callback+"('"+canvas.toDataURL()+"')");
    };

}

function callback(base64data) {
    console.log("AA= "+base64data);
}

var picFile = "";

$(document).ready(function(){
	 var output = document.getElementById("result");
     var file = null;
     var picReader = null;
	$("#files").on("change",function(event){
		var files = event.target.files; //FileList object
        for(var i = 0; i< files.length; i++)
        {
            file = files[i];
            //Only pics
            if(!file.type.match('image'))
              continue;
            
            picReader = new FileReader();
            
            picReader.addEventListener("load",function(event){
            	base64 = event.target.result;
                picFile = event.target;
                
                var div = document.createElement("div");
                
                div.className = 'list-view';
                div.innerHTML = "<img class='thumbnail img-icon-rotated' src='" + picFile.result + "'" +
                        "title='" + picFile.name + "'/>" + "<div class='lable'><input type='checkbox' class='i-checks i-checks-chk_all' checked name='bla'>"+ ' 取り込み</div>';
                output.insertBefore(div,null);
                 $('.i-checks').iCheck({
                          checkboxClass: 'icheckbox_square-green',
                          radioClass: 'iradio_square-green'
                           
                });
                $('.btn_process').removeClass('disabled')
            
            }); 
             //Read the image
            picReader.readAsDataURL(file);
        }
        return false;
	});
	
	$("#btnDelete").click(function(){
		if (confirm('<fmt:message key="operator.list.confirmDelete"/>')) {
			$("#result").find(".i-checks.i-checks-chk_all").each(function( index ) {
				  if($(this).is(":checked")){
					 $(this).parent().parent().parent().remove();
				  }
			});	
			
		}
		
	});
	$("#files").click(function(){
		console.log("click");
		resetRotationOfImages();
	});
	
	$('#btnBackToManageCard').on('click', function(){
		   document.location.href='/ecard-webapp/cards/list';
	});
	
	$("#btnCheckedCheckBox").click(function(){
		
		$("#result").find(".i-checks.i-checks-chk_all").each(function( index ) {
			 $(this).prop('checked', true);
			 $(this).parent().attr("class","icheckbox_square-green checked");
			
		});
	});
	$("#btnUnCheckedCheckBox").click(function(){
		$("#result").find(".i-checks.i-checks-chk_all").each(function( index ) {
			  $(this).prop('checked', false);
			  $(this).parent().attr("class","icheckbox_square-green");  
		});
	});
	
    /*scan card*/
	 $('input[type=file]').bootstrapFileInput();

    $('.i-checks').iCheck({
     checkboxClass: 'icheckbox_square-green',
     radioClass: 'iradio_square-green'
      
   });
  
  
   /*End scan card*/
   
    //enter search 
     $(".seach_card").keyup(function (e) {
		  if (e.which == 13) {
			  $('.btn_search').trigger('click');
		  }
	 });
   //end key up search
    /* list user */
   $('.btn_search').on('click', function() {
	   $('.content_user').html("");
	   var criteriaSearch = $('.seach_card').val();
		$.ajax({
			type: 'POST',
			url: 'listuser',
			cache: false,
			data: 'criteriaSearch='+criteriaSearch,
			success: function(response) {
				 $(function() {
					 var str = "";
					 $('.content_user').html(" ");
			            $.each(response, function(i, item) {
			            	$('.content_user').append($('<tr id="rowData" class="tr1">').append(
			                          $('<td>').append( 
				                        		$('<ul  class="list-group list_user_popup">').append(
				                        				$('<li  class="list-group-item it1 name_user ">').text(item.firstName + " " + item.lastName),
				                        				$('<li  class="list-group-item it2  ">').text(item.companyName),
				                        				$('<li  class="list-group-item it3  ">').text(item.departmentName),
				                        				$('<li  class="list-group-item it4  ">').text(item.positionName),
				                        				$('<input type="hidden" name="country" class="userId" value="'+item.userId+'">')
				                        				)
				                        		)
	                
					                    ));

			            });
			        });
				 $('#modelAddTag').modal('show');
				 
	        },
            error: function (response) {
            	alert("Error");
            }
		});
	}); 
   /* bind name user */
   $('#table_popup tbody.content_user').on("click", "tr", function() { 
	   var nameuser = $(this).find(".it1").text();
	   var userId = $(this).find(".userId").val();
       $('#exampleInputName2').val(nameuser);
       $('.user_id').val(userId);
       $('#modelAddTag').modal('hide');

	}); 
  
	$('.btn_register_card').on("click", function() { 
		   var userId = $('.user_id').val();
		   var rote = $("#rote-image").val();
		   var listCards = new Array();
		 
		   $('.list-view').each(function() {
				if($(this).find('.lable input[name=bla][type=checkbox]:checked').length != 0){
				  /*  var imagesource = $(this).find('img').attr('src');  
			       var n = imagesource.indexOf(","); */
				   var image = $(this).find('img').attr('src'); //imagesource.substring(n+1, base64.length);
				   var card = { "imageFile":image,"userId":userId,"rote":rote};
				   listCards.push({ 'card' : card});
			     }  
		    });
		   
		  if($("#exampleInputName2").val()!=""){
			   if(listCards.length>0){
				   $.ajax({
						type: 'POST',
						url: 'registerCardImage',
						 dataType: 'json', 
						 contentType: 'application/json',
						 mimeType: 'application/json',
						data:JSON.stringify({"listCards":listCards}) 
					}).done(function(data) {
						if(data == 0){
							location.reload();
						}else if(data == 1){
							alert("データ通信が利用できません");
							location.reload();
						}else if(data == 3){
							alert("データ通信が利用できません");
							location.reload();
						}else{
							alert("<fmt:message key="card.scancard.user.error"/>");
							//checkNotOk=0;
						}
					}).fail(function(xhr, status, err) {
						alert('エラー !!!');
						location.reload();
					});
			   } 
		   }else{
			   alert('ユーザを選択してください');
		   }
			   
		   
		   
		});
 	//bach.le reset rotation
   function resetRotationOfImages(){
	   value =0;
       $("#rote-image").val(0);
   }
   function getRoteImage(roteOfImage){
	 var rote=0;
  	 if($.isNumeric(roteOfImage)){
  		 rote=parseInt(roteOfImage)
  	 } else{
  		rote=0; 
  	 }
  	 return rote;
   }
   
   /*rotate  image  */
   
    var value = 0
    $('.btn_rotate_180').click(function(){
    	 //var value = 0
    	 value +=180;
    	 var rote=getRoteImage($("#rote-image").val());
    	
         $("#rote-image").val(rote+180);
    	 $('.list-view').each(function() {
	 		 if($(this).find('.lable input[name=bla][type=checkbox]:checked').length != 0){
	 			$(this).find('img').rotate({ 
	 				animateTo:value
 				});
	 			//rotateBase64Image(picFile, callback);
	 		 }
    	 });
 		 
     });
   
   /*rotate left 90*/
    $('.btn_rotate_left_90').click(function(){
    	 //var value = 0
    	value -=90;
    	var rote=getRoteImage($("#rote-image").val());
    	
        $("#rote-image").val(rote+270);
    	 $('.list-view').each(function() {
	 		 if($(this).find('.lable input[name=bla][type=checkbox]:checked').length != 0){
	 			$(this).find('img').rotate({ 
	 				animateTo:value
 				});
	 			
	 		 }
    	 });
 		 
     });
    /*rotate rightt  90*/
    $('.btn_rotate_right_90').click(function(){
    	 //var value = 0
    	value +=90;
    	var rote=getRoteImage($("#rote-image").val());
    	$("#rote-image").val(rote+90);
    	 $('.list-view').each(function() {
	 		 if($(this).find('.lable input[name=bla][type=checkbox]:checked').length != 0){
	 			$(this).find('img').rotate({ 
	 				animateTo:value
 				});
	 			
	 		 }
    	 });
 		 
     });
   
   /* end rotate  image */
});

</script>
</head>
<body>
	<div class="container-fluid padding-top20 bg-container height100per">
		<!-- RIGHT SIDE -->
		<div id="right-side" class="col-sm-12">
			<!-- BAR TOP -->
			<div class="row bg-white box-shadow menu-top-header col-sm-12">
				<div class="col-sm-12">
					<div class="float-left">
						<h4 class="h4-header">Scan</h4>
					</div>

					<div class="float-right float-right-manage"></div>
				</div>
			</div>

			<!-- END BAR TOP -->
			<div
				class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
				<!-- <div class="col-sm-8 col-xs-offset-2" id="data-table"> -->
				<div id="content">
					<div id="inner">
						<div class="box-1">
							<div class="title-box-1">IBS scan</div>
							<div class="content-box-1">
								<div class="ch-scan">
									<input type="file" name="files[]" class="submit-1" id="files"
										multiple title="フォルダ選択" style="left: -196.859375px; top: 3px;">
								</div>

								<div class="box-2">
									<div class="title-box-2"></div>
									<div class="content-box-2">
										<output id="result" />
										<div class="ch-list-but">
											<!-- <input class="btn btn-default btn_rotate_180" type="button" value="180°回転" style="color: #666666; width:100px">
                                                        <input class="btn btn-default btn_rotate_left_90" type="button" value="左に90°回転" style="color: #666666; width:100px">
                                                        <input class="btn btn-default btn_rotate_right_90" type="button" value="右に90°回転" style="color: #666666; width:100px"> -->
											<input id="btnDelete" class="btn btn-default" type="button"
												value="削除" style="color: #666666; width: 100px"> <input
												id="btnCheckedCheckBox" class="btn btn-default"
												type="button" value="全て選択"
												style="color: #666666; width: 100px"> <input
												id="btnUnCheckedCheckBox" class="btn btn-default"
												type="button" value="全て選択解除"
												style="color: #666666; width: 100px"> <input
												type="hidden" name="rote" id="rote-image" value="">
										</div>
										<div id="loading"
											style="display: none; z-index: 1000; position: fixed; top: 288px; margin: 0 auto; left: 796px">
											<p>
												<img src="../assets/img/loader.gif" height="200" width="235"
													style="border-radius: 238px; opacity: 0.5;" />
											</p>
										</div>
									</div>
								</div>
								<div class="btn-footer col-sm-12">
									<div class="btn-left col-sm-2"
										style="float: left; text-align: left; padding-left: 0;">
										<button type="button" id="btnBackToManageCard"
											class="btn btn-primary btn_process  ">閉じる</button>
									</div>
									<div class="btn-content col-sm-8" style="text-align: left;">
										<button type="button" class="btn btn-primary btn_search "
											data-toggle="modal">利用者検索</button>
										<input type="hidden" name="userId" class="user_id" value="">
										<input type="text" class="form-control" id="exampleInputName2"
											placeholder="（利用者の所属・名前が入る）"
											style="width: 300px; display: inline-block;" disabled>
									</div>
									<div class="btn-right col-sm-2"
										style="float: right; padding-right: 0; text-align: right;">
										<button type="button"
											class="btn btn-primary btn_process btn_register_card ">サーバーに送信</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- BAR BODY -->

		</div>
		<!-- BAR BODY -->

	</div>
	<!-- END RIGHT SIDE -->
	<!-- MODEL ADD TAG -->
	<div class="modal fade modelAdd" id="modelAddTag" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header"
					style="background-color: #1ab394; text-align: left;">
					<h4 class="modal-title" style="text-align: left;">
						<fmt:message key="popup.heading" />
					</h4>
				</div>
				<div class="modal-body">
					<h4 class="modal-title">
						<div class="input-group">
							<input type="text" class="form-control seach_card"
								value="${operatorSearchVO.criteriaSearch}" name="criteriaSearch"
								placeholder="<fmt:message key="operator.list.placeholder"/>">
							<span class="input-group-btn">
								<button type="submit" class="btn btn-primary btn-sm btn_search">
									<fmt:message key="operator.list.search" />
								</button>
							</span>
						</div>
					</h4>

					<div class="col-sm-12">
						<!--   <div class="title_card">
                                    <h4 class="h4-header">リストカード</h4>
                                </div> -->
						<div class="row" id="data-table">
							<div class="ibox-content"
								style="height: 500px; overflow: overlay;">
								<table class="table" id="table_popup">
									<tbody class="content_user">
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">キャンセル</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">OK</button>
				</div>
			</div>
		</div>
	</div>
	<!-- END MODEL ADD TAG -->
	<!-- bPopUp -->
	<div id="element_to_pop_up" style="display: none;">Please choose
		user need upload image</div>
	</div>

</body>
</html>

