import { API_URL } from "../../settings.js"
//const API_URL = "http://localhost:8080/api/"

export async function initLogin() {
    document.getElementById("login-btn").onclick = login;
    document.getElementById("login-message").innerText = ""
    
    const usernameInput = document.getElementById("username-input")
    const passwordInput = document.getElementById("password-input")
    
    const token = localStorage.getItem("token")
    
    async function handleHttpErrors(res) {
        if (!res.ok) {
            const errorResponse = await res.json();
            const error = new Error(errorResponse.message)
            error.apiError = errorResponse
            throw error
        }
        return res.json()
    }
    
    async function login(evt) {
        evt.preventDefault();
        evt.stopPropagation();
    
        const loginRequest = {}
        loginRequest.username = usernameInput.value
        loginRequest.password = passwordInput.value
    
        const options = {
            method: "POST",
            headers: {"Content-type": "application/json"},
            body: JSON.stringify(loginRequest)
        }
    
        try {
            const response = await fetch(API_URL + "/auth/login", options).then(handleHttpErrors)
            storeLoginDetails(response)
        } catch (error) {
            console.log(error)
        }

        if (localStorage.getItem("token") !== null) {
            usernameInput.value = ""
            passwordInput.value = ""
            document.getElementById("login-message").innerText = "You are now logged in to: " + localStorage.getItem("user")
        }
        else {
            document.getElementById("login-message").innerText = "Login failed"
        }
    }
    
    /**
    * Store username, roles and token in localStorage, and update UI-status
    * @param res - Response object with details provided by server for a succesful login
    */
    function storeLoginDetails(res) {
        localStorage.setItem("token", res.token)
        localStorage.setItem("user", res.username)
        localStorage.setItem("roles", res.roles)
    }
}