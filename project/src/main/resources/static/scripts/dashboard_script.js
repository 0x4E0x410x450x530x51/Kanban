// Opens the sidebar on the Kanban Board site.
function openNav() { 
    document.getElementById("mySidenav").style.width = "250px"; 
    document.getElementById("main").style.marginLeft = "250px";
}
// Closes the sidebar on the Kanban Board site.
function closeNav() { 
    document.getElementById("mySidenav").style.width = "0"; 
    document.getElementById("main").style.marginLeft = "0";
}


// New Add
const BtnAdd = document.querySelector(".btn-add");
const DivContainer = document.getElementById("div-container");

function addNew() {
    var todo = document.getElementById("div-container");
    var taskName = document.getElementById("newDiv").value;
  
    todo.innerHTML += `
  
    <div class="task" id="${taskName.toLowerCase().split(" ").join("")}">
  
        <span>${taskName}</span>
  
    </div>`
}
function saveTask() {

    var todo = document.getElementById("todo");
  
    var taskName = document.getElementById("task-name").value;
  
  
  
    if (taskName == 0) alert('You have to name the story.') // checks if the story is named
  
    else {
  
      todo.innerHTML += `
  
    <div class="task" id="${taskName.toLowerCase().split(" ").join("")}" draggable="true" ondragstart="drag(event)">
  
        <span>${taskName}</span>
  
    </div>`
      createTask()
  }
}