

// The array of cards. 
let cardArr = []

// The current score
let score = 0;

// The current number of attempted matches
let attempts = 0;

// The last card that was clicked. 
let lastCardClicked = undefined;

// Specifies if cards can be clicked on. 
let clickingEnabled = true;


// String for a cleared card.
const CLEARED = "matched";

// Displayed on the card when face down
const FACE_DOWN = "";



function addCard(card) {
	const CARDS_PER_ROW = 5;

	// Create a new Column in the row for a card. 
	let newCol = $('<div class="col-sm"></div>').html(card);

	console.log("Rows: " + $('#cardTable').children().length);
	// Get the last row in the table. 
	let lastRow = $('#cardTable .row:last');
//	let lastRow = $('#row1');

	// If will be more than 5 cards to a row by adding one, create a new card. 
	if (lastRow.children().length == CARDS_PER_ROW) {
		// alert("More than 5");
		// console.log("More than 5");
		
		lastRow = $("<div class='row'></div>")
		$('#cardTable').append(lastRow);
	}

	// Append the card to the new column
	newCol.append(card);

	// Append the column to the row. 
	lastRow.append(newCol);

	cardArr.push(card);

} // end of addCard



// Creates a new card. 
function createCard(value) {
	let newCard = $("<div class='card'></div>");
	newCard.value = value;
	newCard.html("<p>" + newCard.value + "</p>");//For testing
	newCard.text(FACE_DOWN);

	newCard.click(function () {
		onCardClick(newCard);
	});

	return newCard;
}





function onCardClick(card) {
	console.log("Card Value: " + card.value);

	// Do nothing if clicking isn't enabled. 
	if (!clickingEnabled) {
		console.log("Clicking not enabled");
		return;
	}

	// First, make sure that we can only click facedown cards, and that the user 
	// clicks a different card. 
	if (card.text() == FACE_DOWN)
			// && card.text() != CLEARED 
			// && card != lastCardClicked) 
	{
		console.log("Facedown && Not Same");

		// If this is the first of two cards clicked, set that it is the last card
		// clicked. 
		if (lastCardClicked == undefined) {
			// Set the last clicked card.
			lastCardClicked = card;

			// Turn this card face up. 
			setFaceUp(card);
		}
		// Otherwise, flip the 2nd card face up, and dely the logic checking for a 
		// moment while the user sees the value that was revealed. 
		else {
			setFaceUp(card);

			// Disable clicking for the timeout. 
			clickingEnabled = false;
			
			setTimeout(function () {
				// If the values are the same, increment score and flag cards as cleared. 
				if (card.value == lastCardClicked.value) {
					setCleared(lastCardClicked);
					setCleared(card);
					score++;
					updateScore();
				}
				// Otherwise, flip the cards back over. 
				else {
					setFaceDown(lastCardClicked);
					setFaceDown(card);
				}
				
				// Either way, add an attempt. 
				attempts++;
				updateAttempts();

				// Clear the last card clicked. 
				lastCardClicked = undefined;

				// Re-enable clicking
				clickingEnabled = true;
			}, 1250);
		}
	}
} // end of onCardClick



function updateScore() {
	$('#score').text(score);
}

function updateAttempts() {
	$('#attempts').text(attempts);
}


function setFaceUp(card) {
	card.html("<p>" + card.value + "</p>");
}

function setFaceDown(card) {
	card.text(FACE_DOWN);
}

function setCleared(card) {
	card.text(CLEARED);
}



window.onload = function () {
	addCard(createCard(1));
	addCard(createCard(5));
	addCard(createCard(4));
	addCard(createCard(3));
	addCard(createCard(2));
	
	addCard(createCard(1));
	addCard(createCard(5));
	addCard(createCard(4));
	addCard(createCard(3));
	addCard(createCard(2));
};




