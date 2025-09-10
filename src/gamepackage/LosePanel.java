/*
 * Roschan Ravindraraj
 * June 9th, 2024
 * To make the lose screen
 */


package gamepackage;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;


public class LosePanel extends JPanel implements KeyListener{

	public LosePanel() {
        super();
        //import and adds image for lose screen and sets normal stuff
        ImageIcon backgroundImage = new ImageIcon("res/losescreen.png");
        Image img1 = backgroundImage.getImage();
        Image newimg1 = img1.getScaledInstance(1200, 1000, java.awt.Image.SCALE_DEFAULT);
        ImageIcon backImg = new ImageIcon(newimg1);
        JLabel backgroundlabel = new JLabel(backImg);
        setLayout(null);
        backgroundlabel.setBounds(0, 0, 1200, 1000);
        add(backgroundlabel); // Add the background label to the panel

        // Create an Action for restarting the program
        Action restartAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restartProgram();
            }
        };

        // Register the Action with the "R" key stroke
        getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "restart");
        getActionMap().put("restart", restartAction);
    }

	/*
	 * pre: KeyEvent e
	 * purpose: to check if R is pressed
	 * post: n/a
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_R) {
        	System.out.println("R");
        	restartProgram();
        } 
	}
	
	/*
	 * pre: KeyEvent e
	 * purpose: to restart the program
	 * post: n/a
	 */
	private static void restartProgram() {
	       try {
	           String java = System.getProperty("java.home") + "/bin/java";
	           String currentClassPath = System.getProperty("java.class.path");
	           String mainClass = Main.class.getName();

	           ProcessBuilder processBuilder = new ProcessBuilder(java, "-cp", currentClassPath, mainClass);
	           processBuilder.start();

	           System.exit(0); // Terminate the current instance
	       } 
	       catch (Exception e) {
	            e.printStackTrace();
	       }
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
