function sendAjaxGet(url, func) {
    let xhr = new XMLHttpRequest() || new ActiveObject("Microsoft.HTTPRquest");
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            func(this);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

sendAjaxGet("http://localhost:8082/SealsProject1/managerhome", display);

function display(xhr) {
    let employees = JSON.parse(xhr.responseText);
    let employeeArr = employees.employees;
    let table = document.getElementById("table");
    for (i in employeeArr) {
        let newRow = document.createElement("tr");
        newRow.innerHTML = `
        <td>${employeeArr[i].fname} ${employeeArr[i].lname}</td>
        <td>${employeeArr[i].username}</td>
        <td>${employeeArr[i].id}</td>`;
       
        table.appendChild(newRow);
    }
}