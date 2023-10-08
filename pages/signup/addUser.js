// Wait for the DOM to be fully loaded
document.addEventListener('DOMContentLoaded', function () {
    // Find the "signup" button by its ID
    const signupButton = document.getElementById('signup');

    // Add a click event listener to the button
    signupButton.addEventListener('click', async function () {
        try {
            // Call the signupUser function when the button is clicked
            await signupUser();
        } catch (error) {
            // Handle any errors that occur during signup
            // You can display an error message to the user or take other actions
            console.error('Signup Error:', error);
        }
    });
});

 async function signupUser() {
    try {
        // Retrieve form field values
        const username = document.getElementById('username').value;
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const name = document.getElementById('name').value;
        const lastName = document.getElementById('lastName').value;
        const age = parseInt(document.getElementById('age').value, 10);

        // Create a JavaScript object with the form data
        const formData = {
            username,
            email,
            password,
            name,
            lastName,
            age
        };

        // Send a POST request to your server to handle user signup
        const response = await fetch('http://localhost:8080/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });

        if (response.ok) {
            // User signup was successful
             // You can handle the response data here
        } else {
            // User signup failed
            const errorData = await response.json();
            throw new Error(errorData.message);
        }
    } catch (error) {
        // Handle any errors that occur during the signup process
        console.error('Error:', error);
        throw error;
    }
}
