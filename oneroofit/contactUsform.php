<?php
include_once('config.php');

    //$con = mysqli_connect("localhost","id15530701_mark50","Password@123","id15530701_onerofit");

     $Name = $_REQUEST['name'];
     $Phone = $_REQUEST['phone'];
     $Email = $_REQUEST['email'];
     $Message = $_REQUEST['message'];
     $Subject = $_REQUEST['subject'];
     
     

     $sql = "INSERT INTO contact_us (name,phone,email,message,subject) VALUES ('$Name','$Phone','$Email','$Message','$Subject')";
    
     $result = mysqli_query($con,$sql);
     if($result){
         echo "Thanks $Name for filling out our form!";
     }
     else{
         echo "failed";
     }
     mysqli_close($con);
?>