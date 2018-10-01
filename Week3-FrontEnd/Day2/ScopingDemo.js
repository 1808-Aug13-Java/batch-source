//window is our global object

/*
window.onload = function(){
    console.log("window has loaded")
}
*/

/*
let x = 12;
console.log(x);

function myfunc(){
    console.log(x);
}

myfunc(); // when we call myfunc we have access to the global scope

function myfunc2(){
    let x = 5;
    console.log(x);
}
// each function creates its own scope with functions/variables

myfunc2();

console.log(x); // no longer access to x=5, global scope is 12
*/

///////////////////////////////////////////////////
// const vs. let vs. var
///////////////////////////////////////////////////

// var person;
// person = {name:'joe', age:28};
// var person2 = {name:'sally', age:25};
// person2 = person;

const person = {name:'joe', age:28};
const person2 = {name:'sally', age:25};
person2.name = 'joe';
person2.age = 28;
//person2 = person;

const z = 5;
const y = 10;
// these cannot be changed because they cannot be reassigned

// // var and block scope
// var pass = false;
// var score = 70;
// if(score>60){
//     var pass = true;
// }

// console.log(pass);


// // let and block scope
// let pass = false;
// let score = 70;
// if(score>60){
//     let pass = true;
//     console.log('within block: '+ pass);
// }

// console.log('outside of block: ' + pass);


//////// let and hoisting

function hoistTest1(){
    let x;
    x = 6;
    console.log(x);
}

hoistTest1()

function hoistTest2(){
    console.log(x);
    let x;
}

// hoistTest2()

//////// var and hoisting

function hoistTest3(){
    var x;
    x = 6;
    console.log(x);
}

hoistTest3()

function hoistTest4(){
    console.log(x);
    var x;
}

// hoistTest4()


function hoistTest5(){
    console.log(x);
    var x = 5; // only applies to assignment not declaration
}

// hoistTest5();

function hoistTest6(){
    var a = 1;
    alert(a+b+c);
    var b = 2;
    var c = 3;
}

// hoistTest6();

let person3 = {name:"Paul", age:40};
for( i in person3 ){
    console.log(i + " is: "+ person[i]);
}

// for( i of person3 ){
//     console.log(i + " is: "+ person[i]);
// }

let arr = ['green', true, 42];
for (i in arr){
    console.log("#"+i+" is "+arr[i]);
}

for (i of arr){
    console.log(i)
}


function printAll(a, b, c, d){
    console.log(a);
    console.log(b);
    console.log(c);
    console.log(d);
}

function printAllAgain(){
    for(i of arguments){
        console.log(i);
    }
}