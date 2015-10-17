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
					<li>
						<div class="dropdown-messages-box">
							<a href="profile.html" class="pull-left"> <img alt="image"
								class="img-circle" src="../assets/img/a7.jpg">
							</a>
							<div class="media-body">
								<small class="pull-right">46h ago</small> <strong>Mike
									Loreipsum</strong> started following <strong>Monica Smith</strong>. <br>
								<small class="text-muted">3 days ago at 7:58 pm -
									10.06.2014</small>
							</div>
						</div>
					</li>
					<li class="divider"></li>
					<li>
						<div class="dropdown-messages-box">
							<a href="profile.html" class="pull-left"> <img alt="image"
								class="img-circle" src="../assets/img/a4.jpg">
							</a>
							<div class="media-body ">
								<small class="pull-right text-navy">5h ago</small> <strong>Chris
									Johnatan Overtunk</strong> started following <strong>Monica
									Smith</strong>. <br> <small class="text-muted">Yesterday
									1:21 pm - 11.06.2014</small>
							</div>
						</div>
					</li>
					<li class="divider"></li>
					<li>
						<div class="dropdown-messages-box">
							<a href="profile.html" class="pull-left"> <img alt="image"
								class="img-circle" src="../assets/img/profile.jpg">
							</a>
							<div class="media-body ">
								<small class="pull-right">23h ago</small> <strong>Monica
									Smith</strong> love <strong>Kim Smith</strong>. <br> <small
									class="text-muted">2 days ago at 2:30 am - 11.06.2014</small>
							</div>
						</div>
					</li>
					<li class="divider"></li>
					<li>
						<div class="text-center link-block">
							<a href="mailbox.html"> <i class="fa fa-envelope"></i> <strong>Read
									All Messages</strong>
							</a>
						</div>
					</li>
				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle count-info"
				data-toggle="dropdown" href="#" style="display: none;"> <i
					class="fa fa-bell"></i> <span class="label label-primary">8</span>
			</a>
				<ul class="dropdown-menu dropdown-alerts">
					<li><a href="mailbox.html">
							<div>
								<i class="fa fa-envelope fa-fw"></i> You have 16 messages <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="profile.html">
							<div>
								<i class="fa fa-twitter fa-fw"></i> 3 New Followers <span
									class="pull-right text-muted small">12 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li><a href="grid_options.html">
							<div>
								<i class="fa fa-upload fa-fw"></i> Server Rebooted <span
									class="pull-right text-muted small">4 minutes ago</span>
							</div>
					</a></li>
					<li class="divider"></li>
					<li>
						<div class="text-center link-block">
							<a href="notifications.html"> <strong>See All Alerts</strong>
								<i class="fa fa-angle-right"></i>
							</a>
						</div>
					</li>
				</ul></li>


			<li><a href="<c:url value='/j_spring_security_logout'/>"> <i
					class="fa fa-sign-out"></i> ログアウト
			</a></li>
		</ul>
	</nav>
</div>
</div>