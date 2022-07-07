$(document).ready(function (){

    $("#loginForm").submit(sendLogin());

    $("logoff").click(sendLogoff());
})

function sendLogin(form){
    form.preventDefault();
    $
        .ajax({
            url:"./resource/user/login",
            dataType: "text",
            type: "POST",
            data: $("#loginFrom").serialize()
        })
        .done(function (){
            window.location.href = "./kino.html";
        })
        .fail(function(xhr, status, errorThrown) {
            if(xhr.status = 404) {
            $("#message").text("Username/Password unknown")
            } else {
                $("#message").text("A error has popped off")
            }
        })
}
function sendLogoff() {
    form.preventDefault();
    $
        .ajax({
            url:"./resource/user/logoff",
            dataType: "text",
            type: "DELETE",
            data: $("#loginFrom").serialize()
        })
        .done(function (){
            window.location.href = "./login.html";
        })
        .fail(function(xhr, status, errorThrown) {
        })
}