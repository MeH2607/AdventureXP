import { API_URL } from "../../settings.js"
//const API_URL = "http://localhost:8080/api/"

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("login-btn").onclick = login;
    document.getElementById("logout-btn").onclick = logout;

    const usernameInput = document.getElementById("username-input")
    const passwordInput = document.getElementById("password-input")

    const token = localStorage.getItem("token")

    async function handleHttpErrors(res) {
        if (!res.ok) {
            const errorResponse = await res.json();
            const error = new Error(errorResponse.message)
            error.apiError = errorResponse
            console.log(error)
            throw error
        }
        return res.json()
    }

    async function login(evt) {
        evt.stopPropagation()

        const loginRequest = {}
        loginRequest.username = usernameInput.value
        loginRequest.password = passwordInput.value

        const options = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(loginRequest)
        }

        try {
            const response = await fetch(API_URL + "auth/login", options).then(handleHttpErrors)
            storeLoginDetails(response)
        } catch (error) {
            console.log(error)
        }
    }

    function storeLoginDetails(res) {
        localStorage.setItem("token", res.token)
        localStorage.setItem("user", res.username)
        localStorage.setItem("roles", res.roles)
        //Update UI
        //toggleLoginStatus(true)
    }

    function logout() {
        localStorage.removeItem("token")
        localStorage.removeItem("user")
        localStorage.removeItem("roles")
    }
})