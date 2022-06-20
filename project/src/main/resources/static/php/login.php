<?php


if (($_SERVER["REQUEST_METHOD"] === "GET") || !isset($_POST["email"]) || !isset($_POST["password"])) {
    header("Location: ./login.html");
    die();

} else {
    $email = $_POST["email"];
    $password = $_POST["password"];

    // check email for UBS

    if (!preg_match('/(\S+)@(ubs\.com)/', trim($email))) {
        header("Location: ./login.html");
        die();
        // do something cool here like add a session cookie or sm that tells the login page that the email is not a @ubs email.
    }


    if (str_contains($email, "'") || str_contains($email, "/") || str_contains($email, "*") || str_contains($email, "\"")) {
        header("Location: ./login.html");
        die();
        // do something cool here aswell
    }

    echo "Hello hacked HAHAAHA";




}


?>