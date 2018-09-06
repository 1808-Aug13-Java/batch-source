function doSomething(){
    for(let i=0; i<5; i++){
        console.log(i);
    }
}

console.log("finally: " +i);

let counter = 5;
counter = "a";

let x;
x=9;
x=false;
x="blue";

let myNum: number;
myNum=9;
myNum=false;
myNum="blue"; // we get errors here...

let boo: boolean;
let str: string;
let obj: object;

function myVoidFunction(): void{
    console.log("This funciton prints but doesn't return anything");
    
}

//other than void, there's a type never

let neverTrue = function(value: any){
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
v = ["four", "five", 6];
let w: any[];
w = ["four", "five", 6];

let drawPoint = (x, y) => {
    //implementation
}

let drawPoint2 = (point) => {
    //implementation
}

drawPoint2({
    x: 3,
    y: 5
})

drawPoint2({
    name: "bob",
    age: 45,
    email: "bob@gmail.com"
})

let drawPoint3 = (point: {x: number, y: number}) => {
    //access point.x and point.y
}

interface Point{
    x: number,
    y: number
}

let drawPoint4 = (point: Point) => {
    //access point.x and point.y
}

//typescript allows us to keep things more structured and organized

class AlsoPoint{
    x: number;
    y: number;

    //? is an optional parameter. can't make x optional without making y optional, but can make y optional without making x optional
    constructor(x?: number, y?: number){
        this.x = x;
        this.y = y;
    }

    drawPoint = ()=>{
        console.log("x is " + this.x + ", y is " + this.y);
    }
}
let myPoint: AlsoPoint = new AlsoPoint();
myPoint.drawPoint();

drawPoint4(myPoint);
