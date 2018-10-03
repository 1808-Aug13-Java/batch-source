const imageArr = {
    1: './images/marble1.jpg',
    2: './images/marble1.jpg',
    3: './images/marble2.jpg',
    4: './images/marble2.jpg',
    5: './images/marble3.jpg',
    6: './images/marble3.jpg',
    7: './images/marble4.jpg',
    8: './images/marble4.jpg',
    9: './images/marble5.jpg',
    10: './images/marble5.jpg',
    11: './images/marble6.jpg',
    12: './images/marble6.jpg'
}
let assignedSquares = {};
let foundMatch = false;
let toggledSquares = {};
let faceup = 0;
let unmatched = 12;
let match1 = [];
let match2 = [];
$(document).ready(function () {
    renderAllCards();
    $('img').on('click', toggleCard);
});


function toggleCard() {
    console.log(this.id);
    if (faceup < 2) {
        if (toggledSquares[this.id] == "facedown") {
            toggledSquares[this.id] = "faceup";
            console.log("toggledSquares[" + this.id + "] faceup");
            $(this).attr('src', imageArr[assignedSquares[this.id]]);
            faceup++;
            if (faceup == 2) {
                checkIfMatch();
                faceup = 0;
            }
        }
    }
}

function checkIfMatch() {
    // check if two cards match
    // if cards match, remove both of them
    // if cards do not match, flip them facedown
    let faceupCards = [];
    for (let i = 1; i <= 12; i++) {
        if (toggledSquares[i] === "faceup") {
            faceupCards.push(i);
        }
    }
    console.log("faceupCards: " + faceupCards[0] + " " + faceupCards[1]);
    for (let i = 0; i < 6; i++) {
        if (match1[i] == match2[i] && match1[i] === faceupCards[0] && match2[i] === faceupCards[1]) {
            foundMatch = true;
            alert("cards match");
            faceupCards = [];
            removeCards();
        } else if (match1[i] == match2[i] && match1[i] === faceupCards[1] && match2[i] === faceupCards[0]){
            foundMatch = true;
            alert("cards match");
            faceupCards = [];
            removeCards();
        }
    }
    if (!foundMatch) {
        alert("cards do not match");
        faceupCards = [];
        hideAllCards();
    } else {
        foundMatch = !foundMatch;
    }
}

function hideAllCards() {
    for (let i = 1; i <= 12; i++) {
        if (assignedSquares[i] !== null) {
            toggledSquares[i] = "facedown";
            $(`#${i}`).attr('src', './images/cover.jpg');
        }
    }
}


function removeCards() {
    for (let i = 1; i <= 12; i++) {
        if (assignedSquares[i] === null) {
            console.log("assigned squares" + i + " is now null");
            let element = '#pic' + i;
            $(element).html('');
            unmatched -= 1;
        }
    }
    if (unmatched == 0) {
        alert("You won the round!");
        renderAllCards();
    }
}


function renderAllCards() {

    assignedSquares = {
        1: null,
        2: null,
        3: null,
        4: null,
        5: null,
        6: null,
        7: null,
        8: null,
        9: null,
        10: null,
        11: null,
        12: null
    };

    toggledSquares = {
        1: "facedown",
        2: "facedown",
        3: "facedown",
        4: "facedown",
        5: "facedown",
        6: "facedown",
        7: "facedown",
        8: "facedown",
        9: "facedown",
        10: "facedown",
        11: "facedown",
        12: "facedown"
    }
    faceup = 0;
    unmatched = 12;

    count = 12;
    match1 = [];
    match2 = [];
    do {
        let random = Math.floor(Math.random() * 12) + 1;
        if (assignedSquares[random] == null) {
            assignedSquares[random] = count;
            if (count % 2 == 1) {
                match1.push(assignedSquares[random]);
            } else {
                match2.push(assignedSquares[random]);
            }
            count--;
        }
    } while (count > 0);

    for (let i = 1; i <= 12; i++) {
        console.log(i + "=i, assignedSquares[" + i + "] =" + assignedSquares[i]);
    }
}