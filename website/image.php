<?php include("modules/database.php"); ?>
<?php

	$id = $_GET['id'];
	$sql = "SELECT * FROM images WHERE imageID=$id";
	$result = mysql_query($sql);
	$fname = "";
	while(($data = mysql_fetch_array($result)) != false){
		$fname = $data['imageName'];
	}

?>
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
		<div class="content shadowed">
			<div class="imagefeed">
				<img class="bigimagedisplay" src="../art/<?php echo $fname; ?>">
			</div>
			
			<?php  
				if(!in_array($id, $_SESSION['likes'])){
					echo '<input type="hidden" id="id" value="'.$id.'">';
					echo '<a class="btn" id="like">Like</a>'; 
				}

			?>
			
			<script>
				$("#like").click(function(){
					var identity = $("#id").val();
					
					var request = $.ajax({
					  type: "POST",
					  url: "apps/like.php",
					  data: { id: identity}
					});

					request.done(function(){
						$("#like").fadeOut();
					});

				});
			</script>
		</div>
	</body>

	<footer>
	</footer>
</html>