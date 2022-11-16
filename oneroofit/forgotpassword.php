<?php

require "config.php";

$email = $_POST['email'];

    $result = mysqli_query($con,"SELECT * FROM users where email='" . $_POST['email'] . "'");
    $row = mysqli_fetch_assoc($result);
	$fetch_email=$row['email'];

	$email=$row['email'];
	$name = $row['name'];
	$RecPassword=$row['recpassword'];
	
	//$verify  = password_verify($password);con
	if($email==$fetch_email) {
				$to = $email;
                $subject = "Password";
                $txt = "Hello Mr/mrs: $name.\n Your account password is : $RecPassword";
                $headers = "From: paragp91199@gmail.com" . "\r\n";
                mail($to,$subject,$txt,$headers);
                echo "Success";
			}
				else{
					echo 'invalid userid';
				}

?>