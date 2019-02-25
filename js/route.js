$( document ).ready(function() {
    $.get( "http://localhost:9091/displayRoute ", function(data) {
            renderAdminPage(data);
    });
    
    function renderAdminPage(data){
        var admindata = data;

        $("#usrname").html(admindata.userName);
        $("#route").html(admindata.routeNumber);
        
        userData(data);
    }
    
    function userData(data){
        console.log(data);
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
                 html += '<input type="checkbox" value="" checked>';
                 html += '</td>';
               }else{
                  html += '<td>';
                 html += '<input type="checkbox" value="" >';
                 html += '</td>';
               }
             if(value.deboarded == "true"){
                 html += '<td>';
                 html += '<input type="checkbox" value="" checked>';
                 html += '</td>';
               }else{
                  html += '<td>';
                 html += '<input type="checkbox" value="" >';
                 html += '</td>';
               }
               
            html += '</tr>';   
           
               
           })
            html += '</table>';
            html += "</div>";
         $("#drawtable").html(html);  
    }
});