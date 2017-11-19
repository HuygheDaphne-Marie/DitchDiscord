var usernameAJAX;

function getAndShowUsername() {
  $.ajax({url: "getUsername", success: function(result){
    console.log(result);
    if($("#user").length != 0) {
      $("#user").html(result);
      usernameAJAX=result;
    }

    if($(".user-view p").length != 0) {
      $(".user-view p").html(result);
    }
  }});
}

$(document).ready(function () {
  getAndShowUsername();
})
