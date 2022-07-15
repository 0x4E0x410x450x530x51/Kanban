


const taskName = document.getElementById("storyNameForm")
const taskDesc = document.getElementById("descritptionForm")
const taskStat = document.getElementById("storyPriorityForm")
const taskDefod = document.getElementById("definitionOfDoneForm")
const taskColor = document.getElementById("storyColorForm")
const taskDate = document.getElementById("storyDueDate")

const taskColors = ["red", "yellow", "green", "blue", "purple"]

var timescale = 1000

// cool workaround, thank you JavaScript!
function getTimescale() {
    return timescale
}

const configuration = {
    "RedWorkers":5,
    "YellowWorkers":5,
    "GreenWorkers":5,
    "BlueWorkers":5,
    "PurpleWorkers":5
}


var taskCount = 0
var incompleteTasks = []
var progressTasks = []
var completeTasks = []
var currentTaskIndex = 0


var nodeObserverProgress = null;
var nodeObserverAdded = null;

// --- I hate my life --- //

var redTasks = []
var yellowTasks = []
var greenTasks = []
var blueTasks = []
var purpleTasks = []


var redTasksIp = []
var yellowTasksIp = []
var greenTasksIp = []
var blueTasksIp = []
var purpleTasksIp = []

var redTasksDone = []
var yellowTasksDone = []
var greenTasksDone = []
var blueTasksDone = []
var purpleTasksDone = []



var redTaskInterval = null;
var yellowTaskInterval = null;
var greenTaskInterval = null;
var blueTaskInterval = null;
var purpleTaskInterval = null;

var observerConfig = {
    attributes: false,
    childList: true,
    subtree: false
}

var ip = document.getElementById("inprogress")
var don = document.getElementById("done")
var bcklog = document.getElementById("todo")

var intervalPaused = false
var intervalObject = null

var simulationPaused = true

function rgb2hex(rgbText) {

    let a = rgbText.split("(")[1].split(")")[0];
    a = a.split(",");
    var b = a.map(function (x) {
    x = parseInt(x).toString(16);
    return (x.length == 1) ? "0" + x : x;
    })
   return "#" + b.join("")
}

// generate task 

function genCreateTask(name, desc, status, defod, color, date, i) {

    taskName.value = name 
    taskDesc.value = desc 
    taskStat.value = status
    taskDefod.value = defod
    taskColor.value = color 
    taskDate.value = date 

    saveTask()  

    let t_1 = {
        "element":document.getElementById("task#"+(i+1).toString()+(i).toString()),
        "color":color,
        "status":status,
        "date":date,
    }
    incompleteTasks.push(t_1)
}


// open settings window
function openSettings() {
    // make settingsPanel visible
    document.getElementById("settingsblock").style.display = "block"
}
function closeSettings() {
    // make settingsPanel invisible
    document.getElementById("settingsblock").style.display = "none"
}

// save configuration


function saveSettings() {
    // another hideous piece of code, sorry...
    taskColors.forEach(col => {
        configuration[(col.charAt(0)).toUpperCase()+col.substring(1)+"Workers"] = parseInt(document.getElementById(col+"Workers").value)
    })
    closeSettings()
}


// color sorting functions
function sortTasks() {
    incompleteTasks.forEach( task => {
        switch(task.color) {
            case "#e60000":
                redTasks.push(task)
                break;
            case "#e6bf33":
                yellowTasks.push(task)
                break;
            case "#aacc33":
                greenTasks.push(task)
                break;
            case "#6495ed":
                blueTasks.push(task)
                break; 
            case "#8e7cc3":
                purpleTasks.push(task)
                break;
        }
    })

}

function getColor(el) {
    
    switch(rgb2hex(el.style.borderLeftColor)) {
        case "#e60000":
            return "red"
        case "#e6bf33":
            return "yellow"
        case "#aacc33":
            return "green"
        case "#6495ed":
            return "blue"
        case "#8e7cc3":
            return "purple"
        default:
            alert("Error in code, [GetColor Function]")
            return "AAAAA";
    }
}

function removeFromArray(arr, el) {
    //console.log("Removing "+el+" from "+arr)
    let a_1 = []
    arr.forEach(a => {
        if (typeof(a) == "undefined") {
            return;
        }
        if (a.hasOwnProperty("element")) {
            if (a.element != el) {
                a_1.push(a)
            } else {
                console.log("Removed from array?")
            }
        } else if (a != el) {
            // console.log(a, el)
            a_1.push(a)
        } else {
            console.log("Removed from array?")
        }
    })
    arr.splice(0)
    a_1.forEach(a => {
        arr.push(a)
    })
    // arr = a_1
}


function findTask(el) {
    return el
}

