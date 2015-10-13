<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<style type="text/css">
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
      border-bottom: 1px solid #b1b1b1;
      width: auto;
      display:block;
      padding-bottom: 5px;
      margin-bottom: 5px;
      text-align: left;
      list-style: none;
      padding: 0 60px 10px 60px;
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
    border-bottom: 1px solid #b1b1b1;
    border-left: 1px solid #b1b1b1;
    border-radius: 0 0 4px 4px;
    border-right: 1px solid #b1b1b1;
    padding: 15px 0 15px 0;
    text-align: center;
}

.label-c{
    color: #666;
    width: 150px;
  }
  .input-c{
    width: 524px;
    padding: 10px;
    color: #666;
  }
 .form{
  width:490px;
  display: inline-block;
  text-align: center; 
 } 
  .textarea{
 
  display: block;
border: 1px solid #b1b1b1;
border-radius: 4px;
-webkit-border-radius: 4px;
-moz-border-radius: 4px;
padding: 10px 9px 8px 9px;
background-color: #f2f2f2;
box-shadow: 0 3px 4px #ccc inset;
width: 100%;
height:300px;
color: #666666;
font-weight: bold;
font-size: 14px;
  } 
 .submit{
cursor: pointer;
font-size: 1.4em;
margin: 20px 0;
padding: 10px 12px 8px 12px;
border: 1px solid #e3157a;
border-radius: 3px;

background-color: #e3157a;
color: #ffffff !important;
vertical-align: middle;
text-shadow: 0 -1px 2px #000;

white-space: nowrap;
font-size: 14px;
width: 100%;
display: block;

 } 
 .form-cm{
    width: 93%;
    display: inline-block;
    text-align: center;
    vertical-align: top;
 }
 .label-c{
  float: left;
  width: 95px;
  text-align: left;
  color: ##666;
  font-size: 14px;
 }
 .input-c{
  width: 500px;
  float: left;
  height: 40px;
 }
 .submit{
    float: left;
    width: 50px;
    margin: 0 0 0 10px;
    height: 40px;
 }
 .show-list-s{
  display: inline-block;
width: 553px;
text-align: left;
margin: 20px auto 30px auto;
 }
 .ul-1,.ul-2,.ul-3{
  margin: 0;
  padding: 0;
  display: inline-block;
  width: 100%;
 }
 .ul-1 li.click{
   
   font-weight: bold;
 }
 .ul-1 .li-1{
    display: inline-block;
    width:  100%;
    text-align: left;
    color: ##666;
    font-size: 16px;
    cursor: pointer;
    margin: 5px 0;
    border-bottom: 1px solid #b1b1b1;
}

.ul-1 .li-11{
    display: inline-block;
    width:  100%;
    text-align: left;
    color: ##666;
    font-size: 16px;
    cursor: pointer;
    margin: 5px 0px 5px 20px;
}
 .ul-2 .li-2{
    display:block;
    width: auto;
    text-align: left;
    color: ##666;
    font-size: 14px;
    margin:5px 0 5px 20px;
    cursor: pointer;
    
 }
 .ul-3 .li-3{
    display:block;
    width: auto;
    text-align: left;
    color: ##666;
    font-size: 14px;
     margin:5px 0 5px 35px;
    cursor: pointer;
    border-bottom: 1px dashed #b1b1b1;
    font-weight: normal;
 }
 .ul-3 .li-3 a{
  color: #666;
 }
 .ul-3 .li-3 a:hover{
    text-decoration: underline;
 }
</style>

<div class="div-list">  
   	<div class="box-1">
    	<div class="title-box-1">顧客組織ツリー</div>
    	<div class="content-box-1">  
           <form class="form-cm" name="searchForm" id="searchForm" method="post">
               <div class="form-group col-md-11">
          			<label class="label-c">企業名</label>
               		<input class="input-c" value="" name="companyName" />
               </div>
               <div class="col-md-1" style="margin-left: -65px;">
               	<input type="button" value="検索" class="submit" id="searchCompany" />
               </div>
           </form>
           
           <div class="show-list-s">
               
           </div>
           <!-- End list search -->
    	</div>
    </div>
</div>

