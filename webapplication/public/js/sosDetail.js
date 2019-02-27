$( document ).ready(function() {
    var getSosId = localStorage.getItem("sosId");
        var SosData;
    $.get( "http://localhost:9091/sosdetails/"+getSosId, function(data) {
              SosData = data;
              renderSosDetailspage(data);
           }); 
 });
   
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
            $("#sostable").html(sosHtml); 
	}

function propogateOnMapSoS() {
     var coordinatesdata;
     $.get("http://localhost:9091/getCoordinates/"+localStorage.getItem("routeID"), function(data) {
         coordinatesdata = data;
         debugger;
         var mapProp= {
      center:new google.maps.LatLng(coordinatesdata.latitude, coordinatesdata.longitude),
      zoom:17,
      disableDefaultUI: true,
      draggable:false
    };
    var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';

    var map = new google.maps.Map(document.getElementById("googleMapSos"),mapProp);

    var marker = new google.maps.Marker({position: mapProp.center, icon: iconBase+'cabs.png'});

    marker.setMap(map);
                 }); 

}