
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
  for (let i = 0; i < 100; i++) {
    setTimeout(function () {
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
// This code manages the tasks
// delete, update, save

var tasks = []; // stores all task names

/* Creates a new Story */
function createTask() {
  clearInputs()
  var z = document.getElementById("create-new-task-block");
  z.style.display = "block";

  // show save-button 
  var z = document.getElementById("save-button");
  z.style.display = "block";

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
  clearInputs()
}

/* edit the story */
function editTask(id) {
  clearInputs()
  createTask() // open the create-new-task-block

  let taskNameID = id + '-name'
  let descriptionID = id + '-description'

  let taskName = document.getElementById(taskNameID).innerText
  let description = document.getElementById(descriptionID).innerText

  // put the name into the input fields
  document.getElementById('storyNameForm').value = taskName
  document.getElementById('descritptionForm').value = description

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
    updateTask(id)
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
  let taskNameID = id + '-name'
  let descriptionID = id + '-description'

  // get the old name
  let oldTaskName = document.getElementById(taskNameID).innerText

  // get the new values
  var newTaskName = document.getElementById("storyNameForm").value
  var newDescription = document.getElementById("descritptionForm").value;

  if (validateTaskname(newTaskName) != false) {
    for (let i = 0; i < tasks.length; i++) {
      if (tasks[i] == oldTaskName) {
        tasks[i] = ''
        return
      }
    }
    tasks.push(newTaskName) // save name of the task in the array

    if (id == 0) {
      alert('You have to name the story.') // checks if the story is named
    }

    // Update values
    let storyTitle = document.getElementById(taskNameID)
    storyTitle.innerHTML = newTaskName

    let description = document.getElementById(descriptionID)
    description.innerHTML = newDescription

  }


}

/* deletes the story  */
function deleteTask(id) {
  var el = document.getElementById(id)
  el.parentNode.removeChild(el) // delete task
  cancelCreateTask() // close the create-new-task-block
}



/* Saves the story in the Backlog */
function saveTask() {
  var todo = document.getElementById("todo");
  var taskName = document.getElementById("storyNameForm").value
  var description = document.getElementById("descritptionForm").value;

  let id = taskName.toLowerCase().split(" ").join("") // generate the taskID

  // validation
  if (validateTaskname(id) == false) return
  if (id == 0) {
    alert('You have to name the story.') // checks if the story is named
    return false;
  }

  todo.innerHTML += `
  <div class="task" id="${id}" draggable="true" ondragstart="drag(event)"">
      <span  class="storyTitle" id="${id + "-name"}">${taskName}</span>
      <div class="edit" onclick="editTask('${id}')"></div>
      <small id="${id + "-description"}" >${description}</small>
  </div>
  `
  let z = document.getElementById(taskName.toLowerCase().split(" ").join(""))
  z.style.opacity = 0
  for (let i = 0; i < 100; i++) {
    setTimeout(function () {
      z.style.opacity = i / 100
    }, i * 5)
  }

  cancelCreateTask() // close the create-new-task-block
  tasks.push(id) // save name of the task in the array
}

// clears the input fields 
function clearInputs() {
  document.getElementById('storyNameForm').value = ""
  document.getElementById('descritptionForm').value = ""
}

// checks if taskname is valid 
function validateTaskname(name) {
  for (let i = 0; i < tasks.length; i++) {
    if (tasks[i] == name) {
      alert("Please enter a different name.")
      return false;
    }
  }
  return true
}

// ---------------------------------------------------------------------------------------