<?php
	include("../modules/database.php");
	session_start();
	
	$id = $_POST['id'];
	$oldLikes = 0;

	$sql = "SELECT * FROM images WHERE imageID=$id";
	$result = mysql_query($sql);
	while(($data = mysql_fetch_array($result)) != false){
		$oldLikes = $data['imageLikes'];
	}

	$newLikes = $oldLikes+1;

	$sql = "UPDATE images SET imageLikes=$newLikes WHERE imageID=$id";
	mysql_query($sql);

	array_push($_SESSION['likes'], $id);


?>