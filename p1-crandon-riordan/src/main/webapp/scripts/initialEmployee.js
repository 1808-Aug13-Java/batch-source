let sessionId;
const isUserUrl = "http://localhost:8082/p1-crandon-riordan/session";

function validateUser(xhr) {
  let response = JSON.parse(xhr.responseText);
  if (response == null || response.email == null) {
    window.location = "http://localhost:8082/p1-crandon-riordan/login";
  }

  let email = document.getElementById("email");
  email.innerHTML = response.email;

  sessionId = response.id;

  let initUrl = `${reimbursmentUrl}?employeeId=${sessionId}&currentState=pending`;
  sendAjaxGet(initUrl, populateTable);
}

function populateTable(xhr) {
  let tbody = document.getElementById("tbody");

  let response = JSON.parse(xhr.responseText);
  let reimbursments = response.reimbursments;

  for (let reimbursment of reimbursments) {
    let trEl = document.createElement("tr");

    trEl.innerHTML = `
      <td>${reimbursment.reimbursmentId}</td>
      <td>${reimbursment.reason}</td>
      <td>$${reimbursment.amount}</td>
      <td>${reimbursment.currentState}</td>
      <td>${new Date(reimbursment.dateCreated).toLocaleDateString()}</td>
      <td>${reimbursment.manager ? reimbursment.manager.name : "N/A"}</td>
    `;

    tbody.appendChild(trEl);
  }


}


// Sets up the intial page for employee home page
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

// validating user and initializing sessionId var 
// for use in later AJAX calls
sendAjaxGet(isUserUrl, validateUser);

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
const reimbursmentUrl = "http://localhost:8082/p1-crandon-riordan/reimbursment";

document.addEventListener("DOMContentLoaded", function () {
  //prevent default submission on submit
  let submit = document.getElementById("submit");
  let reqAlert = document.getElementById("reqAlert");
  submit.addEventListener("click", function (e) {
    e.preventDefault();
    let amount = document.getElementById("amount");
    let reason = document.getElementById("reason");
    if ((amount.value < 0 || amount.value == "") || reason.value.length < 6) {
      addInvalidAlert(reqAlert);
      return;
    } else {
      removeInvalidAlert(reqAlert);
      let formData = `amount=${amount.value}&reason=${reason.value}`;
      amount.value = "";
      reason.value = "";
      sendAjaxPost(reimbursmentUrl, function () {
        showAlert();
        // repopulate the currently selected tab
        let selectedTab = document.getElementsByClassName("activeTab")[0];
        if (selectedTab.innerHTML.toLowerCase() == "pending") {
          populatePending();
        } else if (selectedTab.innerHTML.toLowerCase() == "resolved") {
          populatedResolved();
        }
      }, formData);
      return;
    }


  });

  // Populate pending tab already


  function showAlert() {
    let submitAlert = document.getElementById("submitAlert");
    submitAlert.classList.remove("noDisplay");
    submitAlert.classList.add("doDisplay");
    // hide after 2secs
    setTimeout(function () {
      submitAlert.classList.remove("doDisplay");
      submitAlert.classList.add("noDisplay");
    }, 3000);
  }
  // for user input validation alerts

  function addInvalidAlert(el) {
    el.classList.remove("noDisplay");
    el.classList.add("doDisplay");
  }

  function removeInvalidAlert(el) {
    el.classList.remove("doDisplay");
    el.classList.add("noDisplay");
  }

  // Event listeners for pending/resolved reimbursments
  let navTabs = document.getElementsByClassName("rNav");
  // pending populate table
  navTabs[0].addEventListener("click", populatePending);

  navTabs[1].addEventListener("click", populateResolved);

  function populatePending() {
    tbody.innerHTML = "";
    navTabs[1].classList.remove("activeTab");
    navTabs[0].classList.add("activeTab");
    tbody.innerHTML = "";
    let url = `${reimbursmentUrl}?employeeId=${sessionId}&currentState=pending`;
    sendAjaxGet(url, populateTable);
  }

  function populateResolved() {
    tbody.innerHTML = "";
    navTabs[0].classList.remove("activeTab");
    navTabs[1].classList.add("activeTab");
    let url = `${reimbursmentUrl}?employeeId=${sessionId}&currentState=resolved
    `;
    sendAjaxGet(url, populateTable);
  }




});