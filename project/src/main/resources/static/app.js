var stompClient = null;

var link = window.location.href.split("board/")

$(document).ready ( function(){

    let socket = new SockJS('/boardConnection');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/board/' + link[1], function(input) {
            let textGet = JSON.parse(input.body)
            if (textGet.text.includes("<script>")) {
                disconnect();
            } else  {
                showGreeting(textGet.text);
            }
        });
    });
});

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/live/board/" + link[1], {}, $("#name").val());
}

function showGreeting(text) {
    console.log("show")
    $("#greetings").append("<tr><td>" + text + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

