
const settingsBlock = document.getElementById('settings')
const backdrop = document.getElementById('backdrop')

// tabs
const backlogTab = document.getElementById('backlog-tab')
const teamTab = document.getElementById('team-tab')
const rulesTab = document.getElementById('rules-tab')
const boardTab = document.getElementById('board-tab')

// content
const backlog = document.getElementById('backlog')
const team = document.getElementById('team')
const rule = document.getElementById('rules')
const board = document.getElementById('rules')

function openSettings() {
  backdrop.className = "on" // make background dark
  settingsBlock.style.display = "block" // show settings

  //starts dialog window with backlog as default setting
  backlogTab.className += " active"
  backlog.style.display = "block"
}

function closeSettings() {
  backdrop.className = "off"

  // hide tab content
  team.style.display = "none"
  rule.style.display = "none"
  board.style.display = "none"
  backlog.style.display = "none"

  // set loading elements back to default
  settingsBlock.style.display = "none"
  link.innerHTML = " "

  generateLinkButton.style.display = "block"
}

// Tab management ---------------------------------------------

function openTabContent(evt, contentName) {
  var i, tabcontent, tablinks;
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }
  document.getElementById(contentName).style.display = "block";
  evt.currentTarget.className += " active";
}

// link generation -----------------------------------------

const loader = document.getElementById('loader')
const link = document.getElementById('link')
const generateLinkButton = document.getElementById('dialog-button')

function generateLink() {
  loader.style.display = "flex"

  // loading screen
  delay(3000).then(() => loader.style.display = "none")
  //delay(3001).then(() => link.innerHTML += 'testlink')
  generateLinkButton.style.display = "none"


  //EDIT FOR JSON FILE----------------------------------------------------------
  var data = {
    fleetId:"123",
    text:"Hallo"
  };

  /*$.post("https://localhost:8443/newBoardLink",
      data,
      function(data, status){
        alert("Data: " + data + "\nStatus: " + status);
      });*/
  console.log("AJAX")
  console.log(data)
  delay(3010).then(() => $.ajax({

    url:"https://localhost:8443/newBoardLink",
    method:"POST",
    contentType:"application/json",
    data: JSON.stringify(data),
    success:function(data) {
      console.log(data)
      link.innerHTML += data;
      link.href=data;
    },
    error:function(data) {
      console.log(data)
      alert("Etwas hat nicht geklappt!");
    }
  }));



}

// Time manager
function delay(time) {
  return new Promise(resolve => setTimeout(resolve, time));
}


// settings ----------------------------------------------

// task creation strategy
// get settings 
let specialSettingsConstant = document.getElementById('special-settings-constant')
let specialSettingsRandom = document.getElementById('special-settings-random')
let specialSettingsScrum = document.getElementById('special-settings-scrum')

document.querySelector('#task-creation-strategy').addEventListener("change", function() {
  
  let value = this.value

  if (value == 'limit') {
    specialSettingsConstant.style.display = "none"
    specialSettingsRandom.style.display = "none"
    specialSettingsScrum.style.display = "none"

  } else if (value == 'constant') {
    specialSettingsConstant.style.display = "block"
    specialSettingsRandom.style.display = "none"
    specialSettingsScrum.style.display = "none"
  } else if (value == 'scrum') {
    specialSettingsConstant.style.display = "none"
    specialSettingsRandom.style.display = "none"
    specialSettingsScrum.style.display = "block"
  } else if (value == 'random') {
    specialSettingsConstant.style.display = "none"
    specialSettingsRandom.style.display = "block"
    specialSettingsScrum.style.display = "none"
  }
});

// task size strategy 

// board 
const table = document.getElementById('board-table')
let index = 4

function addNewColumn() {
  table.innerHTML += 
  `
    <tr id="${index}">
      <th><input id="123" type="text" placeholder="Column name"></th>
      <th><input id="123" type="checkbox"><label onclick="deleteRow('${index}')" id="redCross" for="123">&#10799;</label></th>
    </tr>
  `

  index++
}

function deleteRow(id) {
  console.log(id)
  let row = document.getElementById(id)
  row.remove()
}