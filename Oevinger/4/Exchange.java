import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;

class Exchange extends JFrame implements ActionListener {

	private Valuta usd, eur, sek;

	private JPanel panel;
	private JComboBox<Valuta> chooser;
	private JButton b1, b2;
	private JTextField field1, field2;
	private JLabel label1, label2;

	private DecimalFormat format;

	public Exchange() {
		super("Valuta");

		format = new DecimalFormat("0.00##");

		// Set up valuta:
		usd = new Valuta("US Dollar", "USD", 6.28215679);
		eur = new Valuta("Euro", "EUR", 8.13646101);
		sek = new Valuta("Svenske kroner", "SEK", 0.885193585);

		// GUI:
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		GridLayout layout = new GridLayout(3, 3, 20, 10);

		panel = new JPanel();
		panel.setPreferredSize(new Dimension(400, 115));
		layout.preferredLayoutSize(panel);
		panel.setLayout(layout);

		setupGUI();

		super.add(panel);
		super.pack();
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}

	private void setupGUI() {
		Valuta valutas[] = {usd, eur, sek};
		chooser = new JComboBox<Valuta>(valutas);
		chooser.addActionListener(this);
		panel.add(chooser);

		//Fill up empty spaces
		panel.add(new JLabel());
		panel.add(new JLabel());

		field1 = new JTextField();
		field2 = new JTextField();
		b1 = new JButton("Til NOK");
		b2 = new JButton("Til "+((Valuta)chooser.getSelectedItem()).getCode());
		label1 = new JLabel();
		label2 = new JLabel();

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event ) {  
				label1.setText(format.format(((Valuta)chooser.getSelectedItem()).toKr(getValue(field1)))+" kr");
 		 	} 
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event ) {  
				label2.setText(format.format(((Valuta)chooser.getSelectedItem()).toVal(getValue(field2)))+" "+((Valuta)chooser.getSelectedItem()).getCode());
 		 	}
		});

		panel.add(field1);
		
		panel.add(b1);
		
		panel.add(label1);
		
		panel.add(field2);
		
		panel.add(b2);
		
		panel.add(label2);
	}

	private double getValue(JTextField f) {
		double d = 0;
		try {
			d = Double.parseDouble(f.getText());
		}
		catch(Exception e) {
			return 0;
		}

		return d;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// Clear
		label1.setText("");
		label2.setText("");
		b2.setText("Til "+((Valuta)chooser.getSelectedItem()).getCode());
	}

	public static void main(String[] args) {
		new Exchange();
	}
}
