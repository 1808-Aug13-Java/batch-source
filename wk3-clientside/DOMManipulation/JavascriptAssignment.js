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
    // check email
    if(found == null) {
      displayWarning();
    }
    // check phone number
    let phoneRegEx = new RegExp(/^\D?(\d{3})\D?\D?(\d{3})\D?(\d{4})$/);
    let foundPhone = phoneRegEx.test(phone.value);
    if(!foundPhone) {
      displayWarning();
    }

    // check bday
    if(!bday.value) {
      displayWarning();
    }

    let planet = document.querySelector("select.form-control");
    let gender = document.querySelector("input[name='gender']:checked");
    if(!gender) {
      displayWarning();
    }

    let checked = document.querySelectorAll("input.activity:checked");
    let checkedValues = [];
    checked.forEach(el => checkedValues.push(el.value));
    if(checked.length == 0) {
      displayWarning();
    }
    
    let warning = document.getElementById("warning");
    if(!warning) {
      addRow(firstName.value + " " + lastName.value, email.value, phone.value,
              bday.value, color.value, gender.value,
            checkedValues);
    }
    
    
    
  }

  function addRow(name, email, phone,
     bday, favColor, gender, activities) {
    
    let table = document.querySelector("table.table tbody");
    let activitiesHTML = [];
    activities.forEach(activity => {
      let actHtml = `
        <li>${activity}</li>
      `;
      
      
      activitiesHTML.push(actHtml);
    });
    let listActivities = `
      <ul>${activitiesHTML.join("")}</ul>
    `;

    let html = `
      <tr scope="row">
        <td>${name}</td>
        <td>${email}</td>
        <td>${phone}</td>
        <td>${bday}</td>
        <td>${favColor}</td>
        <td>${gender}</td>
        <td>${listActivities}</td>
      </tr>
    `;

    table.innerHTML += html;
  }


  // display a warning
  function displayWarning() {
    if(document.getElementById("warning")) {
      return;
    }
    let form = document.getElementById("form-sub").parentNode;
    let el = document.createElement("span");
    el.setAttribute("id", "warning");
    el.classList += "alert alert-warning";
    el.innerHTML = "Enter complete information";
    el.style = "margin-left: 2%;"
    if(el) {
      form.appendChild(el);
    }
    
  }


  // remove the warning about incomplete data
  function removeWarning() {
    document.getElementById("warning").outerHTML = "";
  }
  submit.addEventListener("click", submitData);


  // 5. Create a function openDetails() 
  // which opens the details element. 
  // Invoke this function when the details’ summary
  //  is moused over. The details should be hidden
  //   when the mouse is removed from the summary.
  let details = document.querySelector("details");
  let summary = document.querySelector("summary");
  function openDetails(e) {
    let open = details.getAttribute("open");
    if(!open) {
      details.setAttribute("open", true);
    }    
  }

  function closeDetails(e) {
    details.removeAttribute("open");
  }

  summary.addEventListener("mouseover", openDetails);
  details.addEventListener("mouseleave", closeDetails);


  // 6. concat all span el & print to console
  function concatSpans() {
    let spans = document.querySelectorAll("span");
    let innerHtml = [];
    spans.forEach(span => innerHtml.push(span.innerHTML));

    console.log(innerHtml.join(""));
  }
  concatSpans();

  // 7.Create a function that displays 
  // the current time on earth in the span 
  // with id “earth_time”. Invoke this function 
  // when “Earth time” button is clicked. 
  let earthBtn = document.getElementById("earth_time_check");
  let earthSpan = document.getElementById("earth_time");

  earthBtn.addEventListener("click", function() {
    earthSpan.innerHTML = new Date().toLocaleTimeString();
  });

  // 8. calc mars time and alpha centauri bb
  let earthOrbitalPeriod = 365;
  let marsOrbitalPeriod = 687;
  

  // 9. Three seconds after a user clicks on the
  //  “Intergalactic Directory” heading, the 
  //  background color should change to a random 
  //  color. Make sure this color is never black so
  //   we can still read our black text! (there are other
  //      dark colors it could change to where we also
  //       couldn’t see the text but it’s enough to just
  //        accomodate for a black background)
  

  


});


