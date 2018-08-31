const BASE_URL = "http://api.apixu.com/v1/current.json?key=4f9095e1ed93462689a193305182003&q=";


document.getElementById("submitBtn").addEventListener("click", searchWeather);

function searchWeather() {
  let input = document.getElementById("zipcode").value;
  console.log(input);
  if(input.length !== 5 || input<0) {
    document.getElementById("alert").innerHTML = "Invalid input";
  } else {
    console.log('send ajax');
    
    sendAjaxGet(BASE_URL + input, displayWeather);
  }
}

function sendAjaxGet(url, callback) {
  console.log('inside ajax');
  
  let xhr = new XMLHttpRequest();
  
  
  xhr.onreadystatechange = function() {
    if(this.readyState == 4 && (this.status >= 200 || this.status < 300)) {
      callback(this);
    }
  }
  xhr.open("GET", url);
  xhr.send();
}

function displayWeather(xhr) {
  console.log(xhr);
  
  let weather = JSON.parse(xhr.responseText);

  document.getElementById("location").innerHTML = `Weather for
  ${weather.location.name}, ${weather.location.region}`;

  document.getElementById("icon").setAttribute("src", "http:"+weather.current.condition.icon);
  document.getElementById("status").innerHTML = weather.current.condition.text;
  document.getElementById("temperature").innerHTML = `${weather.current.temp_f}
   F (feels like ${weather.current.feelslike_f} F)`;


}