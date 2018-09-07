function doSomething() {
  for(let i = 0; i <5; i++) {
    console.log(i);
  }
};

console.log("finally: " + i);

let counter = 5;
// would give an error
// counter = "a";

let x;
x=9;
x=false;
x="blue";

let myNum: number;
myNum =9;

let boo: boolean;
boo = false;
let obj: object;
obj = new String();
let string: string;
string = "";


function myVoidFunction(): number {
  console.log("this function prints but doesn't return anything");
  return 1;
}

let neverTrue = function(value:any) {
  if(typeof value === "string" && typeof value === "number") {
    return value;
  }
}

let u: number[];
let v: string[];
let w: any[];

const drawPoint = (x,y) => {
  // implementation

}

let drawPoint2 = (point) => {
  // implementation
  // access point.x & point.y
}

drawPoint2({
  x: 1,
  y: 5
});

const drawPoint3 = (point: {x:number, y:number}) => {
  // implementation
  // access point.x & point.y
}

drawPoint3({
  x: 1,
  y: 5
});

interface Point {
  x: number,
  y: number
}

const drawPoint4 = (point: Point) => {

}

class AlsoPoint implements Point {
  x: number;
  y: number;

  construtor(x?:number, y?:number) {
    this.x = x;
    this.y = y;
  }

  drawPoint = () => {
    console.log("x: " + this.x);
    console.log("y: " + this.y);
  }
}

let myPoint: Point = new AlsoPoint();

drawPoint4(myPoint);
