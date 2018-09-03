function alienText(){ 
    if(document.getElementById("planet").value !="Earth"){
        document.getElementsByTagName("p")[5].removeAttribute("hidden");
    } else if(document.getElementById("planet").value =="Earth"){
        document.getElementsByTagName("p")[5].addAttribute("hidden");
    }
}

document.getElementById("planet").addEventListener("click", alienText);

function addRow(firstname, lastname, email, phone, bday, gender, color, activity){
    let row = document.createElement("tr");

    let cell1 = document.createElement("td");
    let cell2 = document.createElement("td");
    let cell3 = document.createElement("td");
    let cell4 = document.createElement("td");
    let cell5 = document.createElement("td");
    let cell6 = document.createElement("td");
    let cell7 = document.createElement("td");
    cell7.appendChild(document.createElement("ul"));

    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    row.appendChild(cell5);
    row.appendChild(cell6);
    row.appendChild(cell7);

    for(a of activity){
        if(a.checked == true){
            let cell = document.createElement("li");
            cell.innerHTML = a.defaultValue;
            cell7.appendChild(cell);
        }
    }

    // if(id===undefined){
    //     id = count++;
    // }
    cell1.innerHTML = firstname +" " + lastname;
    //cell2.innerHTML = lastname;
    cell2.innerHTML = email
    cell3.innerHTML = phone;
    cell4.innerHTML = bday;
    cell5.innerHTML = color;
    cell6.innerHTML = gender;
    //cell7.innerHTML = activity;


    console.log(row);
    console.log(document.getElementById("rows"));
    document.getElementsByTagName("tbody")[0].appendChild(row);
    
}

function addNew(){
    let gender = "";
    let firstname = document.getElementById("firstname").value;
    let lastname = document.getElementById("lastname").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let bday = document.getElementById("bday").value;
    for (a of document.getElementsByName("gender")){
        if(a.checked == true){
            gender = a.defaultValue;
            break;
        }
    }
    //let gender = document.getElementsByName("gender");
    let activity = document.getElementsByClassName("activity");
    let color = document.getElementById("color").value;

    console.log(activity);


    // console.log(`name is ${name} and major is ${major}`);

    // if((name && major) != ""){
    //     addRow(name, major);
    // }else{
    //     document.createElement("p");
    // }

    if((firstname && lastname && email && phone && bday && gender && color && activity)){
        if(errorDisplayed){
            removeError();
            errorDisplayed = false;
        }
        addRow(firstname, lastname, email, phone, bday, gender, color, activity);
    } else {
        if(!errorDisplayed){
            displayError();
            errorDisplayed = true;
        }
    }

}

function displayError(){
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


let errorDisplayed = false;


document.getElementById("form-sub").addEventListener("click", addNew);