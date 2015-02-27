package wallpaper.main.utils;

import java.util.Random;

public class Mathz {
	static Random random = new Random();
	public static int getRandomInt(int...ints){
		return ints[random.nextInt(ints.length)];
	}
}
