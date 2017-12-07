import java.awt.Graphics;

import java.io.File;
import java.io.FileReader;

import java.util.Iterator;
import java.util.HashSet;

public class Grid extends Movable {
	private HashSet<Tile> grid;
	
	public Grid() {
		x = -100;
		y = -100;
		grid = new HashSet<Tile>();
		
		File[] levelFiles = new File[2];	//Access File Objects
		levelFiles[0] = new File("./Levels/level1.txt");
		levelFiles[1] = new File("./Levels/level2.txt");
		
		FileReader reader;	//Read Files, Store chars into 2D Char Array
		char[][] levelChars = new char[2][1000];	//1000 Chars is Upper limit for tiles in a level
		try 
		{
			for(int i = 0; i < levelFiles.length; i++) {
				reader = new FileReader(levelFiles[i]);
				reader.read(levelChars[i]);
				reader.close();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		//LOADING LEVELS
		//Note: size of borders must be at least 8, because each tile is 50 tall and wide
		int x;
		int y;
		
		for(int i = 0; i < levelChars.length; i++) {
			System.out.println("Loading Level " + (i + 1));
			x = -100;
			y = -100;
			for(int j = 0; j < levelChars[i].length; j++) {
				char c = levelChars[i][j];
				if(c != '\0') {	//at the end of the file, \0 is null for char
					if(levelChars[i][j] == '\n') {
						//levelTiles.get(i).add(null);	//add a placeholder for the new line
						y += Tile.size;
						x = 0;
					} else {	//char is not a new line
						grid.add(new Tile(c, x, y));
						x = x + Tile.size;
					}
				} else {
					break;
				}
			}
		}
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
}
