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
</style>
<script type="text/javascript">
var dataTables;
function loadDataComplete() {
	$('.i-checks').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',                
   		});
	
	$(document).on('ifClicked', '.iradio_square-green_combo', function(event){
		var cardId=$(this).attr("id");
		console.log(cardId);
		$.ajax({
		    type: 'POST',
		    url: 'searchCards',
		    dataType: 'json', 
			 contentType: 'application/json',
			 mimeType: 'application/json',
		     data: JSON.stringify({ 
		        'cardId':cardId,
		    }),
		    success: function(data){
		    	setDataSearchLoadMore(data);
		    }
		});
	});
}

function clickBox() {
	alert("ok");
}

$(document).ready(function() {
	
	
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
				"createdCell": function (td, cellData, rowData, row, col) {
			       $(td).html("<input type='hidden' id='userId' value='"+rowData.cardId+"'/>"+ rowData.cardId );
			}}, 
			{"data": "email"},

			 { "data": "cardId",
				"className": "ch-color-link",
				"createdCell": function (td, cellData, rowData, row, col) {
					
					$(td).html("<div class='iradio_square-green_combo' id='"+rowData.cardId+"'><input type='radio' class='i-checks' name='bla'></div>");
				}
			},
			{ "data": "name"},
			{ "data": "companyName"},
			{ "data": "addressFull"},
			{ "data": "departmentName"},
			{ "data": "positionName"},
			{ "data": "telNumberCompany"},
			{ "data": "contactDate"},
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
		<div class="title-box-1">繋がった名刺検索</div>
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
											style="background-color: #fff; padding-left: 0;">履歴となる名刺検索</td>
										<td colspan="9"
											style="background-color: #fff; padding-left: 0; text-align: right;">
										
											<input value="" id="criteriaSearch" style="width: 300px; height: 30px;">
											<input value="検索" id="btnSearch"
												style="padding-left: 10px; padding-right: 10px; height: 30px;"
												type="button">
										</td>
									</tr> 
									<tr>
									    <th>Id</th>
										<th><fmt:message key='overlap.cards.table1.email' /></th>
										<th></th>
										<th><fmt:message key='overlap.cards.table1.name' /></th>
										<th><fmt:message key='overlap.cards.table1.company' /></th>
										<th><fmt:message key='overlap.cards.table1.address' /></th>
										<th><fmt:message key='overlap.cards.table1.department' /></th>
										<th><fmt:message key='overlap.cards.table1.position' /></th>
										<th><fmt:message key='overlap.cards.table1.TEL' /></th>
										<th><fmt:message key='overlap.cards.table1.exchanges' /></th>
									</tr>
								</thead>
							</table>

						</div>
					</div>
				</div>
				<!-- DATA TABLE -->
				<div class="col-sm-12 table-list-operator">
					<div class="row" id="tbl-connect-cards">
						<div class="ibox-content  ibox-content-question ibox-custom01"
							style="padding: 0;">
							<table id="tbl-connect-cards" class="table paging"
								style="padding: 0; position: relative; z-index: 9">
								<thead>

									<tr>
										<th><fmt:message key='overlap.cards.table1.email' /></th>
										<th></th>
										<th><fmt:message key='overlap.cards.table1.name' /></th>
										<th><fmt:message key='overlap.cards.table1.company' /></th>
										<th><fmt:message key='overlap.cards.table1.address' /></th>
										<th><fmt:message key='overlap.cards.table1.department' /></th>
										<th><fmt:message key='overlap.cards.table1.position' /></th>
										<th><fmt:message key='overlap.cards.table1.TEL' /></th>
										<th><fmt:message key='overlap.cards.table1.exchanges' /></th>
										<th><fmt:message key='overlap.cards.table1.owner' /></th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
				<div>
					<button type="submit" class="btn btn-primary"
						style="width: 150px !important">履歴として統合する</button>
				</div>
			</div>
		</div>
	</div>
</div>
