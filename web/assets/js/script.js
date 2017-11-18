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

$(document).ready(function () {
  // Init's
  $(".button-collapse").sideNav();

  $("#passwordCheck").on("change", checkIfPasswordsMatch);
  
})
