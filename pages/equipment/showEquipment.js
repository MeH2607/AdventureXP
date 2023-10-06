import { API_URL } from "../../settings.js";
import { sanitizeStringWithTableRows, handleHttpErrors, makeOptions } from "../../utils.js";

const URL = API_URL + "/equipment";

export async function initEquipment() {
    const equipment = await fetch(URL).then(res => res.json());

    const equipmentTableRows = equipment.map(equipment => `
        <tr>
            <td>${equipment.name}</td>
            <td>${equipment.status}</td>
            <td>${equipment.activity}</td>
        </tr>
    `)

    const tableRowsAsStr = equipmentTableRows.join("")

    document.getElementById("table-rows").innerHTML = sanitizeStringWithTableRows(tableRowsAsStr)
}