function startProgress(el, elWorker) {
    // find out how much progress is missing...
    let progEl = document.getElementById(el.id+"-progress")

    if (isNaN(parseInt((progEl.style.width).replace("%", "").replace(";", "")))) {
        progEl.style.width = "1%"
    }
    
    for (let ie = parseInt((progEl.style.width).replace("%", "").replace(";", "")); ie < 101; ie++) {

        setTimeout(function() {
            if (simulationPaused) {
                return;
            }
            
            document.getElementById(el.id+"-progress").style.width = (ie).toString()+"%"
            if (ie >= 100) {
                finalTaskComplete(el, elWorker)
                return;
            }
            console.log("Timescale:", getTimescale())
        }, ((getTimescale() / elWorker) / 10 ) * ie )
    }

   

}


// neccessary for waiting until progress-bar is full.
function waitForProgress(el, elWorker) {
    let c_amt = parseInt((document.getElementById(el.id+"-progress").style.width).replace("%", "").replace(";", ""))
    if (c_amt < 100) {
        setTimeout(waitForProgress.bind(null, el, elWorker), 150);
        return;
    }
    finalTaskComplete(el, elWorker)
}

function finalTaskComplete(el, elWorker) {
    let progEl = document.getElementById(el.id+"-progress")

    if (parseInt((progEl.style.width).replace("%", "").replace(";", "")) < 100) {
        waitForProgress(el, elWorker);
        return;
    }
    if (parseInt((progEl.style.width).replace("%", "").replace(";", "")) >= 100) {

        for (let ie = 0; ie < 100; ie++) {
            setTimeout(function() {
                el.style.opacity = (100-ie) / 100
            }, 2 * ie)
        }

        setTimeout(function() {
                don.appendChild(el)
        }, 300)

        setTimeout(function() {
            for (let ie = 0; ie < 100; ie++) {
                setTimeout(function() {
                    el.style.opacity = ie / 100
                }, 2 * ie)
            }
        },500)

        switch (getColor(el)) {
            case "red":
                removeFromArray(redTasksIp, el)
                break;
            case "yellow":
                removeFromArray(yellowTasksIp, el)
                break;
            case "green":
                removeFromArray(greenTasksIp, el)
                break;
            case "blue":
                removeFromArray(blueTasksIp, el)
                break;
            case "purple":
                removeFromArray(purpleTasksIp, el)
                break;
        }
    } else {
        waitForProgress(el, elWorker);
        return;
    }
}

function completeTask(el) {
    let elWorker = null
    let ipTaskVar = null
    let dTaskVar = null

    switch (getColor(el)) {
        case "red":
            elWorker = configuration.RedWorkers
            redTasksDone.push(findTask(el))
            ipTaskVar = redTasksDone
            break;
        case "yellow":
            elWorker = configuration.YellowWorkers
            yellowTasksDone.push(findTask(el))
            ipTaskVar = yellowTasksDone
            break;
        case "green":
            elWorker = configuration.GreenWorkers
            greenTasksDone.push(findTask(el))
            ipTaskVar = greenTasksDone
            break;
        case "blue":
            elWorker = configuration.BlueWorkers
            blueTasksDone.push(findTask(el))
            ipTaskVar = blueTasksDone
            break;
        case "purple":
            elWorker = configuration.PurpleWorkers
            purpleTasksDone.push(findTask(el))
            ipTaskVar = purpleTasksDone
            break;
    }


    removeFromArray(incompleteTasks, el)
    

    // have to make a function here to be able to pause/play the progress....
    startProgress(el, elWorker)
   
    
}


function waitForTaskClear(el) {
    switch(getColor(el)) {
        case "red":
            if (configuration.RedWorkers <= redTasksIp.length) {
                setTimeout(waitForTaskClear.bind(null, el), 150);
                return;
            }
            moveTask(el)
            break;
        case "yellow":
            if (configuration.YellowWorkers <= yellowTasksIp.length) {
                setTimeout(waitForTaskClear.bind(null, el), 150);
                return;
            }
            moveTask(el)
            break;
        case "green":
            if (configuration.GreenWorkers <= greenTasksIp.length) {
                setTimeout(waitForTaskClear.bind(null, el), 150);
                return;
            }
            moveTask(el)
            break;
        case "blue":
            if (configuration.BlueWorkers <= blueTasksIp.length) {
                setTimeout(waitForTaskClear.bind(null, el), 150);
                return;
            }
            moveTask(el);
            break;
        case "purple":
            if (configuration.PurpleWorkers <= purpleTasksIp.length) {
                setTimeout(waitForTaskClear.bind(null, el), 150);
                return;
            }
            moveTask(el);
            break;
    }
            
}


