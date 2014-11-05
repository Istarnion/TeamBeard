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

		button = new JButton("Noise");
		button.setActionCommand("noise");
		button.addActionListener(this);
		this.add(button);

		button = new JButton("Save");
		button.setActionCommand("save");
		button.addActionListener(this);
		this.add(button);

		ButtonGroup bg = new ButtonGroup();
		JRadioButton rButton = new JRadioButton("Black");
		rButton.setActionCommand("black");
		rButton.addActionListener(this);
		rButton.setSelected(true);
		bg.add(rButton);
		this.add(rButton);

		rButton = new JRadioButton("White");
		rButton.setActionCommand("white");
		rButton.addActionListener(this);
		bg.add(rButton);
		this.add(rButton);

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
			case "noise":
				drawPanel.noise();
				break;
			case "black":
				drawPanel.setDrawColor(true);
				break;
			case "white":
				drawPanel.setDrawColor(false);
				break;
			case "save":
				drawPanel.saveFile();
			default:
				System.out.println("DUCKKNIGGET!");
				break;
		}
		super.requestFocus();
	}
}
