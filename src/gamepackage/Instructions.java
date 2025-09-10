/*
 * Aadil Ahmed
 * May , 2024
 * To make the instruction panel
 */



package gamepackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;


public class Instructions extends JPanel implements ActionListener{
	
	JButton back;
	
	public Instructions (){ //constructor for instruction panel
		super();
		
		setSize(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT); //sets size of panel
		
		//all this is to add the background for image, the text and also the go back button
		ImageIcon backinst = new ImageIcon("res/instructionimg.png"); 
		Image img1 = backinst.getImage();
		Image newimg1 = img1.getScaledInstance(1200, 1000, java.awt.Image.SCALE_SMOOTH ) ;
		ImageIcon instructionImg = new ImageIcon(newimg1);
		JLabel instlabel = new JLabel(instructionImg); //create image for background
		
		setLayout(null);
		instlabel.setBounds(0,0, 1200, 1000);
		
		ImageIcon inst = new ImageIcon("res/instructiontext.png");
		Image img2 = inst.getImage();
		Image newimg2 = img2.getScaledInstance(800, 550, java.awt.Image.SCALE_SMOOTH );
		ImageIcon insttextimg = new ImageIcon(newimg2);
		JLabel textlabel = new JLabel (insttextimg);
		
		
		textlabel.setBounds(200, 220, 800, 650);
		
		ImageIcon gob = new ImageIcon("res/goback.png");
		Image img3 = gob.getImage();
		Image newimg3 = img3.getScaledInstance(280, 70, java.awt.Image.SCALE_SMOOTH );
		ImageIcon gobimg = new ImageIcon(newimg3);
		
		back = new JButton(gobimg); //create back to menu button for allowing user to go back to start
		back.setBounds(470, 850, 280, 70);
        back.setFocusPainted(false);
        back.setContentAreaFilled(false);
        back.setOpaque(false);
        Color btnborder = new Color(157,0,255);
        back.setBorderPainted(true);
        back.setBorder(new LineBorder(btnborder, 2));
        back.addActionListener(this);
		
		add(back);
		add(textlabel);
		add(instlabel);
		
		
	}

	/*
	 * pre: ActionEvent event
	 * purpose: if pressed back, put to main menu
	 * post: n/a
	 */
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == back){
			Main.navigation.show(Main.centerPanel, "Menu");
		}
	}
	
}
