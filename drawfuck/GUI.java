import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	
	public GUI() {
		JFrame frame = new JFrame("DRAWDUCK");
		JPanel container = new JPanel();
		container.add(new OptionPanel(704));
		container.add(new DrawPanel(704, 704));
		container.add(new ComunicatorPanel(704));

		frame.add(container);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GUI();
	}
}
