import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";

const URL = API_URL + "/reservations";

export async function initAllReservations() {
  retrieveAllReservations();

}
async function retrieveAllReservations() {
  document.getElementById("error").innerText = "";
  const selectedActivity = localStorage.getItem("activityName")
  const fetchActivityReservationsUrl = `${API_URL}/reservations/activity?activityName=${selectedActivity}`;
  try {
    const roles = localStorage.getItem("roles")
    let reservations = await fetch(fetchActivityReservationsUrl, makeOptions("GET", null, true)).then(handleHttpErrors);
    if (roles.includes("ADMIN")) {
      reservations = await fetch(URL, makeOptions("GET", null, true)).then(handleHttpErrors);
    }

    const reservationRows = reservations.map((res) => `
          <tr>
            <td>${res.id}</td>
            <td>${res.rentalDate}</td>
            <td>${res.username}</td>
            <td>${res.activityNames.join(", ")}</td>
            <th> <button class="editBtn">Edit Reservation</button>
            <th> <button class="deleteBtn">Cancel Reservation</button>
            </th>
          </tr>
      `).join("\n");

    const safeRows = sanitizeStringWithTableRows(reservationRows);
    document.getElementById("reservation-table-rows").innerHTML = safeRows;

    // Add click handler to editBtn
    const table = document.getElementById("tableElement");

    table.addEventListener('click', function (evt) {

      if (evt.target.classList.contains("editBtn")) {
        const row = evt.target.closest('tr');
        if (row) {
          // Extract data from the row
          const id = row.querySelector('td:first-child').textContent;

          localStorage.setItem("EditReservationId", id);

          // Redirect to a different page with the data
          router.navigate(`/editReservation/`);
        }
      }

      // Delete reservation button
      table.addEventListener('click', async function (evt) {
        if (evt.target.classList.contains("deleteBtn")) {
          const row = evt.target.closest('tr');
          if (confirm("Do you want to cancel the reservation update?")) {
            
              const id = row.querySelector('td:first-child').textContent;
              await fetch(URL + "/" + id, makeOptions("DELETE", null, true));
            
            
          } else {
            window.close();
          }
        }
      });
    });
  } catch (error) {
    console.log(error);
  }
}
