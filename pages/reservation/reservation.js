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
    document.getElementById("reservation-table-rows").addEventListener("click",setupReservationModal)
    const rows = reservations.map(res =  `
        <tr>
      <td>${res.id}</td>
      <td>${res.rentalDate}</td>
      <td>${res.username}</td>
      <td>${res.reservationDate}</td>
      <td>${res.activities.join(",")}</td>
    </tr>
        `
    ).join("\n")
}

}
