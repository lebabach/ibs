  <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head profile="http://www.w3.org/2005/10/profile">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<c:url value='/assets/css/bootstrap.min.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/font-awesome/css/font-awesome.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/animate.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/style.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/plugins/iCheck/custom.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/admin-style.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/jquery.dataTables.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/slick-theme.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/slick.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/clockpicker.css'/>" type="text/css" media="all" />
		<link rel="stylesheet" href="<c:url value='/assets/css/plugins/datapicker/datepicker3.css'/>" type="text/css" media="all" />
		
		<script src="<c:url value='/assets/js/jquery-2.1.1.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/bootstrap.min.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/plugins/iCheck/icheck.min.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/plugins/peity/jquery.peity.min.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/demo/peity-demo.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/jquery.dataTables.min.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/slick.min.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/clockpicker.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/admin.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/bootstrap.file-input.js'/>" type="text/javascript"></script>
		<%-- <script src="<c:url value='/assets/js/jquery.bpopup.min.js'/>" type="text/javascript"></script> --%>
		<script src="<c:url value='/assets/js/jQueryRotate.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/plugins/datapicker/bootstrap-datepicker.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/plugins/datapicker/locales/bootstrap-datepicker.ja.js'/>" type="text/javascript"></script>
		<script src="<c:url value='/assets/js/zoomsl-3.0.js'/>" type="text/javascript"></script>
                
                <script src="<c:url value='/assets/js/jquery.autoKana.js'/>" type="text/javascript"></script>
                <script src="<c:url value='/assets/js/yubinbango.js'/>" type="text/javascript" charset="UTF-8"></script>
		 <link rel="icon" 
           type="image/png" 
           href="<c:url value='/assets/img/logo_icon_favion.png'/>" />
		<title><tiles:insertAttribute name="title" /></title>
	</head>
	
   
	<body>
		<div> <tiles:insertAttribute name="header" /></div>  
       <div class="container-fluid padding-top20 bg-container height100per">
          <tiles:insertAttribute name="menu" />
          <div style="position: relative;">
          		<tiles:insertAttribute name="body" />
          </div>          
       </div>
       
       <div id="loadingProgress" class="loading-progress" style="display:none">
	        <div class="progress-inner">
	            <img class="loader" src="<c:url value='/assets/img/ajax-loader.gif'/>"/>
	        </div>
	   </div>
	</body>
</html>
