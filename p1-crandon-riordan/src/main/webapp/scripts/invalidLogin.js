// used to check for invalid login on session and dynamically change the page if so.

document.addEventListener("DOMContentLoaded", function(){

  function displayInvalidUser() {
    let formContainer = document.getElementById("form");
    formContainer.innerHTML += `
      <div class="alert alert-warning" role="alert">
        Invalid username or password
      </div>
    `;
  }

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

  function invalidLoginCheck(xhr) {
    let response = JSON.parse(xhr.responseText);
    if(response.login) {
      displayInvalidUser();
    }
  }

  sendAjaxGet(isUserUrl, invalidLoginCheck);
});