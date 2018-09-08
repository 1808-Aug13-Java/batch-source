/* 
    JavaScript Assignment Interacting with the DOM
*/

window.onload = function () {
    buildLinks();
    disableOption();
    alienText();  
    getInfo();
    setDetailListners();
    document.getElementById("earth_time_check").addEventListener("click", currentTime);
    document.getElementsByTagName("h1")[0].addEventListener("click", function() {
        timedChange(document.getElementsByTagName("h1")[0]);
    });
    document.getElementById("n1").addEventListener("input", calc);
    document.getElementById("n2").addEventListener("input", calc);
    document.getElementById("operation").addEventListener("change", calc);
}


/*  01
    Make each link direct the user to its respective website (see id)
 */
function buildLinks(){
    document.getElementsByName("google")[0].innerHTML ="Google";
    document.getElementsByName("google")[0].href = "https://google.com";
    document.getElementsByName("twitter")[0].innerHTML ="Twitter";
    document.getElementsByName("twitter")[0].href = "https://twitter.com";
    document.getElementsByName("slack")[0].innerHTML ="Slack";
    document.getElementsByName("slack")[0].href = "https://slack.com";
    document.getElementsByName("javadocs")[0].innerHTML ="JavaDocs";
    document.getElementsByName("javadocs")[0].href = "https://javadocs.com";
}

/*  02 
    Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)
*/
function disableOption(){
    document.getElementById("planet").options[3].disabled = true;
}

/*  03 
    Define a function alienText() which shows the hidden element displaying an alien message. When any planet other than Earth is selected, execute this function.
 */
function alienText(){
    document.getElementById("planet").addEventListener("click", function() {
        let planet = document.getElementById("planet");
        let selected = planet.options[planet.selectedIndex].text;
        if(selected != "Earth"){
            document.getElementsByTagName("p")[5].removeAttribute("hidden");
        }

        
    })
}

/* 04
   When the submit button is pressed, get the values from all of the input 
   into a new row in the table below.  Make sure no input is empty, check 
   that first and last name are at least two letters each. Validate for valid 
   phone number and email structure. This should continue to work for multiple 
   entries and rows.
*/
function getInfo(){
    document.getElementById("form-sub").addEventListener("click", function (){
       
        if(!validInput()){
            return;
        }
        let gender = document.querySelector('input[name = "gender"]:checked').value;
        let color = document.getElementById("color").value;
        let activities = document.getElementsByClassName("activity");
        let activitiesChecked = [];
        for (var i=0; i<activities.length; i++) {
            if (activities[i].checked) {
                activitiesChecked.push(activities[i].value);
                }
        }
    fillTable(
        document.getElementById("firstname").value + " " + document.getElementById("lastname").value,
        document.getElementById("email").value, document.getElementById("phone").value,
        document.getElementById("bday").value, color, gender, activitiesChecked)});
   }

function fillTable(n, e, p, bd, fc, g, a){
    let row = document.createElement("tr");

    let name = document.createElement("td");
    let email = document.createElement("td");
    let phone = document.createElement("td");
    let bday = document.createElement("td");
    let fColor = document.createElement("td");
    let gender = document.createElement("td");
    let activityRow = document.createElement("td")
    let activity = document.createElement("ul");
    
    row.appendChild(name);
    row.appendChild(email);
    row.appendChild(phone);
    row.appendChild(bday);
    row.appendChild(fColor);
    row.appendChild(gender);

    a.forEach(element => {
        let activies = document.createElement("li");
        activies.innerHTML = element;
        activity.appendChild(activies);
    });
    activityRow.appendChild(activity);
    row.appendChild(activityRow);

    name.innerHTML = n;
    email.innerHTML = e;
    phone.innerHTML = p;
    bday.innerHTML = bd;
    fColor.innerHTML = fc;
    gender.innerHTML = g;

    document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].appendChild(row);
    }

function validInput(){
        
    let firstName = document.getElementById("firstname").value;
    let lastName = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let bday = document.getElementById("bday").value;
    let col = document.getElementById("color").value;
    if(document.querySelector('input[name = "gender"]:checked') != null){
        let gender = document.querySelector('input[name = "gender"]:checked').value;
    }else{
        alert("You must select a gender before submitting");
        return;
    }
   
    let color = document.getElementById("color").value;
    let activities = document.getElementsByClassName("activity");
    let activitiesChecked = [];
        for (var i=0; i<activities.length; i++) {
            if (activities[i].checked) {
                activitiesChecked.push(activities[i].value);
                }
        }
        if(activitiesChecked.length === 0){
            alert("You must select at least one activity before submitting");
            return false;
        }
        if (firstName === "" || lastName === "" || email === "" || 
		phone === "" || bday == "" || 
		col === "") 
	{
		alert("All fields are required. Names must be longer than two characters.");
		return false;
    }
    
    let regex = /[^0-9]+/g;
    let testReg = /[a-z]/gi;
    if(testReg.test(phone)){
        alert("The phone number entered is not valid. Phone numbers do not contain letters.");
		return false;
    }
	let processedStr = phone.replace(regex, "");

	if (processedStr.length < 10 || processedStr.length > 10 || processedStr === null) 
	{
        alert("The phone number entered is not valid. Phone numbers must be 10 digits in length." 
        +" We do not currently support international numbers.");
		return false;
    }
    return true;

    }

