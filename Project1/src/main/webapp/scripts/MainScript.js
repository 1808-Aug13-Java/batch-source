function loadMainPage(){
	let initUrl = window.location.href;
	
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "LogInServlet";
	
	//console.log(initUrl);
	
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	xhr.onreadystatechange = function(){
		//console.log("Ready State: " + this.readyState + "  status: " + this.status);
		if(this.readyState === 4 && this.status == 200){
			document.getElementById("pageContent").innerHTML = this.responseText;
			setMessageBoard("");
		}
		else if(this.readyState === 4){
			//console.log(this.responseText);
		}

	}
	
	xhr.open("POST", initUrl);
	xhr.setRequestHeader("contacted", "val");
	xhr.send();
}

function setMessageBoard(str){
	let messageBoard = document.getElementById("messageJumbo");
	if(messageBoard){
		messageBoard.innerHTML = `<h1 class="bg-danger">${str}</h1>`;
	}
}

function submitLogIn()
{
	let initUrl = window.location.href;
	//console.log("Login Called");
	initUrl = initUrl.substring(0, initUrl.lastIndexOf("/") + 1);
	initUrl += "LogInServlet";
	
	 
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	xhr.onreadystatechange = function(){
		//console.log("Ready State: " + this.readyState + "  status: " + this.status);
		if(this.readyState === 4 && this.status == 200){
			document.getElementById("pageContent").innerHTML = this.responseText;
			setMessageBoard("");
		}
		else if(this.readyState === 4 && this.status == 401){
			setMessageBoard("Invalid Username and/or Password!");
		}

	}
	let us = document.getElementById("login");
	//console.log(us);
	if(us){us = us.value;}
	//console.log(us);
	let pw = document.getElementById("password");
	//console.log(pw);
	if(pw){pw = pw.value;}
	//console.log(pw);
	
	xhr.open("POST", initUrl);
	xhr.setRequestHeader("contacted", "val");
	xhr.setRequestHeader("submit", "true");
	if(us && pw){
		//console.log("sending with params");
		let param = `username=${us}&password=${pw}`;
		//console.log("param '"+ param + "'");
		xhr.send(param);
	} else {
		//console.log("sending without params");
		xhr.send();

	}
}