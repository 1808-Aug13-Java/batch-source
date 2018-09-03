var $ = document;
//JavaScript Assignment Interacting with the DOM
//> Create an external Javascript file to manipulate the Index.html file found in the Week 3 folder
//Make each link direct the user to its respective website (see id)
var as = document.getElementsByTagName("a"); 
for(var i=0; i<as.length; i++)
{
  var url = "https://" +  as.item(i).getAttribute("name") + ".com";
  as.item(i).setAttribute("href", url);
}
//Disable the Pluto Planet of Residency option. (Pluto isn’t a planet!!)
var planet = document.getElementById("planet");
for(s of planet) {
  if(s.value === "Pluto") {
    s.setAttribute("disabled", true);
  }
}

//Define a function alienText() which shows the hidden element displaying an alien message. When any planet other than Earth is selected, execute this function.
var alienText = function() {
  var hiddens = document.getElementsByTagName("p");
  for(p of hiddens) {
    if(p.attributes.hidden) {
      alert(p.textContent);
    }
  }
}
planet.addEventListener("change", function() {
  if(this.value !== "Earth") {
    alienText();
  }
});
//When the submit button is pressed, get the values from all of the input into a new row in the table below.  Make sure no input is empty, check that first and last name are at least two letters each. Validate for valid phone number and email structure. This should continue to work for multiple entries and rows.

var sub = $.getElementById("form-sub");
sub.addEventListener("click", function() {
  var firstname = $.getElementById("firstname");
  var lastname = $.getElementById("lastname");
  var email = $.getElementById("email");
  var phone = $.getElementById("phone");
  var bday = $.getElementById("bday");
  var selectedPlanet = planet.selectedIndex;
  var getSelected = function(tag, type, multiple=false) {
    var radio = $.getElementsByTagName(tag);
    var selecteds = [];
    for(r of radio) {
      if((r.getAttribute("type") === type) && (r.checked === true))
        selecteds.push(r.value);
    }
    if(multiple) 
      return selecteds;
    else
      return selecteds[0];
  }
  var selectedGender = getSelected("input", "radio");
  var selectedActivities = getSelected("input", "checkbox", true); //return array =true
  var selectedColor = $.getElementById("color"); 
  var table = $.getElementsByTagName("tbody")[0];
  var createColumn = function(el) {
    var column = $.createElement("td"); 
    var createLi = function(txt) {
      var li = $.createElement("li");
      var text = $.createTextNode(txt);  
      li.appendChild(text);
      return li;
    }
    if(Array.isArray(el)) {
      var ul = $.createElement("ul");
      for(var i of el) {
        ul.appendChild(createLi(i));
      }
      column.appendChild(ul);
    }
    else {
      var text = $.createTextNode(el);
      column.appendChild(text);
    }
    return column; 
  }
  //newColumn.appendChild($.createTextNode(firstname.value));
  var newRow = $.createElement("tr");
  newRow.appendChild(createColumn(firstname.value));
  newRow.appendChild(createColumn(email.value));
  newRow.appendChild(createColumn(phone.value));
  newRow.appendChild(createColumn(bday.value));
  newRow.appendChild(createColumn(selectedColor.value));
  newRow.appendChild(createColumn(selectedGender));
  newRow.appendChild(createColumn(selectedActivities));
  table.appendChild(newRow);
});


//Create a function openDetails() which opens the details element. Invoke this function when the details’ summary is moused over. The details should be hidden when the mouse is removed from the summary.
var openDetails = function() {
  this.open = true;
  details.addEventListener("mouseout", function() {
    this.open = false;
  });
}

var details = $.getElementsByTagName("details")[0];
details.addEventListener("mouseover", openDetails);

//Create a function that concatenates the inner HTML of all of the span elements and prints the results to the console.
var concatSpan = function() {
  var spans = $.getElementsByTagName("span");
  var res = "";
  for(var s of spans) {
    res += s.innerHTML;
  }
}

//Create a function that displays the current time on earth in the span with id “earth_time”. Invoke this function when “Earth time” button is clicked. 
var getEarthTime = function() {
  var d = new Date();
  var zeroPad = function(n) {
    if(n.toString().length === 1) {
      return "0" + n;
    }
    else {
      return n;
    }
  }
  console.log(zeroPad(d.getHours()) + ":" + zeroPad(d.getMinutes()) + ":" + zeroPad(d.getSeconds()));
}
var earthTimeButton = $.getElementById("earth_time_check");
earthTimeButton.addEventListener("click", getEarthTime);

