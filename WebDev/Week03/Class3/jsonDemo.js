
let studentJSON = 
`
[
	{
		"id": 1,
		"first_name": "Raffarty",
		"last_name": "Cubberley",
		"email": "rcubberley0@google.nl",
		"gender": "Male",
		"card_number": "3530307765843786"
	},
	{
		"id": 2,
		"first_name": "Normy",
		"last_name": "Cumo",
		"email": "ncumo1@macromedia.com",
		"gender": "Male",
		"card_number": "3580494825496091"
	},
	{
		"id": 3,
		"first_name": "Caryn",
		"last_name": "Redit",
		"email": "credit2@dmoz.org",
		"gender": "Female",
		"card_number": "3567216159190140"
	}
]
`


window.onload = function() {
	console.log(studentJSON);
	let studentArr = JSON.parse(studentJSON);
	console.log(studentArr);
	
	for (student of studentArr) {
		addRow(student.id, student.first_name + " " + student.last_name, student.email, student.gender, student.card_number);
	}
	
}

// Takes an arbitrary number of arguments and adds them as 'td' elements to the
// 'students' table
function addRow() {
	//arguments
	let row = document.createElement("tr");
	let cell;
//	let cells = [];
	
	for (obj of arguments) {
		// Create a new element
		cell = document.createElement("td");
		
		// Set the contents of the element
		cell.innerHTML = obj;
//		cells.push(cell);
		
		row.appendChild(cell);
	}
	
	document.getElementById("students").appendChild(row);
}


// Add an event listener to the button to add students 
document.getElementById("addStudentButton").addEventListener("click", addNew);

function addNew() {
	// As The table has the header, we need to subtract 1. 
	let rows = document.getElementById("students").childNodes.length - 1;
	let name = document.getElementById("name").value;
	let email = document.getElementById("email").value;
	let gender = document.getElementById("gender").value;
	let cardNumber = document.getElementById("cardNumber").value;
	
	console.log(`Student Name: ${name}  Email: ${email}`);
	
	// If the inputs are not empty, insert them
	if ((name && email && gender && cardNumber) != "") {
		addRow(rows, name, email, gender, cardNumber);
		document.getElementById("addStudentText").innerText = "";
	}
	else {
		document.getElementById("addStudentText").innerText = "Invalid Input";
	}
}