<script type="text/javascript">
$(document).ready(function(){
	var validator = $("#searchForm").validate({
    	rules: {
    		companyName: "required"
		},
		messages: {
			companyName: '<fmt:message key="valid.companyName" />'
		}
    });

	var isClicked = false;
    $(document).on('click','li.li-1', function () {
    	isClicked = !isClicked;
    	var companyName = $("#"+this.id).find("input[name=companyName_"+this.id+"]").val();
    	$(this).removeClass("li-1");
    	$(this).addClass("li-1 click active");
    	searchDepartmentTree(companyName, this.id);
    });
    
    $(document).on('click','li.li-11', function () {  
    	//console.log($("#"+this.id).find("input[name="+this.id+"]").val());
    	$(this).removeClass("li-11");
    	$(this).addClass("li-11 click active");
    	
    	var deptName = $("#"+this.id).find("input[name="+this.id+"]").val();
    	var compName = $("#"+this.id).find("input[name^=comp_]").val();
    	searchCardInfo(deptName, compName, this.id);
    });
	
	$("#searchCompany").click(function(e){
		if($("#searchForm").valid()){
			searchCompanyTree();
		}
	});
});

function searchCompanyTree(){
	var json = {"companyName" : $("input[name=companyName]").val()};
	$.ajax({
    	url: "<c:url value='/user/searchCompany' />",
    	data: JSON.stringify(json),
    	type: "POST",
    	
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
    	success: function(response) {
    		//console.log(JSON.stringify(response));
    		var respHTML = "<ul class='ul-1'>";
       		$.each(response, function(index, value){
       			if(value["companyName"] != "" || value["companyName"] != null){
	       			respHTML += "<li class='li-1' id='"+ value["cardId"] +"'>"+ value["companyName"] +" ("+ value["count"] +")"
	                    	+ "<input type='hidden' value='"+ value["companyName"] +"' name='companyName_"+ value["cardId"] +"' />"
	                    	+ "</li><div id='div_"+ value["cardId"] +"'></div>";
       			}
       		});
       		respHTML += "</ul>";
       		
       		$(".show-list-s").html(respHTML);
    	},
    	error: function(xhr, status, error){
    		console.log("Error :"+ xhr.responseText);
	  	}
    });
}

function searchDepartmentTree(companyName, id){
	var compName = $("input[name=companyName_"+id+"]").val();
	var json = {"companyName" : companyName};
	$.ajax({
    	url: "<c:url value='/user/searchDepartment' />",
    	data: JSON.stringify(json),
    	type: "POST",
    	
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
    	success: function(response) {
       		//console.log(JSON.stringify(response));
       		var respHTML = "<ul class='ul-2'>";
       		$.each(response, function(index, value){
       			if(value["companyName"] != ""){
       				respHTML += "<li id='dep_"+ value["cardId"] +"' class='li-11'>"+ value["companyName"] +"("+ value["count"] +")"
       						+ "<input type='hidden' value='"+ value["companyName"] +"' name='dep_"+ value["cardId"] +"' />"
       						+ "<input type='hidden' value='"+ compName +"' name='comp_"+ value["cardId"] +"' />"
       						+ "</li><div id='div_dep_"+ value["cardId"] +"'></div>";
       			}
       		}); 
       		respHTML += "</ul>";
       		
       		$("#div_"+id).html(respHTML);
    	},
    	error: function(xhr, status, error){
    		console.log("Error :"+ xhr.responseText);
	  	}
    });
}

function searchCardInfo(deptName, compName, id){
	var json = {"companyName" : compName, "departmentName" : deptName};
	$.ajax({
    	url: "<c:url value='/user/searchCardInfo' />",
    	data: JSON.stringify(json),
    	type: "POST",
    	
    	beforeSend: function(xhr) {
    		xhr.setRequestHeader("Accept", "application/json");
    		xhr.setRequestHeader("Content-Type", "application/json");
    	},
    	success: function(response) {
       		console.log(JSON.stringify(response));
       		var respHTML = "<ul style='display: inline-block;' class='ul-3'>";
       		$.each(response, function(index, value){
       			respHTML += "<li class='li-3' id='"+ value["cardId"] +"'><a href='<c:url value='/user/card/details/"+ value["cardId"] +"' />'>"+ value["name"] +"</a></li>";
       		}); 
       		respHTML += "</ul>";
       		
       		$("#div_"+id).html(respHTML);
    	},
    	error: function(xhr, status, error){
    		console.log("Error :"+ xhr.responseText);
	  	}
    });
}
</script>