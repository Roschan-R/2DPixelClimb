/*
 * Roschan Ravindraraj
 * June , 2024
 * For the player class
 */

package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import gamepackage.GamePanel;
import gamepackage.KeyHandler;
import gamepackage.Main;
public class Player extends Entity {
    GamePanel gp; //importing game panel
    KeyHandler keyH; //importing key handler
    FireBall fb;

    public BufferedImage left1, left2, left3, right1, right2, right3, fallleft, fallright, jumpleft, jumpright, climbleft, climbright, idle; //character image
    private boolean jumping = false; //if character is jumping or not
    private final int jumpSpeed = 10; // Increased jump speed
    private final double gravity = 0.5; // Gravity constant
    private double ySpeed = 0; // Vertical velocity
    private boolean falling = true; //if character is falling or not
	BufferedImage image = null;

    public Player(GamePanel gp, KeyHandler keyH, FireBall fb) { //constructor for mario 
    	//setting up hitbox, game panel etc.
        this.gp = gp; 
        this.keyH = keyH;
        this.fb = fb;
        hitbox = new Rectangle(x + 11, y + 10, 22, 40);
        setDefaultValues();
        getPlayerImage();
    }

    /*
     * pre: n/a
     * purpose: to set the default values of mario
     * post: n/a
     */
    public void setDefaultValues() {
        x = 200;
        y = 851;
        speed = 3;
        direction = "right";
    }

