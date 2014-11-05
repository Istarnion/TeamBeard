import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.UIManager.*;
import java.net.URL;
import java.net.URLClassLoader;

public class GUI {

	public GUI(int size) {
		//Sets look and style to Nimbus instead of Metal.
		try {
		    for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
		        if ("Nimbus".equals(info.getName())) {
		            setLookAndFeel(info.getClassName());
		            break;
		        }
		    }
		}
		catch (Exception e) {
		    System.out.println("Nimbus not available. falling back to Metal.");
		}

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
