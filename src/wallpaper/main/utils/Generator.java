package wallpaper.main.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import simplemysql.SimpleMySQLResult;
import wallpaper.main.Art;

public class Generator {
	static Random random = new Random();

	public static void generateImage(){
		new Thread(new Runnable(){

			@Override
			public void run() {

				SimpleMySQLResult result = SQL.query("SELECT * FROM images ORDER BY imageLikes DESC LIMIT 10");
				ArrayList<Art> arts = new ArrayList<Art>();
				while(result.next()){
					boolean squares = Boolean.parseBoolean(result.getString("imageSquares"));
					boolean circles = Boolean.parseBoolean(result.getString("imageCircles"));
					boolean noice = Boolean.parseBoolean(result.getString("imageNoice"));

					int squareChance = Integer.parseInt(result.getString("imageSquareChance"));
					int circleChance = Integer.parseInt(result.getString("imageCircleChance"));
					int noiceChance = Integer.parseInt(result.getString("imageNoiceChance"));

					int squareSize = Integer.parseInt(result.getString("imageSquareSize"));
					int circleSize = Integer.parseInt(result.getString("imageCircleChance"));

					int chance = Integer.parseInt(result.getString("imageChance"));
					int colors = Integer.parseInt(result.getString("imageColors"));

					int likes = Integer.parseInt(result.getString("imageLikes"));

					arts.add( new Art(squares, circles, noice, squareChance, circleChance, noiceChance, squareSize, circleSize,chance,colors,likes));

				}
				result.close();

				BufferedImage img = new BufferedImage(1920,1080, BufferedImage.TYPE_INT_RGB);
				ArrayList<Color> colorList = new ArrayList<Color>();

				boolean squares = false;
				boolean circles = false;
				boolean noice = false;

				int squareChance = 0;
				int circleChance = 0;
				int noiceChance = 0;

				int squareSize = 0;
				int circleSize = 0;

				int chance = 0;

				int colors = 1;

				boolean hasLikes = true;

				for(int i = 0; i < arts.size(); i++){
					if(arts.get(i).likes < 1){
						hasLikes = false;
					}
				}

				if(arts.size() < 1 || !(hasLikes)){


					System.out.println("Could not find top images, creating random variables");
					squares = random.nextBoolean();
					circles = random.nextBoolean();
					noice = random.nextBoolean();

					squareChance = random.nextInt(10000)+1;
					circleChance = random.nextInt(10000)+1;
					noiceChance = random.nextInt(100)+1;

					squareSize = random.nextInt(48)+1;
					circleSize = random.nextInt(48)+1;

					chance = random.nextInt(20)+1;

					colors = random.nextInt(100)+2;


				}else if(arts.size() > 0){

					System.out.println("Found top 3 images, fetching their values...");
					squares = true;
					circles = true;
					noice = true;

					Art art = arts.get(random.nextInt(arts.size()));
					squareChance = art.squareChance;
					art = arts.get(random.nextInt(arts.size()));
					circleChance = art.circleChance;
					art = arts.get(random.nextInt(arts.size()));
					noiceChance = art.noiceChance;
					art = arts.get(random.nextInt(arts.size()));
					squareSize = art.squareSize;
					art = arts.get(random.nextInt(arts.size()));
					circleSize = art.circleSize;
					art = arts.get(random.nextInt(arts.size()));
					chance = art.chance;
					art = arts.get(random.nextInt(arts.size()));
					colors = art.colors;

				}



				while(squares == false && circles == false && noice == false){
					squares = random.nextBoolean();
					circles = random.nextBoolean();
				}

				for(int i = 0; i < colors; i++){
					colorList.add(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
				}


				Color bgColor = colorList.get(random.nextInt(colorList.size()));
				for(int x = 0; x < img.getWidth(); x++){
					for(int y = 0; y < img.getHeight(); y++){
						img.setRGB(x, y, bgColor.getRGB());
					}
				}

				for(int x = 0; x < img.getWidth(); x++){
					for(int y = 0; y < img.getHeight(); y++){
						if(random.nextInt(chance) == 0){
							Color color = colorList.get(random.nextInt(colorList.size()));
							if(circles){
								if(random.nextInt(circleChance) == 0){
									draw_circle(img, color, x,y,random.nextInt(circleSize));
								}
							}
							if(squares){
								if(random.nextInt(squareChance) == 0){
									draw_square(img, color, x, y, random.nextInt(squareSize), random.nextInt(squareSize));
								}
							}
							if(noice){
								if(random.nextInt(noiceChance) == 0){
									img.setRGB(x, y, color.getRGB());
								}
							}
						}
					}
				}


				try {

					String fname = NameGenerator.getGeneratedName()+".png";
					File outputfile = new File("art/"+fname);
					ImageIO.write(img, "png", outputfile);


					String sql = "INSERT INTO images (imageLikes,imageName,imageSquares,"
							+ "imageCircles,"
							+ "imageNoice,"
							+ "imageSquareChance,"
							+ "imageCircleChance,"
							+ "imageNoiceChance,"
							+ "imageSquareSize,"
							+ "imageCircleSize,"
							+ "imageChance,"
							+ "imageColors)"
							+ "VALUES("
							+ 0+",'"+fname+"',"+(squares?1:0)+","+(circles?1:0)+","+(noice?1:0)+","+squareChance+","+circleChance+","+noiceChance+","+squareSize+","+circleSize+","+chance+","+colors
							+ ")";
					SQL.query(sql);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}}).start();



	}

	private static  void draw_circle(BufferedImage img, Color color, int x, int y, int radius){

		for(int xx = x-(radius/2); xx < x+(radius/2); xx++){
			for(int yy = y-(radius/2); yy < y+(radius/2); yy++){
				double distance = Math.sqrt((xx-x)*(xx-x) + (yy-y)*(yy-y));
				if(distance < radius/2 && xx > 0 && xx < img.getWidth() && yy > 0 && yy < img.getHeight()){
					img.setRGB(xx, yy, color.getRGB());
				}
			}
		}
	}

	public static void draw_square(BufferedImage img, Color color, int x, int y, int width, int height){
		for(int xx = x; xx < x+width; xx++){
			for(int yy = y; yy < y+height; yy++){
				if(xx > 0 && xx < img.getWidth() && yy > 0 && yy < img.getHeight()){
					img.setRGB(xx, yy, color.getRGB());
				}
			}
		}
	}
	
	
}
