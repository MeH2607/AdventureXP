import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";

const URL = API_URL + "/activities";

export async function initActivities() {
  const activities = await fetch(URL,makeOptions("GET", null, true)).then((res) => res.json());
  updateActivitiesTable(activities);
}
function updateActivitiesTable(activities) {
  const tableRows = document.getElementById("table-rows");
  tableRows.innerHTML = "";

  activities.forEach((activity, index) => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td>${activity.name}</td>
      <td>${activity.description}</td>
      <td>${activity.ageLimit}</td>
      <td>${activity.status}</td>
      <td>${activity.employee}</td>
      <td><button class="edit-button" data-index="${index}">Edit</button></td>
    `;
    tableRows.appendChild(row);
  });

  // Add event listeners to the "Edit" buttons
  document.querySelectorAll(".edit-button").forEach((button) => {
    button.addEventListener("click", () => openEditModal(activities, button));
  });
}

function openEditModal(activities, button) {
  const index = button.getAttribute("data-index");
  const activity = activities[index];

  // Fill the modal fields with the activity details
  document.getElementById("edit-name").value = activity.name;
  document.getElementById("edit-description").value = activity.description;
  document.getElementById("edit-age-limit").value = activity.ageLimit;
  document.getElementById("edit-status").value = activity.status;
  document.getElementById("edit-employee").value = activity.employee;

  // Show the modal
  const modal = document.getElementById("edit-modal");
  modal.style.display = "block";

  // Add an event listener to the close button
  document.getElementById("close-modal").addEventListener("click", closeEditModal);
  // Add an event listener to the save button
  document.getElementById("save-changes").addEventListener("click", saveChanges);
}

function closeEditModal() {
  const modal = document.getElementById("edit-modal");
  modal.style.display = "none";
}

async function saveChanges() {
  const URL = API_URL + "/activities";
  const reservationRequest = {
    name: document.getElementById("edit-name").value,
    description: document.getElementById("edit-description").value,
    ageLimit: document.getElementById("edit-age-limit").value,
    status: document.getElementById("edit-status").value,
    employee: document.getElementById("edit-employee").value,
  };

  const fetchOptions = {}
  fetchOptions.method = "PUT";
  fetchOptions.headers = { "Content-Type": "application/json" }
  fetchOptions.body = JSON.stringify(reservationRequest)
  fetchOptions.authorization = "Bearer " + localStorage.getItem("token")

  try {
    await fetch(URL, fetchOptions).then(handleHttpErrors);
    alert("Changes saved");
  }
  catch (err) {
    const errorMsg = err.apiError ? err.apiError.message : err.msg;
    alert("Error: " + err);
  }

  closeEditModal();
}
