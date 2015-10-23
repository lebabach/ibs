<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.lang.Integer"%>
<%@ page import="java.util.List"%>
<%@ page import="com.ecard.webapp.security.EcardUser"%>
<div class="row border-bottom">

	<nav class="navbar navbar-static-top bg-white box-shadow"
		role="navigation" style="margin-bottom: 0">
		<div class="navbar-header" style="position: relative;">
			<a class="navbar-minimalize minimalize-styl-2 btn btn-primary "
				href="<c:url value='/manager/home'/>" style="position: absolute;">
				<i class="fa fa-bars" href="<c:url value='/manager/home'/>"></i>
			</a>
			<div class="user_login"
				style="display: inline-block; margin: 20px 10px 10px 60px;"><%-- ${pageContext.request.remoteUser} --%>
				<% EcardUser ecardUser = (EcardUser)org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal(); %>
				<%= ecardUser.getFullName() %>
				</div>
			<!-- <form role="search" class="navbar-form-custom" action="search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder="Search for something..." class="form-control" name="top-search" id="top-search">
                        </div>
                    </form> -->
		</div>
		<ul class="nav navbar-top-links navbar-right">
			<li class="dropdown"><a class="dropdown-toggle count-info"
				data-toggle="dropdown" href="#" style="display: none;"> <i
					class="fa fa-envelope"></i> <span class="label label-warning">16</span>
			</a>
				<ul class="dropdown-menu dropdown-messages">
					
					
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle count-info"
				data-toggle="dropdown" href="#" style="display: none;"> <i
					class="fa fa-bell"></i> <span class="label label-primary">8</span>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					
				</ul></li>


			<li><a href="<c:url value='/j_spring_security_logout'/>"> <i
					class="fa fa-sign-out"></i> ログアウト
			</a></li>
		</ul>
	</nav>
</div>
</div>