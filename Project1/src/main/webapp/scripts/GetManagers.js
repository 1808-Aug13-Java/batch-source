/**
 * 
 */

function checkOmitDeny()
{
	if(document.getElementById("omitD").checked){
		return "&omitdenies=true";
	} else {
		return "";
	}
}

var managers = [];



function getManagerList()
{
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	let initUrl = window.location.href;
	
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "DataServlet";
	
	xhr.onreadystatechange = function(){
		
		if(this.readyState === 4 && this.status == 200){
			
			managers = JSON.parse(this.responseText);
			let manTable = document.getElementById("managerTable");
			
			for(man of managers.employees) {
				let row = document.createElement("tr");
				let manId = document.createElement("td");
				let manUn = document.createElement("td");
				manId.innerHTML = man.id;
				manUn.innerHTML = man.userName;
				row.appendChild(manUn);
				row.appendChild(manId);
				manTable.appendChild(row);
			}
		}
	}
	
	xhr.open("GET", initUrl + "?purpose=manager")
	xhr.send();
}