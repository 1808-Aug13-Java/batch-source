window.onload = function() {
    buildQuiz();
    document.getElementById("submit").addEventListener("click", showResults);
}

function buildQuiz(){
    const quizContent = [];

    myQuestions.forEach((currentQuestion, questionNumber) =>{
        const answers = [];
        for(letter in currentQuestion.answers){
            answers.push(`
            <label>
                <input type="radio" name="question${questionNumber}" 
                value="${letter}"> ${letter} : ${currentQuestion.answers[letter]}
            </label>
            `)
        }

        quizContent.push(`
            <div class="question" ><strong> ${questionNumber+1}. ${currentQuestion.question} </strong> </div>
            <div class="answers"> ${answers.join("")} </div>
        `)
    });

    document.getElementById("quiz").innerHTML = quizContent.join("");
}

function showResults(){
    const answerContainer = document.getElementById("quiz").getElementsByClassName("answers");

    let numCorrect = 0;

    myQuestions.forEach((currentQuestion, questionNumber)=>{
        const currentAnswers = answerContainer[questionNumber];
        // console.log(currentAnswers);
        const checkedInput = (currentAnswers.querySelector("input[name=question" + questionNumber + "]:checked") || {});
        const checkedValue = checkedInput.value;

        if(checkedValue === currentQuestion.correctAnswer){
            numCorrect++;
            currentAnswers.style.color = "lightgreen";
        } else {
            currentAnswers.style.color = "red";
        }
    })

    document.getElementById("results").innerHTML = `${numCorrect} out of ${myQuestions.length} where correct`;
}

const myQuestions = [
    {
        question: '7 + 7 + "7" = ?',
        answers: {
            a: '"777"',
            b: '"147"',
            c: 21
        },
        correctAnswer: "b"
    },
    {
        question: "What is the inherit boolean value of an empty object?",
        answers: {
            a: 'true',
            b: 'false',
            c: 'undefined'
        },
        correctAnswer: "a"
    },
    {
        question: "What is the inherit boolean value of an empty array?",
        answers: {
            a: 'true',
            b: 'false',
            c: 'undefined' 
        },
        correctAnswer: "a"
    },
    {
        question: "typeOf(NaN) = ?",
        answers: {
            a: 'NaN',
            b: 'undefined',
            c: 'Number',
        },
        correctAnswer: "c"
    },
    {
        question: "NaN == NaN",
        answers: {
            a: "true",
            b: "false",
            c: "undefined"
        },
        correctAnswer: "b"
    },
    {
        question: 'What is the inherit boolean value of "false"?',
        answers: {
            a: "true",
            b: "false",
            c: "undefined"
        },
        correctAnswer: "a"
    }
];