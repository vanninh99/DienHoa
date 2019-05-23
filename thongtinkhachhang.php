<?php
	include "connect.php";
	$tenkh = $_POST['fullname'];
	$phone = $_POST['phonenumber'];
	$email = $_POST['email'];
	$address = $_POST['address'];
	if(strlen($tenkh) > 0 && strlen($phone) > 0 && strlen($email) > 0 && strlen($address) > 0){
		$query = "INSERT INTO user(userid, fullname, address, email, phonenumber) VALUES(null, '$tenkh', '$address', '$email', '$phone')";
		if(mysqli_query($conn, $query)){
			$iddonhang = $conn->insert_id;
			echo $iddonhang;
		}else{
			echo "thất bại";
		}
	}else{
		echo "bạn hãy kiểm tra lại dữ liệu";
	}
?>