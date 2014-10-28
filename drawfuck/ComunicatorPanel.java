import lejos.pc.comm.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class ComunicatorPanel extends JPanel {

	private NXTComm nxtComm;

	public ComunicatorPanel(int height) {
		setPreferredSize(new Dimension(150, height));

		JButton connectButton = new JButton("Connect");
		connectButton.setPreferredSize(new Dimension(140, 75));
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					NXTComm nxtComm = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
					NXTInfo[] nxtInfo = nxtComm.search("NXT");

					for(NXTInfo ni : nxtInfo) {
						System.out.println("NXT: "+nxtInfo);
						nxtComm.open(ni);
						DataOutputStream dos = new DataOutputStream(nxtComm.getOutputStream());
						do {
							String s = JOptionPane.showInputDialog("Please input frequency");

							if(s.equals("exit")) {
								dos.writeInt(0);
								dos.flush();
								break;
							}
							else {
								dos.writeInt(Integer.parseInt(s));
								dos.flush();
							}

						}
						while(true);
						nxtComm.close();
					}
				}
				catch(NXTCommException e) {
					e.printStackTrace();
				}
				catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});

		add(connectButton);
	}


}
