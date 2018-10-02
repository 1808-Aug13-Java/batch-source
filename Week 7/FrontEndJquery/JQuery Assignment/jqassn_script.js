// $("#button1").click(function(){

//     // $(this).text("works");
// // console.log($("#img1").attr("hidden"));
//     // if ($("img").attr("hidden")){
//     //     $("img").attr("hidden", null);
//     // }
//     // else{
//     //     $("#img").attr("hidden","hidden");
//     // }
//     // tryCounter++;

// })

// $("#card_container").on("click",".card",function(){
    
   

//     // $("div[flipped|='flipped']").

 

//     if ($("img").attr("hidden")) {

//         $("img").attr("hidden", null);
//     } else {
//         $("img").attr("hidden", "hidden");
//     }
//     tryCounter++;
// })



// $(".card").click( function(){
//     if ($("img.first").attr("hidden")) {
//         $("img.first").attr("hidden", null);
//     } else {
//         $("img.first").attr("hidden", "hidden");
//     }
// })

$(".card").click(function () {

// $(this).children("img:first-child").attr("hidden", null);
let currentCardVal = $(this).children("img:first-child").attr("src");
let currentId = $(this).attr("id");

// if(firstCard===currentCard){
//     firstCard="";
//     console.log("You found a match!");
// }
if(openCards===0){
    firstCardVal = $(this).children("img:first-child").attr("src");
    openCards = 1;
    firstCardId = $(this).attr("id");
}

else{
    if(currentId===firstCardId){
        firstCardVal = "";
        firstCardId = "";
        openCards = 0;
    }
    else{
        if(currentCardVal==firstCardVal ){
            console.log("You found a match!");
            $(`#${firstCardId}`).detach();
            $(`#${currentId}`).detach();
            matches++;
            firstCardVal = "";
            firstCardId = "";
            openCards = 0;
        }
        if(matches===3){
            completed();
        }
    }  
}

if ($(this).children("img:first-child").attr("hidden")){
    $(this).children("img:first-child").attr("hidden", null);
}
else{
    $(this).children("img:first-child").attr("hidden", "hidden");
}
tryCounter++;
})


function completed(){
    var message= $("<p></p>").text(`Congrats! You have beat the game in ${tryCounter} tries. `);
    $(".container").append(message);
}


var firstCardVal;
var firstCardId;

var tryCounter= 0;
var openCards=0;
var matches=0;