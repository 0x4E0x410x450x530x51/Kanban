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
    rows = getBoardRows()

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
              ${rows[i].value} <input type="number" value="${defaultValue}" id="${rows[i].value}-smlxl%"> %
            </div>
            `
        }
    }

});

let boardIndex = 3
let teamIndex = 3

// board 
const boardTable = document.getElementById('board-table')

function addBoardRow() {
    boardIndex++
    let newRow = boardTable.insertRow()
    newRow.id = "board-" + boardIndex
    
    newRow.innerHTML += `
        <th><input id="${boardIndex}-board-input" type="text" placeholder="Column name"></th>
        <th><input type="checkbox"><label onclick="deleteRow('board-${boardIndex}')" class="redCross">&#10799;</label></th>
    `
}

function deleteRow(id) {
    document.getElementById(id).remove()
    if (id.includes('board')) boardIndex-- // when a board row gets deleted
    else teamIndex-- // when a team row gets deleted 
}

function getBoardRows() {
    let rows = []

    for (let i = 1; i <= boardIndex; i++) {
        let id = i + '-board-input'
        let row = document.getElementById(id)
        rows.push(row)
    }

    return rows
}

// team
const teamTable = document.getElementById('team-table')
const teamTitles = document.getElementById('team-titles')

function addTeamRow() {
    teamIndex++

    let newRow = teamTable.insertRow()
    newRow.id = "team-" + teamIndex

    let columNames = []
    columNames = getBoardRows()

    newRow.innerHTML += `
        <th><input type="text" placeholder="Name"></th>
        <th><input type="number" placeholder="Headcount"></th>
    ` 
    for (let i = 1; i <= boardIndex; i++) {
        newRow.innerHTML += `
            <th class="${i + 2}-col"><input type="number"></th>
        `
    }

    newRow.innerHTML += `<th id="lastCell-${teamIndex}"><label onclick="deleteRow('team-${teamIndex}')" class="redCross-team">&#10799;</label></th>`
}

function saveBoardData() {
    columns = []
    columns = getBoardRows()

    teamTitles.innerHTML = ""
    teamTitles.innerHTML += `
    <td><p class="team-title">Name</p></td>
    <td><p class="team-title">Headcount</p></td>
    `

    for (let i = 0; i <= columns.length - 1; i++) {
        teamTitles.innerHTML += `
        <td><p class="team-title">${columns[i].value}</p></td>
        `
    }
    console.log(teamIndex)
    for (let i = 1; i <= teamIndex; i++) {
        document.getElementById('lastCell-' + i).remove()
    }

    // get existing row and add a new cell 
    let index = 1
    while (document.getElementById('team-' + index) != null) {
        
        let row = document.getElementById('team-' + index)
        row.innerHTML += `
        <th><input type="number"></th>
        <th id="lastCell-${teamIndex}"><label onclick="deleteRow('team-${teamIndex}')" class="redCross-team">&#10799;</label></th>
        `
        index++
    }

}