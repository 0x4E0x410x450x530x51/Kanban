var stompClient = null;
let link = window.location.href.split("board/")
$(document).ready ( function(){

    var socket = new SockJS('/fleet');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/fleet/' + link[1], function(input) {
            var textGet = JSON.parse(input.body)
            if (textGet.text.includes("<script>")) {
                disconnect();
            } else  {
                showGreeting(textGet.text);
            }


        });
    });
});

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {

    stompClient.send("/live/fleet/" + link[1], {}, $("#name").val());
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

