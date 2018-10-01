let baseUrl = "http://api.apixu.com/v1/current.json?key=4f9095e1ed93462689a193305182003&q=";

document.getElementById("submitBtn").addEventListener("click", searchWeather);

function searchWeather(){
    let input = document.getElementById("zipcode").value;
    if (input.length !== 5 | input<0){
        document.getElementById("alert").innerHTML = "Invalid Input";
    } else {
        sendAjaxGet(baseUrl+input, displayWeather);
    }
}

function sendAjaxGet(url, callback){
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function(){
        if(this.readyState === 4 && this.status == 200){
            callback(this);
            //console.log(xhr.responseText);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayWeather(xhr){
    //console.log(xhr);
    let weather = JSON.parse(xhr.responseText);
    //console.log(weather);

    document.getElementById("location").innerHTML = `Weather for ${weather.location.name}, ${weather.location.region}`

    document.getElementById("icon").setAttribute("src","http:"+weather.current.condition.icon);

    document.getElementById("status").innerHTML = weather.current.condition.text;

    document.getElementById("temperature").innerHTML = `${weather.current.temp_f} F (feels like ${weather.current.feelslike_f} F)`;
}