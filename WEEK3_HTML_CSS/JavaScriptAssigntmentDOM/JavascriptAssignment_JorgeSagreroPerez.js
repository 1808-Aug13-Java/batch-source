/*Jorge Sagrero-Perez
JavaScript Assignment Interacting with the DOM*/
window.onload = function(){
    document.getElementById("planet").addEventListener("click", alienRange);
    document.getElementById("form-sub").addEventListener("click", checkInput);
    document.getElementsByTagName("details")[0].firstElementChild.addEventListener("mouseover",outDetails);
    document.getElementsByTagName("details")[0].firstElementChild.addEventListener("mouseout",inDetails);
    document.getElementById("earth_time_check").addEventListener("click", earthTime);
    document.getElementById("mars_time_check").addEventListener("click",marsTime);
    document.getElementById("acb_time_check").addEventListener("click",searchKnownUniverse);
    document.getElementById("acb_time_check").addEventListener("click",searchKnownUniverseWithFetch);
    document.getElementsByTagName("h1")[0].addEventListener("click",waitUpBuddy);
    document.getElementById("n1").addEventListener("input", calculate);
    document.getElementById("n2").addEventListener("input", calculate);


}


var phoneNumbers =  /^\d{10}$/;         ///^(\+0?1\s)?\(\d{3}\)[\s.-]?\d{3}[\s.-]?\d{4}$/;
var phones = "Required format: \n##########\n";
var detai = console.log(document.getElementsByTagName("details")[0].firstElementChild)
// the following function uses fetch api to get the orbital period of alpha centauri Bc
function searchKnownUniverseWithFetch () {
    var url = "http://www.astropical.space/astrodb/api-exo.php?which=name&limit=Proxima Centauri b&format=json"
fetch(url, {
	method: 'get'
}).then(function(response) { //.json() returns a Promise object, use .then to get the contents
    response.json().then(function(data) {
        var period = data.exoplanets[0].per
        var d = new Date();
        var date1 = new Date('January 1, 1970 00:00:00');
        var result = d - date1;
        result = result/1000
        result = result/(period*24*60*60)
        document.getElementById("acb_time").innerHTML = "Number of acb years passed since January 1, 1970 00:00:00 " +result;
        console.log(data.exoplanets[0].per);
        // do something with your data
      });
}).catch(function(err) {
	// Error :(
});
}
//=====================================================================

/* 
Syntax: http://www.astropical.space/astrodb/api-exo.php?which=xxx&limit=xxx&format=csv|xml|json

which= supported are: name | mass [xJ] | radius [xJ] | distance [psc] | period [days] | sma [AU] | disc [year] | esi | zone | class
limit= is the query limit, such as 0.1 when which=mass, to limit output to exoplanets with masses up to 0.1 jovian.
format= either of: csv | xml | json (CSV if format is omitted)
ex. need:Alpha Centauri Bb
api-exo.php?which=name&limit=gj 625 b&format=xml
*/

// the following function uses AJAX to get the orbital period of alpha centauri Bc

function searchKnownUniverse() {
// i could not find alpha centauri Bb so i am going to use alpha centauri bc
    var url = "http://www.astropical.space/astrodb/api-exo.php?which=name&limit=Proxima Centauri b&format=json"
    let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
    xhr.onreadystatechange = function() {
        if(this.readyState === 4 && (this.status > 199 && this.status < 300)) {
            let scanner = JSON.parse(this.responseText); 
            var period = `${scanner.exoplanets[0].per}`;
            console.log(scanner.exoplanets[0].per);
            var d = new Date();
            var date1 = new Date('January 1, 1970 00:00:00');
            var result = d - date1;
            result = result/1000
            result = result/(period*24*60*60)
            document.getElementById("acb_time").innerHTML = "Number of acb years passed since January 1, 1970 00:00:00 " +result;
        }
    }
    xhr.open("GET", url);
    xhr.send();
}

////====================================================================--------
// This function goes through each node of the html file index and displays the node on console

function walkTheDom(node, func) {
    var children = node.childNodes;
    for (var i = 0; i < children.length; i++) 
        walkTheDom(children[i], func);
    func(node);
}

function func(node) {
    console.log("inside of " + node + " node");
    return;

}
walkTheDom(document,func); 


