var webSocket;
var convo = $("#convo ul");

var message;
var username;

function send() {

    message = preventJSInjection($("#mess").val());
    var jsondata = {

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


$(document).ready(function () {
    if (window.WebSocket) {
        console.log('Your browser does support websockets!');
        webSocket = new WebSocket("ws://localhost:8080/DitchDiscord/echo"); //verander naar ip van server wanneer je remote wil connecten


        webSocket.onopen = function (event) {

            console.log("this shit works");

        };
        webSocket.onmessage = function (event) {
            var data = JSON.parse(event.data);

            username = data.username;

            message = data.message;

            addToConversation(username + ": " + message, username)


        };
        webSocket.onclose = function (event) {


        };


    } else {
        alert("We're sorry, your browser does not support websockets");
    }



    $("form").submit(function (e) {
        e.preventDefault();
        message = preventJSInjection($("#mess").val());
        addToConversation("Me: " + message, "me");
        send();
        $("#mess").val("");
    });

}
)
