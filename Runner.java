import javax.swing.JFrame;

public class Runner {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Howard's Scavenger Hunt");
		Screen screen = new Screen();
		frame.add(screen);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
		screen.animate();
	}
}
