<?php
include '../connect.php';
if(isset($_POST['sub'])){
$t=$_POST['text'];
$u=$_POST['user'];
//code for image uploading
if($_FILES['f1']['name']){
move_uploaded_file($_FILES['f1']['tmp_name'], "uploads/".$_FILES['f1']['name']);
$img="https://bloodyzone.000webhostapp.com/uploads/".$_FILES['f1']['name'];
}
$i="insert into teamimage(name,designation,image_url)values('$t','$u','$img')";
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
<input type="text" name="text">
</td>
</tr>
<tr>
<td>
Designation
<input type="text" name="user">
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
