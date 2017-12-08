import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Movable {
	private int xRelative;
	private boolean moveRight;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 50;
		this.height = 50;
		
		this.xRelative = (int)(Math.random() * 100);
		this.moveRight = true;
		
		try {
			image = ImageIO.read(new File("./Images/enemy.png"));
        } catch (IOException e) {}
	}
	
	public void move() {
		if(xRelative > 200) {
			moveRight = false;
		} else if(xRelative < 0) {
			moveRight = true;
		}
		
		if(moveRight) {
			x += 1;
			xRelative += 1;
		} else {
			x -= 1;
			xRelative -= 1;
		}
	}
}
