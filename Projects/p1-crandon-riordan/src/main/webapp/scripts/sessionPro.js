document.addEventListener("DOMContentLoaded", function () {
  const isUserUrl = "http://localhost:8082/p1-crandon-riordan/session";
  const employeeUrl = "http://localhost:8082/p1-crandon-riordan/employee";
  let sessionId;

  function sendAjaxGet(url, callback) {
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
      if (this.readyState == 4 && this.status == 200) {
        callback(this);
      }
    }

    xhr.open("get", url);
    xhr.send();
  }
  // data will be like: var=x&var1=y&var2=xx etc..
  function sendAjaxPost(url, callback, data) {
    let xhr = new XMLHttpRequest();

    xhr.onload = function () {
      if (this.readyState == 4 && this.status == 200) {
        callback(this);
      }
    }

    xhr.open("POST", url);
    xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xhr.send(data);
  }

  function validateUser(xhr) {
    let response = JSON.parse(xhr.responseText);
    if (response == null || response.email == null) {
      window.location = "http://localhost:8082/p1-crandon-riordan/login";
    }
    //display user info with session info
    displayUserInfo(response);

    let email = document.getElementById("email");
    email.innerHTML = response.email;
    sessionId = response.id;
    // display info of current employee
    let navLinks = document.getElementsByClassName("nav-link");

    if (response.isManager && navLinks[1]) {
      navLinks[1].setAttribute("href", "managerHome");
      navLinks[1].innerHTML = "Manager Dash";
    }
  }

  function displayUserInfo(employee) {
    let email = document.getElementById("emailInput");
    let name = document.getElementById("name");
    let username = document.getElementById("username");
    // check for null employee
    if (employee) {
      email.value = employee.email;
      name.value = employee.name;
      username.value = employee.username;
    } else {
      email.value = "ERR";
      name.value = "ERR";
      username.value = "ERR";
    }

  }

  sendAjaxGet(isUserUrl, validateUser);

  let editBtn = document.getElementById("edit");
  // enable/disable form
  editBtn.addEventListener("click", function () {
    let fieldSet = document.getElementById("fSet");

    fieldSet.disabled = !fieldSet.disabled;
    if (fieldSet) {
      editBtn.innerHTML = "View";
    } else {
      editBtn.innerHTML = "Edit";
    }
  });


  let submitBtn = document.getElementById("submit");
  // submit form w/out redirecting
  submitBtn.addEventListener("click", function (e) {
    // updating user info
    e.preventDefault();
    let name = document.getElementById("name");
    let username = document.getElementById("username");
    let email = document.getElementById("emailInput");
    let password = document.getElementById("password");

    if(!checkInputs()) {
      let data = `name=${name.value}&username=${username.value}&email=${email.value}&password=${password.value}`;
      console.log(data);
      
      sendAjaxPost(employeeUrl, function(xhr) {
        if(xhr.responseText == "null") {
          toggleBadAlert("Server side failure of request");
        } else {
          toggleGoodAlert();
        }
      }, data)
    }

  });


  // check all of the inputs
  function checkInputs() {
    let email = document.getElementById("emailInput");
    let numWrong;
    if (!validateEmail(email.value)) {
      numWrong++;
      toggleBadAlert("Enter a correct email");
    }
    let name = document.getElementById("name");
    if (name.value.length > 25 || name.value.length < 5) {
      numWrong++;
      toggleBadAlert("name too long/short");
    }
    let username = document.getElementById("username");
    if (username.value.length < 6) {
      numWrong++;
      toggleBadAlert("Username needs to be greater than 5 characters");
    }
    let password = document.getElementById("password");
    if (password.value.length < 7 || password.value.length > 15) {
      numWrong++;
      // if empty, keep same 
      if(password.value.length == 0) {
        numWrong--;
      } else {
        toggleBadAlert("Enter a password with 7-15 characters");
      }
      
    }

    return numWrong;
  }


  function toggleBadAlert(message) {
    let alert = document.getElementById("bad");
    alert.innerHTML = message
    alert.classList.remove("noDisplay")
    alert.classList.add("doDisplay");
    setTimeout(function () {
      let alert = document.getElementById("bad");
      alert.classList.remove("doDisplay");
      alert.classList.add("noDisplay");
    }, 4000)
  }

  function toggleGoodAlert() {
    let alert = document.getElementById("good");
    alert.classList.toggle("noDisplay");
    setTimeout(function () {
      let alert = document.getElementById("good");
      if (!alert.classList.contains("noDisplay")) {
        alert.classList.toggle("noDisplay");
      }
    }, 4000)
  }

  function validateEmail(email) {
    //client side validation
    var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
  }

});