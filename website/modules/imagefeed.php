<?php

	$sql = "SELECT * FROM images ORDER BY imageDate DESC LIMIT 12";
	$result = mysql_query($sql);
	$imgs = [];
	while(($data = mysql_fetch_array($result)) != false){
		array_push($imgs, $data);
	}

?>
<div class="imagefeed">
	<?php
		foreach($imgs as $image){
			$fname = $image['imageName'];
			$id = $image['imageID'];

			echo "<div class='imagedisplay shadowed' style='background-image:url(../art/$fname);'>";
				echo "<a href='image.php?id=$id'><div class='displaycontent'>";
					echo "<span>";
						echo "<h2>Rate</h2>";
					echo "</span>";
				echo "</div></a>";
			echo "</div>";
		}

	?>
	<div style="clear:both;"></div>
</div>