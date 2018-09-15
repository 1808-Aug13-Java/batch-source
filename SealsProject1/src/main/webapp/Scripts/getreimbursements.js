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

sendAjaxGet("http://localhost:8082/SealsProject1/getreimbursements", display);

function display(xhr) {
    let reimbursement = JSON.parse(xhr.responseText);
    console.log(reimbursement);
    let reimbursementArr = reimbursement.reimbursement;
    let table = document.getElementById("rTable");
    let selector = document.getElementById("reimburseselect");
    
    for (i in reimbursementArr) {
    	
        let newRow = document.createElement("tr");
        let newOp = document.createElement("option");
    	let resolutionStatus;
    			
    		switch (reimbursementArr[i].status) {
    		case 0 :
    			resolutionStatus = "Pending";
    			break;
    		case 1 :
    			resolutionStatus = "Approved";
    			break;
    		case -1 : 
    			resolutionStatus = "Denied";
    			break;
    		default : 
    			console.log("Resolution Status Error");
    		}

      if (reimbursementArr[i].status != 0) {
            console.log("If is true: " + reimbursementArr[i].managerId);
            newRow.innerHTML = `<td>${reimbursementArr[i].empId}</td>
            <td>${resolutionStatus}</td>
            <td>${reimbursementArr[i].description}</td>
            <td>$${reimbursementArr[i].amount}
            <td>${reimbursementArr[i].managerId}</td>`;
            table.appendChild(newRow);
         } else {
            console.log("If is false: " + reimbursementArr[i].managerId);
            newOp.value = reimbursementArr[i].id;
            newOp.text = `${reimbursementArr[i].empId}
     	   : ${reimbursementArr[i].amount} :
  			 ${reimbursementArr[i].description}`;
           
             selector.appendChild(newOp);
         }
    }
}