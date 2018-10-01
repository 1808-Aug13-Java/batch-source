let managerId = document.getElementById("manager");

function disableManagerId() {
  managerId.disabled = true;
}

function enableManagerId() {
  managerId.disabled = false;
}

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

window.onload = function () {
  const url = "http://localhost:8082/p1-crandon-riordan/createEmployee";
  let submit = document.getElementById("submit");
  submit.addEventListener("click", function (e) {
    e.preventDefault();
  });

  submit.addEventListener("click", function () {
    // will POST form data 
    let name = document.getElementById("name");
    let username = document.getElementById("username");
    let email = document.getElementById("emailInput");
    let password = document.getElementById("password");
    let managerId = document.getElementById("manager");
    let isManager = document.querySelector("input[name='isManager']:checked");

    // if checking some validation
    if (name.value.length < 5 ||
      username.value.length < 5 ||
      password.value.length < 8) {
      displayAlert();
      return;
    } else {
      let data = `name=${name.value}&username=${username.value}`;
      data += `&email=${email.value}&password=${password.value}`;
      data += `&managerId=${managerId.value}&isManager=${isManager.value}`;

      // send data to employee creationg url
      sendAjaxPost(url, function (xhr) {
        displaySubmitAlert();
        clearEls(name, username, email, password, managerId, isManager);s
      }, data);
    }
  });

  function displayAlert() {
    // indicates an error on client side validation
    let alert = document.getElementsByClassName("alert");
    alert[1].classList.remove("noDisplay");
    alert[1].classList.add("doDisplay");
    setTimeout(function () {
      alert[1].classList.remove("doDisplay");
      alert[1].classList.add("noDisplay");
    }, 4000);
  }

  function displaySubmitAlert() {
    // indicates POST request went through
    let alert = document.getElementsByClassName("alert");
    alert[0].classList.remove("noDisplay");
    alert[0].classList.add("doDisplay");
    setTimeout(function () {
      alert[0].classList.remove("doDisplay");
      alert[0].classList.add("noDisplay");
    }, 4000);
  }

  function clearEls(...args) {
    // helper f(x) for clearing the values of forms
    for(arg of args) {
      arg.value = "";
    }
  }
};