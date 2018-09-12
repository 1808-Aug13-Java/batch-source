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

sendAjaxGet("http://localhost:8082/Project-1-jeremiah-laforge/GetReimbursmetsByEmployee", display);

function display(xhr) {
    let reimbursment = JSON.parse(xhr.responseText);
    console.log(reimbursment);
    let reimbursmentArr = reimbursment.reimbursment;
    let table = document.getElementById("rTable");
    let resolvedBy = "not resolved"

    for (i in reimbursmentArr) {
        let newRow = document.createElement("tr");

        if (reimbursmentArr[i].resolvedBy) {
            resolvedBy = "not resolved";
        } else {
            resolved = `${reimbursmentArr[i].resolvedBy}`;
        }

        newRow.innerHTML = `<td>${reimbursmentArr[i].status}</td>
                        <td>${reimbursmentArr[i].description}</td>
                         <td>${resolvedBy}</td>`;

        table.appendChild(newRow);

    }
}