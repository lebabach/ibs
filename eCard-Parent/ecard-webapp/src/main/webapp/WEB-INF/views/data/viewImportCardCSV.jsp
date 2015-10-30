<%-- 
    Document   : importCSV
    Created on : Sep 10, 2015, 12:03:34 PM
    Author     : vinhla
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<style>
.mesage_error {
	color: red;
	display: none;
}

.mesage_record {
	color: red;
	display: none;
	font-size: 12;
}
</style>

<div class="container-fluid padding-top20 bg-container height100per">
	<input type="text" name="recordSuccess" id="recordSuccess"
		class="hidden" value="${recordSuccess}"> <input type="text"
		name="recordError" id="recordError" class="hidden"
		value="${recordError}"> <input type="text" name="recordEmpty"
		id="recordEmpty" class="hidden" value="${recordEmpty}">
	<%-- <input type="text" name="errorLineNo" id="errorLineNo" class="hidden" value="${errorLineNo}"> --%>
	<!-- CENTER SIDE -->
	<div id="center-side" class="col-sm-12">
		<!-- BAR TOP -->
		<div class="row bg-white box-shadow menu-top-header" style="width:100%;">
			<div class="col-sm-12 ch-check" id="ct1">
				<div class="float-left col-sm-6">
					<h4 class="h4-header">
						<fmt:message key="msg.business.card.data.migration" />
					</h4>

				</div>
				<div style="float: right; margin: 0 auto;">
					<h4 class="h4-header">
						<span><button type="button"
								class="btn btn-primary btn_cancle" data-dismiss="modal"
								id="btnBack">
								<fmt:message key="team.allocation.cancel" />
							</button></span>
					</h4>
				</div>
			</div>
		</div>

		<!-- END BAR TOP -->
		<style type="text/css">
			.divBtn{
				float: right;
				padding: 15px;
			}
			
			#btnDrawImage{
				background-color: #e3157a;
				padding: 8px 12px 8px 12px;
				text-shadow: 0 -1px 2px #000;
				white-space: nowrap;
				font-size: 14px;
				color: #ffffff !important;
				display:inline-block;
				cursor: pointer;
				border:0px;
				border-radius: 5px;
			}
			.btn-default {
			    background-color: #1AB394 !important;
			    border-color: #1AB394;
			    color: #FFF;
			}
		</style>

		<div
			class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
			<div class="divBtn">
				<button id="btnDrawImage">画像合成バッチ実行</button>
			</div>
			
			<div style="margin: 0 auto">
				<div class="row" style="text-align: center; margin: 25px;">
					<p style="color: red;" class="mesage_error error_exception"></p>
					<p style="color: red;" class="mesage_error group_company"></p>
					<p style="color: red;" class="mesage_error user_id"></p>
					<p style="color: red;" class="mesage_error file_name"></p>
					<p style="color: red;" class="mesage_record recordSuccess"></p>
					<p style="color: red;" class="mesage_record recordError"></p>
					<p style="color: red;" class="mesage_record recordEmpty"></p>
				</div>

				<form class="form-horizontal" role="form" id="upload"
					action="<c:url value="/data/uploadUserCSV"/>"
					enctype="multipart/form-data" method="POST">
					<!-- Import User -->

					<div class="col-md-3 col-xs-offset-4">
						<select id="groupId" name="groupCompanyId"
							style="width: 170px; height: 32px;">
							<option value=''><fmt:message
									key="msg.company.selection" /></option>
							<c:forEach var="listGroupCompany" items="${listGroupCompany}"
								varStatus="loop">
								<option value='${listGroupCompany.groupCompanyId}'><c:out
										value="${listGroupCompany.groupCompanyName}" /></option>
							</c:forEach>
						</select>
					</div>
					<input type="text" name="groupCompanyId" id="groupCompanyId"
						class="hidden" value="">
					<div class="col-md-4">
						<select id="userId" name="userId"
							style="width: 170px; height: 32px;">
							<option value=''><fmt:message key="msg.user.selection" /></option>
							<c:forEach var="listUserInfo" items="${listUserInfo}"
								varStatus="loop">
								<option value='${listUserInfo.userId}'><c:out
										value="${listUserInfo.name}" /></option>
							</c:forEach>
						</select> <input type="text" name="userId" id="userId" class="hidden"
							value="">
					</div>

					<div class="col-md-3 col-xs-offset-4">
						<div class="form-group"
							style="margin-top: 7px; margin-left: 12px;">
							<div class="row">
								<label for="fileCSV" class="control-label">利用者CSVファイル選択</label>
								<input type="file" name="file" class="submit-1" id="files" />
							</div>
							<div class="row" style="margin-top: 5px;">
								<span><button type="button" class="btn btn-primary"
										id="btnUploadUser">
										<fmt:message key="team.allocation.register" />
									</button></span>
							</div>
						</div>
					</div>
				</form>
				<!-- End Import User -->

				<!-- Import Card -->
				<div class="col-md-2">
					<%--                     <c:if test="${msgImportSuccess != null}">
                        <div class="row">
                            <c:out value="${msgImportSuccess}"></c:out>
                        </div>
                    </c:if> --%>

					<form id="frmImportCsv" name="frmImportCsv"
						style="padding-bottom: 10px;" method="POST"
						action="<c:url value="/data/uploadCardCsv"/>"
						enctype="multipart/form-data">
						<div class="form-group"
							style="margin-top: 7px; margin-left: 12px;">
							<div class="row">
								<label for="fileCSV" class="control-label">名刺CSVファイル選択</label> <input
									type="file" name="file" class="submit-1" id="file" /> <input
									type="text" name="hGroupCompanyId" id="hGroupCompanyId"
									class="hidden" value=""> <input type="text"
									name="huserId" id="huserId" class="hidden" value="">
							</div>
							<div class="row" style="margin-top: 5px;">
								<span><button type="button" class="btn btn-primary"
										id="btnImportCardCsv">
										<fmt:message key="team.allocation.register" />
									</button></span>
							</div>
						</div>
					</form>
				</div>
				<!-- End Import Card -->

			</div>


		</div>
		<!-- 		<div class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12" id="showLog" style="display:none;" >
			<div style="margin: 0 auto;text-align: center; height:200px;overflow-y:scroll">
				<p class="row" id="errorDiv" style="display: none; color: red;"></p>
 		    <p style="color: red;" class="mesage_record errorLineNo"></p>
			</div>
			
		
		</div> -->

	</div>
	<!-- BAR BODY -->

