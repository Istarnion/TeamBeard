import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class OptionPanel extends JPanel implements ActionListener {

	private DrawPanel drawPanel;

	private JButton colorButton;

	public OptionPanel(int height, DrawPanel drawPanel) {
		this.drawPanel = drawPanel;
		setPreferredSize(new Dimension(150, height));

		JButton button = new JButton("Clear");
		button.setActionCommand("clear");
		button.addActionListener(this);
		this.add(button);

		button = new JButton("Invert");
		button.setActionCommand("invert");
		button.addActionListener(this);
		this.add(button);

		super.setFocusable(true);
		super.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
			case "clear":
				drawPanel.clear();
				break;
			case "invert":
				drawPanel.invert();
				break;
			default:
				System.out.println("DUCKKNIGGET!");
				break;
		}
		super.requestFocus();
	}
}
