
//fetch 
document.addEventListener('DOMContentLoaded', (event) => {
  fetch('api')
    .then(response => response.text())
    .then(data => {
      document.getElementById('api').innerText = data;
    })
    .catch(error => console.log('Error:', error));
});


//carregar users
async function loadUser() {
  try {
    const res = await fetch("/api/user");
    if (!response.ok) {
      throw new Error("Failed to load user");
    }

    const user = await res.json();

    displayUsers(user);

  } catch (error) {
    console.error("Error loading users: ", error);
  }
}

function displayUsers(user) {
  const username = document.getElementsByID("username");
}

document.getElementById(form_register).addEventListener("submit", async (e) => {
  e.preventDefault();
  const form = e.target;
  const id = form.id.value;
  const params = new URLSearchParams({
    username: form.usename.value,
    name: form.name.value,
    email: form.email.value,
    password: form.password.value,
  });

  const url = id ? `api/user/${id}?${params}` : `api/user?${params}`;
  const res = await fetch(url, { method: id ? "PUT" : "POST" });
  if (res.status === 201 || res.status === 200) {
    showMessage("user", id ? "Updated!" : "Created!", true);
    loadUser();
  } else {
    showMessage("users", `Erro (${res.status}).`, false);
  }
});

/*fetch('api/qqcoisa', {{method: 'POST', body: JSON.stringify(data) });*/