// 1. Make each link direct the user to its respective website (see id)

var googleLink = document.getElementsByName("google");
googleLink[0].innerHTML = '<a href="https://www.google.com/">Google</a?>';

var twitterLink = document.getElementsByName("twitter");
twitterLink[0].innerHTML = '<a href="https://twitter.com/">Twitter</a?>';

var slackLink = document.getElementsByName("slack");
slackLink[0].innerHTML = '<a href="https://www.google.com/">Slack</a?>';

var javadocsLink = document.getElementsByName("javadocs");
javadocsLink[0].innerHTML = '<a href="https://docs.oracle.com/javase/8/docs/api/">Javadocs</a?>';

// 2. Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)
var disablePluto = document.getElementById("planet").getElementsByTagName("option");

for (var i=0; i<disablePluto.length; i++){
    if (disablePluto[i].value == "Pluto"){
        disablePluto[i].disabled = true;
    }
}

// 3. Show hidden alien message when planet other than Earth is chosen
document.getElementById("planet").addEventListener("click",alienText);
function alienText(){
    let paragraphs = document.getElementsByTagName("p")
    let planets = document.getElementById("planet");
   
    if(planets.value !== "Earth"){
        paragraphs[5].hidden = false;
    } else {
        paragraphs[5].hidden = true;
    }
}

// 4. When the submit button is pressed, get the values from all of the input
//    check for valid input

function isValidEmail(email) {
    var reg = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
    return reg.test(email);
}

function isValidPhoneNumber(num) {
    num = num.trim(); 
    var numReg = /^[0-9]{3}[0-9]{3}[0-9]{4}$/;
    return numReg.test(num);
}



function addRow(name, email, phone, birthday, color, gender, activities){
    let row = document.createElement("tr");

    let cell1 = document.createElement("td");
    let cell2 = document.createElement("td");
    let cell3 = document.createElement("td");
    let cell4 = document.createElement("td");
    let cell5 = document.createElement("td");
    let cell6 = document.createElement("td");
    let cell7 = document.createElement("td");


    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    row.appendChild(cell5);
    row.appendChild(cell6);
    row.appendChild(cell7);

    cell1.innerHTML = name;
    cell2.innerHTML = email;
    cell3.innerHTML = phone;
    cell4.innerHTML = birthday;
    cell5.innerHTML = color;
    cell6.innerHTML = gender;
    cell7.innerHTML = activities;

    document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].appendChild(row);    
    
}

document.getElementById("form-sub").addEventListener("click", addNew);

function addNew(){

    // check length of name && validity of email
    if (document.getElementById("firstname").value.length > 1 
    && document.getElementById("lastname").value.length > 1
    && isValidEmail(document.getElementById("email").value) 
    && isValidPhoneNumber(document.getElementById("phone").value) 
    && document.getElementById("bday").value !== null 
    && document.getElementById("color").value !== null 
    && document.querySelector('input[name = "gender"]:checked') 
    && document.querySelector('input[type = "checkbox"]:checked')
    ){

        let fullName = document.getElementById("firstname").value + " " + document.getElementById("lastname").value;
        let email = document.getElementById("email").value;
        let phone = document.getElementById("phone").value;
        let birthday = document.getElementById("bday").value;
        let color = document.getElementById("color").value;
        let gender = document.querySelector('input[name = "gender"]:checked').value;
        let activities = document.querySelector('input[type = "checkbox"]:checked').value;

        addRow(fullName,email,phone,birthday,color,gender,activities);
    } else {
        alert ("You have an invalid input");
    }
}

// 5. Create a function openDetails() which opens the details element. Invoke this function when the details’ summary 
// is moused over. The details should be hidden when the mouse is removed from the summary.

document.getElementsByTagName("details")[0].addEventListener("mouseover", openDetails);
document.getElementsByTagName("details")[0].addEventListener("mouseout", closeDetails);


function openDetails() {
    document.getElementsByTagName("details")[0].open = true;
}
  
function closeDetails() {
    document.getElementsByTagName("details")[0].open = false;
}
  
// 6. Create a function that concatenates the inner HTML of all of the span elements and prints the results to the 
// console.

