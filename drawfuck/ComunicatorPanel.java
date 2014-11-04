import lejos.pc.comm.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class ComunicatorPanel extends JPanel implements ActionListener {

	private final static byte
		TRANSFER = 0,
		PLAY = 1,
		DRAW = 2,
		DISCONNECT = 3;

	private NXTComm nxtComm;
	private DrawPanel dp;

	private DataOutputStream dos;

	private boolean connected = false;

	private JButton connectButton, transferButton, disconnectButton;

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

		disconnectButton = new JButton("Disconnect");
		disconnectButton.setPreferredSize(new Dimension(140, 75));
		disconnectButton.setActionCommand("disconnect");
		disconnectButton.addActionListener(this);
		add(disconnectButton);
	}

	private void transfer() {
		boolean[][] dp.getBarray();
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
					}
					catch(NXTCommException e) {
						e.printStackTrace();
					}
				}
				break;
			case "transfer":
				break;
			case "disconnect":
				disconnect();
				break;
			default:
				break;
		}

		super.requestFocus();
	}
}
