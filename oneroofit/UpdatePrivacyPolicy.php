<!DOCTYPE html>
<html>
  <body>
	<form method="post">
		 Privacy Policy:<br>
		<input type="text" name="privacytxt">
		<br><br>
		<input type="submit" name="save" value="Update">
	</form>
  </body>
</html>


<?php
include_once 'config.php';
if(isset($_POST['save']))
{	 
	 $text = $_POST['privacytxt'];
	 $sql = "UPDATE privacypolicy SET privacy='$text'";
	 if (mysqli_query($con, $sql)) {
		echo "Privacy Policy Updated";
	 } else {
		echo "Error: " . $sql . "
" . mysqli_error($con);
	 }
	 mysqli_close($con);
}
?>
