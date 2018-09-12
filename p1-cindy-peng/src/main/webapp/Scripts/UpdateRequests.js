/**
 * 
 */

//console.log("hey");  //test that the js file is linked right
function sendAjaxGet(url, func) {
	//make ur XMLHttpRequest object
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function () {
		if(this.readyState === 4 && this.status === 200)
			func(this);     //remember readystate 4 means DONE
	}
	
	xhr.open("GET", url);
	xhr.send();
}

//now call wat we just defined
sendAjaxGet("http://localhost:8082/p1-cindy-peng/pendingRequests", display);
	//the link is generated from our servlet WebPasteMembersServlet

function display(xhr){
	//use XMLHttpRequest object's responseText, but change the xml to JSON
	console.log(xhr.responseText); //test that xml http request text was passed in
	reimbursements = JSON.parse( xhr.responseText ).pending;  //remember parse takes that string and turns into java object
		//have to do .staffMembers cuz u named the property thats mapped to the array 
//	console.log(staffMembers);
	table = document.getElementById("pendingRequestTable");
	for(let i in reimbursements){
		let newRow = document.createElement("tr");
		//do checks for if the database had a null value
		
		let date = reimbursements[i].reqDate;
		if(date === null)
			date = "n/a";
		
		newRow.innerHTML = 
		    `<td>${reimbursements[i].requestId}</td>
			 <td>${reimbursements[i].staffId}</td>
			 <td>${reimbursements[i].amount}</td>
			 <td>${date}</td>
			  <td>${reimbursements[i].message}</td>`;
		table.appendChild(newRow);
	}
	
	
}/**
 * 
 */