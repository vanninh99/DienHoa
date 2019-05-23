<?php
	include "connect.php";
	$page = $_GET['page'];
	$idsp = $_POST['idloaisp'];
	$space = 6;
	$limit = ($page - 1)*$space;

	$arrsanpham = array();
	$query = "SELECT * FROM sanpham WHERE idloaisp = $idsp LIMIT $limit,$space";
	$data = mysqli_query($conn, $query);
	while($row = mysqli_fetch_assoc($data)){
		array_push($arrsanpham, new Sanpham(
			$row['flowerid'],
			$row['flowername'],
			$row['flowerimage'],
			$row['price'],
			$row['idloaisp']));
	}
	echo json_encode($arrsanpham);
	/**
	 * 
	 */
	class Sanpham
	{
		
		function Sanpham($flowerid,$flowername,$flowerimage,$price,$idloaisp)
		{
			$this->flowerid = $flowerid;
			$this->flowername = $flowername;
			$this->flowerimage = $flowerimage;
			$this->price = $price;
			$this->idloaisp = $idloaisp;
		}
	}
?>