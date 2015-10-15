<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<style type="text/css">
 .ibox-content-company .dataTables_wrapper {
 	width: 100% !important;
    margin-left: 0% !important;
 }
 .ibox-content-question .dataTables_wrapper {
 	width: 100% !important;
    margin-left: 0% !important;
 }
 
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
	bottom: -13px;
}

.a-new-pc {
	float: right;
	border: 2px solid #000;
	text-align: center;
	padding: 5px 10px;
	color: #000;
}

.btn_back img {
	width: 12px;
	margin-right: 6px;
}

/**/
.div-list {
	display: inline-block;
	margin: 30px auto 0 auto;
	text-align: center;
	width: 100%;
}

.list-profile {
	margin: 0;
	padding: 0;
	width: auto;
	display: block;
}

.list-profile li {
	border-bottom: 1px solid #b1b1b1;
	width: auto;
	display: block;
	padding-bottom: 5px;
	margin-bottom: 5px;
	text-align: left;
	list-style: none;
	padding: 0 60px 10px 60px;
	margin-bottom: 10px;
}

.list-profile li p {
	width: 100%;
	display: inline-block;
	margin: 0;
	padding: 0;
}

.list-profile li .p-1 {
	color: #555;
	font-size: 13px;
}

.list-profile li .p-2 {
	color: #000;
	font-size: 15px;
}

.box-1 {
	display: block;
	height: auto;
	margin: 0 auto;
	text-align: left;
	width: 800px;
}

.title-box-1 {
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
	font-family: "ã¡ã¤ãªãª", Meiryo, "ãã©ã®ãè§ã´ Pro W3",
		"Hiragino Kaku Gothic Pro", "ï¼­ï¼³ ï¼°ã´ã·ãã¯", "MS PGothic",
		sans-serif !important;
}

.content-box-1 {
	background-color: #fff;
	border-bottom: 1px solid #b1b1b1;
	border-left: 1px solid #b1b1b1;
	border-radius: 0 0 4px 4px;
	border-right: 1px solid #b1b1b1;
	padding: 15px 0 0 0;
	text-align: center;
}

.label-c {
	color: #666;
	width: 150px;
}

.input-c {
	width: 524px;
	padding: 10px;
	color: #666;
}

.form {
	width: 490px;
	display: inline-block;
	text-align: center;
}

.textarea {
	display: block;
	border: 1px solid #b1b1b1;
	border-radius: 4px;
	-webkit-border-radius: 4px;
	-moz-border-radius: 4px;
	padding: 10px 9px 8px 9px;
	background-color: #f2f2f2;
	box-shadow: 0 3px 4px #ccc inset;
	width: 100%;
	height: 300px;
	color: #666666;
	font-weight: bold;
	font-size: 14px;
}

.submit {
	cursor: pointer;
	font-size: 1.4em;
	margin: 20px 0;
	padding: 10px 12px 8px 12px;
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
	font-size: 14px;
	width: 100%;
	display: block;
}

.for-add-bu {
	display: inline-block;
	width: 93%;
	margin: 0;
	padding: 0;
}

.for-add-bu fieldset {
	display: inline-block;
	width: 100%;
	margin: 3px 0;
}

.for-add-bu fieldset label {
	font-size: 14px;
	color: #666;
	font-weight: bold;
	text-align: left;
	float: left;
	width: 150px;
}

.for-add-bu fieldset label span {
	color: #ff0000;
}

.l-a-b {
	line-height: 32px;
}

.input-a-b {
	float: left;
	width: 300px;
	height: 35px;
	text-indent: 10px;
	border: 1px solid #e5e6e7;
}

.text-a-b-d {
	float: left;
	width: 300px;
	text-align: left;
}

.text-a-b {
	float: left;
	width: 300px;
	height: 100px;
	text-indent: 10px;
	border: 1px solid #e5e6e7;
	margin-bottom: 3px;
}
.label-c {
	color: #666;
	width: 150px;
}

.input-c {
	width: 524px;
	padding: 10px;
	color: #666;
}
/*edit 9/10*/
.category_title {
	text-align: left;
	font-weight: bold;
	font-size: 15px;
	line-height: 40px;
	padding: 0 10px 0 20px;
	margin-bottom: 15px;
	background: url(img/faq2.png) no-repeat 10px center #f0eee4;
}

