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
    
    public void add(Item item) {
    	if(inventory.containsKey(item)) {
			inventory.put(item, inventory.get(item) + 1);
		} else {
			System.out.println("Adding new item");
			inventory.put(item, 1);
		}
    }
}
