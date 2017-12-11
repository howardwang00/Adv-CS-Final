import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Enemy extends Movable {
	private int xRelative;
	private boolean moveRight;
	private static final int distance = 400;
	
	public Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 50;
		this.height = 50;
		this.initialX = x;
		this.initialY = y;
		
		this.xRelative = (int)(Math.random() * 400);
		this.moveRight = true;
		
		try {
			image = ImageIO.read(new File("./Images/enemy.png"));
        } catch (IOException e) {}
	}
	
	public void move() {
		if(xRelative > 400) {
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
