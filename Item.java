import java.awt.Graphics;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Item extends Movable implements Comparable<Item> {
	private String type;
	
	public static final String ember = "ember";
	public static final String wood = "wood";
	public static final String steel = "steel";
	
	public Item(String type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = 50;
		this.height = 50;
		
		try {
			image = ImageIO.read(new File("./Images/" + type + ".png"));
        } catch (IOException e) {}
	}
	
	public String type() {
		return type;
	}
	
	public void drawMe(Graphics g) {
		g.drawImage(image, (int)(this.x), (int)(this.y), width, height, null);
	}
	public void displayImage(Graphics g, int x, int y) {
		g.drawImage(image, (int)x, (int)y, width, height, null);
	}
	public boolean getCollision(Main main, int direction) {
		//ignore direction, direction is for obstacles only
		int mainX = main.getX();
		int mainY = main.getY();
		int mainHeight = main.getHeight() - 5;
		int mainWidth = main.getWidth() - 15;
		
		int objectHeight = height - 5;
		int objectWidth = width - 8;
		
		if(mainX + mainWidth > x && mainX < x + objectWidth && mainY + mainHeight > y && mainY < y + objectHeight) {
			return true;
		}
		return false;
	}

	@Override
	public int compareTo(Item item) {
		return type.compareTo(item.type());
	}
}