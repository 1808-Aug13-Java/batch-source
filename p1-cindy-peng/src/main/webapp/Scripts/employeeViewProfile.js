/**viewProfileTable"
 * 
 */



function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		if(this.readyState === 4 && this.status === 200)
			func(this);
	}
	
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/p1-cindy-peng/emp_profile", display);

function display(xhr){
	requests = JSON.parse(xhr.responseText).member;
	console.log(requests);
	table = document.getElementById("viewProfileTable");
//	for (let i in requests){
		newRow = document.createElement("tr");
		
		newRow.innerHTML = 
			`<td>${requests.staffName}</td>
			<td>${requests.manId}</td>
			<td>${requests.staffRole}</td>
			<td>${requests.phone}</td>`;
		
		table.appendChild(newRow);
	
}