.category_title.active {
	background: url(img/faq1.png) no-repeat 10px center #f0eee4;
}
/*end edit 9/10*/
.dl {
	display: block;
	width: auto;
	text-align: left;
	margin: 0 10px;
	font-family: "ã¡ã¤ãªãª", Meiryo, "ãã©ã®ãè§ã´ Pro W3",
		"Hiragino Kaku Gothic Pro", "ï¼­ï¼³ ï¼°ã´ã·ãã¯", "MS PGothic",
		sans-serif !important;
}

.dl dt {
	float: left;
	width: 25px
}

.dl dt img {
	width: 25px;
}

.dl dd {
	margin-left: 35px;
	font-weight: bold;
	color: #555;
	font-size: 1em;
	display: block;
	width: auto;
	margin-bottom: 10px;
}

.dl dd.dd-maB {
	margin-bottom: 20px;
	font-weight: normal;
}

td {
	text-align: left;
}

.ch-color-link{
    text-align: center;
}
</style>
<script type="text/javascript">
var dataTables;
function loadDataComplete() {
	$('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',                
   		});
	
	
}

function setConnectCard(cardId,email,name,companyName,addressFull,departmentName,positionName,telNumberCompany,owner,contactDate){
	var modal=	'<tr id='+cardId+'>'
	+'<td style="text-align: center;"><div class="iradio_square-green_combo"><input type="radio" class="i-checks" name="bla1" value='+cardId+'></div></td>'
	+'<td>'+email+'</td>'
	+'<td>'+name+'</td>'
	+'<td>'+companyName+'</td>'
	+'<td>'+addressFull+'</td>'
	+'<td>'+departmentName+'</td>'
	+'<td>'+positionName+'</td>'
	+'<td>'+telNumberCompany+'</td>'
	+'<td style="text-align:right">'+contactDate+'</td>'
	+'<td>'+owner+'</td>'
	+'</tr>'
	return modal;
}

function setNewConnectCard(cardId,email,name,companyName,addressFull,departmentName,positionName,telNumberCompany,owner,contactDate){
	var modal=	'<tr id='+cardId+'>'
	+'<td style="text-align: center;"><div class="iradio_square-green_combo"><input type="radio" class="i-checks" name="bla1" value='+cardId+'></div></td>'
	+'<td>'+name+'<br><br>'+companyName+'<br>'+email+'</td>'
	+'<td>'+addressFull+'<br><br>'+telNumberCompany+'</td>'
	+'<td>'+positionName+'<br><br>'+departmentName+'</td>'
	+'<td>'+contactDate+'<br><br>'+owner+'</td>'
	+'</tr>'
	return modal;
}

