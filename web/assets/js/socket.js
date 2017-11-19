var webSocket;
var convo = $("#convo ul");

var message;
var username = "nothing";
var init = true;

function send(user, mess) {

    message = mess;
    username = user;
    var jsondata = {
        "username": username,
        "message": message
    };
    var package = JSON.stringify(jsondata);
    webSocket.send(package);
    console.log(package);
}

function addToConversation(message, src) {
    if (src === "me") {
        convo.append("<li class=" + src + ">" + message + "</li>");
    } else if (src === "server") {
        convo.append("<li class=" + src + ">" + message + "</li>");
    } else {
        convo.append("<li class='other'>" + message + "</li>");
    }
}

function openSocket() {
    if (window.WebSocket) {
        console.log('Your browser does support websockets!');
        webSocket = new WebSocket("ws://localhost:8080/DitchDiscord/echo"); //verander naar ip van server wanneer je remote wil connecten


        webSocket.onopen = function (event) {



            send(username, username + " connected");

        };
        webSocket.onmessage = function (event) {
            var data = JSON.parse(event.data);



            addToConversation(data.username + ": " + data.message, data.username)


        };
        webSocket.onclose = function (event) {


        };


    } else {
        alert("We're sorry, your browser does not support websockets");
    }
}


$(document).ajaxComplete(function () {
    if (init) {
        username = usernameAJAX;
        $("#usrname").attr("value", username);
        $("#profile").attr("src", "assets/images/" + username + ".png");//need fix. displays image enkel na een paar keer reladen AJAX call fix
        openSocket();
        init = false;
    }






    $("#chat").submit(function (e) {
        e.preventDefault();
        message = preventJSInjection($("#mess").val());
        addToConversation("Me: " + message, "me");
        send(username, message);
        $("#mess").val("");
    });



}
)
