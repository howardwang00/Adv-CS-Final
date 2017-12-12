
public class Obstacle extends Tile {
	public Obstacle(char c, int x, int y) {
		super(c, x, y);
	}
	
	@Override
	public boolean getCollision(Main main) {
		int mainX = main.getX();
		int mainY = main.getY();
		int mainHeight = main.getHeight() - 5;
		int mainWidth = main.getWidth() - 15;
		
		int objectHeight = height - 30;
		int objectWidth = width - 8;
		
		if(mainX + mainWidth > x && mainX < x + objectWidth && mainY + mainHeight > y && mainY < y + objectHeight) {
			return true;
		}
		return false;
	}
}
