var preventJSInjection = function(text) {
  var safeText = text.replace(/</g, "&lt;").replace(/>/g, "&gt;");
  return safeText;
};

function checkIfPasswordsMatch(e) {
  e.preventDefault();
  if (!passwordsMatch()) {
    $("main form button").attr('disabled', 'disabled');
  } else {
    $('main form button').prop("disabled", false);
  }
};

function passwordsMatch() {
  return ($("#password").val() === $("#passwordCheck").val());
};

$(document).ready(function () {
  // Init's
  $(".button-collapse").sideNav();
  $('select').material_select();

  $("#passwordCheck").on("change", checkIfPasswordsMatch);
  $("#complaintform").on("submit",function(){
        //e.preventDefault();
        preventJSInjection($("#complaint").val());
        console.log(preventJSInjection($("#complaint").val()));
        
  });
});
