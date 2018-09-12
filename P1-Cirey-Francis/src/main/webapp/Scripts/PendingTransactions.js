/**
 * 
 */

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest || new ActiveXObject("Microsoft.HttpRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status === 200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/P1-Cirey-Francis/pendingTable", displayPending);

function displayPending(xhr){
	
	transactions = JSON.parse(xhr.responseText);
	console.log(transactions.transactions);
	transactionArr = transactions.transactions;
	
	
	let table = document.getElementById("table");
	
	for(i in transactionArr){
		let newRow = document.createElement("tr");
		
		newRow.innerHTML = `<td>${transactionArr[i].id}</td>
		<td>${transactionArr[i].date}</td>
		<td>${transactionArr[i].creator}</td>
		<td>${transactionArr[i].approved}</td>
		<td>${transactionArr[i].approverName}</td>
		<td>${transactionArr[i].amount}</td>
		<td>${transactionArr[i].reason}</td>
		<td>
		<button type = "submit" class = "btn btn-primary" onClick = "sendApprove(${transactionArr[i].id})">Approve</button>
		<button type = "submit" class = "btn btn-danger" onClick = "sendDeny(${transactionArr[i].id})">Deny</button>
		</td>`;
		
		table.appendChild(newRow);
	}
}