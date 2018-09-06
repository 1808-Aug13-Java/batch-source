function doSomething(){
    for (let i =0; i<5;i++){
        console.log(1);
    }
}

// console.log( "finally: "+i );
let counter =5; 
// counter="a";
 let x:any
 
 x=9;
 x=false
 x="blue";

 let mynum:number
 mynum=9;
//  mynum="blue";


 let boo:boolean
 let str:string
 let obj:object

 function myvoidFunction():void {
     console.log("this function prints out but it does not return anything");
 }

 let never=function(value:any){
     if(typeof value === "string" && typeof value ==="number"){
        return value;
        //since value will never return, set the value to never
     }
 }

 let rickRoll =function(){
    while(true){
        console.log("never gonna give you up");
        console.log("alkdjfl")
    }
 }


//structures


let u:number[];

let v:string[];
let w:any[];

let drawpoint=(x,y)=>{
    //implementation
}

let drawpoint2=(point)=>{
    //implementation
    //access point.x and point.y
}

drawpoint2({
    name:"bob",
    age:45,
    email:"bob@gmail.com"
})

let drawpoint3=(point:{x:number,y:number} )=>{

}

// drawpoint3({
        // name:"bob",
        // age:45,
        // email:"bob@gmail.com"
// })


interface Point {
    x:number,
    y:number
}

let drawPoint4=(point:Point)=>{

}


drawPoint4({
x:3,
y:4

})


class AlsoPoint{
    x:number;
    y:number;

    constructor(_x?:number, _y?:number){
        this.x=_x;
        this.y=_y;
    }
    drawPoint=()=>{
        console.log("x is "+this.x+"y is "+this.y);
    }
}


let myPoint:AlsoPoint = new AlsoPoint;

