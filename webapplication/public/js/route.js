// Normal User
var getRoutedata;
$( document ).ready(function() {


    var userName = sessionStorage.getItem("userName");

    $.get( "http://localhost:9091/displayRoute/"+userName, function(data) {
        getRoutedata = data;
            renderAdminPage(data);
    });
     
    function renderAdminPage(data){
        var admindata = data;
        $("#usrname").html(admindata.userName);
        $("#route").html(admindata.routeNumber);
        userData(data);
    }
    
    $(document).on('click', '.boarded', userBoarded);
    $(document).on('click', '.deboarded', userDeBoarded);
    
    function userData(data){
        
        var html= '';
            html += "<div class='col-md-12'>";
            html += "<div class='col-md-12'>";
            html += '<p> <b>Route Number:</b><span id="usrname">'+data[0].routeNumber+'</span></p>';
            html += '<p> <b> Name:</b><span id="usrname">'+data[0].userName+'</span></p>';
            html += "</div>";
            html += '<table class="table table-bordered table-responsive">';
            html += '<tr>';
            html += '<th><input type="checkbox" value=""> Select All</th>';
            html += '<th>Drop Location</th>';
            html += '<th>Delayed By</th>';
            html += '<th>Boarded</th>';
            html += '<th>Deboarded</th>';
            html += '</tr>';
            data.forEach(function(value,index){
            html += '<tr>';
            html += '<td>'+value.userName+'</td>';
            html += '<td>'+value.dropLocation+'</td>';
            html += '<td>'+value.delayedBy+'</td>';
                if(value.boarded == "true"){
                     html += '<td>';
                     html += '<input type="checkbox" value="" checked data-attr='+value.userName+'>';
                     html += '</td>';
               }else{
                     html += '<td>';
                     html += '<input type="checkbox" value="" class="boarded" data-attr='+value.userName+'>';
                     html += '</td>';
               }
              if(value.deboarded == "true"){
                     html += '<td>';
                     html += '<input type="checkbox" value="" checked class="deboarded" data-attr='+value.userName+'>';
                     html += '</td>';
               }else{
                     html += '<td>';
                     html += '<input type="checkbox" value="" class="deboarded" data-attr='+value.userName+'>';
                     html += '</td>';
               }
            html += '</tr>';    
           })
            html += '</table>';
            html += "</div>";
         $("#drawtable").html(html);  
    }
    
    function userBoarded(){
        //$(".boarded").prop("checked", true);
        var hetUsername = $(".deboarded").attr("data-attr");
         $.get( "http://localhost:9091/board/"+hetUsername+"/"+getRoutedata[0].routeId, function(data) {
          if(data == true){
         
               $("#content").html("User have boarded");
              $("#alertModal").modal('show');
              $(".boarded").prop("checked", true).attr("disabled", true);
          }
     });
    }
    function userDeBoarded(){
        //$(".deboarded").prop("checked", true);
        var hetUsername = $(".deboarded").attr("data-attr");
        $.get( "http://localhost:9091/deboard/"+hetUsername+"/"+getRoutedata[0].routeId, function(data) {
            if(data == true){
         
                 $("#content").html("User have deboarded");
              $("#alertModal").modal('show');
                $(".deboarded").prop("checked", true).attr("disabled", true);
          }
     });
    }
    
    
    
});