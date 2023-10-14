import { handleHttpErrors, sanitizeStringWithTableRows, makeOptions } from "../../utils.js"
import { API_URL } from "../../settings.js"
import { FETCH_NO_API_ERROR } from "../../settings.js"

export async function initListReservationsForUser() {
    document.getElementById("error").innerText = ""
    try {
      
    
      const URL = API_URL + "/reservations/user/" + localStorage.getItem("user") 
      const reservations = await fetch(URL, makeOptions("GET", null, true)).then(handleHttpErrors)
      const rows = reservations.map(res =>  { 
        return `
      <tr>
        <td>${res.id}</td>
        <td>${res.rentalDate}</td>
        <td>${res.username}</td>
        <td>${res.activityNames.join(", ")}</td>
      </tr>
     `}).join("\n")
      const safeRows = sanitizeStringWithTableRows(rows)
      document.getElementById("reservation-table-rows").innerHTML = safeRows
    } catch (err) {
      if (err.apiError) {
        document.getElementById("error").innerText = err.apiError.message
      } else {
        document.getElementById("error").innerText = err.message + FETCH_NO_API_ERROR
        console.error(err.message + FETCH_NO_API_ERROR)
      }
    }
  }
  