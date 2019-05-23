<?php
	include "connect.php";
	$query = "SELECT * FROM loaisanpham";
	$data = mysqli_query($conn, $query);
	$arrloaisp = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($arrloaisp, new Loaisp(
			$row["idloaisp"],
			$row["ten"],
			$row["img"]));
	}
	echo json_encode($arrloaisp);
	class Loaisp
	{
		
		function Loaisp($idloaisp,$ten,$img)
		{
			$this->idloaisp = $idloaisp;
			$this->ten = $ten;
			$this->img = $img;
		}
	}
?>