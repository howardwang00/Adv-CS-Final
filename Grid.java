import java.awt.Graphics;

import java.io.File;
import java.io.FileReader;

import java.util.HashSet;
import java.util.Iterator;

public class Grid {
	private HashSet<Tile> grid;
	private File[] levelFiles;
	private int level;
	
	private static int x;
	private static int y;
	
	public Grid() {
		grid = new HashSet<Tile>();
		
		levelFiles = new File[2];	//Access File Objects
		levelFiles[0] = new File("./Levels/level1.txt");
		levelFiles[1] = new File("./Levels/level2.txt");
		
		level = 1;
		x = -300;
		y = -300;
		loadLevel();
	}
	
	private void loadLevel() {
		//Loading chars from file
		
		FileReader reader;	//Read Files, Store chars into 2D Char Array
		char[] levelChars = new char[1000];	//1000 Chars is Upper limit for tiles in a level
		try 
		{
			reader = new FileReader(levelFiles[level - 1]);
			reader.read(levelChars);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//LOADING TILES OF LEVELS
		//Note: size of borders must be at least 8 to make sure player does not see whitespace
		//each tile is 50 tall and wide
		
		System.out.println("Loading Level " + level);
		int tileX = x;
		int tileY = y;
		for(int i = 0; i < levelChars.length; i++) {
			char c = levelChars[i];
			if(c != '\0') {	//at the end of the file, \0 is null for char
				if(levelChars[i] == '\n') {
					//levelTiles.get(i).add(null);	//add a placeholder for the new line
					tileY += Tile.size;
					tileX = x;
				} else if(c == 'o') {	//char is not a new line
					grid.add(new Obstacle(c, tileX, tileY));
					tileX = tileX + Tile.size;
				} else {	//char is not a new line
					grid.add(new Tile(c, tileX, tileY));
					tileX = tileX + Tile.size;
				}
			} else {
				break;
			}
		}
	}
	
	public boolean nextLevel() {
		if(level == 2) {
			return true;
		} else {
			grid = new HashSet<Tile>();
			level++;
			
			if(level == 2) {
				x = -300;
				y = -500;
			}
			
			loadLevel();
			return false;
		}
	}
	
	public int level() {
		return level;
	}
	
	public void display(Graphics g) {
		Iterator<Tile> iterator = grid.iterator();
		while(iterator.hasNext()) {
			iterator.next().drawMe(g);
		}
	}
	
	public void moveUp() {
		Iterator<Tile> iterator = grid.iterator();
		while(iterator.hasNext()) {
			iterator.next().moveUp();
		}
	}
	public void moveDown() {
		Iterator<Tile> iterator = grid.iterator();
		while(iterator.hasNext()) {
			iterator.next().moveDown();
		}
	}
	public void moveRight() {
		Iterator<Tile> iterator = grid.iterator();
		while(iterator.hasNext()) {
			iterator.next().moveRight();
		}
	}
	public void moveLeft() {
		Iterator<Tile> iterator = grid.iterator();
		while(iterator.hasNext()) {
			iterator.next().moveLeft();
		}
	}
	
	public boolean checkObstacle(Main main) {
		Iterator<Tile> iterator = grid.iterator();
		while(iterator.hasNext()) {
			Tile tile = iterator.next();
			if(tile.type().equals(Tile.obstacle)) {
				Obstacle obstacle = (Obstacle)tile;
				if(obstacle.getCollision(main, 0)) {
					main.hit(1);
					return true;
				}
			}
		}
		return false;
	}
	
	public void reset() {
		Iterator<Tile> iterator = grid.iterator();
		while(iterator.hasNext()) {
			iterator.next().reset();
		}
	}
}