//The following function performs the calculation of the calculator and displays results
function calculate () {
    console.log(document.getElementById("n1").value); 

    if((document.getElementById("n1").value.match((/^\d{1,}$/))) && (document.getElementById("n2").value.match(/^\d{1,}$/))) {
        console.log(document.getElementById("operation").value); 
        if(document.getElementById("operation").value === "Divide") {
            if(document.getElementById("n2").value == 0) {
                document.getElementById("result").innerHTML = "UNDEFINED CANNOT DIVIDE BY ZERO";
                return;
            }
            else {
                document.getElementById("result").innerHTML = document.getElementById("n1").value / document.getElementById("n2").value


            }
        }
        else if(document.getElementById("operation").value ==="Subtract") {
            document.getElementById("result").innerHTML = document.getElementById("n1").value - document.getElementById("n2").value
        }
        else if(document.getElementById("operation").value ==="Add") {
            document.getElementById("result").innerHTML = parseInt(document.getElementById("n1").value) + parseInt(document.getElementById("n2").value)
        }
        else {
            document.getElementById("result").innerHTML = document.getElementById("n1").value * document.getElementById("n2").value
        }
        //document.getElementById("result").innerHTML = document.getElementById("n1")

        //document.getElementById("result")
    }
    console.log("hello from calculate") 
}

// causes a three second delay before calling the function to change background
function waitUpBuddy() {
    setTimeout(colorBaby, 3000); // wait 10 seconds

}

//changes the background color excluding the color black
function colorBaby() {
    let x = Math.floor(Math.random() * 255) +1;
    let y = Math.floor(Math.random() * 255) +1;
    let z = Math.floor(Math.random() * 255) +1;   
    let rbgColor = "rgb(" + x + "," + y + "," + z + ")";
    
      
    document.body.style.background = rbgColor;
}


//calculates and displays the number of years on mars that have passed since the january 1, 1970
function marsTime() {
    var d = new Date();
    var date1 = new Date('January 1, 1970 00:00:00');
    var result = d - date1;
    result = result/1000
    var earthDays = result/(365*24*60*60)
    console.log("number of earth years passed since january 1, 1970 00:00:00 " +earthDays);
    result = result/(687*24*60*60)
    console.log("number of mars years passed since january 1, 1970 00:00:00 " +result);
    document.getElementById("mars_time").innerHTML = "Number of mars years passed since January 1, 1970 00:00:00 " +result;
    return;
}


// displays the current time on earth
function earthTime() {
    var d = new Date();
    document.getElementById("earth_time").innerHTML = new Date();
}
//makes the details visible
function outDetails() {
    //console.log("hello from outdetails")
    document.getElementsByTagName("details")[0].open = true;
}

//makes the details hidden
function inDetails() {
    document.getElementsByTagName("details")[0].open = false;
}

//connects the innerhtml of the span tags and dispalys on console
function intergalacticBobRoss() {
    var bobRoss = document.getElementsByTagName("span")
    var bobholder = "";
    for(rossBob of bobRoss) {
        //console.log(rossBob)
        bobholder = bobholder + rossBob.innerHTML;
    }
    console.log(bobholder)
}
intergalacticBobRoss();

var em = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/


