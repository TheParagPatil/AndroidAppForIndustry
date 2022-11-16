<?php
include '../connect.php';
if(isset($_POST['sub'])){
$Name=$_POST['name'];
$Desc=$_POST['desc'];
$smallDesc=$_POST['small_desc'];
//code for image uploading
if($_FILES['f1']['name']){
move_uploaded_file($_FILES['f1']['tmp_name'], "service_image/".$_FILES['f1']['name']);
$img="https://bloodyzone.000webhostapp.com/oneroofit/add_services/service_image/".$_FILES['f1']['name'];
}
$i="insert into our_services(service_name,service_description,service_image,small_desc)values('$Name','$Desc','$img','$smallDesc')";
if(mysqli_query($con, $i)){
echo "inserted successfully..!";
}
}
?>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<form method="POST" enctype="multipart/form-data">
<table>
<tr>
<td>
Name
<input type="text" name="name">
</td>
</tr>
<tr>
<td>
Description
<input type="text" name="desc">
</td>
</tr>
<tr>
<td>
Small Desc
<input type="text" name="small_desc">
</td>
</tr>
<tr>
<td>
Image
<input type="file" name="f1">
</td>
</tr>
<tr>
<td>
<input type="submit" value="submit" name="sub">
</td>
</tr>
</table>
</body>
</html>
