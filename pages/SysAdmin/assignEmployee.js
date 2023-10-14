import { API_URL } from "../../settings.js";
import { sanitizeStringWithTableRows, handleHttpErrors, makeOptions } from "../../utils.js";
const options = makeOptions("GET", null, true);
const optionsPOST = makeOptions("POST", null, true);

export async function initAssignEmployee() {
    
    populateActivityDropdown();
  document.getElementById('assign-button').addEventListener('click', async function () {
    const userSearch = document.getElementById('user-search').value;
    const selectedActivity = document.getElementById('activity-dropdown').value;
    
    const assignURL = `${API_URL}/activities/assignActivity?userId=${userSearch}&activityName=${selectedActivity}`;

    try {
      // Make a POST request to your backend API to assign the user to the activity
      const response = await fetch(assignURL, optionsPOST);

      if (response.ok) {
        // Assignment was successful
        const resultMessage = `Assigned user '${userSearch}' to activity '${selectedActivity}'.`;
        document.getElementById('result').textContent = resultMessage;
      } else {
        // Assignment failed
        const errorData = await response.text();
        document.getElementById('result').textContent = `Failed to assign activity: ${errorData}`;
      }
    } catch (error) {
      console.error(error);
      document.getElementById('result').textContent = 'An error occurred during assignment.';
    }
  }); 

}
const URL = API_URL + "/activities";

async function populateActivityDropdown() {
    try {
      const response = await fetch(URL, options);
      
      if (!response.ok) {
        throw new Error(`Failed to fetch activities: ${response.status}`);
      }
  
      const activities = await response.json();
      const activityDropdown = document.getElementById("activity-dropdown");
  
      // Clear any existing options
      activityDropdown.innerHTML = '';
  
      // Create and add options based on the activities data
      activities.forEach((activity) => {
        const option = document.createElement("option");
        option.value = activity.name;
        option.textContent = activity.name;
        activityDropdown.appendChild(option);
      });
    } catch (error) {
      console.error(error);
      document.getElementById('result').textContent = 'An error occurred while fetching activities.';
    }
  }
  