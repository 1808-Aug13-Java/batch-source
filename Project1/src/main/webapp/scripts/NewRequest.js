/**
 * 
 */

function prepManagers()
{
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	let initUrl = window.location.href;
	
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "DataServlet";
	
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status == 200){
			let managers = JSON.parse(this.responseText);
			let combo = document.getElementById("targetManager");
			
			for(man of managers.employees){
				let opt = document.createElement("option");
				opt.innerHTML = man.userName;
				opt.value = man.userName;
				combo.appendChild(opt);
			}
		}
	}
	
	xhr.open("GET", initUrl + "?purpose=manager")
	xhr.send();
}

function checkName()
{
	let reqName = document.getElementById("reqName");
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));

	let initUrl = window.location.href;
	
	
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "DataServlet";
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status == 200){
			let headerWarning = document.getElementById("reqNameWarn");
			if(this.responseText === "true"){
				headerWarning.innerHTML = "That Name is Already taken! Submission will fail!";
			} else {
				headerWarning.innerHTML = "";
			}
			
		}
	}
	
	xhr.open("GET", initUrl + "?purpose=requestExists&name=" + reqName.value)
	xhr.send();
}

