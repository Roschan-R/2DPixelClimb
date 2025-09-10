/*
 * Roschan Ravindraraj
 * May 8th, 2024
 * Main class that runs the entire game
 */

package gamepackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.*;

import javax.swing.*;
public class Main {
    public static final int WINDOW_WIDTH = 1200, WINDOW_HEIGHT = 1000;
    public static final CardLayout navigation = new CardLayout();
    public static JPanel centerPanel;

    public static void main(String[] args) {
        JFrame frame = new JFrame("2D Donkey Kong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocationRelativeTo(null);

        centerPanel = new JPanel(navigation); // create a panel with a CardLayout
        frame.add(centerPanel, BorderLayout.CENTER); // make center panel in the middle of screen

        centerPanel.add(new MenuPanel(), "Menu"); // create a new menu panel and add to CardLayout centerPanel
        centerPanel.add(new Instructions(), "Instructions"); // create a new instruction panel and add to CardLayout centerPanel
        centerPanel.add(new GamePanel(), "Game"); // create a new game panel and add to CardLayout centerPanel
        centerPanel.add(new WinPanel(), "Win");
        centerPanel.add(new LosePanel(), "Lose");
        
        GamePanel gamePanel = new GamePanel(); // Create test panel
        centerPanel.add(gamePanel, "Game"); // add test panel to CardLayout centerPanel
        
        // Add a component listener to wait for the panel to be added to the frame
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                gamePanel.requestFocusInWindow(); // Set focus to the test panel when it's shown
            }
        });
        // Set the initial view to be the MenuPanel
        navigation.show(centerPanel, "Menu");

        frame.setVisible(true); // set frame to be visible
        frame.setResizable(false);

    }

}
