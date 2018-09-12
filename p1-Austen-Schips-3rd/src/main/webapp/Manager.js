function onLoad(){
	if(!sessionStorage.user){
		logout();
	}
	document.getElementById("empname").innerHTML = sessionStorage.user;
	document.getElementById("empmail").innerHTML = sessionStorage.email;
	document.getElementById("toReplace").innerHTML = sessionStorage.table;
}
function logout(){
    sessionStorage.removeItem("user");
    sessionStorage.removeItem("pass");
    sessionStorage.removeItem("manager");
    sessionStorage.removeItem("id");
    sessionStorage.removeItem("email");
    sessionStorage.clear();
    
    window.location.href = 'Index.html';
  }
function approval(RID, bool){
	let xhttp = new XMLHttpRequest();
    var params = "rid="+RID+"&actiontype="+bool+"&approver=" + sessionStorage.id;
	xhttp.onreadystatechange = function() {
		console.log(this.readyState);
		if (this.readyState === 4 && this.status === 200){
			alert("Completed");
		}
	};
	xhttp.open("GET", "Manage"+"?"+params, true);
	xhttp.send();
  
}