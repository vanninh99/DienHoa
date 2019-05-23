<?php
	include "connect.php";
	$json = $_POST['json'];
	$data = json_decode($json, true);
	foreach ($data as $value) {
		$madonhang = $value['madonhang'];
		$masanpham = $value['masanpham'];
		$tensanpham = $value['tensanpham'];
		$soluong = $value['soluong'];
		$giasanpham = $value['giasanpham'];
		$query = "INSERT INTO chitietdonhang(id, madonhang, masanpham, tensanpham, soluong, giasanpham) VALUES(null, '$madonhang', '$masanpham', '$tensanpham', '$soluong', 'giasanpham')";
		$Dat = mysqli_query($conn, $query);
	}
	if($Dat){
		echo "1";
	}else{
		echo "0";
	}
?>