window.onload = searchPokemon;
let baseUrl ="https://pokeapi.co/api/v2/";

document.getElementById("pokeball").addEventListener("click", searchPokemon);

document.getElementById("previous").addEventListener("click", prev);
document.getElementById("next").addEventListener("click", next);




function searchPokemon(){
    var random = Math.floor(Math.random() * 721) + 1;

    sendAjaxGet(baseUrl +"pokemon/"+ random+"/", displayPokemon);
}

function sendAjaxGet(url, callback) {
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status == 200) {
            callback(this);
            // console.log(xhr.responseText);
        }
    }
    xhr.open("GET", url);
    xhr.send();

}

function displayPokemon(xhr){
    let pokemon = JSON.parse(xhr.responseText);
    console.log(pokemon);
    // console.log(xhr);
    document.getElementById("name").innerHTML = `Pokemon name is:  ${pokemon.name}`

    document.getElementById("icon").setAttribute("src",pokemon.sprites.front_default);

    document.getElementById("number").innerHTML = pokemon.id;
}


function prev(){
    // var random = Math.floor(Math.random() * 721) + 1;
    var num = document.getElementById("number").innerHTML;
    var yup = parseInt(num) - 1;
    sendAjaxGet(baseUrl + "pokemon/" + yup + "/", displayPokemon);

}
function next() {
    // var random = Math.floor(Math.random() * 721) + 1;
    var num = document.getElementById("number").innerHTML;
    var yup = parseInt(num)+1;

    sendAjaxGet(baseUrl + "pokemon/" + yup + "/", displayPokemon);

}