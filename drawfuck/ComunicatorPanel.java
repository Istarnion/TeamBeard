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

	private JButton connectButton, transferButton, disconnectButton, playButton, drawButton;

	public ComunicatorPanel(int height, DrawPanel dp) {
		this.dp = dp;
		setPreferredSize(new Dimension(150, height));

		connectButton = new JButton("Connect");
		connectButton.setPreferredSize(new Dimension(140, 75));
		connectButton.setActionCommand("connect");
		connectButton.addActionListener(this);

		add(connectButton);

		super.setFocusable(true);
		super.requestFocus();
	}

	private void disconnect() {
		remove(transferButton);
		remove(disconnectButton);
		remove(playButton);
		remove(drawButton);

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

		playButton = new JButton("Play Lydian");
		playButton.setPreferredSize(new Dimension(46, 75));
		playButton.setActionCommand("lydian");
		playButton.addActionListener(this);
		add(playButton);

		playButton = new JButton("Play Harmonic");
		playButton.setPreferredSize(new Dimension(46, 75));
		playButton.setActionCommand("harmonic");
		playButton.addActionListener(this);
		add(playButton);

		playButton = new JButton("Play Chromatic");
		playButton.setPreferredSize(new Dimension(46, 75));
		playButton.setActionCommand("chromatic");
		playButton.addActionListener(this);
		add(playButton);

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
						setupButtons();
					}
					catch(NXTCommException e) {
						e.printStackTrace();
					}
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
