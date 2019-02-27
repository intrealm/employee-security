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
    $(document).on('click', '#SosList tr', sosDetailspage);
    
    $(document).on('click', '.sos-btn', createSos);
    $(document).on('click', '.boarded', userBoarded);
    $(document).on('click', '.deboarded', userDeBoarded);
    $(document).on('click', '#completetrip', tripComplete);
    $(document).on('click', '.start-trip', startTrip);
	$(document).on('click', '#trackrtip', naviagatetoTrack);
    function sosDetailspage(){
        $.get( "http://localhost:9091/sosdetails/"+this.id, function(data) {
              renderSosDetailspage(data);
           });   
    }
	
	function renderSosDetailspage(data){
        var sosHtml= '';
		    sosHtml +='<div class="col-md-6 col-md-offset-3 col-xs-10 col-xs-offset-1 sosdetailspage" >';
            sosHtml += "<h3 class='text-center'>SOS</h3>";
            sosHtml += "<p><b>Phone Number</b> :"+data.phonenumber+"</p>";
            sosHtml += "<p><b>Route</b> :"+data.routeid+"</p>";
            sosHtml += "<p><b>User Name</b> :"+data.username+"</p>";
            sosHtml += "<p><b>Shift</b> :"+data.shift+"</p>";
            sosHtml +='<div class="col-md-12 col-xs-12" >';            

            sosHtml += "</div>";
            sosHtml += "</div>";
        
            $("#pageview").html(sosHtml); 
	}
	function naviagatetoTrack()
	{
      $.get( "templates/track.html", function(data) {
          $("#pageview").html(data);
          });
	}
	
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
            html += '<th colspan="5" style="text-align:center">SOS lists</th>';
            html += '</tr>';
            html += '<tr>';
            html += '<th>Raised At</th>';
            html += '<th>Raised On</th>';
            html += '<th>Route Number</th>';
            html += '<th>User Name</th>';
            html += '<th>Start Time</th>';
            html += '</tr>'
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
            html += '<table class="table table-bordered table-responsive" id="routelisttable">';
            html += '<tr>';
            html += '<th colspan="5" style="text-align:center">Route lists</th>';
            html += '</tr>';
            html += '<tr>';
            html += '<th>Route Number</th>';
            html += '<th>Shift</th>';
            html += '<th>Delayed By</th>';
            html += '<th>Vehicle Number</th>';
            html += '<th>Start Time</th>';
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
                console.log(getRoutedata)
               AdminData(data);
               
           });      
    
    }
    
  
    function AdminData(data){
        console.log(data);
        var html= '';
            html += "<div class='col-md-12'>";
            html += "<div class='col-md-6 col-md-6 col-xs-6 col-sm-6'>";
            html += '<p><b> Route Number:</b><span id="usrname">'+data[0].routeNumber+'</span></p>';
            html += '<p><b> Name: </b><span id="usrname">'+data[0].userName+'</span></p>';
            html += "</div>";
            html += "<div class='col-md-6 col-xs-6 col-sm-6'>";
            html += '<button class="btn btn-default sos-btn pull-right" type="submit">SOS</button>';
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
            html += '<div class="row">';
            html += '<div class="col-md-12 text-center" id="buttonContainer">';
            html += '<button class="btn btn-default start-trip" type="submit" id="startTrip">Start Trip</button>';
            html += '<button class="btn btn-default track-trip" id="trackrtip" type="submit" style="display:none; margin-right:50px">Track Trip</button>';
            html += '<button class="btn btn-default track-trip" type="submit" id="completetrip" style="display:none; margin-left:50px">Complete Trip</button>';
            html += '</div>';
            html += '</div>';
         $("#pageview").html(html); 
    }
    
    function startTrip(){
        
        $.get("http://localhost:9091/board/"+getRoutedata[0].userName+"/"+getRoutedata[0].routeId, function(data) {
            if(data == true){
                localStorage.setItem("routeID", getRoutedata[0].routeId);
                $("#content").html("Trip has started");
              $("#alertModal").modal('show');
                $(".track-trip").show();
                $(".start-trip").hide();
            }
           });     
    }
    function tripComplete(){
        $.get("http://localhost:9091/board/"+getRoutedata[0].userName+"/"+getRoutedata[0].routeId, function(data) {
            if(data == true){
               $("#content").html("Trip has completed");
              $("#alertModal").modal('show');
                $(".track-trip").show();
                $(".start-trip").hide();
            }
           });     
    }
    function userBoarded(){
        //$(".boarded").prop("checked", true);
        var hetUsername = $(".deboarded").attr("data-attr");
        debugger;
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
        $.get( "http://localhost:9091/board/"+hetUsername+"/"+getRoutedata[0].routeId, function(data) {
            if(data == true){
                $("#content").html("User have deboarded");
              $("#alertModal").modal('show');
                $(".deboarded").prop("checked", true).attr("disabled", true);
          }
     });
    }
    
});