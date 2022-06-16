
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


/* Drag and drop storys in the Kanban Board */
function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
  console.log(1)
  var elements = document.getElementsByClassName("story-table")
  for(i = 0; i < elements.length; i++){
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
  ev.target.appendChild(document.getElementById(data));
  ev.currentTarget.appendChild(document.getElementById(data)); // prevents the storys from droping in an other story
  var elements = document.getElementsByClassName("story-table")
  for(i = 0; i < elements.length; i++){
    elements[i].classList.remove("story-border")
  }
}

/* Creates a new Story */
function createTask() {
  var x = document.getElementById("inprogress");
  var y = document.getElementById("done");
  var z = document.getElementById("create-new-task-block");

  if (x.style.display === "none") {
    x.style.display = "block";
    y.style.display = "block";
    z.style.display = "none";
  } else {
    x.style.display = "none";
    y.style.display = "none";
    z.style.display = "flex";
  }
}

/* Saves the story in the Backlog */
function saveTask() {
  var todo = document.getElementById("todo");
  var taskName = document.getElementById("task-name").value;

  if (taskName == 0) alert('You have to name the story.') // checks if the story is named
  else {
    todo.innerHTML += `
  <div class="task" id="${taskName.toLowerCase().split(" ").join("")}" draggable="true" ondragstart="drag(event)">
      <span>${taskName}</span>
  </div>
  `

    createTask()
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