</div>


<script type="text/javascript">
    $(document).ready(function(){
    	var recordSuccess = $('input[name=recordSuccess]').val();
    	var recordError = $('input[name=recordError]').val();
    	var recordEmpty = $('input[name=recordEmpty]').val();
    	/* var errorLineNo = $('input[name=errorLineNo]').val(); */
    	if (recordSuccess != 0 || recordError != 0 || recordEmpty != 0){
    		$(".recordSuccess").text("<fmt:message key='import.msg.success'/> : " +recordSuccess);
	  		$(".recordError").text("<fmt:message key='import.msg.error'/> : "+recordError);
	  		$(".recordEmpty").text("<fmt:message key='import.msg.empty'/> : "+recordEmpty);
    		/* $(".errorLineNo").html(errorLineNo); */
            $(".mesage_record").css("display","block");
            /* $("#showLog").css("display","block"); */
            
    	}
    	
        var error = getUrlParameter('error');
        if(error == "formatCSV"){
               $(".error_exception").text('<fmt:message key="msg.error.format.csv"/>');
               $(".mesage_error").css("display","block");
        }

        if(error == "insertData"){
                $(".error_exception").text('<fmt:message key="msg.error.insert.sql"/>');
               $(".mesage_error").css("display","block");
        }

        if(error == "ErrorSystem"){
               $(".error_exception").text('<fmt:message key="msg.error.error.system"/>');
               $(".mesage_error").css("display","block");
        }
        
        if(error == "validationData"){
            $(".error_exception").text('<fmt:message key="msg.error.validation.data"/>');
            $(".mesage_error").css("display","block");
     	}
        
        if(error == "emptyName"){
            $(".error_exception").text('<fmt:message key="msg.error.empty.name"/>');
            $(".mesage_error").css("display","block");
     	}
        
        if(error == "noSansanId"){
            $(".error_exception").text('<fmt:message key="msg.error.no.sansan.id"/>');
            $(".mesage_error").css("display","block");
	     }
  	  
        $("#btnImportCardCsv").click(function(){
        	$(".error_exception").text("");
            $(".mesage_error").css("display","none");
    		validationFile("file");
    		checkValidationForm();
    		if(!checkValidationForm()){			
    			return false;
    		}
    		if(!validationFile("file")){			
    			return false;
    		}
            $("#hGroupCompanyId").val($("#groupId").val());
            $("#huserId").val($("#userId").val());
            $("#frmImportCsv").submit();
        });
        
        $("#btnBack").click(function(){
            document.location.href = '<c:url value="/manager/home"/>';
        });
        
        $('#btnUploadUser').on('click', function(){
        	$(".error_exception").text("");
            $(".mesage_error").css("display","none");
    		validationFile("files");
    		checkValidationForm();
    		if(!checkValidationForm()){			
    			return false;
    		}
    		if(!validationFile("files")){			
    			return false;
    		}
    		$("#upload").submit();
    	});
        
        //Process card image
        $("#btnDrawImage").click(function(){
        	BootstrapDialog.confirm({
                title: '<fmt:message key="popup.title.info" />',
                message: '<fmt:message key="msg.process.card.confirm" />',
                closable: true, // <-- Default value is false
                btnOKLabel: 'OK', // <-- Default value is 'OK',
                btnCancelLabel: 'キャンセル', // <-- Default value is 'Cancel',
                btnCancelClass: 'btn-primary',
                callback: function(result) {
                    if(result) {
                    	processCardImage();
                    }else {
                    	//result.close();
                    }
                }
            });
        	
        });
    });
    
    $('select').on('change', function() {		
        $('input[name=groupCompanyId]').val(this.value);
        $('input[name=groupCompanyId]').value = this.value;
    });
    
    
    /* Function util */
    function validationFile(fileId){
		var checkValidation=true;
		var x = document.getElementById(fileId);
		var txt;
		if ('files' in x) {
	        if (x.files.length != 1) {
	        	$(".file_name").text("<fmt:message key='import.file.validate'/>");
	            $(".mesage_error").css("display","block");
	            checkValidation = false;
	        } else {
	        	if (x.files.length == 0) {
	                txt = "Select one or more files.";
	            } else {
	                for (var i = 0; i < x.files.length; i++) {
	                    
	                    var file = x.files[i];
	                    if ('name' in file) {
	                        txt += "name: " + file.name + "<br>";
	                    }
	                    /* if ('size' in file) {
	                    	if(file.size > 1000000){
	                    		$(".file_name").text("<fmt:message key='import.file.validate.large'/>");
	        	        		$(".mesage_error").css("display","block");
	        	        		checkValidation = false;
	        	        	}
	                        
	                    } */
	                }
	            }
	        }
	        $('.file_name').innerHTML = txt;
	    }
		return checkValidation;
	};
	
	function checkValidationForm(){
		var checkValidation=true;
		if($("#groupId").val() == ""){
			$(".group_company").text("<fmt:message key='import.group.company.validate'/>");
			checkValidation = false;
			$(".mesage_error").css("display","block");
		}
		
		if($("#userId").val() == ""){
			$(".user_id").text("<fmt:message key='import.user.id.validate'/>");
			checkValidation = false;
			$(".mesage_error").css("display","block");
		}
		
		return checkValidation;
	};
	
    function getUrlParameter(sParam) {
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
    
    function processCardImage(){
    	$.ajax({
        	url: "<c:url value='/data/processCardImage' />",
        	//data: JSON.stringify(json),
        	type: "POST",
        	dataType: 'json',
        	beforeSend: function(xhr) {
        		xhr.setRequestHeader("Accept", "application/json");
        		xhr.setRequestHeader("Content-Type", "application/json");
        	},
        	success: function(resp) {
				if(resp == true){
					BootstrapDialog.show({
	    				title: '<fmt:message key="popup.title.info" />',
	    				message: '<fmt:message key="msg.process.card.success" />'
	    	      	});
				}
        	},
        	error: function(error){
        		console.log(error);
		  	}
        });	    	
    }
</script>
