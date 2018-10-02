$('img').click(itemClick);
var matrix = [['mountain','island','plain'],['island','mountain','island'],['island','island','plain'],['forest','island','forest']];
var colors = ["plain","plain","island","island","swamp","swamp","mountain","mountain","forest","forest","waste","waste"];
var firstCard = "";
var firstCoord = [];
var secondCard = "";
var secondCoord = [];
var score = 0;
var scores = [];

function startTheGame() {
    $('#startBtn').hide();
    $('#board').show();
    reshuffle();
}

function itemClick() {
    let dirtyCoords = [$(this).parent().attr('class'),$(this).attr('class')];
    let x = dirtyCoords[0].replace('x','');
    let y = dirtyCoords[1].replace('y','');
    console.log(x + y);
    if(!firstCard){
        $(this).attr('src',"img/" + matrix[x][y] + ".jpg");
        $(this).off('click');
        firstCard = matrix[x][y];
        firstCoord = [x,y];
    }
    else{
        $(this).attr('src',"img/" + matrix[x][y] + ".jpg");
        $(this).off('click');
        secondCard = matrix[x][y];
        secondCoord = [x,y];
        checkCards();
    }
}

function reshuffle(){
    matrix = [['','',''],['','',''],['','',''],['','','']];
    let x = Math.floor(Math.random()*4);
    let y = Math.floor(Math.random()*3);
    for (let color of colors){
       while (matrix[x][y]){
            x = Math.floor(Math.random()*4);
            y = Math.floor(Math.random()*3);
        }
        matrix[x][y] = color;
        $(`.x${x} y${y}`).children().classList = color;
    }
}

function checkCards() {
    if (firstCard == secondCard) {
        firstCard = "";
        secondCard = "";
        alert("MATCH!!!");
        //check if that's all folks, final screen with score
    }
    else {
        firstCard = "";
        secondCard = "";
        setTimeout(function(){
            let x = firstCoord[0];
            let y = firstCoord[1];
            $(`.x${x} y${y}`).children()
                    .setAttribute('src',"img/back.jpg");
            $(`.x${x}`).children().on('click',itemClick);

            x = secondCoord[0];
            y = secondCoord[1];
            $(`.x${x} y${y}`).children()
                    .setAttribute('src',"img/back.jpg");
            $(`.x${x}`).children().on('click',itemClick);
        }, 2500);
        //set timeout 5000, flip cards back over, reset enabled
    }
}