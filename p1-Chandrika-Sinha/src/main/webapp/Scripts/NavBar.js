function sendAjaxGet(url, func) {
	let xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
	xhr.onreadystatechange = function() {
		if (this.readyState==4 && this.status==200) {
			func(this);
		}
	}
	xhr.open("GET", url);
	xhr.send();
}

sendAjaxGet("http://localhost:8082/p1-Chandrika-Sinha/allReqs", addNavBar);

function addNavBar(xhr) {
	let navBarDiv = document.getElementById("EmployeeNavBar");
	navBarDiv.innerHTML = 
		`<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		  <a class="navbar-brand" href="/p1-Chandrika-Sinha/home">ERS</a>
		  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		    <span class="navbar-toggler-icon"></span>
		  </button>
		  <div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav">
		      <li class="nav-item active">
		        <a class="nav-link" href="/p1-Chandrika-Sinha/home">Home<span class="sr-only">(current)</span></a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="/p1-Chandrika-Sinha/request">View Requests</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="/p1-Chandrika-Sinha/profile">View Profile</a>
		      </li>
		      <li class="nav-item">
		        <a class="nav-link" href="/p1-Chandrika-Sinha/profile">Update Profile</a>
		      </li>
		    </ul>
		  </div>
		</nav>`;
}