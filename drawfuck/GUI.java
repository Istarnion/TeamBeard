import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
	


	public GUI(int size) {
		JFrame frame = new JFrame("DRAWDUCK");
		JPanel container = new JPanel();
		DrawPanel dp = new DrawPanel(size, size);
		OptionPanel op = new OptionPanel(size, dp);
		op.addKeyListener(dp);
		ComunicatorPanel cp = new ComunicatorPanel(size, dp);
		cp.addKeyListener(dp);

		container.add(op);
		container.add(dp);
		container.add(cp);

		container.addKeyListener(dp);
		frame.add(container);

		frame.addKeyListener(dp);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new GUI(704);
	}
}
