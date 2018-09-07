document.addEventListener("DOMContentLoaded", function(){
	  const isUserUrl = "http://localhost:8080/p1-alex-bumpers/session";

	  function sendAjaxGet(url, callback) {
	    let xhr = new XMLHttpRequest();
	    xhr.onreadystatechange = function() {
	      if(this.readyState == 4 && this.status == 200) {
	        callback(this);
	      }
	    }

	    xhr.open("get", url);
	    xhr.send();
	  }

	  function populateUser(xhr) {
	    let response = JSON.parse(xhr.responseText);
	    console.log(response);
	    if(response == null || response.username == null) {
		    console.log(response);
	        window.location="http://localhost:8080/p1-alex-bumpers/login";
	    }
	  }

	  sendAjaxGet(isUserUrl, populateUser);
	});