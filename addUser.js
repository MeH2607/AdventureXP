document.getElementById('signup-button').addEventListener('click', function(event) {
    event.preventDefault(); // Prevent the default link behavior

    // Redirect to the sign-up page (replace '/signup' with the actual URL)
    window.location.href = 'addUser.html';
});

export async function signupUser() {
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
            age,
        };

        // Send a POST request to your server to handle user signup
        const response = await fetch('api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData),
        });

        if (response.ok) {
            // User signup was successful
            const responseData = await response.json();
            return responseData; // You can handle the response data here
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
