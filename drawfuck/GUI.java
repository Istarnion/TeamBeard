
import javax.swing.JFrame;

public class GUI {

	
	public GUI() {
		JFrame frame = new JFrame("Drawing");
		frame.add(new DrawPanel(462, 715));
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GUI();
	}
}
