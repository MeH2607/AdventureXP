import { API_URL } from "../../settings.js";
import {
  sanitizeStringWithTableRows,
  handleHttpErrors,
  makeOptions,
} from "../../utils.js";

export function initSignup() {
  // Find the "signup" button by its ID on the current page
  const signupButton = document.getElementById("signup");

  if (signupButton) {
    // Add a click event listener to the button if it exists on this page
    signupButton.addEventListener("click", async function (event) {
      event.preventDefault(); // Prevent the default form submission
      console.log("this shit clicked")
      try {
        // Call the signupUser function when the button is clicked
        await signupUser();
      } catch (error) {
        // Handle any errors that occur during signup
        console.error("Signup Error:", error);
      }
    });
  }
}

// Call initSignup when the DOM is loaded
document.addEventListener("DOMContentLoaded", initSignup);

async function signupUser() {
  try {
    // Retrieve form field values
    // @ts-ignore
    const username = document.getElementById("username").value;
    // @ts-ignore
    const email = document.getElementById("email").value;
    // @ts-ignore
    const password = document.getElementById("password").value;
    // @ts-ignore
    const name = document.getElementById("name").value;
    // @ts-ignore
    const lastName = document.getElementById("lastName").value;
    // @ts-ignore
    const age = parseInt(document.getElementById("age").value, 10);

    // Create a JavaScript object with the form data
    const formData = {
      username,
      email,
      password,
      name,
      lastName,
      age,
    };

    // Send a POST request to your server to handle user signup
    const response = await fetch(API_URL + "/users/signup", makeOptions("POST", formData, true));

    if (response.ok) {
      // User signup was successful
      const responseData = await response.json();
      return responseData;
      alert("User was created successfully")
      window.location.href = "/";
    } else {
      // User signup failed
      const errorData = await response.json();
      throw new Error(errorData.message);
    }
  } catch (error) {
    // Handle any errors that occur during the signup process
    console.error("Error:", error);
    throw error;
  }
}
