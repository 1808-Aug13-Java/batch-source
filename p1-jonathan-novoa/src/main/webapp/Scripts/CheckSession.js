/**
 * 
 */

let checkSessionUrl= "http://localhost:8082/p1-jonathan-novoa/session";
//http://localhost:8082/p1-jonathan-novoa/home

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status===200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}//standard XML ajax request
sendAjaxGet(checkSessionUrl, populateUser);

function populateUser(xhr){
	let response = JSON.parse(xhr.response);
	if(response.username != "null"){
		document.getElementById("user").innerHTML = "You are logged in as: "+ response.username;
	} else {
//		window.location = "http://localhost:8082/ServletLogInDemo/login";
		window.location= "http://localhost:8082/p1-jonathan-novoa/home"
	}
}

function logout(){
	window.location="http://localhost:8082/p1-jonathan-novoa/logout"
}