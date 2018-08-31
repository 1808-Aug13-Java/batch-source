/*JavaScript Assignment Interacting with the DOM
> Create an external Javascript file to manipulate the Index.html file found in the Week 3 folder

Make each link direct the user to its respective website (see id)

Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)

Define a function alienText() which shows the hidden element displaying an alien message. When any planet other than Earth is selected, execute this function.

When the submit button is pressed, get the values from all of the input into a new row in the table below.  Make sure no input is empty, check that first and last name are at least two letters each. Validate for valid phone number and email structure. This should continue to work for multiple entries and rows.

Create a function openDetails() which opens the details element. Invoke this function when the details’ summary is moused over. The details should be hidden when the mouse is removed from the summary.

Create a function that concatenates the inner HTML of all of the span elements and prints the results to the console.

Create a function that displays the current time on earth in the span with id “earth_time”. Invoke this function when “Earth time” button is clicked. 

Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri Bb if the onset of January 1st, 1970 occured at the same time. Invoke the respective functions when their time buttons are clicked. An orbital period for Mars is 687 Earth days. Using an external api to get the orbital period for Alpha Centauri Bb. (try http://www.astropical.space/astrodb/apiref.php) Provide an implementation for getting this value using both AJAX and the fetch API.

Three seconds after a user clicks on the “Intergalactic Directory” heading, the 	background color should change to a random color. Make sure this color is never black so we can still read our black text! (there are other dark colors it could change to where we also couldn’t see the text but it’s enough to just accomodate for a black background)

When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the select. Display the result in the element with id result.

Define function walkTheDom(node, func)
	This function should traverse every node in the DOM. 
	Use recursion. On each node, calle func(node).
*/

document.getElementsByTagName("a")[0].setAttribute("href","https://www.google.com");
document.getElementsByTagName("a")[1].setAttribute("href","https://twitter.com");
document.getElementsByTagName("a")[2].setAttribute("href","https://slack.com");
document.getElementsByTagName("a")[3].setAttribute("href","https://docs.oracle.com/javase/7/docs/api/");

document.getElementsByTagName("option")[3].setAttribute("disabled",true);