let baseUrl = "http://www.omdbapi.com/?i=tt3896198&apikey=1befa530&t=";

document.getElementById("movieBtn").addEventListener("click", searchMovie);

function searchMovie() {
    let input = document.getElementById("movie").value;
    if (input.length != 0) {
        document.getElementById("invalidInput").innerHTML = "";
        sendAjaxGet(baseUrl + input, displayMovie);
    } else {
        document.getElementById("invalidInput").innerHTML = "Invalid input";
    }
}

function sendAjaxGet(url, callback) {
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            console.log(xhr.response);
            callback(this);
        }
     }
    xhr.open("GET", url);
    xhr.send();
}

function displayMovie(xhr) {
    console.log(xhr);
    let movieDetails = JSON.parse(xhr.responseText);
    console.log(movieDetails);
    document.getElementById("title").innerHTML = movieDetails.Title;
    document.getElementById("year").innerHTML = movieDetails.Year;
    document.getElementById("poster").setAttribute("src", movieDetails.Poster);
    document.getElementById("MPAARating").innerHTML = movieDetails.Rated;
    document.getElementById("releaseDate").innerHTML = movieDetails.Released;
    document.getElementById("plot").innerHTML = movieDetails.Plot;
    document.getElementById("runtime").innerHTML = movieDetails.Runtime;
    document.getElementById("actors").innerHTML = `Cast: ${movieDetails.Actors}`;
    document.getElementById("IMDBRating").innerHTML = `IMDB Rating: ${movieDetails.Ratings[0].Value}`;

}