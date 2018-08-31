window.onload = function() {

  // 	https://api.icndb.com/jokes/random?
  //firstName=John&amp;lastName=Doe
  let BASE_URL = 'https://api.icndb.com/jokes/random?';

  let submit = document.getElementById("submit");

  submit.addEventListener("click", searchJokes);

  function searchJokes() {
    console.log("clicked");
    
    let firstName = document.getElementById("firstName");
    let lastName = document.getElementById("lastName");
    if(firstName.value.length < 2 || lastName.value.length < 2) {
      displayWarning();
    }

    removeWarning();

    let url = `
    http://api.icndb.com/jokes/random?firstName=${firstName.value}&amp;lastName=${lastName.value}
    `;

    sendAjaxGet(url, displayJoke);
  }

  function sendAjaxGet(url, callback) {
    let xhr = new XMLHttpRequest();
    
    xhr.onreadystatechange = function() {
      if(this.readyState == 4 && (this.status == 200)) {
        // this.setRequestHeader("Cache-Control", "no-cache");
        callback(this);
      }
    }

    xhr.open("GET", url);
    xhr.send();
  }

  function displayJoke(xhr) {
    
    let obj = JSON.parse(xhr.responseText);
    let text = obj.value.joke;

    main.innerHTML += `
      <div class="alert alert-succes"><strong>${obj.value.joke}</strong></div>
    `;
 
  }

  let main = document.getElementById("main");

  function displayWarning() {
    let warning = document.getElementById("warning");
    let warningEl = `
    <div class="alert alert-danger" id="warning">Enter complete name</div>
    `;

    if(warning == null) {
      main.innerHTML += warningEl;
    }
  }

  function removeWarning() {
    let warning = document.getElementById("warning");
    if(warning != null) {
      warning.outerHTML = "";
    }
  }
}

