import lejos.pc.comm.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class ComunicatorPanel extends JPanel implements ActionListener {

	private final static byte
		TRANSFER = 0,
		PLAY_LYDIAN = 1,
		PLAY_HARMONIC = 2,
		PLAY_CHROMATIC = 3,
		DRAW = 4,
		DISCONNECT = 5;

	private NXTComm nxtComm;
	private DrawPanel dp;

	private DataOutputStream dos;

	private boolean connected = false;

	private JButton 
		connectButton,
		transferButton,
		disconnectButton,
		lydianButton,
		harmonicButton,
		chromaticButton,
		drawButton;

	private JPanel playPanel;
	private JPanel subPlayPanel;
	private JLabel playLabel;

	JDialog waitingDialog;
	JLabel messageLabel;

	public ComunicatorPanel(int height, DrawPanel dp) {
		this.dp = dp;
		setPreferredSize(new Dimension(170, height));

		waitingDialog = new JDialog();
		messageLabel = new JLabel("Please wait..");
		waitingDialog.setTitle("Please wait..");
		waitingDialog.add(messageLabel);
		messageLabel.setPreferredSize(new Dimension(200, 100));
		waitingDialog.pack();
		waitingDialog.setLocationRelativeTo(null);

		connectButton = new JButton("Connect");
		connectButton.setPreferredSize(new Dimension(140, 75));
		connectButton.setActionCommand("connect");
		connectButton.addActionListener(this);

		add(connectButton);

		setupButtons();

		setButtonsActive(false);

		super.setFocusable(true);
		super.requestFocus();
	}

	private void setButtonsActive(boolean b) {
		transferButton.setEnabled(b);
		lydianButton.setEnabled(b);
		harmonicButton.setEnabled(b);
		chromaticButton.setEnabled(b);
		drawButton.setEnabled(b);
		disconnectButton.setEnabled(b);
	}

	private void disconnect() {
		setButtonsActive(false);

		try {
			dos.close();
			nxtComm.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}

		connected = false;
		connectButton.setEnabled(true);
		connectButton.setText("Connect");
	}

	private void setupButtons() {
		transferButton = new JButton("Transfer");
		transferButton.setPreferredSize(new Dimension(140, 75));
		transferButton.setActionCommand("transfer");
		transferButton.addActionListener(this);
		add(transferButton);

		//Play buttons:
		playPanel = new JPanel();
		playPanel.setPreferredSize(new Dimension(175, 140));
		subPlayPanel = new JPanel();
		subPlayPanel.setPreferredSize(new Dimension(100, 140));

		lydianButton = new JButton("Lydian");
		lydianButton.setPreferredSize(new Dimension(100, 40));
		lydianButton.setActionCommand("lydian");
		lydianButton.addActionListener(this);
		subPlayPanel.add(lydianButton);

		harmonicButton = new JButton("Harmonic");
		harmonicButton.setPreferredSize(new Dimension(100, 40));
		harmonicButton.setActionCommand("harmonic");
		harmonicButton.addActionListener(this);
		subPlayPanel.add(harmonicButton);

		chromaticButton = new JButton("Chromatic");
		chromaticButton.setPreferredSize(new Dimension(100, 40));
		chromaticButton.setActionCommand("chromatic");
		chromaticButton.addActionListener(this);
		subPlayPanel.add(chromaticButton);

		playLabel = new JLabel("Play:");
		playPanel.add(playLabel);
		playPanel.add(subPlayPanel);
		add(playPanel);

		drawButton = new JButton("Draw");
		drawButton.setPreferredSize(new Dimension(140, 75));
		drawButton.setActionCommand("draw");
		drawButton.addActionListener(this);
		add(drawButton);

		disconnectButton = new JButton("Disconnect");
		disconnectButton.setPreferredSize(new Dimension(140, 75));
		disconnectButton.setActionCommand("disconnect");
		disconnectButton.addActionListener(this);
		add(disconnectButton);
	}

	private void transfer() {
		boolean[][] barray = dp.getBarray();

		sendByte(TRANSFER);
		try {
			for(int x=0; x<barray.length; x++) {
				for(int y=0; y<barray[0].length; y++) {
					dos.writeBoolean(barray[x][y]);
				}
			}
			dos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	private void sendByte(byte b) {
		try {
			dos.writeByte(b);
			dos.flush();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		switch(ae.getActionCommand()) {
			case "connect":
				if(!connected) {
					waitingDialog.setVisible(true);
					try {
						nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
						NXTInfo[] nxtInfo = nxtComm.search("NXT");

						for(NXTInfo ni : nxtInfo) {
							System.out.println("NXT: "+ni.name+", "+ni.deviceAddress);
							nxtComm.open(ni);
							dos = new DataOutputStream(nxtComm.getOutputStream());
						}
						connectButton.setText("Connected...");
						connectButton.setEnabled(false);
						connected = true;
						setButtonsActive(true);
					}
					catch(NXTCommException ie) {
						messageLabel.setText("Bluetooth not available.");
						try {
							Thread.sleep(4000);
						}
						catch(InterruptedException e) {
							ie.printStackTrace();
						}
					}
					waitingDialog.setVisible(false);
				}
				break;
			case "transfer":
				transfer();
				break;
			case "disconnect":
				disconnect();
				break;
			case "lydian":
				sendByte(PLAY_LYDIAN);
				break;
			case "harmonic":
				sendByte(PLAY_HARMONIC);
				break;
			case "chromatic":
				sendByte(PLAY_CHROMATIC);
				break;								
			case "draw":
				sendByte(DRAW);
				break;
			default:
				break;
		}

		super.requestFocus();
	}
}
