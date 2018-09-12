

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		if(this.readyState === 4 && this.status === 200)
			func(this);
	}
	
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/p1-cindy-peng/emp_pending", display);

function display(xhr){
	requests = JSON.parse(xhr.responseText).pending;
	
	table = document.getElementById("employeeViewPendingTable");
	for (let i in requests){
		newRow = document.createElement("tr");
		
		newRow.innerHTML = 
			`<td>${requests[i].requestId}</td>
			<td>${requests[i].status}</td>
			<td>${requests[i].manId}</td>
			<td>${requests[i].amount}</td>
			<td>${requests[i].reqDate}</td>
			<td>${requests[i].message}</td>`;
		
		table.appendChild(newRow);
	}
}