
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


function manageSidebar() {
  openNav()

  var element = document.querySelector('.sidenav');

  // width and height in pixels
  // including padding + border

  // jQuery outerWidth()
  var width = element.offsetWidth;

  if (width == 250) closeNav()
}

// ---------------------------------------------------------------------------------------

/* Drag and drop storys in the Kanban Board */

// First drag and drop version

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
  // console.log(1)
  var elements = document.getElementsByClassName("story-table")
  for (i = 0; i < elements.length; i++) {
    elements[i].classList.add("story-border")
  }
}

function allowDrop(ev) {
  ev.preventDefault();
}

function drop(ev) {
  ev.preventDefault();
  console.log(1)

  var data = ev.dataTransfer.getData("text");
  document.getElementById(data).style.opacity = 0;
  ev.target.appendChild(document.getElementById(data));
  ev.currentTarget.appendChild(document.getElementById(data)); // prevents the storys from droping in an other story
  for (let i = 0;i < 100; i++) {
    setTimeout(function() {
      document.getElementById(data).style.opacity = i / 100
    }, i * 5)
  }
  var elements = document.getElementsByClassName("story-table")
  for (i = 0; i < elements.length; i++) {
    elements[i].classList.remove("story-border")
  }
}



// Drag and drop version 2
// working on it

// ---------------------------------------------------------------------------------------

var tasks = [];

/* Creates a new Story */
function createTask() {
  var z = document.getElementById("create-new-task-block");
  z.style.display = "block";

}

function cancelCreateTask() {
  var z = document.getElementById("create-new-task-block");
  z.style.display = "none";
}

/* edit the story */
function editTask(id) {
  createTask()
  let taskName = id
  alert(taskName)
  
  // put the name into the input field
  document.getElementById('storyNameForm').value = fileName

  // show the delete button 
  var deleteButton = document.getElementById("delete-button")
  deleteButton.style.display = "block"

}

/* deletes the story  */
function deleteTask(id) {
  var el = document.getElementById(id);
  el.parentNode.removeChild( el );
}

/* Saves the story in the Backlog */
function saveTask() {
  var todo = document.getElementById("todo");
  var taskName = document.getElementById("storyNameForm").value
  var description = document.getElementById("descritptionForm").value;

  let id = taskName.toLowerCase().split(" ").join("")

  // check if the task already exists
  for (let i = 0; i < tasks.length; i++)
  {
    if (tasks[i] == id) 
    {
      alert("Please enter a different name.")
      return 
    }
  }

  if (taskName == 0) alert('You have to name the story.') // checks if the story is named
  else {
    todo.innerHTML += `
  <div class="task" id="${id}" draggable="true" ondragstart="drag(event)"">
      <span  class="storyTitle" id="${id + "-name"}">${taskName}</span>
      <div class="edit" onclick="editTask('${id}')"></div>
      <small id="${id + "-description"}" >${description}</small>
  </div>
  `
  let z = document.getElementById(taskName.toLowerCase().split(" ").join(""))
  z.style.opacity = 0
  for (let i = 0;i < 100; i++) {
    setTimeout(function() {
      z.style.opacity = i / 100
    }, i * 5)
  }

    cancelCreateTask()
    // save name of the task in the array
    tasks.push(id)
  }

}
/*
function editTask(){
  var saveButton = document.getElementById("save-button");
  var editButton = document.getElementById("edit-button");
  if (saveButton.style.display === "none") {
      saveButton.style.display = "block";
      editButton.style.display = "none";
  } else{
      saveButton.style.display = "none";
      editButton.style.display = "block";
  }
}*/

// ---------------------------------------------------------------------------------------