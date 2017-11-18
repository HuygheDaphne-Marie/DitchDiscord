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

function getAndShowUsername() {
  $.ajax({url: "getUsername", success: function(result){
    $("#user").html(result);
    if($(".user-view p").length != 0) {
      $(".user-view p").html(result);
    }
  }});
}

$(document).ready(function () {
  // Init's
  $(".button-collapse").sideNav();

  $("#passwordCheck").on("change", checkIfPasswordsMatch);
})
