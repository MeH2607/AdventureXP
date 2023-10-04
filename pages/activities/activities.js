import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";
const URL = API_URL + "/activities";

export async function initActivities() {
  //const activities = await fetch(URL, makeOptions("GET", null, true)).then((res) => handleHttpErrors(res));
  const activities = await fetch(URL).then((res) => res.json());

  const activitiesTableRows = activities
    .map(
      (activity) =>
        `
        <tr>
        <td>${(activity.name)}</td>
        <td>${(activity.description)}</td>
        <td>${(activity.ageLimit)}</td>
        <td>${(activity.status)}</td>
        <td>${(activity.employee)}</td>
        </tr>`
    )
    const tableRowsAsStr = activitiesTableRows.join("")

    document.getElementById("table-rows").innerHTML = sanitizeStringWithTableRows(tableRowsAsStr)
  }
