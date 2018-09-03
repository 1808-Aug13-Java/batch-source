// JavaScript Assignment Interacting with the DOM
// > Create an external Javascript file to manipulate the Index.html file found in 
// the Week 3 folder

//1. Make each link direct the user to its respective website (see id)
// document.getElementsByTagName("google").setAttribute(href, "google.com");
let link1 = document.getElementsByName("google")[0];  
// console.log(link1); //testing
link1.href = "https://google.com";   //twitter, slack, javadocs
link1.innerHTML = "Google";
let link2 = document.getElementsByName("twitter")[0];
link2.innerHTML = '<a href = "https://twitter.com">Twitter</a>';
link3 = document.getElementsByName("slack")[0];
link3.innerHTML = '<a href = "https://slack.com">Slack</a>';
link4 = document.getElementsByName("javadocs")[0];
link4.innerHTML = '<a href = "https://javadocs.com">Javadocs</a>';
//2. Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)
// line 95. has <select class="form-control" id="planet">, then <option>s:
//          earth, mars, proxima, pluto
a = document.getElementById("planet");
a.options[3].disabled = true;  //options array lets u grab the <option> u want
//3. Define a function alienText() which shows the hidden element displaying an
//  alien message. When any planet other than Earth is selected, execute this 
//  function.

// document.getElementById("planet").options[2].addEventListener(
                // "onselect", alienText());
// document.getElementById("planet").options[1].onselect = alienText();
// document.getElementById("planet").options[2].onselect = alienText();
// console.log(document.getElementById("planet").value); testing
// https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_onchange_dom
document.getElementById("planet").onchange = function (){
    // // console.log(document.getElementsByTagName("p")); //it's the 5th <p>
    // selectedOption = document.getElementById("planet");
    // // console.log(selectedOption.options[selectedOption.selectedIndex].innerHTML);
    // if(selectedOption.options[this.selectedIndex].innerHTML
    //     !== "Earth")    //vs "hidden"
    //     document.getElementsByTagName("p")[5].hidden = false; 
    // else
    //     document.getElementsByTagName("p")[5].hidden = true;
    // // console.log(document.getElementsByTagName("p")[5]); testing getting the right <p>
    alienText();
};
function alienText(){
    // console.log(document.getElementsByTagName("p")); //it's the 5th <p>
    selectedOption = document.getElementById("planet");
    // console.log(selectedOption.options[selectedOption.selectedIndex].innerHTML);
    if(selectedOption.options[selectedOption.selectedIndex].innerHTML
                    // before i could put this.selectedIndex, but now cant
        !== "Earth")    //vs "hidden"
        document.getElementsByTagName("p")[5].hidden = false; 
    else
        document.getElementsByTagName("p")[5].hidden = true;
    // console.log(document.getElementsByTagName("p")[5]); testing getting the right <p>
}       
//4. When the submit button is pressed, get the values from all of the input into
//  a new row in the table below.  Make sure no input is empty, check that first 
//  and last name are at least two letters each. Validate for valid phone number 
//  and email structure. This should continue to work for multiple entries and rows.
document.getElementById("form-sub").addEventListener("click", addToTable);
function addToTable()
{
    //read in the values
    let firstName = document.getElementById("firstname").value;
    let lastName = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let birthday = document.getElementById("bday").value;
    // console.log(birthday === "");   If no bday entered, value is ""
    let favColor = document.getElementById("color").value;
    let gRadios = document.getElementsByName("gender");

    let activityFlag = false, genderFlag = false, gender;

    for(let i = 0; i < gRadios.length; i++) //google JS dom get radio value
        if(gRadios[i].checked) {//this radio button is checked/filled by user
            gender = gRadios[i].value;
            genderFlag = true;         
        }                           
    let activitiesChecked = [];        //google js dom get checkbox value
    let activityID = 0;
    let activities = document.getElementsByClassName("activity");
    activities[1].value = "stamp collecting";       //changing cuz "innerHTML"
    activities[3].value = "underwater basket weaving";  //doesnt work
    for(let i = 0; i < activities.length; i++)
        if(activities[i].checked)
        {
            activitiesChecked[activityID++] = activities[i].value; // it's actually
             //innerHTML value, but idk how to get taht  
            activityFlag = true;                   
        }  
                                  
    if(firstName === null || lastName === null || email === null || phone === null || 
    birthday === "" || favColor === null || genderFlag === false || 
    activityFlag === false)
    {
        //FIXME alert doesnt actually work .__. ptting them int eh wrong place i think
        // let alert1 = document.createElement("alert");
        // alert1.innerHTML = "Enter non-null values.";
        // document.getElementById("firstname").insertBefore(
        //     alert1, alert1.parentNode);
        alert("Enter non-null values.");
    }
    else if(firstName.length < 2 || lastName.length < 2)
    {
        // let alert2 = document.createElement("alert");
        // alert2.innerHTML = "Both names must each be 2 or more characters.";
        // // document.getElementById("firstname").insertBefore(
        // //     alert2, document.getElementById("firstname"));
        // document.getElementById("firstname").appendChild(alert2);
        alert("Both names must each be 2 or more characters.");
    }
    else if(!isValidEmail(email))
    {
        // let alert3 = document.createElement("alert");
        // alert3.innerHTML = "Invalid email.";
        // document.getElementById("email").appendChild(alert3);
        alert("Invalid email.");
    }
    else if(!isValidPhone(phone))
    {
        //FIXME make that function
    }
    else
    {
        //add a row
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

        let name = firstName + " " + lastName;
        cell1.innerHTML = name;
        cell2.innerHTML = email;
        cell3.innerHTML = phone;
        cell4.innerHTML = birthday;
        cell5.innerHTML = favColor;
        cell6.innerHTML = gender;
        let activityList = document.createElement("ul");
        for(let i = 0; i < activitiesChecked.length; i++){
            // cell7.innerHTML = activities;
            let listItem = document.createElement("li");
            listItem.innerHTML = activitiesChecked[i];
            activityList.appendChild(listItem);
        }
        cell7.appendChild(activityList);

        document.getElementsByTagName("tbody")[0].appendChild(row);
    }
}

