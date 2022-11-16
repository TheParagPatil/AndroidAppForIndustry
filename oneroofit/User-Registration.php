<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include 'DatabaseConfig1.php';

 $con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

 $Email = $_POST['email'];

 $Phone = $_POST['phone'];
 
 $Full_Name =$_POST['name'];

 $Password = $_POST['password'];

 $RecPassword = $_POST['recpassword'];

 $Email=mysqli_real_escape_string($con,$Email);

 $Phone=mysqli_real_escape_string($con,$Phone);

 $Full_Name = mysqli_real_escape_string($con,$Full_Name);

 $Password=mysqli_real_escape_string($con,$Password);

 $RecPassword=mysqli_real_escape_string($con,$RecPassword);

 $Email=htmlentities($Email);

 $Full_Name=htmlentities($Full_Name);
 
 $Password=htmlentities($Password);
 
 $RecPassword=htmlentities($RecPassword);

 $Password = PASSWORD_HASH($Password, PASSWORD_DEFAULT);

 $CheckSQL = "SELECT * FROM users WHERE email='$Email'";
 
 $check = mysqli_fetch_array(mysqli_query($con,$CheckSQL));
 
 if(isset($check)){

 echo 'Email Already Exist, Please Enter Another Email.';

 }
else{ 
        $Sql_Query = "INSERT INTO users (email,phone,name,password,recpassword) values ('$Email','$Phone','$Full_Name','$Password','$RecPassword')";

 if(mysqli_query($con,$Sql_Query))
    {
        echo 'User Registration Successfully';
    }

else
    {
        echo 'Something went wrong';
    }
 
}

}
 
?>