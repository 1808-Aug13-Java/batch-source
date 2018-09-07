function doSomething(){
    for(let i=0; i<5; i++){
        console.log(i);
    }
}

console.log("finally: "+i);
var i;

let counter = 5;
counter = "a";

let x;
x=9;
x=false;
x="blue";

let myNum: number;
myNum=9;
myNum=false;
myNum="blue";

let boo: boolean;
let str: string;
let obj: object;


function myVoidFunction(): void{
    console.log("This function prints but doesn't return anything");
}

let neverTrue = function(value:any){
    if(typeof value === "string" && typeof value === "number"){
        return value;
    }
}

let rickRoll = function(){
    while(true){
        console.log("never gonna give you up");
        console.log("never gonna let you down")
    }
}

let u: number[];
u = [4,5,6,"seven"];
let v: string[];
v = ["four", "five",6];
let w: any[];
w = ["four", "five",6];


let drawPoint = (x,y) => {
    //implementation
}

let drawPoint2 = (point) => {
    //implementation
    //access point.x and point.y
}

drawPoint2({
    x: 3,
    y: 5
})

drawPoint2({
    name: "bob",
    age:45,
    email:"bob@gmail.com"
})

let drawPoint3 = (point: {x:number,y:number})=>{
    //implementation
    //access point.x and point.y   
}

drawPoint3({
    x: 3,
    y: 5
})

drawPoint3({
    name: "bob",
    age:45,
    email:"bob@gmail.com"
})

interface Point {
    x: number,
    y: number
}

let drawPoint4 = (point: Point)=>{
    //implementation
    //access point.x and point.y  
}

drawPoint4({
    x: 3,
    y: 5
})

drawPoint4({
    y: 5,
    x: 6
})

drawPoint4({
    name: "bob",
    age:45,
    email:"bob@gmail.com"
})

class AlsoPoint{
    x: number;
    y: number;

    constructor(_x?:number, _y?:number){
        this.x = _x;
        this.y = _y;
    }

    drawPoint = ()=>{
        console.log("x is "+ this.x+", y is "+this.y);
    }
}

let myPoint: AlsoPoint = new AlsoPoint();
myPoint.drawPoint();

drawPoint4(myPoint);
