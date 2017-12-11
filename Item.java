import java.awt.Graphics;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Item extends Movable implements Comparable<Item> {
	private String type;
	
	public static final String ember = "ember";
	public static final String wood = "wood";
	public static final String steel = "steel";
	public static final String concrete = "concrete";
	public static final String paint = "paint";
	
	public Item(String type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
		this.width = 50;
		this.height = 50;
		this.initialX = x;
		this.initialY = y;
		
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

	@Override
	public int compareTo(Item item) {
		return type.compareTo(item.type());
	}
}