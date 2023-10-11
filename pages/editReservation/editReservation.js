import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const reservationURL = API_URL + "/reservations";
const activitiesURL = API_URL + "/activities";

export async function initEditReservation(){
    //Setting up the form
    try {
        const activitiesResponse = await fetch(activitiesURL);
        const reservationResponse = await fetch(reservationURL + )
    
        if (!activitiesResponse.ok) {
          throw new Error("Network response was not ok");
        }
    
        
        const activities = await activitiesResponse.json();
        const checkBoxDiv = document.getElementById("checkboxDiv");
        
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
}

}