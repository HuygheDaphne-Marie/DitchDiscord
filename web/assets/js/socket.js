var webSocket;
var messages = document.getElementById("showMessages");
var username;
var text;



function openSocket(e) {
  e.preventDefault();


  webSocket = new WebSocket("ws://localhost:8080/ditchdiscord/echo"); //verander naar ip van server wanneer je remote wil connecten


  webSocket.onopen = function(event) {
    //username = preventJSInjection(document.getElementById("user").value);
    console.log("this shit works");
    /*text = "Connected";
    var jsondata = {
        //"username": username,
        "message": text
    };
    var package = JSON.stringify(jsondata);

    webSocket.send(package);

    if (event.data === undefined)
        return;

    writeResponse(event.data);*/
  };

  webSocket.onmessage = function(event) {
    writeResponse(event.data);
  };

  webSocket.onclose = function(event) {
    writeResponse(event.data);
  };
}

function send(e) {
  e.preventDefault();

  text = preventJSInjection(document.getElementById("mess").value);
  var jsondata = {
    //"username": username,
    "message": text
  };
  var package = JSON.stringify(jsondata);

  webSocket.send(package);
  console.log("test");
}

function closeSocket(e) {
  e.preventDefault();
  webSocket.close();
}

function writeResponse(text) {
  messages.innerHTML += "<br/>" + text;
}

$(document).ready(function () {
  if (window.WebSocket) {
    console.log('Your browser does support websockets!')
  } else {
    alert("We're sorry, your browser does not support websockets")
  }
  $("#open").on("click", openSocket);
  $("#send").on("click", send);
  $("#close").on("click", closeSocket);
})
