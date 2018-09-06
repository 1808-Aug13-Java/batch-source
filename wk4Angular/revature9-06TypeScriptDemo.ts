function doSomething() {
    for(let i = 0; i < 5; i++){
        console.log(i);
    }
}

console.log("finally: "+i); // here it wont be ok with this bcuz i not defined
    //diff from js

let counter = 5;
counter = "a";   //in js, its good. Here NO GOOD!

let x;          //on the other hand, this is ok for tsc.
x=9;            //bcuz let x. not defined, so u can put watever
x=false;
x="blue";

let myNum: number;  //this makes the var to be type number
//now cant assign randomly!

let boo: boolean;
let str: string;
let obj: object;

function myVoidFUnction(): void{
    consolelog("prints but doenst rtn anything");
}

let neverTrue = function(value:any){
    if(typeof value === "string" && typeof value === "number"){
        return value;
    }
}

let rickRoll = function(){
    while(true){
        console.log("never gonna give you up");
        console.log("never gonna let you down");
    }
}

let u: number[];  //number array
let v: string[];
let w: any[];
u = [4,5,6];   //but not [4,5,6,"seven"]
//v = ["four", 6] is ok though because it's string
//w = ["a", "bees", 56] is def ok because it's any type

let drawPoint = (x, y) => {

}

let drawPoint2 = (point) => {
    
}

drawPoint2({
    x: 3;
    y: 5
})

drawPoint2({
    name: "bob",
    age:45,
    email:"bob@gmail.com"
})

let drawPoint3 = (point: {x:number, y:number}) => {
    name: "bob",
    age:45,
    email:"bob@gmail.com"
}

interface Point {
    x: number,
    y: number
}

let drawPoint4 = (point: Point){

}

class AlsoPoint{
    x:number;
    y:number;

    //can do _x:number, _y?:number
    //but not _x?:number, _y:number.   ? means optional param
    constructor(_x?:number, _y?:number){
        this.x = _x;
        this.y = _y;
    }

    drawPoint = ()=>{
        console.log("x is "+this.x+", y is "+this.y);
    }
}