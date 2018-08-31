
// function add (){
//     let counter = 0;
//     counter += 1;
//     console.log(counter);
//     
//     }
// }


function add(){
    let counter =0;
    return function(){
        return ++counter;
        console.log(counter);
    }
}


add();
add();
add();


//callback functions

function apply2(fun){
    return fun(2);
}

// function add5(param){
//     return param+5;
// }



// setting a variable to a function
// let add5Again= function(){
//     return param+5;
// }

let add5=(param)=>param+5;

console.log(apply2(add5));


//object creation

let p={name:"John", age:39};
console.log(p);

//maker function
function makePerson(name,age){
    let person ={"name":name, "age":age}
    return person;
}

let p2=makePerson("sally",30);
console.log(p2);

function person(name,age){
    this.name=name;
    this.age=age;
    
}

let p4=new person("Shannon",23);
console.log(p4);


