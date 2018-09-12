/**
 * 
 */

let myUrl = "http://localhost:8082/P1-Cirey-Francis/profileField";

function sendAjaxGet(url, func){
	let xhr = new XMLHttpRequest || new ActiveXObject("Microsoft.HttpRequest");
	xhr.onreadystatechange = function(){
		if(this.readyState === 4 && this.status === 200){
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet(myUrl, generateFields);

function generateFields(xhr){
	
	let fields = JSON.parse(xhr.responseText);
	console.log(fields);
	fieldsArr = fields.fields;
	
	document.getElementById("name").value = fieldsArr[0].name;
	document.getElementById("username").value = fieldsArr[0].username;
	document.getElementById("private").value = fieldsArr[0].private;
}