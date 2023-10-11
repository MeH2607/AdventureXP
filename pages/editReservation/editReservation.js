import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const reservationURL = API_URL + "/reservations/";
const activitiesURL = API_URL + "/activities";

export async function initEditReservation(reservationId) {
    // Use the reservationId to fetch the specific reservation for editing
    try {
  
        const activitiesResponse = await fetch(activitiesURL);
        const reservationResponse = await fetch(reservationURL + reservationId, makeOptions("GET", null, false));

    
        //set up the form
        if (!activitiesResponse.ok) {
          throw new Error("Network response was not ok");
        }
        
    
        
        const activities = await activitiesResponse.json();
        const checkBoxDiv = document.getElementById("checkboxDiv");
        //Setup the checkboxes with Activities in the form
        if (!checkBoxDiv.classList.contains("initialized")) {
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
        
          checkBoxDiv.classList.add("initialized");
        }

        const dateInput = document.getElementById("editInputDate");
        dateInput.value = reservationResponse.rentalDate;

        const checkboxes = document.querySelectorAll('input[type="checkbox"]');
        reservationResponse.activityNames.forEach((act) =>{
            checkboxes.forEach((check) =>{
                if(check.value === act){
                    checkbox.checked = true;  
                }
            })
        })
     }catch (error) {
        console.error(error);
      }
  }

