/*
 * Roschan Ravindraraj
 * May , 2024 
 * For the game panel class
 */

package gamepackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import entity.DK;
import entity.FireBall;
import entity.Player;
import entity.PrincessPeach;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread; // game thread
    KeyHandler keyH = new KeyHandler(); //importing the key handler
    FireBall fb;
 
    
    Player mario = new Player(this, keyH, fb); //creating the player
    DK donkeyk = new DK(this); //creating donkey kong
    PrincessPeach prp = new PrincessPeach(this); //creating princess peach

    int playerSpeed = 4; //setting the player screen
    public BufferedImage levelImage; //buffered image for the level
    public int [][] levelData; //2d integer array that keeps pixel and level data
    //player width and height
    public int playerWidth = 44; 
    public int playerHeight = 44;

    //array for the fireballs, the intervals and the amount that have spawned
    private List <FireBall> fireballs = new ArrayList<>();
    private int fireballSpawnInterval = 120; //timer for when the fireball spawns
    private int fireballSpawnCounter = 0; //counter that counts up for the spawn
    Rectangle firehitbox;
    
    public GamePanel() { //constructor for game panel
        super();

        //try and catch to get the level image, and then putting the pixel RGB into the 2d integer level data array
        try {
            levelImage = ImageIO.read(new File("res/levelonemap2.png"));
            levelData = new int[levelImage.getWidth()][levelImage.getHeight()];
            for (int x = 0; x < levelImage.getWidth(); x++) {
                for (int y = 0; y < levelImage.getHeight(); y++) {
                    levelData[x][y] = levelImage.getRGB(x, y);
                }
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        this.setLayout(null);
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.setDoubleBuffered(true);

        this.addKeyListener(keyH);
        this.startGameThread();
        this.addComponentListener(new ComponentAdapter() {
        	
        	/*
        	 * pre: ComponentEvent e
        	 * purpose: to make sure panel is focused
        	 */
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                requestFocusInWindow(); // Set focus to the test panel when it's shown
            }});
    }

    /*
     * pre: n/a
     * purpose: starts the game thread
     * post: n/a
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /*
     * pre: n/a
     * purpose: to start the game clock and creates FPS and also keeps updating the character positions and draws entities
     * post: n/a
     */
    public void run() {
        double drawInterval = 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) { //if game thread is not null
            update(); // update character position
            repaint(); // draw character

            try { //found in video but it is the game time which allows everything to work
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) { //if time left is less than 0, set to 0
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } 
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * pre: n/a
     * purpose: to update the entities
     */
    public void update() {
        mario.update(); //updates mario
        donkeyk.update(); //updates donkeykong
        prp.update();

        fireballSpawnCounter++; //increments the fireball spawn counter
        if (fireballSpawnCounter >= fireballSpawnInterval) { //if statement that spawns the fireballs when it is above the fireball interval
            spawnFireBall(); //spawn a fireball
            fireballSpawnCounter = 0; //set counter to 0
        }

        Iterator<FireBall> iterator = fireballs.iterator(); //list to iterate through the fireballs
        while (iterator.hasNext()) { //if there is a next iteration, then the while look will continue
            FireBall fireball = iterator.next(); //creates the object for the next iteration
            fireball.update(); //creates the fireball

            if (fireball.getX() == 253 && fireball.getY() == 876) { //if the fireball is greater than 900, it deletes the fireball
                iterator.remove(); 
            }
         
        }
        
    }

    /*
     * pre: n/a
     * purpose: to spawn the fireball
     * post: n/a
     */
    public void spawnFireBall() {
        int x = 350; //starting x
        int y = 275; //starting y
        int radius = 20; //radius of fireball
        double xspeed = 4.5; // x speed
        double yspeed = 2.0; // y speed
        

        FireBall fireball = new FireBall(x, y, xspeed, yspeed, radius); //creating object
        //fireball.getBallImage(); //getting image for object
        fireballs.add(fireball); //add the fireball to array list
    }

    /*
     * pre: n/a
     * purpose: to paint the characters and fireballs
     * post: n/a
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (levelImage != null) { //draws the level image if it is not null
            g2.drawImage(levelImage, 0, 0, this);
        }

        mario.draw(g2); //draws mario
        donkeyk.draw(g2); //draws donkey kong
        prp.draw(g2); //draws princess peach

        for (FireBall fireball : fireballs) {
            fireball.draw(g2);
            firehitbox = new Rectangle((int) fireball.getX() - fireball.getRadius(), (int) fireball.getY() - fireball.getRadius(), 2 * fireball.getRadius(), 2 * fireball.getRadius());
            g2.setColor(Color.WHITE);
            //g2.draw(firehitbox);
            if (mario.hitbox.intersects(firehitbox)){
            	Main.navigation.show(Main.centerPanel, "Lose");
            }
        }

        g2.dispose();
    }

    /*
     * pre: int x, int y
     * purpose: collision checker, checks if player hitbox is touching certain pixel
     * post: returns true or false
     */
    public boolean collisionCheck(int x, int y) {

    	//loop checks each pixel that goes inside marios hitbox
        for (int i = 0; i < mario.hitbox.width; i++) {
            for (int j = 0; j < mario.hitbox.height; j++) {
                int pixelColour = levelData[x + i][y + j]; //makes a 2d array of pixel colours in hitbox
                if (pixelColour == new Color(255, 27, 72).getRGB()) { //if pixel colour in hitbox equals red floor, collision is detected and returns false
                    return false;
                }
            }
        }
        return true; //returns true if there is no collision 	
    }
    
    
    /*
     * pre: int x, int y
     * purpose: to check if it is a barrier
     * post: returns true or false
     */
    public boolean barrierCheck(int x, int y) {
        
    	//loop checks each pixel that goes inside marios hitbox
        for (int i = 0; i < playerWidth; i++) {
            for (int j = 0; j < playerHeight; j++) {
                int pixelColour = levelData[x + i][y + j]; //makes a 2d array of pixel colours in hitbox
                if (pixelColour == new Color(151, 1, 1).getRGB()) { //if pixel colour equal bottom of floor, it is a barrier and collision is detected (returns false)
                    return false;
                }
            }
        }
        return true; //returns true if there is no collision 
    }


    /*
     * pre: int x, int y
     * purpose: to check if player is touching ladder
     * post: returns true or false
     */
    public boolean ladderCheck(int x, int y) {
    	//creates the boundary where the ladder can be using players size
        int ldl = x;
        int ldr = x + playerWidth;
        int ldt = y;
        int ldb = y + playerHeight;

        for (int i = ldl; i < ldr; i++) { //for loop that checks each pixel colour
            for (int j = ldt; j < ldb; j++) {
                int pixelColor = levelData[i][j]; //makes a 2d with all pixel colours
                if (pixelColor == new Color(255, 255, 255).getRGB()) { //if white is detected in player hitbox pixels, they are touching a ladder
                    return true;
                }
            }
        }
        return false; //returns false if there is no ladder detected
    }
    
	
    
}
