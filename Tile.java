import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Tile extends Movable {
	public static final int size = 50;
	
	private String type;
	
	public Tile(char c, int x, int y) {
		this.x = x;
		this.y = y;
		this.height = size;
		this.width = size;
		
		if(c == 'w') {	//Setting the type based on the character
			type = "water";
		} else if(c == 'l') {
			type = "land";
		}
		
		if(type != null) {
			try {
				image = ImageIO.read(new File("./Images/" + type + ".png"));
	        } catch (IOException e) {}
		}
	}
	
	public void display(Graphics g, int x, int y) {
		g.drawImage(image, x, y, width, height, null);
	}
}