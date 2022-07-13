
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
  ev.preventDefault()
}

function drop(ev) {
  ev.preventDefault()

  var data = ev.dataTransfer.getData("text")
  //ev.target.appendChild(document.getElementById(data))
  ev.currentTarget.appendChild(document.getElementById(data)) // prevents the storys from droping in an other story
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

  document.getElementsByClassName("md-backdrop")[0].classList.add("backdrop-state")

  // makes that the user can't select anything in the background
  let allConts = document.getElementsByClassName("task")
  for (let i = 0; i < allConts.length; i++) {
    allConts[i].style.webkitUserDrag = "none"
  }

  document.getElementsByClassName("container")[0].style.userSelect = "none"

  var createNewTaskBlock = document.getElementById("create-new-task-block");
  createNewTaskBlock.style.display = "block"; // shows the create-new-task-block"

  // set color of create-new-task-block
  document.querySelector('#storyColorForm').addEventListener("change", function() {
    createNewTaskBlock.style.borderLeft = "solid " + this.value + " 0.5em"
  });

  // set priority an prioritycolor of of create-new-task-block
  document.querySelector('#storyPriorityForm').addEventListener("change", function() {  
    let place = document.getElementById('storyPriority')
    selected = this.value
    place.innerHTML = this.value

    if (selected == "!") place.style.color = "#e60000"
    else if (selected == "•") place.style.color = "#8e7cc3"
    else if (selected == "↓") place.style.color = "#6495ed"
      
  });
  
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
  document.getElementsByClassName("md-backdrop")[0].classList.remove("backdrop-state")
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
  let dueDateID = id + '-dueDate'
  let prioID = id + '-prio'
  let defOfDoID = id + '-defOfDo'

  // get old values from the story
  let taskName = document.getElementById(taskNameID).innerText
  let description = document.getElementById(descriptionID).innerText
  let dueDate = document.getElementById(dueDateID).innerText
  let priority = document.getElementById(prioID).innerText
  let defOfDo = document.getElementById(defOfDoID).innerText

  // back to the original format
  let dueDateFormated = dueDate.replace(/ /g, "T")

  // get color of the story
  let currentColorOfStory = document.getElementById(id).style.borderLeftColor

  // set the color
  var createNewTaskBlock = document.getElementById("create-new-task-block")
  createNewTaskBlock.style.borderLeft = "solid " + currentColorOfStory + " 0.5em"

  // put the values into the input fields
  // priority
  document.getElementById('storyPriorityForm').value = priority 
  document.getElementById('storyPriority').innerHTML = priority
  document.getElementById('storyNameForm').value = taskName // name
  document.getElementById('descritptionForm').value = description // description
  document.getElementById('storyDueDate').value = dueDateFormated // due date
  document.getElementById('definitionOfDoneForm').value = defOfDo // definition of done


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

  // ckeck if the update button got clicked 
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
  let taskNameID = id + '-name'
  let descriptionID = id + '-description'
  let dueDateID = id + '-dueDate'
  let priorityID = id + '-prio'
  let defOfDoID = id + '-defOfDo'

  // get the new values
  let newTaskName = document.getElementById("storyNameForm").value
  let newDescription = document.getElementById("descritptionForm").value
  let newColor = document.getElementById("storyColorForm").value
  let newDueDate = document.getElementById("storyDueDate").value
  let priority = document.getElementById("storyPriorityForm").value
  let definitionOfDone = document.getElementById("definitionOfDoneForm").value

  let newDueDateFormated = newDueDate.replace(/T/g, " ");

  // validation
  if (newTaskName == 0) {
    alert('You have to name the story.') // checks if the story is named
    return false
  }

  deleteTaskInArray(id)
  tasks.push(newTaskName) // save name of the task in the array

  // Update values
  document.getElementById(taskNameID).innerHTML = newTaskName
  document.getElementById(descriptionID).innerHTML = newDescription
  document.getElementById(dueDateID).innerHTML = newDueDateFormated
  document.getElementById(id).style.borderLeft = "solid " + newColor + " 0.5em"
  document.getElementById(priorityID).innerHTML = priority
  document.getElementById(defOfDoID).innerHTML = definitionOfDone

  var taskPrio = document.getElementById(id + "-prio");
  if (taskPrio.innerText == "!") taskPrio.style.color = "#e60000"
  else if (taskPrio.innerText == "•") taskPrio.style.color = "#8e7cc3"
  else if (taskPrio.innerText == "↓") taskPrio.style.color = "#6495ed"
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
  let dueDate = document.getElementById("storyDueDate").value
  let priority = document.getElementById("storyPriorityForm").value
  let definitionOfDone = document.getElementById("definitionOfDoneForm").value

  let id = taskName.toLowerCase().split(" ").join("") + index // generate the taskID

  // format the dates
  let dueDateFormated = dueDate.replace(/T/g, " ")

  // validation
  if (id == 0) {
    alert('You have to name the story.') // checks if the story is named
    return false;
  }

  // genereate div
  todo.innerHTML += `
  <div class="task" id="${id}" draggable="true" ondragstart="drag(event)"">
      <span  class="storyTitle" id="${id + "-name"}">${taskName}</span>
      <div class="prio" id="${id + "-prio"}">${priority}</div>
      <div class="edit" onclick="editTask('${id}')"></div>
      <small class="description" id="${id + "-description"}" >${description}</small>
      <div style="height: 5px;background-color: #ccc;max-width: 90%;">
      <div id="${id}-progress" style="height: 5px;background-color: green;max-width: 100%;width: 0%"></div>
      </div>
      <span class="storyInformation" id="${id + '-storyExtensionButton'}" data-hover="Show more" onclick="showMore('${id}')">»</span>
      <span class="storyInformation closeButton" id="${id + '-storyCloseButton'}" data-hover="Close" onclick="showLess('${id}')">&#171;</span>
      <div id="${id + "-showMoreBlock"}" class="showMoreBlock">
          <div class="dueDateTitle">Due date:</div>
          <small class="dueDate" id="${id + "-dueDate"}">${dueDateFormated}</small>
          <div class="definitionOfDonetitle">Definition of done:</div>
          <small class="definitionOfDone" id="${id + "-defOfDo"}">${definitionOfDone}</small>
      </div>
  </div>
  `
  // set the color
  var taskPrio = document.getElementById(id + "-prio");
  if (taskPrio.innerText == "!") taskPrio.style.color = "#e60000"
  else if (taskPrio.innerText == "•") taskPrio.style.color = "#8e7cc3"
  else if (taskPrio.innerText == "↓") taskPrio.style.color = "#6495ed"
  var task = document.getElementById(id)
  task.style.borderLeft = "solid " + color + " 0.5em"

  cancelCreateTask() // close the create-new-task-block
  tasks.push(id) // save name of the task in the array
  index++;
}

