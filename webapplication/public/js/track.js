
function propogateOnMap() {
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

var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

var marker = new google.maps.Marker({position: mapProp.center,
icon: iconBase+'cabs.png'});

marker.setMap(map);
             }); 

}



