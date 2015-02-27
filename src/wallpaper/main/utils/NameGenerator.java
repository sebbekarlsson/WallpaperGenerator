package wallpaper.main.utils;

import java.util.Random;

public class NameGenerator {
	static Random random = new Random();
	static String[] letters = new String[]{
		"a",
		"b",
		"c",
		"d",
		"e",
		"f",
		"g",
		"h",
		"i",
		"j",
		"k",
		"l",
		"m",
		"n",
		"o",
		"p",
		"q",
		"r",
		"s",
		"t",
		"u",
		"w",
		"x",
		"y",
		"z"
	};
	public static String getGeneratedName(){
		int length = random.nextInt(6)+5;
		String name = "";
		for(int i = 0; i < length; i++){
			name += letters[random.nextInt(letters.length)]+random.nextInt(9);
		}
		return name;
	}
}
