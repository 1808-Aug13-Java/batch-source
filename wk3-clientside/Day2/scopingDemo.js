// // // global object is the window
// // window.onload = function() {
// //   console.log('window has loaded');
// //   let x = 11;
// // }

// let x = 12;

// function myFunc() {
//   // console.log('x: ' + x); // access to global scope
// }

// function myFunc2() {
//   let x = 5;
//   // console.log('myFunc2 x:' + x);
  
// }

// myFunc();

// myFunc2();

// ////// 
// // const vs let vs var
// ///////
// // var person;
// // person = {name : 'joe', age: 28};

// // var person2 = {name: 'sally', age: 23};

// // person2 = person;


// const person = {name : 'joe', age: 28};

// const person2 = {name: 'sally', age: 23};
// // can change value of obj's props
// person2.name = "jan";
// person2.age = 28;
// const z = 5;
// const y = 10;

// let vs var and block scope
// var pass = false;
// var score = 70;
// if(score > 60) {
//   var pass = true;  
// }

// console.log(pass);

// let pass = false;
// var score = 70;
// if(score > 60) {
//   let pass = true; 
//   console.log(pass);
   
// }

// console.log(pass);

///////////////
// var and hoisting

function createCounter() {
  let counter = 0;
  const myCounter = function() {
    counter += 1;
    return counter;
  }

  return myCounter;
}

let increment = createCounter();
console.dir(increment);
let c1 = increment();
let c2 = increment();
let c3 = increment();

console.log('c1, c2, c3: ' + c1 + ' ' + c2 + ' ' + c3);



const addThree = x => n => n+x;
// addThree is a function that return a function that return n+x

let adder = addThree(4);
console.dir(adder);
let result = adder(7);
console.log('result: ' + result);


