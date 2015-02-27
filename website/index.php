<?php include("modules/database.php"); ?>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="styles/style.css">
		<link href='http://fonts.googleapis.com/css?family=Roboto:300' rel='stylesheet' type='text/css'>
		<meta charset="utf-8">
		<script type="text/javascript" src="apps/jquery.js"></script>
	</head>

	<body align="center">
		<?php include("modules/navbar.php"); ?>
		<div class="content">
			<?php
				include("modules/imagefeed.php");
			?>
		</div>
	</body>

	<footer>
	</footer>
</html>