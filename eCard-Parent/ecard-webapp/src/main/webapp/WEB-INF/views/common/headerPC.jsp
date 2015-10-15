<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style>
.btn-lg {
	padding: 2px 16px !important;
}

.form-group {
	margin-bottom: 0 !important;
}

.clearfix {
	margin-bottom: 10px !important;
}

.navbar-right {
	margin-right: -40px !important;
}

.clearfix a.active {
	font-weight: bold !important;
}

.navbar-left {
	float: left !important;
	margin-top: 45px !important;
}

.navbar-left li {
	position: relative !important;
	margin-left: 20px !important;
}

.navbar-top-links .dropdown-menu li {
	margin-left: 0 !important;
}

.navbar-left li a {
	padding: 0 !important;
	min-height: inherit !important;
}

.navbar-left li a .label {
	line-height: 12px !important;
	padding: 2px 5px !important;
	position: absolute !important;
	right: -3px !important;
	top: -8px !important;
}

/* .navbar-static-top {
	margin-bottom: 0 !important;
	float: right !important;
	background: none !important;
	position: absolute !important;
	right: -15px !important;
	bottom: -7px !important;
} */

.searchTargetSwitcher {
	background: #fff !important;
	display: block !important;
	padding-bottom: 5px !important;
}

.list-group-item-title {
	background: -moz-linear-gradient(center top, #f4f4f4, #e6e6e6) repeat
		scroll 0 0 rgba(0, 0, 0, 0) !important;
	border: 1px solid #b1b1b1 !important;
	border-radius: 4px 4px 0 0 !important;
	box-shadow: 0 1px 1px #fff inset !important;
	font-weight: bold !important;
	padding: 14px 30px 10px !important;
	text-align: left !important;
	color: #666 !important;
	font-weight: bold !important;
	font-family: "メイリオ", Meiryo, "ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro",
		"ＭＳ Ｐゴシック", "MS PGothic", sans-serif !important;
}

.list-group-item {
	background: #fff !important;
	border: 1px solid #b1b1b1 !important;
}
</style>
 <div class="row border-bottom" style="text-align:center; background: #fff;">
        <div style="display:inline-block; width:770px; margin:0 auto; padding:0;  position: relative;">
            <a href="<c:url value="/user/home"/>"><img src="<c:url value='/assets/img/login_logo_pc.png'/>" style="float:left; width:80px; margin:5px 0;"></a>
                 
            <!-- edit 9/10 -->
          <style type="text/css">
          .abc-ex{
            width: auto ;
            text-align: right;
            display: block;
            margin-top: 15px;
          }
          .nav-menu-left{
              margin-bottom: 0 !important; 
              left: 85px !important;
              bottom: 10px !important;
              margin-top:0px !important;
              right: inherit !important;
          }
          .btn-goto-admin{
		      padding: 4px 5px;
		  }
          </style>
           <p class="abc-ex">${pageContext.request.remoteUser}</p>
		   <c:if test="${pageContext.request.isUserInRole('ROLE_LEADER') or pageContext.request.isUserInRole('ROLE_OPERATOR') or pageContext.request.isUserInRole('ROLE_SUPERVISOR') or pageContext.request.isUserInRole('ROLE_ADMIN') or pageContext.request.isUserInRole('ROLE_AUTHORITY_USER') or pageContext.request.isUserInRole('ROLE_OPERATOR_MANAGER') }">				
				 <p style=" position: absolute;right: 100px;top: 50px; width:141px"><button class="btn btn-primary btn-goto-admin" onclick="location.href='<c:url value='/manager/home'/>';">管理画面へ戻る</button></p>
		   </c:if>	
           <ul class="nav navbar-top-links navbar-right">
           		           		
                <li class="dropdown" style="margin-top:-9px; text-align:right"><a class="dropdown-toggle count-info" data-toggle="dropdown" href="#" style=" position: relative;">
                        <span style=" position: absolute;right: 3px;top: 6px; width:121px;">お知らせ
                        	<img src="<c:url value='/assets/img/icon-notice.png'/>" width="44px;" >
                        </span>
                        
                       	<c:if test="${objectNotification.numberOfNotification>=1}">
                       		<span class="label label-warning"> 
			      			${objectNotification.numberOfNotification}
			      			</span>
		      			</c:if>
			      		
                        
                    </a>
                    <ul class="dropdown-menu notification dropdown-messages " style="padding:0;">
                        <li>
                            <div class="dropdown-messages-box">
                                <span style="padding:10px 10px 0 10px;">お知らせ</span>
                                <button class="btn btn-primary" style="margin-top:2px;margin-bottom:2px; margin-left: 137px; padding-top: 2px;padding-bottom: 2px" onclick="deleteAllNotify()">一括既読化</button>
                            </div>
                        </li>
                        <li>                  
                            <table class="table table-hover" id="notification">

							<tbody>
								
								<c:forEach var="notification" items="${objectNotification.notifications}" varStatus="loop">
									<tr class="pointer">
									 <c:choose>
									  <c:when test="${notification.image!=''}">
									   	<td style="vertical-align: middle" width="30%"><img alt="image"
											style="width: 100%" src="data:image/png;base64,${notification.image}"></td>
										<td style="vertical-align: middle"  width="60%" >
										     <c:choose>
											  <c:when test="${notification.read_flg==0}">
											   	<div class="content_notice" style="font-weight: bold">${notification.contents}</div>
											  </c:when>
											  <c:otherwise>
											   	<div class="content_notice">${notification.contents}</div>
											  </c:otherwise>
											</c:choose>
											
											
											<div class="date">${notification.date}</div>
										</td>
									  </c:when>
									  <c:otherwise>
									   
										<td style="vertical-align: middle"  width="60%" colspan="2" >
										     <c:choose>
											  <c:when test="${notification.read_flg==0}">
											   	<div class="content_notice" style="font-weight: bold">${notification.contents}</div>
											  </c:when>
											  <c:otherwise>
											   	<div class="content_notice">${notification.contents}</div>
											  </c:otherwise>
											</c:choose>
											
											
											<div class="date">${notification.date}</div>
										</td>
									  </c:otherwise>
									</c:choose>
									
									 <%-- <c:if test="${notification.image!=''}">
									      <td style="vertical-align: middle" width="30%"><img alt="image"
											style="width: 100%" src="data:image/png;base64,${notification.image}"></td>
								      </c:if>
									
									<td style="vertical-align: middle"  width="60%" >
									
									     <c:choose>
										  <c:when test="${notification.read_flg==0}">
										   	<div class="content_notice" style="font-weight: bold">${notification.contents}</div>
										  </c:when>
										  <c:otherwise>
										   	<div class="content_notice">${notification.contents}</div>
										  </c:otherwise>
										</c:choose>
										
										
										<div class="date">${notification.date}</div>
									</td> --%>
									<c:choose>
									  <c:when test="${notification.image!=''}">
									   <td style="vertical-align: middle"  width="10%"><a href="notificationDetail/${notification.id}" class=""><i
											class="fa fa-angle-right"></i></a></td>
									  </c:when>
									  <c:otherwise>
									   <td style="vertical-align: middle"  width="10%"><a class="" onclick="${notification.id}"><i
											class="fa fa-angle-right"></i></a></td>
									  </c:otherwise>
									</c:choose>
									</tr>
								</c:forEach>
							</tbody>
						</table> <!-- </div> -->
                        <!-- </div> -->
                      </li>
                    </ul>
                </li>
           		
           </ul>
            <nav class="navbar navbar-static-top nav-menu-left" role="navigation">   
                <ul class="nav navbar-top-links">
                     <li style="position: relative; text-align:left">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="personal_cards.html" style="position: relative;padding:0;min-height:auto; font-size:12px;">
                          <span style="position: absolute;width: 110px;display: inline-block;left: 0;">
                            <img src="<c:url value='/assets/img/icon-menu.png'/>" width="42px;">メニュー
                          </span>
                        </a>
                        <ul class="dropdown-menu animated fadeInLeft m-t-xs" style=" margin-top: 47px; margin-left: 11px;">
                          <li><a href="<c:url value='/user/profile' />">プロフィール</a></li>
                          <li class="divider"></li>
                          <li><a href="<c:url value='/user/addBusinessCard' />">名刺データ作成</a></li>
                          <li><a href="<c:url value='/user/companyTree' />">顧客組織一覧</a></li>
                          <li><a href="<c:url value='/user/overlapcards' />">名刺の最新化（名寄せ）</a></li>
                          <li><a href="<c:url value='/user/download' />">自分の名刺ダウンロード</a></li>                          
                          <li class="divider"></li>
                          <li><a href="<c:url value='/user/faq' />">FAQ</a></li>                          
                          <li><a href="<c:url value='/user/contact' />">ご意見・不具合の連絡</a></li>
                          <li><a href="<c:url value='/user/changepass' />">パスワード設定</a></li>                          
<%--                           <c:if test="${pageContext.request.isUserInRole('ROLE_LEADER') or pageContext.request.isUserInRole('ROLE_OPERATOR') or pageContext.request.isUserInRole('ROLE_SUPERVISOR') or pageContext.request.isUserInRole('ROLE_ADMIN') or pageContext.request.isUserInRole('ROLE_AUTHORITY_USER') or pageContext.request.isUserInRole('ROLE_OPERATOR_MANAGER') }">
						    <li><a href="<c:url value='/manager/home'/>">管理ページ</a></li>
					      </c:if>
 --%>                         <!-- Profile -->
                          <li class="divider"></li>
                          <li><a href="<c:url value='/j_spring_security_logout'/>">ログアウト</a></li>
                        </ul>
                      </li>
                </ul>
           </nav>
           <!-- end edit 9/10 -->
          </div>
        </div>
<script>
function deleteAllNotify(){
	$.ajax({
	    type: 'POST',
	    url: 'deleteAllNotify',
	    data: { 
	        'id':$(this).find("a").attr("onclick")
	    },
	    success: function(msg){
	    	location.reload();
	    }
	});
}

$(document).ready(function() {
    $('#notification tr').click(function() {
        var href = $(this).find("a").attr("href");
        if(href) {
            window.location = href;
        }else{
        	$.ajax({
        	    type: 'POST',
        	    url: 'notificationDetailRibbon',
        	    data: { 
        	        'id':$(this).find("a").attr("onclick")
        	    },
        	    success: function(msg){
        	        if(msg==true){
        	        	window.location = "https://bc-ribbon.temp-holdings.co.jp/";
        	        }
        	    }
        	});
        }
    });
    
    

});
</script>
