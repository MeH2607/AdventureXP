//TODO tilføj bruger credentials når man opretter en reservation

import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const reservationURL = API_URL + "/reservations";
const activitiesURL = API_URL + "/activities";

export async function initMakeReservations() {

  //Setting up the form
  try {
    const activitiesResponse = await fetch(activitiesURL, makeOptions("GET", null, true));

    if (!activitiesResponse.ok) {
      throw new Error("Network response was not ok");
    }

    
    const activities = await activitiesResponse.json();
    const checkBoxDiv = document.getElementById("checkboxDiv");
    
    checkBoxDiv.innerHTML = ""
      activities.forEach((act) => {
        const activityLabel = document.createElement("label");
        const breakLine = document.createElement("br");
        activityLabel.textContent = act.name;
        const check = document.createElement("input");
        check.setAttribute("type", "checkbox");
        check.value = act.name;
        checkBoxDiv.appendChild(breakLine);
        checkBoxDiv.appendChild(activityLabel);
        checkBoxDiv.appendChild(check);
      });
    
    


    //Making reservation from form data
   document.getElementById("makeReservationBtn").onclick = (evt) => {
  evt.preventDefault();
  // Get date
  const inputDate = document.getElementById("inputDate").value;
  // Get selected activities
  let selectedActivities = [];
  var checkBoxes = checkBoxDiv.querySelectorAll('input[type="checkbox"]');
  checkBoxes.forEach((act) => {
    if (act.checked) {
      selectedActivities.push(act.value);
    }
  });

  const body = {
    rentalDate: inputDate,
    username: localStorage.getItem("user"),
    activityNames: selectedActivities // Send the array of selected activities
  };
  const fetchOption = makeOptions("POST", body, true);

  // POST request
  fetch(reservationURL, fetchOption)
    .then((postResponse) => {
      if (postResponse.ok) {
        return postResponse.json();
      } else {
        return postResponse.json().then((errorData) => {
          throw new Error(errorData.message);
        });
      }
    })
    .then((responseData) => {
      alert("Reservation successful");
      // Do something with responseData
      return responseData;
    })
    .catch((error) => {
      console.error(error);
      alert("Reservation failed");
});
    }
  } catch (error) {
    console.error(error);
  }
}

