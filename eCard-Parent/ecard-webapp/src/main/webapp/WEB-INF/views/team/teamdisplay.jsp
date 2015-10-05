<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<style type="text/css">
    .btn-team{
        width: 9% !important;
        margin: 0 auto;
        position: relative;
        left: 44%;
        margin-bottom: 26px;
    }

</style>
<body>
 <div class="container-fluid padding-top20 bg-container height100per">
        
        <!-- RIGHT SIDE -->
        <div id="right-side" class="col-sm-12">
            <!-- BAR TOP -->
            <div class="row bg-white box-shadow menu-top-header col-sm-12">
                <div class="col-sm-12">
                    <div class="float-left">
                        <h4 class="h4-header">メール送信</h4>
                    </div>
                    
                    <div class="float-right float-right-manage">
                         
                    </div>
                </div>
            </div>
            
            <!-- END BAR TOP -->
            <div class="row bg-white box-shadow box-marginTop5 padding-top-bottom col-sm-12">
                    <div class="team-name" style="margin: 0 auto;
                                           width:68%;
                                          padding-bottom:5%">
                          <div class="form-group">
                               <div class="col-sm-3">
                                <label class="control-label col-sm-3" for="email" style="padding:0;line-height:18px;width:27%;">目標登録数</label>
                                <div class="col-sm-8" style="padding-left:25px;">
                                <label>Team A</label>
                                 </div>
                              </div>
                            </div>
                            <div class="form-group">
                               <div class="col-sm-3">
                                <label class="control-label col-sm-3" for="email" style="padding:0;line-height:18px;width:27%;">目標登録数</label>
                                <div class="col-sm-8" style="padding-left:25px;">
                                <label>100</label>
                                 </div>
                              </div>
                            </div>
                     </div>
                    <div class="container col-sm-8 col-xs-offset-2" id="data-table">
                          <form class="form-horizontal" role="form">
                            
                              <div class="col-sm-12 " style="margin-top:-49px;">
                                     
                                      <table class="table container" id="paging" >
                                             <thead>
                                                  <tr id="rowData" style="background:#c3c3c3;">
                                                    <td>氏名</td>
                                                    <td>権限　</td>
                                                
                                                    
                                                   </tr>
                                             </thead>
                                            <tbody>
                                               
                                                <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                 <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                 <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                                <tr id="rowData">
                                                    <td><a href="#"> Pepe</a></td>
                                                    <td><input type="text" class="i-checks i-checks-chk_all" name="chkALL" id="chkAll"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                              </div>
                              <button type="submit" class="btn btn-primary btn-team">返信する</button>
                            </div>

                                    
                           
                          </form>
                       
                    </div>
            </div>
            <!-- BAR BODY -->
            
        </div>
        <!-- END RIGHT SIDE -->
      </div>
</body>
</html>