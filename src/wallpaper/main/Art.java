package wallpaper.main;

public class Art {
	public boolean squares;
	public boolean circles;
	public boolean noice;

	public int squareChance;
	public int circleChance;
	public int noiceChance;

	public int squareSize;
	public int circleSize;

	public int chance;

	public int colors;
	
	public int likes;
	
	public Art(boolean squares, boolean circles, boolean noice, int squareChance, int circleChance, int noiceChance, int squareSize, int circleSize, int chance, int colors, int likes){
		this.squares = squares;
		this.circles = circles;
		this.noice = noice;
		this.squareChance = squareChance;
		this.circleChance = circleChance;
		this.noiceChance = noiceChance;
		this.chance = chance;
		this.colors = colors;
		this.squareSize = squareSize;
		this.circleSize = circleSize;
		this.likes = likes;
	}
}
