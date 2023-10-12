import { API_URL } from "../../settings.js";
import { sanitizeStringWithTableRows, handleHttpErrors, makeOptions } from "../../utils.js";

const URL = API_URL + "/equipment";

export async function initEquipment() {
    const options = makeOptions("GET", null, true);
    const equipment = await fetch(URL, options).then(res => res.json());
  
    const tableRows = document.getElementById("table-rows");
    tableRows.innerHTML = "";

  
    equipment.forEach(equipmentItem => {
      const row = document.createElement("tr");

      row.innerHTML = `
        <td>${equipmentItem.name}</td>
        <td>
          <select class="status-dropdown">
            <option value="Available" ${equipmentItem.status === "Available" ? "selected" : ""}>Available</option>
            <option value="Unavailable" ${equipmentItem.status === "Unavailable" ? "selected" : ""}>Unavailable</option>
          </select>
        </td>
        <td>${equipmentItem.activity}</td>
      `;
      row.setAttribute("data-id", equipmentItem.name);
      tableRows.appendChild(row);
    });
  
    
    tableRows.addEventListener("change", async (event) => {
      if (event.target.classList.contains("status-dropdown")) {
        const selectedStatus = event.target.value;
        const row = event.target.closest("tr");
        const itemId = row.getAttribute("data-id");
      
        const itemToUpdate = equipment.find(item => item.id === itemId);
        if (itemToUpdate) {
          itemToUpdate.status = (selectedStatus === "Available") ? "Available" : "Unavailable";
        }
  
        const updateOptions = makeOptions("PUT", { status: selectedStatus }, true);
        try {
            await fetch(`${URL}/${itemId}`, updateOptions).then(handleHttpErrors);
        }
        catch(error) {
            alert(error);
        }
      }
    });
  }