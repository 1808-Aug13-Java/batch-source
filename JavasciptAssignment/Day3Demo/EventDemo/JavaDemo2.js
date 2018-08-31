// function add(){
//     let counter = 0;
//     counter += 1;
//     console.log(counter);
// }
// add();
// add();
// add();


function add(){
    let counter = 0;
    return function(){
        return ++counter;
    }
}


function apply2(fun){
    return fun(2);      //return add5(2)
}

// function add5(param){   //return 2+5
//     return param+5;
// } 

// let add5Again = function (param){
//     return param+5;
// }

// console.log(apply2(add5));

let add5 = (param)=> {
    return param+5;
}

console.log(apply2(add5));

//object creation methods
let person = {"name":name, "age":age}

//maker function

function makePerson(name, age){
    let person = {"name": name, "age" : age}
    return person;
}
//incomplet code, need to copy for github