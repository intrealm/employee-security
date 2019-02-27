
var SosData;
$( document ).ready(function() {
    var getSosId = localStorage.getItem("sosId");
        
    $.get( "http://localhost:9091/sosdetails/"+getSosId, function(data) {
              SosData = data;
              renderSosDetailspage(data);
           }); 
 });
    var getSosId = localStorage.getItem("sosId");
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
            sosHtml += "<button id='resolveSOS'>Resolve SOS</button>";
            sosHtml += "</div>";
            $("#sostable").html(sosHtml); 
            propogateOnMapSoS();
	}

    $(document).on('click', '#resolveSOS', resolveSOSRequest);

   function resolveSOSRequest(){
        $.get("http://localhost:9091/resolveSOSRequest/"+getSosId, function(data) {
            if(data == true){
               $("#content").html("SOS Resolved");
              $("#alertModal").modal('show');
                $("#resolveSOS").hide();
            }
           });     
    }

function propogateOnMapSoS() {

console.log(SosData);

var mapProp= {
      center:new google.maps.LatLng(SosData.lat, SosData.long),
      zoom:17,
      disableDefaultUI: true,
      draggable:false
    };

    var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
var map = new google.maps.Map(document.getElementById("googleMapSos"),mapProp);

    var marker = new google.maps.Marker({position: mapProp.center, icon: iconBase+'cabs.png'});

    marker.setMap(map);
    

}
     
     
    