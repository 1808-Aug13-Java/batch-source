$(document).ready(function () {
  const items = ['<i class="material-icons">android</i>', '<i class="material-icons">backup</i>',
    '<i class="material-icons">card_giftcard</i>', '<i class="material-icons">motorcycle</i>',
    '<i class="material-icons">dns</i>', '<i class="material-icons">gavel</i>',
    '<i class="material-icons">invert_colors</i>', '<i class="material-icons">offline_bolt</i>'
  ];

  let placed = [];
  let possibleItems = $('div.item');
  // populate the divs
  for (let item of items) {
    let randomIndex = randomIntFromInterval(0, possibleItems.length - 1);
    let secondRandomIndex = randomIntFromInterval(0, possibleItems.length - 1);

    while (placed.includes(randomIndex)) {
      randomIndex = randomIntFromInterval(0, possibleItems.length - 1);
    }

    while (placed.includes(secondRandomIndex) || secondRandomIndex == randomIndex) {
      secondRandomIndex = randomIntFromInterval(0, possibleItems.length - 1);
    }
    // adding the item to a specific div at a random index
    possibleItems.eq(randomIndex).html(item);
    possibleItems.eq(secondRandomIndex).html(item);
    placed[placed.length] = randomIndex;
    placed[placed.length] = secondRandomIndex;
  }

  // on click functionality that will compare the previous clicked and current clicked
  let tries = 0;
  $('div.item').on('click', memoryTestClick);

  // restart the game
  $('#restart').on('click', function () {
    $('div.item').addClass('hide').removeClass('show active').on('click', memoryTestClick);
    $('#tries').html(0);

  });


});

function randomIntFromInterval(min, max) {
  return Math.floor(Math.random() * (max - min + 1) + min);
}

const memoryTestClick = function () {
  console.log('clicked');

  let previousSelected = $('div.show.active');
  $(this).removeClass('hide');
  $(this).addClass('show active');
  $('#tries').html(tries);
  let currentSelected = $(this);
  console.log(previousSelected.length);

  if (previousSelected.length > 0) {
    tries++;
    // if there is another car
    if (currentSelected.html() == previousSelected.html()) {
      console.log('correct!');
      currentSelected.off('click').removeClass('active');
      previousSelected.off('click').removeClass('active');

    } else {
      // ensure the user can see the last seleceted for a second
      setTimeout(function () {
        previousSelected.removeClass('show active').addClass('hide');
        currentSelected.removeClass('show active').addClass('hide');
      }, 500)
    }
  }
}