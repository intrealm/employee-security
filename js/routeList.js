// admin user

$( document ).ready(function() {
    $.get( "http://localhost:9091/allActiveRoutes", function(data) {
            renderROuteList(data);
    });
    
    function renderROuteList(data){
        
        userData(data);
    }
    $(document).on('click', '#routelisttable tr', routeNestPages);
    $(document).on('click', '.start-trip', startTrip);
    function userData(data){
        console.log(data);
        var html= '';
           
            html += "<div class='col-md-12'>";
            html += '<table class="table table-bordered" id="routelisttable">';
            html += '<tr>';
            html += '<td>routeNumber</td>';
            html += '<td>shift</td>';
            html += '<td>delayedBy</td>';
            html += '<td>vehicleNumber</td>';
            html += '<td>startTime</td>';
            html += '</tr>';
           data.forEach(function(value,index){
               
          [{"id":2,"routeNumber":15,"vehicleNumber":"UP-14-1234","delayedBy":0,
            "startTime":null,"etaInMinutes":2,"shift":"DROP","started":true,"completed":false}]  
            html += '<tr id='+value.id+' style="cursor:pointer" >';
            html += '<td>'+value.routeNumber+'</td>';
            html += '<td>'+value.shift+'</td>';
            html += '<td>'+value.delayedBy+'</td>'; 
            html += '<td>'+value.vehicleNumber+'</td>';
            html += '<td>'+value.startTime+'</td>';
            html += '</tr>';   
           
               
           });
            html += '</table>';
            html += "</div>";
         $("#drawRouteList").html(html);  
    }
    
    function routeNestPages(){
          
        var getRoleId = localStorage.getItem("roleid");
           
            $.get( "http://localhost:9091/displayRouteForAdmin/"+this.id, function(data) {
              
                AdminData(data)
                
           });      
    
    }
    
    
    function AdminData(data){
        console.log(data);
        var html= '';
            html += "<div class='col-md-12'>";
            html += "<div class='col-md-6'>";
            html += '<p> Route Number:<span id="usrname">'+data[0].routeNumber+'</span></p>';
            html += '<p> Name:<span id="usrname">'+data[0].userName+'</span></p>';
            html += "</div>";
            html += "<div class='col-md-6'>";
            html += '<button class="btn btn-default sos" type="submit">SOS</button>';
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
            html += '<div class="row">';
            html += '<div class="col-md-12 text-center" id="buttonContainer">';
            html += '<button class="btn btn-default start-trip" type="submit">Start Trip</button>';
            html += '<button class="btn btn-default track-trip" type="submit" style="display:none; margin-right:50px">Track Trip</button>';
            html += '<button class="btn btn-default track-trip" type="submit" style="display:none; margin-left:50px">Complete Trip</button>';
            html += '</div>';
            html += '</div>';
         $("#pageview").html(html); 
    }
    
    function startTrip(){
        $(".track-trip").show();
        $(".start-trip").hide();
        
    }
    
});