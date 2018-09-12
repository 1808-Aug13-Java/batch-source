
var requestList = [];
var employees = null;

let tableHeaders = `<th>Request Name</th><th>Made By</th><th>Target Manager</th><th>Resolved By</th>
				<th>View Text</th><th>Accept</th><th>Deny</th>`; 

function toggleTables(){
	let empTab = document.getElementById("employeeTable");
	let manTab = document.getElementById("managerTable");
	let togBut = document.getElementById("toggleButton");
	
	if(empTab.hidden) {
		empTab.hidden = false;
		manTab.hidden = true;
		togBut.innerHTML = "Show Employees"
	} else {
		empTab.hidden = true;
		manTab.hidden = false;
		togBut.innerHTML = "Show Managers"
	}
}

function setUp(){
	getEmployees();
	getManagerList();
}

function getEmployees(){
	if(employees != null){
		return;
	}
	
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	let initUrl = window.location.href;
	
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "DataServlet";
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status == 200) {
			let emps = JSON.parse(this.responseText);
			employees = emps.employees;
			
			//console.log(employees);
			
			let tab = document.getElementById("employeeTable");
			
			for(emp of employees){
				let row = document.createElement("tr");
				let empName = document.createElement("td");
				let userName = document.createElement("td");
				let manStat = document.createElement("td");
				let birth = document.createElement("td");
				let hire = document.createElement("td");
				
				empName.innerHTML = emp.firstName + " " + emp.lastName;
				userName.innerHTML = emp.userName;
				if(emp.manager) {
					manStat.innerHTML = "Yes";
				} else {
					manStat.innerHTML = "No";
				}
				
				birth.innerHTML = emp.birthdate;
				hire.innerHTML = emp.hiredate;
				
				row.appendChild(empName);
				row.appendChild(userName);
				row.appendChild(manStat);
				row.appendChild(birth);
				row.appendChild(hire);
				
				tab.appendChild(row);
			}
			
		}
	}
	xhr.open("GET", initUrl + "?purpose=employee");
	xhr.send();
	
}

function getuserNameById(id){
	if(employees == null){return "";}
	
	for(emp of employees){
		if(emp.id === id){
			return emp.userName;
		}
	}
}

function getManagerView(params) {
	
	getEmployees();
	
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	let initUrl = window.location.href;
	
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "DataServlet";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status == 200) {
			//console.log(this.responseText);
			let requests = JSON.parse(this.responseText);
			requestList = requests.requests;
			
			let tab = document.getElementById("manReqTable");
			tab.innerHTML = tableHeaders;
			let c = 0;
			for(req of requestList){
				let row = document.createElement("tr");
				
				let reqName = document.createElement("td");
				let reqSource = document.createElement("td");
				let tarMan = document.createElement("td");
				let resMan = document.createElement("td");
				let viewText = document.createElement("td");
				let accept = document.createElement("td");
				let deny = document.createElement("td");
				
				reqName.innerHTML = req.name;
				reqSource.innerHTML = getuserNameById(req.employeeId);
				tarMan.innerHTML = req.targetManager;
				resMan.innerHTML = req.resolvingManager;
				
				let textView = document.createElement("button");
				textView.innerHTML = "View Request";
				textView.addEventListener("click", setText);
				textView.className = "btn btn-info";
				textView.param = c;
				viewText.appendChild(textView);
				
				if(req.resolvingManager == -1){
					let acceptCom = document.createElement("button");
					acceptCom.innerHTML = "Accept";
					acceptCom.addEventListener("click", approveRequest);
					acceptCom.className = "btn btn-success";
					acceptCom.param = c;
					
					
					let denyCom = document.createElement("button");
					denyCom.innerHTML = "Deny";
					denyCom.addEventListener("click", denyRequest);
					denyCom.className = "btn btn-danger";
					denyCom.param = c;
					
					accept.appendChild(acceptCom);
					deny.appendChild(denyCom);
				} else {
					if (req.denied) {
						deny.innerHTML = "X";
					} else {
						accept.innerHTML = "X";
					}
				}
				
				row.appendChild(reqName);
				row.appendChild(reqSource);
				row.appendChild(tarMan);
				row.appendChild(resMan);
				row.appendChild(viewText);
				row.appendChild(accept);
				row.appendChild(deny);
				tab.appendChild(row);
				c++;
			}
		
		}
	}
	if(params){
		//console.log("Params " + params);
		xhr.open("GET", initUrl + "?purpose=requests&" + params + checkOmitDeny());
	} else {
		xhr.open("GET", initUrl + "?purpose=requests" + checkOmitDeny());
		
	}
	xhr.send();
}




function viewAll() {
	getManagerView();
}

function viewUnresolved() {
	getManagerView("resolved=false");
}

function viewResolved() {
	getManagerView("resolved=true");
}

function viewEmpRequest() {
	let empName = document.getElementById("empName").value;
	getManagerView("employee=" + empName);
}

function denyRequest(but) {
	let index = but.target.param;
	if(index < requestList.length) {
		let request = requestList[index];
		resolveRequest("false", request.name);
	}
}

function setText(but){
	//console.log("set text called with idex of " + but.target.param + "and request Length of "+ requestList.length);
	if(but.target.param < requestList.length) {
		
		let textarea = document.getElementById("reqLetter");
		textarea.innerHTML = requestList[but.target.param].text;
		
		
	}
}

function approveRequest(but){
	let index = but.target.param;
	if(index < requestList.length) {
		let request = requestList[index];
		resolveRequest("true", request.name);
	}
}

function resolveRequest(approve, name){
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	let initUrl = window.location.href;
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status == 200) {
			if(this.responseText == "true"){
				location.reload();
			} else {
				alert("Failed to update Window! \n\n" + this.reponseText);
			}
		}
	}
	
	xhr.open("POST", initUrl);
	xhr.setRequestHeader("RequestName", name);
	xhr.setRequestHeader("Approve", approve);
	xhr.send();
}
