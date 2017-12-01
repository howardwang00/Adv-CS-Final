import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Iterator;

public class Screen extends JPanel implements KeyListener {
	private Main main;
	private boolean playerMoveUp = false;
	private boolean playerMoveDown = false;
	private boolean playerMoveRight = false;
	private boolean playerMoveLeft = false;
	private TreeMap<Item, Integer> inventory;
	private ArrayList<Item> itemList;
	
    public Screen() {
    	this.setLayout(null);
    	this.setFocusable(true);
		addKeyListener(this);
    	
		main = new Main();
		inventory = new TreeMap<Item, Integer>();
		itemList = new ArrayList<Item>();
		itemList.add(new Dandelion(100, 100));
		itemList.add(new Ember(200, 400));
		itemList.add(new Dandelion(400, 100));
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
	
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 800, 600);
		
		Font arial = new Font("Arial", Font.PLAIN, 30);
		main.drawMe(g);
		displayInventory(g);
		
		Iterator<Item> iterator = itemList.iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			item.drawMe(g);
		}
    }
	public void animate()
    {
        //wait for .1 second
        while(true)
        {
            try {
                //Thread.sleep(10);
            	Thread.sleep(5);	//reduce repaint lag
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
			
			
			//Movement
			if(playerMoveUp == true) {
				main.moveUp();
			}
			if(playerMoveDown == true) {
				main.moveDown();
			}
			if(playerMoveRight == true) {
				main.moveRight();
			}
			if(playerMoveLeft == true) {
				main.moveLeft();
			}
			
			for(int i = 0; i < itemList.size(); i++) {
				if(itemList.get(i).getCollision(main, 0)) {
					addToInventory(itemList.remove(i));
					i--;
				}
			}
			
            repaint();
        }
    }
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 80) {	//Level Cheat Key
			
        }
        if(e.getKeyCode() == 38) {	//Up Arrow
			playerMoveUp = true;
        }
        if(e.getKeyCode() == 40) {	//Down Arrow
			playerMoveDown = true;
        }
		if(e.getKeyCode() == 37) {	//Left Arrow
			playerMoveLeft = true;
        }
		if(e.getKeyCode() == 39) {	//Right Arrow
			playerMoveRight = true;
        }
		if(e.getKeyCode() == 32) {	//Space Key, Interact: talk, pick up
			
		}
        repaint();
    }
	
    public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 38) {	//Up Arrow
			playerMoveUp = false;
        }
        if(e.getKeyCode() == 40) {	//Down Arrow
			playerMoveDown = false; 
        }
		if(e.getKeyCode() == 37) {	//Left Arrow
			playerMoveLeft = false;
        }
		if(e.getKeyCode() == 39) {	//Right Arrow
			playerMoveRight = false;
        }
	}
    public void keyTyped(KeyEvent e) {}
	
    public void displayInventory(Graphics g) {
		int x = 50;
		int y = 80;
		
		g.setColor(Color.black);
		g.drawString("Inventory", 50, 50);
		Font arial = new Font("Arial", Font.PLAIN, 15);
		g.setFont(arial);
		
		Iterator<Item> iterator = inventory.keySet().iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			item.drawMe(g, x - 50, y - 25);
			g.drawString("x" + inventory.get(item), x, y);
			
			y = y + 50;
		}
		
	}
    
    public void addToInventory(Item item) {
    	if(inventory.containsKey(item)) {
			inventory.put(item, inventory.get(item) + 1);
		} else {
			System.out.println("Adding new item");
			inventory.put(item, 1);
		}
    }
    
}