//Create two other functions which calculates and displays the time passed on Mars and Alpha Centauri Bb if the onset of January 1st, 1970 occured at the same time. Invoke the respective functions when their time buttons are clicked. An orbital period for Mars is 687 Earth days. Using an external api to get the orbital period for Alpha Centauri Bb. (try http://www.astropical.space/astrodb/apiref.php) Provide an implementation for getting this value using both AJAX and the fetch API.
var getYears = function(ms, period) {
  var ss = ms/1000;
  var mm = ss/60;
  var hh = mm/60;
  var dd = hh/24;
  var yy = dd/period;
  return yy;
}
var getMarsTime = function() {
  var d = new Date();
  var marsTime = $.getElementById("mars_time");
  marsTime.innerHTML = getYears(d.getTime(), 687);
}

var marsTimeCheck = $.getElementById("mars_time_check");
marsTimeCheck.addEventListener("click", getMarsTime);


var acbTimeCheck = $.getElementById("acb_time_check");
acbTimeCheck.addEventListener("click", function() {
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function() {
    if((xhr.readyState === 4) && (xhr.status === 200)) {
      //console.log(xhr.responseText);
      var exoplanets = JSON.parse(xhr.responseText).exoplanets;
      for(var p of exoplanets) {
        if(p.id === 123) {
          var d = new Date();
          var acbTime = $.getElementById("acb_time");
          acbTime.innerHTML = getYears(d.getTime(), p.per);
        }
      }
    }
    else {
      console.log("nope");
      console.log(xhr.readyState);
      console.log(xhr.status);
    }
  }
  xhr.open('get', 'http://www.astropical.space/astrodb/api-exo.php?which=name&limit=0&format=json', true);
  xhr.send();
});
//Three seconds after a user clicks on the “Intergalactic Directory” heading, the     background color should change to a random color. Make sure this color is never black so we can still read our black text! (there are other dark colors it could change to where we also couldn’t see the text but it’s enough to just accomodate for a black background)
var interspans = $.getElementsByTagName("span");
var getRandomColor = function() {
    var colorCode = "";
    colorCode = Math.floor(Math.random()*100+1) + ',';
    colorCode += Math.floor(Math.random()*100+1) + '%,';
    colorCode += Math.floor(Math.random()*100+1) + '%,';
    colorCode += Math.random()*(0.7-0.2) + 0.2;

    return 'hsla(' + colorCode + ')';
    
}
var findInter = function() {
  for(var s of interspans) {
    if(s.innerHTML === "Intergalactic ") {
      return s;
    }
  }
}

var s = findInter().parentNode;
s.addEventListener("click", function() {
    setTimeout(function()  {
      var body = $.getElementsByTagName("body")[0];
      var style = "background-color:" + getRandomColor();
      body.setAttribute("style", style);
    }, 3000);
});
//When inputs with id n1 and n2 have valid numerical input, perform the operation specified in the select. Display the result in the element with id result.
var op = $.getElementById("operation");
var n1e = $.getElementById("n1");
var n2e = $.getElementById("n2");
var doMath = function() {
  var res = 0;
  var n1 = parseInt(n1e.value);
  var n2 = parseInt(n2e.value);
    if(!isNaN(n1) && !isNaN(n2))
    {
      if(op.value === 'Add') {
        res = n1 + n2;
      }
      if(op.value === 'Subtract') {
        res = n1 - n2;
      }
      if(n2 !== 0 && op.value === 'Divide') {
        res = n1/n2;
      }
      if(op.value === 'Multiply') {
        res = n1 * n2;
      }
      var resultEl = $.getElementById("result");
      resultEl.innerHTML = res;
    }
  }

n1e.addEventListener("input", doMath);
n2e.addEventListener("input", doMath);
op.addEventListener("change", doMath);

//Define function walkTheDom(node, func)
//    This function should traverse every node in the DOM. 
//    Use recursion. On each node, calle func(node).

var walkTheDom = function(node, func) {
  func(node);
}

var func = function(node) {
  if((node) && (node.hasChildNodes())) {
    console.log(node);
    func(node.firstElementChild);
  }
  if((node) && (node.nextSibling)) {
    console.log(node);
    func(node.nextSibling);
  }
  return;
}
walkTheDom($.getElementsByTagName("body")[0], func);
