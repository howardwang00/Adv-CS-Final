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
	private Grid grid;
	private boolean gameOver;
	private boolean startScreen = true;
	
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
		
		if(startScreen) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 60));
			g.drawString("Bridge Builder", 200, 100);
			g.setFont(arial);
			g.drawString("Arrow Keys to Move", 50, 250);
			g.drawString("Collect items to complete the level.", 50, 300);
			g.drawString("Avoid enemies. Collect fruit for health.", 50, 350);
			g.drawString("Press Space to Start", 200, 500);
		} else if(gameOver) {
			g.setColor(Color.BLUE);
			g.fillRect(0, 0, 800, 600);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.PLAIN, 60));
			g.drawString("Blue Screen of", 50, 100);
			g.drawString("Game Over :(", 150, 200);
			g.setFont(arial);
			g.drawString("Press Space to Restart", 200, 500);
			
		} else {
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
			
			g.drawString("Level: " + grid.level(), 10, 30);
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
			
            if(!startScreen && !gameOver) {
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
    				System.out.println("Item List is Empty - Next Level");
    				nextLevel();
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
    					main.hit(5);
    					i--;
    				}
    			}
    			
    			
    			if(grid.checkObstacle(main)) {
    				reset();
    			}
    			
    			if(main.defeated()) {
    				gameOver();
    			}
    			
                repaint();
            }
        }
    }
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == 80) {	//Level Cheat Key (p)
			System.out.println("Level Skipped");
			itemList = new ArrayList<Item>();
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
		if(e.getKeyCode() == 32) {	//Space Key
			if(startScreen) {
				startGame();
				startScreen = false;
			} else if(gameOver) {
				startGame();
				
				try {
	            	Thread.sleep(100);	//wait for restart
	            } catch(InterruptedException ex) {
	                Thread.currentThread().interrupt();
	            }
			}
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
	
    public void nextLevel() {
    	if(grid.nextLevel()) {
    		System.out.println("Game Over");
    		gameOver();
    		return;
    	}
    	instantiateLevel(grid.level());
    }
    
    public void reset() {
    	for(int i = 0; i < itemList.size(); i++) {
			itemList.get(i).reset();
		}
		
    	for(int i = 0; i < foodList.size(); i++) {
			foodList.get(i).reset();
		}
    	
    	for(int i = 0; i < enemyList.size(); i++) {
			enemyList.get(i).reset();
		}
    }
    
    public void startGame() {
    	grid = new Grid();
    	main = new Main();
    	
		instantiateLevel(1);
		gameOver = false;
    }
    
    public void gameOver() {
    	inventory = new Inventory();
    	itemList = new ArrayList<Item>();
    	foodList = new ArrayList<Food>();
		enemyList = new ArrayList<Enemy>();
		
		gameOver = true;
    }
    
    public void instantiateLevel(int level) {
    	inventory = new Inventory();
    	itemList = new ArrayList<Item>();
		foodList = new ArrayList<Food>();
		enemyList = new ArrayList<Enemy>();
		
    	if(level == 1) {
    		itemList.add(new Item(Item.ember, 700, 100));
    		itemList.add(new Item(Item.ember, 150, 100));
    		itemList.add(new Item(Item.ember, 200, 100));
    		itemList.add(new Item(Item.ember, 250, 100));
    		
    		itemList.add(new Item(Item.wood, 100, 0));
    		itemList.add(new Item(Item.wood, 0, 200));
    		itemList.add(new Item(Item.wood, 50, 400));
    		
    		itemList.add(new Item(Item.steel, 300, 500));
    		itemList.add(new Item(Item.steel, 200, 400));
    		
    		itemList.add(new Item(Item.concrete, 700, 450));
    		itemList.add(new Item(Item.concrete, 500, 250));
    		itemList.add(new Item(Item.concrete, 100, 100));
    		
    		itemList.add(new Item(Item.paint, 800, 400));
    		itemList.add(new Item(Item.paint, 700, -50));
    		
    		foodList.add(new Food(200, 300));
    		foodList.add(new Food(400, 300));
    		foodList.add(new Food(800, 200));
    		foodList.add(new Food(0, 400));
    		
    		enemyList.add(new Enemy(100, 50));
    		enemyList.add(new Enemy(500, 150));
    		enemyList.add(new Enemy(400, 450));
    		enemyList.add(new Enemy(300, -50));
    		enemyList.add(new Enemy(200, 0));
    	} else if(level == 2) {
    		int y = 200;
    		for(int i = 0; i < 10; i++) {
    			itemList.add(new Item(Item.ember, 400, y));
    			y -= 50;
    		}
    		
    		itemList.add(new Item(Item.wood, 150, -100));
    		itemList.add(new Item(Item.wood, 0, 200));
    		itemList.add(new Item(Item.wood, 50, 100));
    		
    		itemList.add(new Item(Item.steel, 300, -100));
    		itemList.add(new Item(Item.steel, 200, -300));
    		
    		itemList.add(new Item(Item.concrete, 300, 100));
    		itemList.add(new Item(Item.concrete, 500, 250));
    		itemList.add(new Item(Item.concrete, 150, 100));
    		
    		itemList.add(new Item(Item.paint, 800, 200));
    		itemList.add(new Item(Item.paint, 700, -50));
    		
    		foodList.add(new Food(200, 0));
    		foodList.add(new Food(500, -100));
    		foodList.add(new Food(800, -200));
    		foodList.add(new Food(0, -250));
    		foodList.add(new Food(800, 150));
    		foodList.add(new Food(850, 150));
    		foodList.add(new Food(850, 200));
    		
    		int x = 200;
    		y = 150;
    		for(int i = 0; i < 5; i++) {
    			x = (int)(Math.random() * 100) + 300;
    			enemyList.add(new Enemy(x, y));
    			x = (int)(Math.random() * 100) + x;
    			enemyList.add(new Enemy(x, y));
    			y -= 100;
    		}
    	}
    }
    
}