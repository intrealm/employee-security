// Normal User

$( document ).ready(function() {
    $.get( "http://localhost:9091/displayRoute/souravp", function(data) {
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
            html += '<p> Route Number:<span id="usrname">'+data[0].routeNumber+'</span></p>';
            html += '<p> Name:<span id="usrname">'+data[0].userName+'</span></p>';
            html += "</div>";
            html += '<table class="table table-bordered">';
            html += '<tr>';
            html += '<td><input type="checkbox" value=""> Select All</td>';
            html += '<td>Drop Location</td>';
            html += '<td>Delayed By</td>';
            html += '<td>Boarded</td>';
            html += '<td>Deboarded</td>';
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
              alert("User have boarded");
              $(".boarded").prop("checked", true).attr("disabled", true);
          }
     });
    }
    function userDeBoarded(){
        //$(".deboarded").prop("checked", true);
        var hetUsername = $(".deboarded").attr("data-attr");
        $.get( "http://localhost:9091/board/"+hetUsername+"/"+getRoutedata[0].routeId, function(data) {
            if(data == true){
              alert("User have deboarded");
                $(".deboarded").prop("checked", true).attr("disabled", true);
          }
     });
    }
});