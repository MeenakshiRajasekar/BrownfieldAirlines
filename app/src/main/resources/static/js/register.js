const form = document.querySelector(".reg_form");
const form_btn = document.querySelector(".reg_btn");
const handleSubmit = async (e) => {
  e.preventDefault();
  form_btn.classList.add("disabled");
  const formData = new FormData(e.target);
  const password1 = formData.get("password");
  const password2 = formData.get("confirm-password");

  if (password1 !== password2) {
    alert("Passwords don't match");
    return false;
  }

  const jsonData = JSON.stringify(Object.fromEntries(formData));
  console.log(jsonData);
  const settings = {
    method: "POST",
    body: jsonData,
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json",
    },
  };
  try {
    const fetchResponse = await fetch(
      "http://localhost:8081/api/v1/user/register",
      settings
    );
    const data = await fetchResponse.json();
    if (fetchResponse.status === 201) {
      if (data) {
        window.location.href = "/login";
      }
    } else {
      throw new Error("Something went wrong. Please try again!");
    }
  } catch (e) {
          form_btn.classList.remove("disabled");
          window.location.href = "/register?error";
          alert(e.message);
  }
};

form.addEventListener("submit", handleSubmit);