function concatSpans() {
    let allSpans = "";
    let spans = document.getElementsByTagName("span");
    for (var i=0; i<spans.length; i++){
        allSpans += spans[i].innerHTML;
    }
    console.log(allSpans);
}
// concatSpans();


//7. Create a function that displays the current time on earth in the span with id “earth_time”. Invoke this 
// function when “Earth time” button is clicked. 

document.getElementById("earth_time_check").addEventListener("click",earthTime);
function earthTime(){
    let eTime = new Date();
    document.getElementById("earth_time").innerHTML = eTime;
}

// 8. Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri Bb if the 
// onset of January 1st, 1970 occured at the same time. Invoke the respective functions when their time buttons 
// are clicked. An orbital period for Mars is 687 Earth days. Using an external api to get the orbital period 
// for Alpha Centauri Bb. (try http://www.astropical.space/astrodb/apiref.php) Provide an implementation for 
// getting this value using both AJAX and the fetch API.

document.getElementById("mars_time_check").addEventListener("click",marsTime);
function marsTime(){
    let pastTime = new Date(1970, 1, 1, 0, 0, 0);
    let currTime = new Date();
    let marsOrbit = 687;
    let timePast = (currTime.getTime() - pastTime.getTime())/(1000*60*60*24*marsOrbit);
    document.getElementById("mars_time").innerHTML = timePast + " years";
}

document.getElementById("acb_time_check").addEventListener("click",acbTime);
function acbTime(){
    let pastTime = new Date(1970, 1, 1, 0, 0, 0);
    let currTime = new Date();
    let acbOrbit = 550000*365;
    let timePast = (currTime.getTime() - pastTime.getTime())/(1000*60*60*24*acbOrbit);
    document.getElementById("acb_time").innerHTML = timePast + " years";
    //couldnt find api to use to find ACB orbial period
}

// 9. Three seconds after a user clicks on the “Intergalactic Directory” heading, the 	
// background color should change to a random color. Make sure this color is never black so we can 
// still read our black text! (there are other dark colors it could change to where we also couldn’t see 
// the text but it’s enough to just accomodate for a black background)


document.getElementsByTagName("h1")[0].addEventListener("click",waitTime);

function waitTime(){
    setTimeout(randomColorBackground, 3000);
}

function randomColorBackground(){
    let r = Math.floor(Math.random() * 120) + 120;
    let g = Math.floor(Math.random() * 120) + 120;
    let b = Math.floor(Math.random() * 120) + 120;
    var bgColor = "rgb(" + r + "," + g + "," + b + ")";
    // console.log(bgColor);
    document.body.style.background = bgColor;
}

// 10. When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the select. 
// Display the result in the element with id result.

function isValidNumber(numb) {
    var reg = /^[-+]?[0-9]*\.?[0-9]*$/;
    return reg.test(numb);
}

var num1 = document.getElementById("n1");
var num2 = document.getElementById("n2");
num1.addEventListener("keyup",calculator);
num2.addEventListener("keyup",calculator);
var answer;

function calculator(){
    var op = document.getElementById("operation").value;
    if (isValidNumber(document.getElementById("n1").value) 
    && isValidNumber(document.getElementById("n2").value)){
        n1 = Number.parseFloat(num1.value);
        n2 = Number.parseFloat(num2.value);

        switch(op){
            case "Add":
                answer = n1 + n2;
                document.getElementById("result").innerHTML = answer;
            break;
            case "Subtract":
                answer = n1 - n2;
                document.getElementById("result").innerHTML = answer;
            break;
            case "Multiply":
                answer = n1 * n2;
                document.getElementById("result").innerHTML = answer;
            break;
            case "Divide":
                answer = n1 / n2;
                document.getElementById("result").innerHTML = answer;
            break;
        }
    } else {
        alert("Invalid input");
    }
}


// 11. Define function walkTheDom(node, func)
// 	This function should traverse every node in the DOM. 
// 	Use recursion. On each node, calle func(node).


function walkTheDom(node, func) {

    func(node);
    node = node.firstChild;
    while (node) {
        walkTheDOM(node, func);
        node = node.nextSibling;
    } 
}

