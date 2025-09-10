/*
 * Aadil Ahmed
 * May 29th
 * To make the main menu panel
 */

package gamepackage;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.*;

public class MenuPanel extends JPanel implements ActionListener, MouseListener {
    JButton startbtn, instructionsbtn, exit;

    public MenuPanel() {
        super();

        //imports and adds all images for background, and buttons then sets the size and adds an action listener
        ImageIcon backgroundImage = new ImageIcon("res/donkeykongbackground.png");
        Image img1 = backgroundImage.getImage();
        Image newimg1 = img1.getScaledInstance(1200, 1000, java.awt.Image.SCALE_SMOOTH);
        ImageIcon backImg = new ImageIcon(newimg1);
        JLabel backgroundlabel = new JLabel(backImg);
        setLayout(null);
        backgroundlabel.setBounds(0, 0, 1200, 1000);

        ImageIcon startimage = new ImageIcon("res/startpicture.png");
        Image img2 = startimage.getImage();
        Image newimg2 = img2.getScaledInstance(200, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon startImg = new ImageIcon(newimg2);

        ImageIcon instructionimage = new ImageIcon("res/instructionbtn.png");
        Image img3 = instructionimage.getImage();
        Image newimg3 = img3.getScaledInstance(360, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon instructionImg = new ImageIcon(newimg3);

        ImageIcon exitimage = new ImageIcon("res/exit.png");
        Image img4 = exitimage.getImage();
        Image newimg4 = img4.getScaledInstance(280, 70, java.awt.Image.SCALE_SMOOTH);
        ImageIcon exitImg = new ImageIcon (newimg4);
        
        startbtn = new JButton(startImg);
        startbtn.setBounds(250, 600, 200, 70);
        startbtn.setFocusPainted(false);
        startbtn.setContentAreaFilled(false);
        startbtn.setOpaque(false);
        Color btnborder = new Color(113, 61, 19);
        startbtn.setBorderPainted(true);
        startbtn.setBorder(new LineBorder(btnborder, 2));

        exit = new JButton(exitImg);
        exit.setFocusPainted(false);
        exit.setBounds(875, 850, 280, 70);
        exit.setContentAreaFilled(false);
        exit.setOpaque(false);
        exit.setBorderPainted(true);
        exit.setBorder(new LineBorder(btnborder, 2));

        instructionsbtn = new JButton(instructionImg);
        instructionsbtn.setFocusPainted(false);
        instructionsbtn.setBounds(700, 600, 380, 70);
        instructionsbtn.setContentAreaFilled(false);
        instructionsbtn.setOpaque(false);
        instructionsbtn.setBorderPainted(true);
        instructionsbtn.setBorder(new LineBorder(btnborder, 2));
        
        startbtn.addActionListener(this);
        instructionsbtn.addActionListener(this);
        exit.addActionListener(this);
        add(startbtn);
        add(instructionsbtn);
        add(exit);
        add(backgroundlabel);
        setVisible(true);
    }

    /*
     * pre: ActionEvent event
     * purpose: to check if a button is clicked
     * post: n/a
     */
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == instructionsbtn) {
            Main.navigation.show(Main.centerPanel, "Instructions");
        }
        if (event.getSource() == exit){
        	System.exit(0);
        }
        if (event.getSource() == startbtn){
        	Main.navigation.show(Main.centerPanel, "Game");
        	
        }
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getX() + ", " + e.getY());	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
