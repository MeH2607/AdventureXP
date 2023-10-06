//import "https://unpkg.com/navigo"  //Will create the global Navigo object used below
import "./navigo_EditedByLars.js"; //Will create the global Navigo, with a few changes, object used below
//import "./navigo.min.js"  //Will create the global Navigo object used below

import { setActiveLink, loadHtml, renderHtml } from "./utils.js";

import {initActivities}from "./pages/activities/activities.js";
import {initEquipment } from "./pages/equipment/showEquipment.js";


window.addEventListener("load", async () => {
  const templateActivities = await loadHtml("./pages/activities/activities.html");

  const templateNotFound = await loadHtml("./pages/notFound/notFound.html");

  //If token existed, for example after a refresh, set UI accordingly
  const token = localStorage.getItem("token");
  //toggleLoginStatus(token);

  const router = new Navigo("/", { hash: true });
  //Not especially nice, BUT MEANT to simplify things. Make the router global so it can be accessed from all js-files
  window.router = router;

  router
    .hooks({
      before(done, match) {
        setActiveLink("menu", match.url);
        done();
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
        renderHtml(templateActivities, "content");
        initEquipment();
      }
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
