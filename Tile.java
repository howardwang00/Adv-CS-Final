import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile extends Movable {
	public static final int size = 50;
	public static final String water = "water";
	public static final String land = "land";
	public static final String obstacle = "obstacle";
	
	public static BufferedImage waterImage;
	public static BufferedImage landImage;
	public static BufferedImage obstacleImage;
	
	private String type = "";
	
	public Tile(char c, int x, int y) {
		this.x = x;
		this.y = y;
		this.height = size;
		this.width = size;
		this.initialX = x;
		this.initialY = y;
		
		if(c == 'w') {	//Setting the type based on the character
			type = Tile.water;
		} else if(c == 'l') {
			type = Tile.land;
		} else if(c == 'o') {
			type = Tile.obstacle;
		}
		
		try {
			if(Tile.waterImage == null) {
				Tile.waterImage = ImageIO.read(new File("./Images/water.png"));
				Tile.landImage = ImageIO.read(new File("./Images/land.png"));
				Tile.obstacleImage = ImageIO.read(new File("./Images/obstacle.png"));
			}
        } catch (IOException e) {}
	}
	
	public String type() {
		return type;
	}
	
	@Override
	public void drawMe(Graphics g) {
		int x = (int)this.x;
		int y = (int)this.y;
		if(type.equals(Tile.water)) {
			g.drawImage(Tile.waterImage, x, y, width, height, null);
		} else {
			g.drawImage(Tile.landImage, x, y, width, height, null);
			if(type.equals("obstacle")) {
				g.drawImage(Tile.obstacleImage, x, y, width, height, null);
			}
		}
	}
	
	public void display(Graphics g, int x, int y) {
		g.drawImage(image, x, y, width, height, null);
	}
}