$( document ).ready(function() {
    $.get( "http://localhost:9091/allActiveRoutes", function(data) {
            renderROuteList(data);
    });
    
    function renderROuteList(data){
        
        userData(data);
    }
    
    function userData(data){
        console.log(data);
        var html= '';
           
            html += "<div class='col-md-12'>";
            html += '<table class="table table-bordered">';
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
            html += '<tr id='+value.id+' style="cursor:pointer">';
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
});