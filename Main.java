import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.util.Stack;

public class Main {
	private int x;
	private int y;
	private int width;
	private int height;
	private BufferedImage image;
	
	private Stack<Integer> healthBar;
	private static final double maxHealth = 20.0;
	
	public Main() {
		try
        {
			image = ImageIO.read(new File("./Images/main.png"));
        } catch (IOException e) {}
		
		
		width = 50;
		height = 50;
		x = 400 - width/2;
		y = 300 - height/2;
		
		healthBar = new Stack<Integer>();
		for(int i = 0; i < maxHealth; i++) {
			healthBar.add(1);
		}
	}
	
	public void drawMe(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
		g.setColor(Color.white);
		g.drawString("You", x + width/4, y + height + 20);
		
		int healthX = x;
		int healthY = y - 10;
		g.setColor(Color.BLACK);
		g.drawRect(healthX, healthY, 50, 15);
		g.setColor(Color.RED);
		g.fillRect(healthX, healthY, 50, 15);
		g.setColor(Color.GREEN);
		
		double healthWidth = (healthBar.size() / maxHealth) * 50;
		g.fillRect(healthX, healthY, (int)healthWidth, 15);	//Current Health
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
    
    public void hit(int damage) {
    	for(int i = 0; i < damage; i++) {
    		if(healthBar.size() > 0) {
    			healthBar.pop();
    		}
    	}
    }
    
    public void heal() {
    	healthBar.add(1);
    	healthBar.add(1);
    	while(healthBar.size() > maxHealth) {
    		healthBar.pop();
    	}
    }
    
    public void recover() {
    	while(healthBar.size() < maxHealth) {
    		healthBar.add(1);
    	}
    }
    
    public boolean defeated() {
    	return healthBar.isEmpty();
    }
    
}