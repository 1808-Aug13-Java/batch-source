// Window is our global object

// window.onload = function(){
//     console.log('window has loaded');
// }


// let x = 12;
// console.log(x);

function myfunc(){
    console.log(x);
}

// myfunc(); // when we call myfunc we have access to the global scope

function myfunc2(){
    let x = 5;
    console.log(x);
}

// myfunc2(); 

// console.log(x); // prints 12 b/c global

/////////////////////////////////////////////////////////////
// const vs let vs var
/////////////////////////////////////////////////////////////

// var person;
// person = {name:'joe', age:28};
// var person2 = {name: 'sally', age:25};
// person2 = person;

// Can work around const objects
const person = {name:'joe', age:28};
const person2 = {name: 'sally', age:25};
person2.name = 'joe';
person2.age = 28;


// these consts cannot be reassigned
const z = 5;
const y = 10;

// var and block scope
// var pass = false;
// var score = 70;
// if (score > 60){
//     var pass = true;
// }
// console.log(pass);


// let and block scope
// let pass = false;
// let score = 70;
// if (score > 60) {
//     let pass = true;
//     console.log('within block: ' + pass);
// }
// console.log('outside of bloack: ' + pass);


///////////////// var and hoisting
function hoistTest1(){
    let x;
    x = 6;
    console.log(x);
}

hoistTest1();

function hoistTest2(){
    console.log(x);
    let x;
}

// hoistTest2();

function hoistTest3(){
    var x;
    x = 6;
    console.log(x);
}

hoistTest3();

function hoistTest4(){
    console.log(x);
    var x;
}

hoistTest4();

function hoistTest5(){
    console.log(x);
    var x = 5;
}

hoistTest5();

function hoistTest6(){
    var a = 1;
    alert(a+b+c);
    var b = 2;
    var c = 3;
}

// hoistTest6();

let person3 = {name:"Paul", age:40};
for (i in person3){
    console.log(i + " is: " + person3[i]);
}

let arr = ['green', true, 42];
for (i in arr){
    console.log("#" + i + " is: " + arr[i]);
}

// of works on arrays but not objects; can't use w/t person3
for (i of arr){
    console.log(i);
}


function printAll(a, b, c, d){
    console.log(a);
    console.log(b);
    console.log(c);
    console.log(d);
}

function printAllAgain(){
    for (i of arguments){
        console.log(i);
    }
}