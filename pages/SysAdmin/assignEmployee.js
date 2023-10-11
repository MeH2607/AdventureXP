import { API_URL } from "../../settings.js";
export async function initAssignEmployee() {

/* document.getElementById('assign-button').addEventListener('click', function () {
    const userSearch = document.getElementById('user-search').value;
    const selectedActivity = document.getElementById('activity-dropdown').value;

    // You can implement your assignment logic here
    // For example, you can display a message with the selected user and activity
    const resultMessage = `Assigned user '${userSearch}' to activity '${selectedActivity}'.`;
    document.getElementById('result').textContent = resultMessage;
}); */
populateActivityDropdown();
}
const URL = API_URL + "/activities";

async function populateActivityDropdown() {
    const activities = await fetch(URL).then((res) => res.json());
    const activityDropdown = document.getElementById("activity-dropdown");
  
    // Clear any existing options
    activityDropdown.innerHTML = '';
  
    // Create and add options based on the activities data
    activities.forEach((activity) => {
      const option = document.createElement("option");
      option.value = activity.name;
      option.textContent = activity.name;
      activityDropdown.appendChild(option);
      console.log(activities)
    });
  }

  

  
  
  