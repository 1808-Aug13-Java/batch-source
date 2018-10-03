$(document).ready(function () {

    // array of cards I know will each have pairs
    var cards = [1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6];

    randomize();

    // this will kind of shuffle the cards up so it's more like a game
    function randomize() {
        var random = 0;
        var temp = 0;
        for (i = 1; i < cards.length; i++) {
            random = Math.round(Math.random() * i);
            temp = cards[i];
            cards[i] = cards[random];
            cards[random] = temp;
            putValueOnFace();
        }
    }

    // this will give each card a value from 1 - 6 (values from randomized array)
    function putValueOnFace() {
        $('.card').each(function (index) {
            $(this).attr('data-card-value', cards[index]);
        });
        isSelected();
    }

    // event listener for whenever a card is chosen.
    function isSelected() {
        var count = 0;
        // anonymous function to indicate that current card has been chosen
        $('.card').on('click', function () {
            $(this).html('<p>' + $(this).data('cardValue') + '</p>').addClass('chosen');
            $('#count').html(++count);
            // keep track of amount of card touches
            localStorage.setItem("count", count+ "");
            isMatch();
        });
    }

    // check for matches
    function isMatch() {
        if ($('.chosen').length === 2) {
            if ($('.chosen').first().data('cardValue') == $('.chosen').last().data('cardValue')) {
                $('.chosen').each(function () {
                    // if 1st card has same value as second card, remove both cards and indicate that they are no longer unpaired
                    $(this).animate({
                        opacity: 0
                    }).removeClass('unpaired');
                });
                $('.chosen').each(function () {
                    $(this).removeClass('chosen');
                });
                isCompleteGame();
            } else {
                setTimeout(function () {
                    $('.chosen').each(function () {
                        $(this).html('').removeClass('chosen');
                    });
                }, 1000);
            }
        }
    }

    // check if the player is complete
    function isCompleteGame() {
        if ($('.unpaired').length === 0) {
            $('.container').html('<h1>You Finished the Game!</h1>');
            // tries is not just the amount of cards touched, it should be the amount of matches attempted
            let tries = parseInt(localStorage.getItem("count"))/2;
            $('.container').html(`<h2>Num Tries = ${tries}</h2>`);
        }
    }


});
