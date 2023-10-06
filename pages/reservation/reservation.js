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
      </tr>
        `
    ).join("\n")

    const safeRows = sanitizeStringWithTableRows(reservationRows);
    document.getElementById("reservation-table-rows").innerHTML = safeRows
}catch (error) {
  console.log(error)
}

}
