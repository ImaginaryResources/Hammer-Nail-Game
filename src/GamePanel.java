import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener {
	
	private ImageIcon hammer, nail;
	private Timer nailTimer, hamTimer;
	private int points, nailPos, hamPos = 250;
	private boolean bounds = false;
	private Rectangle hamBounds = new Rectangle(200,hamPos,75,137), //75 137 
			nailBounds = new Rectangle(nailPos,500,53,123); //53 123
	
	public GamePanel() {
		
		nailTimer = new Timer(1, new NailListener());
		nailTimer.start();
		
		hamTimer = new Timer(250, new HamListener());
		hamTimer.start();
		
		hammer = new ImageIcon("res/hammer.png");
		nail = new ImageIcon("res/nail.png");
		
		setPreferredSize(new Dimension(Main.WIDTH, Main.HEIGHT));
		setBackground(Color.BLACK);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.GREEN);
		hammer.paintIcon(this, g, 200, hamPos);
		nail.paintIcon(this, g, nailPos, 500);
		
		hamBounds.setLocation(hamBounds.x, hamPos);
		//Hammer Hit Box
		//g.drawRect(hamBounds.x, hamBounds.y, hamBounds.width, hamBounds.height);
		
		nailBounds.setLocation(nailPos, nailBounds.y);
		//Nail Hit Box
		//g.drawRect(nailPos + 26, nailBounds.y, nailBounds.width, nailBounds.height);
		
		//Displays Points
		g.setFont(new Font("Calibri", Font.PLAIN, 75));
		g.setColor(Color.white);
		g.drawString("Points: " + points, 0, 75);
		
	}
	
	private class NailListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			
			int randNum = (int) (Math.random() * 2) + 1;
			
			if(nailPos < 400 && !bounds) {
				nailPos += randNum;
				if(nailPos > 400)
					bounds = true;
			}
			else {
				nailPos -= randNum;
				if(nailPos < 0)
					bounds = false;
			}
			repaint();
		}
		
	}
	
	private class HamListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(hamBounds.intersects(nailBounds)){
				points++;
				hamPos = 250;
				try {
					PlaySound.myPlay("file:res/hit.wav");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else if(!hamBounds.intersects(nailBounds) && hamPos == 365) {
				hamPos = 250;
				try {
					PlaySound.myPlay("file:res/miss.wav");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			repaint();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent event) {
		if(event.getKeyCode() == KeyEvent.VK_SPACE) {
			hamPos = 365;
		}
	}

	@Override
	public void keyReleased(KeyEvent event) {
	}

	@Override
	public void keyTyped(KeyEvent event) {
	}
}
