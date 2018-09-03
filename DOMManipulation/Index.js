//1

var google = document.getElementsByName("google");
google[0].innerHTML = "Google";
google[0].setAttribute("href", "https://www.google.com/");

var twitter = document.getElementsByName("twitter");
twitter[0].innerHTML = "Twitter";
twitter[0].setAttribute("href", "https://www.twitter.com/");

var slack = document.getElementsByName("slack");
slack[0].innerHTML = "Slack";
slack[0].setAttribute("href", "https://www.slack.com/");

var javadocs = document.getElementsByName("javadocs");
javadocs[0].innerHTML = "JavaDocs";
javadocs[0].setAttribute("href", "https://www.javadocs.com/");


//2
let planet = document.getElementById("planet");
planet.options[3].disabled = true;


//3
let select = document.getElementById("planet");

select.onchange = function optionchange(){
let p = document.getElementsByTagName('p');
	
	 if (select.value == "Earth") {
		for (i = 0; i < p.length; i++) {
			if (p[i].innerHTML == "Beep boop") {
				p[i].style.visibility = "hidden";
			}	
		}	
	}
    else if (select.value != "Earth") {
		for (i = 0; i < p.length; i++) {
			if (p[i].innerHTML == "Beep boop") {
			 p[i].removeAttribute("hidden");
			 p[i].style.visibility = "";
			}
		}
	}
}

//4




//5
var el = document.getElementsByTagName("details");

el[0].onmouseover = function openDetails() {
    el[0].setAttribute('open', 'true'); }

    function hideDetails(){
    
        this.open = false;}
        
    el[0].addEventListener("mouseleave", hideDetails);
 


//#6

var el1 = document.getElementsByTagName("span");
var x="";
for (e of el1){
    
    x+=e.innerHTML;
    
}
console.log(x);


//#7
var el2 = document.getElementById("earth_time_check");

function onClick1(){
    var el3 = document.getElementById("earth_time")
    el3.innerHTML = Date();
    
}
el2.addEventListener("click", onClick1);



//#8
var el4 = document.getElementsByTagName("span");

function colorChange(){
        var letters = '0123456789ABCDEF';
        var color = '#';
        for (var i = 0; i < 6; i++) {
          document.body.style.backgroundColor ="#"+Math.floor(Math.random() * 16000000);
        }
        return color;
      }
    
    
      
el4[0].addEventListener("click", function(){
    setTimeout(colorChange, 3000)});
