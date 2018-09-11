// VALIDATING USER, POPULATING REIMBURSMENTS TABLE

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

const isUserUrl = "http://localhost:8082/p1-crandon-riordan/session";
const reimbursmentUrl = "http://localhost:8082/p1-crandon-riordan/reimbursment";


function populatPendingReimbursments(xhr) {
  let response = JSON.parse(xhr.responseText);
  let reimbursments = response.reimbursments;

  let table = document.getElementById("tbodyPending");
  table.innerHTML = "";
  for (let reimbursment of reimbursments) {
    let trEl = document.createElement("tr");
    trEl.innerHTML = `
    <td>${reimbursment.reimbursmentId}</td>
    <td>${reimbursment.employeeId}</td>
    <td>${reimbursment.employee.name}</td>
    <td>${reimbursment.reason}</td>
    <td>$${reimbursment.amount}</td>
    <td>${reimbursment.currentState.toUpperCase()}</td>
    <td>${new Date(reimbursment.dateCreated).toLocaleDateString()}</td>
    <td>${reimbursment.manager != undefined ? reimbursment.manager.name : "N/A"}</td>
    `;

    if (reimbursment.currentState.toLowerCase() == "pending") {
      trEl.innerHTML += `
      <td>
      <i onclick="approveRequest(event); " class="material-icons pointer approve">
        done
      </i>
    </td>
    <td>
      <i onclick="denyRequest(event);" class="material-icons pointer deny">
        delete_outline
      </i>
    </td>
      `;
    }

    table.appendChild(trEl);
  }
}

function populateResolvedReimbursments(xhr) {
  let response = JSON.parse(xhr.responseText);
  let reimbursments = response.reimbursments;

  let table = document.getElementById("tbodyResolved");
  table.innerHTML = "";
  for (let reimbursment of reimbursments) {
    let trEl = document.createElement("tr");
    trEl.innerHTML = `
    <td>${reimbursment.reimbursmentId}</td>
    <td>${reimbursment.employeeId}</td>
    <td>${reimbursment.employee.name}</td>
    <td>${reimbursment.reason}</td>
    <td>$${reimbursment.amount}</td>
    <td>${reimbursment.currentState.toUpperCase()}</td>
    <td>${new Date(reimbursment.dateCreated).toLocaleDateString()}</td>
    <td>${reimbursment.manager != undefined ? reimbursment.manager.name : "N/A"}</td>
    `;

    if (reimbursment.currentState.toLowerCase() == "pending") {
      trEl.innerHTML += `
        <td>
          <i onclick="approveRequest(event)" class="material-icons pointer approve">
            done
          </i>
        </td>
        <td>
          <i onclick="denyRequest(event)" class="material-icons pointer deny">
            delete_outline
          </i>
        </td>
      `;
    }

    table.appendChild(trEl);
  }
}


document.addEventListener("DOMContentLoaded", function () {
  

  

  function validateUser(xhr) {
    console.log(xhr);
    let response = JSON.parse(xhr.responseText);
    // if they aren't logged in or logged in as employee
    // redirect
    if (response == null) {
      window.location = "http://localhost:8082/p1-crandon-riordan/login";
    } else if (response.email == null) {
      window.location = "http://localhost:8082/p1-crandon-riordan/login";
    } else {
      let emailEl = document.getElementById("email");
      emailEl.innerHTML = response.email;
      sendAjaxGet(reimbursmentUrl, populateReimbursments);
    }




  }


  function populateReimbursments(xhr) {

    let response = JSON.parse(xhr.responseText);
    let reimbursments = response.reimbursments;

    let table = document.getElementById("tbodyAll");
    table.innerHTML = "";

    for (let reimbursment of reimbursments) {
      let trEl = document.createElement("tr");
      trEl.innerHTML = `
      <td>${reimbursment.reimbursmentId}</td>
      <td>${reimbursment.employeeId}</td>
      <td>${reimbursment.employee.name}</td>
      <td>${reimbursment.reason}</td>
      <td>$${reimbursment.amount}</td>
      <td>${reimbursment.currentState.toUpperCase()}</td>
      <td>${new Date(reimbursment.dateCreated).toLocaleDateString()}</td>
      <td>${reimbursment.manager != undefined ? reimbursment.manager.name : "N/A"}</td>
      `;

      if (reimbursment.currentState.toLowerCase() == "pending") {
        trEl.innerHTML += `
        <td>
        <i onclick="approveRequest(event); " class="material-icons pointer approve">
          done
        </i>
      </td>
      <td>
        <i onclick="denyRequest(event); " class="material-icons pointer deny">
          delete_outline
        </i>
      </td>
        `;
      }

      table.appendChild(trEl);
    }
  };

  



  sendAjaxGet(isUserUrl, validateUser);

  // AJAX event listenrs on click of tabs
  let reimbursmentNavs = document.getElementsByClassName("tabsNavItem");
  let reimbursmentDivs = document.getElementsByClassName("reimbursmentTable");

  // send ajax request for all reimbursments
  reimbursmentNavs[0].addEventListener("click", function () {
    sendAjaxGet(reimbursmentUrl, populateReimbursments);
  });

  // pending reimbursments
  reimbursmentNavs[1].addEventListener("click", function () {
    sendAjaxGet(reimbursmentUrl + "?currentState=pending", populatPendingReimbursments);
  })

  // resolved reimbursments
  reimbursmentNavs[1].addEventListener("click", function () {
    sendAjaxGet(reimbursmentUrl + "?currentState=resolved", populateResolvedReimbursments);
  });


  



});


function approveRequest(event) {
  let reimbursmentId = event
      .target
      .parentNode.parentNode.childNodes[1].innerHTML;
  // remove all data and wait for response
  event.target.parentNode.parentNode.parentNode.innerHTML = "";
  event.target.parentNode.parentNode.style = "display: none";
  sendAjaxGet((reimbursmentUrl + 
    "?action=approve&reimbursmentId=" + 
    reimbursmentId), function() {
      console.log("approving request");
      sendAjaxGet(reimbursmentUrl, populateReimbursments);
    });
}

function denyRequest(event) {
  let reimbursmentId = event
      .target
      .parentNode.parentNode.childNodes[1].innerHTML;
  event.target.parentNode.parentNode.style = "display: none";
  sendAjaxGet((reimbursmentUrl + 
    "?action=deny&reimbursmentId=" + 
    reimbursmentId), function() {
      console.log("deny request");
  });
}