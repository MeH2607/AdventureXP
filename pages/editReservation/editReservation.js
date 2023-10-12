import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const reservationURL = API_URL + "/reservations/id/";
const activitiesURL = API_URL + "/activities";

export async function initEditReservation() {
  try {
    const id = localStorage.getItem("EditReservationId");
    console.log("ID from localstorage = " + id)

    const activitiesResponse = await fetch(
      activitiesURL,
      makeOptions("GET", null, true)
    );
    const reservationResponse = await fetch(
      reservationURL + id,
      makeOptions("GET", null, true)
    );

    //set up the form
    if (!activitiesResponse.ok) {
      throw new Error("Network response was not ok");
    }

    const activities = await activitiesResponse.json();
    const checkBoxDiv = document.getElementById("editCheckboxDiv");
    //Setup the checkboxes with Activities in the form
    checkBoxDiv.innerHTML = "";
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

    //Sets default date
    const dateInput = document.getElementById("editInputDate");

    if (reservationResponse.ok) {
      // Assuming that reservationResponse contains JSON data
      const reservationData = await reservationResponse.json();
      dateInput.value = reservationData.rentalDate;
    } else {
      console.error("Reservation response was not ok");
    }
   
    if (reservationResponse && reservationResponse.activityNames) {
      const activityNames = reservationResponse.json().activityNames;
      const checkboxes = document.querySelectorAll('input[type="checkbox"]');
      activityNames.forEach((act) => {
        checkboxes.forEach((checkbox) => {
          if (checkbox.value === act) {
            checkbox.checked = true;
          }
        });
      });
    } else {
      console.error('The reservationResponse object is missing or does not contain activityNames.');
    }
    
  } catch (error) {
    console.error(error);
  }
}
