<?php 
	$host = "localhost";
	$username = "root";
	$password = "";
	$database = "appsellingflower";
	$conn = mysqli_connect($host,$username,$password,$database);
	mysqli_query($conn,"SET NAMES 'utf8'");
?>