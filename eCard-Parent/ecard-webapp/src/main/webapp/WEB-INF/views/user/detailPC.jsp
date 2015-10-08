<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style type="text/css">
.btn-lg {
	padding: 2px 16px;
}

.form-group {
	margin-bottom: 0;
}

.clearfix {
	margin-bottom: 4px;
}

.navbar-right {
	margin-right: -40px;
}

.clearfix a.active {
	font-weight: bold;
}

.navbar-left {
	float: left;
	margin-top: 45px;
}

.navbar-left li {
	position: relative;
	margin-left: 20px;
}

.navbar-top-links .dropdown-menu li {
	margin-left: 0;
}

.navbar-left li a {
	padding: 0;
	min-height: inherit;
}

.navbar-left li a .label {
	line-height: 12px;
	padding: 2px 5px;
	position: absolute;
	right: -3px;
	top: -8px;
}

.navbar-static-top {
	margin-bottom: 0;
	float: right;
	background: none;
	position: absolute;
	right: -15px;
	bottom: -7px;
}

.searchTargetSwitcher {
	background: #fff;
	display: block;
	padding-bottom: 5px;
}

.list-group-item-title {
	background: -moz-linear-gradient(center top, #f4f4f4, #e6e6e6) repeat
		scroll 0 0 rgba(0, 0, 0, 0);
	border: 1px solid #b1b1b1;
	border-radius: 4px 4px 0 0;
	box-shadow: 0 1px 1px #fff inset;
	font-weight: bold;
	padding: 14px 30px 10px;
	text-align: left;
	color: #666;
	font-weight: bold;
	font-family: "メイリオ", Meiryo, "ヒラギノ角ゴ Pro W3", "Hiragino Kaku Gothic Pro",
		"ＭＳ Ｐゴシック", "MS PGothic", sans-serif !important;
}

.list-group-item {
	background: #fff;
	border: 1px solid #b1b1b1;
}

.row-new {
	display: table;
	padding: 12px 0 10px 0;
	margin: 0;
	float: none;
	width: 100%;
}

.col-md-1 {
	display: table-cell;
	vertical-align: middle;
	width: 50px;
	float: none;
}

.col-md-5 {
	display: table-cell;
	vertical-align: middle;
	width: auto;
}

.col-md-6 {
	display: table-cell;
	vertical-align: middle;
	width: 290px;
	float: none;
}
</style>
<div id="details" class="container">
	<!-- Start banner -->
	<div class="row animated fadeInRight">
		<div class="rel" style="margin-top:0">
			<div class="bg">
				<img src="<c:url value='/assets/img/bg-1.jpg'/>">
			</div>
			<div>
				<a href="<c:url value="/user/home"/>" class="btn_back"><span><img
						src="<c:url value='/assets/img/mt.png'/>">戻る</span></a>
			</div>
			<div class="abs">
				<div class="name1">
					<dt style="font-size: 20px;">
						<b><c:out value="${cardInfo.cardOwnerName}" /></b>
					</dt>
					<dd style="font-size: 15px;">
						<c:out value="${cardInfo.companyName}" />
					</dd>

				</div>
				<!-- <ul class="icon">
                   <li class="ic_ml"><span><span class="none">Eight Link</span></span></li>
                   
                   </ul> -->
				<div class="card">
					<div class="card_img">
						<a href="#" title="Image from Unsplash" data-target="#myModal"
							id="popup"> <img id="imageresource" width="318" height="190"
							src="data:image/png;base64,${cardInfo.imageFile}"></a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- End banner -->

	<div class="row" style="margin-top: 25px">
		<button id="addTag" class="btn btn-primary " type="button">
			<i class="fa fa-tag"></i>
		</button>

		<a href="#" class="a-new-pc">セールスフォース連携</a>


		<div class="balloon lbl_balloon" style="display: none; margin-top: 10px">
			<div class="">
				<div class="col-sm-12" style="border-bottom: solid 1px #c1c1c1;">
					
						<table class="table" id="paging">
							<col width="10%">
							<col width="80%">
							<col width="10%">
							<tbody>
							<c:forEach var="cardTag" items="${cardTagList}" varStatus="loop">
								<tr id="rowData">
									<td><input type="checkbox" class="i-checks" id="${ cardTag.tagId }" value="${ cardTag.tagId }" name="tagId"></td>
									<td><c:out value="${ cardTag.tagName }"></c:out></td>
									<td><a href="javascript:void(0);" class="delTag" id="${ cardTag.tagId }"><i class="fa fa-trash"></i></a></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
					
				</div>
			</div>

			<div class="">
				<div class="input-group lbl" style="padding: 15px 15px 10px 15px; display:inline-block; width:400px">
					<form name="addTag">
						<input type="hidden" value="${ cardInfo.cardId }" name="cardId" />
						<input type="text" class="form-control" placeholder="新規タグを追加" style="margin:0; width:317px;" name="tagName" id="tagName">
						<span class="input-group-btn">
							<button id="addLabel" type="button" class="btn btn-success">作成</button>
						</span>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row" style="margin-top: 20px">
		<!-- Left side -->
		<div class="col-xs-5">
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-heading" style="height: 40px;">
						<div style="float: left">
							<h5>名刺交換日</h5>
						</div>

						<div class="div-pen" style="float: right">
							<a> <i class="fa fa-pencil"></i>
							</a>
						</div>
					</div>
					<style type="text/css">
.div-pen-ac {
	background: #dfdfdf;
	padding: 1px 8px;
}

.p-date {
	border: none;
	background: none;
	margin-left: 15px;
	padding-bottom: 8px;
}

.p-date-input {
	display: block;
	border: 1px solid #b1b1b1;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	padding: 10px 9px 8px 9px;
	background-color: #f2f2f2;
	box-shadow: 0 3px 4px #ccc inset;
	width: 300px;
	color: #666666;
	font-weight: bold;
	font-size: 14px;
	margin: 0 15px;
}

.p-fomr {
	display: inline-block;
	width: 100%;
	border-top: 1px solid #b1b1b1;
	padding: 12px 17px 10px 17px;
	margin: 10px 0 0 0;
	text-align: center;
	background: #e2f3ff;
}

.p-fomr2 {
	display: inline-block;
	width: 100%;
	border-top: 1px solid #b1b1b1;
	padding: 12px 17px 10px 17px;
	margin: 10px 0 0 0;
	text-align: center;
	background: #e2f3ff;
}

.input-submit {
	font-size: 1.2em;
	display: inline-block;
	padding: 5px 12px 2px 12px;
	border: 1px solid #12476a;
	border-radius: 3px;
	background: linear-gradient(#307cae, #27648d);
	background: -webkit-gradient(linear, left top, left bottom, from(#307cae),
		to(#27648d));
	background: -moz-linear-gradient(top, #307cae, #27648d);
	background-color: #307caf;
	color: #ffffff !important;
	vertical-align: middle;
	text-shadow: 0 -1px 2px #000;
	box-shadow: 1px 1px 0 #649dc2 inset;
	white-space: nowrap;
	-ms-filter:
		"progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae, EndColorStr=#27648d)";
	filter: progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae,
		EndColorStr=#27648d);
}

.input-reset {
	font-size: 1.2em;
	display: inline-block;
	padding: 5px 12px 2px 12px;
	border: 1px solid #a5a5a5;
	border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	background: linear-gradient(#e0e0e0, #c8c8c8);
	background: -webkit-gradient(linear, left top, left bottom, from(#e0e0e0),
		to(#c8c8c8));
	background: -moz-linear-gradient(top, #e0e0e0, #c8c8c8);
	background-color: #e0e0e0;
	color: #666666 !important;
	vertical-align: middle;
	text-shadow: 0 1px 0px #fff;
	white-space: nowrap;
	-ms-filter:
		"progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#e0e0e0, EndColorStr=#c8c8c8)";
	filter: progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#e0e0e0,
		EndColorStr=#c8c8c8);
}
</style>
					<script type="text/javascript">
                          $(document).ready(function(){
                                  
	                              $('.div-pen').on({
	                               'click':function(){
	                                          $(".p-date").addClass("p-date-input");
	                                          $('.p-fomr').show();
	                                          $('.div-pen').addClass('div-pen-ac') 
	                                          $(".p-date").removeAttr('readonly');
	                                  }
	                              });   
	                              $('.input-reset,.input-submit').on({
	                               'click':function(){
	                                          $(".p-date").removeClass("p-date-input");
	                                          $('.p-fomr').hide();
	                                          $('.div-pen').removeClass('div-pen-ac') 
	                                          $(".p-date").attr('readonly', 'readonly');
	                                  }
	                              });     
                              
                              	$('.p-date').datepicker({
	      							language : 'en',
	      							todayHighlight : true,
	      							keyboardNavigation : false,
	      							format : 'yyyy-mm-dd',
	      							forceParse : true,
	      							autoclose : true,
	      							calendarWeeks : true
      					     	});
                              
                              	$("#editContactDate").click(function(){
                              		$.ajax({
										  type: "POST",
										  url: "<c:url value='/user/editContactDate' />",
										  data: 'contactDate='+ $("input[name=contactDate]").val() +'&cardId='+$("input[name=cardId]").val(),
										  success: function(){
											  BootstrapDialog.show({
					                				title: 'Information',
					               	             	message: 'Edit contact date success'
					                	      });
										  },
										  error: function(){
											  BootstrapDialog.show({
					                				title: 'Information',
					               	             	message: 'Edit contact date failed'
					                	      });
										  }
									});
                              	});
                          });   
                          
                      </script>
					<div class="panel-body" style="padding: 12px 0 0 0;">
						<form name="frmEditContactDate" id="frmEditContactDate">
							<input type="hidden" value="${ cardInfo.cardId }" name="cardId" />
							<input value="<fmt:formatDate value='${ cardInfo.contactDate }' pattern="yyyy-MM-dd"/>" class="p-date" name="contactDate">
							<p class="p-fomr" style="display: none">
								<input type="button" class="input-submit" value="保存" id="editContactDate"> 
								<input type="reset" class="input-reset" value="キャンセル">
							</p>
						</form>
					</div>
					<!-- <div class="panel-footer" style="height:50px">
                       <button class="btn btn-sm btn-primary" type="submit">Save</button>
                       </div> -->
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5>メモ</h5>
					</div>
					<style type="text/css">
.ul-memo {
	width: 100%;
	display: inline-block;
	margin: 0;
	padding: 0;
}

.ul-memo li {
	display: block;
	width: auto;
	position: relative;
	padding: 4px 10px;
	border-top: 1px dotted #b1b1b1;
	margin-top: -1px;
}

.ul-memo li p {
	color: #666;
	display: inline-block;
	width: 90%;
	margin: 0;
}

.ul-memo li p.p-date-n {
	font-size: 0.9em;
	color: #b1b1b1;
}

.ul-memo li span {
	position: absolute;
	top: 15px;
	right: 7px;
	color: #666;
	width: 17px;
	height: 22px;
	font-size: 14px;
	text-align: center;
	cursor: pointer;
}

.panel-body-memo {
	border-bottom: 1px solid #ddd;
}
</style>
					<script type="text/javascript">
                          $(document).ready(function(){
                              $('.click-memo').click(function(){
                                
                                     var memo = $('#textarea-memo').val();
                                     if(memo == ""){
                                          alert("保存するメモがありません");
                                     }
                                     else{
                                          
                                          $('.ul-memo').show();
                                          $('.panel-body').addClass('panel-body-memo');
                                          $(".ul-memo").append("<li><p style='font-size:1.4em;'>"+ memo +"</p><p class='p-date-n'>2015/10/06</p><span id='aaa' class='span-close'>x</span></li>");
                                     }
                              });    
                             
                           });     
                      </script>
					<div class="panel-body">

						<textarea id="textarea-memo" class="form-control custom-control"
							rows="3" style="resize: vertical; margin-bottom: 5px;"
							placeholder="出会いの記録をメモしておきましょう"></textarea>

						<button class="btn btn-sm btn-primary pull-right click-memo">保存</button>

					</div>

					<ul class="ul-memo" style="display: none">

					</ul>

				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<h5>刺交換日</h5>
					</div>
					<div class="panel-body">
						<div class="career_section selected">
							<div class="career_date " style="font-weight: bold !important;">
								2014/10/26<span>(最新)</span>
							</div>
							<div>
								<table class="table">
									<tbody>
										<tr id="rowData">
											<td>
												<p>株式会社コアネット</p>
												<p></p>
												<p>代表取締役</p>
											</td>

										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="career_section">
							<div class="career_date " style="font-weight: bold !important;">2014/10/25</div>
							<div>
								<table class="table">
									<tbody>
										<tr id="rowData">
											<td>
												<p>株式会社コアネット</p>
												<p></p>
												<p>代表取締役</p>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div class="career_section">
							<div class="career_date " style="font-weight: bold !important;">2014/10/24</div>
							<div>
								<table class="table">
									<tbody>
										<tr id="rowData">
											<td>
												<p>株式会社コアネット</p>
												<p></p>
												<p>代表取締役</p>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
			<button id="delBusinessCard" type="button"
				class="btn btn-block btn-outline btn-primary">
				<i class="fa fa-trash"></i> この名刺を削除する
			</button>
		</div>

		<!-- End Left side -->

		<!-- Right side -->
		<div class="col-xs-7">
			<div class="panel panel-default">
				<div class="panel-heading" style="height: 40px;">
					<div class="col-md-12">
						<div style="float: left">
							<h5>名刺情報</h5>
						</div>

						<div class="edit2" style="float: right">
							<a id="editPersonalInfo"> <i class="fa fa-pencil"></i>
							</a>
						</div>
					</div>
				</div>
				<style type="text/css">
.input-new-1 {
	border: none;
	background: none;
}

.input-new-1-ac {
	display: block;
	border: 1px solid #b1b1b1;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	padding: 10px 9px 8px 9px;
	background-color: #f2f2f2;
	box-shadow: 0 3px 4px #ccc inset;
	width: 300px;
	color: #666666;
	font-weight: bold;
	font-size: 14px;
	margin: 0 15px;
}
</style>
				<script type="text/javascript">
                          $(document).ready(function(){
                                  $('.edit2').on({
                                   'click':function(){
                                              $(".input-new-1").addClass("input-new-1-ac");
                                              $('.p-fomr2').show();
                                              $('.div-pen').addClass('div-pen-ac') 
                                              $(".input-new-1").removeAttr('readonly');
                                      }
                                  });   
                                  $('.input-reset,.input-submit').on({
                                   'click':function(){
                                              $(".input-new-1").removeClass("input-new-1-ac");
                                              $('.p-fomr2').hide();
                                              $('.div-pen').removeClass('div-pen-ac') 
                                              $(".input-new-1").attr('readonly', 'readonly');
                                      }
                                  });     
                              
                          });        
                      </script>
                
				<div class="panel-body" style="padding: 15px 15px 0 15px;">
					<form action="<c:url value='/user/editCardInfo' />" method="post">
						<input type="hidden" name="cardId" value="${ cardInfo.cardId }" />
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_busho.png'/>"
										alt="${cardInfo.companyName}">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.companyName}" name="companyName"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.companyNameKana}" name="companyNameKana">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_com.png'/>"
										alt="${cardInfo.departmentName}">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.departmentName}" name="departmentName"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.positionName}" name="positionName">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_info.png'/>"
										alt="${cardInfo.lastNameKana}">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.lastName}" name="lastName"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.firstName}" name="firstName"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.lastNameKana}" name="lastNameKana"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.firstNameKana}" name="firstNameKana">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_mail.png'/>" alt="氏名">
								</dt>
								<dd>

									<div class="ipt_txt front_email email-hide"
										style="display: none">
										<a href="mailto:${cardInfo.email}" target="_blank">${cardInfo.email}</a>
									</div>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.email}" name="email">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_tel_com.png'/>"
										alt="会社電話">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.telNumberDirect}" name="telNumberDirect"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.telNumberDepartment}" name="telNumberDepartment"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.telNumberCompany}" name="telNumberCompany"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.mobileNumber}" name="mobileNumber">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_fax.png'/>" alt="会社FAX">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.faxNumber}" name="faxNumber">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_address.png'/>" alt="会社FAX">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.zipCode}" name="zipCode">
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_address.png'/>"
										alt="${cardInfo.address1}">
								</dt>
								<dd>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address1}" name="address1"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address2}" name="address2"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address3}" name="address3"> <br/>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.address4}" name="address4"> <br/>
								</dd>
							</dl>
						</div>
						<div class="section">
							<dl>
								<dt>
									<img src="<c:url value='/assets/img/ico_url.png'/>"
										alt="ホームページ">
								</dt>
								<dd>
									<div class="ipt_txt front_url1 email-hide"
										style="display: none">
										<a href="${cardInfo.companyUrl}" target="_blank">${cardInfo.companyUrl}</a>
									</div>
									<input class="ipt_txt front_full_name input-new-1"
										value="${cardInfo.companyUrl}" name="companyUrl">
								</dd>
							</dl>
						</div>
						
						<p class="p-fomr2"
							style="border: none; margin: 0 0 0 -15px; width: 107.4%; display: none">
							<input type="submit" class="input-submit" value="保存"> 
							<input type="reset" class="input-reset" value="キャンセル">
						</p>
					</form>
				</div>

			</div>
			<!-- List card connected -->
			<c:if test="${listCardConnected.size > 0}">
			<div class="panel panel-default">
				<div class="panel-heading" style="height: 40px;">
					<div style="float: left">
						<h5>この名刺で繋がっている人</h5>
					</div>
				</div>
				
				<div class="panel-body"
					style="padding: 15px 0; overflow: auto; height: 184px;">
					<c:forEach var="listCardConnected" items="${listCardConnect}" varStatus="loop">
						<div class="list-group-item pointer div-new">
							<div class="row" style="margin-right: 0">
								<div class="col-md-5 col-md-5-n">
									<div class="col-xs-11 mg-top">
										<p class="name"><c:out value="${listCardConnected.name}"></c:out> </p>
										<p class="livepass"><c:out value="${listCardConnected.companyName}"></c:out></p>
										<p class="department_and_position">
											<c:out value="${listCardConnected.departmentName}"></c:out> 
											<c:out value="${listCardConnected.positionName}"></c:out>
										</p>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
			</c:if>
			<!-- List card connected -->
		</div>
		<!-- End Right side -->
	</div>
</div>

<script>
     var id_manager = 1;
     $(document).ready(function(){
    	 
    	 //Delete card bussiness
    	 $("#delBusinessCard").click(function(){
    		 $.ajax({
	   			  type: "POST",
	   			  url: "<c:url value='/user/delBusinessCard' />",
	   			  data: 'cardId='+ $("input[name=cardId]").val(),
	   			  success: function(){
	   				  window.location.href = "<c:url value='/user/home' />";
	   			  },
	   			  error: function(){
	   				  BootstrapDialog.show({
	        				title: 'Information',
	       	             	message: 'Remove card failed'
	        	      		});
	   			  }
   			});
    	 });
    	//Delete card tag
         $(".delTag").click(function(){
        	 $.ajax({
    			  type: "POST",
    			  url: "<c:url value='/user/deleteTag' />",
    			  data: 'tagId='+ this.id,
    			  success: function(){
    				  BootstrapDialog.show({
         				title: 'Information',
        	             	message: 'Remove tag success'
         	      		});
    				  window.location.href = "<c:url value='/user/card/details' />/"+$("input[name=cardId]").val();
    			  },
    			  error: function(){
    				  BootstrapDialog.show({
         				title: 'Information',
        	             	message: 'Remove tag failed'
         	      		});
    			  }
    		});
         });
    	
    	 var isEdit = (getUrlParameter('isEdit') != null) ? getUrlParameter('isEdit') : "";
    	 if(isEdit){
    		 BootstrapDialog.show({
	             title: 'Information',
	             message: 'Edit card success'
	        });
    	 }
    	 if(isEdit == false && getUrlParameter('isEdit') != null){
    		BootstrapDialog.show({
 				title: 'Information',
	             	message: 'Edit card failed'
 	        });
    	 }
    	 
       $('.i-checks').iCheck({
         checkboxClass: 'icheckbox_square-green',
         radioClass: 'iradio_square-green',                
       });

       $("input[name=bla]").on('ifChecked', function(event){
         $(".btn-group").find("#addTag, #deletePeople").removeClass("disabled");
       });
       $("input").on('ifUnchecked', function(event){     
         if($(".icheckbox_square-green").find('.checked').size() == 1){
           $(".btn-group").find("#addTag, #deletePeople").addClass("disabled");
           $(".addTagCard").css("display","none");  
         }          
       });

       // Process add tag and delete
       $("#deletePeople").click(function(e){
         
       });

       $("#addTag").click(function(e){
         if($(".balloon").css('display') == 'block')
           $(".balloon").css("display","none");
         else
           $(".balloon").css("display","block");
       });

       $('.makefriend').click(function(e){
         if($(this).find('button').hasClass('btn-success')){
           $(this).find('button').removeClass('btn-success');
           $(this).find('button').addClass('btn-default');
           $(this).find('button').text("取り消す");
           return false;
         } 
          
         if($(this).find('button').hasClass('btn-default')){
           $(this).find('button').removeClass('btn-default');
           $(this).find('button').addClass('btn-success');
           $(this).find('button').text("追加");
           return false;
         }

       });
       $('.mail').click(function(e) {
         console.log('Go to mailbox');
         e.stopPropagation();
       });

       // Click to personal details page
       $('.business_card_book .list-group-item').click( function() {
         console.log("Move to personal details page"); 
         window.location.href = "personal_details.html"
       }).hover(function() {
         $(this).toggleClass('hover');
       });

       // Sort name card
       $('.clearfix a').click(function(e) {
         $(this).parent().find('a').removeClass("active");
         $(this).addClass("active");

         // Process with Business Card List
         if($(".BusinessCardList").hasClass("active")) {                
           $(".business_card_book").css("display", "block");
           $(".peopleSearchResults").css("display", "none");
           $(".network_card_book").css("display", "none");                
           $(".setDisplayTerm").css("display","block");
           $(".network_card_book").css("display", "none");
           $(".manager_search_card").css("display","none");
           $(".ManagerSearch_box").css("display","none");
         }

         // Process with IBS Card List
         if($(".IBSNetworkCardList").hasClass("active")) {              
           $(".business_card_book").css("display", "none");
           $(".peopleSearchResults").css("display", "block");
           $(".network_card_book").css("display", "none");
           $(".setDisplayTerm").css("display","none");
           $(".IBSNetworkCardList_popup").css("display","none");
           $(".manager_search_card").css("display","none");
           $(".ManagerSearch_box").css("display","none");
         }

         // if($(".ManagerSearch").hasClass("active")) {
         //   // $(".business_card_book").css("display", "none");
         //   // $(".peopleSearchResults").css("display", "none");
         //   // $(".setDisplayTerm").css("display","none");
         //   // $(".manager_search_card").css("display","block");
         //   // $(".searchTargetSwitcher").css("display", "none");
         // }
       });
       // Remove tag
       
     });
     // Manager Search 
     $(".ManagerSearch").click(function(event) {        
       if($(".ManagerSearch").hasClass("active")) {
         $(".ManagerSearch_box").css("display","none");
       } else {
         $(".ManagerSearch_box").css("display","block");
       }        
     });

     $('.search_tag_index').change(function(event) {
       $('#rowData_'+id_manager).find(".nametag").html(this.value);
       if(id_manager >=5)
         id_manager = 1;
       else
         id_manager =id_manager + 1;          
       this.value = '';        
       return false;
     });
     $('.ac').click(function(event) {
       var command = $( event.target ).html();        
       if (command == '選択した検索条件を使用する') {
         // Save values and return IBSNetworkCardList
         $(".BusinessCardList").addClass("active");
         $(".searchTargetSwitcher").css("display", "block");
         $(".ManagerSearch").removeClass("active");
         $(".business_card_book").css("display", "block");
         $(".peopleSearchResults").css("display", "none");
         $(".network_card_book").css("display", "none");                
         $(".setDisplayTerm").css("display","block");
         $(".network_card_book").css("display", "none");
         $(".manager_search_card").css("display","none");
         $(".ManagerSearch_box").css("display","none");
       } 
       if (command == '選択した検索条件を削除') {
         // Clear text and disable checkbox     
         $("#mSearch").find("td .i-checks").iCheck('uncheck');
         // $(".ManagerSearch_box").css("display","none");
         // $(".BusinessCardList").addClass("active");
         // $(".ManagerSearch").removeClass("active");
         
       }
     });

     // Search function
     $('#search-card').change(function(event) {
       if($('.ManagerSearch').hasClass('active')) {
         $('#managercard_'+id_manager+' form input').val();
         $('#managercard_'+id_manager+' form input').val(this.value);
         
         if(id_manager >=5)
           id_manager = 1;
         else
           id_manager =id_manager + 1;          
         this.value = '';
       } else {
         console.log('AA',this.value);  
         this.value = '';
       }
       return false;
     });

     // Add card tag
     $('#addLabel').click(function(event) {
		if($("#tagName").val() == ""){
			BootstrapDialog.show({
				title: 'Warning',
             	message: 'Please enter tag name'
	        });
		}
		else{
			$.ajax({
				  type: "POST",
				  url: "<c:url value='/user/addTag' />",
				  data: 'tagName='+ $("input[name=tagName]").val() + '&cardId='+$("input[name=cardId]").val(),
				  success: function(){
					  BootstrapDialog.show({
          				title: 'Information',
         	             	message: 'Add tag success'
          	      		});
					  window.location.href = "<c:url value='/user/card/details' />/"+$("input[name=cardId]").val();
				  },
				  error: function(){
					  BootstrapDialog.show({
          				title: 'Information',
         	             	message: 'Add tag failed'
          	      		});
				  }
			});
		}
		
     });
     
     // Process with Notification List
     $("#notification").click(function(event) {
       // console.log($(this));
     });

	//Get params URL
	var getUrlParameter = function getUrlParameter(sParam) {
	    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
	        sURLVariables = sPageURL.split('&'),
	        sParameterName,
	        i;
	
	    for (i = 0; i < sURLVariables.length; i++) {
	        sParameterName = sURLVariables[i].split('=');
	
	        if (sParameterName[0] === sParam) {
	            return sParameterName[1] === undefined ? true : sParameterName[1];
	        }
	    }
	};
   </script>