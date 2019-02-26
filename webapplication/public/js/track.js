function propogateOnMap() {


	//fetch long and latitude from the microservice
var mapProp= {
  center:new google.maps.LatLng(28.535517,77.3910),
  zoom:17,
  disableDefaultUI: true,
  draggable:false
};
var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';

var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);

var marker = new google.maps.Marker({position: mapProp.center,
icon: iconBase+'cabs.png'});

marker.setMap(map);
}