$(document).ready(function() {
	$(document).on('ifClicked', '#tbl-cards .iradio_square-green_combo .iradio_square-green', function(event){
		var cardId=$(this).attr("id");
		var email=$(this).closest( "tr" ).find(".email").val();
		var firstName=$(this).closest( "tr" ).find(".name").val();
		var companyName=$(this).closest( "tr" ).find(".companyName").val();
		var addressFull=$(this).closest( "tr" ).find(".addressFull").val();
		var departmentName=$(this).closest( "tr" ).find(".departmentName").val();
		var positionName=$(this).closest( "tr" ).find(".positionName").val();
		var telNumberCompany=$(this).closest( "tr" ).find(".telNumberCompany").val();
		var groupCompanyId=$(this).closest( "tr" ).find(".groupCompanyId").val(); 
		$.ajax({
			    type: 'POST',
			    url: 'listConnectCards',
			    dataType: 'json', 
				 contentType: 'application/json',
				 mimeType: 'application/json',
			    data: JSON.stringify({ 
			        'email':email,
			        'companyName':companyName,
			        'addressFull':addressFull,
			        'departmentName':departmentName,
			        'positionName':positionName,
			        'telNumberCompany':telNumberCompany,
			        'groupCompanyId':groupCompanyId,
			        'firstName':firstName
			    }),
			    success: function(cards){
			    	$("#tbl-connect-cards >tbody").remove();
			    	$.each( cards, function( key, data ) {
			    		$("#tbl-connect-cards").append(setNewConnectCard(data.cardId,data.email,data.name,data.companyName,data.addressFull,data.departmentName,data.positionName,data.telNumberCompany,data.owner,data.contactDateString));	
			    	});
			    	loadDataComplete();
			    	return false;
			    }
		});
	});
	
	$(".btn.btn-primary").click(function(){
		if(!($('#tbl-cards input[name=bla]:checked').val()==undefined || $('#tbl-connect-cards input[name=bla1]:checked').val()==undefined)){
			var cardid1=$('#tbl-cards input[name=bla]:checked').val();
			var cardid2=$('#tbl-connect-cards input[name=bla1]:checked').val();
			$.ajax({
			    type: 'POST',
			    url: 'handleConnectCards',
			    data: { 
			        'cardid1':cardid1,
			        'cardid2':cardid2
			    },
			    success: function(cards){
			    	location.reload();
			    }
		});
		}
	})
	dataTables = $('#tbl-cards').dataTable( {
		"fnDrawCallback" : function() {
			loadDataComplete();
		},
		"dom" : '<<t>ip>',
		"iDisplayLength" : 10,
		"processing": true,
		"serverSide": true,
		"searching": false,
		"ordering": false,
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
		"ajax": {
			"url": "searchOverLapCards",
			"type": "POST",
			"data": function (dataTableRequest) {
				dataTableRequest.criteriaSearch = $('#criteriaSearch').val();
				dataTableRequest.status = $('#status').val();
				return dataTableRequest;
			},
			"dataSrc": "data",
			"error": function(xhr) {
				alert('error datatable')
			}
		},
		"columns": [
			{ "data": "cardId",
				"className": "ch-color-link",
				"createdCell": function (td, cellData, rowData, row, col) {
					
					$(td).html("<div class='iradio_square-green_combo' id='"+rowData.cardId+"'><input type='radio' class='i-checks' value='"+rowData.cardId+"' name='bla'></div>");
				}
			},
			/* { "data": "name",
				"createdCell": function (td, cellData, rowData, row, col) {
				       $(td).html("<input type='hidden' class='name' value='"+rowData.name+"'/>"+ rowData.name );
				}},
			{ "data": "companyName",
				"createdCell": function (td, cellData, rowData, row, col) {
				       $(td).html("<input type='hidden' class='companyName' value='"+rowData.companyName+"'/>"+ rowData.companyName );
				}},
			{"data": "email",
				"createdCell": function (td, cellData, rowData, row, col) {
				       $(td).html("<input type='hidden' class='groupCompanyId' value='"+rowData.groupCompanyId+"'/>"+"<input type='hidden' class='email' groupCompanyId ='"+rowData.groupCompanyId+"' value='"+rowData.email+"'/>"+ rowData.email );
				       
				}}, */
			{ "data": "name",
				"createdCell": function (td, cellData, rowData, row, col) {
						var name= "<input type='hidden' class='name' value='"+rowData.name+"'/>"+ rowData.name +'<br><br>';
						name+="<input type='hidden' class='companyName' value='"+rowData.companyName+"'/>"+ rowData.companyName+'<br>';
						name+="<input type='hidden' class='groupCompanyId' value='"+rowData.groupCompanyId+"'/>"+"<input type='hidden' class='email' groupCompanyId ='"+rowData.groupCompanyId+"' value='"+rowData.email+"'/>"+ rowData.email;
				       	$(td).html(name);
				}},
				
				
			/* { "data": "addressFull",
				"createdCell": function (td, cellData, rowData, row, col) {
				       $(td).html("<input type='hidden' class='addressFull' value='"+rowData.addressFull+"'/>"+ rowData.addressFull );
				}},
			{ "data": "departmentName",
				"createdCell": function (td, cellData, rowData, row, col) {
				       $(td).html("<input type='hidden' class='departmentName' value='"+rowData.departmentName+"'/>"+ rowData.departmentName );
							}}, */
			{ "data": "addressFull",
				"createdCell": function (td, cellData, rowData, row, col) {
						var address= "<input type='hidden' class='addressFull' value='"+rowData.addressFull+"'/>"+ rowData.addressFull +'<br><br>';
						address+="<input type='hidden' class='telNumberCompany' value='"+rowData.telNumberCompany+"'/>"+ rowData.telNumberCompany;
				      	$(td).html(address);
				}},
				
			/* { "data": "positionName",
				"createdCell": function (td, cellData, rowData, row, col) {
				       $(td).html("<input type='hidden' class='positionName' value='"+rowData.positionName+"'/>"+ rowData.positionName );
								}},
			{ "data": "telNumberCompany",
				"createdCell": function (td, cellData, rowData, row, col) {
				       $(td).html("<input type='hidden' class='telNumberCompany' value='"+rowData.telNumberCompany+"'/>"+ rowData.telNumberCompany );
			}}, */
			
			{ "data": "positionName",
				"createdCell": function (td, cellData, rowData, row, col) {
						var position = "<input type='hidden' class='departmentName' value='"+rowData.departmentName+"'/>"+ rowData.departmentName +'<br><br>';
						position += "<input type='hidden' class='positionName' value='"+rowData.positionName+"'/>"+ rowData.positionName;
		       			$(td).html(position);
			}},
				
			{ "data": "contactDateString"},
		],
		"columnDefs": [
		               { "width": "1px", "targets": 0 },
		               { "width": "330px", "targets": 1 },
		               { "width": "350px", "targets": 2 },
		               { "width": "135px", "targets": 3 }
		             ],
	});
	
	$("#btnSearch").click(function(){
		var dataTableSearch = $('#tbl-cards').dataTable();
		dataTableSearch.fnFilter();
	})
	
});

