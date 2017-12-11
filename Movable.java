import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Movable {
	protected double x;
	protected double y;
	protected int initialX;
	protected int initialY;
	protected int width;
	protected int height;
	protected BufferedImage image;
	
	public void drawMe(Graphics g) {
		g.drawImage(image, (int)x, (int)y, width, height, null);
	}
	public void moveUp() {
		y -= 1.5;
	}
	public void moveDown() {
		y += 1.5;
	}
	public void moveRight() {
		x += 1.5;
	}
	public void moveLeft() {
		x -= 1.5;
	}
	
	public boolean getCollision(Main main) {
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
	
	public void reset() {
		this.x = initialX;
		this.y = initialY;
	}
}