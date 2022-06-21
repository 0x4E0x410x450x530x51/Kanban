
let sock;
let client;

function begin() {
    // replace IP here with the website IP
  sock = new SockJS("http://192.168.43.46:8080/board");

  client = Stomp.over(sock);
  client.connect({
    'email': document.getElementById("email").value,
    'password': document.getElementById("password").value
  }, frame => {

    // this is the subscriber, check AppSecurityConfig
    client.subscribe("/kanban/"+document.getElementById("boardid").value, payload => {

        /*
      let message_list = document.getElementById('message-list');
      let message = document.createElement('li');
      message.appendChild(document.createTextNode("[" + JSON.parse(payload.body).email + "]: " + JSON.parse(payload.body).message));
      message_list.appendChild(message);
    */

        // Code above is placeholder for the old messaging system



    });
  });
}

function sendMessage(){

    /*
  let input = document.getElementById("message-input");
  let messagee = input.value;

  client.send('/boardSend/'+document.getElementById("boardid").value, {}, JSON.stringify({message: messagee, email: document.getElementById("email").value}));
     */

    // Code above also a placeholder for chatting system.

}

// login
async function login() {
    let a_1 = await (fetch(
        "/api/login", {
          method: "POST",
            headers: {
              'Content-Type':"application/json"
            },
          body: JSON.stringify({
            email: document.getElementById("emailLoginInput").value,
            password: document.getElementById("passwordLoginInput").value
          })
        }
      ))
    let a_2 = await a_1.text();
    if (a_2 === "Success!") {
        document.location.pathname = "/html/index.html"
    }
}



