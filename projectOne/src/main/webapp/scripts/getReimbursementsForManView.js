/**
 * 
 */

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

sendAjaxGet("http://localhost:8082/Project-1-jeremiah-laforge/GetReimbursmets", display);

function display(xhr) {
    let reimbursment = JSON.parse(xhr.responseText);
    console.log(reimbursment);
    let reimbursmentArr = reimbursment.reimbursment;
    let table = document.getElementById("rTable");
    let selector = document.getElementById("reimbSelector");
    



    for (i in reimbursmentArr) {
        let newRow = document.createElement("tr");
        let newOp = document.createElement("option");

        if (reimbursmentArr[i].resolvedBy != 0) {
            console.log("If is true: " + reimbursmentArr[i].resolvedBy);
            newRow.innerHTML = `<td>${reimbursmentArr[i].empId}</td>
            <td>${reimbursmentArr[i].status}</td>
            <td>${reimbursmentArr[i].description}</td>
             <td>${reimbursmentArr[i].managerName}</td>`;
            table.appendChild(newRow);
        } else {
            newOp.value = reimbursmentArr[i].reimbId;
            newOp.text = `${reimbursmentArr[i].empId} : ${reimbursmentArr[i].empName} : ${reimbursmentArr[i].description}`;

            // let innerHTML = `<option value="${reimbursmentArr[i].reimbId}" > ${reimbursmentArr[i].empId} : ${reimbursmentArr[i].empName} : ${reimbursmentArr[i].description} </option>`;
            selector.appendChild(newOp);
        }
    }
}