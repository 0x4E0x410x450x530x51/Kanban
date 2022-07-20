
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
  delay(3001).then(() => link.innerHTML += 'testlink')
  generateLinkButton.style.display = "none"

}

// Time manager
function delay(time) {
  return new Promise(resolve => setTimeout(resolve, time));
}


// settings ----------------------------------------------

// task creation strategy
// get settings 
let specialSettingsConstant = document.getElementById('constant-special-settings')
let specialSettingsRandom = document.getElementById('random-special-settings')
let specialSettingsScrum = document.getElementById('scrum-special-settings')
let taskCreationStrat = document.getElementById('task-creation-strat')

taskCreationStrat.addEventListener("change", function () {

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
// get settings 
let specialSettingsConstantSize = document.getElementById('constant-special-settings')
let taskSizeStrat = document.getElementById('task-size-strat')
const constantSettings = document.getElementById('constant-settings-size')
const distributionSettings = document.getElementById('distribution-settings-size')
const smlxlSettings = document.getElementById('smlxl-settings-size')
let smlxlDefaultSection = document.getElementById('smlxl-default-section')

taskSizeStrat.addEventListener("change", function () {

  let value = this.value

  // get board values
  let rows = []
  rows = getBoardColumns()

  if (value == 'constant') {
    constantSettings.style.display = "block"
    distributionSettings.style.display = "none"
    smlxlSettings.style.display = "none"
    // clear fields 
    distributionSettings.innerHTML = ""
    smlxlSettings.innerHTML = ""

    smlxlDefaultSection.style.display = "none" // hide section

    // insert values 
    for (let i = 0; i <= rows.length - 1; i++) {
      constantSettings.innerHTML +=
        `
          <div class="input-section">
            ${rows[i].value} <input type="number" value="2" id="${rows[i].value}-input"> hours
          </div>
          `
    }

  }
  else if (value == 'distribution') {
    constantSettings.style.display = "none"
    distributionSettings.style.display = "block"
    smlxlSettings.style.display = "none"
    // clear fields 
    constantSettings.innerHTML = ""
    smlxlSettings.innerHTML = ""

    smlxlDefaultSection.style.display = "none" // hide section

    // insert values 
    for (let i = 0; i <= rows.length - 1; i++) {
      distributionSettings.innerHTML +=
        `
          <div class="input-section">
            ${rows[i].value} mean <input type="number" value="2" id="${rows[i].value}-mean"> hours
          </div>
          <div class="input-section">
            ${rows[i].value} variation <input type="number" value="2" id="${rows[i].value}-variation">
          </div>
          `
    }
  }

  else if (value == 'smlxl') {
    constantSettings.style.display = "none"
    distributionSettings.style.display = "none"
    smlxlSettings.style.display = "block"
    // clear fields 
    constantSettings.innerHTML = ""
    distributionSettings.innerHTML = ""

    smlxlDefaultSection.style.display = "flex" // show section

    let defaultValue = 100 / rows.length

    // insert values 
    for (let i = 0; i <= rows.length - 1; i++) {
      smlxlSettings.innerHTML +=
        `
          <div class="input-section">
            ${rows[i].value} <input type="number" value="${defaultValue}" id="${rows[i].value}-input%"> %
          </div>
          `
    }
  }

});

// board 
const table = document.getElementById('board-table')
let index = 4

function addNewColumn() {
  table.innerHTML +=
    `
    <tr id="${index}">
      <th><input id="${index}-input" type="text" placeholder="Column name"></th>
      <th><input type="checkbox"><label onclick="deleteRow('${index}')" class="redCross">&#10799;</label></th>
    </tr>
  `

  index++
}

const teamTable = document.getElementById('team-table')
let teamIndex = 4

function addNewTeamColumn() {
  teamTable.innerHTML +=
    `
    <tr id="team-${teamIndex}">
      <th><input type="text" placeholder="Name"></th>
      <th><input type="number"></th>
      <th><input type="number"></th>
      <th><input type="number"></th>
      <th><input type="number"></th>
      <th><label onclick="deleteRow('team-${teamIndex}')" class="redCross-team">&#10799;</label></th>
    </tr>
  `

  index++
}


function deleteRow(id) {
  console.log(id)
  let row = document.getElementById(id)
  row.remove()
  index--
}

let rowIndex = 3
const teamTitles = document.getElementById('team-titles')

// team 
function saveBoard() {
  // get new values 
  let boardRows = []
  boardRows = getBoardColumns()


  // title
  teamTitles.innerHTML = "" // clear
  teamTitles.innerHTML +=  // insert default values
    `
    <td><p class="team-title">Name</p></td>
    <td><p class="team-title">Headcount</p></td>
  `
  // insert updated values
  for (let i = 0; i <= boardRows.length - 1; i++) {
    teamTitles.innerHTML +=
      `
    <td><p class="team-title">${boardRows[i].value}</p></td>
  `
  }

}

function getBoardRows() {
  let rows = []

  for (let i = 1; i <= index - 1; i++) {
    let id = i + '-input'
    let row = document.getElementById(id)
    rows.push(row)
  }

  return rows
}