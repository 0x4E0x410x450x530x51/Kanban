// Opens the sidebar on the Kanban Board site.
var navState = 0

function toggleNav() {
    if (navState == 0) {
        openNav()
        navState = 1
    } else {
        closeNav()
        navState = 0
    }
}
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
    document.getElementById("tlines").style.marginLeft = "250px";
}
// Closes the sidebar on the Kanban Board site.
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft = "0";
    document.getElementById("tlines").style.marginLeft = "";
}


var index = 0;


function createTask() {

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
}



/* Saves the story in the Backlog */
function saveTask() {
  var todo = document.getElementById("backlog"); //changed string/id value to backlog, as it's more sensibel, but can't be bothered to change the variable name
  var taskNameQ = taskName 
  var description = taskDesc
  var color = taskColor
  let dueDate = taskDate
  let priority = taskStat
  //let definitionOfDone = document.getElementById("definitionOfDoneForm").value

  let id = taskNameQ.toLowerCase().split(" ").join("") + index // generate the taskID

  // format the dates
  //let dueDateFormated = dueDate.replace(/T/g, " ")


  todo.innerHTML += `
  <div class="task" id="${id}" draggable="true" ondragstart="drag(event)"">
      <span  class="storyTitle" id="${id + "-name"}">${taskNameQ}</span>
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