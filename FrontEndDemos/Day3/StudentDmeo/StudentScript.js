let studentJSON = 
`[{
	"id": 873,
	"name": "Steve",
	"major": "History"
}, {
	"id": 995,
	"name": "Julius",
	"major": "Latin"
}, {
	"id": 784,
	"name": "Paulina",
	"major": "Physics"
}]`;

window.onload = function(){
    //console.log(studentJSON);
    let studentArr = JSON.parse(studentJSON);
    //console.log(studentArr)
    for(student of studentArr){
        addRow(student.name, student.major, student.id);
    }
}

let count = 1000;

function addRow(name, major, id){
    let row = document.createElement("tr");

    let cell1 = document.createElement("td");
    let cell2 = document.createElement("td");
    let cell3 = document.createElement("td");

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);

    if(id===undefined){
        id = count++;
    }
    cell1.innerHTML = id;
    cell2.innerHTML = name;
    cell3.innerHTML = major;

    document.getElementById("students").appendChild(row);
}

document.getElementById("studentButton").addEventListener("click", addNew);

let errorDisplayed = false;

function addNew(){
    let name = document.getElementById("name").value;
    let major = document.getElementById("major").value;

    console.log(`name is ${name} and major is ${major}`);

    if((name && major)){
        if(errorDisplayed){
            removeError();
            errorDisplayed = false;
        }
        addRow(name, major);
    } else {
        if(!errorDisplayed){
            displayError();
            errorDisplayed = true;
        }
    }
}

function displayError(){
    let error = document.getElementById("error")
    let errorNode = document.createElement("p");
    errorNode.innerHTML = "Invalid Input"
    errorNode.setAttribute("id", "error");
    errorNode.style = "color : red; display : inline; margin-left: 20px;";
    document.getElementsByTagName("div")[0].appendChild(errorNode);
}

function removeError(){
    let error = document.getElementById("error")
    if(error){
        error.remove();
    }
}

let d = new Date();
console.log(d.getFullYear());

let d1 = new Date('December 17, 1995 03:24:00');
console.log(d.getFullYear());