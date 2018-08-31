let baseUrl = "https://pokeapi.co/api/v2/";

document.getElementById("submit").addEventListener("click", searchWeather);

function searchWeather(){
    let input = document.getElementById("pokeID").value;
    if (input < 0) {
        document.getElementById("alert").innerHTML = "Invalid Input";
    } else {
        sendAjaxGet(baseUrl + "pokemon/" + input +"/", displayWeather);
    }
}

function sendAjaxGet(url, callback){
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    // console.log("test");
    xhr.onreadystatechange = function(){
        if (this.readyState === 4 && this.status == 200){
            callback(this);
            // console.log(xhr.responseText);
        } else if (this.status == 404){
            document.getElementById("alert").innerHTML = "404 not found";
            document.getElementById("name").innerHTML = "";
            document.getElementById("icon").setAttribute("src", "https://vignette.wikia.nocookie.net/joke-battles/images/d/d8/MissingNo..png/revision/latest/scale-to-width-down/400?cb=20160129051405");
            document.getElementById("icon").setAttribute("height", 200);
            document.getElementById("icon").setAttribute("width", 200);
            document.getElementById("type").innerHTML = "";
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

function displayWeather(xhr){
    console.log(xhr);
    let pokemon = JSON.parse(xhr.responseText);
    console.log(pokemon);

    // Get types array for pokemon
    let typeArr = pokemon.types;
    console.log(typeArr);

    //  clean out type.innerHTML
    document.getElementById("type").innerHTML = "";


    document.getElementById("name").innerHTML = `#${pokemon.id}  ${pokemon.name}`;
    document.getElementById("icon").setAttribute("src", pokemon.sprites.front_default);
    document.getElementById("icon").setAttribute("height", 200);
    document.getElementById("icon").setAttribute("width", 200);
    // document.getElementById("condition").innerHTML = weather.current.condition.text;
    document.getElementById("type").innerHTML = `Types: `;
    for(i in typeArr){
        document.getElementById("type").innerHTML += `${pokemon.types[i].type.name}, `;
    }
    // document.getElementById("temperature").innerHTML = `1340 F (feels like 110 F)`;

}