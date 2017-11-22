var webSocket;
var convo = $("#convo ul");

var message;
var username;
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
    if (src === username) {
        convo.append("<li class='me'>" + message + "</li>");

    } else {

        convo.append("<li class='other'>" + message + "</li>");
    }
}

function openSocket() {
    if (window.WebSocket) {
        console.log('Your browser does support websockets!');
        webSocket = new WebSocket("ws://localhost:8080/DitchDiscord/echo"); //verander naar ip van server wanneer je remote wil connecten


        webSocket.onopen = function (event) {



            //send(username, username + " connected");

        };
        webSocket.onmessage = function (event) {
            var data = JSON.parse(event.data);
            console.log(data);
            if (init) {
                username = data.username;
                init = false;
            }

            addToConversation(data.username + ": " + data.message, data.username);
            $("#user").html(username);
            $("#usr").html(username);
            $("#profile").attr("src", "assets/images/" + username + ".png");


        };
        webSocket.onclose = function (event) {


        };


    } else {
        alert("We're sorry, your browser does not support websockets");
    }
}


$(document).ready(function () {
    openSocket();

    //need fix. displays image enkel na een paar keer reladen AJAX call fix




    





    $("#chat").submit(function (e) {
        e.preventDefault();
        message = preventJSInjection($("#mess").val());
        setTimeout(function () {
            if (message.replace(/\s+/g, "") !== "" && message.length<100) {

                addToConversation(username + ": " + message, username);

                send(username, message);
                $("#mess").val("");
            }
        }, 500)
        
        
    });



}
)
