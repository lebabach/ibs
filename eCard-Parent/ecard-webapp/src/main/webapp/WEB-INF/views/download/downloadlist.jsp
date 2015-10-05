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
.div-gird{
	border-top:2px solid #777777;		
	width:90%;
	display:inline-block;
	height:auto;
	text-align:center;
	border-collapse:collapse;
	}
.div-gird td,
.div-gird th{
	border:1px solid #a9a9a9;
	padding:10px;
	}	
.div-gird th{
	text-align: center;
	width:10%;
	background: #efefef;
	}
.div-gird th.th2{
	width:5%;
	}
.div-gird th.th4{
	width:50%;
	background:#e4eef5;
	}
</style>
<script type="text/javascript">
$(document).ready(function() {
	$(document).on('click',"#ok-01",function(){
	    $('#first-table .i-checks').each(function(){
	      if($(this).find('input').prop('disabled') == false) {
	        if($(this).find('input').prop('checked')) {
	          id = $(this).data('check-id');
	          $("#ft-status-"+id).text('承認');
	          $("#ft-status-name-"+id).text('承認 次郎');
	          $(this).find('input').attr('disabled',true);
	          $(this).find('.icheckbox_square-green').addClass('disabled');
	          var downloadId = $(this).find('input').val();
	  		
	  		  console.log('BBB='+downloadId);
	          $.ajax({
	  			type: 'POST',
	  			url: '/ecard-webapp/download/recognitionDownload',
	  			data: 'csvId='+ downloadId+'&csvStatus=1'
	  		}).done(function(resp, status, xhr) {
	  			document.location.href='/ecard-webapp/download/list';
	  		}).fail(function(xhr, status, err) {
	  			alert('Error');
	  		}); 
	        }
	      }
	    });
	  });

	  $(document).on('click',"#ng-01",function(){
	    $('#first-table .i-checks').each(function(){
	      if($(this).find('input').prop('disabled') == false) {
	        if($(this).find('input').prop('checked')) {
	          id = $(this).data('check-id');
	          $("#ft-status-"+id).text('否認');
	          $("#ft-status-name-"+id).text('否認 三郎');
	          $(this).find('input').attr('disabled',true);
	          $(this).find('.icheckbox_square-green').addClass('disabled');
	          var downloadId = $(this).find('input').val();
		  		
	  		  console.log('BBB='+downloadId);
	          $.ajax({
	  			type: 'POST',
	  			url: '/ecard-webapp/download/recognitionDownload',
	  			data: 'csvId='+ downloadId+'&csvStatus=2'
	  		}).done(function(resp, status, xhr) {
	  			document.location.href='/ecard-webapp/download/list';
	  		}).fail(function(xhr, status, err) {
	  			alert('Error');
	  		});
	        }
	      }
	    });
	  });

	  $(document).on('click',"#ok-02",function(){
	    $('#second-table .i-checks').each(function(){
	      if($(this).find('input').prop('disabled') == false) {
	        if($(this).find('input').prop('checked')) {
	          id = $(this).data('check-id');
	          $("#st-status-"+id).text('承認');
	          $("#st-status-name-"+id).text('承認 次郎');
	          $(this).find('input').attr('disabled',true);
	          $(this).find('.icheckbox_square-green').addClass('disabled');
	          var downloadId = $(this).find('input').val();
		  		
	  		  console.log('BBB='+downloadId);
	          $.ajax({
	  			type: 'POST',
	  			url: '/ecard-webapp/download/recognitionDownload',
	  			data: 'csvId='+ downloadId+'&csvStatus=1'
	  		}).done(function(resp, status, xhr) {
	  			document.location.href='/ecard-webapp/download/list';
	  		}).fail(function(xhr, status, err) {
	  			alert('Error');
	  		});
	        }
	      }
	    });
	  });

	  $(document).on('click',"#ng-02",function(){
	    $('#second-table .i-checks').each(function(){
	      if($(this).find('input').prop('disabled') == false) {
	        if($(this).find('input').prop('checked')) {
	          id = $(this).data('check-id');
	          $("#st-status-"+id).text('否認');
	          $("#st-status-name-"+id).text('否認 三郎');
	          $(this).find('input').attr('disabled',true);
	          $(this).find('.icheckbox_square-green').addClass('disabled');
	          var downloadId = $(this).find('input').val();
		  		
	  		  console.log('BBB='+downloadId);
	          $.ajax({
	  			type: 'POST',
	  			url: '/ecard-webapp/download/recognitionDownload',
	  			data: 'csvId='+ downloadId+'&csvStatus=2'
	  		}).done(function(resp, status, xhr) {
	  			document.location.href='/ecard-webapp/download/list';
	  		}).fail(function(xhr, status, err) {
	  			alert('Error');
	  		});
	        }
	      }
	    });
	  });
	  
	 
	
	$('.btn-cancel').on('click', function() {
		var downloadId = $(this).parent().find('input[name=csvId]').val();
		console.log('BBB='+downloadId);
		$.ajax({
			type: 'POST',
			url: '/ecard-webapp/download/recognitionDownload',
			data: 'csvId='+ downloadId+'&csvStatus=0'
		}).done(function(resp, status, xhr) {			
			document.location.href='/ecard-webapp/download/list';
		}).fail(function(xhr, status, err) {
			alert('Error');
		});
	}); 
	
	$('#first-table').dataTable({
		"dom" : '<<t>ip>',
		"ordering" : false,
		"iDisplayLength" : 5,
		"language": {
			"zeroRecords": '<fmt:message key="operator.list.table.emptyTable"/>',
			"emptyTable": '<fmt:message key="operator.list.table.emptyTable"/>',
			"info": '<fmt:message key="operator.list.table.info"/>',
			"infoEmpty": '<fmt:message key="operator.list.table.info"/>',
			"paginate": {
				"previous": '<fmt:message key="operator.list.table.paginate.previous"/>',
				"next": '<fmt:message key="operator.list.table.paginate.next"/>'
			}
		},
	});
	$('#second-table').dataTable({
		"dom" : '<<t>ip>',
		"ordering" : false,
		"iDisplayLength" : 5,
		"language": {
			"zeroRecords": '<fmt:message key="operator.list.table.emptyTable"/>',
			"emptyTable": '<fmt:message key="operator.list.table.emptyTable"/>',
			"info": '<fmt:message key="operator.list.table.info"/>',
			"infoEmpty": '<fmt:message key="operator.list.table.info"/>',
			"paginate": {
				"previous": '<fmt:message key="operator.list.table.paginate.previous"/>',
				"next": '<fmt:message key="operator.list.table.paginate.next"/>'
			}
		},
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
					<h4 class="h4-header"><fmt:message key="download.header"/></h4>
				</div>

			</div>
		</div>

		<!-- END BAR TOP -->
		<div class="row bg-white  box-marginTop5 padding-top-bottom">
		<div class="container container-left backup-restored" >
           <div class="row clearfix">
           	<p><fmt:message key="download.title"/></p>
           	</br>
           	<div class="row " id="data-table">
           <div class="ibox-content ibox-custom01">
           		
               <table id="first-table" class="table container paging" style="padding: 0;">
                   <thead style="">
                       <tr>
                           <td colspan="7"  style="background-color: #fff; padding-left: 0;">自社名刺ダウンロード申請者</td>
                       </tr>
                       <tr>
                           <th></th>
                           <th><fmt:message key="download.user.request"/></th>
                           <th><fmt:message key="download.user.department"/></th>
                           <th><fmt:message key="download.user.position"/></th>
                           <th><fmt:message key="download.operater.process"/></th>
                           <th><fmt:message key="download.request.status"/></th>
                           <th><fmt:message key="download.date.request"/></th>
                       </tr>
                   </thead>
                   <tbody style="">
	                   <c:forEach var="downloadGroupCSVHistory" items="${downloadGroupCSVHistory}" varStatus="loop">
                     			                  		
		                  		<tr>
		                  			<td>
		                  				<div class="i-checks" data-check-id="${downloadGroupCSVHistory.csvId}">
		                  				<%-- <input class="hidden" name="csvId" value="${downloadGroupCSVHistory.csvId}"> --%>
		                  					<c:if test="${downloadGroupCSVHistory.csvApprovalStatus!=0}">
												<label class=""> <div class="icheckbox_square-green checked disabled" style="position: relative;">
			                  					<input type="checkbox" value="${downloadGroupCSVHistory.csvId}" checked disabled style="position: absolute; opacity: 0;">
			                  					<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
				                  				</div>
				                  				</label>	
											</c:if>
											<c:if test="${downloadGroupCSVHistory.csvApprovalStatus==0}">
												<label class=""> <div class="icheckbox_square-green" style="position: relative;">
			                  					<input type="checkbox" value="${downloadGroupCSVHistory.csvId}" style="position: absolute; opacity: 0;">
			                  					<ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins>
				                  				</div>
				                  				</label>	
											</c:if>
			                  				
		                  				</div>
		                  				<%-- <input class="hidden" name="csvId" value="${downloadGroupCSVHistory.csvId}"> --%>
		                  			</td>
		                  			
		                  			<td><c:out value="${downloadGroupCSVHistory.userName}"/></td>		                  			
		                  			<td><c:out value="${downloadGroupCSVHistory.departmentName}"/></td>
		                  			<td><c:out value="${downloadGroupCSVHistory.positionName}"/></td>
		                  			<td>
		                  				<c:if test="${downloadGroupCSVHistory.operaterId!=0}">
		                  					<c:out value="${downloadGroupCSVHistory.operaterName}"/>
		                  				</c:if>
		                  			</td>
									
									<td>
										<c:if test="${downloadGroupCSVHistory.csvApprovalStatus==0}">
											<c:out value="未承認"/>	
										</c:if>
										<c:if test="${downloadGroupCSVHistory.csvApprovalStatus==1}">
											<c:out value="承認"/>	
										</c:if>
										<c:if test="${downloadGroupCSVHistory.csvApprovalStatus==2}">
											<c:out value="否認"/>	
										</c:if>					                            		
										<input class="hidden" name="csvApprovalStatus" value="${downloadGroupCSVHistory.csvApprovalStatus}">				                            		
		                            </td>
		                            <td>
		                            	<fmt:formatDate value="${downloadGroupCSVHistory.requestDate}" pattern="yyyy年 MM月dd日" />	
		                            </td>						
		                      	</tr>
	                      	
	                   	</c:forEach>
                   </tbody>
               </table>
                   <div style="overflow: hidden; margin: 0 auto; width: 210px;">
                   <div style="float:right;">
                       <button id="ok-01" type="button" class="btn btn-primary" style="display: inline-block; width:100px !important">承認</button>
                       <button id="ng-01" type="button" class="btn btn-primary" style="display: inline-block; width:100px !important">否認</button>
                   </div>
                 </div>
           </div>
       		</div>
   
   			<div class="row " id="data-table">
	         	<div class="ibox-content ibox-custom01">
	              <table id="second-table" class="table container paging" style="padding: 0;">
	                  <thead>
	                      <tr>
	                          <td colspan="7"  style="background-color: #fff; padding-left: 0;">グループ名刺ダウンロード申請者</td>
	                      </tr>
	                      <tr>
	                          <th></th>
	                          <th>申請者</th>
	                          <th>部署</th>
	                          <th>役職</th>
	                          <th>承認者</th>
	                          <th>承認・否認</th>
	                          <th>日付</th>
	                      </tr>
	                  </thead>
	                  <tbody style="">
	                   <c:forEach var="downloadAllCSVHistory" items="${downloadAllCSVHistory}" varStatus="loop">
                     		<form id="${downloadAllCSVHistory.csvId}" method="POST" action="/ecard-webapp/download/regconitionDownload/${downloadAllCSVHistory.csvId}" accept-charset="UFT-8">	                  		
		                  		<tr>
		                  			<td><div class="i-checks" data-check-id="1"><label class=""> <div class="icheckbox_square-green" style="position: relative;"><input type="checkbox" value="" style="position: absolute; opacity: 0;"><ins class="iCheck-helper" style="position: absolute; top: 0%; left: 0%; display: block; width: 100%; height: 100%; margin: 0px; padding: 0px; border: 0px; opacity: 0; background: rgb(255, 255, 255);"></ins></div></label></div></td>
		                  			<td><c:out value="${downloadAllCSVHistory.userName}"/></td>		                  			
		                  			<td><c:out value="${downloadAllCSVHistory.departmentName}"/></td>
		                  			<td><c:out value="${downloadAllCSVHistory.positionName}"/></td>
		                  			<td>
		                  				<c:if test="${downloadAllCSVHistory.operaterId!=0}">
		                  					<c:out value="${downloadAllCSVHistory.operaterId}"/>
		                  				</c:if>
		                  				
		                  			</td>
									
									<td>
										<c:if test="${downloadAllCSVHistory.csvApprovalStatus==0}">
											<c:out value="未承認"/>	
										</c:if>
										<c:if test="${downloadAllCSVHistory.csvApprovalStatus==1}">
											<c:out value="承認"/>	
										</c:if>
										<c:if test="${downloadAllCSVHistory.csvApprovalStatus==2}">
											<c:out value="否認"/>	
										</c:if>					                            		
										<input class="hidden" name="csvApprovalStatus" value="${downloadAllCSVHistory.csvApprovalStatus}">				                            		
		                            </td>
		                            <td>
		                            	<fmt:formatDate value="${downloadAllCSVHistory.requestDate}" pattern="yyyy年 MM月dd日" />	
		                            </td>						
		                      	</tr>
	                      	</form>
	                   	</c:forEach>
                   </tbody>
	              </table>
	                  <div style="overflow: hidden; margin: 0 auto; width: 210px;">
	                  <div style="float:right;">
	                      <button id="ok-02" type="button" class="btn btn-primary" style="display: inline-block; width:100px !important">承認</button>
	                      <button id="ng-02" type="button" class="btn btn-primary" style="display: inline-block; width:100px !important">否認</button>
	                  </div>
	                </div>
	          </div>
      		</div>
   			</div>
         </div>
        </div>
                    <!-- END  BACKUP RESTORE SCREEN -->
    </div>
		<!-- BAR BODY -->
 </div>
<!-- END BODY -->


