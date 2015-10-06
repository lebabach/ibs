<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head profile="http://www.w3.org/2005/10/profile">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title><fmt:message key="webapp.title" /></title>
<link rel="icon" type="image/png"
	href="<c:url value='/assets/img/logo_icon_favion.png'/>" />
<link rel="stylesheet"
	href="<c:url value='/assets/css/bootstrap.min.css'/>" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="<c:url value='/assets/font-awesome/css/font-awesome.css'/>"
	type="text/css" media="all" />
<link rel="stylesheet" href="<c:url value='/assets/css/animate.css'/>"
	type="text/css" media="all" />
<link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>"
	type="text/css" media="all" />
<link rel="stylesheet"
	href="<c:url value='/assets/css/table_table.css'/>" type="text/css"
	media="all" />
<link rel="stylesheet"
	href="<c:url value='/assets/css/admin-style.css'/>" type="text/css"
	media="all" />
<link rel="stylesheet" href="<c:url value='/assets/css/style_ibs.css'/>"
	type="text/css" media="all" />
<script src="<c:url value='/assets/js/jquery-2.1.1.js'/>"
	type="text/javascript"></script>
<script type="text/javascript">
	    $(document).ready(function() {
	    	 $('#btnLogin').on('click', function(){
	    		 var email = $('.email-login').val();
	    		 var password = $('.password-login').val();
	    		 if (email == '' || password == ''){
	    			 $('.alert-dismissable').css("display","block");
	    			 $('.alert-dismissable').text('<fmt:message key="login.input.required"/>');
	    			 return false;
	    		 }
	    		 $('#form_login').submit();
	    	 });
	    });
    </script>

</head>
<body class="gray-bg">
	<div class="middle-box text-center loginscreen animated fadeInDown">
		<div>
			<div style="margin-left: -65px;">
				<h2 class="logo-name">
					<%-- <fmt:message key="webapp.name"/> --%>
				</h2>
			</div>
			<%-- <h3><fmt:message key="webapp.welcome"/></h3> --%>
			<!-- <p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.          
      </p> -->
			<%-- <p><fmt:message key="login.heading"/></p --%>

			<form class="m-t" role="form" id="form_login"
				action="<c:url value='/j_security_check'/>" method="post">

				<div class="alert alert-danger alert-dismissable"
					style="display:${param.error != null? '' : 'none'}">
					<c:if test="${param.error != null}">
						<fmt:message key="login.fail.message" />
					</c:if>
				</div>

				<div class="form-group">
					<input type="email" name="j_username"
						class="form-control email-login"
						placeholder="<fmt:message key='login.userName.placeholder'/>">
				</div>
				<div class="form-group">
					<input type="password" name="j_password"
						class="form-control password-login"
						placeholder="<fmt:message key='login.password.placeholder'/>">
				</div>

				<div class="form-group">
					<div class="i-checks">
						<label> <input type="checkbox"
							name="_spring_security_remember_me"><i></i> <fmt:message
								key="login.rememberMe" />
						</label>
					</div>
				</div>
				<button type="button" class="btn btn-primary block full-width m-b"
					id="btnLogin">
					<fmt:message key="login.button.login" />
				</button>

				<a href="<c:url value='/recoverpass'/>"><small><fmt:message
							key="login.forgotPassWord" /></small></a>
			</form>

		</div>

	</div>

	<!-- <div class="container-fluid" style="margin:50px">
	<div class="box-1">
	       
	          <div class="panel-body" style="text-align: center">
	            <ul class="nav navbar-top-links " >
	            <li>
	                <a href="itms-services://?action=download-manifest&url=https://dl.dropboxusercontent.com/u/3792351/Production/app.plist">IPhone Version 0.31 <i class="fa fa-download"></i></a> 
	            </li>
	            <li>
	                <a href="https://bc-ribbon.temp-holdings.co.jp/installer/BC-RIBBON_20150912_v013_PRODUCE.apk">Android Version 0.31 <i class="fa fa-download"></i></a> 
	            </li>
	                
	            </ul>
	         </div>
	           
	   </div>
</div> -->
</body>
</html>