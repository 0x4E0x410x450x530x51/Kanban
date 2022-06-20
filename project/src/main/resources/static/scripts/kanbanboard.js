
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

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
  // makes a shadow around the board when a story gets draged
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

  var data = ev.dataTransfer.getData("text");
  ev.target.appendChild(document.getElementById(data));
  ev.currentTarget.appendChild(document.getElementById(data)); // prevents the storys from droping in an other story
  document.getElementById(data).style.opacity = 0;
  for (let i = 0; i < 100; i++) {
    setTimeout(function () {
      document.getElementById(data).style.opacity = i / 100
    }, i * 2)
  }
  var elements = document.getElementsByClassName("story-table")
  for (i = 0; i < elements.length; i++) {
    elements[i].classList.remove("story-border")
  }
}

// ---------------------------------------------------------------------------------------
// This code manages the tasks
// delete, update, save

var tasks = []; // stores all task names
var index = 0;

/* Creates a new Story */
function createTask() {
  clearInputs()

  for (let i = 0; i < 2; i++) {
    setTimeout(function () {
      let background = document.getElementsByClassName("container")[0]
      background.style.filter = "blur(" + i + "px)" // blurs background
    }, i * 2)
  }

  // makes that the user can't select anything in the background
  let allConts = document.getElementsByClassName("task")
  for (let i = 0; i < allConts.length; i++) {
    allConts[i].style.webkitUserDrag = "none"
  }

  document.getElementsByClassName("container")[0].style.userSelect = "none"

  var createNewTaskBlock = document.getElementById("create-new-task-block");
  createNewTaskBlock.style.display = "block"; // shows the create-new-task-block"

  // set color
  createNewTaskBlock.style.borderLeft = "solid #E60000 0.5em"

  // shows save-button 
  var saveButton = document.getElementById("save-button");
  saveButton.style.display = "block";

  // hide update and delete button
  var updateButton = document.getElementById("update-button")
  updateButton.style.display = "none"
  var deleteButton = document.getElementById("delete-button")
  deleteButton.style.display = "none"
}

/* cloeses the  create-new-task-block */
function cancelCreateTask() {
  // hide the create-new-task-block
  var z = document.getElementById("create-new-task-block");
  z.style.display = "none";
  for (let i = 0; i < 2; i++) {
    setTimeout(function () {
      document.getElementsByClassName("container")[0].style.filter = "blur(" + -(i) + "px)"
    }, i * 2)
  }
  let allConts = document.getElementsByClassName("task")
  for (let i = 0; i < allConts.length; i++) {
    allConts[i].style.webkitUserDrag = "element"
  }

  document.getElementsByClassName("container")[0].style.userSelect = "auto";

  clearInputs()
}

/* edit the story */
function editTask(id) {
  createTask() // open the create-new-task-block

  let taskNameID = id + '-name'
  let descriptionID = id + '-description'

  let taskName = document.getElementById(taskNameID).innerText
  let description = document.getElementById(descriptionID).innerText

  // get color of the story
  let currentColorOfStory = document.getElementById(id).style.borderLeftColor
  
  // set the color
  var createNewTaskBlock = document.getElementById("create-new-task-block")
  createNewTaskBlock.style.borderLeft = "solid " + currentColorOfStory + " 0.5em"

  // put the values into the input fields
  document.getElementById('storyNameForm').value = taskName // name
  document.getElementById('descritptionForm').value = description // description
  document.getElementById("storyColorForm").value = currentColorOfStory // color

  // convert rgb to hex:
  let a = currentColorOfStory.split("(")[1].split(")")[0];
  a = a.split(",");
  var b = a.map(function (x) {            
    x = parseInt(x).toString(16);      
    return (x.length == 1) ? "0" + x : x;  
  })
  currentColorOfStoryHex = "#" + b.join("")

  // set color option to current color of the story
  document.getElementById('storyColorForm').value = currentColorOfStoryHex

  // show the delete and update button 
  var updateButton = document.getElementById("delete-button")
  var deleteButton = document.getElementById("update-button")
  updateButton.style.display = "block"
  deleteButton.style.display = "block"

  // hide the save button
  var saveButton = document.getElementById("save-button")
  saveButton.style.display = "none"

  // ckeck if the delete button got clicked 
  document.getElementById('delete-button').onclick = function () {
    deleteTask(id)
  };

  // ckeck if the save button got clicked 
  document.getElementById('update-button').onclick = function () {
    if (updateTask(id) == false) return
    cancelCreateTask()
  };

}

/* Update the task with the new values */
/*
Version 1
function updateTask(id) {
  saveTask()

  for (let i = 0; i < tasks.length; i++) {
    if (tasks[i] == id) {
      tasks[i] = ''
      return
    }
  }

}
*/
// update version 2
function updateTask(id) {

  var taskNameID = id + '-name'
  var descriptionID = id + '-description'

  // get the new values
  var newTaskName = document.getElementById("storyNameForm").value
  var newDescription = document.getElementById("descritptionForm").value
  var newColor = document.getElementById("storyColorForm").value


  console.log(newDescription)

  // validation
  if (newTaskName == 0) {
    alert('You have to name the story.') // checks if the story is named
    return false
  }

  deleteTaskInArray(id)
  tasks.push(newTaskName) // save name of the task in the array

  // Update values
  let storyTitle = document.getElementById(taskNameID)
  storyTitle.innerHTML = newTaskName

  document.getElementById(descriptionID).value = newDescription

  let task = document.getElementById(id)
  task.style.borderLeft = "solid " + newColor + " 0.5em"

  
}

/* deletes the story  */
function deleteTask(id) {
  var el = document.getElementById(id)
  el.parentNode.removeChild(el) // delete task

  deleteTaskInArray(id)
  cancelCreateTask() // close the create-new-task-block
}

/* Saves the story in the Backlog */
function saveTask() {
  var todo = document.getElementById("todo");
  var taskName = document.getElementById("storyNameForm").value
  var description = document.getElementById("descritptionForm").value
  var color = document.getElementById("storyColorForm").value

  let id = taskName.toLowerCase().split(" ").join("") + index // generate the taskID

  // validation
  if (id == 0) {
    alert('You have to name the story.') // checks if the story is named
    return false;
  }

  todo.innerHTML += `
  <div class="task" id="${id}" draggable="true" ondragstart="drag(event)"">
      <span  class="storyTitle" id="${id + "-name"}">${taskName}</span>
      <div class="edit" onclick="editTask('${id}')"></div>
      <small id="${id + "-description"}" >${description}</small>
      <hr width="80%">
  </div>
  `

  var task = document.getElementById(id)
  task.style.borderLeft = "solid " + color + " 0.5em"

  cancelCreateTask() // close the create-new-task-block
  tasks.push(id) // save name of the task in the array
  index++;
}

// clears the input fields 
function clearInputs() {
  document.getElementById('storyNameForm').value = ""
  document.getElementById('descritptionForm').value = ""
  document.getElementById('storyColorForm').value = "#e60000"
}

function deleteTaskInArray(taskName) {
  for (let i = 0; i < tasks.length; i++) {
    if (tasks[i] == taskName) {
      tasks[i] = ''
      return
    }
  }
}

/*
class Story {
  constructor (name, description, id) {
    this.name = name;
    this.description = description
    this.id = id
  }
}
*/
// ---------------------------------------------------------------------------------------