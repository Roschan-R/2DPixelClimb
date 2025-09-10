/*
 * Roschan Ravindraraj
 * June , 2024
 * For donkey kong
 */

package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import gamepackage.GamePanel;

public class DK extends Entity{
	
	GamePanel gp; //importing the game panel
	BufferedImage dkleft, dkright; //buffered image for dk
	
	public DK(GamePanel gp) { //constructor for dk default values
        this.gp = gp;
        getDKImage();
        direction = "right";
    }	
	
	/*
	 * pre: n/a
	 * purpose: to load donkey kong images
	 * post: n/a
	 */
	public void getDKImage() {
        try {
            dkleft = ImageIO.read(getClass().getResourceAsStream("/player/dkleft.png")); //load image
            dkright = ImageIO.read(getClass().getResourceAsStream("/player/dkright.png")); //load image
        
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	/*
	 * pre: n/a
	 * purpose: to update donkey kong sprites
	 * post: n/a
	 */
	public void update(){
	    spriteCounter++; //increase sprite counter
	    if (spriteCounter > 30) { //if sprite counter goes above then it will change
	        spriteNum++;
	        if (spriteNum > 2) {
	            spriteNum = 1;
	        }
	        spriteCounter = 0; //set it back to 0
	    }
	}
	
	/*
	 * pre: Graphics2D g2
	 * purpose: to draw donkey kong sprite and images
	 * post: n/a
	 */
	public void draw(Graphics2D g2) {
	    BufferedImage image = null;
	    //if and else if to change the image based on sprite #
	    if (spriteNum == 1) {
	    	image = dkleft;
	    } 
	    else if (spriteNum == 2) {
	    	image = dkright;
	    }
	    

	    g2.drawImage(image, 280, 203, 100, 100, null); //draw the image
	}
}	
