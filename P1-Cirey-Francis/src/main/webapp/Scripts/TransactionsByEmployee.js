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

sendAjaxGet("http://localhost:8082/P1-Cirey-Francis/transactionsEmployeeTable" +
		window.location.search, display);

function display(xhr){
	
	console.log("Im running!");
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
		<td>${transactionArr[i].approverId}</td>
		<td>${transactionArr[i].amount}</td>
		<td>${transactionArr[i].reason}</td>`;
		
		table.appendChild(newRow);
	}
}