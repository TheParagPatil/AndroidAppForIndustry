<!DOCTYPE html>
<html>
  <body>
	<form method="post">
		 Privacy Policy:<br>
		<input type="text" name="text">
		<br><br>
		<input type="submit" name="save" value="submit">
	<a href="https://bloodyzone.000webhostapp.com/oneroofit/UpdatePrivacyPolicy.php">Update</a>
	</form>
  </body>
</html>


<?php
include_once 'config.php';
if(isset($_POST['save']))
{	 
	 $text = $_POST['text'];
	 $sql = "INSERT INTO privacypolicy (privacy)
	 VALUES ('$text')";
	 if (mysqli_query($con, $sql)) {
		echo "Privacy Policy Inserted";
	 } else {
		echo "Error: " . $sql . "
" . mysqli_error($con);
	 }
	 mysqli_close($con);
}
?>
