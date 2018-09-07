function doSomething(){
    for(let i=0; i<5; i++){
        console.log(i);
    }
}

console.log("finally: "+i);


let counter = 5;
counter = "a";

let x;
x=9;
x=false;
x="blue";

let myNum: number;
myNum=false;
myNum="blue";

let boo: boolean;
let str: string;
let obj: object;


function myVoidFunction(): number{ //myvoidFunction(): void{}
console.log("This function prints but doesn't return anything");
return 9;// one way to declare a type or you can be explicit by making it void
}

let neverTrue = function(value:any){
    if(typeof value === "string" && typeof value === "number"){
        return value;
    }
}

let rickRoll = function(){
    while(true){
        console.log("never gonna give you up");
        console.log("never gonna let you up");
    }
}

let u: number[];
u = [4,5,6,"seven"];
let v: string[];
v = ["four","five",6];
let w: any[];
w = ["four","five",6];

let drawPoint = (x,y) => {
    //implementation

    
}

let drawPoint2 = (point) => {
    //implementation
    //access point.x and point.y
}

drawPoint2({
    x:3,
    y:5
})

drawPoint2({
    name:"bob",
    age:45,
    email:"bob@gmail"
})

let drawPoint3 = (point:{x:number, y:number})=>{
    //implementation
    //access point.x point.y
}
drawPoint3({
    x:3,
    y:5
})

drawPoint3({ //restricted by the function decaration
    name:"bob",
    age:45,
    email:"bob@gmail"
})

interface Point{
    x:number,
    y:number
}

let drawPoint4 = (point: Point)=>{
    //implementation
    //access point.x and point.y
}
drawPoint4({
    x:3,
    y:5
})

drawPoint4({ //restricted by the function decaration
    name:"bob",
    age:45,
    email:"bob@gmail"
})

class AlsoPoint{
    x: number;
    y: number;

    constructor(x:number, y:number){
        this.x=x;
        this.y=y;
    }
    drawPoint= ()=>{
        console.log("x is" + this.x+" , y is " +this.y);
    }
}
let myPoint: AlsoPoint = new AlsoPoint();
myPoint.drawPoint();

drawPoint4(myPoint);