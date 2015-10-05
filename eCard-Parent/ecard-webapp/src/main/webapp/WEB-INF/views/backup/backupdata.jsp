<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<style>
.height100per {
    height: 100%;
}
.success{
   color: #00CC99;
    text-align: center;
    border: 1px solid #00FFFF;
    padding-top: 14px;
    height: 50px;
    width: 50%;
    margin: 0 auto;
}
.error{
   color:red;
   text-align: center;
   border:1px solid red;
   padding-top: 14px;
   background-color:#9966CC;
   height:50px;
   width: 50%;
   margin: 0 auto;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$('.btn-backup').on('click', function() {
		  var timebackup = $('.time-backup').val();
			$.ajax({
				type: 'POST',
				url: '/ecard-webapp/backupdatas/backupData',
				data: 'timebackup='+ timebackup
			}).done(function(resp, status, xhr) {
				
				if(resp ==0){
					$('#message').removeClass('error');
					$('#message').addClass('success');
					$('#message').text('データをバックアップしました');
					
				}else{
					$('#message').removeClass('success');
					$('#message').addClass('error');
					$('#message').text('データのバックアップ中にエラーが発生しました');
				}
			}).fail(function(xhr, status, err) {
				alert('Error');
			});
		 
	   });
	
	$('.btn-restore').on('click', function() {
		 // var filerestore = 'C:\\Users\\admin\\Downloads\\eclipse\\backup\\manual\\20150824_db_back.csv';
			$.ajax({
				type: 'POST',
				url: '/ecard-webapp/backupdatas/restoredata'
			}).done(function(resp, status, xhr) {
				if(resp ==0){
					$('#message').removeClass('error');
					$('#message').addClass('success');
					$('#message').text('リストア完成');
					
				}else{
					$('#message').removeClass('success');
					$('#message').addClass('error');
					$('#message').text('リストアエーラ');
				}
			}).fail(function(xhr, status, err) {
				alert('Error');
			});
		 
	   });
});
</script>
<!-- BODY -->

	<!-- RIGHT SIDE -->
	<div id="right-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header">
			<div class="col-sm-12">
				<div class="float-left">
					<h4 class="h4-header"><fmt:message key="backup.header"/></h4>
				</div>

			</div>
		</div>

		<!-- END BAR TOP -->
		<div class="row bg-white  box-marginTop5 padding-top-bottom">
		<div class="container container-left backup-restored" >
                        <div class="row clearfix">
                            <div class="col-md-6 col-md-offset-3 column">
                                <form role="form">
                                        <ul class="list-li">
                                            <li>
                                                <div id="message" ></div>
                                            </li>
                                            <li>
                                                <div class="col-xs-12">
                                                    <div class="col-xs-6">
                                                         <input type="checkbox" class="i-checks idRow i-checks-chk" id="1"> <span>自動バックアップ時間</span>
                                                    </div>
                                                    <div class="col-xs-6">
                                                            <div class="form-group">
                                                                <div class='input-group input-group clockpicker' data-placement="left" data-align="top" data-autoclose="true" id='datetimepicker-icon'>
                                                                    <input type='text' class="form-control time-backup" placeholder = "hh:mm:ss" />
                                                                   <span class="input-group-addon">
                                                                        <span class="glyphicon glyphicon-time"></span>
                                                                    </span>
                                                                </div>
                                                            </div>
                                                            
                                                    </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="col-xs-12">
                                                     <div class="form-group">
                                                        <label for="lastname-furigana" class="control-label col-xs-6">手動バックアップ</label>
                                                        <div class="col-xs-6">
                                                            <button type="button" class="btn btn-primary btn-backup" >Backup</button>
                                                        </div>
                                                     </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="col-xs-12">
                                                    <div class="form-group">
                                                        <label for="lastname-furigana" class="control-label col-xs-6">リストア</label>
                                                        <div class="col-xs-6">
                                                             <button type="button" class="btn btn-primary btn-restore" data-dismiss="modal">Restore</button>
                                                        </div>
                                                    </div> 
                                                </div>
                                            </li>
                                        
                                       </ul>     
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- END  BACKUP RESTORE SCREEN -->
        </div>
		<!-- BAR BODY -->
 </div>
<!-- END BODY -->


