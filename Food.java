import java.awt.Graphics;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Food extends Movable {
	
	public Food(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 50;
		this.height = 50;
		
		try {
			image = ImageIO.read(new File("./Images/food.png"));
        } catch (IOException e) {}
	}
	
	public void drawMe(Graphics g) {
		g.drawImage(image, (int)(this.x), (int)(this.y), width, height, null);
	}
	
}