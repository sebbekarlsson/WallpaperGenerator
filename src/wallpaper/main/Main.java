package wallpaper.main;


import java.io.File;
import java.util.Random;

import simplemysql.SimpleMySQLResult;
import wallpaper.main.utils.Generator;
import wallpaper.main.utils.SQL;



public class Main {
	Random random = new Random();
	public static void main(String[] args){
		new Main();
	}
	public Main(){
		File artFolder = new File("art");
		if(!artFolder.exists()){
			artFolder.mkdirs();
		}

		// retrieve image
		new Thread(new Runnable(){

			@Override
			public void run() {
				long lastTime = System.currentTimeMillis();
				while(true){
					if(System.currentTimeMillis()-lastTime >= 1000*20){
						System.out.println("seconds");
						Generator.generateImage();
						
						if(random.nextInt(100) == 0){
							SimpleMySQLResult result = SQL.query("SELECT * FROM images ORDER BY imageLikes ASC LIMIT 100");
							while(result.next()){
								String id = result.getString("imageID");
								String fname = result.getString("imageName");
								String sql = "DELETE FROM images WHERE imageID="+id;
								SQL.query(sql);
								File file = new File("art/"+fname);
								file.delete();
							}
						}
						
						lastTime = System.currentTimeMillis();
					}
				}
				
			}}).start();

	}
}
