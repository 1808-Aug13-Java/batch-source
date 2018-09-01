
// let baseURL = "http://api.apixu.com/v1/current.json?key=4f9095e1ed93462689a193305182003&q=";
// let baseURL = "https://pokeapi.co/";
let baseURL = "https://pokeapi.co/api/v2/pokemon-form/";

document.getElementById("submitbtn").addEventListener("click", searchWeather);

function searchWeather()
{
    //read the user's input after they clicked submit
    let input = document.getElementById("pokemon").value;
    
    sendAjaxGett(baseURL + input, displayPokemon);
}

function sendAjaxGett(url, callback)
{
    // console.log("here");
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200)
            callback(this);
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayPokemon(xhr)
{
    console.log("heeer");
    // console.log(xhr.responseText);
    let jsonRT = JSON.parse(xhr.responseText);
    console.log(jsonRT);
    document.getElementById("name").innerHTML =
        `This is ${jsonRT.pokemon.name}`
}