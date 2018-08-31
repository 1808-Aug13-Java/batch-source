//window is our global object

window.onload=function(){
    console.log("window has loaded")
}

let x=12;
// console.log(x)
function myFunc (){
    console.log(x);
}

myFunc();//when we call function we have access to the global scope

function myFunc2() {
    let x=5;
    console.log(x);
}

myFunc2();//each function create its won scope with its own variable
console.log(x) //no longer have global scope

// var person
// person={name:"joe", age:28};
// var person2 = { name: "sally", age: 25 };

// person2=person1;


const person = { name: "joe", age: 28 };
const person2 = { name: "sally", age: 25 };

person2.name = "joe";
person2.age=28; //getting arroud assignment
console.log(person2)

const v=5;
const y=10;
// v=y;// won't actually do anything
//const cannot be reassigned


//var and block scope

// var pass=false;
// var score =70;
// if(score>60){
//     var pass=true;
// }
// console.log(pass)

// let pass = false;
// let score = 70;
// if (score > 60) {
//     let pass = true;
//     console.log('within block scope: '+pass)
// }
// console.log('outside block scope: '+ pass)

/////////var and hoisting
// function hoistTest(){
//     let x ;
//     x=5;
//     console.log(x);
    
// }

// hoistTest();

// function hoistTest2() {
//     console.log(x);
//     let x;
// }

// hoistTest2();


function hoistTest3() {
    var  x=7;
    console.log(x);
}

hoistTest3();

function hoistTest4() {
    console.log(x);
    var x ;
    
}

hoistTest4();

// function hoistTest5() {
//     console.log(x);
//     var x=8;

// }

// hoistTest5();

// function hoistTest6() {
//     var a=1;
//     alert(a+b+c);
// var b=2;
// var c =3;

// }

// hoistTest6();


//enhanced for loops

let person3={name:"Paul", age:40};
for (i in person3){
    console.log(i+"is: "+ person[i]);
};

let arr = ["green", true, 42];
for (i in arr){
    console.log(i + "is : "+ arr[i]);

}

function printAll(a,b,c,d){
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