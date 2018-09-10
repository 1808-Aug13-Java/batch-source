var pcb = "http://www.astropical.space/astrodb/api-exo.php?which=name&limit=Proxima%20centauri%20b&format=json";

//http://www.astropical.space/astrodb/api-exo.php?which=name&limit=Proxima%20centauri%20b&format=json

window.onload = function(){
    //create ye olde working links
    //getElementsByName returns an array of objects that meet the criteria
    var arr = document.getElementsByName("google");
    arr[0].href = 'https://www.google.com';
    arr = document.getElementsByName("twitter");
    arr[0].href = 'https://www.twitter.com';
    arr = document.getElementsByName("slack");
    arr[0].href = 'https://www.slack.com';
    arr = document.getElementsByName("javadocs");
    arr[0].href = 'https://www.javadocs.com';

    //disables the last element which happens to be pluto
    document.getElementById("planet").lastElementChild.disabled = true;
    //alienText
    document.getElementById("planet").addEventListener("change",alienText);

    var deets = $("details:contains(<summary>A happy cloud.</summary>)");
    arr = document.getElementsByTagName("details");
    arr[0].addEventListener("mouseover",openDetails);
    arr[0].addEventListener("mouseleave",closeDetails);
    consoleSpan();
    earthtime = document.getElementById("earth_time_check").addEventListener("click",displayTime);
    var interheading = document.getElementsByTagName("h1");
    interheading[0].addEventListener("click",colorChange);

    document.getElementById("n1").addEventListener("change",math);
    document.getElementById("n2").addEventListener("change",math);
    document.getElementById("operation").addEventListener("change",math);

    document.getElementById("form-sub").addEventListener("click",addRow);
    document.getElementById("mars_time_check").addEventListener("click",marsTime);
    document.getElementById("acb_time_check").addEventListener("click",sendAjaxGet);
}

function alienText(){
    selection = document.getElementById("planet").value;
    aTArr = $("p:contains(Beep boop)");
    console.log(aTArr);
    if(selection=='Earth'){
        aTArr[0].hidden = true;
    }else{
        aTArr[0].hidden = false;
    }
}

function openDetails(){
    this.open=true;
}

function closeDetails(){
    this.open=false;
}

function consoleSpan(){
    span = document.getElementsByTagName("span");
    str = "";
    for(s of span){str += s.innerHTML;}
    console.log(str);
}

function displayTime(){
    document.getElementById("earth_time").innerHTML = Date();
}

function colorChange(){
    setTimeout(function change(){document.body.style.backgroundColor = '#' + Math.floor(Math.random()*16777214+1).toString(16)},3000);
}

function math(){
    operation = document.getElementById("operation").value;

    if(operation == 'Add'){
        document.getElementById("result").innerHTML = Number(n1.value)+Number(n2.value);
    }else if(operation == 'Subtract'){
        document.getElementById("result").innerHTML = Number(n1.value)-Number(n2.value);
    }else if(operation == 'Divide'){
        document.getElementById("result").innerHTML = Number(n1.value)/Number(n2.value);
    }else if(operation == 'Multiply'){
        document.getElementById("result").innerHTML = Number(n1.value)*Number(n2.value);
    }
}

function addRow(){
    var fName = document.getElementById("firstname");
    var lName = document.getElementById("lastname");
    var email = document.getElementById("email");
    var tele = document.getElementById("phone");
    var bday = document.getElementById("bday");
    var color = document.getElementById("color");
    var gend = document.getElementsByName("gender");
    //for loop to check for checked

    var activ = document.getElementsByClassName("activity");

    if(fName.value.length > 2 && lName.value.length > 2&&email.value!=null&&tele.value!=null&&bday.value!=null){
        var table = document.getElementsByTagName("table");
        var row = table[0].insertRow();
        var cell0 = row.insertCell(0);
        var cell1 = row.insertCell(1);
        var cell2 = row.insertCell(2);
        var cell3 = row.insertCell(3);
        var cell4 = row.insertCell(4);
        var cell5 = row.insertCell(5);
        var cell6 = row.insertCell(6);

        cell0.innerHTML = fName.value;
        cell0.innerHTML += " "+lName.value;
        cell1.innerHTML = email.value;
        cell2.innerHTML = tele.value;
        cell3.innerHTML = bday.value;
        cell4.innerHTML = color.value;
        for(e of gend){
            if(e.checked==true){
                cell5.innerHTML = e.value;
                break;
            }
        }
        cell6.innerHTML = "<ul>";
        for(e of activ){
            if(e.checked==true){
                cell6.innerHTML += "<li>"+e.value+"</li>";
            }
        }
        cell6.innerHTML += "</ul>";
        
    }
}

function marsTime(){
    var ms = new Date();
    var days = ms/1000/60/60/24;
    var mYears = days/687;
    days = days%687;
    document.getElementById("mars_time").innerHTML = "Mars Years: "+parseInt(mYears)+" Earth Days: "+parseInt(days);
}

function sendAjaxGet(){
	let xhr = (new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest"));
	xhr.onreadystatechange = function(){
		if(this.readyState===4 && this.status==200){
			pcbTime(this);
			//console.log(xhr.responseText);
		}
	}
	xhr.open("GET","http://www.astropical.space/astrodb/api-exo.php?which=name&limit=Proxima%20centauri%20b&format=json");
	xhr.send();
}

function pcbTime(xhr){
    let planet = JSON.parse(xhr.responseText);
    console.log(planet);
    var ms = new Date();
    var days = ms/1000/60/60/24;
    var pcbYears = days/`${planet.exoplanets[0].per}`;
    days = days%`${planet.exoplanets[0].per}`;
    document.getElementById("acb_time").innerHTML = "PCB Years: "+parseInt(pcbYears)+" Earth Days: "+parseInt(days);
}

function walkTheDom(node, func){
    func(node);
    for(s of node.childNodes){
        walkTheDom(s,func)
    }
}

walkTheDom(document.body, function (node) {
    if (node.nodeType === 3) {
        /*  1 is element node like <p> or <div>
            3 is text node
            4 is a CDATASection
            7 is a processing instruction node
            8 is a comment
            9 is a document node
            10 is a document type node like at beginning of html
            11 is a document fragment node
        */
        var text = node.data.trim();
        if (text.length > 0) { //check for content
            console.log(text);
        }
    }
});