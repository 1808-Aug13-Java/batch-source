function doSomething(){
    for (let i = 0; i < 5; i++)
        console.log(i);
}
//console.log("finally, " + i);   BLOCK SCOPE

let counter = 5;
//count = "A";     STRONG TYPING

let x; //          nondefined type
x = 9;
x = false;
x = 'blue';

let mynum: number;// DEFINED TYPE
mynum = 9;
// mynum = false;
// mynum = 'blue';

let bool:boolean;
let str:string;
let obj:object;
let bagel:void;

function myFunction():number{  //      DECLARE RETURN TYPE, no return returns void
    console.log("THE VOID SCREAMS BACK");
    return 9;
}
let myVoidFunction = function():void{  //      DECLARE RETURN TYPE, no return returns void
    console.log("THE VOID SCREAMS BACK");
}
let neverTrue = function(value:any){     //declare input type       also returns NEVER
    if (typeof value === "string" && typeof value === "number")
        return value;
}

let rickroll = function(){//                 ALSO NEVER
    while(true){
        console.log("never gonna give you up");
        console.log("never gonna let you down");
    }
}

let u:number[];
let v:string[];
let w:any[];

//DECLARE YOUR OWN TYPES?!?!?!?!
let drawPoint = (x,y) => {};
let drawPoint2 = (point) => {};
let drawPoint3 = (point:{x:number,y:number}) =>{//ONLY ACCEPTS OBJECTS WITH AN X AND Y MEMBER
    //point.x, point.y
};
drawPoint2({x:3, y:5});
drawPoint3({x:3, y:5});

drawPoint2({name:"bob",age:45, email:"bob@gmail.com"});


interface Point{x:number; y:number;};

let drawPoint4 = (point:Point) => {};

class AlsoPoint {x:number; y:number;
    constructor(_x?:number,_y?:number){
        this.x=_x;
        this.y=_y;
    };
    drawPoint = () => {
        console.log("x is " + this.x + "y is " + this.y);
    }
};

let myPoint:AlsoPoint = new AlsoPoint();
drawPoint4(myPoint);


