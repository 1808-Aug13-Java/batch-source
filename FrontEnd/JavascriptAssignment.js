/*JavaScript Assignment Interacting with the DOM
>>>Create an external Javascript file to manipulate the Index.html file found in the Week 3 folder

>>>Make each link direct the user to its respective website (see id)

>>>Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)

>>>Define a function alienText() which shows the hidden element displaying an alien message. When any 
    planet other than Earth is selected, execute this function.

>>>When the submit button is pressed, get the values from all of the input into a new row in the table below.  
    Make sure no input is empty, check that first and last name are at least two letters each. Validate for 
    valid phone number and email structure. This should continue to work for multiple entries and rows.

>>>Create a function openDetails() which opens the details element. Invoke this function when the details’ 
    summary is moused over. The details should be hidden when the mouse is removed from the summary.

>>>Create a function that concatenates the inner HTML of all of the span elements and prints the results to 
    the console.

>>>Create a function that displays the current time on earth in the span with id “earth_time”. Invoke 
    this function when “Earth time” button is clicked. 

>>>Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri Bb 
    if the onset of January 1st, 1970 occured at the same time. Invoke the respective functions when 
    their time buttons are clicked. An orbital period for Mars is 687 Earth days. Using an external api 
    to get the orbital period for Alpha Centauri Bb. (try http://www.astropical.space/astrodb/apiref.php) 
    Provide an implementation for getting this value using both AJAX and the fetch API.

>>>Three seconds after a user clicks on the “Intergalactic Directory” heading, the background color should 
    change to a random color. Make sure this color is never black so we can still read our black text! 
    (there are other dark colors it could change to where we also couldn’t see the text but it’s enough 
    to just accomodate for a black background)

>>>When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the select. 
    Display the result in the element with id result.

>>>Define function walkTheDom(node, func)
	This function should traverse every node in the DOM. 
	Use recursion. On each node, call func(node).
*/

document.getElementsByTagName("a")[0].setAttribute("href","https://www.google.com");
document.getElementsByTagName("a")[1].setAttribute("href","https://twitter.com");
document.getElementsByTagName("a")[2].setAttribute("href","https://slack.com");
document.getElementsByTagName("a")[3].setAttribute("href","https://docs.oracle.com/javase/7/docs/api/");

document.getElementsByTagName("option")[3].setAttribute("disabled",true);

document.getElementById("planet").addEventListener("click",alienText);
function alienText(){
    let pars = document.getElementsByTagName("p")
    pars[5].hidden = document.getElementById("planet").selectedIndex == 0? true : false;
}

document.getElementsByClassName("activity")[3].value = "underwater basket weaving";
document.getElementsByClassName("activity")[2].value = "stamp collecting";
document.getElementsByClassName("gender")[0].value = "Male";
document.getElementsByClassName("gender")[1].value = "Female";
document.getElementsByClassName("gender")[2].value = "Other";
document.getElementById("form-sub").addEventListener("click",function(){
    let fName = document.getElementById("firstname");
    let lName = document.getElementById("lastname");
    let email = document.getElementById("email");
    let phone = document.getElementById("phone");
    let bday = document.getElementById("bday");
    let planet = document.getElementById("planet");
    let gender = document.querySelector('input[name = "gender"]:checked');
    let color = document.getElementById("color");
    let active = document.querySelectorAll('input[class = "activity"]:checked');


    if (fName.value.length <= 2){
        return;
    }
    else
        fName = fName.value;
    if (lName.value.length <= 2){
        return;
    }
    else
        lName = lName.value;
    if (email.value.search(/[\w|\d]*@[\w]*\.[\w]*/g) == -1){
        return;
    }
    else
        email = email.value;
    if (phone.value.search(/(\(\d{3}\)|\d{3})[ |\-]?\d{3}[ |\-]?\d{4}/g) == -1){
        return;
    }
    else
        phone = phone.value;
    if (bday.value === ""){
        return;
    }
    else
        bday = bday.value;
    if (gender == null){
        return;
    }

    let row = [];
    row.push(`<tr>
        <td>
            ${fName}
        </td><td>
            ${email}
        </td><td>
            ${phone}
        </td><td>
            ${bday}
        </td><td>
            ${color.value}
        </td><td>
            ${gender.value}
        </td><td>
            <ul>`);
            
    if(active.length == 0)
        row.push(`<li>NONE</li>`);
    else
    for(a of active)
        row.push(`<li>${a.value}</li>`);

    row.push(`</ul>
            </td>
        </tr>`);

    let table = document.getElementsByTagName("tbody")[0];
    table.innerHTML = table.innerHTML + row.join("");
});

document.getElementsByTagName("details")[0].addEventListener("mouseover",openDetails);
document.getElementsByTagName("details")[0].addEventListener("mouseout",closeDetails);
function openDetails(){
    this.open = true;
}
function closeDetails(){
    this.open = false;
}

function concatInnerHTML(){
    let spans = document.getElementsByTagName("span");
    let out = "";
    for (each of spans)
        out += each.innerHTML;
    console.log(out);
}

document.getElementById("earth_time_check").addEventListener("click",earthTime);
function earthTime(){
    let now = new Date();
    document.getElementById("earth_time").innerHTML = "" + now.getHours() + ":" + now.getMinutes() + "." + now.getSeconds();
}




document.getElementById("n1").addEventListener("input",calculate);
document.getElementById("n2").addEventListener("input",calculate);
document.getElementById("operation").addEventListener("change",calculate);
function calculate(){
    let out = document.getElementById("result");
    let op = document.getElementById("operation");
    let n1 = document.getElementById("n1");
    let n2 = document.getElementById("n2");
    if (n1.value.search(/\D/g) > -1)
        n1.value = "";
    if (n2.value.search(/\D/g) > -1)
        n2.value = "";   
    
    if (n1.value == "" || n2.value == "")
        out.innerHTML = "Insert valid number in both fields.";
    else
        switch(op.value){
            case"Subtract":
                out.innerHTML = n1.value - n2.value;
                break;
            case"Add":
            out.innerHTML = eval(n1.value) + eval(n2.value);
                break;
            case"Divide":
                if (n2.value == 0)
                    out.innerHTML = "Err: Division by 0";
                else
                    out.innerHTML = n1.value / n2.value;
                break;
            case"Multiply":
            out.innerHTML = n1.value * n2.value;
                break;
            default:
                out.innerHTML = "How in the actual fuck??";
        }
}

document.getElementsByTagName("h1")[0].onclick = makeDelayedHandler(colorChange, 3000);
function colorChange(e) {
    var colorValues = ["coral", "red", "blue", "green", "yellow", "light-green", "dark-green"];
    var color = colorValues[Math.floor(Math.random() * 7)];
    document.getElementsByTagName("h1")[0].style.backgroundColor = color;
    clearTimeout();
}
function makeDelayedHandler(func, time) {
    return function( e ){
        var ev = e ;
        setTimeout( function(){
            func( ev );
        }, time );        
    };
}

function walkTheDom(node,func){
    func(node);
    for(n of node.childNodes)
        walkTheDom(n,func);
}

function testFunc(node){
    console.log(node.outerHTML);
}