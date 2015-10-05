  <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
	$(document).ready(function(){
		$('a.export').click(function(e){
			
		    $('a.export').removeClass('active');
		    $('button.export').removeClass('active');
		    $(this).addClass('active');
		    var  id = $(this).attr('href');
		    
		    var groupDownload = $('input[name=groupDownload]') .val();
		    var allDownload = $('input[name=allDownload]') .val();
		    $.ajax({
				type: 'GET',
				url: '/ecard-webapp/user/'+id
			}).done(function(resp, status, xhr) {
				/* alert('111' + resp);
				if(id == '2' && groupDownload==0){
					showMsg();
				}
				
				if(id == '3' && allDownload==0){
					showMsg();
				}
				setTimeout(function(){ 				
					document.location.href='/ecard-webapp/user/download';
				}, 3000); */
			}).fail(function(xhr, status, err) {
				document.location.href='/ecard-webapp/user/download';
			});
		});
		
		$('button.export').click(function(e){
		    $('a.export').removeClass('active');
		    $('button.export').removeClass('active');
		    $(this).addClass('active');
		    var  id = $(this).attr('id');
		    console.log('BBBB = '+id);
		    $.ajax({
				type: 'GET',
				url: '/ecard-webapp/user/downloadCSV/'+id
			}).done(function(resp, status, xhr) {
				
				
				$("#titleMsg").css('display','block');
				setTimeout(function(){ 				
					document.location.href='/ecard-webapp/user/download';
				}, 3000);
					
				
			}).fail(function(xhr, status, err) {
				document.location.href='/ecard-webapp/user/download';
			});
		}); 
		
	});
	
	function showMsg(){
		$("#titleMsg").css('display','block');
		setTimeout(function(){ 				
			document.location.href='/ecard-webapp/user/download';
		}, 3000);
	}
</script>

<div id="content">
     <div id="inner">
     		<div class="box-1">
          	<div class="title-box-1">
              	名刺のダウンロード 
              </div>
              <div class="content-box-1">
                  <p class="p-box-1 p-box-1-blue">ダウンロード可能な名刺データ</p>
				  <p id="titleMsg" class="p-box-1 p-box-1-blue" style="display:none"><fmt:message key="user.waiting.request"/></p>                  	
                  <div class="box-3">
                  	  <a href="downloadCSV/1" class="export">自分の名刺をダウンロード</a>                  	  
                  	  <c:if test="${permissionType.downloadGroup == 1 && permissionType.groupDataDlRequestFlg != 1}"><button id="2" class="export">自社の名刺をダウンロード</button></c:if>                  	  
                  	  <c:if test="${permissionType.downloadGroup == 1 && permissionType.groupDataDlRequestFlg == 1}"><a href="downloadCSV/2" class="export">自社の名刺をダウンロード</a></c:if>
                  	  
                  	  <c:if test="${permissionType.downloadAll == 1  && permissionType.allDataDlRequestFlg != 1}"><button id="3" class="export">グループの名刺をダウンロード</button></c:if>                  	  
                      <c:if test="${permissionType.downloadAll == 1 && permissionType.allDataDlRequestFlg == 1}"><a href="downloadCSV/3" class="export">グループの名刺をダウンロード</a></c:if>
                      
  					  <input class="hidden" name="groupAll" value="${permissionType.allDataDlRequestFlg}">
  					  <input class="hidden" name="groupDownload" value="${permissionType.groupDataDlRequestFlg}"> 						                                             
                  </div>
                  <p class="p-line"></p>
                  <p class="p-box-3">CVSファイルのリンクからダウンロードできます（ファイルの保存期間は１ヶ月です）</p>
                  <p class="p-box-4">ダウンロードファイル一覧</p>
                  <table class="div-gird">
                  	<tr>
                          <th  class="th1">依頼日時</th>
                          <th  class="th1 th2">名刺枚数</th>
                          <th  class="th1 th3">ファイル作成日時</th>
                          <th  class="th1 th4">ダウンロード</th>
                      </tr>
                      <c:forEach var="downloadCSVHistory" items="${downloadCSVHistory}" varStatus="loop">
                  		<tr>
							<td><fmt:formatDate value="${downloadCSVHistory.requestDate}" pattern="dd-MM-yyyy" /></td>
                            <td><c:out value="${downloadCSVHistory.csvType}"/></td>
                            <td><fmt:formatDate value="${downloadCSVHistory.approvalDate}" pattern="dd-MM-yyyy" /></td>
                            <td><a href="downloadFileCSV/${downloadCSVHistory.csvUrl}"><c:out value="${downloadCSVHistory.csvUrl}" /></a></td>						
                      	</tr>
                      </c:forEach>	
                  </table>
                 
                  <ul class="list-icon">
                  	<li>文字化けする場合（UTF-8版とは？）</li>
                      <li>ダウンロードすると名刺データがブラウザで開く場合</li>
                  </ul>
              </div>    
          </div>
     </div>
</div>





