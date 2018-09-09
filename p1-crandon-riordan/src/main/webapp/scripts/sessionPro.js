
document.addEventListener("DOMContentLoaded", function(){
  const isUserUrl = "http://localhost:8082/p1-crandon-riordan/session";
  const employeeUrl = "http://localhost:8082/p1-crandon-riordan/employee";
  let sessionId;
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
    //display user info with session info
    displayUserInfo(response);

    let email = document.getElementById("email");
    email.innerHTML = response.email;
    sessionId = response.id;
    // display info of current employee
    let navLinks = document.getElementsByClassName("nav-link");

    if(response.isManager && navLinks[1]) {
      navLinks[1].setAttribute("href", "managerHome");
      navLinks[1].innerHTML = "Manager Dash";
    }
  }

  function displayUserInfo(employee) {
    let email = document.getElementById("emailInput");
    let name = document.getElementById("name");
    let username = document.getElementById("username");
    // check for null employee
    if(employee) {
      email.value = employee.email;
      name.value = employee.name;
      username.value = employee.username;
    } else {
      email.value = "ERR";
      name.value = "ERR";
      username.value= "ERR";
    }
    
  }

  sendAjaxGet(isUserUrl, validateUser);

});