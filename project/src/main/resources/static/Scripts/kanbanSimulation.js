// Opens the sidebar on the Kanban Board site.
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("content").style.marginLeft = "250px";
}

// Closes the sidebar on the Kanban Board site.
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("content").style.marginLeft = "0";
}


function manageSidebar() {
    var element = document.querySelector('.sidenav');
    var width = element.offsetWidth;

    if (width == 250) closeNav()
    else openNav()
}