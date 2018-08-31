// let counter = 0;
// function add(){
//     counter += 1;
//     console.log(counter);
// }

// add();
// add();
// add();

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

// callback functions: passing functions as parameter
function apply2(fun){
    return fun(2);
}

// function add5(param){
//     return param + 5;
// }

// console.log(apply2(add5));

// let add5Again = function (param) {
//     return param + 5;
// }

let add5 = (param) => param + 5;

console.log(apply2(add5));

// object creation methods
let person = {"name":name, "age":age};

// maker function
function makePerson(name, age){
    let person = {"name":name, "age":age};
    return person;
}
let person2 = makePerson("Sally", 30);