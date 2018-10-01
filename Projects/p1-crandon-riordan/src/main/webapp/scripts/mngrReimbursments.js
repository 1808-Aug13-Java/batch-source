document.addEventListener("DOMContentLoaded", function () {
  let reimbursmentNavs = document.getElementsByClassName("tabsNavItem");
  let reimbursmentDivs = document.getElementsByClassName("reimbursmentTable");
  // add event listeners to nav items
  // event listener for toggling styling of nav items\
  for (let navItem of reimbursmentNavs) {
    navItem.addEventListener("click", function () {
      // toggle style of nav tab
      toggleActive(this, reimbursmentNavs);
      toggleDisplayOfTables(this, reimbursmentDivs);
    });
  }

  // toggle display of divs
  // remove corresponding noDisplay
  // ---- table display toggler
  function toggleDisplayOfTables(navItem, tables) {
    let tableToTurnOn;
    let indexOfTable;
    switch(navItem.innerHTML.toLowerCase()) {
      // bug fix for spaces
      case "all requests":
        tableToTurnOn = tables[0];
        indexOfTable = 0;
        break;
      case "pending":
        tableToTurnOn = tables[1];
        indexOfTable = 1;
        break;
      case "resolved":
        tableToTurnOn = tables[2];
        indexOfTable = 2;
        break;
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







  // event listener callbacks
  function toggleActive(navItem, navItems) {
    for (let item of navItems) {
      item.classList.remove("activeTab");
      if (item == navItem) {
        navItem.classList.add("activeTab");
      }
    }
  }

  
});