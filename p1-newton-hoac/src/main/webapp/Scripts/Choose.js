function sendAjaxGet(url, func){
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	xhr.onreadystatechange = function(){
		if (this.readyState == 4 && this.status == 200){
			func(this);
		}
	}
	xhr.open("GET", url)
	xhr.send();
}
//Get id from url
var str = ""+window.location.href;
var index = str.search("id=");
console.log(str.slice(index+3));

var manButton = document.getElementById("manButton");
manButton.disabled = true;

sendAjaxGet("http://localhost:8082/p1-newton-hoac/manager?" + str.slice(index), enableButton);

function enableButton(xhr){
	manager = JSON.parse(xhr.responseText);
	
	if(manager.id){
		manButton.disabled = false;
	}
}
