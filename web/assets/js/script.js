var preventJSInjection = function(text) {
  var safeText = text.replace(/</g, "&lt;").replace(/>/g, "&gt;");
  return safeText;
};

function checkIfPasswordsMatch(e) {
  e.preventDefault();
  if (!passwordsMatch()) {
    $("main div form button").attr('disabled', 'disabled');
  } else {
    $('main div form button').prop("disabled", false);
  }
};

function passwordsMatch() {
  return ($("#password").val() === $("#passwordCheck").val());
};

(function() {
  // Init's
  $(".button-collapse").sideNav();

  if (window.WebSocket) {
    console.log('Your browser does support websockets!')
  } else {
    alert("We're sorry, your browser does not support websockets")
  }
  $("#open").on("click", openSocket);
  $("#send").on("click", send);
  $("#close").on("click", closeSocket);
  $("#passwordCheck").on("change", checkIfPasswordsMatch);
})();
