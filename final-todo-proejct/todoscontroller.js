"use strict";

const getFirstName = document.querySelector("#firstname");
const getLastName = document.querySelector("#lastname");
const saveButton = document.querySelector("#savebutton");
const fetchButton = document.querySelector("#fetch");
const postTemplate = document.getElementById("single-post");
const listElement = document.querySelector(".posts");
const postList = document.querySelector("ul");

function sendHttpRequest(method, url, data) {
  return fetch(url, {
    method: method,
    body: JSON.stringify(data),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (response.status >= 200 && response.status < 300) {
        return response.json();
      } else {
        return response.json().then((errData) => {
          console.log(errData);
          throw new Error("Something went wrong - server-side");
        });
      }
    })
    .catch((error) => {
      console.log(error);
      throw new Error("Something went wrong");
    });
}

async function fetchPosts() {
  try {
    const responseData = await sendHttpRequest(
      "GET",
      "http://localhost:8080/user/"
    );
    const listOfUsers = responseData;
    console.log(listOfUsers);
    for (const us of listOfUsers) {
      const postEl = document.importNode(postTemplate.content, true);
      postEl.querySelector("h2").textContent = us.firstName;
      postEl.querySelector("h3").textContent = us.lastName;
      postEl.querySelector("li").id = us.id;
      listElement.append(postEl);
    }
  } catch (error) {
    alert(error.message);
  }
}

async function createPost(firstName, lastName) {
  const post = {
    firstName: firstName,
    lastName: lastName,
  };

  sendHttpRequest("POST", `http://localhost:8080/user/`, post);
}
fetchButton.addEventListener("click", fetchPosts);

saveButton.addEventListener("click", (event) => {
  event.preventDefault();
  const eneteredFirstName = getFirstName.value;
  const enteredLastName = getLastName.value;

  createPost(eneteredFirstName, enteredLastName);
});

postList.addEventListener("click", (event) => {
  if (event.target.tagName === "BUTTON") {
    const userId = event.target.closest("li").id;
    sendHttpRequest("DELETE", `http://localhost:8080/user/${userId}`);
  }
});