

// 1. Set the anchor tags to folow their actual links. 
function setAnchorLinks() {
	// Get all the anchor tags
	let links = document.getElementsByTagName("a");

	// Go through each anchor tag and set it's link accordingly. 
	for (link of links) {
		console.log("Link: " + link.getAttribute("name"));
		if (link.getAttribute("name") == "google") {
			link.setAttribute("href", "https://www.google.com");
		}
		else if (link.getAttribute("name") == "twitter") {
			link.setAttribute("href", "https://www.twitter.com");
		}
		else if (link.getAttribute("name") == "slack") {
			link.setAttribute("href", "https://slack.com");
		}
		else if (link.getAttribute("name") == "javadocs") {
			link.setAttribute("href", "https://docs.oracle.com/javase/8/docs/api/");
		}
	}
} // end of setAnchorLinks


// 2. Disable Pluto as a planet of residence. 
function disablePluto() {
	// Get the form control with the planet listings
	let planets = document.getElementById("planet");

	// Search through the listings to find an option that contains "Pluto". 
	// If we find it, remove it from the planet listings. 
	for (let i=0; i<planets.childNodes.length;) {
		// If the text is "Pluto" remove the node. Also, don't increment i as 
		// it might skip if there are multiple "Pluto"s (not that it will likely 
		// be an issue). 
		if (planets.childNodes[i].innerText == "Pluto") {
			planets.removeChild(planets.childNodes[i]);
			continue;
		}
		// Increment i++ only if we didn't remove an element. 
		i++;
	}

} // disablePluto


// 3.1 A function that displays 
function alienText() {
	
}





setAnchorLinks();
disablePluto();