</script>

<div class="div-list">
	<div class="box-1" style="width: 1200px;">
		<div class="title-box-1">名刺の最新化（名寄せ）</div>
		<div class="content-box-1" style="padding: 0">

			<div class="row bg-white box-shadow  padding-top-bottom"
				style="margin-left: 0; margin-right: 0">

				<div class="col-sm-12 table-list-operator">
					<div class="row ">
						<div class="ibox-content  ibox-content-question ibox-custom01"
							style="padding: 0;">
							<table class="table paging" style="padding: 0;"  id="tbl-cards">
								<thead>
									<tr>
										<td colspan="2"
											style="background-color: #fff; padding-left: 31px;">最新化したい自分の名刺を検索</td>
										<td colspan="9"
											style="background-color: #fff; padding-left: 0; text-align: right;">
										
											<input value="" id="criteriaSearch" style="width: 300px; height: 30px;">
											<input value="検索" id="btnSearch"
												style="padding-left: 10px; padding-right: 10px; height: 30px;"
												type="button" class="btn-primary">
										</td>
									</tr> 
									<tr>
										<th></th>
										<%-- <th><fmt:message key='overlap.cards.table1.name' /></th>
										<th><fmt:message key='overlap.cards.table1.company' /></th>
										<th><fmt:message key='overlap.cards.table1.email' /></th> 
										<th><fmt:message key='overlap.cards.table1.address' /></th>
										<th><fmt:message key='overlap.cards.table1.department' /></th>
										<th><fmt:message key='overlap.cards.table1.position' /></th>
										<th><fmt:message key='overlap.cards.table1.TEL' /></th>--%>
										
										<th><fmt:message key='overlap.cards.table1.name.company' /> <br><fmt:message key='overlap.cards.table1.email.company' /></th>
										<th><fmt:message key='overlap.cards.table1.address' /> <br><fmt:message key='overlap.cards.table1.TEL' /></th>
										<th><fmt:message key='overlap.cards.table1.department' /> <br><fmt:message key='overlap.cards.table1.position' /></th>
										
										
										
										<th><fmt:message key='overlap.cards.table1.exchanges' /></th>
									</tr>
								</thead>
							</table>

						</div>
					</div>
				</div>
				<!-- DATA TABLE -->
				<div class="col-sm-12 table-list-operator">
					<div class="row">
						<div class="ibox-content  ibox-content-question ibox-custom01"
							style="padding: 0;">
							<table id="tbl-connect-cards" class="table paging"
								style="padding: 0; position: relative; z-index: 9">
								<thead>

									<tr>
										<th style="width: 1px; text-align: center"></th>
										<%-- <th><fmt:message key='overlap.cards.table1.email' /></th>
										<th><fmt:message key='overlap.cards.table1.name' /></th>
										<th><fmt:message key='overlap.cards.table1.company' /></th>
										<th><fmt:message key='overlap.cards.table1.address' /></th>
										<th><fmt:message key='overlap.cards.table1.department' /></th>
										<th><fmt:message key='overlap.cards.table1.position' /></th>
										<th><fmt:message key='overlap.cards.table1.TEL' /></th>
										<th><fmt:message key='overlap.cards.table1.exchanges' /></th>
										<th><fmt:message key='overlap.cards.table1.owner' /></th> --%>
										
										<th style="width: 378px"><fmt:message key='overlap.cards.table1.name.email.company' /></th>
										<th style="width: 380px"><fmt:message key='overlap.cards.table1.address' /> <br><fmt:message key='overlap.cards.table1.TEL' /></th>
										<th style="width: 173px"><fmt:message key='overlap.cards.table1.department' /> <br><fmt:message key='overlap.cards.table1.position' /></th>
										<th><fmt:message key='overlap.cards.table1.exchanges' /><br>名刺所有者</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-primary"
						style="width: 345px !important">自分の名刺を選択した名刺の情報で最新化する</button>
				</div>
			</div>
		</div>
	</div>
</div>
