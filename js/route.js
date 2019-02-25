$( document ).ready(function() {
    $.get( "http://localhost:9091/displayRoute/shipuri ", function(data) {
            console.log(data)
    });
});