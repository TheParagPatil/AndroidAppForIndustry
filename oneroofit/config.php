<?php
	// Database configuration 
	$dbHost     = "localhost"; 
	$dbUsername = "id15530701_mark50"; 
	$dbPassword = "Password@123"; 
	$dbName     = "id15530701_oneroofit"; 
	 
	// Create database connection 
	$con =  new mysqli($dbHost, $dbUsername, $dbPassword, $dbName); 
	 
	// Check connection 
	if ($con->connect_error) { 
	    die("Connection failed: " . $con->connect_error); 
	} 
?>