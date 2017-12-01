import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage image;
	
	public Main() {
		try
        {
			image = ImageIO.read(new File("./Images/doge.png"));
        } catch (IOException e) {}
		
		
		width = 100;
		height = 100;
		x = 400 - width/2;
		y = 300 - height/2;
	}
	
	public void drawMe(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
		g.setColor(Color.black);
		g.drawString("You", x + width/2 - 15, y + height + 10);
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getHeight() {
		return height;
	}
	public int getWidth() {
		return width;
	}
	
	public void moveUp() {
		y -= 3;
    }
     
    public void moveDown() {
		y += 3;
	}
    
    public void moveLeft() {
		x -= 3;
    }
     
    public void moveRight() {
		x += 3;
	}
    
}