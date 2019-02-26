$( document ).ready(function() {  
        var roleid;
        openPage("../templates/signin.html", '');
        $(document).on('click','.btn-block', login);
    
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
                        if(dataofconfirm.roleId){
                           localStorage.setItem("roleid", dataofconfirm.roleId);
                           }
                        if(dataofconfirm.roleId == "user"){
                            openPage('templates/route.html', dataofconfirm);
                        }else if(dataofconfirm.roleId == "admin"){
                            openPage('templates/routelist.html', dataofconfirm);
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
                  
                }
            });

       }
});