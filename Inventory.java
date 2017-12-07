import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.TreeMap;

public class Inventory {
	private TreeMap<Item, Integer> inventory;
	
	public Inventory() {
		inventory = new TreeMap<Item, Integer>();
	}
	
	public void display(Graphics g) {
		int x = 600;
		int y = 0;
		
		g.setColor(Color.black);
		g.fillRect(x, y, 200, 600);
		
		g.setColor(Color.white);
		Font font = new Font("Arial", Font.PLAIN, 30);
		g.setFont(font);
		g.drawString("Inventory", x + 30, y + 40);
		y += 100;
		
		Iterator<Item> iterator = inventory.keySet().iterator();
		while(iterator.hasNext()) {
			Item item = iterator.next();
			item.displayImage(g, x + 20, y);
			g.drawString("x" + inventory.get(item), x + 80, y + 40);
			
			y = y + 75;
		}
	}
    
    public void add(Item item) {
    	if(inventory.containsKey(item)) {
			inventory.put(item, inventory.get(item) + 1);
		} else {
			inventory.put(item, 1);
		}
    }
}
