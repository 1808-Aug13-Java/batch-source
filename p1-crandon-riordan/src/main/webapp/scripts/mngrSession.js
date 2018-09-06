document.addEventListener("DOMContentLoaded", function(){
  const isUserUrl = "http://localhost:8082/p1-crandon-riordan/session";

  function sendAjaxGet(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
      if(this.readyState == 4 && this.status == 200) {
        callback(this);
      }
    }

    xhr.open("get", url);
    xhr.send();
  }

  function validateUser(xhr) {
    let response = JSON.parse(xhr.responseText);
    // if they aren't logged in or logged in as employee
    // redirect
    if((response.isManager == 0) || (response == null || response.email == null)) {
      window.location="http://localhost:8082/p1-crandon-riordan/login";
    } else {
      let emailEl = document.getElementById("email");
      emailEl.innerHTML = response.email;
    }
  }

  sendAjaxGet(isUserUrl, validateUser);
});