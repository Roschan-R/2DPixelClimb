/*
 * Aadil Ahmed
 * June 10th, 2024
 * To make the win panel
 */

package gamepackage;

import java.awt.Image;


import javax.swing.*;

public class WinPanel extends JPanel{

	public WinPanel(){
	    super();
	    //import and adds image for the winner background and sets the size
	    ImageIcon backgroundImage = new ImageIcon("res/winner.png");
	    Image img1 = backgroundImage.getImage();
	    Image newimg1 = img1.getScaledInstance(1200, 1000, java.awt.Image.SCALE_DEFAULT);
	    ImageIcon backImg = new ImageIcon(newimg1);
	    JLabel backgroundlabel = new JLabel(backImg);
	    setLayout(null);
	    backgroundlabel.setBounds(0, 0, 1200, 1000);
	    add(backgroundlabel); // Add the background label to the panel
	}
}



