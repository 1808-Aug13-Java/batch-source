document.addEventListener("DOMContentLoaded", function() {
  // 1. make each link direct the user to 
  // its respective website
  let google = document.getElementsByName("google");
  google.href = "http://google.com";

  let twitter = document.getElementsByName("twitter");
  twitter.href = "http://twitter.com";
  let slack = document.getElementsByName("slack");
  slack.href = "http://slack.com";

  let javadocs = document.getElementsByName("javadocs");
  javadocs.href = "https://docs.oracle.com/javase/8/docs/api/";

  // 2. disable the pluto plant of residency option
  let planetOptions = document.getElementById("planet");
  planetOptions.lastElementChild.disabled = true;

  // 3. define a function alienText()
  function alientText() {
    let mainContainer = document.getElementsByClassName("container")[0];
    
    let alienP = mainContainer.getElementsByTagName("p")[5];
    
    
    if(planetOptions.value != "Earth") {
      alienP.hidden = false;
    } else {
      alienP.hidden = true;
    }
  }
  planetOptions.addEventListener("click", alientText);

  // 4. When the submit button is pressed, 
  // get the values from all of the input into a 
  // new row in the table below.  Make sure no input 
  // is empty, check that first and last name are at least 
  // two letters each. Validate for valid phone number and 
  // email structure. This should continue to work for 
  // multiple entries and rows.
  let submit = document.getElementById("form-sub");
  const submitData = function() {
    let form = document.getElementsByClassName
    ("form-group")[0];
    let data = {};
    let firstName = document.getElementById("firstname");
    let lastName = document.getElementById("lastname");
    if(firstName.value.length < 2 || lastname < 2) {
      displayWarning();
    }

    let email = document.getElementById("email");
    let phone = document.getElementById("phone");
    let bday = document.getElementById("bday");
    let color = document.getElementById("color");
    let regex = /^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$/gi;
    let found = email.value.match(regex);

    if(found == null) {
      displayWarning();
    }

    let phoneRegEx = new RegExp(/^\D?(\d{3})\D?\D?(\d{3})\D?(\d{4})$/);
    let foundPhone = phoneRegEx.test(phone.value);
    if(!foundPhone) {
      displayWarning();
    }

    
    
    
  }

  function displayWarning() {
    console.log('error has occured');
    
    if(document.getElementById("warning")) {
      return;
    }
    
  }

  function removeWarning() {
    document.getElementById("warning").outerHTML = "";
  }
  submit.addEventListener("click", submitData);
});


