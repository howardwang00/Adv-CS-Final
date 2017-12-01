import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Dandelion extends Item implements Comparable<Item> {
	public Dandelion(int x, int y) {
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
		
		try {
			image = ImageIO.read(new File("./Images/dandelion.png"));
        } catch (IOException e) {}
		
	}
	public String toString() {
		return "Dandelion";
	}
	@Override
	public int compareTo(Item item) {
		// TODO Auto-generated method stub
		return toString().compareTo(item.toString());
	}
}