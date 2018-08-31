
const BASE_URL = "https://pokeapi.co/api/v2/pokemon/";


function searchPokemon() {
	let input = document.getElementById("pokemonId").value;
	//console.log(input);
	
	// Attempt to cast the input to a number
	input = Number(input);
	
	// If the number is invalid, print an error message
	if (isNaN(input)) {
		document.getElementById("name").innerHTML = "Invalid Input";
	}
	// Otherwise, make a request
	else{
		//document.getElementById("").innerHTML = "";
		sendAjaxGet(BASE_URL + input, handlePokemonResponse);
	}
	
	
}



function handlePokemonResponse(xhr) {
	//console.log(xhr);
	let pokemon = JSON.parse(xhr.responseText);
	
	document.getElementById("name").innerHTML = `Name: ${pokemon.name}`;
	
	let types = pokemon.types;
	if (types.length == 2) {
		document.getElementById("type").innerHTML = `Type: 
			${types[0].type.name} & ${types[1].type.name}
		`;
	}
	else {
		document.getElementById("type").innerHTML = `Type: 
			${types[0].type.name}
		`;
	}
	
	document.getElementById("weight").innerHTML = `Weight: ${pokemon.weight}`;
	document.getElementById("height").innerHTML = `Height: ${pokemon.height}`;
}



function sendAjaxGet(url, callback) {
	// Get a new XHR object, or an activeX object if the browser doesn't support XHL
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	
	// Set the behaviour when the ready state changes.
	xhr.onreadystatechange = function () {
		// If the request is done, and it returned a successful code, handle the weather
		if (this.readyState === 4 && this.status === 200) {
			//console.log("Recieved XHR Response: " + xhr.responseText);
			// Call the handler, passing this xhr object in. 
			callback(this);
		}
	}
	
	// Open and send the request. 
	xhr.open("GET", url);
	xhr.send();
}


// Add action listeners
document.getElementById("submitBtn").addEventListener("click", searchPokemon);

