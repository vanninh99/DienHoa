<?php
	include "connect.php";
	$query = "SELECT * FROM sanpham ORDER BY flowerid DESC LIMIT 10";
	$data = mysqli_query($conn, $query);
	$arrspmn = array();
	while($row = mysqli_fetch_assoc($data)){
		array_push($arrspmn, new SanphamMoinhat(
			$row['flowerid'],
			$row['flowername'],
			$row['flowerimage'],
			$row['price'],
			$row['idloaisp']));
	}
	echo json_encode($arrspmn);
	class SanphamMoinhat
	{
		
		function SanphamMoinhat($flowerid,$flowername,$flowerimage,$price,$idloaisp)
		{
			$this->flowerid = $flowerid;
			$this->flowername = $flowername;
			$this->flowerimage = $flowerimage;
			$this->price = $price;
			$this->idloaisp = $idloaisp;
		}
	}
?>