    /*
     * pre: n/a
     * purpose: to load all player sprites
     * post: n/a
     */
    public void getPlayerImage() {
        try { //try and catch system to load all of the sprites
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/mariowalk1left.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/mariowalk2left.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/player/mariowalk3left.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/mariowalk1right.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/mariowalk2right.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/player/mariowalk3right.png"));
            fallleft = ImageIO.read(getClass().getResourceAsStream("/player/mariofallleft.png"));
            fallright = ImageIO.read(getClass().getResourceAsStream("/player/mariofallright.png"));
            jumpleft = ImageIO.read(getClass().getResourceAsStream("/player/mariojumpleft.png"));
            jumpright = ImageIO.read(getClass().getResourceAsStream("/player/mariojumpright.png"));
            climbleft = ImageIO.read(getClass().getResourceAsStream("/player/marioclimbleft.png"));
            climbright = ImageIO.read(getClass().getResourceAsStream("/player/marioclimbright.png"));
            idle = ImageIO.read(getClass().getResourceAsStream("/player/marioidle.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * pre: n/a
     * purpose: to update the player position and everything
     * post: n/a
     */
    public void update() {
        boolean moving = false; //sets moving automatically to false
        // new values
        int x2 = x; 
        int y2 = y;

        //if and else for A and D movement
        if (keyH.isDPressed()) {
            x2 += speed; 
            direction = "right"; 
            moving = true;  
        } 
        else if (keyH.isAPressed()) {
            x2 -= speed;
            direction = "left";
            moving = true;
        }
        //if statement used to check if the player is touching the ladder when holding W and S to let them up or down
        if (keyH.isWPressed() && gp.ladderCheck(x, y)) {
            y2 -= speed;
            moving = true;
            direction = "up";
        }
        if (keyH.isSPressed() && gp.ladderCheck(x, y)){
        	y2 += speed;
        	moving = true;
        	direction = "down";
        }
        
        
        
        //check if player enters the winner rectangle 
        Rectangle winArea = new Rectangle(632, 103, 90, 70);
        if (winArea.contains(x, y)) {
            Main.navigation.show(Main.centerPanel, "Win");
        }
        
        //if the player enters a y value greater than 900 (falls below floor) they lose
        if (y > 900){
        	Main.navigation.show(Main.centerPanel, "Lose");
        }
        
        // if statement which makes character idle if they are not moving
        if (moving == false) {
            direction = "idle";
        }

        
        if (keyH.isSpacePressed()) { //checks if space is pressed
            if (!jumping && !falling) { //if not actively jumping or falling, makes them jump
                jumping = true;
                ySpeed = -jumpSpeed;
                direction = "jump";
                image = jumpright;
                
            } 
            else if (jumping) { //if jumping, make it false and make the player fall
                jumping = false;
                falling = true;
                direction = "fall";
                image = fallright;
            }
        }


        if (!gp.ladderCheck(x, y)) { //if player not touching ladder, add gravity
            ySpeed += gravity;
            y2 += ySpeed;
        }
        

        boolean canMoveY = gp.collisionCheck(x, y2); //boolean for whether player can move to a y location 
        if (!canMoveY) { // if false, move player till they can stand 
            while (!gp.collisionCheck(x, y2 + 1) && !gp.barrierCheck(x, y2 + 1)) { 
                y2++;
            }
            
            
            if (gp.collisionCheck(x, y2 + 1) || gp.barrierCheck(x, y2 + 1)) { //if true, then stop falling and jumping
                jumping = false;
                falling = false;
                ySpeed = 0;
            }
        }

        boolean canMoveX = gp.collisionCheck(x2, y); //boolean for whether player can move to an x location

        if (canMoveX) { //if true, update x
            x = x2;
        }

        if (canMoveY) { //if true, update y
            y = y2;
        }
        
        
        Rectangle playerHitbox = new Rectangle(x + 11, y + 10, 22, 40);
        if (fb != null ) {  
        	if (playerHitbox.intersects(fb.firehitbox)){
        		System.out.println("connect");
        		Main.navigation.show(Main.centerPanel, "Lose");
        	}
        	else{
        		System.out.println("no");
        	}
        }
        
        
        //sprite counter used for every sprite user
        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum++;
            if (spriteNum > 3) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }




    /*
     * pre: Graphics2D g2
     * purpose: to draw the character sprites at the position and thing they are doing
     * post: n/a
     */
    public void draw(Graphics2D g2) {

    	switch (direction) { //switch case for every sprite
    		case "up":
    			if (spriteNum == 1) {
    				image = climbleft;
    			} 
    			if (spriteNum == 2) {
    				image = climbright;
    			}
    			if (spriteNum == 3){
    				image = climbleft;
    			}
    			break;
    		case "right":
    			if (spriteNum == 1) {
    				image = right1;
    			}
    			if (spriteNum == 2) {
    				image = right2;
    			}
    			if (spriteNum == 3) {
    				image = right3;
    			}
    			break;
    		case "left":
    			if (spriteNum == 1) {
    				image = left1;
    			}
    			if (spriteNum == 2) {
    				image = left2;
    			}
    			if (spriteNum == 3) {
    				image = left3;
    			}
    			break;
    		case "down":
    			if (spriteNum == 1) {
    				image = climbleft;
    			} 
    			if (spriteNum == 2) {
    				image = climbright;
    			}
    			if (spriteNum == 3){
    				image = climbleft;
    			}
    			break;
    		case "jump":
    			image = jumpright;
    		case "fall":
    			image = fallright;
    		case "idle":
    			image = idle;
    			break;
    	}
    	

        g2.drawImage(image, x, y, gp.playerWidth, gp.playerHeight, null);
        g2.setColor(Color.WHITE);
        
        
        Rectangle playerHitbox = new Rectangle(x + 11, y + 10, 22, 40); //  hitbox position and size
        //g2.draw(playerHitbox); // draw hitbox
        //g2.drawRect(650, 100, 52, 83);
    }
    
    /*
     * pre: n/a
     * purpose: return x of player
     * post: return x
     */
    public int getX (){
    	return x;
    }
    
    /*
     * pre: n/a
     * purpose: return y of player
     * post: return y
     */
    public int getY (){
    	return y;
    }
    
    /*
     * pre: n/a
     * purpose: return radius of player (hitbox)
     * post: return 5
     */
    public int getRadius() {
        // Return the maximum of width and height of the hitbox
        return 5;
    }
}