function isValidPhone(phone)
{
    //FIXME add implementation
}

function isValidEmail(string)
{
    let indexAt = 0;
    let indexLastDot = 0;
    let existsAt = 0;
    let existsDot = false;
    for(i in string)
    {
        //check @ sign
        if(string[i] === "@")
        {
            indexAt = i;
            existsAt++;
        }
        //check index of . > index of @
        if(string[i] === ".")
        {
            indexLastDot = i;
            existsDot = true;
        }
    }

    if(existsAt !== 1)
        return false;
    if(existsDot === false)
        return false;
    if(indexLastDot > indexAt)  
        return false;
    //check . isnt at the end 
    if(indexLastDot === string.length - 1)
        return false;
    return true;
}
//5. Create a function openDetails() which opens the details element. Invoke this 
// function when the details’ summary is moused over. The details should be hidden 
// when the mouse is removed from the summary.
// console.log(document.getElementsByTagName("details")); //testing, making sure
//only one "details" tag
//Note: Remember that mouseover/mouseouts aren't part of 
//addEventListener. It's actually an attribute of the element ;)
document.getElementsByTagName("details")[0].setAttribute("onmouseover",
            "openDetails(this)");
document.getElementsByTagName("details")[0].setAttribute("onmouseout",
            // "closeDetails(this)");
            "this.open = false;");   //can do either "false;" or "false"
function openDetails(obj){
    obj.open = "true";
}                   //FIXME WHY DOESNT IT WORK IF I PUT CLOSE IN A FUNCTION????
// function closeDetails(obj){
//     console.log("ehhh");
//     obj.open = "false";
// }
//6. Create a function that concatenates the inner HTML of all of the 
// span elements 
// and prints the results to the console.
function innerHTMLOfSpanElems()
{
    let spanArray = document.getElementsByTagName("span");
    let concatenatedString = "";
    for(let i = 0; i < spanArray.length; i++)
        concatenatedString += spanArray[i].innerHTML;
    // console.log(concatenatedString); testing purpose
}
innerHTMLOfSpanElems();
//7. Create a function that displays the current time on earth in the span with id 
// “earth_time”. Invoke this function when “Earth time” button is clicked. 

//Date object NOTES:
//how to create a date, that displays curr time: 
//      d = new Date();    using Javascript DOM
// More Details: 7 numbers specify year, month, day, hour, minute, second, 
// and millisecond (in that order):
// var d = new Date(2018, 11, 24, 10, 33, 30, 0); ==> 10/24/2018 33hrs, 30 min
// if you specify less parameters, like
    // d=new Date(2018, 11), that's valid. it will just be November 2018

document.getElementById("earth_time_check").addEventListener
        ("click", displayCurrTimeOnEarth);
function displayCurrTimeOnEarth()
{
    document.getElementById("earth_time").innerHTML = Date();
}
//8. Create two other functions which calculates and displays the time passed on 
// Mars 
// and Alpha Centauri Bb if the onset of January 1st, 1970 occured at the same 
// time. Invoke the respective functions when their time buttons are clicked. 
// An orbital period for Mars is 687 Earth days. Using an external api to get 
// the orbital period for Alpha Centauri Bb. 
// (try http://www.astropical.space/astrodb/apiref.php) Provide an implementation
// for getting this value using both AJAX and the fetch API.
document.getElementById("mars_time_check").addEventListener("click", timePassedOnMars);
function timePassedOnMars()
{
    // console.log(Date() - Date(1970, 1, 1, 0, 0, 0)); //this gives NaN :((
    let pastTime = new Date(1970, 1, 1, 0, 0, 0);
    let currTime = new Date();
    let timePassed = (currTime.getTime() - pastTime.getTime());
    let timePassedDays = ((((timePassed /1000) / 60)/60)/24);
    let marsYearsPassed = timePassedDays/687;
    document.getElementById("mars_time").innerHTML = marsYearsPassed
        +" years passed on Mars";
}
    //FIXME didn't do Alpha Centauri Bb
