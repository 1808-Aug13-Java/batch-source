let allCards = $('.card');
let cardArr = [];
const flippedIcons = [
    '<i class="far fa-grin-beam">Grin</i>',
    '<i class="far fa-grin-hearts">Hearts</i>',
    '<i class="far fa-grin-alt">Smile</i>',
    '<i class="far fa-sad-cry">Cry</i>',
    '<i class="far fa-dizzy">Dizzy</i>',
    '<i class="far fa-angry">Angry</i>'
];

function indexRNG(firstIndex, lastIndex) {
    return Math.floor((Math.random()) * ((lastIndex - firstIndex + 1) + firstIndex));
}

for (let icons of flippedIcons) {
    let populateItemAtRandom = indexRNG(0, allCards.length - 1);
    let populateOtherItemAtRandom = indexRNG(0, allCards.length - 1);

    while (cardArr.includes(populateItemAtRandom)) {
        populateItemAtRandom = indexRNG(0, allCards.length - 1);
    }

    while (cardArr.includes((populateOtherItemAtRandom)) || (populateOtherItemAtRandom === populateItemAtRandom)) {
        populateOtherItemAtRandom = indexRNG(0, allCards.length - 1);
    }

    allCards.eq(populateItemAtRandom).html(icons);
    allCards.eq(populateOtherItemAtRandom).html(icons);
    cardArr[cardArr.length] = populateItemAtRandom;
    cardArr[cardArr.length] = populateOtherItemAtRandom;
}

let game = function () {
    let firstCard = $('div.back.active')

    $(this).removeClass('front');
    $(this).addClass('back active');

    let secondCard = $(this);
    let positionFirst = firstCard.length;
    if (positionFirst >= 1) {

        if (secondCard.html() === firstCard.html()) {
            // disable if match is found/cancel event listener
            secondCard.off('click').removeClass('active');
            firstCard.off('click').removeClass('active');


        } else {
            // if no match is found, restart
            firstCard.removeClass('back active').addClass('front');
            secondCard.removeClass('back active').addClass('front');
        }
    }
}

$('.card').on('click', game);
$(('.front')).click(function () {
    var value = parseInt($("#count").text(), 10) + 1;
    $("#count").text(value);
})