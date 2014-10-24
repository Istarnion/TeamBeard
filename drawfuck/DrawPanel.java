
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private static final long serialVersionUID = 8978779502294174479L;

	private BufferedImage img;
	private boolean[][] barray;
	private boolean saving = false;
	
	private double pxWidth, pxHeight;
	
	public DrawPanel(int w, int h) {
		super.setPreferredSize(new Dimension(w, h));
		img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
		
		barray = new boolean[img.getWidth()][img.getHeight()];
		
		pxWidth = w/img.getWidth();
		pxHeight = h/img.getHeight();
		
		MouseHelper mh = new MouseHelper(this);
		super.addMouseListener(mh);
		super.addMouseMotionListener(mh);
		
		super.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_S && e.isControlDown() && !saving) saveFile();
				if(e.getKeyCode() == KeyEvent.VK_I && e.isControlDown()) invert();
			}

			@Override
			public void keyReleased(KeyEvent e) {}

			@Override
			public void keyTyped(KeyEvent e) {}
		});
		
		super.setFocusable(true);
		super.requestFocus();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D imgGraphics = img.createGraphics();
		for(int x=0; x<img.getWidth(); x++) {
			for(int y=0; y<img.getHeight(); y++) {
				if(barray[x][y]) imgGraphics.setColor(Color.BLACK);
				else imgGraphics.setColor(Color.WHITE);
				imgGraphics.fillRect(x, y, 1, 1);
			}
		}
		
		g.drawImage(img, 0, 0, super.getWidth(), super.getHeight(), null);
	}
	
	private void invert() {
		for(int i=0; i<barray.length; i++) {
			for(int j=0; j<barray[0].length; j++) {
				barray[i][j] = !barray[i][j];
			}
		}
		repaint();
	}

	public void handleMouseThing(double x, double y) {
		int posx = (int)((x-pxWidth/2)/pxWidth);
		int posy = (int)((y-pxHeight/2)/pxHeight);
		
		if(posx >= 0 && posx < barray.length && posy >= 0 && posy < barray[0].length) {
			barray[posx][posy] = true;
		}
		super.repaint();
	}
	
	public boolean[][] getBarray() {
		return barray;
	}
	
	private void saveFile() {
		saving = true;
		FileSaver.saveFile(barray);
		saving = false;
	}
	
	private class MouseHelper extends MouseAdapter {
		
		private DrawPanel client;
		
		public MouseHelper(DrawPanel dp) {
			client = dp;
		}
		
		@Override
		public void mouseDragged(MouseEvent me) {
			client.handleMouseThing(me.getX(), me.getY());
		}
		
		@Override
		public void mousePressed(MouseEvent me) {
			client.handleMouseThing(me.getX(), me.getY());
		}
	}
}
