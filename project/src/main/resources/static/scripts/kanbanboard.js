function openNav() {

  document.getElementById("mySidenav").style.width = "250px";
  document.getElementById("main").style.marginLeft = "250px";
}


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

  if (width == 250)  closeNav()
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

var tasks = []; 
var index = 0;


function createTask() {


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




/* Saves the story in the Backlog */
function saveTask() {
  var todo = document.getElementById("backlog"); //changes string/id value to backlog, as it's more sensibel, but can't bother to change the variable name
  var taskName = document.getElementById("storyNameForm").value
  var description = document.getElementById("descritptionForm").value
  var color = document.getElementById("storyColorForm").value
  let dueDate = document.getElementById("storyDueDate").value
  let priority = document.getElementById("storyPriorityForm").value
  let definitionOfDone = document.getElementById("definitionOfDoneForm").value

  let id = taskName.toLowerCase().split(" ").join("") + index // generate the taskID

  // format the dates
  let dueDateFormated = dueDate.replace(/T/g, " ")


  if (id == 0) {
    alert('You have to name the story.') 
    return false;
  }


  todo.innerHTML += `
  <div class="task" id="${id}" draggable="true" ondragstart="drag(event)"">
      <span  class="storyTitle" id="${id + "-name"}">${taskName}</span>
      <div class="prio" id="${id + "-prio"}">${priority}</div>
      <div class="edit" onclick="editTask('${id}')"></div>
      <small class="description" id="${id + "-description"}" >${description}</small>
      <div style="height: 5px;background-color: #ccc;max-width: 90%;">
      <div id="${id}-progress" style="height: 5px;background-color: green;max-width: 100%;width: 0%"></div>
      </div>
      `+/*[HERE]*/+`
  </div>
  `
  // set the color
  var taskPrio = document.getElementById(id + "-prio");
  if (taskPrio.innerText == "!") taskPrio.style.color = "#e60000"
  else if (taskPrio.innerText == "•") taskPrio.style.color = "#8e7cc3"
  else if (taskPrio.innerText == "↓") taskPrio.style.color = "#6495ed"
  var task = document.getElementById(id)
  task.style.borderLeft = "solid " + color + " 0.5em"

  tasks.push(id) // save name of the task in the array
  index++;
}

//heres the rest of the generated task, incase we ever DO need it || put it to [HERE]

/*
<span class="storyInformation" id="${id + '-storyExtensionButton'}" data-hover="Show more" onclick="showMore('${id}')">»</span>
<span class="storyInformation closeButton" id="${id + '-storyCloseButton'}" data-hover="Close" onclick="showLess('${id}')">&#171;</span>
<div id="${id + "-showMoreBlock"}" class="showMoreBlock">
    <div class="dueDateTitle">Due date:</div>
    <small class="dueDate" id="${id + "-dueDate"}">${dueDateFormated}</small>
    <div class="definitionOfDonetitle">Definition of done:</div>
    <small class="definitionOfDone" id="${id + "-defOfDo"}">${definitionOfDone}</small>
</div>
*/



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