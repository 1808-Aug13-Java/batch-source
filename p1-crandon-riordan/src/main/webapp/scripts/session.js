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
    if(response == null || response.email == null) {
      window.location="http://localhost:8082/p1-crandon-riordan/login";
    }
  }

  sendAjaxGet(isUserUrl, validateUser);
});