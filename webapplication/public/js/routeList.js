// admin user

$( document ).ready(function() {
    $.get( "http://localhost:9091/fetchSOSRequests", function(data) {
             sosdata = data;
         $.get( "http://localhost:9091/allActiveRoutes", function(data) {
            userData(data);
     });
    });
    
    var getRoutedata;
    
    $(document).on('click', '#routelisttable tr', routeNestPages);
    $(document).on('click', '.start-trip', startTrip);
    $(document).on('click', '.sos-btn', createSos);
    $(document).on('click', '.boarded', userBoarded);
    $(document).on('click', '.deboarded', userDeBoarded);
    
    function createSos(){ 
    $.get("http://localhost:9091/raiseSOS/prerana/"+getRoutedata[0].routeId, function(data) {
           console.log(data)
     });
    }
    function userData(data){
       
        console.log(data);
        console.log(sosdata);
        var html= '';
           
            html += "<div class='col-md-12'>";
            html += '<table class="table table-bordered" id="SosList">';
            html += '<tr>';
            html += '<td colspan="5" style="text-align:center">SOS lists</td>';
            html += '</tr>';
           sosdata.forEach(function(value,index){
           
            html += '<tr id='+value.id+' style="cursor:pointer" >';
            html += '<td>'+value.raisedAt+'</td>';
            html += '<td>'+value.raisedOn+'</td>';
            html += '<td>'+value.routeNumber+'</td>'; 
            html += '<td>'+value.userName+'</td>';
            html += '<td>'+value.startTime+'</td>';  
            html += '</tr>'; 
           })
            html += '</table>';
            html += '<table class="table table-bordered" id="routelisttable">';
            html += '<tr>';
            html += '<td colspan="5" style="text-align:center">Route lists</td>';
            html += '</tr>';
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
               getRoutedata = data;
               AdminData(data);
               
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
            html += '<button class="btn btn-default sos-btn" type="submit">SOS</button>';
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
                     html += '<input type="checkbox" value="" class="boarded">';
                     html += '</td>';
               }
              if(value.deboarded == "true"){
                     html += '<td>';
                     html += '<input type="checkbox" value="" checked class="deboarded">';
                     html += '</td>';
               }else{
                     html += '<td>';
                     html += '<input type="checkbox" value="" class="deboarded">';
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
    function userBoarded(){
        //$(".boarded").prop("checked", true);
         $.get( "http://localhost:9091/board/prerana/"+getRoutedata[0].routeId, function(data) {
          if(data == true){
              alert("User have boarded");
              $(".boarded").prop("checked", true).attr("disabled", true);
          }
     });
    }
    function userDeBoarded(){
        //$(".deboarded").prop("checked", true);
        $.get( "http://localhost:9091/board/prerana/"+getRoutedata[0].routeId, function(data) {
            if(data == true){
              alert("User have deboarded");
                $(".deboarded").prop("checked", true).attr("disabled", true);
          }
     });
    }
    
});