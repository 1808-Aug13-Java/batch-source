let baseUrl = "https://pokeapi.co/api/v2/";

document.getElementById("submitBtn").addEventListener("click", searchWeather);

function searchWeather(){
    let input = document.getElementById("PokeId").value;
    if (input<0){
        document.getElementById("alert").innerHTML = "Invalid Input";
    } else {
        sendAjaxGet(baseUrl+ "pokemon/"+input +"/", displayWeather);
    }
}

function sendAjaxGet(url, callback){
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function(){
        if(this.readyState === 4 && this.status == 200){
            callback(this);
            // console.log(xhr.responseText);
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayWeather(xhr){
    console.log(xhr);
    let pokemon = JSON.parse(xhr.responseText);
    console.log(pokemon);
//get types
let typeArr = pokemon.types;
console.log(typeArr);

    document.getElementById("type").innerHTML = "";

    document.getElementById("name").innerHTML = `# ${pokemon.id} ${pokemon.name}`;

    document.getElementById("icon").setAttribute("src", pokemon.sprites.front_default);
    document.getElementById("icon").setAttribute("height", 150);
    document.getElementById("icon").setAttribute("width", 150);

    document.getElementById("type").innerHTML = `Types: `;
    for(i in typeArr){ document.getElementById("type").innerHTML += `${pokemon.types[i].type.name},`};

    

    //document.getElementById("status").innerHTML = weather.current.condition.text;

    //document.getElementById("temperature").innerHTML = `${weather.current.temp_f} F (feels like ${weather.current.feelslike_f} F)`;
}