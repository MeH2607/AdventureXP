//import "https://unpkg.com/navigo"  //Will create the global Navigo object used below
import "./navigo_EditedByLars.js"; //Will create the global Navigo, with a few changes, object used below
//import "./navigo.min.js"  //Will create the global Navigo object used below

import { setActiveLink, loadHtml, renderHtml } from "./utils.js";

import { initActivities}from "./pages/activities/activities.js";
import { initEquipment } from "./pages/equipment/showEquipment.js";
import { initAllReservations } from "./pages/reservation/reservation.js";
import { initMakeReservations } from "./pages/MakeReservation/makeReservation.js";
import { initSignup } from "./pages/signup/addUser.js";
import { initLogin } from "./pages/login/login.js";
import { initListReservationsForUser } from "./pages/MyReservations/showReservationForUser.js";
import { toggleUiBasedOnRoles } from "./pages/login/login.js";
import { initAssignEmployee } from "./pages/SysAdmin/assignEmployee.js";

window.addEventListener("load", async () => {
  const templateActivities = await loadHtml("./pages/activities/activities.html");
  const templateReservations = await loadHtml("./pages/reservation/reservation.html");
  const templateEquipment = await loadHtml("./pages/equipment/showEquipment.html");
  const templateLogin = await loadHtml("./pages/login/login.html");  
  const templateAddUser = await loadHtml("./pages/signup/addUser.html");
  const templateMakeReservation = await loadHtml("./pages/MakeReservation/makeReservation.html")
  const templateMyReservations = await loadHtml("./pages/MyReservations/myReservations.html")
 const templateAssignEmployee = await loadHtml("./pages/SysAdmin/assignEmployee.html")
  
  

  const templateNotFound = await loadHtml("./pages/notFound/notFound.html");

  //If token existed, for example after a refresh, set UI accordingly
  const token = localStorage.getItem("token");
  //toggleLoginStatus(token);

  // @ts-ignore
  const router = new Navigo("/", { hash: true });
  //Not especially nice, BUT MEANT to simplify things. Make the router global so it can be accessed from all js-files
  // @ts-ignore
  window.router = router;

  router
    .hooks({
      before(done, match) {
        setActiveLink("menu", match.url);
        done();
        isLoggedIn()
      },
    })
    .on({
      //For very simple "templates", you can just insert your HTML directly like below
      "/": () =>
        (document.getElementById("content").innerHTML = `
        <h2>Home</h2>

     `),
      "/activities": () => {
        renderHtml(templateActivities, "content");
        initActivities();
      },
      "/equipment": () => {
        renderHtml(templateEquipment, "content");
        initEquipment();
      },
      "/reservations": () => {
        renderHtml(templateReservations, "content");
       initAllReservations();
             },
      "/signup": () => {
        renderHtml(templateAddUser, "content");
        initSignup();
      },
      "/login": () => {
        renderHtml(templateLogin, "content");
        initLogin();
      },
      "/makeReservation": ()=>{
        renderHtml(templateMakeReservation, "content");
        initMakeReservations();
      },
      "/myReservations": ()=>{
        renderHtml(templateMyReservations, "content");
        initListReservationsForUser();
      },
      "assignEmployee": () => {
       renderHtml(templateAssignEmployee, "content")
       initAssignEmployee();

      },
      "/logout": () => {
        logout();
        alert("You are now logged out")
      },
  
    })
    .notFound(() => {
      renderHtml(templateNotFound, "content");
    })
    .resolve();
});

window.onerror = function (errorMsg, url, lineNumber, column, errorObj) {
  alert(
    "Error: " +
      errorMsg +
      " Script: " +
      url +
      " Line: " +
      lineNumber +
      " Column: " +
      column +
      " StackTrace: " +
      errorObj
  );
};

function logout(){
  localStorage.removeItem("token")
  localStorage.removeItem("user")
  localStorage.removeItem("roles")
  toggleUiBasedOnRoles(false);
}

function isLoggedIn() {
  const token = localStorage.getItem("token");
  if (token == null) {
    toggleUiBasedOnRoles(false)
  }
  else {
    toggleUiBasedOnRoles(true);
  }
}
