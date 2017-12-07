import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public abstract class Movable {
	protected double x;
	protected double y;
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
}