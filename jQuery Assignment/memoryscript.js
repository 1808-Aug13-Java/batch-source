let array = ['acoustic','afrobeat','alt-rock','alternative','ambient','anime','black-metal','bluegrass','acoustic','afrobeat','alt-rock','alternative','ambient','anime','black-metal','bluegrass'];
let shffldArr = [];
var tries = 0;
$(document).ready(function(){ 
        $('p').addClass("pHidden");
        shffldArr=shuffle(array);
        populate();
    });

function shuffle(array) {
    //Fisher-Yates (aka Knuth) Shuffle
    var currentIndex = array.length, temporaryValue, randomIndex;
    while (0 !== currentIndex) {
        randomIndex = Math.floor(Math.random() * currentIndex);
        currentIndex -= 1;
        temporaryValue = array[currentIndex];
        array[currentIndex] = array[randomIndex];
        array[randomIndex] = temporaryValue;
    }
    return array;
}
var cheat = '';
$('*').keydown(function(e){
    cheat += e.keyCode;
    //Konami Code
    if(cheat=='38383838404040403737393937373939666665651313'){
        alert("CHEATAH");
        tries=0;
        cheat = '';
        //"rainbow"
    }else if(cheat=='8282656573737878666679798787'){
        for(d of $('div > .col')){
            $(d).css('background-color',function(){
                    var letters = '0123456789ABCDEF';
                    var color = '#';
                    for (var i = 0; i < 6; i++) {
                    color += letters[Math.floor(Math.random() * 16)];
                    }
                return color;
            });
        }
        cheat='';
        //bighead
    }else if(cheat=='6666737371717272696965656868'){
        for(div of $('.container > div')){
            $(div).css('width','50%');
            $(div).css('height','200px');
        }
        cheat='';
        //chaos
    }else if(cheat=='67677272656579798383'){
        for(div of $('.container > div')){
            for(d of $(div).children('div')){
                $(d).css('width',Math.floor(Math.random()*100)+'%');
                $(d).css('height',Math.floor(Math.random()*100)+'%');
                $(d).css('background-color',function(){
                    var letters = '0123456789ABCDEF';
                    var color = '#';
                    for (var i = 0; i < 6; i++) {
                    color += letters[Math.floor(Math.random() * 16)];
                    }
                return color;
            });
            }
        }
        cheat='';
        //bammargera
    }else if(cheat=='6666656577777777656582827171696982826565'){
        for(d of $('.container > div')){
            for(da of $(d).children('div')){
                $(da).children('p').html('<img src="http://www.meanstars.com/uploads/celebrity/default/normal/32.jpg" height="100%" width="100%">');
            }
        }
        cheat='';
    }else if(cheat.substr(cheat.length-4,cheat.length)=='3232'){
        cheat='';
    }
})

function populate(){
    let elements = $('p');
    for(e in elements){
        $(elements[e]).text(shffldArr.shift());
    }
}
$('.col').on('click',function(){
    if($('.pShown').length<2 && $(this).children('p').hasClass('pHidden')){
    $(this).children('p').toggleClass("pHidden");
    $(this).children('p').toggleClass("pShown");
    var x = $('.pShown');
    if(x.length==1){

    }else if(x.length>1){
        if($(x[0]).text()==$(x[1]).text()){
        $(x[0]).addClass('matched');
        $(x[0]).removeClass('pShown');
        $(x[1]).addClass('matched');
        $(x[1]).removeClass('pShown');
        }else{
            setTimeout(function(){
            $('.pShown').delay(1000).addClass('pHidden');
            $('.pShown').delay(1000).removeClass('pShown');
        },1000
            );
        }
    }
    tries++;
    $('#score').text('Score: '+tries);
    if($('.matched').length==16){
        alert("done in "+tries+" clicks!");
    }
}
})