var x = document.getElementById("login");
var y = document.getElementById("register");
var z = document.getElementById("btn");

var regbtn = document.getElementById("registerbtn")
var logbtn = document.getElementById("loginbtn")

function register(){
    x.style.left = "-300%";
    y.style.right = "0%"
    z.style.left = "50%";
    for (let i = 0; i < 255; i++) {
        setTimeout(function() {
            regbtn.style.color = `rgb(${i}, ${i}, ${i})`
            logbtn.style.color = `rgb(${255-i}, ${255-i}, ${255-i})`
        }, i)
    }
}

function login(){
    x.style.left = "0%";
    y.style.right = "-300%"
    z.style.left = "0";
    for (let i = 0; i < 255; i++) {
        setTimeout(function() {
            logbtn.style.color = `rgb(${i}, ${i}, ${i})`
            regbtn.style.color = `rgb(${255-i}, ${255-i}, ${255-i})`

        }, i)
    }
}

const btn1 = document.getElementById('submit1');
const btn2 = document.getElementById('submit2');

btn1.addEventListener('click', function onClick() {
btn1.style.color = '#CCCCCC';
});
btn2.addEventListener('click', function onClick() {
btn2.style.color = '#CCCCCC';
});

