
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
            
<style type="text/css">
body{
	 color: #666;
   
	
}
  .btn-lg{
    padding: 2px 16px;  
  }
  .form-group{
    margin-bottom: 0;
  }
  .clearfix{
    margin-bottom: 4px;
  }
  .navbar-right{
    margin-right: -40px;
  }
  .clearfix a.active{
    font-weight: bold;
  }
  .navbar-left{
    float: left;
    margin-top: 45px;
    
  }
  .navbar-left li {
   
    position: relative;
    margin-left: 20px;

    }
  .navbar-top-links .dropdown-menu li{
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
  .navbar-static-top{
    margin-bottom: 0;
    float: right;
    background: none;
    position: absolute;
    right: -15px;
    bottom: -13px;
  }
  .a-new-pc{
    float: right;
    border:2px solid #000;
    text-align: center;
    padding: 5px 10px;
    color: #000;
  }
  
  .btn_back img{
    width: 12px;
    margin-right: 6px;
  }
  .content_notice {
    max-width: 171px;
  }
  /**/

    .div-list{
      display: inline-block;
      margin: 105px auto 0 auto;
     text-align: center;
      width: 100%;
    }
    .list-profile{
      margin: 0;
      padding: 0;
      width: auto;
      display: block;
    }
    .list-profile li{
      border-bottom: 1px solid #e7eaec;
      width: auto;
      display:block;
      padding-bottom: 5px;
      margin-bottom: 5px;
      text-align: left;
      list-style: none;
      padding: 0 30px 10px 30px;
      margin-bottom: 10px;
    }
    .list-profile li p{
      width: 100%;
      display: inline-block;
      margin: 0;
      padding: 0;
    }
    .list-profile li .p-1{
      color: #555;
      font-size: 13px;
    }
    .list-profile li .p-2{
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
        background: -moz-linear-gradient(center top , #f4f4f4, #e6e6e6) repeat scroll 0 0 rgba(0, 0, 0, 0);
        border: 1px solid #b1b1b1;
        border-radius: 4px 4px 0 0;
        box-shadow: 0 1px 1px #fff inset;
        font-weight: bold;
        padding: 14px 30px 10px;
        text-align: left;
        color: #666;
        font-weight: bold;
        font-family: "メイリオ",Meiryo,"ヒラギノ角ゴ Pro W3","Hiragino Kaku Gothic Pro","ＭＳ Ｐゴシック","MS PGothic",sans-serif !important;
}
.content-box-1 {
    background-color: #fff;
    border-bottom: 1px solid #e7eaec;
    border-left: 1px solid #e7eaec;
    border-radius: 0 0 4px 4px;
    border-right: 1px solid #e7eaec;
    padding: 20px 0;
    text-align: center;
}
/**/
#content {
		background:#f4f4f4;
		width:100%;
		height:auto;
		display:inline-block;
		/* margin:90px auto 0 auto; */
		padding:0;
		text-align:center;
		height:auto;
	}
	#inner {
		width:800px;
		height:auto;
		margin: 10px auto;
		display:block;
		text-align:left;
	}	
	.box-1{
		
		width:100%;
		display:inline-block;
	
		}
	.title-box-1{
		background: -moz-linear-gradient(center top , #f4f4f4, #e6e6e6) repeat scroll 0 0 rgba(0, 0, 0, 0);
		box-shadow: 0 1px 1px #fff inset;
		padding: 14px 30px 10px;
		text-align: left;
		border: 1px solid #b1b1b1;
		border-radius: 4px 4px 0 0;
		-webkit-border-radius: 4px 4px 0 0;
		-moz-border-radius: 4px 4px 0 0;
		font-weight: bold;
		color: #666;
		}	
	.content-box-1{
		 background-color: #fff;
		padding: 20px 30px 20px 30px;
		border-left: 1px solid #b1b1b1;
		border-right: 1px solid #b1b1b1;
		border-bottom: 1px solid #b1b1b1;
		border-radius: 0 0 4px 4px;
		-webkit-border-radius: 0 0 4px 4px;
		-moz-border-radius: 0 0 4px 4px;
		text-align:center;
		
		}	
	.p-box-1{
		font-size:14px;
		text-align:left;
		margin-top:10px;
		margin-bottom:20px;
		}	
	.box-1-form{
		width:500px;
		margin:20px auto 60px auto;
		text-align:center;
		display:inline-block;
		}	
	.input-1{
		float:left;
		border: 1px solid #b1b1b1;
		border-radius: 4px;
		-webkit-border-radius: 4px;
		-moz-border-radius: 4px;
		padding: 10px 9px 8px 9px;
		background-color: #f2f2f2;
		box-shadow: 0 3px 4px #ccc inset;
		width:412px;
		margin-right:10px;
		color:#666666;
		font-weight:bold;
		font-size:14px;
	}
	.submit-1{
		cursor: pointer;
		font-size: 1.4em;
		float:left;
		padding: 10px 12px 8px 12px;
		border: 1px solid #12476a;
		border-radius: 3px;
		background: linear-gradient(#307cae, #27648d);
		background: -webkit-gradient(linear, left top, left bottom, from(#307cae), to(#27648d));
		background: -moz-linear-gradient(top, #307cae, #27648d);
		background-color: #307caf;
		color: #ffffff !important;
		vertical-align: middle;
		text-shadow: 0 -1px 2px #000;
		box-shadow: 1px 1px 0 #649dc2 inset;
		white-space: nowrap;
		-ms-filter: "progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae, EndColorStr=#27648d)";
		filter: progid:DXImageTransform.Microsoft.Gradient(StartColorStr=#307cae, EndColorStr=#27648d);
		font-size:14px;
		width:auto;
		}
	.box-2{
	
		width:100%;
		display:inline-block;
		background:#fff;
		
		}
	.title-box-2{
		    background-color: #cecece;
			padding: 8px 30px 4px 30px;
			text-align: center;
			border: 1px solid #b1b1b1;
			border-radius: 4px 4px 0 0;
			-webkit-border-radius: 4px 4px 0 0;
			-moz-border-radius: 4px 4px 0 0;
			font-weight: bold;
		}	
	.title-box-2 span{
		background:url(img/ico_info.png) no-repeat left center;
		padding-left:30px;
		height:25px;
		width:auto;
		display:inline-block;
		color:#666666;
		
		}	
	.content-box-2{
		    background-color: #fafafa;
			padding: 20px 30px 20px 30px;
			border-left: 1px solid #b1b1b1;
			border-right: 1px solid #b1b1b1;
			border-bottom: 1px solid #b1b1b1;
			border-radius: 0 0 4px 4px;
			-webkit-border-radius: 0 0 4px 4px;
			-moz-border-radius: 0 0 4px 4px;
		}	
	.p-box-2{
		margin:0px 0 20px 0;
		width:auto;
		display:block;
		text-align:left;
		}
	.p-box-1-blue{
		font-weight:bold;
		color:#477497;
		text-align:center;
		font-size:16px;
		}	
	@media screen and (max-width: 799px) {
		h1 {
			width: auto;
			text-align: center;
		}
		#inner {
			width: auto;
			margin:10px 1%;
		}
		.content-box-1,
		.content-box-2{
			padding:20px;
			}
		.box-1-form {
			width: auto;
			margin: 20px auto 60px auto;
			text-align: center;
			display: block;
		}
		.input-1{
			float:none;
			width:100%;
			display:inline-block;
			margin:0;
			padding:10px 0 8px 0;
			text-indent:10px;
			}
		.submit-1{
			float:right;
			margin-top:10px;
			}	
	}
    /*edit 9/10*/
	.box-3{
		width:230px;
		margin:60px auto;
		display:inline-block;
		text-align:center;
		}
    
	.box-3 button{
		width:auto;
		margin:12px 0;
		display:inline-block;
		cursor: pointer;
		font-size: 1.4em;
		padding: 5px 12px 5px 12px;
		border: 1px solid #e3157a;
		border-radius: 3px;
		background-color: #e3157a;
		color: #ffffff !important;
		vertical-align: middle;
		text-shadow: 0 -1px 2px #000;
		white-space: nowrap;
		font-size: 14px;
   		border:0px;
		}	
   button.span-td{
     background-color: #e3157a;
     padding: 5px 12px 5px 12px;
     text-shadow: 0 -1px 2px #000;
    white-space: nowrap;
    font-size: 14px;
    color: #ffffff !important;
    display:inline-block;
    cursor: pointer;
    border:0px;
   } 
  /*end edit 9/10*/    
		.p-line{
			width:100%;
			border:1px solid #cfcfcf;
			display:inline-block;
			margin:20px 0 10px 0;
			}
		.p-box-3{
			width:100%;
			text-align:left;
			display:inline-block;
			margin:5px 0;
			}	
		.p-box-4{
			width:100%;
			text-align:left;
			display:inline-block;
			margin:5px 0;
			color:#525252;
			font-weight:bold;
			font-size:18px;
			}	
		.div-gird{
			border-top:2px solid #777777;
			
			width:100%;
			display:inline-block;
			height:auto;
			text-align:center;
			border-collapse:collapse;
			}
    /*edit 9/10*/   
		.div-gird th{
			padding:10px;
      border:1px solid #a9a9a9;
			}	
    .div-gird td{
      padding:6px 10px;
      border:1px solid #a9a9a9;
    } 
    /*end edit 9/10*/ 
		.div-gird th{
			width:17%;
			background: #efefef;
			}
		.div-gird th.th2{
			width:10%;
			}
		.div-gird th.th4{
			width:50%;
			background:#e4eef5;
			}			
	
		.span-0{
			background:#fff;
			}	
		.list-icon{
			margin:10px 0 0 0;
			padding:0;
			width:100%;
			display:inline-block;
			text-align:left;
			}
		.list-icon li{
			background:url(img/ico_info2.png) no-repeat left center;
			padding-left:10px;
			display:block;
			width:auto;
			text-align:left;
			color:#1a629d;
			font-size:12px;
			font-weight:bold;
			}
			
	.active {
		background-color : #1F74C7 !important;
	} 
  </style>

<div id="content">
	<div id="inner">
		<div class="box-1">
			<div class="title-box-1">名刺のダウンロード</div>
			<div class="content-box-1">
                <p id="titleMsg" class="p-box-1 p-box-1-blue" style="display: none">
					ダウンロードファイルの作成を行います。しばらくお待ちください。 <br/>
					<span style="color:#0080cb;">最大10分程度かかります</span>	
				</p>
                
                <div class="box-3">                	
                    <button id="1"  class="export">自分の名刺ダウンロード</button>
                    <c:if test="${roleAdminId == 7}">
                    	<button id="2" class="export">自社の名刺をダウンロード</button>
                    	<button id="3" class="export">グループ名刺をダウンロード</button>
                    </c:if>
                </div>
                <p class="p-line"></p>
                <p class="p-box-3">ダウンロード可能期間は1週間です。</p>
                <p class="p-box-3">自社の名刺データは1回のみダウンロード可能です。ダウンロードできなかった場合は、再度「自社の名刺をダウンロード」をクリックしてください。</p>
                <p class="p-box-4">ダウンロードファイル一覧</p>
                <table class="div-gird">
                	<tr>
                        <th  class="th1">依頼日時</th>
                        <th  class="th1 th2">名刺枚数</th>
                        <th  class="th1 th3">ファイル作成日時</th>
                        <th  class="th1 th4">ダウンロード</th>
                    </tr>
                    <c:forEach var="downloadCSVHistory" items="${downloadCSVHistory}" varStatus="loop">
						<tr id="${downloadCSVHistory.csvId}">
							<td><fmt:formatDate value="${downloadCSVHistory.requestDate}" pattern="yyyy-MM-dd" /></td>
							<td><c:out value="${downloadCSVHistory.csvType}" /></td>
							<td><fmt:formatDate value="${downloadCSVHistory.approvalDate}" pattern="yyyy-MM-dd" /></td>
							<td style="text-align:right;">
								<c:if test="${downloadCSVHistory.csvApprovalStatus != 1}">
									<%-- <c:out value="${downloadCSVHistory.csvUrl}" /> --%>
									<%-- <a href="downloadFileCSV/${downloadCSVHistory.csvUrl}"><c:out value="${downloadCSVHistory.csvUrl}" /></a> --%>
									<input type="hidden" name="csvURL" value="${downloadCSVHistory.csvUrl}" />
									<button class="span-td">ダウンロード</button>
								</c:if>
							</td>
						</tr>
					</c:forEach>
              </table>         
             </div>    
         </div>			
		</div>
	</div>


<script type="text/javascript">
   $(document).on('click', 'button.export', function(){
	   $(this).prop('disabled', true);
	   $(this).attr("style", "background-color:gray");
	   
	    $('button.export').removeClass('active');
	    $('button.export').removeClass('active');
	    $(this).addClass('active');
	    var id = $(this).attr('id');
		if(id == 1){
			document.location.href="downloadCSV/"+id;
			$(this).prop('disabled', false);
			/* setTimeout(function(){ 				
				document.location.href='/ecard-webapp/user/download';
			}, 3000); */
		} else {
			$("#titleMsg").css('display','block');
			$.ajax({
				type: 'GET',
				url: '/ecard-webapp/user/downloadCSV/'+id
			}).done(function(resp, status, xhr) {
				$("#titleMsg").css('display','none');
				setTimeout(function(){ 				
					document.location.href='/ecard-webapp/user/download';
				}, 3000);
				
				$(this).prop('disabled', false);
								
			}).fail(function(xhr, status, err) {
				$("#titleMsg").css('display','none');
				document.location.href='/ecard-webapp/user/download';
				
				$(this).prop('disabled', false);
			});	
		}
	});
	$(document).ready(function(){
		
		$('.span-td').click(function(e){
			$(this).attr("style", "background-color:gray");
			$(this).prop('disabled', true);
			
			var csvID = parseInt($(this).parents('tr').attr("id"));
			var csvName = $(this).parents('tr').find('input[name=csvURL]').val();
			document.location.href="downloadFileCSV/"+csvID;
			setTimeout(function(){ 				
				document.location.href='/ecard-webapp/user/download';
			}, 3000);
			
			/* $.ajax({
				type: 'POST',
				url: 'requestDownloadCSV',
				data: 'csvID=' + csvID + '&csvName='+csvName,
				beforeSend: function(xhr) {
	        		xhr.setRequestHeader("Accept", "application/json");
	        		xhr.setRequestHeader("Content-Type", "application/json");
	        	}
			}).done(function(resp, status, xhr) {				
				setTimeout(function(){ 				
					document.location.href='/ecard-webapp/user/download';
				}, 3000);
			}).fail(function(xhr, status, err) {
				document.location.href='/ecard-webapp/user/download';				
			});  */
			
		});
	});
	
	function showMsg(){
		$("#titleMsg").css('display','block');
		setTimeout(function(){ 				
			document.location.href='/ecard-webapp/user/download';
		}, 3000);
	}
</script>





