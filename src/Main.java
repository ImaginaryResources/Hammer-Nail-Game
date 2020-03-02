
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Main {
	
	public static JFrame frame;
	public static int WIDTH = 500, HEIGHT = 800;
	public static Toolkit tools = Toolkit.getDefaultToolkit();
	public static Dimension size = tools.getScreenSize();
	public static Timer timer;
	
	public static void main (String[] args){
		frame = new JFrame("Hammer and Nail Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation((size.width/2) - (WIDTH/2)  , (size.height/2) - (HEIGHT/2));
		
		GamePanel gp = new GamePanel();
		
		frame.add(gp);
		frame.addKeyListener(gp);
		
		frame.pack();
		frame.setResizable(true);
		frame.setVisible(true);
	}
}
