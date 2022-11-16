<?php
header('Content-Type: application/json');

$connect = mysqli_connect("localhost","id15530701_mark50","Password@123","id15530701_oneroofit");
$sql = "SELECT * FROM teamimage";

$result = mysqli_query($connect,$sql);

//$json_array = array();

if(mysqli_num_rows($result) > 0){
    $output = mysqli_fetch_all($result,MYSQLI_ASSOC);
    echo json_encode($output);
}

/*while($row = mysqli_fetch_all($result))
{
    $json_array = $row;
    
}*/


//echo json_encode($json_array);
?>