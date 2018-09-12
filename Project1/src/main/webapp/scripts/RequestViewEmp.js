let tableHeaders = `<th>Request Name</th><th>Target Manager</th><th>Resolved By</th>
	<th>View Text</th><th>Status</th>`
 

var requestList = null;
var requestListItem = [];

function getEmployeeView(caller) {
	
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	let initUrl = window.location.href;
	
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "DataServlet";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status == 200) {
			let requests = JSON.parse(this.responseText);
			requestList = requests.requests;
			
			let rust = 0;
			let tab = document.getElementById("empReqTable");
			for(req of requestList){
				let row = document.createElement("tr");
				
				let reqName = document.createElement("td");
				let tarMan = document.createElement("td");
				let resMan = document.createElement("td");
				let text = document.createElement("td");
				let status = document.createElement("td");
				
				reqName.innerHTML = req.name;
				tarMan.innerHTML = req.targetManager;
				resMan.innerHTML = req.resolvingManager;
				
				let textView = document.createElement("button");
				textView.innerHTML = "View Request";
				textView.addEventListener("click", setText);
				textView.className = "btn btn-info";
				textView.param = rust;
				text.appendChild(textView);
				
				if(req.resolvingManager == -1) {
					status.innerHTML = "<p class='bg-warning'>Pending</p>";
				} else {
					if(req.denied) {
						status.innerHTML = "<p class='bg-danger'>Denied</p>";
					} else {
						status.innerHTML = "<p class='bg-success'>Approved</p>";
					}
				}
				
				
				row.appendChild(reqName);
				row.appendChild(tarMan);
				row.appendChild(resMan);
				row.appendChild(text);
				row.appendChild(status);
				tab.appendChild(row);
				requestListItem.push(row);
				rust++;
			}
			
			caller();
		}
	}

	xhr.open("GET", initUrl + "?purpose=requests" + checkOmitDeny());

	xhr.send();
}

function viewAll() {
	//console.log("View All called!");
	
	if(requestList == null) {
		//console.log("Calling getEmployee View!");
		getEmployeeView(this);
		return;
	}
	

}

function viewUnresolved() {
	if(requestList == null) {
		//console.log("Calling getEmployee View!");
		getEmployeeView(this);
		return;
	}
	
	for(var c = 0; c < requestList.length && c < requestListItem.length; c++){
		if(requestList[c].resolvingManager == -1){
			requestListItem[c].hidden = false;
		} else {
			requestListItem[c].hidden = true;
		}
		
	}
	
}

function getOmitDeny(){
	return document.getElementById("omitD").checked;
}

function viewResolved() {

	if(requestList == null) {
		//console.log("Calling getEmployee View!");
		getEmployeeView(this);
		return;
	}
	
	for(var c = 0; c < requestList.length && c < requestListItem.length; c++){
		if(requestList[c].resolvingManager == -1 || getOmitDeny()){
			requestListItem[c].hidden = true;
		} else {
			requestListItem[c].hidden = false;
		}
		
	}
}

function setText(but){
//	console.log("set text called with idex of " + but.target.param + "and request Length of "+ requestList.length);
	if(but.target.param < requestList.length) {
		
		let textarea = document.getElementById("reqLetter");
		textarea.innerHTML = requestList[but.target.param].text;
		
		
	}
}

function viewEmpRequest() {
	let empName = document.getElementById(empName).value;
	getManagerView("employee=" + empName);
}