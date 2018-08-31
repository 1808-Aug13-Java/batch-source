// 1
document.getElementById("google").href = "https://www.google.com";
document.getElementById("twitter").setAttribute("href", "https://twitter.com/");
document.getElementById("slack").setAttribute("href", "https://slack.com/");
document.getElementById("javadocs").setAttribute("href", "https://docs.oracle.com/javase/8/docs/api/");

// 2
let planet = document.getElementById("planet");
planet.options[3].disabled = true;

// 3
planet.addEventListener("click", alienText);

function alienText() {
    let paragraphs = document.getElementsByTagName("p"); 
    if (planet.value !== "Earth") {
        for (let i = 0; i < paragraphs.length; i++) {
            if (paragraphs[i].innerText == "Beep boop") {
                paragraphs[i].removeAttribute("hidden");
            }
        }
    } else {
        for (let i = 0; i < paragraphs.length; i++) {
            if (paragraphs[i].innerText == "Beep boop") {
                paragraphs[i].hidden = true;
            }
        }
    }
}

// 4
