<?php
$server = "localhost";
$username = "id15530701_mark50";
$password = "Password@123";
$database = "id15530701_oneroofit";
$con = new mysqli($server, $username, $password, $database);
if($con->connect_error){
    die("Connection Failed: ". $con->connect_error);
}
?>