// validates the user input and if all checked fields are valid, then a new row is inserted in the table with the user input
function checkInput() {
    console.info(document.getElementsByTagName("details"));

    let mat = (/^[a-z ,.'-]{0,1}$/i)
    let inputs = document.getElementsByTagName("input");
    console.log(document.getElementById("bday").value)
    if(((document.getElementById("firstname").value == "")||(document.getElementById("lastname").value == "")
    ||(document.getElementById("email").value == "")||(document.getElementById("phone").value == "")
    ||(document.getElementById("bday").value == "")
    || ((inputs[5].checked === false) && (inputs[6].checked === false) && (inputs[7].checked === false)))) {
        alert("Please Fill in all Fields")
        return;
    }
    else if(!(document.getElementById("phone").value.match(phoneNumbers))) {
        alert("Phone number is not formatted properly. Please enter your 10 digit number no spaces\n\n##########\n");
    }
    else if(!(document.getElementById("firstname").value.match((/^[a-z]{2,}$/i)))) {
        alert("Please enter your first name with at least two letters")

    }
    else if(!(document.getElementById("lastname").value.match((/^[a-z ,.'-]{2,}$/i)))) {
        alert("Please enter your last name with at least two letters")

    }
    else if(!(document.getElementById("email").value.match(em))) {
        alert("Please enter a valid Email address")
    }
    else {
        let table = document.getElementsByClassName("table table-striped table-bordered table-hover table-sm")[0].getElementsByTagName("tbody")[0]
        let row = table.insertRow(0);
        let cell1 = row.insertCell(0);
        let cell2 = row.insertCell(1);
        let cell3 = row.insertCell(2);
        let cell4= row.insertCell(3);
        let cell5 = row.insertCell(4);
        let cell6 = row.insertCell(5);
        let cell7 = row.insertCell(6);
        let ul1 = document.createElement("ul");
        
        cell1.innerHTML = document.getElementById("firstname").value;
        cell2.innerHTML = document.getElementById("email").value;
        cell3.innerHTML = document.getElementById("phone").value;
        cell4.innerHTML = document.getElementById("bday").value;
        cell5.innerHTML = document.getElementById("color").value;
        if(inputs[5].checked === true){
            cell6.innerHTML = inputs[5].value;

        } 
        else if (inputs[6].checked === true) {
            cell6.innerHTML = inputs[6].value;

        }
        else {
            cell6.innerHTML = inputs[7].value; 
        }


        var activ = document.getElementsByClassName("activity");
        for(x = 0; x<activ.length;x++) {
            if(activ[x].checked) {
                var item = document.createElement('li');
                console.log(inputs)
                item.appendChild(document.createTextNode(activ[x].nextSibling.textContent));
                ul1.appendChild(item);
                
            }
        }
        cell7.appendChild(ul1);
        return;
        
    }
}

// makes the hidden message invisible if earth is selected, and visible if the other options are chosen
function alienRange() {
    let e = document.getElementById("planet");
    let element = document.body.getElementsByTagName("p");
    let b = findAlien(element);
    
    if(e.options[e.selectedIndex].value == "Earth") {
        element[b].hidden = true;

    }
    else {
        element[b].hidden = false;

    }
    //console.log(e.options[e.selectedIndex].value);
}




// sets the links href attribute's to their respective webpages
document.getElementsByName("google")[0].setAttribute("href","https://www.google.com/");
document.getElementsByName("twitter")[0].setAttribute("href", "https://twitter.com/?lang=en");
document.getElementsByName("slack")[0].setAttribute("href", "https://slack.com/");
document.getElementsByName("javadocs")[0].setAttribute("href", "https://javadocs.com/");


//sets the links href attributes to their respective webpages using the jquery instead, but jquery is not used in this assignment
//.setAttribute("href", "https://www.google.com/");
//console.log(holder[0]);
//console.log(holder[1]);
//.href("https://www.google.com/");
//console.log(holder)
/*src="otherPicture.jpg"
var element = $("a[name='google']").attr("href", "https://www.google.com/");
var element = $("a[name='twitter']").attr("href", "https://twitter.com/?lang=en");

var element = $("a[name='slack']").attr("href", "https://slack.com/");

var element = $("a[name='javadocs']").attr("href", "https://javadocs.com/");


// problem 2 hides Pluto
var element = $('select option:contains("Pluto")').hide(); */
document.getElementById("planet")[3].setAttribute("hidden","true")
/*
3.	Define a function alienText() which shows the hidden element displaying an alien message. 
When any planet other than Earth is selected, execute this function.
<p hidden>Beep boop</p>
*/
//var element = document.body.getElementsByTagName("p");

// function finds the tag with the hidden message and returns the index of where it is at to the function alien range
function findAlien(array) {
    for(a = 0; a < array.length; a++) {
        if((array[a].innerText == "Beep boop")) {
            return a
        }
        //console.log(a.innerText);
    }
}

// below are some notes
//let b = findAlien(element);
//element[b].hidden = false;
//console.log(element)
//console.log(element)
//var element = $('p:contains("Beep boop")').setAttribute("hidden","false")
//console.log(element);

   /*
    var options = [
        set0 = ['Option 1','Option 2'],
        set1 = ['First Option','Second Option','Third Option']
    ];

function makeUL(array) {
    // Create the list element:
    var list = document.createElement('ul');

    for (var i = 0; i < array.length; i++) {
        // Create the list item:
        var item = document.createElement('li');

        // Set its contents:
        item.appendChild(document.createTextNode(array[i]));

        // Add it to the list:
        list.appendChild(item);
    }

    // Finally, return the constructed list:
    return list;
}

// Add the contents of options[0] to #foo:
document.getElementById('foo').appendChild(makeUL(options[0]));
*/

