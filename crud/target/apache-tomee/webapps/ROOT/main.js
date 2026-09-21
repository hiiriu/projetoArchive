

 $(function(){
    $( "#poster_section" ).load( "scrollPoster.html" );
   
  });


document.addEventListener('DOMContentLoaded', (event) =>{
  fetch('api')
    .then(response => response.text())
    .then(data => {
      document.getElementById('api').innerText = data;
    })
    .catch(error => console.log('Error:', error));
});

/*
async function loadUser(){
  try{
  const response = await fetch("/api/user");
  if(!response.ok){
    throw new Error("Failed to load user");
  }

  const user = await response.json();

  displayUsers(user);

} catch (error){
  console.error("Error loading users: ", error);
}
}

function displayUsers(user){
  const username = document.getElementsByClassName("username");

  

}*/