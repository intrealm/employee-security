$( document ).ready(function() {  
    
        openPage("../templates/signin.html", '');
        $(document).on('click','.btn', login);
    
        function login(){
            var myform = document.getElementById("signin");
                var fd = new FormData(myform );
                $.ajax({
                    url: "http://localhost:9091/login",
                    data: fd,
                    cache: false,
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    success: function (dataofconfirm) {
                       debugger;
                        if(typeof dataofconfirm.roleId !== undefined && typeof dataofconfirm.roleId !== null){
                             openPage('templates/route.html', dataofconfirm)
                        }else{
                            alert("Unable to login")
                        }
                          
                    }
                });
        }
 
      function openPage(url, loginData){
           $.ajax({
                url: url,
                type: 'GET',
                success: function (data) {
                    $("#pageview").html(data);
                    console.log(loginData);
                }
            });

       }
});