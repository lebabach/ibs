 
$(document).ready(function(){
	/* loading progress */
	$(document).ajaxStart(function() {
        $("#loadingProgress").show();
    });
    $(document).ajaxStop(function() {
        $('#loadingProgress').hide();
    });
	
	/*Begin process Manage Card*/
	$('.i-checks').iCheck({
	  checkboxClass: 'icheckbox_square-green',
	  radioClass: 'iradio_square-green'
       
	});
    $('#data-table input[name=chkALL]').on('ifChecked', function(event){
        if(event.type == "ifChecked"){
            $('input[name=bla]').iCheck("check"); 
        }
    });
  

    $('#data-table input[name=chkALL]').on('ifUnchecked', function(event){
        //alert(event.type + ' callback');
        if(event.type == "ifUnchecked"){
            $('input[name=bla]').iCheck("uncheck"); 
        }
    });

    $("[id*=chk_]").on('ifChecked', function(event){
        if ($('input[id*=chk_][type=checkbox]:checked').length == $('input[id*=chk_][type=checkbox]').length) {
             $('input[name=chkALL]').iCheck("check"); 
             $('th div:first-child').addClass('checked'); 
        } 
    });

    $("[id*=chk_]").on('ifUnchecked', function(event){
        if ($('input[id*=chk_][type=checkbox]:checked').length != $('input[id*=chk_][type=checkbox]').length) {
            $('th div:first-child').removeClass('checked') ;
        } 
             
    });

   $("input[name=bla]").on('ifChecked', function(event){
          $(".float-right").find("#collect_name").removeClass("disabled");
   });
   $("input[name=bla]").on('ifUnchecked', function(event){     
          if ($('input[id*=chk_][type=checkbox]:checked').length == 0){
            $(".float-right").find("#collect_name").addClass("disabled");
          }          
    });
    
    $("#collect_name").click(function(){
        window.location.href = "colectname.html";
    });
    
	$("#rowData").click(function(){
		window.location.href = "detail_card.html";
	});
	
    $('#paging').dataTable( {
        "dom": '<<t>ip>',
        "ordering": false,
        "iDisplayLength": 5,
        "language": {
			"zeroRecords": '見つかりませんでした。',
			"emptyTable": '見つかりませんでした。',
			"info": 'ページ表示件数',
			"infoEmpty": 'ページ表示件数',
			"paginate": {
				"previous": '前へ',
				"next": '次へ'
			}
		}
    } );
   
  
    /*End process Manage Card*/
    /*Begin process Top menu*/
    	$('#carousel').slick({
    	arrows: true,
    	slidesToShow: 4,
    	/*autoplay: true,*/
    	speed: 500,
    	variableWidth: true,
    	onAfterChange: function(){ 
    	      var cat = ($('#carousel').slickCurrentSlide()) + 1;
    	      $('.client-text > li').hide();
    	      $('.client-text > li:nth-child('+ cat +')').show();
    	}
    	});



    	/*Begin accordion  */
    	function toggleChevron(e) {
    	$(e.target)
    	    .prev('.panel-heading')
    	    .find("i.indicator")
    	    .toggleClass('fa-chevron-down fa-chevron-up');
    	}
    	$('#accordion').on('hidden.bs.collapse', toggleChevron);
    	$('#accordion').on('shown.bs.collapse', toggleChevron);
    	/*END accordion*/


    	/*begin click backup restore*/
    	$( "#backup-restore" ).click(function() {
    	 $(".container-progress").css("display", "none");
    	 $(".export-csv").css("display", "none");
    	$(".confirm-log").css("display", "none");
    	$(".download-permission").css("display", "none");
    	 $("#content-header").text('Backup / Restore')
    	 $(".backup-restored").css("display", "block");
    	});

    	$('.clockpicker').clockpicker();

    	/*end click backup restore */

    	/*begin click confirm log*/
    	
    	/*end click lick confirm log */
    	/*begin click export csv*/
    	$("#export-csv" ).click(function() {
    	$(".container-progress").css("display", "none");
    	$(".backup-restored").css("display", "none");
    	$(".confirm-log").css("display", "none");
    	$(".download-permission").css("display", "none");
    	$(".export-csv").css("display", "block");
    	 $("#content-header").text('Export');
    	 
    	});
    	/*end click export csv */
    	/*begin click recognition-download*/
    	$("#recognition-download" ).click(function() {
    	$(".container-progress").css("display", "none");
    	$(".backup-restored").css("display", "none");
    	$(".confirm-log").css("display", "none");
    	$(".export-csv").css("display", "none");
    	$(".download-permission").css("display", "block");

    	 $("#content-header").text('Download');
    	 
    	});
    	/*end click recognition-download */
    	
    	/*End process top menu*/
       /*scan card*/
    	
        
        /*End scan card*/
    	
});