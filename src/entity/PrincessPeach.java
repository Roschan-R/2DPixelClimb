/*
 * Roschan Ravindrarj
 * ICS4U
 * June, 2024
 */

package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;
import java.io.IOException;

import javax.imageio.ImageIO;

import gamepackage.GamePanel;

public class PrincessPeach extends Entity{
	
	GamePanel gp; //importing the game panel
	BufferedImage pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp9, pp10, pp11, pp12, pp13; //buffered image for dk
	
	public PrincessPeach(GamePanel gp) { //constructor for dk default values
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
            pp1 = ImageIO.read(getClass().getResourceAsStream("/player/pp1.png")); //load image
            pp2 = ImageIO.read(getClass().getResourceAsStream("/player/pp2.png")); //load image
            pp3 = ImageIO.read(getClass().getResourceAsStream("/player/pp3.png")); //load image
            pp4 = ImageIO.read(getClass().getResourceAsStream("/player/pp4.png")); //load image
            pp5 = ImageIO.read(getClass().getResourceAsStream("/player/pp5.png")); //load image
            pp6 = ImageIO.read(getClass().getResourceAsStream("/player/pp6.png")); //load image
            pp7 = ImageIO.read(getClass().getResourceAsStream("/player/pp7.png")); //load image
            pp9 = ImageIO.read(getClass().getResourceAsStream("/player/pp9.png")); //load image
            pp10 = ImageIO.read(getClass().getResourceAsStream("/player/pp10.png")); //load image
            pp11 = ImageIO.read(getClass().getResourceAsStream("/player/pp11.png")); //load image
            pp12 = ImageIO.read(getClass().getResourceAsStream("/player/pp12.png")); //load image
            pp13 = ImageIO.read(getClass().getResourceAsStream("/player/pp13.png")); //load image
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
	    if (spriteCounter > 20) { //if sprite counter goes above then it will change
	        spriteNum++;
	        if (spriteNum > 13) {
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

	    switch (direction) { //switch system to switch his sprites
	        case "right":
	            if (spriteNum == 1) {
	                image = pp1;
	            }
	            else if (spriteNum == 2){
	            	image = pp13;
	            }
	            else if (spriteNum == 3) {
	                image = pp2;
	            }
	            else if (spriteNum == 4) {
	                image = pp12;
	            }
	            else if (spriteNum == 5){
	            	image = pp3;
	            }
	            else if (spriteNum == 6) {
	                image = pp5;
	            }
	            else if (spriteNum == 7) {
	                image = pp4;
	            }
	            else if (spriteNum == 8) {
	                image = pp1;
	            } 
	            else if (spriteNum == 9) {
	                image = pp6;
	            }
	            else if (spriteNum == 10) {
	                image = pp7;
	            } 
	            else if (spriteNum == 11) {
	                image = pp9;
	            } 
	            else if (spriteNum == 12) {
	                image = pp10;
	            } 
	            else if (spriteNum == 13) {
	                image = pp11;
	            } 
	            
	            break;
	    }

	    g2.drawImage(image, 632, 103, 90, 90, null); //draw the image
	}
}	