function moveTask(el) {
    let elWorker = null
    let ipTaskVar = null
    switch (getColor(el)) {
        case "red":
            
            if (configuration.RedWorkers <= redTasksIp.length) {
              waitForTaskClear(el)
              return;
            }
            console.log("Done waiting "+configuration.RedWorkers + " "+ redTasksIp.length)
            elWorker = configuration.RedWorkers
            ipTaskVar = redTasksIp
            ipTaskVar.push(findTask(el))
            break;
        case "yellow":
            if (configuration.YellowWorkers <= yellowTasksIp.length) {
                waitForTaskClear(el)
                return;
            }
            elWorker = configuration.YellowWorkers
            ipTaskVar = yellowTasksIp
            ipTaskVar.push(findTask(el))
            break;
        case "green":
            if (configuration.GreenWorkers <= greenTasksIp.length) {
                waitForTaskClear(el)
                return;
            }
            elWorker = configuration.GreenWorkers
            ipTaskVar = greenTasksIp
            ipTaskVar.push(findTask(el))
            break;
        case "blue":
            if (configuration.BlueWorkers <= blueTasksIp.length) {
                waitForTaskClear(el)
                return;
            }
            elWorker = configuration.BlueWorkers
            ipTaskVar = blueTasksIp
            ipTaskVar.push(findTask(el))
            break;
        case "purple":
            if (configuration.PurpleWorkers <= purpleTasksIp.length) {
                waitForTaskClear(el)
                return;
            }
            elWorker = configuration.PurpleWorkers
            ipTaskVar = purpleTasksIp
            ipTaskVar.push(findTask(el))
            break;
        default:
            return;
    }

    setTimeout(function() {
        ip.appendChild(el)
        el.style.opacity = 0;
        for (let i = 0; i < 100; i++) {
            setTimeout(function() {
                el.style.opacity = i/100
            }, i*2)
        }
            progressTasks.push(findTask(el))

    })

    setTimeout(function() {
        bcklog.childNodes.forEach(cn => {
            if (cn.id == el.id) {
                cn.remove();
            }
        })
    },100)



    
}

function startSimulation() {
    if (simulationPaused) {
        simulationPaused = false;
        incompleteTasks.forEach(t => {
            moveTask(t.element)
        })
        progressTasks.forEach(t => {
            switch (getColor(t)) {
                case "red":
                    startProgress(t, configuration.RedWorkers)
                    break;
                case "yellow":
                    startProgress(t, configuration.YellowWorkers)
                    break;
                case "green":
                    startProgress(t, configuration.GreenWorkers)
                    break;
                case "blue":
                    startProgress(t, configuration.BlueWorkers)
                    break;
                case "purple":
                    startProgress(t, configuration.PurpleWorkers)
                    break;
            }
        
        })
    }
    

}
function pauseSimulation() {
    if (!simulationPaused) {
        simulationPaused = true;
    }
}


function beginAll() {


    // generate random tasks (Terribly sorry for the one-liner, I'm in a hurry.)
    let max_gen = taskCount + 20
    let t_inc = 0
    for (let i = taskCount; i < (max_gen); i++) {
        setTimeout(function() {

            genCreateTask(
                "Task #"+(i+1).toString(),
                "Description #"+(i+1).toString(),
                ["!", "•", "↓"][Math.floor(Math.random() * 3)],
                "Definition of done #"+(i+1).toString(),
                ["#e60000", "#e6bf33", "#aacc33", "#6495ed", "#8e7cc3"][Math.floor(Math.random() * 5)],
                "2022-0"+(Math.floor(Math.random()*8)+1).toString()+"-0"+(Math.floor(Math.random()*8)+1).toString()+"T"+(Math.floor(Math.random() * 23)+1).toString()+":"+(Math.floor(Math.random() * 59)+1).toString(), //2022-06-24T17:12"
                i
            )
            
            taskCount++;
            t_inc++;           
        }, t_inc + i * 100)

    }

    sortTasks()
    

    // Once all tasks have been generated we start by evaluating all the variables set by the user.

}


function changeTime() {

    timescale = 1000 / parseInt(document.getElementById("sliderTime").value)
    document.getElementById("sliderDiv").innerHTML = Math.round(1000 / (100/ parseInt(document.getElementById("sliderTime").value)))

}

// goated optimization !!!

window.onload = function() {
    let obsProg = new MutationObserver(progressCallback)
    let obsAdded = new MutationObserver(addedCallback)

    obsProg.observe(ip, observerConfig)
    obsAdded.observe(bcklog, observerConfig)
    nodeObserverProgress = obsProg
    nodeObserverAdded = obsAdded
    

}

var progressCallback = function(ml) {
    for (var mutation of ml) {
        if (mutation.type == "childList" && mutation.addedNodes.length > 0) {
            console.log(mutation.addedNodes[0])
            completeTask(mutation.addedNodes[0])
            break;
        }
    }
}



var addedCallback = function(ml) {
    for (var mutation of ml) {
        if (mutation.type == "childList" && mutation.addedNodes.length > 0) {
            for (let i = mutation.addedNodes.length-1; i > 0; i--) {
                if (mutation.addedNodes[i].tagName == "DIV" && mutation.addedNodes[i].getAttribute("class") == "task") {
                    if (!simulationPaused) {
                        moveTask(mutation.addedNodes[i])
                    }
                    break;
                }
            }
        }
    }
}



