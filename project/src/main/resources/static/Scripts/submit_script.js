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



function addFunction() {
    const placeToPut = document.getElementById("submitTable")
    placeToPut.innerHTML += `
                <tr>
                    <td>Test data</td>
                    <td>Test data</td>
                    <td>Test data</td>
                    <td><button type="button" class="btn-view">View</button></td>
                </tr>
    `
}