//9. Three seconds after a user clicks on the “Intergalactic Directory” heading, 
// e 	background color should change to a random color. Make sure this color 
// is never black so we can still read our black text! (there are other dark 
// colors it could change to where we also couldn’t see the text but it’s enough 
// to just accomodate for a black background)
    // let arrray = document.getElementsByTagName("h1");
    // for(i in arrray)
    //     console.log(arrray[i]);
    // testing: just seeing if there's only one h1 elem
//Note: how to do functions 3 sec after clicking some elem
// console.log(document.getElementsByTagName("h1")[0]);
document.getElementsByTagName("h1")[0].addEventListener("click", 
                    function (){ setTimeout(changeColor, 3000); });
// click = "setTimeout(changeColor, 3000)"; //<== NO WORK :(
// function changeColorAfter3()  //    <=== B4 I put this instead of function(){}
// {
//     setTimeout(changeColor, 3000);
// }
function convertDecToHex(decimal)
{
    if(decimal < 10)
        return decimal;
    else //10 -> 15
    {
        // number = decimal - 10. 
        // make array 0->5 with {ABCDEF}
        // return array[number] 
        let hexDigits = "ABCDEF";
        return hexDigits[decimal - 10];
    }
}

function changeColor(){
    // console.log("does it work?"); //testing if executes 3 sec after click
    //to get color not completely black, first hex digit must be >= 4
    //Math.floor() will make 3.243241412 => 3
    //Math.random() will make 0->1 number
    //we want 0->15 0123456789012345
    //              0123456789abcdef
    //math.floor(math.random() * 16) will give 0->15
    let str = "#"; //start the new string
    let randomHexDigit = convertDecToHex(Math.floor( Math.random() * 16 ));
    while(randomHexDigit < 5) //for the first one, make it greater than = 4
        randomHexDigit = convertDecToHex(Math.floor( Math.random() * 16 ));
    str += randomHexDigit; //you can do while loop without {}

    for(let i = 1; i < 6; i++)
        str += convertDecToHex(Math.floor( Math.random() * 16 ));
        // console.log(str); //for testing
    document.getElementsByTagName("body")[0].style.backgroundColor = str;
    // console.log(str);
}
//10. When inputs with id n1 and n2 have valid numerical input, perform the operation 
// specified in the select. Display the result in the element with id result.

// console.log(num1); //testing its default value is UNDEFINED!!
// https://www.w3schools.com/jsref/event_onkeydown.asp  <===SO HELPFUL
document.getElementById("n1").addEventListener("keyup", calculate); 
document.getElementById("n2").addEventListener("keyup", calculate);
// for when operation gets changed. select stmt usually uses onchange.
// event listener version is just change
// https://www.w3schools.com/jsref/tryit.asp?filename=tryjsref_onchange_dom
document.getElementById("operation").addEventListener("change", calculate);
// console.log(document.getElementById("n1"));
function calculate()
{
    let num1 = Number(document.getElementById("n1").value);
    let num2 = Number(document.getElementById("n2").value);
    // if(isNaN(num3))          //I used to do === NaN. Doesnt work. use isNaN()
    //     console.log("yay");
    // console.log(num1);

    // let noDigit = new RegExp("\D+");
    // if(noDigit.test(document.getElementById("n1").value) )
    //     console.log("here");
    if(isNaN(num1) || isNaN(num2)) //if obj, update the inputNumber
        return;
    else
    {
        //if both exist now, run calculate :D
        let arrayOp = "+-/*";
        let operations = document.getElementById("operation").options, operation;
        // console.log(operations);
        // for(i in operations)    <=== .checked is for input type radio
        //     if(operations[i].checked)  <== logging operations shows there is selectedIndex
        operation = operations[operations.selectedIndex].innerHTML;
        // console.log(operation); //should be add, subtract, divid, or multiply
        let total;
        switch(operation)
        {
            case "Add":
                total =  num1 + num2;
                break;
            case "Subtract":
                total = num1 - num2;
                break;
            case "Divide":
                total = num1 / num2;
                break;
            case "Multiply":
                total = num1 * num2;
        }
        
        document.getElementById("result").innerHTML = total;
    }
}

//11. Define function walkTheDom(node, func)
// 	This function should traverse every node in the DOM. 
// 	Use recursion. On each node, calle func(node).
function walkTheDom(node, func)
{
    func(node);
    node = node.firstChild;  //go down. DFS
    while(node){
        walkTheDom(node, func); // because .nextSibling is after all the recur
        node = node.nextSibling;//sive calls, it will go all the way down first
                                //until node = node.firstChild === null
                                //now at the bottom, 
    }
}