// clears the input fields 
function clearInputs() {
  document.getElementById('storyNameForm').value = ""
  document.getElementById('definitionOfDoneForm').value = ""
  document.getElementById('descritptionForm').value = ""
  document.getElementById('storyColorForm').value = "#e60000"
  document.getElementById('storyDueDate').value = ""
  document.getElementById('storyPriorityForm').value = "&#8226;"
  document.getElementById('storyPriorityForm').selectedIndex = 1
  document.getElementById('storyPriority').innerHTML = "&#8226;"
  document.getElementById('storyPriority').style.color = "#8E7CC3"
  document.getElementById('create-new-task-block').style.borderLeft = "solid #e60000 0.5em"
}

function deleteTaskInArray(taskName) {
  for (let i = 0; i < tasks.length; i++) {
    if (tasks[i] == taskName) {
      tasks[i] = ''
      return
    }
  }
}


/* Show more details from a story */
function showMore(id) {
  let blockID = id + '-showMoreBlock'
  let showMoreBlock = document.getElementById(blockID)

  let storyExtensionButtonID = id + '-storyExtensionButton'
  let storyExtensionButton = document.getElementById(storyExtensionButtonID)

  let storyCloseButtonID = id + '-storyCloseButton'
  let storyCloseButton = document.getElementById(storyCloseButtonID)
  

  // show close button and the showMoreBlock and hide the showMoreButton
  showMoreBlock.style.display = "block"
  storyExtensionButton.style.display = "none"
  storyCloseButton.style.display = "block"

}

function showLess(id) {
  let blockID = id + '-showMoreBlock'
  let showMoreBlock = document.getElementById(blockID)

  let storyExtensionButtonID = id + '-storyExtensionButton'
  let storyExtensionButton = document.getElementById(storyExtensionButtonID)

  let storyCloseButtonID = id + '-storyCloseButton'
  let storyCloseButton = document.getElementById(storyCloseButtonID)

  // hide the showMoreBlock and close button and show the showMoreButton
  showMoreBlock.style.display = "none"
  storyExtensionButton.style.display = "block"
  storyCloseButton.style.display = "none"
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