/* 05 
    Create a function openDetails() which opens the details element. 
    Invoke this function when the details’ summary is moused over. 
    The details should be hidden when the mouse is removed from the summary.
 */


function setDetailListners(){
    deats = document.getElementsByTagName("details");
    for (let i=0; i<deats.length; i++) {
        deats[i].setAttribute("onmouseenter", "openDetails(this)");
        deats[i].setAttribute("onmouseleave", "closeDetails(this)");
    }
}

function openDetails(node) {
	node.setAttribute("open", "");
}

function closeDetails(node) {
	node.removeAttribute("open");
} 


/* 06 
    Create a function that concatenates the inner HTML of all of the span elements 
    and prints the results to the console.
 */
function print(){
    pans = document.getElementsByTagName("span");

	let pansHTML = "";

	for (let i=0; i<pans.length; i++) {
		pansHTML += pans[i].innerHTML;
	}

	console.log(pansHTML);
}

/* 07 
    Create a function that displays the current time on earth in the span with id “earth_time”. 
    Invoke this function when “Earth time” button is clicked. 
 */

 function currentTime(){
    document.getElementById("earth_time").innerHTML = new Date();
 }
/* 08 
    Create two other functions which calculates and displays the time passed on Mars and 
    Alpha Centauri Bb if the onset of January 1st, 1970 occured at the same time. 
    Invoke the respective functions when their time buttons are clicked. An orbital period for Mars is 687 Earth days. 
    Using an external api to get the orbital period for Alpha Centauri Bb. 
    (try http://www.astropical.space/astrodb/apiref.php) Provide an implementation for getting this 
    value using both AJAX and the fetch API.
 */

 


/* 09 
    Three seconds after a user clicks on the “Intergalactic Directory” heading, 
    the background color should change to a random color. Make sure this color is 
    never black so we can still read our black text! (there are other dark colors it 
    could change to where we also couldn’t see the text but it’s enough to just 
    accomodate for a black background)
 */
function timedChange(node){
	let changeBackground = function (localNode) {
		const MAX = 255;
		const MIN = 64;

		let red = Math.floor(Math.random() * (MAX - MIN) + MIN + 1);
		let green =Math.floor(Math.random() * (MAX - MIN) + MIN + 1);
		let blue = Math.floor(Math.random() * (MAX - MIN) + MIN + 1);


		localNode.setAttribute("style", `background-color: rgb(${red}, ${green}, ${blue})`);
	}

	setTimeout(function () {changeBackground(node);}, 3000);
}

 /* 10 
    When inputs with id n1 and n2 have valid numerical input, perform the operation 
    specified in the select. Display the result in the element with id result.
 */
function calc(){

    let n1 = document.getElementById("n1").value;
    let n2 = document.getElementById("n2").value;
    let op = document.getElementById("operation");
    let result = document.getElementById("result");
    let regex = /[^0-9]+/g;
    console.log(n1+  " " + n2)
    if(!(n1 === "")){
        if(!(n2 === "")){
            if(!regex.test(n2) & !regex.test(n1)){
                switch(op.value){
                    case"Subtract":
                        result.innerHTML = n1 - n2;
                        break;
                    case"Add":
                        result.innerHTML = eval(n1) + eval(n2);
                        break;
                    case"Divide":
                        if (n2 == 0)
                            result.innerHTML = "Division by zero not alowed.";
                        else
                        result.innerHTML = n1/ n2;
                        break;
                    case"Multiply":
                        result.innerHTML = n1 * n2;
                        break;
                    default:
                        result.innerHTML = "You have managed to click an invalid option.";
                    }
                }
        }
        
    }

}

/* 11 
    Define function walkTheDom(node, func)
	This function should traverse every node in the DOM. 
    Use recursion. On each node, calle func(node).
 */
function walkTheDom(node, fun){
    fun(node);
 
	for (let i=0; i<node.childNodes.length; i++) {
        walkTheDom(node.childNodes[i], fun);
        console.log(node.nodeName + ": " + node.childNodes.length + " children. ");
	}
}