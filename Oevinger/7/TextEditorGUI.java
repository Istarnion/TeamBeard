import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class TextEditorGUI extends JFrame {

	TextEditor editor;

	JPanel panel;

	JPanel inputPanel;
	JPanel outputPanel;
	JPanel buttonPanel;

	JTextArea inputArea;
	JTextArea outputArea;

	JScrollPane inputScroll;
	JScrollPane outputScroll;

	JButton refresh;

	JButton replace;
	JTextField toReplace;
	JTextField replaceWith;

	public TextEditorGUI() {
		super("TextEditor");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Main panel
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(450, 525));

		// Input space
		inputPanel = new JPanel();
		inputPanel.setPreferredSize(new Dimension(450, 230));

		inputArea = new JTextArea("Input your text here", 14, 40);
		inputArea.setLineWrap(true);

		inputScroll = new JScrollPane(inputArea);

		inputPanel.add(inputScroll);

		// Output space
		outputPanel = new JPanel();
		outputPanel.setPreferredSize(new Dimension(450, 230));

		outputArea = new JTextArea("OutputField", 14, 40);
		outputArea.setLineWrap(true);
		outputArea.setEditable(false);

		outputScroll = new JScrollPane(outputArea);

		outputPanel.add(outputScroll);

		// Button space
		buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(450, 50));

		refresh = new JButton("ANALYZE");
		refresh.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				refresh();
			}
		});

		toReplace = new JTextField("Replace", 10);
		replaceWith = new JTextField("With", 10);
		replace = new JButton("REPLACE");
		replace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				replace();
			}
		});

		buttonPanel.add(refresh);
		buttonPanel.add(toReplace);
		buttonPanel.add(replaceWith);
		buttonPanel.add(replace);

		panel.add(inputPanel);
		panel.add(outputPanel);
		panel.add(buttonPanel);

		add(panel);
		pack();
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void refresh() {
		editor = new TextEditor(inputArea.getText());
		outputArea.setText("");
		outputArea.append("Word count: "+editor.numWords());
		outputArea.append("\nAverage word length: "+editor.averageWordLength());
		outputArea.append("\nAverage number of words per sentence: "+editor.averageWordsInSentence());
		outputArea.append("\n\nInput: \n"+editor.getText());
		outputArea.append("\n\nInput in upper case: \n"+editor.getTextToUpperCase());
	}

	private void replace() {
		editor = new TextEditor(inputArea.getText());
		inputArea.setText(editor.replace(toReplace.getText(), replaceWith.getText()));

		refresh();
	}

	public static void main(String[] args) {
		new TextEditorGUI();
	}
}
