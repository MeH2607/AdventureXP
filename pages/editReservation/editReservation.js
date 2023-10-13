import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const reservationURL = API_URL + "/reservations/";
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
       reservationURL + "id/" + id,
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

      const checkboxes = document.querySelectorAll('input[type="checkbox"]');
      reservationData.activityNames.forEach((act) => {
        checkboxes.forEach((checkbox) => {
          if (checkbox.value === act) {
            checkbox.checked = true;
          }
        });
      }); // Close the forEach loop here
    } else {
      console.error("Reservation response was not ok");
    }


     //Making reservation body form data
   document.getElementById("confirmEditBtn").onclick = (evt) => {
    evt.preventDefault();
    // Get date
    const inputDate = document.getElementById("editInputDate").value;
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
      activityNames: selectedActivities, // Send the array of selected activities
      username: localStorage.getItem("user")
    };
    const fetchOption = makeOptions("PUT", body, true);
  
    // PUT request
    fetch(reservationURL+id,fetchOption)
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
    alert("Reservation successfully updated");
    router.navigate("/reservations")
    return responseData;
  })
  .catch((error) => {
    console.error(error);
    alert("Reservation update failed");
});
  }
 

  //Cancel button
  document.getElementById("cancelEditBtn").onclick = ()=>{
    if(confirm("Do you want to cancel the reservation update?")){
      router.navigate("/reservations")
    }else{
      self.close()

    }
  }

  } catch (error) {
    console.error(error);
  }
}
