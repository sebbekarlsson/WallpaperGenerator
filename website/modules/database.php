<?php

	$dbhost = "localhost";
	$dbname = "artist";
	$dbusername = "root";
	$dbpassword = "root";

	$ok = mysql_connect($dbhost, $dbusername, $dbpassword);
	mysql_select_db($dbname);

	session_start();
	if(!isset($_SESSION['likes'])){
		$_SESSION['likes'] = [];
	}
?>