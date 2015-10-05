<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<div class="row border-bottom">
	<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
		<div class="navbar-header" style = "position:relative;">
            <a class="navbar-minimalize minimalize-styl-2 btn btn-primary "  href="<c:url value='/user/home'/>" style = "position:absolute;">
            	<i class="fa fa-home" href="<c:url value='/user/home'/>"></i> 
            </a>
            <!-- <div class = "user_login" style = "display:inline-block;margin: 20px 10px 10px 60px; ">${pageContext.request.remoteUser}</div> -->  

        </div>
        <ul class="nav navbar-top-links navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                    <i class="fa fa-flag"></i>  <span class="label label-warning">1</span>
                </a>
                <!-- <ul class="dropdown-menu notification dropdown-messages ">
                    <li>
                        <div class="dropdown-messages-box">
                            <p>アップデート</p>
                        </div>
                    </li>                    
                    <li>                                        
                        <table class="table table-hover" id="notification">                        
                          <col width="40%">
                          <col width="50%">
                          <col width="10%">
                          <tbody>
                              <tr class="pointer" id="4575476666">
                                <td style="vertical-align: middle"><img alt="image" style="width:80%" src=""></td>                  
                                <td style="vertical-align: middle">
                                  <div class="content_notice">Mark111111111333333333333333333333333333333333333333333333</div>
                                  <div class="date">
                                    3日前
                                  </div>
                                </td>
                                <td style="vertical-align: middle"><a href="#" class=""><i class="fa fa-angle-right"></i></a></td>
                              </tr>
                              <tr class="pointer" id="5654686866">
                                <td style="vertical-align: middle"><img alt="image" style="width:80%" src=""></td>                  
                                <td style="vertical-align: middle">
                                  <div class="content_notice">2345555</div>
                                  <div class="date">
                                    3日前
                                  </div>
                                </td>
                                <td style="vertical-align: middle"><a href="#" class=""><i class="fa fa-angle-right"></i></a></td>
                              </tr>
                          </tbody>
                        </table>
                  </li>
                </ul> -->
                
                
            </li>
            <li>
              <p>${pageContext.request.remoteUser}</p>
            </li>

            <li>
                <a data-toggle="dropdown" class="dropdown-toggle" href="#">                  
                  <i class="fa fa-user"><b class="caret"></b></i>
                </a>
                <ul class="dropdown-menu animated fadeInRight m-t-xs">
                  <!-- <li><a href="properties.html">Profile</a></li> -->
                  <li><a href="<c:url value='/user/download'/>">自分の名刺をダウンロード</a></li>
                  <!--  <li><a href="contacts.html">Settings</a></li>  -->
                  <!-- <li><a href="mailbox.html">Mailbox</a></li>  -->
                  <li class="divider"></li>
                  <li><a href="<c:url value='/j_spring_security_logout'/>">ログアウト</a></li>
                </ul>
            </li>
        </ul>
    </nav>
</div> 