window.onload = function() {
  buildQuiz();
  let submit = document.getElementById("submit");
  submit.addEventListener('click', showResults);
}

function buildQuiz() {
  const quizContent = [];
  
  myQuestions.forEach((currQuestion, index) => {
    const answers = [];
    for(letter in currQuestion.answers) {
      answers.push(`
        <label>
          <input type="radio" name="question${index}" 
          value="${letter}"> ${letter} : 
          ${currQuestion.answers[letter]} />
        </label>
      `)
    }

    quizContent.push(`
      <div class="question"> <strong>${index + 1}.
       ${currQuestion.question}</strong>
      </div>
      <div class="answers">
        ${answers.join("")}
      </div>
    `);
  });
  
  document.getElementById("quiz").innerHTML = quizContent.join("");
}

function showResults() {
  const answers = document.getElementsByClassName("answers");
  let numCorrect = 0;
  myQuestions.forEach((question, index) => {
    const answerContainer = answers[index];
    const checkedInput = (answerContainer.querySelector(`
    input:checked`) || {});
    console.log('index ' + index );
    const checkedValue = checkedInput.value;

    if(checkedValue == question.correctAnswer) {
      numCorrect++;
      answerContainer.style.color = "lightgreen";
    } else {
      answerContainer.style.color = "red";
    }
  });

  document.getElementById("results").innerHTML = `
  ${numCorrect} out of ${myQuestions.length} were correct
  `;
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

