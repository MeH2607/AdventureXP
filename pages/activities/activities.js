import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const URL = API_URL + "/activities";

export async function initActivities() {
  const activities = await fetch(URL, makeOptions("GET", null, true)).then((res) =>
    handleHttpErrors(res)
  );
  const activitiesTableRows = activities
    .map(
      (activities) =>
        `
        <tr>
        <td>${sanitizeStringWithTableRows(activities.name)}</td>
        <td>${sanitizeStringWithTableRows(activities.description)}</td>
        <td>${sanitizeStringWithTableRows(activities.status)}</td>
        <td>${sanitizeStringWithTableRows(activities.agelimit)}</td>
        <td>${sanitizeStringWithTableRows(activities.employee)}</td>
        </tr>`
    )
    .join("");
  document.getElementById("table-rows").innerHTML =
    sanitizeStringWithTableRows(activitiesTableRows);
}
