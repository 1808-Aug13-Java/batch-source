
//keep track of previously marked td's so you dont click same td twice and count it twice?
let uncovered = 0;
let tries = 0;  //track # of tries
$('td').click(function(){
    if($(this).find('div').css('visibility') === 'hidden'){
        uncovered++;
        $(this).find('div').css('visibility', 'visible');
    }

    if(uncovered % 2 === 0){
        tries++;
        //if 2 match (correct), remove them?? remain flipped over??
        let firstValueIndex = null;
        // console.log($('div').eq(0).css("visibility"));
        for(let i = 0; i < $('div').length; i++) 
            if($('div').eq(i).css('visibility') === 'visible'){
                if(firstValueIndex !== null) {//already set, just compare
                    if($('div').eq(firstValueIndex).html() === $('div').eq(i).html())
                    {  
                        setTimeout(function(){
                            // $('div').eq(firstValueIndex).parent().remove(); does same as below
                            //since i will always be accessed after firstValueIndex, when we remove 
                            //the td at firrstValueIndex, i will now shift down by 1
                            $('td').eq(firstValueIndex).remove();
                            $('td').eq(i - 1).remove();  

                            if($('td').length === 0) {//if removing these two leaves 0 td's, game over
                                $('#showTries').html('Victory! Took you '+ (tries) + ' tries'); 
                                $('a').css('visibility', 'visible');
                            }
                        }, 1000);
                        return;  // don't want to uncover a next card
                    }
                }
                else //firstValue not encountered until now, so set it
                    firstValueIndex = i;
            }
        //incorrect: hide rest and uncover next one
        setTimeout(function(){ $('div').css('visibility', 'hidden'); }, 1000);
    }
});

