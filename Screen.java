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
	private int level = 1;
	private Grid grid;
	
	private Main main;
	private boolean playerMoveUp = false;
	private boolean playerMoveDown = false;
	private boolean playerMoveRight = false;
	private boolean playerMoveLeft = false;
	
	private Inventory inventory;
	private ArrayList<Item> itemList;
	private ArrayList<Food> foodList;
	private ArrayList<Enemy> enemyList;
	
    public Screen() {
    	this.setLayout(null);
    	this.setFocusable(true);
		addKeyListener(this);
    	
		grid = new Grid();
		
		main = new Main();
		inventory = new Inventory();
		itemList = new ArrayList<Item>();
		itemList.add(new Item(Item.ember, 700, 100));
		itemList.add(new Item(Item.ember, 150, 100));
		itemList.add(new Item(Item.ember, 200, 100));
		itemList.add(new Item(Item.ember, 250, 100));
		itemList.add(new Item(Item.wood, 100, 0));
		
		itemList.add(new Item(Item.wood, 100, 200));
		itemList.add(new Item(Item.wood, 100, 500));
		itemList.add(new Item(Item.steel, 300, 500));
		itemList.add(new Item(Item.steel, 200, 400));
		itemList.add(new Item(Item.concrete, 700, 500));
		itemList.add(new Item(Item.concrete, 800, 600));
		itemList.add(new Item(Item.concrete, 100, 100));
		itemList.add(new Item(Item.paint, 200, 500));
		
		foodList = new ArrayList<Food>();
		foodList.add(new Food(200, 300));
		foodList.add(new Food(400, 300));
		foodList.add(new Food(800, 200));
		foodList.add(new Food(100, 600));
		
		enemyList = new ArrayList<Enemy>();
		enemyList.add(new Enemy(100, 50));
		enemyList.add(new Enemy(100, 150));
		enemyList.add(new Enemy(100, 250));
    }
    public Dimension getPreferredSize() {
        //Sets the size of the panel
        return new Dimension(800,600);
    }
	
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		
		Font arial = new Font("Arial", Font.PLAIN, 30);
		
		grid.display(g);
		
		//Draw items
		Iterator<Item> iterator = itemList.iterator();
		while(iterator.hasNext()) {
			iterator.next().drawMe(g);
		}
		
		//Draw food
		Iterator<Food> foodIterator = foodList.iterator();
		while(foodIterator.hasNext()) {
			foodIterator.next().drawMe(g);
		}
		
		//Draw enemies
		Iterator<Enemy> enemyIterator = enemyList.iterator();
		while(enemyIterator.hasNext()) {
			enemyIterator.next().drawMe(g);
		}
		
		main.drawMe(g);
		inventory.display(g);
		
		g.drawString("Level: " + level, 10, 30);
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
				grid.moveDown();
				
				Iterator<Item> iterator = itemList.iterator();
				while(iterator.hasNext()) {
					iterator.next().moveDown();
				}
				
				Iterator<Food> foodIterator = foodList.iterator();
				while(foodIterator.hasNext()) {
					foodIterator.next().moveDown();
				}
				
				Iterator<Enemy> enemyIterator = enemyList.iterator();
				while(enemyIterator.hasNext()) {
					enemyIterator.next().moveDown();
				}
			}
			if(playerMoveDown == true) {
				grid.moveUp();
				
				Iterator<Item> iterator = itemList.iterator();
				while(iterator.hasNext()) {
					iterator.next().moveUp();
				}
				
				Iterator<Food> foodIterator = foodList.iterator();
				while(foodIterator.hasNext()) {
					foodIterator.next().moveUp();
				}
				
				Iterator<Enemy> enemyIterator = enemyList.iterator();
				while(enemyIterator.hasNext()) {
					enemyIterator.next().moveUp();
				}
			}
			if(playerMoveRight == true) {
				grid.moveLeft();
				
				Iterator<Item> iterator = itemList.iterator();
				while(iterator.hasNext()) {
					iterator.next().moveLeft();
				}
				
				Iterator<Food> foodIterator = foodList.iterator();
				while(foodIterator.hasNext()) {
					foodIterator.next().moveLeft();
				}
				
				Iterator<Enemy> enemyIterator = enemyList.iterator();
				while(enemyIterator.hasNext()) {
					enemyIterator.next().moveLeft();
				}
			}
			if(playerMoveLeft == true) {
				grid.moveRight();
				
				Iterator<Item> iterator = itemList.iterator();
				while(iterator.hasNext()) {
					iterator.next().moveRight();
				}
				
				Iterator<Food> foodIterator = foodList.iterator();
				while(foodIterator.hasNext()) {
					foodIterator.next().moveRight();
				}
				
				Iterator<Enemy> enemyIterator = enemyList.iterator();
				while(enemyIterator.hasNext()) {
					enemyIterator.next().moveRight();
				}
			}
			
			if(itemList.isEmpty()) {
				startLevel2();
			} else {
				for(int i = 0; i < itemList.size(); i++) {
					if(itemList.get(i).getCollision(main)) {
						inventory.add(itemList.remove(i));
						i--;
					}
				}
			}
			
			for(int i = 0; i < foodList.size(); i++) {
				if(foodList.get(i).getCollision(main)) {
					foodList.remove(i);
					main.heal();
					i--;
				}
			}
			
			for(int i = 0; i < enemyList.size(); i++) {
				enemyList.get(i).move();
				if(enemyList.get(i).getCollision(main)) {
					enemyList.remove(i);
					main.hit(2);
					i--;
				}
			}
			
			
			grid.checkObstacle(main);
			
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
	
    public void startLevel2() {
    	System.out.println("Level 2");
    	level = 2;
    	inventory = new Inventory();
    	itemList = new ArrayList<Item>();
    	itemList.add(new Item(Item.ember, 250, 100));
		itemList.add(new Item(Item.wood, 100, 0));
		
		itemList.add(new Item(Item.wood, 100, 200));
		itemList.add(new Item(Item.wood, 100, 500));
		itemList.add(new Item(Item.steel, 300, 500));
		itemList.add(new Item(Item.steel, 200, 400));
		itemList.add(new Item(Item.concrete, 700, 500));
		itemList.add(new Item(Item.concrete, 800, 900));
		itemList.add(new Item(Item.concrete, 100, 100));
		itemList.add(new Item(Item.paint, 200, 500));
		
		foodList = new ArrayList<Food>();
		enemyList = new ArrayList<Enemy>();
    	
    }
    
}