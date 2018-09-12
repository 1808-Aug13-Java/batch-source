// POPULATING ALL EMPLOYEES AND EMPLOYEES SECTION
// OF MANAGER HOMEPAGE
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

const employeeUrl = "http://localhost:8082/p1-crandon-riordan/employee";
document.addEventListener("DOMContentLoaded", function() {
  
  
  sendAjaxGet(employeeUrl, populateEmployees);

  let employeeNavs = document.getElementsByClassName("empNavItem");
  let employeeTables = document.getElementsByClassName("employeeTable");
  for (let item of employeeNavs) {
    item.addEventListener("click", function () {

      // toggle style of nav tab
      toggleActive(this, employeeNavs);
      toggleDisplayOfTables(this, employeeTables);
    });
  }

  function toggleActive(navItem, navItems) {
    for (let item of navItems) {
      item.classList.remove("activeTab");
      if (item == navItem) {
        navItem.classList.add("activeTab");
      }
    }
  }

  function toggleDisplayOfTables(navItem, tables) {
    let tableToTurnOn;
    let indexOfTable;
    switch(navItem.innerHTML.toLowerCase()) {
      // bug fix for spaces
      case "all employees":
        tableToTurnOn = tables[0];
        break;
      case "reimbursments by employee":
        tableToTurnOn = tables[1];
    }

    for(let table of tables) {
      table.classList.remove("doDisplay");
      table.classList.add("noDisplay");
      if(table == tableToTurnOn) {
        table.classList.remove("noDisplay");
        table.classList.add("doDisplay");
      }
    }
  }

  function populateEmployees(xhr) {
    let response = JSON.parse(xhr.responseText);
    let empTable = document.getElementById("tbodyEmployees");
    let employees = response.employees;

    for(employee of employees) {
      let trEl = document.createElement("tr");
      trEl.innerHTML = `
        <td>${employee.id}</td>
        <td>${employee.name}</td>
        <td>${employee.email}</td>
        <td>${employee.isManager ? 'Yes' : 'No'}</td>
      `;

      empTable.appendChild(trEl);
    }

  }

  let searchBtn = document.getElementById("search");
  let tbody = document.getElementById("tbodyById");
  searchBtn.addEventListener("click", function() {
    tbody.innerHTML = "";
    let employeeId = document.getElementById("employeeId");
    if(employeeId.value == "") {
      console.log("invalid value");
      return;
    }
    let url = `
    ${reimbursmentUrl}?employeeId=${employeeId.value}
  `;
    sendAjaxGet(url, populateReimbursmentsById);
  });

  function populateReimbursmentsById(xhr) {
    
    let response = JSON.parse(xhr.responseText);
    let reimbursments = response.reimbursments;

    if(reimbursments.length == 0) {
      showPopupEmp();
    }

    for(let reimbursment of reimbursments) {
      let trEl = document.createElement("tr");
      trEl.innerHTML = `
        <td>${reimbursment.reimbursmentId}</td>
        <td>${reimbursment.employee.name}</td>
        <td>${reimbursment.reason}</td>
        <td>$${reimbursment.amount}</td>
        <td>${reimbursment.currentState}</td>
        <td>${reimbursment.manager == null ? "N/A" : reimbursment.manager.name}</td>
        <td>${new Date(reimbursment.dateCreated)
          .toLocaleDateString()}</td>
      
      `;

      tbody.appendChild(trEl);
      unshowPopupEmp();
    }

  }

  
  let alert = document.getElementById("employeeAlert");
  function showPopupEmp() {
    alert.classList.remove("noDisplay");
    alert.classList.add("doDisplay");
  }

  function unshowPopupEmp() {
    alert.classList.remove("doDisplay");
    alert.classList.add("noDisplay");
  }

  

});