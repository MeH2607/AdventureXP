import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const URL = API_URL + "/reservations";

export async function initAllReservations() {
    document.getElementById("error").innerText = ""
try{
    const reservations = await fetch(URL, makeOptions("GET", null, false/*TODO ændrer til true senere*/)).then(handleHttpErrors);
  
    const reservationRows = reservations.map(res => `
        <tr>
      <td>${res.id}</td>
      <td>${res.rentalDate}</td>
      <td>${res.username}</td>
      <td>${res.activityNames.join(", ")}</td>
      <th>      <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#edit-modal">Edit Reservation</button>
      </th>
      </tr>
        `
    ).join("\n")

    const safeRows = sanitizeStringWithTableRows(reservationRows);
    document.getElementById("reservation-table-rows").innerHTML = safeRows

}catch (error) {
  console.log(error)
}

function reservationModal(){
  const tableDiv = document.getElementById("tableDiv");

  tableDiv.onclick = async(evt) =>{

    const reservationId = evt.target.id;
    const reservationDate = evt.target.rentalDate;
    let activities = evt.target.activityNames.join(", ");

    
    
  }
  
}

}
