import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ember extends Item implements Comparable<Item> {
	public Ember(int x, int y) {
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
		
		try {
			image = ImageIO.read(new File("./Images/ember.png"));
        } catch (IOException e) {}
		
	}
	public String toString() {
		return "Ember";
	}
	@Override
	public int compareTo(Item item) {
		// TODO Auto-generated method stub
		return toString().compareTo(item.toString());
	}
}