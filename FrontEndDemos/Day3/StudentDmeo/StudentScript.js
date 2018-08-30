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


function addNew(){
    let name = document.getElementById("name").value;
    let major = document.getElementById("major").value;

    console.log(`name is ${name} and major is ${major}`);

    if((name && major) != ""){
        addRow(name, major);
    }

}