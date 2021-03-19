"use strict";

const getTitle = document.querySelector("#title");
const getmemo = document.querySelector("#memo");
const getimp = document.querySelector("#imp");
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
      "http://localhost:8080/todo/"
    );
    const listOfPosts = responseData;
    console.log(listOfPosts);
    for (const post of listOfPosts) {
      const postEl = document.importNode(postTemplate.content, true);
      postEl.querySelector("h2").textContent = post.title.toUpperCase();
      postEl.querySelector("p").textContent = post.memo;
      postEl.querySelector("li").id = post.id;
      listElement.append(postEl);
    }
  } catch (error) {
    alert(error.message);
  }
}

async function createPost(title, memo) {
  const post = {
    title: title,
    memo: memo,
  };

  sendHttpRequest("POST", `http://localhost:8080/todo/`, post);
}
fetchButton.addEventListener("click", fetchPosts);

saveButton.addEventListener("click", (event) => {
  event.preventDefault();
  const eneteredTitle = getTitle.value;
  const enteredMemo = getmemo.value;

  createPost(eneteredTitle, enteredMemo);
});

postList.addEventListener("click", (event) => {
  if (event.target.tagName === "BUTTON") {
    const todoId = event.target.closest("li").id;
    sendHttpRequest("DELETE", `http://localhost:8080/todo/${todoId}`);
  }
});

// post todos but fix the important button
// let isimportant = false;
// saveButton.addEventListener("click", function (event) {
//   event.preventDefault();
//   alert("Do Stuff");
//   fetch(`http://localhost:8080/todo/`, {
//     //1
//     method: "post", //2
//     headers: {
//       "Content-type": "application/json", //3
//     },
//     body: JSON.stringify(
//       //4
//       {
//         title: getTitle.value,
//         memo: getmemo.value,
//         important: getimp.value,
//         isimportant: true,
//       }
//     ),
//   })
//     .then((res) => res.json())
//     .then((data) => console.log(`Request succeeded with JSON response ${data}`))
//     .catch((error) => console.log(`Request failed ${error}`));
// });

// Get request
// fetchButton.addEventListener("click", function (event) {
//   event.preventDefault();
//   fetch(`http://localhost:8080/todo`) // 1
//     .then((response) => {
//       if (response.status !== 200) {
//         //  2
//         console.error(`status: ${reponse.status}`);
//         return;
//       }
//       response
//         .json() // 3
//         .then((data) => console.info(data)); // 4
//     })
//     .catch((err) => console.error(`${err}`)); // 5
// });
