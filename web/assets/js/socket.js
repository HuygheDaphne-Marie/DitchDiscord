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
        convo.prepend("<li class=" + src + ">" + message + "</li>");
    } else if (src === "server") {
        convo.prepend("<li class=" + src + ">" + message + "</li>");
    } else {
        convo.prepend("<li class='other'>" + message + "</li>");
    }
}

function openSocket() {
    if (window.WebSocket) {
        console.log('Your browser does support websockets!');
        webSocket = new WebSocket("ws://192.168.30.14/echo"); //verander naar ip van server wanneer je remote wil connecten


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
        setTimeout(function () {
            if (message.replace(/\s+/g, "") !== "" && message.length < 100) {

                addToConversation(username + ": " + message, "me");

                send(username, message);
                $("#mess").val("");
            }
        }, 500)


    });



}
);







