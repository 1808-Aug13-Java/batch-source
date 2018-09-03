let baseUrl ="http://api.apixu.com/v1/current.json?key=4f9095e1ed93462689a193305182003&q=60201";

document.getElementById("submitBtn").addEventListener("click",searchWeather);

function searchWeather(){
    let input =document.getElementById("zipcode").value;
    if(input.length != 5 | input <0){
        document.getElementById("alert").innerHTML("Invalid Input");
    }
    else{
        sendAjaxGet(baseUrl+input,displayWeather);
    }
}

function sendAjaxGet(url, callback) {
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status == 200) {
            callback(this);
            //console.log(xhr.responseText);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayWeather(){
    let weather =JSON.parse(xhr.responsetext);
    console.log(weather);
    console.log(xhr);

   
}